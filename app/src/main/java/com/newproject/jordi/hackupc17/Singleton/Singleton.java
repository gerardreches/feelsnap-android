package com.newproject.jordi.hackupc17.Singleton;

/**
 * Created by jordi on 4/3/17.
 */
public class Singleton {
    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

}
