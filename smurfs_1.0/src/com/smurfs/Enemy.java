package com.smurfs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Enemy extends Character{
    private int tileSize;

    private int height;
    private int width;
    private int Prow;
    private int Pcol;
    private int startRow;
    private int startCol;

    private int moveSpeed;
    public BufferedImage playerImage;
    private Color pathColor;

    private Map map;
    private Player player;

    final static int TRIED = 2;
    final static int PATH = 3;

    int [][] path;
    int[][] pathMap;
    int [][] M;

    public Enemy(int row, int col, Map m,Player p,
                 int enemyID, String enemyName, String enemyType) {
        super(enemyID, enemyName, enemyType,row,col);
        this.map = m;
        this.player = p;
        //this.row = row;
        //this.col = col;
        this.M = m.getMap();
        this.tileSize = 80;
        this.startRow=row;
        this.startCol=col;
    }


    public int[][] getPath() {
        return path;
    }

    public void setPathColor(Color pathColor) {
        this.pathColor = pathColor;
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
    public int getStartRow() {
        return startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(int moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public BufferedImage getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(BufferedImage playerImage) {
        this.playerImage = playerImage;
    }

    public void move(int r, int c) {
        if(map.isNavigable(row + r, col + c )){
            row += r;
            col += c;
        }
    }

    public void moveEnemy(int [][] path, boolean isMoved) {
        if(col == player.getCol() && row < player.getRow()) { // Down
            if (path[1][0] == 3) {
                move(1, 0);
            }
        }else if(col == player.getCol() && row > player.getRow()) { // Up
            if (path[1][0] == 3) {
                move(-1, 0);
            }
        }else if(col < player.getCol() && row == player.getRow()) { // Right
            if (path[0][1] == 3) {
                move(0, 1);
            }
        }else if(col > player.getCol() && row == player.getRow()) { // Left
            if (path[1][0] == 3) {
                move(1, -1);
            }
        } else if(col < player.getCol() &&  row < player.getRow()) { // Right-Down
            if(path[0][1]  == 3){
                move(0,1);
            }else if(path[1][0]  == 3){
                move(1,0);
            }
        }else if(col > player.getCol() &&  row < player.getRow()) { // Left-Down
            if(path[0][1]  == 3){
                move(0,-1);
            }else if(path[1][0]  == 3){
                move(1,0);
            }
        }else if(col < player.getCol() &&  row > player.getRow()) { // Right-Up
            if(path[0][1] == 3){
                move(0,1);
            }else if(path[1][0] == 3){
                move(-1,0);
            }
        }else if(col > player.getCol() &&  row > player.getRow()) { // Left-Up
            if(path[0][1] == 3){
                move(0,-1);
            }else if(path[1][0] == 3){
                move(-1,0);
            }
        }
        if(moveSpeed == 2 && !isMoved){
            moveEnemy(path, true);
        }
    }

    @Override
    public void shortestPath() {
        height = Math.abs(player.getRow() - row)+1;
        width = Math.abs(player.getCol() - col)+1;
        Prow = row;
        Pcol = col;

        path = new int[height][width];
        pathMap = new int[height][width];

        for(int i = 0; i < height; i++) {
            for(int j =0; j < width; j++) {
                if(col <= player.getCol() &&  row <= player.getRow()) { // Right-Down
                    pathMap[i][j] = M[i+row][j+col];

                }else if(col > player.getCol() &&  row < player.getRow()) { // Left-Down
                    pathMap[i][j] = M[row+i][col-j];
                }else if(col <= player.getCol() &&  row >= player.getRow()) { // Right-Up
                    pathMap[i][j] = M[row-i][col+j];
                }else if(col > player.getCol() &&  row > player.getRow()) { // Left-Up
                    pathMap[i][j] = M[row-i][col-j];
                }
            }
        }
        traverse(0,0);

        System.out.println(Arrays.deepToString(path));
    }

    private boolean traverse(int i, int j) {
        if (!isValid(i,j)) {
            return false;
        }

        if ( isEnd(i, j) ) {
            path[i][j] = PATH;
            return true;
        } else {
            path[i][j] = TRIED;
        }

        // Up
        if (traverse(i - 1, j)) {
            path[i-1][j] = PATH;
            return true;
        }
        // Right
        if (traverse(i, j + 1)) {
            path[i][j + 1] = PATH;
            return true;
        }
        // Down
        if (traverse(i + 1, j)) {
            path[i + 1][j] = PATH;
            return true;
        }
        // Left
        if (traverse(i, j - 1)) {
            path[i][j - 1] = PATH;
            return true;
        }

        return false;
    }

    private boolean isEnd(int i, int j) {
        return i == height - 1 && j == width - 1;
    }

    private boolean isValid(int i, int j) {
        if (inRange(i, j) && isOpen(i, j) && !isTried(i, j)) {
            return true;
        }
        return false;
    }

    private boolean isOpen(int i, int j) {
        return pathMap[i][j] == 1;
    }

    private boolean isTried(int i, int j) {
        return path[i][j] == TRIED;
    }

    private boolean inRange(int i, int j) {
        return inHeight(i) && inWidth(j);
    }

    private boolean inHeight(int i) {
        return i >= 0 && i < height;
    }

    private boolean inWidth(int j) {
        return j >= 0 && j < width;
    }

    private void drawPath(int height,int width, int[][] path,Graphics2D g) {
        g.setColor(pathColor);
        for(int i = 0; i < height; i++) {
            for(int j =0; j < width; j++) {
                if(path[i][j] == 3) {
                    if(col <= player.getCol() &&  row <= player.getRow()) { // Right-Down
                        g.fillRect( (j+Pcol) * tileSize,
                                (i+Prow)* tileSize,
                                tileSize, tileSize);

                    }else if(col >= player.getCol() &&  row <= player.getRow()) { // Left-Down
                        g.fillRect( (Pcol-j) * tileSize,
                                (i+Prow)* tileSize,
                                tileSize, tileSize);
                    }else if(col <= player.getCol() &&  row >= player.getRow()) { // Right-Up
                        g.fillRect( (Pcol+j) * tileSize,
                                (Prow-i)* tileSize,
                                tileSize, tileSize);
                    }else if(col >= player.getCol() &&  row >= player.getRow()) { // Left-Up
                        g.fillRect( (Pcol-j) * tileSize,
                                (Prow-i)* tileSize,
                                tileSize, tileSize);
                    }
                    player.draw(g);
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////

    public void update() {

    }

    public void draw(Graphics2D g) {
        g.drawImage(playerImage,
                (col* map.getTileSize()),
                (row* map.getTileSize()),
                60,
                80,
                null);

        drawPath(height,width,path,g);
    }
}
