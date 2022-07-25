package sut.civilization.Model.Classes;

import com.google.gson.Gson;

import java.util.HashMap;

public class Response {
    private final int statusCode;
    private final String message;
    private final HashMap<String,Object> data;

    public Response(String message){
        if (message.charAt(message.length()-1) == '!')
            this.statusCode = 404;
        else this.statusCode = 200;
        this.message = message;
        this.data = new HashMap<>();
    }
    public Response(int statusCode,String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = new HashMap<>();

    }

    public void addData(String key,Object value) {
        this.data.put(key,value);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Object getDataToken(String key) {
        return data.get(key);
    }

    public String getMessage() {
        return message;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static Response fromJson(String json){
        return new Gson().fromJson(json,Response.class);
    }

    @Override
    public String toString() {
        return "Response{ " + this.toJson() + "}";
    }
}
