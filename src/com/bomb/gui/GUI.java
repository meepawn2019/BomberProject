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
        //setLayout(new CardLayout());
        pack();
        setLocationRelativeTo(null);
        addKeyListener(keyboard);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
    }

    /*@Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            test.move(com.bomb.character.Character.TREN);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            test.move(com.bomb.character.Character.DUOI);
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            test.move(com.bomb.character.Character.TRAI);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            test.move(com.bomb.character.Character.PHAI);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            test.addObject(new com.bomb.OBJECT.Bomb(test.bomber.x, test.bomber.y));
            System.out.println(test.bomber.x  + " " + test.bomber.y);
            for(OBJECT object : TEST.listObject){
                System.out.println(object.x + " " + object.y);
            }
        }
    }*/
}
