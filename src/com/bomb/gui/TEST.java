package com.bomb.gui;

import com.bomb.BackGround.Background;
import com.bomb.OBJECT.*;
import com.bomb.character.Ballom;
import com.bomb.character.Bomber;
import com.bomb.character.Character;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.bomb.character.Bomber.MOVE;
import item.*;

public class TEST extends JPanel implements ActionListener {
    static final int D_W = 905;
    public static final int D_H = 610;

    private static final int ix=45;
    private static final int iy=45;
    public static Bomber bomber;

    private Background background = new Background();
    public static ArrayList<OBJECT> listObject = new ArrayList<>();
    public static ArrayList<Bombbang> listBombbang = new ArrayList<>();
    public static ArrayList<Character> listMonster = new ArrayList<>();
    public static ArrayList<item> listItem =new ArrayList<>();
    private Camera camera = new Camera(0, 0);
    private int huong;
    static public int framesUp=0;
    static public int framesDown=0;
    static public int framesLeft=0;
    static public int framesRight=0;
    public static boolean isKeyPressed = false;
    private int dem = 0;
    int demBomb = 0;

    TEST(){
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
                        case '#': {
                            this.addObject(new Wall(row*ix,line*iy));
                            break;
                        }
                        case '*': {
                            this.addObject(new Brick(row*ix,line*iy));
                            break;
                        }
                        case '1': {
                            listMonster.add(new Ballom(row*ix, line*iy));
                            break;
                        }
                        case 'p': {
                            bomber = new Bomber(row*ix,line*iy);
                            break;
                        }
                        case 'f': {
                            listItem.add(new flame(row*ix,line*iy));
                            break;
                        }
                        case 's': {
                            listItem.add(new speed(row*ix,line*iy));
                            break;
                        }

                    }
                    row++;
                }
                s=br.readLine();
                line++;
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(TEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        Timer timer = new Timer(20, this);
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
            if(object instanceof Brick || object instanceof Bomb)
            object.drawObject(g2d);
        }
        for(Bombbang bombb : listBombbang) bombb.drawObject(g2d);
        for(item i:listItem) i.drawItem(g2d);
        for(OBJECT object : listObject){
            if(object instanceof Wall)
                object.drawObject(g2d);
        }
        for(Character character : listMonster){
            ((Ballom) character).drawCharacter(g2d);
        }
        bomber.drawCharacter(g2d);
        g2d.translate(-camera.getX(), -camera.getY());
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
            if(e.getKeyCode() != KeyEvent.VK_SPACE) isKeyPressed = true;
            if(e.getKeyCode() == KeyEvent.VK_UP){
                Bomber.huong = 1;
                huong = Character.TREN;
                bomber.dy = -MOVE;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                Bomber.huong = 2;
                huong = Character.DUOI;
                bomber.dy = MOVE;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                Bomber.huong = 3;
                huong = Character.TRAI;
                bomber.dx = -MOVE;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                Bomber.huong = 4;
                huong = Character.PHAI;
                bomber.dx = MOVE;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_SPACE){
                boolean isDraw = true;
                Bomb bomb = new com.bomb.OBJECT.Bomb(bomber.x + bomber.getWidth() / 2, bomber.y + bomber.getHeight() / 2, 3000 );
                for(OBJECT object : listObject){
                    if(object instanceof Bomb ) {
                        if(object.x == bomb.x && object.y == bomb.y) isDraw = false;
                    }
                }

                if(isDraw) addObject(bomb);
            }
            if(e.getKeyCode() == KeyEvent.VK_UP){
                dem =10;
                isKeyPressed = false;
                framesUp = 0;
                bomber.dy = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_DOWN){
                dem =10;
                isKeyPressed = false;
                framesDown=0;
                bomber.dy = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_LEFT){
                dem =10;
                isKeyPressed = false;
                framesLeft = 0;
                bomber.dx = 0;
            }
            if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                dem =10;
                isKeyPressed = false;
                framesRight = 0;
                bomber.dx = 0;
            }
        }
    };
    public void checkBomb(){
        Iterator<OBJECT> ite = listObject.iterator();
        while(ite.hasNext()){
            OBJECT object = ite.next();
            if(object instanceof Bomb ){
                ((Bomb) object).explose();
                if(((Bomb) object).lifeTime <= 0)
                {
                    int x = object.x;
                    int y = object.y;
                    listBombbang.add(new Bombbang(x,y,800,3,!((Bomb) object).impactRightBomb, !((Bomb) object).impactLeftBomb, !((Bomb) object).impactDownBomb, !((Bomb) object).impactUpBomb));
                    ite.remove();

                }
            }
        }
    }

    public void checkBombbang(){
        Iterator<Bombbang> ite = listBombbang.iterator();
        while(ite.hasNext()){
            Bombbang bombb = ite.next();
            bombb.remove();
            if(bombb.lifeTime <= 0) {
                bombb.isRemove = true;
                ite.remove();
            }
        }
    }

    public void moveMonster(){
        for(Character character : listMonster){
            ((Ballom)character).move();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isKeyPressed){
            dem++;
            if(dem >= 10){
                dem = 0;
                if(Bomber.huong == 1) framesUp++;
                if(Bomber.huong == 2) framesDown++;
                if(Bomber.huong == 3) framesLeft++;
                if(Bomber.huong == 4) framesRight++;
            }
        }
        demBomb++;
        if(demBomb == 20){
            demBomb = 0;
            for(OBJECT object : listObject){
                if(object instanceof Bomb){
                    ((Bomb) object).framesBomb++;
                }
            }
        }
        for(Character ballom : listMonster){
            if(ballom instanceof Ballom){
                ((Ballom) ballom).doiHuong();
            }
        }
        for(Bombbang object : listBombbang){
                object.impactWithBomber();
                object.impactWithItems();
                object.impactWithMonster();
        }
        bomber.move();
        bomber.doiHuong(huong);
        camera.moveCamera(bomber);
        moveMonster();
        checkBomb();
        checkBombbang();
        repaint();
    }
}

