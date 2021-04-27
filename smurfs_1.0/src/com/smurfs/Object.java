package com.smurfs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

abstract public class Object {
    private int point;
    private int numberOfSpawns;
    private int spawnTime;
    private int appear;
    private int [][] Location;

    int tileSize;
    public BufferedImage objectImage;

    Map map;

    public Object(int point, int numberOfSpawns, int spawnTime, int appear, Map m) {
        this.point = point;
        this.numberOfSpawns = numberOfSpawns;
        this.spawnTime = spawnTime;
        this.appear = appear;
        this.map = m;
        tileSize = 80;
    }

    public int[][] getLocation() {
        return Location;
    }

    public void setLocation(int[][] location) {
        Location = location;
    }

    public void setObjectImage(BufferedImage objectImage) {
        this.objectImage = objectImage;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getNumberOfSpawns() {
        return numberOfSpawns;
    }

    public void setNumberOfSpawns(int numberOfSpawns) {
        this.numberOfSpawns = numberOfSpawns;
    }

    public int getSpawnTime() {
        return spawnTime;
    }

    public void setSpawnTime(int spawnTime) {
        this.spawnTime = spawnTime;
    }

    public int getAppear() {
        return appear;
    }

    public void setAppear(int appear) {
        this.appear = appear;
    }

    public void findPoints(int i) {
            int r = ThreadLocalRandom.current().nextInt(1, 10);
            int c = ThreadLocalRandom.current().nextInt(1, 12);
            if(map.isNavigable(r, c)) {
                Location[i][0] = r;
                Location[i][1] = c;
            }
            else {
                findPoints(i);
            }
    }

    public void setPoints() {
        for(int i = 0;  i < numberOfSpawns; i++) {
            findPoints(i);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////

    public void update() {

    }

    public void draw(Graphics2D g) {
        for(int i = 0;  i < numberOfSpawns; i++) {
            g.drawImage(objectImage,
                    (Location[i][1]*tileSize),
                    (Location[i][0]*tileSize),
                    60,
                    60,
                    null);
        }

    }
}
