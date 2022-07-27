package sut.civilization.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import sut.civilization.Controller.GameControllers.LandController;
import sut.civilization.Model.Classes.*;
import sut.civilization.Model.ModulEnums.NationType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler extends Thread {
    private final Socket clientSocket;
    private final Socket serverSideSocket;
    private Pair<DataInputStream,DataOutputStream> serverHandleStream;
    private Pair<DataInputStream,DataOutputStream> clientHandleStream;
    private User ownerUser;

    private HashMap<String,String> isReadyForGame = new HashMap<>();

    public RequestHandler(Socket socket,Socket serverSideSocket) {
        this.serverSideSocket = serverSideSocket;
        this.clientSocket = socket;

        try {
            clientHandleStream = new Pair<>(new DataInputStream(clientSocket.getInputStream()),new DataOutputStream(clientSocket.getOutputStream()));
            serverHandleStream = new Pair<>(new DataInputStream(serverSideSocket.getInputStream()),new DataOutputStream(serverSideSocket.getOutputStream()));

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
                    this.sendResponseToClient(response.toJson());
                    Game.instance.saveUserDatabase();
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
                return this.gameHandler(request);
            case "server" :
                this.serverHandler(request);
                break;
            case "scoreBoard" :
                this.scoreBoardHandler(request);
            case "social":
                return this.socialRequestsHandler(request);
            case "gameMenu" :
                return this.gameMenuRequestHandler(request);
            case "chat" :
                return this.chatRequestHandler(request);
        }
        return null;
    }

    private Response chatRequestHandler(Request request) {
        if (request.getHeader().equals("updateChat")) {
            Chat updateChat = (Chat) new XStream().fromXML(request.getToken("chat"));
            if (updateChat == null) return null;
            boolean flag = false;

            for (Chat chat : Game.instance.getLoggedInUser().getChats()) {
                if (chat.equals(updateChat)){
                    for (User user : chat.getUsers()) {
                        getUserByName(user.getUsername()).getChats().remove(chat);
                        getUserByName(user.getUsername()).getChats().add(updateChat);
                    }
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                for (User user : updateChat.getUsers()) {
                    getUserByName(user.getUsername()).getChats().remove(updateChat);
                    getUserByName(user.getUsername()).getChats().add(updateChat);
                }
            }
        }

        if (request.getHeader().equals("updateAllChats")) {
            getUserByName(request.getToken("userName")).setChats((ArrayList<Chat>) new XStream().fromXML(request.getToken("chats")));
        }

        if (request.getHeader().equals("getChats")){
            Response response = new Response("updateChats");
            response.addData("chats",new XStream().toXML(getUserByName(request.getToken("userName")).getChats()));
            return response;
        }

        return null;
    }


    private Response gameMenuRequestHandler(Request request) {
        if (request.getHeader().equals("startGameRequest")) {
            ArrayList<String> users = (ArrayList<String>) new XStream().fromXML(request.getToken("users"));
            int temp = 0;
            Response overAllResponse = new Response("game will start.");
            ArrayList<RequestHandler> connectedPeopleInGame = new ArrayList<>();
            ArrayList<User> userArrayList = new ArrayList<>();
            getUserByName(request.getToken("owner")).setNation(new Nation(NationType.getNationByName(request.getToken("nation"))));
            for (String user : users) {
                userArrayList.add(getUserByName(user));
                for (RequestHandler connectedUser : ConnectionController.getConnectedUsers()) {
                    if (connectedUser.ownerUser.getUsername().equals(user) && !connectedUser.ownerUser.getUsername().equals(request.getToken("owner"))) {
                        connectedPeopleInGame.add(connectedUser);
                        temp++;

                        Response response = new Response("startGameRequest");
                        response.addData("users", new XStream().toXML(users));
                        response.addData("owner",request.getToken("owner"));
                        connectedUser.sendUpdateToClient(response.toJson());
                    }
                }
            }

            if (temp != users.size() - 1)
                overAllResponse = new Response("connection with one user in lost!");

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            for (RequestHandler requestHandler : connectedPeopleInGame) {
                if (requestHandler.isReadyForGame.get(request.getToken("owner")) == null || requestHandler.isReadyForGame.get(request.getToken("owner")).equals("no"))
                    overAllResponse = new Response("at least one user didn't accept!");
            }
            System.out.println(userArrayList);
            if (overAllResponse.getStatusCode() == 200){
                Game.instance.setPlayersInGame(userArrayList);
                this.sendMapToAllUsers(users);
            }

            return overAllResponse;
        }

        if (request.getHeader().equals("startGameAnswer")) {
            this.isReadyForGame.put(request.getToken("owner"),request.getToken("answer"));
            getUserByName(this.ownerUser.getUsername()).setNation(new Nation(NationType.getNationByName(request.getToken("nation"))));
        }

        return null;
    }

    private Response socialRequestsHandler(Request request) {
        if (request.getHeader().equals("addFriendRequest")){
            ServerDataBase.getInstance().addFriendRequest(request.getToken("to"),request);
            return new Response("request sent to " + request.getToken("to"));
        }

        if (request.getHeader().equals("sendFriendRequests")){
            Response response = new Response("updateFriendRequestList");
            HashMap<String,ArrayList<Request>> requests = ServerDataBase.getInstance().getAddFriendRequestTree();
            response.addData("requests",new XStream().toXML(requests.get(request.getToken("userName"))));
            return response;
        }

        if (request.getHeader().equals("answerFriendRequest")) {
            ServerDataBase.getInstance().getAddFriendRequestTree().get(request.getToken("userName")).removeIf(userName -> userName.getToken("to").equals(request.getToken("userName")) && userName.getToken("from").equals(request.getToken("target")));
            if (ServerDataBase.getInstance().getAddFriendRequestTree().get(request.getToken("userName")).size() == 0)
                ServerDataBase.getInstance().getAddFriendRequestTree().remove(request.getToken("userName"));

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
            response.addData("requests",new XStream().toXML(requests));
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

        if (request.getHeader().equals("searchPlayer")){
            User target = getUserByName(request.getToken("targetName"));
            if (target.getUsername().equals(""))
                return new Response("no such user exists!");

            Response response = new Response("user found.");
            response.addData("user",new XStream().toXML(target));
            return response;
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

            HashMap<String,String> avatarLocations = new HashMap<>();
            HashMap<String,Integer> scores = new HashMap<>();
            HashMap<String,Long> lastWins = new HashMap<>();

            for (User user : Game.instance.getUsers()) {
                avatarLocations.put(user.getUsername(),user.getAvatarLocation());
                scores.put(user.getUsername(),user.getScore());
                lastWins.put(user.getUsername(),user.getLastWinTime());
            }
            response.addData("avatarLocations",new XStream().toXML(avatarLocations));
            response.addData("scores",new XStream().toXML(scores));
            response.addData("lastWins",new XStream().toXML(lastWins));

            response.addData("userNames",new XStream().toXML(userNames));
            this.sendResponseToClient(response.toJson());
        }
    }
    private void serverHandler(Request request) {
        if (request.getHeader().equals("userSetter")) {
            this.setUsers(request);
        }

        if (request.getHeader().equals("userLogout")){
            Response response = new Response(new MainController().logoutUser(this.ownerUser));
            if (response.getStatusCode() == 200)
                this.ownerUser = null;
            this.sendResponseToClient(response.toJson());
        }
    }
    private Response gameHandler(Request request) {
        if (request.getHeader().equals("serverUpdateDataBase"))
            this.updateDataBase(request);

        if (request.getHeader().equals("getMap"))
            this.sendMap(getUserByName(request.getToken("userName")),Game.instance.map);

        if (request.getHeader().equals("getOnlineUsers")){
            ArrayList<User> users = new ArrayList<>();
            for (RequestHandler connectedUser : ConnectionController.getConnectedUsers()) {
                if (connectedUser.ownerUser != null && connectedUser.ownerUser.isOnline())
                    users.add(connectedUser.ownerUser);

            }
            Response response = new Response("updateOnlineUsers");
            response.addData("users",new XStream().toXML(users));
            return response;
        }

        return null;
    }
    private void sendMapToAllUsers(ArrayList<String> userNames) {
        Game.instance.map = LandController.mapInitializer();

        for (String userName : userNames) {
            this.sendMap(getUserByName(userName),Game.instance.map);
        }
    }

    private void sendMap(User user, Land[][] map) {

        Response response = new Response("setMap");
        XStream xStream = new XStream();

        response.addData("map",xStream.toXML(map));
        response.addData("players",xStream.toXML(Game.instance.getPlayersInGame()));
        response.addData("subTurn", Game.instance.getSubTurn());

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
        Game.instance.map = (Land[][]) new XStream().fromXML(request.getToken("map"));
        Game.instance.setPlayersInGame((ArrayList<User>) new XStream().fromXML(request.getToken("players")));
        Game.instance.setSubTurn(Integer.parseInt(request.getToken("subTurn")));

        Response response = new Response( "clientUpdateDataBase");
        response.addData("map",new XStream().toXML(Game.instance.map));
        response.addData("players",new XStream().toXML(Game.instance.getPlayersInGame()));
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
            case ("getAllUsers") : {
                response = new Response("...");
                response.addData("users",new XStream().toXML(Game.instance.getUsers()));
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

    public Socket getServerSideSocket() {
        return serverSideSocket;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }
}
