package com.bomb.gui;

import javax.swing.*;


public class GUI extends JFrame {

    //private TEST test = new TEST();
    private TEST test;

    public GUI() {
        //setSize(WIDTHJF, HEI`GHTJF);
        String[] map={"map1.txt","map2.txt","map3.txt","map4.txt"};
        test = new TEST(map[0]);
        add(test);
        pack();
        setLocationRelativeTo(null);
        addKeyListener(test.myAdapter);
        pack();
        setLocationRelativeTo(null);
        addKeyListener(test.myAdapter);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
        
    }
}
