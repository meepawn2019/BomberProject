
package com.bomb.OBJECT;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wall extends OBJECT{

    public Wall(int x,int y){
        this.x=x;
        this.y=y;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/Character/stone.png"));
        } catch (IOException ex) {
            Logger.getLogger(Wall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void drawObject(Graphics2D g2) {
        g2.drawImage(this.image, this.x, this.y,this.image.getWidth() ,this.image.getHeight(), null);
    }
    
}
