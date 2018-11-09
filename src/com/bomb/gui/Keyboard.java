package com.bomb.gui;

import com.bomb.OBJECT.OBJECT;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Keyboard implements KeyListener {
    TEST test;

    public Keyboard()  {
        this.test = new TEST();
    }
    @Override
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
    }

}
