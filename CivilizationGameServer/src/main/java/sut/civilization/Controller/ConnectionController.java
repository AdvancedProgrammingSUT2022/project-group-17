package sut.civilization.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionController extends Controller{

    private final ServerSocket serverSocket;
    private static final ArrayList<RequestHandler> connectedUsers = new ArrayList<>();


    public ConnectionController(final int SERVER_PORT) {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            System.out.println("server port is busy cannot run on this port !");
            throw new RuntimeException();
        }

    }

    public void listenForNewClient() {
        Socket socket = null;
        Socket secondSocket = null;
        while (true) {
            try {
                socket = serverSocket.accept();
                secondSocket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket,secondSocket);
                requestHandler.start();
                connectedUsers.add(requestHandler);
                System.out.println("new client connected on : " + socket + ".");
            } catch (IOException e) {
                System.out.println("cannot accept user on : " + socket);
            }
        }
    }
    public static ArrayList<RequestHandler> getConnectedUsers() {
        return connectedUsers;
    }
}
