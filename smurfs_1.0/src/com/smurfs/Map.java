package com.smurfs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Map {
    Scanner scan;

    int rows;
    int columns;

    int [][] map;

    public Map(int tileSize) throws IOException {
        this.tileSize = tileSize;
        image = ImageIO.read(new File("images/sirine.png"));
        map = ReadFile.getMap();
        rows = 11;
        columns = 13;
    }

    private int tileSize;
    private BufferedImage image;

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getTileSize() {
        return tileSize;
    }


    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public boolean isNavigable(int r, int c){
        int rc = map[r][c];
        if(rc == 0){
            return false;
        }
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////////

    public void update() {

    }

    public void draw(Graphics2D g) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                int rc = map[i][j];
                if(rc == 0) {
                    g.setColor(Color.gray);
                }
                if(rc == 1) {
                    if((i == 5 && j == 0) || (i == 0 && j == 3)
                            || (i == 0 && j == 10) || (i == 10 && j == 3)){
                        g.setColor(Color.pink);
                    }
                    else{
                        g.setColor(Color.white);
                    }
                }
                g.fillRect( j * tileSize,
                        i* tileSize,
                        tileSize, tileSize);
            }
        }
        g.drawImage(image, (12*tileSize), (7*tileSize),
                80, 80, null);
    }
}
