package com.newproject.jordi.hackupc17.Entities;

/**
 * Created by jordi on 4/3/17.
 */

public class UserRanking {
    String name;
    String score;
    String position;
    int face;

    public UserRanking(String name, String score, String position, int face) {
        this.name = name;
        this.score = score;
        this.position = position;
        this.face = face;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }
}
