package sut.civilization.Controller;

import sut.civilization.Model.Classes.Pair;
import sut.civilization.Model.Classes.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionController {
    private static final ConnectionController instance = new ConnectionController();
    private Socket clientSocket;

    private Socket serverReaderSocket;

    private Pair<DataInputStream,DataOutputStream> clientHandleStream;

    public void initConnection() {
        try {
            //192.168.204.133
            this.clientSocket = new Socket("192.168.192.121",7090);
            this.serverReaderSocket = new Socket("192.168.192.121",7090);

            this.clientHandleStream = new Pair<>(new DataInputStream(clientSocket.getInputStream()),new DataOutputStream(clientSocket.getOutputStream()));

            ConnectionListener connectionListener = new ConnectionListener(serverReaderSocket);
            connectionListener.start();

            System.out.println("connected to server.");

        } catch (IOException e) {
            System.out.println("cannot connect to this server trying again ...");
            initConnection();
        }

    }

    public Response sendRequestToServer(String message) {
        try {
            clientHandleStream.y.writeInt(message.length());
            clientHandleStream.y.writeBytes(message);
            clientHandleStream.y.flush();
            return Response.fromJson(listenForResponse());
        } catch (IOException e){
            System.out.println("cannot get response of " + message + " from server.");
        }
        return null;
    }

    public void sendUpdateToServer(String message) {
        try {
            clientHandleStream.y.writeInt(message.length());
            clientHandleStream.y.writeBytes(message);
            clientHandleStream.y.flush();
        } catch (IOException e){
            System.out.println("cannot get response of " + message + " from server.");
        }
    }

    private String listenForResponse() throws IOException{
        int length = 0;
        length = clientHandleStream.x.readInt();

        byte[] message = new byte[0];

        if (length > 0){
            message = new byte[length];
            clientHandleStream.x.readFully(message,0,message.length);
        }
        return new String(message);
    }

    public static ConnectionController getInstance(){
        return instance;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public Socket getServerReaderSocket() {
        return serverReaderSocket;
    }

    public void closeConnection() {
        try {
            clientSocket.close();
            serverReaderSocket.close();
            clientHandleStream.y.close();
            clientHandleStream.x.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
