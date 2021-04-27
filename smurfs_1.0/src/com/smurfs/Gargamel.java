package com.smurfs;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Gargamel extends Enemy{
    public Gargamel(int row, int col, Map m, Player p, int enemyID, String enemyName, String enemyType) throws IOException {
        super(row, col, m, p, enemyID, enemyName, enemyType);
        setMoveSpeed(2);
        setPlayerImage(ImageIO.read(new File("images/gargamel.png")));
        setPathColor(new Color(0,255,255,180));
    }
}


