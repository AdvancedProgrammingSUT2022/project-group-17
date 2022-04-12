package Model.Users;

import Model.Nations.Nation;

public class User {

    private String username;
    private String password;
    private String nickname;
    private int score;
    private Nation nation;

    public User(String username, String password, String nickname, Nation nation){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.nation = nation;
        this.score = 0;
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
}
