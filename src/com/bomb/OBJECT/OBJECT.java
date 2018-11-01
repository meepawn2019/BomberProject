package com.bomb.OBJECT;

import java.awt.*;

abstract public class OBJECT {
    public int x, y;

    Image image;

    public abstract void drawObject(Graphics2D g2);

    public abstract Rectangle getObjectBound();

}
