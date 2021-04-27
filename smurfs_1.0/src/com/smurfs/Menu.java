package com.smurfs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    private static int choice;

    public static int getChoice() {
        return choice;
    }

    JDialog frame = new JDialog();
    JButton tembel = new JButton("Tembel Sirin");
    JButton gozluklu = new JButton(("Gozluklu Sirin"));
    JButton startGame = new JButton("Start!");

    Menu() {
        tembel.setBounds(50,50,140,40);
        tembel.addActionListener(this);
        frame.add(tembel);

        gozluklu.setBounds(200,50,140,40);
        gozluklu.addActionListener(this);
        frame.add(gozluklu);

        startGame.setBounds(100, 100,200,80);
        startGame.addActionListener(this);
        frame.add(startGame);

        frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        frame.setSize(400,300);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setTitle("Menu");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==tembel){
            choice = 1;
        }
        else if(e.getSource()==gozluklu){
            choice = 2;
        }
        else if(e.getSource()==startGame){
            frame.setVisible(false);
            JFrame window = new JFrame("Smurfs");
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setContentPane(new gameScreen());
            window.pack();
            window.setVisible(true);

        }
    }
}
