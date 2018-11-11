package com.bomb.character;

import com.bomb.OBJECT.Bomb;
import com.bomb.OBJECT.OBJECT;
import com.bomb.gui.TEST;

import javax.swing.*;
import java.awt.*;

public class Bomber extends Character implements  CanMove{
    static public int HUONG = 2;
    static public int MOVE = 1;
    public int dx = 0;
    public int dy = 0;


    @Override
    public void move() {
       this.x+=dx;
       if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()) this.x-=dx;
       this.y+=dy;
       if(this.y + dy <0 || this.y + image.getHeight(null) + dy >TEST.D_H || collision()) this.y-=dy;
    }

    public Bomber(int x,int y){
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource("/Character/bomber_down.png")).getImage();
    }

    public Bomber(){
        image = new ImageIcon(getClass().getResource("/Character/bomber_down.png")).getImage();
    }


    public void doiHuong(int huong){
        switch(huong){
            case TREN:
                image = new ImageIcon(getClass().getResource("/Character/bomber_up.png")).getImage();
                break;
            case DUOI:
                image = new ImageIcon(getClass().getResource("/Character/bomber_down.png")).getImage();
                break;
            case TRAI:
                image = new ImageIcon(getClass().getResource("/Character/bomber_left.png")).getImage();
                break;
            case PHAI:
                image = new ImageIcon(getClass().getResource("/Character/bomber_right.png")).getImage();
                break;
        }
    }

    @Override
    public void drawCharacter(Graphics2D g2) {
        g2.drawImage(image, this.x, this.y-20, null);
    }

    @Override
    public int getX() {
        return super.getX();
    }


    private boolean collision(){
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

    private Rectangle getBound() {
        return new Rectangle(x, y, image.getWidth(null)-5, image.getHeight(null)-20);
    }

    public int getHeight(){return image.getHeight(null)-20;}
    public int getWidth(){return image.getWidth(null);}
}
