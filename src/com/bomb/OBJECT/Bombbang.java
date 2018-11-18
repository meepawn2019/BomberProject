package com.bomb.OBJECT;

import com.bomb.gui.TEST;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Bombbang extends OBJECT{

    private Image img_left, img_right, img_up, img_down;
    public int lifeTime;
    int size;

    public Bombbang(int x, int y, int lifeTime, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.lifeTime = lifeTime;
        img_left = new ImageIcon(getClass().getResource("/Character/paopaoleft1.png")).getImage();
        img_right = new ImageIcon(getClass().getResource("/Character/paopaoright1.png")).getImage();
        img_up = new ImageIcon(getClass().getResource("/Character/paopaoup1.png")).getImage();
        img_down = new ImageIcon(getClass().getResource("/Character/paopaodown1.png")).getImage();
    }

    public void remove(){
        lifeTime -=50;
    }
    public void removeBrick(ArrayList<OBJECT> list,Brick brick){//hàm xóa
        Iterator<OBJECT> ite = list.iterator();//dùng Iterator để xóa, nếu dùng list.move thì sẽ ko đấm dồn lên mà phần tử đó sẽ trở thành null và lần duyệt tiếp theo sẽ sai 
        while(ite.hasNext()){
            OBJECT object = ite.next();//ite.next sẽ trả về đối tượng tiếp theo của list
            if(object instanceof Brick){
                if(object.equals(brick)){
                    ite.remove();
                    
                }
            }
        }
    }
    public boolean isImpactBrick(ArrayList<OBJECT>list,int x,int y){
        int i,b=0;
        OBJECT object;
        for(i=0;i<list.size();i++){
            object=list.get(i);
            if(object instanceof Brick ){
                if(((Brick) object).x==x||((Brick)object).y==y){
                    removeBrick(list,(Brick)object);
                    b=1;
                    return true;
                }
            }
            if(b==1) break;
        }
        return false;
    }

    @Override
    public void drawObject( Graphics2D g2) {
        int i;
        for(i=1;i<=size;i++){
            g2.drawImage(img_left, x-45*i,y, null);
            g2.drawImage(img_right, x+45*i, y, null);
            g2.drawImage(img_up, x, y-45*i, null);
            g2.drawImage(img_down, x, y+45*i, null);
            
        }
        isImpactBrick(TEST.listObject,this.x,this.y);
    }
}
