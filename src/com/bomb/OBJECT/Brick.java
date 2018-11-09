
package com.bomb.OBJECT;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Brick extends OBJECT{

    public Brick(int x,int y)  {
        this.x=x;
        this.y=y;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/Character/box2.png"));
        } catch (IOException ex) {
            Logger.getLogger(Brick.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void drawObject(Graphics2D g2) {
        g2.drawImage(this.image, this.x, this.y,this.image.getWidth() ,this.image.getHeight(), null);
    }

    
    
}
