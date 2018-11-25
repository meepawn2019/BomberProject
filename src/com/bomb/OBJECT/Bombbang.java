package com.bomb.OBJECT;

import com.bomb.character.Ballom;
import com.bomb.character.Character;
import com.bomb.gui.TEST;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Bombbang extends OBJECT {

    private BufferedImage img_left, img_right, img_up, img_down, img_center;
    public int lifeTime;
    private int size;
    private int right = 0, left = 0, up = 0, down = 0;
    public boolean isRemove = false;
    int tempRight, tempLeft, tempUp, tempDown;

    public Bombbang(int x, int y, int lifeTime, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        tempRight = size;
        tempUp = size;
        tempDown = size;
        tempLeft = size;

        this.lifeTime = lifeTime;
        try {
            img_left = ImageIO.read(getClass().getResourceAsStream("/Character/paopaoleft1.png"));
            img_right = ImageIO.read(getClass().getResourceAsStream("/Character/paopaoright1.png"));
            img_up = ImageIO.read(getClass().getResourceAsStream("/Character/paopaoup1.png"));
            img_down = ImageIO.read(getClass().getResourceAsStream("/Character/paopaodown1.png"));
            img_center = ImageIO.read(getClass().getResourceAsStream("/Character/paopaoup.png"));
        } catch (IOException ex) {
            Logger.getLogger(Bombbang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void remove() {
        lifeTime -= 50;
    }

    public void removeBrick(ArrayList<OBJECT> list, Brick brick) {//hàm xóa
        Iterator<OBJECT> ite = list.iterator();//dùng Iterator để xóa, nếu dùng list.remove thì sẽ ko dồn lên mà phần tử đó sẽ trở thành null và lần duyệt tiếp theo sẽ sai 
        while (ite.hasNext()) {
            OBJECT object = ite.next();//ite.next sẽ trả về đối tượng tiếp theo của list
            if (object instanceof Brick) {
                if (object.equals(brick)) {
                    ite.remove();
                    return;
                }
            }
        }
    }

    public boolean DOWN(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.x == x) {
                if (obj.y == y + 45 * size) {
                    if (!(obj instanceof Bomb)) {
                        if (obj instanceof Brick && down == 0) {
                            down++;
                            removeBrick(TEST.listObject, (Brick) obj);
                            tempDown = size;
                        }
                    } else if (obj instanceof Bomb ) {
                        ((Bomb) obj).lifeTime = 15;
                        tempDown = size - 1;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean UP(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.x == x) {
                if (obj.y == y - 45 * size) {
                    if (!(obj instanceof Bomb)) {
                        if (obj instanceof Brick && up == 0) {
                            up++;
                            removeBrick(TEST.listObject, (Brick) obj);
                            tempUp = size;
                        }
                    } else if (obj instanceof Bomb ) {
                        ((Bomb) obj).lifeTime = 15;
                        tempUp = size - 1;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean LEFT(ArrayList<OBJECT> list, int x, int y, int size) {
        int i;
        OBJECT object;
        for (i = 0; i < list.size(); i++) {
            object = list.get(i);
            if (object.y == y) {
                if (object.x == x - 45 * size) {
                    if (!(object instanceof Bomb)) {
                        if (object instanceof Brick && left == 0) {
                            tempLeft = size;
                            removeBrick(TEST.listObject, (Brick) object);
                            left++;
                        }
                    } else if (object instanceof Bomb ) {
                        ((Bomb) object).lifeTime = 15;
                        tempLeft = size - 1;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    public boolean UP1(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.x == x) {
                if (obj.y == y - 45 * size) {
                    if (obj instanceof Bomb) {
                        //((Bomb) obj).lifeTime = 15;
                        tempUp = size;
                        removeBomb(TEST.listObject, (Bomb) obj);
                        makeBombbangLeft(((Bomb) obj).x,((Bomb) obj).y,tempUp);
                    } else {
                        if (obj instanceof Brick && up == 0) {
                            tempUp = size;
                            up++;
                            removeBrick(TEST.listObject, (Brick) obj);
                        }

                    }
                    return true;
                }
            }
        }
        return false;
    }
    public boolean DOWN1(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.x == x) {
                if (obj.y == y + 45 * size) {
                    if (obj instanceof Bomb) {
                        //((Bomb) obj).lifeTime = 15;
                        tempUp = size;
                        removeBomb(TEST.listObject, (Bomb) obj);
                        makeBombbangLeft(((Bomb) obj).x,((Bomb) obj).y,tempDown);
                    } else {
                        if (obj instanceof Brick && down == 0) {
                            tempDown = size;
                            down++;
                            removeBrick(TEST.listObject, (Brick) obj);
                        }

                    }
                    return true;
                }
            }
        }
        return false;
    }
    public boolean LEFT1(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.y == y) {
                if (obj.x == x - 45 * size) {
                    if (obj instanceof Bomb) {
                        //((Bomb) obj).lifeTime = 15;
                        tempLeft = size;
                        removeBomb(TEST.listObject, (Bomb) obj);
                        makeBombbangLeft(((Bomb) obj).x,((Bomb) obj).y,tempLeft);
                    } else {
                        if (obj instanceof Brick && left == 0) {
                            tempLeft = size;
                            left++;
                            removeBrick(TEST.listObject, (Brick) obj);
                        }

                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean RIGHT1(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.y == y) {
                if (obj.x == x + 45 * size) {
                    if (obj instanceof Bomb) {
                        tempRight = size;
                        //((Bomb) obj).lifeTime = 15;
                        removeBomb(TEST.listObject, (Bomb) obj);
                        makeBombbangRight(((Bomb) obj).x,((Bomb) obj).y,tempRight);
                        
                        
                    } else {
                        if (obj instanceof Brick && right == 0) {
                            tempRight = size;
                            right++;
                            removeBrick(TEST.listObject, (Brick) obj);
                        }

                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public void removeBomb(ArrayList<OBJECT> list, Bomb bomb){
        
        Iterator<OBJECT> ite = list.iterator();
        while(ite.hasNext()){
            OBJECT object = ite.next();
            if(object instanceof Bomb ){
                if(bomb.equals(object))
                {
                    ite.remove();
                    break;
                }
            }
        }
    }
    public void makeBombbangLeft(int x,int y,int tempLeft){
        Bombbang b=new Bombbang(x,y,2000,5);
        b.tempLeft=tempLeft;
        TEST.listBombbang.add(b);
    }
    public void makeBombbangRight(int x,int y,int tempRight){
        Bombbang b=new Bombbang(x,y,2000,5);
        b.tempRight=tempRight;
        TEST.listBombbang.add(b);
    }
    public void makeBombbangUp(int x,int y,int tempUp){
        Bombbang b=new Bombbang(x,y,2000,5);
        b.tempUp=tempUp;
        TEST.listBombbang.add(b);
    }
    public void makeBombbangDown(int x,int y,int tempDown){
        Bombbang b=new Bombbang(x,y,2000,5);
        b.tempDown=tempDown;
        TEST.listBombbang.add(b);
    }

//    public boolean RIGHT(ArrayList<OBJECT> list, int x, int y, int size) {
//        int i;
//        OBJECT object;
//        for (i = 0; i < list.size(); i++) {
//            object = list.get(i);
//            if (object.y == y) {
//                if (object.x == x + 45 * size) {
//                    if (!(object instanceof Bomb)) {
//                        if (object instanceof Brick && right == 0) {
//                            tempRight = size;
//                            if (bRight == 0) {
//                                removeBrick(TEST.listObject, (Brick) object);
//                            }
//                            right++;
//                        }
//                    } else if (object instanceof Bomb && bRight == 0) {
//                        ((Bomb) object).lifeTime = 15;
//                        bRight = 1;
//                        tempRight = size - 1;
//                    }
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

    public void removeMonster(ArrayList<Character> list, Ballom ballom) {
        Iterator<Character> ite = list.iterator();
        Character character;
        while (ite.hasNext()) {
            character = ite.next();
            if (character instanceof Ballom) {
                if (character.equals(ballom)) {
                    ite.remove();
                    break;
                }
            }
        }
    }

    public void monsterRight(ArrayList<Character> list, int x, int y, int size) {
        for (Character c : list) {
            if (c instanceof Ballom) {
                if (((Ballom) c).y == y) {
                    if (((Ballom) c).x == x + 45 * size) {
                        removeMonster(list, (Ballom) c);
                        break;
                    }
                }
            }
        }
    }

    public void monsterLeft(ArrayList<Character> list, int x, int y, int size) {
        for (Character c : list) {
            if (c instanceof Ballom) {
                if (((Ballom) c).y == y) {
                    if (((Ballom) c).x == x - 45 * size) {
                        removeMonster(list, (Ballom) c);
                        break;
                    }
                }
            }
        }
    }

    public void monsterUp(ArrayList<Character> list, int x, int y, int size) {
        for (Character c : list) {
            if (c instanceof Ballom) {
                if (((Ballom) c).x == x) {
                    if (((Ballom) c).y == y - 45 * size) {
                        removeMonster(list, (Ballom) c);
                        break;
                    }
                }
            }
        }
    }

    public void monsterDown(ArrayList<Character> list, int x, int y, int size) {
        for (Character c : list) {
            if (c instanceof Ballom) {
                if (((Ballom) c).x == x) {
                    if (((Ballom) c).y == y + 45 * size) {
                        removeMonster(list, (Ballom) c);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void drawObject(Graphics2D g2) {
        int i;
        for (i = 1; i <= tempUp; i++) {
            if (!UP1(TEST.listObject, this.x, this.y, i)) {
                g2.drawImage(img_up, x, y - 45 * i, 45, 45, null);
            } else {
                break;
            }
        }
        for (i = 1; i <= tempDown; i++) {
            if (!DOWN1(TEST.listObject, this.x, this.y, i)) {
                g2.drawImage(img_down, x, y + 45 * i, 45, 45, null);
            } else {
                break;
            }
        }
        for (i = 1; i <= tempRight; i++) {
            if (!RIGHT1(TEST.listObject, this.x, this.y, i)) {
                g2.drawImage(img_right, x + 45 * i, y, 45, 45, null);
            } else {
                break;
            }
        }
        for (i = 1; i <= tempLeft; i++) {
            if (!LEFT1(TEST.listObject, this.x, this.y, i)) {
                g2.drawImage(img_left, x - 45 * i, y, 45, 45, null);
            } else {
                break;
            }
        }
//        monsterRight(TEST.listMonster,this.x,this.y, tempRight);
//        monsterLeft(TEST.listMonster,this.x,this.y, tempLeft);
//        monsterUp(TEST.listMonster,this.x,this.y, tempUp);
//        monsterDown(TEST.listMonster,this.x,this.y, tempDown);
    }
}
