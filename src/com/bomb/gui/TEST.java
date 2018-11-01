package com.bomb.gui;

import com.bomb.BackGround.Background;
import com.bomb.OBJECT.OBJECT;
import com.bomb.character.Bomber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TEST extends JPanel {
    public static final int D_W = 905;
    public static final int D_H = 610;
    Bomber bomber = new Bomber();
    Background background = new Background();
    public static ArrayList<OBJECT> listObject = new ArrayList<>();
    Camera camera = new Camera(0, 0);
    @Override

    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        g2d.translate(camera.getX(), camera.getY());
        background.drawBackGround(g2d);
        bomber.drawCharacter(g2d);

        for(OBJECT object : listObject){
            object.drawObject(g2d);
        }
        repaint();
        g2d.translate(-camera.getX(), -camera.getY());
        repaint();
    }


    public void move(int huong){
        if(Bomber.HUONG != huong){
            bomber.doiHuong(huong);
            Bomber.HUONG = huong;
            bomber.move(huong);
        }
        else bomber.move(huong);
        if(huong == 3 || huong == 4) camera.moveCamera(bomber);
    }
    @Override

    public Dimension getPreferredSize() {
        return new Dimension(D_W, D_H);

    }

    public void addObject(OBJECT object){
        listObject.add(object);
    }


}
