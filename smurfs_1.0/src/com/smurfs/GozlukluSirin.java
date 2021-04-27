package com.smurfs;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GozlukluSirin extends Player {


    public GozlukluSirin(int row, int col, Map m, int playerID, String playerName, String playerType) throws IOException {
        super(row, col, m, playerID, playerName, playerType);
        setMoveSpeed(2);
        setScore(20);
        setPlayerImage(ImageIO.read(new File("images/gozluklu-sirin.png")));
    }

    @Override
    public String showScore() {
        return "Score: " + GozlukluSirin.getScore();
    }
}
