package com.bomb.gui;

import com.bomb.OBJECT.OBJECT;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI extends JFrame {

    //private TEST test = new TEST();
    Keyboard keyboard;

    public GUI() {
        //setSize(WIDTHJF, HEIGHTJF);
        keyboard = new Keyboard();
        add(keyboard.test);
        pack();
        setLocationRelativeTo(null);
        addKeyListener(keyboard);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }
}
