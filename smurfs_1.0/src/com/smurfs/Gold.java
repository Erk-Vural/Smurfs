package com.smurfs;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Gold extends Object{


    public Gold( Map m) throws IOException {
        super(5, 5, 10, 5, m);
        setObjectImage(ImageIO.read(new File("images/gold.png")));
        setLocation(new int[5][2]);
    }
}
