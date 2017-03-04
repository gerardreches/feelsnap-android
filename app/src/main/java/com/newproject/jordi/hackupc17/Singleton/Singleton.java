package com.newproject.jordi.hackupc17.Singleton;

import com.newproject.jordi.hackupc17.Entities.User;

/**
 * Created by jordi on 4/3/17.
 */
public class Singleton {
    private User user;
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
