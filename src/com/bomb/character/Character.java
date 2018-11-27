package com.bomb.character;

import java.awt.*;

public abstract class Character {
    public int x, y, speed;
    public static final int TREN = 1;
    public static final int DUOI= 2;
    public static final int TRAI = 3;
    public static final int PHAI = 4;
    public  Image image;

    public abstract void drawCharacter(Graphics2D g2);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
