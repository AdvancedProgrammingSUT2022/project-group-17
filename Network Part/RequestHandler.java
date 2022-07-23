package sut.civilization.Controller;

import sut.civilization.Model.Classes.Request;
import sut.civilization.Model.Classes.Response;
import sut.civilization.Model.Classes.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;

public class RequestHandler extends Thread {
    private final Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public RequestHandler(Socket socket) {

        this.clientSocket = socket;
        try {
            inputStream = new DataInputStream(clientSocket.getInputStream());
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("cannot start connection with : " + socket);
            ;
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                sendResponse(Objects.requireNonNull(handleRequest(this.listenForInput())));
            } catch (IOException e) {
                System.out.println("connection with client " + clientSocket + " is Lost!");
                break;
            }
        }
    }

    private Request listenForInput() throws IOException {
        return Request.fromJson(inputStream.readUTF());
    }


    private void sendResponse(Response response) throws IOException {
        outputStream.writeUTF(response.toJson());
    }

    private Response handleRequest(Request request) {
        switch (request.getSection()) {
            case "Login" :
                return loginHandler(request);
            case "Profile":

                break;
            case "Game":
                break;

            case "DataBase":
                this.updateDataBase(request);
                break;

        }
        return null;
    }

    private void updateDataBase(Request request) {

    }

    private Response loginHandler(Request request) {
        LoginController loginController = new LoginController();
        Response response = new Response(-1, "not processed!");
        String result = "";
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




}
