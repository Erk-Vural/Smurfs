package com.smurfs;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TembelSirin extends Player {

    public TembelSirin(int row, int col, Map m, int playerID, String playerName, String playerType) throws IOException {
        super(row, col, m, playerID, playerName, playerType);
        setMoveSpeed(1);
        setScore(20);
        setPlayerImage(ImageIO.read(new File("images/tembel-sirin.png")));
    }

    @Override
    public String showScore() {
        return "Score: " + TembelSirin.getScore();
    }
}
