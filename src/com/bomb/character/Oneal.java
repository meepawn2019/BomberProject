package com.bomb.character;

import com.bomb.OBJECT.OBJECT;
import com.bomb.gui.TEST;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Oneal extends Monster implements CanMove{
    private int dx = 0;
    private int dy = 0;
    private int MOVE = -4;
    int turn = 0;

    public Oneal(int x, int y){
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource("/Character/monster_down.png")).getImage();
    }
    @Override
    public void drawCharacter(Graphics2D g2) {
        g2.drawImage(image, x, y-23, null);
    }

    @Override
    public void move() {
        int tempX = TEST.bomber.x - this.x;
        int tempY = TEST.bomber.y - this.y;
        if(tempX < 0) dx = -MOVE;
        else if(tempX > 0) dx = MOVE;
        else dx = 0;
        if(tempY < 0) dy = -MOVE;
        else if(tempY > 0) dy = MOVE;
        else dy = 0;
        this.x += dx;
        if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()) this.x -=dx;
        this.y += dy;
        if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()) this.y -=dy;
        /*if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()){
            this.x -= dx;
            this.y -= dy;
        }*/

    }

    private boolean collision(){
        for(OBJECT object : TEST.listObject){
            if(getBound().intersects(object.getBound())) {
                return true;
            }
        }
        return false;
    }
    public Rectangle getBound() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null)-23);
    }

    public void doiHuong(){
        if(dx == 0 && dy > 0) this.image = new ImageIcon(getClass().getResource("/Character/monster_down.png")).getImage();
        if(dx == 0 && dy < 0) this.image = new ImageIcon(getClass().getResource("/Character/monster_up.png")).getImage();
        if(dx > 0 && dy == 0) this.image = new ImageIcon(getClass().getResource("/Character/monster_right.png")).getImage();
        if(dx < 0 && dy == 0) this.image = new ImageIcon(getClass().getResource("/Character/monster_left.png")).getImage();
    }

    public boolean impactBomber(){
        if(getBound().intersects(TEST.bomber.getBound())) return true;
        else return false;
    }
}
