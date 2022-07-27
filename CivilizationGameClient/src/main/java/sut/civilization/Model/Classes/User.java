package sut.civilization.Model.Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class User {
    private String username;
    private String password;
    private String nickname;
    private int score;
    private Nation nation;
    private String avatarLocation;
    private boolean isOnline;
    private Date lastTimeOnline;
    private long lastWinTime;
    private ArrayList<Chat> chats;

    private ArrayList<String> friendsUserNames;

    public void setChats(ArrayList<Chat> chats) {
        this.chats = new ArrayList<>(chats);
    }

    private int gameState = 0;
    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = 0;
        this.lastWinTime = 0;
        this.isOnline = false;
        this.avatarLocation = "sut/civilization/Images/Avatars/(2).png";
        this.chats = new ArrayList<>();
        this.friendsUserNames = new ArrayList<>();

    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getAvatarLocation() {
        return avatarLocation;
    }

    public void setAvatarLocation(String avatarLocation) {
        this.avatarLocation = avatarLocation;
    }

    public Date getLastTimeOnline() {
        return lastTimeOnline;
    }

    public void setLastTimeOnline(Date lastTimeOnline) {
        this.lastTimeOnline = lastTimeOnline;
    }

    public ArrayList<String> getFriendsUserNames() {
        if (friendsUserNames == null) friendsUserNames = new ArrayList<>();
        return friendsUserNames;
    }

    public void setFriendsUserNames(ArrayList<String> friendsUserNames) {
        this.friendsUserNames = friendsUserNames;
    }

    public long getLastWinTime() {
        return lastWinTime;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void addChat(Chat chat) {
        if (chats == null) chats = new ArrayList<>();
        chats.add(chat);
    }

    public void setLastWinTime(long lastWinTime) {
        this.lastWinTime = lastWinTime;
    }

    public void setNation(Nation nation) {
        this.nation = nation;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public int getScore() {
        return score;
    }

    public Nation getNation() {
        return nation;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", nation=" + nation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    public int getGameState() {
        return gameState;
    }
}
