package sut.civilization.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionController extends Controller{

    private final ServerSocket serverSocket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Socket socket;

    public ConnectionController(final int SERVER_PORT) {
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException e) {
            System.out.println("server port is busy cannot run on this port !");
            throw new RuntimeException();
        }

    }

    public void ListenForNewClient() {
        while (true) {
            try {
                socket = serverSocket.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                requestHandler.start();
            } catch (IOException e) {
                System.out.println("cannot accept user on : " + socket);
            }
        }
    }

}
