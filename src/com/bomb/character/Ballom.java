package com.bomb.character;

import com.bomb.OBJECT.OBJECT;
import com.bomb.gui.TEST;

import javax.swing.*;
import java.awt.*;

public class Ballom extends Character implements CanMove {

    private int dx = 1;
    private int dy = 0;

    public Ballom(int x, int y){
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource("/Character/monster_down")).getImage();
    }
    @Override
    void drawCharacter(Graphics2D g2) {
        g2.drawImage(image, x, y, null);
    }

    @Override
    public void move() {
        this.x+=dx;
        if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()) {
            dx = -dx;
            this.x+=dx;
        }
        this.y+=dy;
        if(this.y + dy <0 || this.y + image.getHeight(null) + dy > TEST.D_H || collision()) {
            dy = -dy;
            this.y+=dy;
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
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null)-20);
    }
}
