package com.bomb.gui;

import com.bomb.BackGround.Background;
import com.bomb.OBJECT.*;
import com.bomb.character.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.bomb.character.Bomber.MOVE;

import com.bomb.character.Character;
import item.*;

public class TEST extends JPanel implements ActionListener {
    static final int D_W = 905;
    public static final int D_H = 610;

    private static final int ix=45;
    private static final int iy=45;
    public static Bomber bomber;
    int bx=0;
    int by=0;
    private Background background = new Background();
    public static ArrayList<OBJECT> listObject = new ArrayList<>();
    public static ArrayList<Bombbang> listBombbang = new ArrayList<>();
    public static ArrayList<Character> listMonster = new ArrayList<>();
    public static ArrayList<item> listItem =new ArrayList<>();
    public static ArrayList<Portal> listPortal=new ArrayList<>();
    private Camera camera = new Camera(0, 0);
    private int huong;
    static public int framesUp=0;
    static public int framesDown=0;
    static public int framesLeft=0;
    static public int framesRight=0;
    public boolean asd=false;
    
    public static boolean isKeyPressed = false;
    private int dem = 0;
    private int demBomb = 0;
    
    TEST(String map){
        loadMap(map);
        Timer timer = new Timer(20, this);
        timer.start();
    }
    private void loadMap(String map){
        BufferedReader br;
        String s;

        int i,row=0,line=0;
        try {
            br=new BufferedReader(new FileReader(map));
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
                            /*if(bomber==null) {
                                bomber = new Bomber(row*ix,line*iy);
                                break;
                            } else {
                                bomber.x = row*ix;
                                bomber.y = row*iy;
                            }*/
                            bomber = new Bomber(row*ix,line*iy);
                            bx=row*ix;
                            by=line*iy;
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
                        case 'b': {
                            listItem.add(new NumberBomb(row*ix,line*iy));
                            break;
                        }
                        case '2':{
                            listMonster.add(new Oneal(row*ix,line*iy));
                            break;
                        }
                        case 'x':{
                            listPortal.add(new Portal(row*ix,line*iy));
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
        for(Portal p:listPortal)p.drawPortal(g);
        
        for(Character character : listMonster){
            ((Monster) character).drawCharacter(g2d);
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

                if(isDraw && TEST.bomber.currentBomb < TEST.bomber.maxBomb) {
                    addObject(bomb);
                    TEST.bomber.currentBomb++;
                }
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
                    listBombbang.add(new Bombbang(x,y,400,bomber.bombSize,!((Bomb) object).impactRightBomb, !((Bomb) object).impactLeftBomb, !((Bomb) object).impactDownBomb, !((Bomb) object).impactUpBomb));
                    ite.remove();
                    TEST.bomber.currentBomb--;

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
            ((Monster)character).move();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(asd) {
            listObject.clear();
            listMonster.clear();
            listItem.clear();
            listPortal.clear();
            //loadMap("map2.txt");
        }
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
        for(Character character : listMonster){
            if(character instanceof Monster){
                ((Monster) character).doiHuong();
            }
        }
        for(item it:listItem){
            if(bomber.insertItem(it)){
                 Bombbang.removeItem(listItem, it);
                 break;
            }
            
        }
        for(Portal p: listPortal){
            if(bomber.isInsertPortal(p)) {
                asd=true;
                break;
            }
        }
        for(Bombbang object : listBombbang){
                object.impactWithBomber();
                object.impactWithItems();
                object.impactWithMonster();
        }
        for(Bombbang object : listBombbang){
                if(object.impactWithBomber()) {
                    bomber.maxBomb=1;
                    bomber.speed=1;
                    bomber.speed=1;
                    Bomber.MOVE=1;
                    bomber=new Bomber(bx,by);
                } 
                
        }
        bomber.move();
        bomber.doiHuong(huong);
        camera.moveCamera(bomber);
        moveMonster();
        checkBomb();
        checkBombbang();
        repaint();
    }

/*    public static void music(){
        AudioPlayer MGP = AudioPlayer.player;
        AudioStream BGM;
        AudioData MD;
        ContinuousAudioDataStream loop = null;
        try{
            BGM = new AudioStream(new FileInputStream("C:\\test\\ha.wav"));
            MD = BGM.getData();
            loop = new ContinuousAudioDataStream(MD);
        }catch(IOException error){
            System.out.print("file not found");
        }

        MGP.start(loop);
    }*/
}

