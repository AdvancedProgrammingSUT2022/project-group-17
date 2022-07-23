package sut.civilization.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Platform;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.*;
import sut.civilization.View.Graphical.GamePlayController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionListener extends Thread {
    private final Pair<DataInputStream, DataOutputStream> serverHandleStream;

    public ConnectionListener(Socket serverSideSocket) {
        try {
            this.serverHandleStream = new Pair<>(new DataInputStream(serverSideSocket.getInputStream()),new DataOutputStream(serverSideSocket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.setDaemon(true);
    }
    @Override
    public void run() {
        while (true){
            try {
                handleUpdate(Response.fromJson(listenForUpdateFromServer()));
            } catch (IOException e) {
                System.out.println("connection with server is lost ! closing app ...");
                break;
            }
        }
    }

    private String listenForUpdateFromServer() throws IOException{
        int length;
        length = serverHandleStream.x.readInt();

        byte[] message = new byte[0];

        if (length > 0){
            message = new byte[length];
            serverHandleStream.x.readFully(message,0,message.length);
        }
        return new String(message);
    }

    private void handleUpdate(Response updateResponse) {
        if (updateResponse == null) {
            return;
        }
        if (updateResponse.getMessage().equals("setMap")){
            Game.instance.map = new Gson().fromJson((String) updateResponse.getDataToken("map"), Land[][].class);
            Game.instance.setPlayersInGame(new Gson().fromJson((String) updateResponse.getDataToken("players"),new TypeToken<ArrayList<User>>(){}.getType()));
            GameController.setCurrentTurnUser(Game.instance.getPlayersInGame().get(0));
            Game.instance.setSubTurn(0);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Game.instance.changeScene(Menus.GAME_MENU);
                }
            });

        }
    }


}

