package sut.civilization.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Model.Classes.*;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RequestHandler extends Thread {
    private final Socket clientSocket;

    private final Socket serverSideSocket;

    private Pair<DataInputStream,DataOutputStream> serverHandleStream;

    private Pair<DataInputStream,DataOutputStream> clientHandleStream;

    private User ownerUser;


    public RequestHandler(Socket socket,Socket serverSideSocket) {
        this.serverSideSocket = serverSideSocket;
        this.clientSocket = socket;

        try {
            clientHandleStream = new Pair<>(new DataInputStream(clientSocket.getInputStream()),new DataOutputStream(clientSocket.getOutputStream()));
            serverHandleStream = new Pair<>(new DataInputStream(clientSocket.getInputStream()),new DataOutputStream(clientSocket.getOutputStream()));

        } catch (IOException e) {
            System.out.println("cannot start connection with : " + socket);
        }

    }

    @Override
    public void run() {
        Response response;
        while (true) {
            try {
                if ((response = handleRequest(Request.fromJson(this.listenForRequest()))) != null){
                    sendResponseToClient(response.toJson());
                    Game.instance.saveUserListToDatabase();
                }
            } catch (IOException e) {
                ConnectionController.getConnectedUsers().remove(this);
                System.out.println("connection with client " + clientSocket + " is Lost!");
                break;
            }
        }
    }

    private String listenForRequest() throws IOException{
        int length = 0;
        length = clientHandleStream.x.readInt();

        byte[] message = new byte[0];

        if (length > 0){
            message = new byte[length];
            clientHandleStream.x.readFully(message,0,message.length);
        }
        return new String(message);
    }

    public void sendResponseToClient(String message){
        System.out.println(message);
        try {
            clientHandleStream.y.writeInt(message.length());
            clientHandleStream.y.writeBytes(message);
            clientHandleStream.y.flush();
        } catch (IOException e) {
            System.out.println("cannot send data to client ! try again !");
        }
    }

    public void sendUpdateToClient(String message){
        try {
            serverHandleStream.y.writeInt(message.length());
            serverHandleStream.y.writeBytes(message);
            serverHandleStream.y.flush();
        } catch (IOException e) {
            System.out.println("cannot send data to client ! try again !");
        }
    }

    private Response handleRequest(Request request) {
        System.out.println(request);
        if (request == null || request.getSection() == null)
            return null;

        switch (request.getSection()) {
            case "login" :
                return loginHandler(request);
            case "profile":
                return profileHandler(request);
            case "game" :
                this.gameHandler(request);
                break;
            case "server" :
                this.serverHandler(request);
                break;
            case "scoreBoard" :
                this.scoreBoardHandler(request);
            case "social":
                return this.socialRequestsHandler(request);
        }
        return null;
    }

    private Response socialRequestsHandler(Request request) {
        if (request.getHeader().equals("addFriendRequest")){
            ServerDataBase.getInstance().addFriendRequest(request.getToken("to"),request);
            if (getUserByName(request.getToken("to")).isOnline()){
                Response response = new Response("addFriendRequest");
                response.addData("to",request.getToken("to"));
                response.addData("from",request.getToken("from"));
                Objects.requireNonNull(this.getRequestHandlerByOwner(request.getToken("to"))).sendUpdateToClient(response.toJson());
            }

            return new Response("request sent to " + request.getToken("to"));
        }

        if (request.getHeader().equals("sendFriendRequests")){
            Response response = new Response("updateFriendRequestList");
            HashMap<String,ArrayList<Request>> requests = ServerDataBase.getInstance().getAddFriendRequestTree();
            response.addData("requests",new Gson().toJson(requests.get(request.getToken("userName"))));
            return response;
        }

        if (request.getHeader().equals("answerFriendRequest")) {
            ServerDataBase.getInstance().getAddFriendRequestTree().get(request.getToken("userName")).removeIf(userName -> userName.getToken("to").equals(request.getToken("userName")) && userName.getToken("from").equals(request.getToken("target")));

            Response response;

            if (request.getToken("answer").equals("yes")) {
                getUserByName(request.getToken("userName")).getFriendsUserNames().add(request.getToken("target"));
                getUserByName(request.getToken("target")).getFriendsUserNames().add(request.getToken("userName"));
                response = new Response(request.getToken("target") + " successfully added to your friends list.");
            } else response = new Response("request rejected!");
            return response;
        }

        if (request.getHeader().equals("sendWaitingRequests")){
            ArrayList<Request> requests = new ArrayList<>();
            for (String s : ServerDataBase.getInstance().getAddFriendRequestTree().keySet()) {
                for (Request request1 : ServerDataBase.getInstance().getAddFriendRequestTree().get(s)) {
                    if (request1.getToken("from").equals(request.getToken("userName")))
                        requests.add(request1);
                }
            }

            Response response = new Response("updatingWaitingRequests");
            response.addData("requests",new Gson().toJson(requests));
            return response;
        }
        return null;
    }

    private Response profileHandler(Request request) {
        ProfileController profileController = new ProfileController();
        Game.instance.setLoggedInUser(getUserByName(request.getToken("userName")));
        String result = "";

        if (request.getHeader().equals("changeNickname"))
            result = profileController.changeNickname(new Pair<>(request.getToken("newNickname1"),request.getToken("newNickname2")),request.getToken("oldNickname"));

        if (request.getHeader().equals("changePassword"))
            result = profileController.changePassword(new Pair<>(request.getToken("newPassword1"),request.getToken("newPassword2")),request.getToken("oldPassword"));

        if (request.getHeader().equals("updateAvatarLocation")){
            result = profileController.updateAvatarLocation(request.getToken("location"));
        }
        return new Response(result);
    }

    private void scoreBoardHandler(Request request) {

        if (request.getHeader().equals("updateList")) {
            Response response = new Response("updateList");

            ArrayList<String> userNames = new ArrayList<>();

            for (User user : Game.instance.getUsers()) {
                user.setOnline(false);
            }
            for (RequestHandler connectedUser : ConnectionController.getConnectedUsers()) {
                if (connectedUser.ownerUser == null) continue;

                userNames.add(connectedUser.ownerUser.getUsername());
                connectedUser.ownerUser.setOnline(true);
            }
            Map<String,String> avatarLocations = new HashMap<>();
            Map<String,Integer> scores = new HashMap<>();
            Map<String,Long> lastWins = new HashMap<>();

            for (User user : Game.instance.getUsers()) {
                avatarLocations.put(user.getUsername(),user.getAvatarLocation());
                scores.put(user.getUsername(),user.getScore());
                lastWins.put(user.getUsername(),user.getLastWinTime());
            }
            response.addData("avatarLocations",avatarLocations);
            response.addData("scores",scores);
            response.addData("lastWins",lastWins);

            response.addData("userNames",userNames);
            this.sendResponseToClient(response.toJson());
        }
    }

    private void serverHandler(Request request) {
        if (request.getHeader().equals("userSetter")) {
            this.setUsers(request);
        }
    }

    private void gameHandler(Request request) {
        if (request.getHeader().equals("updateDataBase"))
            this.updateDataBase(request);

        if (request.getHeader().equals("startGameRequest"))
            this.operateStartGameRequest(request);

        if (request.getHeader().equals("getMap"))
            this.sendMap(getUserByName(request.getToken("userName")),Game.instance.map);


    }

    private void operateStartGameRequest(Request request) {
        ArrayList<String> userNames = new Gson().fromJson(request.getToken("users"), new TypeToken<ArrayList<String>>(){}.getType());

        for (String userName : userNames) {
            Game.instance.getPlayersInGame().add(getUserByName(userName));
        }
        Game.instance.setSubTurn(0);
        this.sendMapToAllUsers(userNames);

    }

    private void sendMapToAllUsers(ArrayList<String> userNames) {
        Game.instance.map = new LandController().mapInitializer();

        for (String userName : userNames) {
            this.sendMap(getUserByName(userName),Game.instance.map);
        }
    }

    private void sendMap(User user, Land[][] map) {

        Response response = new Response("setMap");
        response.addData("map",map);
        response.addData("players",new Gson().toJson(Game.instance.getPlayersInGame()));
        response.addData("subTurn", String.valueOf(Game.instance.getSubTurn()));

        for (RequestHandler connectedUser : ConnectionController.getConnectedUsers()) {
            if (connectedUser.ownerUser.getUsername().equals(user.getUsername())){
                connectedUser.sendUpdateToClient(response.toJson());
            }
        }
    }

    private void setUsers(Request request) {
        this.ownerUser = this.getUserByName(request.getToken("userName"));
        this.ownerUser.setOnline(true);

    }

    private User getUserByName(String userName) {
        for (User user : Game.instance.getUsers()) {
            if (user.getUsername().equals(userName))
                return user;
        }

        return new User("","","");
    }

    private void updateDataBase(Request request) {
        Game.instance = new Gson().fromJson(request.getToken("dataBase"),Game.class);
        Response response = new Response( "updateDataBase");
        response.addData("map",new Gson().toJson(Game.instance.map));
        response.addData("players",new Gson().toJson(Game.instance.getPlayersInGame()));
        response.addData("subTurn", String.valueOf(Game.instance.getSubTurn()));

        for (RequestHandler connectedUser : ConnectionController.getConnectedUsers()) {
            for (User user : Game.instance.getPlayersInGame()) {
                if (connectedUser.ownerUser.getUsername().equals(user.getUsername())) {
                    connectedUser.sendUpdateToClient(response.toJson());
                }
            }
        }
    }

    private Response loginHandler(Request request) {
        LoginController loginController = new LoginController();
        Response response = new Response(-1, "not processed!");
        switch (request.getHeader()) {
            case ("createUser") : {
                response = new Response(loginController.createUser(new User(request.getToken("userName"),request.getToken("password"),request.getToken("nickName"))));
                break;
            }
            case ("loginUser") : {
                response = new Response(loginController.loginUser(new User(request.getToken("userName"),request.getToken("password"),"")));
                break;
            }
            case ("enterAsGuest") : {
                response = new Response(loginController.enterAsGuest());
                break;
            }
        }
        return response;
    }

    private RequestHandler getRequestHandlerByOwner(String userName) {
        for (RequestHandler connectedUser : ConnectionController.getConnectedUsers()) {
            if (connectedUser.ownerUser != null && connectedUser.ownerUser.getUsername().equals(userName))
                return connectedUser;
        }
        return null;
    }
}
