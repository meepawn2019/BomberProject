package com.bomb.BackGround;

import javax.swing.*;
import java.awt.*;

public class Background {
    public final Image image = new ImageIcon(getClass().getResource("/Character/background_Play.png")).getImage();

    public void drawBackGround(Graphics2D g2) {
        g2.drawImage(image,0, 0, null);
    }
}
