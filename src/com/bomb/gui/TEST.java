package com.bomb.gui;

import com.bomb.BackGround.Background;
import com.bomb.OBJECT.Brick;
import com.bomb.OBJECT.OBJECT;
import com.bomb.OBJECT.Wall;
import com.bomb.character.Bomber;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TEST extends JPanel {
    public static final int D_W = 905;
    public static final int D_H = 610;
    public static final int ix=45;
    public static final int iy=45;
    Bomber bomber;
    Background background = new Background();
    public static ArrayList<OBJECT> listObject = new ArrayList<>();
    Camera camera = new Camera(0, 0);

    public TEST(){
        BufferedReader br;
        String s;
        int i,row=0,line=0;
        try {
            br=new BufferedReader(new FileReader("map1.txt"));
            s=br.readLine();
            while(s!=null){
                row=0;
                for(i=0;i<s.length();i++){
                    switch(s.charAt(i)){
                        case '#': this.addObject(new Wall(row*ix,line*iy));
                        break;
                        case '*': this.addObject(new Brick(row*ix,line*iy));
                        break;
                        case 'p': bomber = new Bomber(row*ix,line*iy);
                        
                    }
                    row++;
                }
                s=br.readLine();
                line++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TEST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TEST.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
