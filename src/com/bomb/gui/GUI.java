package com.bomb.gui;

import javax.swing.*;


public class GUI extends JFrame {

    //private TEST test = new TEST();
    TEST test;

    public GUI() {
        //setSize(WIDTHJF, HEIGHTJF);
        test = new TEST();
        add(test);
        pack();
        setLocationRelativeTo(null);
        addKeyListener(test.myAdapter);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
        
    }
}
