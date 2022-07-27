package sut.civilization.View.Graphical;

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
import sut.civilization.Civilization;
import sut.civilization.Controller.GameControllers.GameController;
import sut.civilization.Enums.Menus;
import sut.civilization.Model.Classes.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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


    public void BackToMainMenu() {
        Game.instance.changeScene(Menus.MAIN_MENU);
    }

    @FXML
    private void initialize() {

        if (Game.instance.getLoggedInUser().getChats() != null) {
            int i = 0;
            for (Chat chat : Game.instance.getLoggedInUser().getChats()) {
                listAChatByChat(chat, i);
                i += 2;
            }
        }
    }

    public void send() {
        if (!messageInput.getText().isBlank() && selectedChat != null) {
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

            Message message1 = new Message(Game.instance.getLoggedInUser(), messageText.getText(), time.getText());
            selectedChat.addMessage(message1);

            messageBox.setOnMouseClicked(mouseEvent -> {
                Popup popup = new Popup();
                Button delete = new Button("Delete");
                Button edit = new Button("Edit");
                VBox vBox = new VBox(delete, edit);
                delete.setOnMouseClicked(mouseEvent1 -> {
                    selectedChat.removeMessage(message1);
                    loadMessages(selectedChat);
                    popup.hide();
                });
                edit.setOnMouseClicked(mouseEvent1 -> {
                    TextField editTextField = new TextField(message1.getMessageText());
                    Button editButton = new Button("Edit");
                    editButton.setOnMouseClicked(mouseEvent2 -> {
                        message1.setMessageText(editTextField.getText());
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
    }

    public void sendByEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) send();
    }

    public void search() {
        allChats.getChildren().clear();
        int i = 0;
        for (User user : Game.instance.getUsers()) {
            if (user.getUsername().startsWith(search.getText()) && user != Game.instance.getLoggedInUser()) {
                listAChatByUser(user, i);
                i += 2;
            }
        }

        searchHBox.getChildren().add(0, new ImageView(new Image(
                (Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/ex.png")
                ))));
        searchHBox.getChildren().get(0).setOnMouseClicked(mouseEvent -> {
            allChats.getChildren().clear();
            initialize();
            searchHBox.getChildren().remove(0);
        });
    }

    private void listAChatByUser(User user, int i) {
        //fixme avatar
        ImageView avatar = new ImageView(new Image(user.getAvatarLocation()));
        avatar.setFitWidth(60);
        avatar.setFitHeight(60);
        Label name = new Label(user.getUsername());
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

        chatPreview.setOnMouseClicked(mouseEvent -> selectChatByUser(user, i));
    }

    private void listAChatByChat(Chat chat, int i) {
        //fixme avatar
        Image image;
        Label name;
        if (chat instanceof ChatRoom) {
            image = new Image(Civilization.class.getResourceAsStream("/sut/civilization/Images/otherIcons/public.png"));
            name = new Label(((ChatRoom) chat).getName());
        } else {
            if (chat.getUsers().get(0) == Game.instance.getLoggedInUser()) {
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
                if (chat.getUsers().contains(user) && !(chat instanceof ChatRoom)) {
                    selectedChat = chat;
                    isChatFound = true;
                    loadMessages(selectedChat);
                }

        if (!isChatFound) {
            ArrayList<User> users = new ArrayList<>(Arrays.asList(Game.instance.getLoggedInUser(), user));
            selectedChat = new Chat(users);
            Game.instance.getLoggedInUser().addChat(selectedChat);
            user.addChat(selectedChat);
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
                if (message.getSender() != Game.instance.getLoggedInUser())
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
                if (user.getUsername().startsWith(groupSearch.getText()) && user != Game.instance.getLoggedInUser()) {
                    searchMemberForGroup(user, groupMemberList, i);
                    i += 2;
                }
            }
        });

        createButton.setOnMouseClicked(mouseEvent -> {
            if (selectedMembersForGroup.isEmpty())
                showPopUp(Game.instance.getCurrentScene().getWindow(), "Select at least one user!");
            else {
                //TODO name of the group
                selectedMembersForGroup.add(Game.instance.getLoggedInUser());
                ChatRoom chatRoom = new ChatRoom("group", selectedMembersForGroup);
                for (User user : selectedMembersForGroup)
                    user.addChat(chatRoom);
                allChats.getChildren().clear();
                initialize();
                selectChatByChat(chatRoom, 2 * Game.instance.getLoggedInUser().getChats().size() - 2);
                wholeBorderPane.setEffect(null);
                createGroupPopup.hide();
                selectedMembersForGroup.clear();
            }
        });

        cancelButton.setOnMouseClicked(mouseEvent -> {
            wholeBorderPane.setEffect(null);
            createGroupPopup.hide();
            selectedMembersForGroup.clear();
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
            if (selectedMembersForGroup.contains(user)) {
                groupMemberList.getChildren().get(i).setStyle("-fx-background-color: white;");
                selectedMembersForGroup.remove(user);
            } else {
                groupMemberList.getChildren().get(i).setStyle("-fx-background-color: green;");
                selectedMembersForGroup.add(user);
            }
        });
    }

}
