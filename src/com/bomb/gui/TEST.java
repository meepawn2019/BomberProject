package com.bomb.gui;

import com.bomb.BackGround.Background;
import com.bomb.OBJECT.Brick;
import com.bomb.OBJECT.OBJECT;
import com.bomb.OBJECT.Wall;
import com.bomb.character.Bomber;
import com.bomb.character.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static com.bomb.character.Bomber.MOVE;

public class TEST extends JPanel implements ActionListener {
    static final int D_W = 905;
    public static final int D_H = 610;
    Bomber bomber = new Bomber();
    private Background background = new Background();
    public static ArrayList<OBJECT> listObject = new ArrayList<>();
    private Camera camera = new Camera(0, 0);
    private int huong;

    TEST(){
        this.addObject(new Brick(100, 100));
        this.addObject(new Wall(100,200));
        Timer timer = new Timer(10, this);
        timer.start();
    }
    protected void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g);
        g2d.setStroke(new java.awt.BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(camera.getX(), camera.getY());
        background.drawBackGround(g2d);
        for(OBJECT object : listObject){
            object.drawObject(g2d);
        }
        bomber.drawCharacter(g2d);
        g2d.translate(-camera.getX(), -camera.getY());
    }


    void move(int huong){
        if(Bomber.HUONG != huong){
            bomber.doiHuong(huong);
            Bomber.HUONG = huong;
        }
        bomber.move();

    }
    @Override

    public Dimension getPreferredSize() {
        return new Dimension(D_W, D_H);

    }

    void addObject(OBJECT object){
        listObject.add(object);
    }


    KeyAdapter myAdapter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_UP){
                huong = Character.TREN;
                bomber.dy = -MOVE;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                huong = Character.DUOI;
                bomber.dy = MOVE;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                huong = Character.TRAI;
                bomber.dx = -MOVE;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                huong = Character.PHAI;
                bomber.dx = MOVE;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                addObject(new com.bomb.OBJECT.Bomb(bomber.x, bomber.y));
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                bomber.dy = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                bomber.dy = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                bomber.dx = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                bomber.dx = 0;
            }
        }
    };


    @Override
    public void actionPerformed(ActionEvent e) {
        bomber.doiHuong(huong);
        if(huong == 3 || huong == 4) camera.moveCamera(bomber);
        bomber.move();
        repaint();
    }
}
