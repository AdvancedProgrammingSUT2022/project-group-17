package Model.Users;

import Model.Nations.Nation;

public class User {

    private String username;
    private String password;
    private String nickname;
    private int score;
    private Nation nation;

    public User(String username, String password, String nickname){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.score = 0;
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

    @Override
    //you can change this as you wish
    public String toString() {
        return nickname + " : " + score;
    }
}
