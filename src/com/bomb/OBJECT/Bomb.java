package com.bomb.OBJECT;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bomb extends OBJECT {

    public int c;
    public Bomb(int x, int y){
        this.x = x;
        this.y = y;
        c=0;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/Character/bomb.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    @Override
    public void drawObject(Graphics2D g2) {
        g2.drawImage(this.image, this.x, this.y,this.image.getWidth() ,this.image.getHeight() , null);
    }
}
