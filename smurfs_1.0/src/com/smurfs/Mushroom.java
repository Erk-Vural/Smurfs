package com.smurfs;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Mushroom extends Object{

    public Mushroom(Map m) throws IOException {
        super(50, 1, 20, 7, m);
        setObjectImage(ImageIO.read(new File("images/mushroom.png")));
        setLocation(new int[1][2]);
    }
}
