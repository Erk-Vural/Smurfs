package com.smurfs;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Character{
    public static int score;

    private int moveSpeed;
    public BufferedImage playerImage;

    private Map map;

    public Player(int row, int col, Map m,
                  int playerID, String playerName, String playerType) {
        super(playerID, playerName, playerType,row,col);
        this.map = m;
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public static int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPlayerImage(BufferedImage playerImage) {
        this.playerImage = playerImage;
    }

    public String showScore() {

        return null;
    }

    public void move(int r, int c) {
         if(map.isNavigable(row + r, col + c )){
            row += r;
            col += c;
            score -= 1;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////

    public void update() {

    }

    public void draw(Graphics2D g) {
        g.drawImage(playerImage,
                (col* map.getTileSize()),
                (row* map.getTileSize()),
                60,
                80,
                null);
    }
}
