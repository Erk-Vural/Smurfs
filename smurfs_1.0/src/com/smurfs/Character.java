package com.smurfs;

import java.awt.*;

public class Character extends Location {
    private int ID;
    private String name;
    private String type;

    public Character(int ID, String name, String type, int row, int col) {
        super(row,col);
        this.ID = ID;
        this.name = name;
        this.type = type;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void shortestPath() {

    }
}
