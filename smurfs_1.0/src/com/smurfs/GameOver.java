package com.smurfs;

import javax.swing.*;
import java.awt.*;

public class GameOver {

    JFrame frame = new JFrame();
    JLabel youWonPanel = new JLabel("YOU WON!");
    JLabel youLostPanel = new JLabel("YOU LOST!");
    JLabel scorePanel = new JLabel("Score: ");
    JLabel scoreShow = new JLabel();
    GameOver(int status, int score) {
        if(status == 1){
            youWonPanel.setBounds(120, 50 , 150, 100);
            youWonPanel.setFont(new Font("Serif", Font.BOLD, 24));
            scorePanel.setBounds(110, 150 , 100, 100);
            scorePanel.setFont(new Font("Serif", Font.BOLD, 24));

            scoreShow.setText(Integer.toString(score));
            scoreShow.setBounds(210, 150 , 100, 100);
            scoreShow.setFont(new Font("Serif", Font.BOLD, 24));
        }
        else if(status == 2) {
            youLostPanel.setBounds(120, 50 , 150, 100);
            youLostPanel.setFont(new Font("Serif", Font.BOLD, 24));
            scorePanel.setBounds(110, 150 , 100, 100);
            scoreShow.setText(Integer.toString(score));
            scoreShow.setBounds(210, 150 , 100, 100);
        }

        frame.add(youWonPanel);
        frame.add(youLostPanel);
        frame.add(scorePanel);
        frame.add(scoreShow);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
