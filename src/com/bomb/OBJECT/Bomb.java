package com.bomb.OBJECT;

import javax.swing.*;
import java.awt.*;

public class Bomb extends OBJECT {

    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
        this.image = new ImageIcon(getClass().getResource("/Character/bomb.png")).getImage();
    }

    public Rectangle getObjectBound() {
        return new Rectangle(x, y+45, image.getWidth(null), image.getHeight(null)-45);
    }
    @Override
    public void drawObject(Graphics2D g2) {
        g2.drawImage(this.image, this.x, this.y+10, null);
    }
}
