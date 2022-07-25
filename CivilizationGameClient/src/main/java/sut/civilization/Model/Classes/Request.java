package sut.civilization.Model.Classes;

import com.google.gson.Gson;

import java.util.HashMap;

public class Request {
    private String section;
    private String header;
    private HashMap<String,String> tokens;

    public Request(String section,String header) {
        this.header = header;
        this.section = section;
        this.tokens = new HashMap<>();
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static Request fromJson(String message){
        return new Gson().fromJson(message,Request.class);
    }

    public void addToken(String key,String value) {
        this.tokens.put(key,value);
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public HashMap<String, String> getTokens() {
        return tokens;
    }

    public void setTokens(HashMap<String, String> tokens) {
        this.tokens = tokens;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getToken(String key) {
        return this.tokens.get(key);
    }
}
