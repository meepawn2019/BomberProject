package com.bomb.OBJECT;

import javax.swing.*;
import java.awt.*;

public class Bombbang extends OBJECT{

    private Image img_left, img_right, img_up, img_down;
    public int lifeTime;
    int size;

    public Bombbang(int x, int y, int lifeTime, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.lifeTime = lifeTime;
        img_left = new ImageIcon(getClass().getResource("/Character/bombbang_left_1.png")).getImage();
        img_right = new ImageIcon(getClass().getResource("/Character/bombbang_right_1.png")).getImage();
        img_up = new ImageIcon(getClass().getResource("/Character/bombbang_up_1.png")).getImage();
        img_down = new ImageIcon(getClass().getResource("/Character/bombbang_down_1.png")).getImage();
    }

    public void remove(){
        lifeTime -=50;
    }

    public void impactBox(){

    }

    @Override
    public void drawObject( Graphics2D g2) {
        g2.drawImage(img_left, x, y,null);
        g2.drawImage(img_right, x, y,null);
        g2.drawImage(img_up, x, y,null);
        g2.drawImage(img_down, x, y,null);
        if(size > 1){
            g2.drawImage(img_left, x-45*size, y, null);
            g2.drawImage(img_right,x+45*size ,y,null);
            g2.drawImage(img_up, x, y-45*size,null);
            g2.drawImage(img_down, x, y+45*size,null);
        }
    }
}
