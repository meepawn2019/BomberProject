package com.bomb.character;

import com.bomb.OBJECT.Bomb;
import com.bomb.OBJECT.OBJECT;
import com.bomb.gui.TEST;

import javax.swing.*;
import java.awt.*;

public class Bomber extends Character implements  CanMove{
    static public int HUONG = 2;
    static public int MOVE = 1;
    static public int SPEED = 15;
    public int dx = 0;
    public int dy = 0;

    @Override
    public void move() {
       /* boolean canMove = true;
        switch (huong){
            case TREN:
                this.y-=MOVE;
                if(this.y< 0) this.y+=MOVE;
                else {
                    for(OBJECT object : TEST.listObject){
                        if(getBound().intersects(object.getBound())) {
                            this.y+=MOVE;
                            break;
                        }
                    }
                }
                break;

            case DUOI:
                this.y+=MOVE;
                if(this.y + image.getHeight(null) > TEST.D_H) this.y-=MOVE;
                else {
                    for(OBJECT object : TEST.listObject){
                        if(getBound().intersects(object.getBound())) {
                            this.y-=MOVE;
                            break;
                        }
                    }
                }
                break;
            case TRAI:
                for(OBJECT object : TEST.listObject){
                    if(this.x == object.x+MOVE && this.y == object.y) {
                        canMove = false;
                        break;
                    }
                }
                if(this.x - MOVE < 0 || !canMove) break;
                else {
                    this.x -= MOVE;
                    break;
                }
            case PHAI:
                for(OBJECT object : TEST.listObject){
                    if(this.x == object.x-MOVE && this.y == object.y) {
                        canMove = false;
                        break;
                    }
                }
                if(this.x + image.getWidth(null) + MOVE > 45*31 || !canMove) break; // Thay 45*31 bang chieu dai brick*31
                else {
                    this.x += MOVE;
                    break;
                }
        }*/
       /*if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()) return;
       else this.x+=dx;
       if(this.y + dy <0 || this.y + image.getHeight(null) + dy >TEST.D_H || collision()) return;
       else this.y+=dy;*/
       this.x+=dx;
       if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()) this.x-=dx;
       this.y+=dy;
       if(this.y + dy <0 || this.y + image.getHeight(null) + dy >TEST.D_H || collision()) this.y-=dy;
    }

    public Bomber(){
        this.x = 0;
        this.y = 0;
        image = new ImageIcon(getClass().getResource("/Character/bomber_down.png")).getImage();
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
        g2.drawImage(image, this.x, this.y, image.getWidth(null), image.getWidth(null), null);
    }

    @Override
    public int getX() {
        return super.getX();
    }


    public boolean collision(){
        for(OBJECT object : TEST.listObject){
            if(object instanceof Bomb){
                if(!getBound().intersects(object.getBound())) ((Bomb )object).c = 1;
                if(((Bomb) object).c==0) return false;
            }
            if(getBound().intersects(object.getBound())) {
                return true;
            }
        }
        return false;
    }
}
