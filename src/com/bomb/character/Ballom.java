package com.bomb.character;

import com.bomb.OBJECT.OBJECT;
import com.bomb.gui.TEST;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ballom extends Character implements CanMove {

    private int dx = 1;
    private int dy = 0;

    public Ballom(int x, int y){
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource("/Character/monster_down-2.png")).getImage();
    }
    @Override
    public void drawCharacter(Graphics2D g2) {
        g2.drawImage(image, x, y, null);
    }

    @Override
    public void move() {
        this.x += dx;
        this.y += dy;
        if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()){
            this.x -= dx;
            this.y -= dy;
            Random random = new Random();
            int randomInt = random.nextInt(4) + 1;
            if(randomInt == 1) {
                dx = 0;
                dy = -1;
            }
            if(randomInt == 2){
                dx = 0;
                dy = 1;
            }
            if(randomInt == 3){
                dx = -1;
                dy = 0;
            }
            if(randomInt == 4){
                dx = 1;
                dy = 0;
            }
        }

    }

    private boolean collision(){
        for(OBJECT object : TEST.listObject){
            if(getBound().intersects(object.getBound())) {
                return true;
            }
        }
        return false;
    }
    private Rectangle getBound() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
    }

    private int isCrossWay(){
        if(dx == 0){
            this.x+=dx;
            if(!collision()){
                this.x-=dx;
                this.x-=dx;
                if(!collision()) {
                    this.x+=dx;
                    return 2;
                }
                else return 1;
            }
        }
        else if(dy == 0){
            this.y+=dy;
            if(!collision()){
                this.y-=dy;
                this.y-=dy;
                if(!collision()){
                    this.y+=dy;
                    return 2;
                }
                else return 1;
            }
        }
    }
}
