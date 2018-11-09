package com.bomb.OBJECT;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class OBJECT {
    public int x, y;

    BufferedImage image;

    public abstract void drawObject(Graphics2D g2);


}
