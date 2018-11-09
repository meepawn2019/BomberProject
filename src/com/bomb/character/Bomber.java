package com.bomb.character;

import com.bomb.OBJECT.OBJECT;
import com.bomb.gui.TEST;

import javax.swing.*;
import java.awt.*;

public class Bomber extends Character implements  CanMove{
    static public int HUONG = 2;
    static public int MOVE = 45;

    @Override
    public void move(int huong) {
        boolean canmove = true;
        switch (huong){
            case TREN:
                for(OBJECT object : TEST.listObject){
                    if(this.y == object.y+MOVE && this.x == object.x) {
                        canmove = false;
                        break;
                    }
                }
                if(this.y - MOVE < 0 || !canmove) break;
                else {
                    this.y -= MOVE;
                    break;
                }
            case DUOI:
                for(OBJECT object : TEST.listObject){
                    if(this.y == object.y-MOVE && this.x == object.x) {
                        canmove = false;
                        break;
                    }
                }
                if(this.y + image.getHeight(null) + MOVE > TEST.D_H  || !canmove) break;
                else {
                    this.y += MOVE;
                    break;
                }
            case TRAI:
                for(OBJECT object : TEST.listObject){
                    if(this.x == object.x+MOVE && this.y == object.y) {
                        canmove = false;
                        break;
                    }
                }
                if(this.x - MOVE < 0 || !canmove) break;
                else {
                    this.x -= MOVE;
                    break;
                }
            case PHAI:
                for(OBJECT object : TEST.listObject){
                    if(this.x == object.x-MOVE && this.y == object.y) {
                        canmove = false;
                        break;
                    }
                }
                if(this.x + image.getWidth(null) + MOVE > 45*31 || !canmove) break; // Thay 45*31 bang chieu dai brick*31
                else {
                    this.x += MOVE;
                    break;
                }
        }
    }

    public Bomber(int x,int y){
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(getClass().getResource("/Character/bomber_down.png")).getImage();
    }

    public void doiHuong(int huong){
        switch(huong){
            case TREN:
                this.image = new ImageIcon(getClass().getResource("/Character/bomber_up.png")).getImage();
                break;
            case DUOI:
                this.image = new ImageIcon(getClass().getResource("/Character/bomber_down.png")).getImage();
                break;
            case TRAI:
                this.image = new ImageIcon(getClass().getResource("/Character/bomber_left.png")).getImage();
                break;
            case PHAI:
                this.image = new ImageIcon(getClass().getResource("/Character/bomber_right.png")).getImage();
                break;
        }
    }

    @Override
    public void drawCharacter(Graphics2D g2) {
        g2.drawImage(this.image, this.x, this.y,this.image.getWidth(null),this.image.getWidth(null), null);
    }

    @Override
    public int getX() {
        return super.getX();
    }
}
