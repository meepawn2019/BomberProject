package com.bomb.gui;

import com.bomb.character.Bomber;

public class Camera {
    private int x, y;

    public Camera(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveCamera(Bomber bomber){
        if(bomber.getX() > TEST.D_W/2 && bomber.getX() + TEST.D_W/2 - Bomber.MOVE <45*31) this.x = -bomber.getX() + TEST.D_W/2;
        else if(bomber.getX() <= TEST.D_W/2)    this.x = 0;
        //else if(bomber.getX() + TEST.D_W/2 > 45*31) this.x = this.x;
        //else this.x = 0;
    }
}
