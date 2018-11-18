package com.bomb.OBJECT;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bomb extends OBJECT {

    public int c;
    public int lifeTime;
    private Bomb(int x, int y){
        x=(x/45)*45;
        y=(y/45)*45;
        this.x = x;
        this.y = y;
        c=0;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/Character/bomb.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Bomb(int x, int y, int lifeTime){
        this(x, y);
        this.lifeTime = lifeTime;
    }

    public void explose(){
        lifeTime -=15;
    }

    @Override
    public void drawObject(Graphics2D g2) {
        g2.drawImage(this.image, this.x, this.y, null);
    }
}
