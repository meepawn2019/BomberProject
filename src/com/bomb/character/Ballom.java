package com.bomb.character;

import com.bomb.OBJECT.OBJECT;
import com.bomb.gui.TEST;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static com.bomb.character.Bomber.MOVE;

public class Ballom extends Monster implements CanMove {

    private int dx = 1;
    private int dy = 0;
    int turn = 0;

    public Ballom(int x, int y){
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource("/Character/monster_down.png")).getImage();
    }
    @Override
    public void drawCharacter(Graphics2D g2) {
        g2.drawImage(image, x, y-23, null);
    }

    @Override
    public void move() {
        if(isCrossWay() && this.x % 45   == 0 || isCrossWay() && this.y % 45 == 0){
            turn++;
            if(turn % 2 == 0){
                {
                    random();
                    this.x+=dx;
                    this.y+=dy;
                    if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()){
                        this.x -= dx;
                        this.y -= dy;
                        random();
                    }
                }
            }
        }
        else {
            this.x += dx;
            this.y += dy;
            if(this.x + dx < 0 || this.x + image.getWidth(null) + dx > 45*31 || collision()){
                this.x -= dx;
                this.y -= dy;
                random();
            }
        }
    }

    private boolean collision(){
        for(OBJECT object : TEST.listObject){
            if(getBound().intersects(object.getBound())) {
                return true;
            }
        }
        return false;
    }
    public Rectangle getBound() {
        return new Rectangle(x, y, image.getWidth(null), image.getHeight(null)-23);
    }

    private boolean isCrossWay(){
        /*this.x += MOVE;
        this.y += MOVE;
        if(!collision()) {
            this.x -=MOVE;
            this.y -=MOVE;
            return false;
        }
        this.x -= MOVE;
        this.y -= MOVE;*/
        if(dx == 0){
            this.x+=1;
            if(!collision()){
                this.x-=1;
                return true;
            }
            this.x-=1;
            this.x-=1;
            if(!collision()){
                this.x+=1;
                return true;
            }
            this.x+=1;
        }
        else if(dy==0){
            this.y+=1;
            if(!collision()){
                this.y-=1;
                return true;
            }
            this.y-=1;
            this.y-=1;
            if(!collision()){
                this.y+=1;
                return true;
            }
            this.y+=1;
        }
        return false;
    }
    private void random(){
        Random random = new Random();
        int randomInt = random.nextInt(4) + 1;
        if(randomInt == 1) {
            dx = 0;
            dy = -1;
        }
        if(randomInt == 2){
            dx = 0;
            dy = 1;
        }
        if(randomInt == 3){
            dx = -1;
            dy = 0;
        }
        if(randomInt == 4){
            dx = 1;
            dy = 0;
        }
    }

    public void doiHuong(){
        if(dx == 0 && dy > 0) this.image = new ImageIcon(getClass().getResource("/Character/monster_down.png")).getImage();
        if(dx == 0 && dy < 0) this.image = new ImageIcon(getClass().getResource("/Character/monster_up.png")).getImage();
        if(dx > 0 && dy == 0) this.image = new ImageIcon(getClass().getResource("/Character/monster_right.png")).getImage();
        if(dx < 0 && dy == 0) this.image = new ImageIcon(getClass().getResource("/Character/monster_left.png")).getImage();
    }
}
