package sut.civilization.View.Graphical;

import com.thoughtworks.xstream.XStream;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Window;
import javafx.util.Duration;
import sut.civilization.Civilization;
import sut.civilization.Controller.ConnectionController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class ChatMenuController extends ViewController {
    public BorderPane wholeBorderPane;
    public TextArea messageInput;
    public TextField search;
    public VBox allMessages;
    public ScrollPane messagesScrollPane;
    public VBox allChats;
    public HBox searchHBox;
    private Chat selectedChat = null;
    private ArrayList<User> selectedMembersForGroup = new ArrayList<>();

    private Timeline updateTimeLine;

    public void BackToMainMenu() {
        Game.instance.changeScene(Menus.MAIN_MENU);
        updateTimeLine.stop();
    }

    @FXML
    private void initialize() {
        updateTimeLine = new Timeline();
        updateTimeLine.setCycleCount(-1);
        updateTimeLine.getKeyFrames().add(new KeyFrame(Duration.millis(8000), e -> updateChats()));
        updateTimeLine.play();

//        Request request = new Request("chat","updateAllChats");
//        request.addToken("chats",new XStream().toXML(Game.instance.getLoggedInUser().getChats()));
//        request.addToken("userName",Game.instance.getLoggedInUser().getUsername());
//        ConnectionController.getInstance().sendUpdateToServer(request.toJson());

        init();
    }

    private void updateChats() {
        Request request = new Request("chat","getChats");
        request.addToken("userName",Game.instance.getLoggedInUser().getUsername());
        Response response = ConnectionController.getInstance().sendRequestToServer(request.toJson());
        Game.instance.getLoggedInUser().setChats((ArrayList<Chat>) new XStream().fromXML((String) response.getDataToken("chats")));
        init();
        if (selectedChat != null){
            selectedChat = getChatByChat(selectedChat);
            loadMessages(selectedChat);
        }

    }

    private void init() {
        if (getUserByName(Game.instance.getLoggedInUser().getUsername()).getChats() != null) {
            allChats.getChildren().clear();
            System.out.println(getUserByName(Game.instance.getLoggedInUser().getUsername()).getChats());
            int i = 0;
            for (Chat chat : getUserByName(Game.instance.getLoggedInUser().getUsername()).getChats()) {
                listAChatByChat(chat, i);
                i += 2;
            }
        }
    }

    public void send() {
        if (messageInput.getText().isBlank() || selectedChat == null)
            return;

        VBox messageBox = new VBox();
        Label name = new Label(Game.instance.getLoggedInUser().getUsername());
        name.getStyleClass().add("name");
        Label messageText = new Label(messageInput.getText());
        messageText.getStyleClass().add("message");
        Label time = new Label(LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute());
        time.getStyleClass().add("time");
        messageBox.getChildren().add(name);
        messageBox.getChildren().add(messageText);
        messageBox.getChildren().add(time);
        messageBox.getStyleClass().add("messageBox");
        messageBox.setMaxWidth(Region.USE_PREF_SIZE);
        messageBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        VBox.setMargin(messageBox, new Insets(10, 0, 0, 0));

        ImageView avatar = new ImageView(new Image(Game.instance.getLoggedInUser().getAvatarLocation()));
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);

        HBox messageHBox = new HBox();
        messageHBox.getChildren().add(messageBox);
        messageHBox.getChildren().add(avatar);
        messageHBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageHBox.setStyle("-fx-padding: 0 0 15 0;");

        allMessages.getChildren().add(messageHBox);
        messageInput.setText("");
        messagesScrollPane.setVvalue(1);

        Message message1 = new Message(getUserByName(Game.instance.getLoggedInUser().getUsername()), messageText.getText(), time.getText());
        selectedChat.addMessage(message1);

        updateThisChat(selectedChat);

        messageBox.setOnMouseClicked(mouseEvent -> {
            Popup popup = new Popup();
            Button delete = new Button("Delete");
            Button edit = new Button("Edit");
            VBox vBox = new VBox(delete, edit);
            delete.setOnMouseClicked(mouseEvent1 -> {
                selectedChat.removeMessage(message1);
                loadMessages(selectedChat);
                updateThisChat(selectedChat);
                popup.hide();
            });


            vBox.getStylesheets().add("/sut/civilization/StyleSheet/ChatMenu.css");
            popup.getContent().add(vBox);
            popup.setAutoHide(true);
            popup.show(Game.instance.getCurrentScene().getWindow());
            edit.setOnMouseClicked(mouseEvent1 -> {
                TextField editTextField = new TextField(message1.getMessageText());
                Button editButton = new Button("Edit");
                editButton.setOnMouseClicked(mouseEvent2 -> {
                    message1.setMessageText(editTextField.getText());
                    loadMessages(selectedChat);
                    updateThisChat(selectedChat);
                    popup.hide();
                });
                ((VBox) popup.getContent().get(0)).getChildren().add(editTextField);
                ((VBox) popup.getContent().get(0)).getChildren().add(editButton);
            });
        });
    }

    private void updateThisChat(Chat chat) {
        Request request = new Request("chat", "updateChat");
        request.addToken("chat", new XStream().toXML(chat));
        ConnectionController.getInstance().sendUpdateToServer(request.toJson());
    }

    public void sendByEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) send();
    }

    public void search() {
        allChats.getChildren().clear();
        int i = 0;
        for (User user : Game.instance.getUsers()) {
            if (getUserByName(user.getUsername()).getUsername().startsWith(search.getText()) && !getUserByName(user.getUsername()).equals(Game.instance.getLoggedInUser())) {
                listAChatByUser(user, i);
                i += 2;
            }
        }

        searchHBox.getChildren().add(0, new ImageView(new Image(
                (Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/ex.png")
                ))));
        searchHBox.getChildren().get(0).setOnMouseClicked(mouseEvent -> {
            allChats.getChildren().clear();
            init();
            searchHBox.getChildren().remove(0);
        });
    }

    private void listAChatByUser(User user, int i) {
        //fixme avatar
        ImageView avatar = new ImageView(new Image(getUserByName(user.getUsername()).getAvatarLocation()));
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);
        Label name = new Label(getUserByName(user.getUsername()).getUsername());
        name.getStyleClass().add("name");
        name.getStyleClass().add("chatPreview");
        //fixme message preview
        Label message = new Label("message preview...");
        message.getStyleClass().add("chatPreview");
        VBox nameAndMessageVBox = new VBox(name, message);
        //fixme message number
        Label messageNumber = new Label("10");
        messageNumber.getStyleClass().add("chatPreview");
        //fixme time
        Label time = new Label("10:20");
        time.getStyleClass().add("chatPreview");
        VBox numberAndTimeVBox = new VBox(messageNumber, time);
        numberAndTimeVBox.setAlignment(Pos.CENTER);
        HBox chatPreview = new HBox(avatar, nameAndMessageVBox, numberAndTimeVBox);

        allChats.getChildren().add(chatPreview);
        allChats.getChildren().add(new Separator());

        chatPreview.setOnMouseClicked(mouseEvent -> selectChatByUser(getUserByName(user.getUsername()), i));
    }

    private void listAChatByChat(Chat chat, int i) {
        //fixme avatar
        Image image;
        Label name;
        if (chat instanceof ChatRoom) {
            image = new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/public.png"));
            name = new Label(((ChatRoom) chat).getName());
        } else {
            image = new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/user-profile.png"));
            if (chat.getUsers().get(0).equals(Game.instance.getLoggedInUser())) {
                name = new Label(chat.getUsers().get(1).getUsername());
                image = new Image(chat.getUsers().get(1).getAvatarLocation());
            } else {
                name = new Label(chat.getUsers().get(0).getUsername());
                image = new Image(chat.getUsers().get(0).getAvatarLocation());
            }
        }
        ImageView avatar = new ImageView(image);
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);
        name.getStyleClass().add("name");
        name.getStyleClass().add("chatPreview");
        //fixme message preview
        Label message = new Label("message preview...");
        message.getStyleClass().add("chatPreview");
        VBox nameAndMessageVBox = new VBox(name, message);
        //fixme message number
        Label messageNumber = new Label("10");
        messageNumber.getStyleClass().add("chatPreview");
        //fixme time
        Label time = new Label("10:20");
        time.getStyleClass().add("chatPreview");
        VBox numberAndTimeVBox = new VBox(messageNumber, time);
        numberAndTimeVBox.setAlignment(Pos.CENTER);
        HBox chatPreview = new HBox(avatar, nameAndMessageVBox, numberAndTimeVBox);

        allChats.getChildren().add(chatPreview);
        allChats.getChildren().add(new Separator());

        chatPreview.setOnMouseClicked(mouseEvent -> selectChatByChat(chat, i));
    }

    private void selectChatByUser(User user, int i) {
        boolean isChatFound = false;
        if (Game.instance.getLoggedInUser().getChats() != null)
            for (Chat chat : Game.instance.getLoggedInUser().getChats())
                if (chat.getUsers().contains(getUserByName(user.getUsername())) && !(chat instanceof ChatRoom)) {
                    selectedChat = chat;
                    isChatFound = true;
                    loadMessages(selectedChat);
                }

        if (!isChatFound) {
            ArrayList<User> users = new ArrayList<>(Arrays.asList(Game.instance.getLoggedInUser(), getUserByName(getUserByName(user.getUsername()).getUsername())));
            selectedChat = new Chat(users);
            Game.instance.getLoggedInUser().addChat(selectedChat);
            getUserByName(getUserByName(user.getUsername()).getUsername()).addChat(selectedChat);
            loadMessages(selectedChat);
        }

        for (int j = 0; j < allChats.getChildren().size(); j++)
            allChats.getChildren().get(j).setStyle("-fx-background-color: white;");
        allChats.getChildren().get(i).setStyle("-fx-background-color: gray;");

    }

    private void selectChatByChat(Chat chat, int i) {

        selectedChat = chat;
        loadMessages(chat);

        for (int j = 0; j < allChats.getChildren().size(); j++)
            allChats.getChildren().get(j).setStyle("-fx-background-color: white;");
        allChats.getChildren().get(i).setStyle("-fx-background-color: gray;");
    }


    private void loadMessages(Chat chat) {
        allMessages.getChildren().clear();

        if (chat.getMessages() != null) {
            for (Message message : chat.getMessages()) {
                VBox messageBox = new VBox();
                Label name = new Label(message.getSender().getUsername());
                name.getStyleClass().add("name");
                Label messageText = new Label(message.getMessageText());
                messageText.getStyleClass().add("message");
                Label time = new Label(message.getTime());
                time.getStyleClass().add("time");
                messageBox.getChildren().add(name);
                messageBox.getChildren().add(messageText);
                messageBox.getChildren().add(time);
                messageBox.getStyleClass().add("messageBox");
                if (!message.getSender().equals(Game.instance.getLoggedInUser()))
                    messageBox.setStyle("-fx-background-color: #cbe6f7;");
                messageBox.setMaxWidth(Region.USE_PREF_SIZE);
                messageBox.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                VBox.setMargin(messageBox, new Insets(10, 0, 0, 0));

                if (message.getSender().equals(Game.instance.getLoggedInUser())) {
                    messageBox.setOnMouseClicked(mouseEvent -> {
                        Popup popup = new Popup();
                        Button delete = new Button("Delete");
                        delete.setOnMouseClicked(mouseEvent1 -> {
                            selectedChat.removeMessage(message);
                            loadMessages(selectedChat);
                            popup.hide();
                        });
                        Button edit = new Button("Edit");
                        VBox vBox = new VBox(delete, edit);
                        edit.setOnMouseClicked(mouseEvent1 -> {
                            TextField editTextField = new TextField(message.getMessageText());
                            Button editButton = new Button("Edit");
                            editButton.setOnMouseClicked(mouseEvent2 -> {
                                message.setMessageText(editTextField.getText());
                                loadMessages(selectedChat);
                                popup.hide();
                            });
                            ((VBox) popup.getContent().get(0)).getChildren().add(editTextField);
                            ((VBox) popup.getContent().get(0)).getChildren().add(editButton);
                        });

                        vBox.getStylesheets().add("/sut/civilization/StyleSheet/ChatMenu.css");
                        popup.getContent().add(vBox);
                        popup.setAutoHide(true);
                        popup.show(Game.instance.getCurrentScene().getWindow());
                    });


                }

                //fixme avatar
                ImageView avatar = new ImageView(new Image(message.getSender().getAvatarLocation()));
                avatar.setFitWidth(60);
                avatar.setFitHeight(60);

                HBox hBox = new HBox();
                hBox.getChildren().add(messageBox);
                hBox.getChildren().add(avatar);
                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                hBox.setStyle("-fx-padding: 0 0 15 0;");

                allMessages.getChildren().add(hBox);
                messagesScrollPane.setVvalue(1);
            }
        }
    }

    public void newGroup() {
        Window window = Game.instance.getCurrentScene().getWindow();
        Popup createGroupPopup = new Popup();
        TextField groupSearch = new TextField();
        groupSearch.setPrefWidth(200);
        groupSearch.setPadding(new Insets(10));
        ImageView searchIcon = new ImageView(new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/search.png")));
        searchIcon.setFitHeight(32);
        searchIcon.setFitWidth(32);
        HBox searchHBox = new HBox(groupSearch, searchIcon);
        VBox groupMemberList = new VBox();
        ScrollPane groupScrollPane = new ScrollPane(groupMemberList);
        Button cancelButton = new Button("cancel");
        Button createButton = new Button("Create");
        cancelButton.setStyle("-fx-background-color: red");
        createButton.setStyle("-fx-background-color: green");
        HBox buttonsHBox = new HBox(cancelButton, createButton);
        VBox popupVBox = new VBox(searchHBox, groupScrollPane, buttonsHBox);
        popupVBox.setPrefWidth(320);
        popupVBox.setPrefHeight(400);


        createGroupPopup.getContent().add(popupVBox);
        createGroupPopup.setX(window.getWidth() / 2);
        createGroupPopup.setY((window.getHeight() / 2) - 200);
        createGroupPopup.show(window);
        wholeBorderPane.setEffect(new Lighting());

        searchIcon.setOnMouseClicked(mouseEvent -> {
            groupMemberList.getChildren().clear();
            selectedMembersForGroup.clear();
            int i = 0;
            for (User user : Game.instance.getUsers()) {
                if (user.getUsername().startsWith(groupSearch.getText()) && !user.equals(Game.instance.getLoggedInUser())) {
                    searchMemberForGroup(getUserByName(user.getUsername()), groupMemberList, i);
                    i += 2;
                }
            }
        });

        createButton.setOnMouseClicked(mouseEvent -> {
            if (selectedMembersForGroup.isEmpty())
                showPopUp(Game.instance.getCurrentScene().getWindow(), "Select at least one user!");
            else {
                //TODO name of the group
                selectedMembersForGroup.add(getUserByName(Game.instance.getLoggedInUser().getUsername()));
                ChatRoom chatRoom = new ChatRoom("group", selectedMembersForGroup);
                for (User user : selectedMembersForGroup)
                    getUserByName(user.getUsername()).addChat(chatRoom);
                updateThisChat(chatRoom);
                allChats.getChildren().clear();
                init();
                selectChatByChat(chatRoom, 2 * getUserByName(Game.instance.getLoggedInUser().getUsername()).getChats().size() - 2);
                wholeBorderPane.setEffect(null);
                createGroupPopup.hide();
                selectedMembersForGroup.clear();
            }
        });

        cancelButton.setOnMouseClicked(mouseEvent -> {
            createGroupPopup.hide();
        });

    }


    private void searchMemberForGroup(User user, VBox groupMemberList, int i) {
        //fixme avatar
        ImageView avatar = new ImageView(new Image(user.getAvatarLocation()));
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);
        Label name = new Label(user.getUsername());
        name.getStyleClass().add("name");
        name.getStyleClass().add("chatPreview");
        HBox memberPreview = new HBox(avatar, name);
        memberPreview.setPrefWidth(300);

        groupMemberList.getChildren().add(memberPreview);
        groupMemberList.getChildren().add(new Separator());

        memberPreview.setOnMouseClicked(mouseEvent -> {
            if (selectedMembersForGroup.contains(getUserByName(user.getUsername()))) {
                groupMemberList.getChildren().get(i).setStyle("-fx-background-color: white;");
                selectedMembersForGroup.remove(getUserByName(user.getUsername()));
            } else {
                groupMemberList.getChildren().get(i).setStyle("-fx-background-color: green;");
                selectedMembersForGroup.add(getUserByName(user.getUsername()));
            }
        });
    }

    private Chat getChatByChat(Chat that) {
        for (User user : Game.instance.getUsers()) {
            for (Chat chat : user.getChats()) {
                if (chat.equals(that))
                    return chat;
            }
        }
        return null;
    }
}
