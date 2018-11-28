package com.bomb.character;

import GameSound.GameSound;
import com.bomb.OBJECT.Bomb;
import com.bomb.OBJECT.Bombbang;
import com.bomb.OBJECT.OBJECT;
import com.bomb.gui.TEST;
import static com.bomb.gui.TEST.bomber;
import static com.bomb.gui.TEST.listItem;
import item.NumberBomb;
import item.Portal;
import item.flame;
import item.item;
import item.speed;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bomber extends Character implements CanMove {

    static public int MOVE = 2;
    public int bombSize = 1;
    public int dx = 0;
    public int dy = 0;
    private Image imageUp2 = new ImageIcon(getClass().getResource("/Character/bomber_up_2.png")).getImage();
    private Image imageUp3 = new ImageIcon(getClass().getResource("/Character/bomber_up_3.png")).getImage();
    private Image imageUp4 = new ImageIcon(getClass().getResource("/Character/bomber_up_4.png")).getImage();
    private Image imageUp5 = new ImageIcon(getClass().getResource("/Character/bomber_up_5.png")).getImage();
    private Image imageUp6 = new ImageIcon(getClass().getResource("/Character/bomber_up_6.png")).getImage();
    private Image imageDown2 = new ImageIcon(getClass().getResource("/Character/bomber_down_2.png")).getImage();
    private Image imageDown3 = new ImageIcon(getClass().getResource("/Character/bomber_down_3.png")).getImage();
    private Image imageDown4 = new ImageIcon(getClass().getResource("/Character/bomber_down_4.png")).getImage();
    private Image imageDown5 = new ImageIcon(getClass().getResource("/Character/bomber_down_5.png")).getImage();
    private Image imageDown6 = new ImageIcon(getClass().getResource("/Character/bomber_down_6.png")).getImage();
    private Image imageLeft2 = new ImageIcon(getClass().getResource("/Character/bomber_left_2.png")).getImage();
    private Image imageLeft3 = new ImageIcon(getClass().getResource("/Character/bomber_left_3.png")).getImage();
    private Image imageLeft4 = new ImageIcon(getClass().getResource("/Character/bomber_left_4.png")).getImage();
    private Image imageLeft5 = new ImageIcon(getClass().getResource("/Character/bomber_left_5.png")).getImage();
    private Image imageLeft6 = new ImageIcon(getClass().getResource("/Character/bomber_left_6.png")).getImage();
    private Image imageRight2 = new ImageIcon(getClass().getResource("/Character/bomber_right_2.png")).getImage();
    private Image imageRight3 = new ImageIcon(getClass().getResource("/Character/bomber_right_3.png")).getImage();
    private Image imageRight4 = new ImageIcon(getClass().getResource("/Character/bomber_right_4.png")).getImage();
    private Image imageRight5 = new ImageIcon(getClass().getResource("/Character/bomber_right_5.png")).getImage();
    private Image imageRight6 = new ImageIcon(getClass().getResource("/Character/bomber_right_6.png")).getImage();
    public static int huong = 1;
    public int currentBomb = 0;
    public int maxBomb = 1;

    private Image[] imageUp = {imageUp2, imageUp3, imageUp4, imageUp5, imageUp6};
    private Image[] imageDown = {imageDown2, imageDown3, imageDown4, imageDown5, imageDown6};
    private Image[] imageLeft = {imageLeft2, imageLeft3, imageLeft4, imageLeft5, imageLeft6};
    private Image[] imageRight = {imageRight2, imageRight3, imageRight4, imageRight5, imageRight6};

    @Override
    public void move() {
        this.x += dx;
        if (this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45 * 31 || collision()) {
            this.x -= dx;
        }
        this.y += dy;
        if (this.y + dy < 0 || this.y + image.getHeight(null) + dy > TEST.D_H || collision()) {
            this.y -= dy;
        }
    }

    public Bomber(int x, int y) {
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource("/Character/bomber_down_1.png")).getImage();
    }
    
    public void doiHuong(int huong) {
        switch (huong) {
            case TREN:
                Bomber.huong = 1;
                image = new ImageIcon(getClass().getResource("/Character/bomber_up_1.png")).getImage();
                break;
            case DUOI:
                Bomber.huong = 2;
                image = new ImageIcon(getClass().getResource("/Character/bomber_down_1.png")).getImage();
                break;
            case TRAI:
                Bomber.huong = 3;
                image = new ImageIcon(getClass().getResource("/Character/bomber_left_1.png")).getImage();
                break;
            case PHAI:
                Bomber.huong = 4;
                image = new ImageIcon(getClass().getResource("/Character/bomber_right_1.png")).getImage();
                break;
        }
    }
    
    public void drawDead(Graphics2D g2){
        BufferedImage img=null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Character/bomber_dead.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bomber.class.getName()).log(Level.SEVERE, null, ex);
        }
        g2.drawImage(img, this.x,this.y,  null);
    }
    @Override
    public void drawCharacter(Graphics2D g2) {
        if (TEST.framesUp == 0 && TEST.framesDown == 0 && TEST.framesLeft == 0 && TEST.framesRight == 0 && !TEST.isKeyPressed) {
            g2.drawImage(image, this.x, this.y - 20, null);
        } else {
            if (huong == 1) {
                if (TEST.framesUp % 4 == 0) {
                    g2.drawImage(imageUp[0], this.x, this.y - 20, null);
                }
                if (TEST.framesUp % 4 == 1) {
                    g2.drawImage(imageUp[1], this.x, this.y - 20, null);
                }
                //if(TEST.framesUp % 5 == 2) g2.drawImage(imageUp[2], this.x,this.y-20, null);
                if (TEST.framesUp % 4 == 2) {
                    g2.drawImage(imageUp[3], this.x, this.y - 20, null);
                }
                if (TEST.framesUp % 4 == 3) {
                    g2.drawImage(imageUp[4], this.x, this.y - 20, null);
                }
            }
            if (huong == 2) {
                if (TEST.framesDown % 4 == 0) {
                    g2.drawImage(imageDown[0], this.x, this.y - 20, null);
                }
                if (TEST.framesDown % 4 == 1) {
                    g2.drawImage(imageDown[1], this.x, this.y - 20, null);
                }
                //if(TEST.framesDown % 5 == 2) g2.drawImage(imageDown[2], this.x,this.y-20, null);
                if (TEST.framesDown % 4 == 2) {
                    g2.drawImage(imageDown[3], this.x, this.y - 20, null);
                }
                if (TEST.framesDown % 4 == 3) {
                    g2.drawImage(imageDown[4], this.x, this.y - 20, null);
                }
            }
            if (huong == 3) {
                if (TEST.framesLeft % 4 == 0) {
                    g2.drawImage(imageLeft[0], this.x, this.y - 20, null);
                }
                if (TEST.framesLeft % 4 == 1) {
                    g2.drawImage(imageLeft[1], this.x, this.y - 20, null);
                }
                //if(TEST.framesLeft % 5 == 2) g2.drawImage(imageLeft[2], this.x,this.y-20, null);
                if (TEST.framesLeft % 4 == 2) {
                    g2.drawImage(imageLeft[3], this.x, this.y - 20, null);
                }
                if (TEST.framesLeft % 4 == 3) {
                    g2.drawImage(imageLeft[4], this.x, this.y - 20, null);
                }
            }
            if (huong == 4) {
                if (TEST.framesRight % 4 == 0) {
                    g2.drawImage(imageRight[0], this.x, this.y - 20, null);
                }
                if (TEST.framesRight % 4 == 1) {
                    g2.drawImage(imageRight[1], this.x, this.y - 20, null);
                }
                //if(TEST.framesRight % 5 == 2) g2.drawImage(imageRight[2], this.x,this.y-20, null);
                if (TEST.framesRight % 4 == 2) {
                    g2.drawImage(imageRight[3], this.x, this.y - 20, null);
                }
                if (TEST.framesRight % 4 == 3) {
                    g2.drawImage(imageRight[4], this.x, this.y - 20, null);
                }
            }
        }
    }

    public boolean impactWithMonster(Monster monster){
        Rectangle rec=monster.getBound();
        return (rec.intersects(getBound()));
    }
    public boolean insertItem(item it) {
        Rectangle rec1 =it.getBound();
        if (rec1.intersects(getBound())) {
            GameSound gameSound = new GameSound("./src/GameSound/item.wav");
            gameSound.play();
            if(it instanceof flame ) bombSize++;
            if(it instanceof speed) MOVE++;
            if(it instanceof NumberBomb) maxBomb++;
            return true;
        }
        return false;
    }
    
    public boolean isInsertPortal(Portal p){
        Rectangle rec=p.getBound();
        if(rec.intersects(getBound())){
            return true;
        }
        return false;
    }
    @Override
    public int getX() {
        return super.getX();
    }

    private boolean collision() {
        for (OBJECT object : TEST.listObject) {
            if (object instanceof Bomb) {
                if (!getBound().intersects(object.getBound())) {
                    ((Bomb) object).c = 1;
                }
                if (((Bomb) object).c == 0) {
                    return false;
                }
            }
            if (getBound().intersects(object.getBound())) {
                return true;
            }
        }
        return false;
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null) - 20);
    }

    public int getHeight() {
        return image.getHeight(null) - 20;
    }

    public int getWidth() {
        return image.getWidth(null);
    }
}
