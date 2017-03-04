package com.newproject.jordi.hackupc17.Entities;

/**
 * Created by jordi on 4/3/17.
 */

public class User {

    String id;
    String token;

    public User(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
}
