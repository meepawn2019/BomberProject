package com.bomb.OBJECT;

import com.bomb.gui.TEST;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bombbang extends OBJECT{

    private BufferedImage img_left, img_right, img_up, img_down,img_center;
    public int lifeTime;
    private int size;
    private int right = 0;
    public boolean isRemove = false;
    int tempRight;
    public Bombbang(int x, int y, int lifeTime, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        tempRight = size;
        this.lifeTime = lifeTime;
        try {
            img_left = ImageIO.read(getClass().getResourceAsStream("/Character/paopaoleft1.png"));
            img_right = ImageIO.read(getClass().getResourceAsStream("/Character/paopaoright1.png"));
            img_up = ImageIO.read(getClass().getResourceAsStream("/Character/paopaoup1.png"));
            img_down = ImageIO.read(getClass().getResourceAsStream("/Character/paopaodown1.png"));
            img_center=ImageIO.read(getClass().getResourceAsStream("/Character/paopaoup.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bombbang.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void remove(){
        lifeTime -=50;
    }
    public void removeBrick(ArrayList<OBJECT> list,Brick brick){//hàm xóa
        Iterator<OBJECT> ite = list.iterator();//dùng Iterator để xóa, nếu dùng list.remove thì sẽ ko dồn lên mà phần tử đó sẽ trở thành null và lần duyệt tiếp theo sẽ sai 
        while(ite.hasNext()){
            OBJECT object = ite.next();//ite.next sẽ trả về đối tượng tiếp theo của list
            if(object instanceof Brick){
                if(object.equals(brick)){
                    ite.remove();
                    return;
                }
            }
        }
    }
    public boolean isImpactBrickUp(ArrayList<OBJECT>list,int x,int y,int size){
        int i;
        OBJECT object;
        for(i=0;i<list.size();i++){
            object=list.get(i);
            if(object instanceof Wall ){
                if(((Wall) object).x==x){
                   if(((Wall) object).y==y-45*size+45) {
                       return true;
                   }
                }
            }
            if(object instanceof Brick ){
                if(((Brick) object).x==x){
                   if(((Brick) object).y==y-45*size) {
                       return true;
                   }
                }
            }
        }
        return false;
    }
    public boolean isImpactBrickDown(ArrayList<OBJECT>list,int x,int y,int size){
        int i;
        OBJECT object;
        for(i=0;i<list.size();i++){
            object=list.get(i);
            if(object instanceof Wall){
                if(((Wall)object).x==x) {
                    if(((Wall) object).y==y+45*size) return true;
                }
                } 
            if(object instanceof Brick){
                if(((Brick) object).x==x){
                    if(((Brick) object).y==y+45*size-45) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isImpactBrickRight(ArrayList<OBJECT>list,int x,int y,int size){
        int i;
        OBJECT object;
        for(i=0;i<list.size();i++){
            object=list.get(i);
            if(!(object instanceof Bomb)){
                if(object.y==y){
                    if(object.x==x+45*size){
                        if(object instanceof Brick && right == 0){
                            tempRight = size;
                            removeBrick(TEST.listObject,(Brick) object);
                            right++;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean isImpactBrickLeft(ArrayList<OBJECT>list,int x,int y,int size){
        int i;
        OBJECT object;
        for(i=0;i<list.size();i++){
            object=list.get(i);
            if(!(object instanceof Bomb)){
                if(object.y==y){
                    if(object.x==x-45*size+45) return true;
                }
            }
        }
        return false;
    }
    @Override
    public void drawObject( Graphics2D g2) {
        int i;
        for(i=1;i<=size;i++){
            if(!isImpactBrickUp(TEST.listObject,this.x,this.y,i)){
                g2.drawImage(img_up, x, y-45*i,45,45, null);
            }else break;
        }
        for(i=1;i<=size;i++){
            if(!isImpactBrickDown(TEST.listObject,this.x,this.y,i)){
                g2.drawImage(img_down, x, y+45*i,45,45, null);
            }else {
                break;
            }
        }
        for(i=1;i<=tempRight;i++){
            if(!isImpactBrickRight(TEST.listObject,this.x,this.y,i)){
                g2.drawImage(img_right, x+45*i, y,45,45, null);
            }else {
                break;
            }
        }
        for(i=1;i<=size;i++){
            if(!isImpactBrickLeft(TEST.listObject,this.x,this.y,i)){
                g2.drawImage(img_left, x-45*i, y,45,45, null);
            }else break;
        }
    }
}
