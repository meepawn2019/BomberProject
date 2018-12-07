package com.bomb.gui;

import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {

    //private TEST test = new TEST();
    public static final int WIDTHJF = 905;
    public static final int HEIGHTJF = 620;
    private TEST test;
    private Container container;

    public GUI() {
        setSize(WIDTHJF, HEIGHTJF);
        String[] map={"map1.txt","map2.txt","map3.txt","map4.txt"};
        //test = new TEST(map[0]);
        //add(test);
        //pack();
        setLayout(new CardLayout());
        setLocationRelativeTo(null);
        container = new Container(this);
        add(container);
        //addKeyListener(container.test.myAdapter);
        /*setLocationRelativeTo(null);
        addKeyListener(test.myAdapter);
        pack();*/
        /*setLocationRelativeTo(null);
        addKeyListener(test.myAdapter);*/
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE) ;
    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
        
    }
}
