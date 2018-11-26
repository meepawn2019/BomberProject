/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package item;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author thota
 */
public abstract class item {

    public int x, y;
    BufferedImage image;

    public abstract void drawItem(Graphics2D g2);
}
