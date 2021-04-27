package com.smurfs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Azman extends Enemy{
    public Azman(int row, int col, Map m, Player p, int enemyID, String enemyName, String enemyType) throws IOException {
        super(row, col, m, p, enemyID, enemyName, enemyType);
        setMoveSpeed(1);
        setPlayerImage(ImageIO.read(new File("images/azman.png")));
        setPathColor(new Color(255, 165, 0,180));
    }
}


