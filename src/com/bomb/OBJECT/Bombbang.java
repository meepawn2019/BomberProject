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
import javax.swing.*;

public class Bombbang extends OBJECT {

    public int framesBombb = 0;
    private BufferedImage img_left, img_right, img_up, img_down, img_center;
    ImageIcon imageIcon;
    private Image img_down_2 = new  ImageIcon(getClass().getResource("/Character/paopaodown.png")).getImage();
    private Image img_up_2 = new  ImageIcon(getClass().getResource("/Character/paopaoup.png")).getImage();
    private Image img_left_2 = new  ImageIcon(getClass().getResource("/Character/paopaoleft.png")).getImage();
    private Image img_right_2 = new  ImageIcon(getClass().getResource("/Character/paopaoright.png")).getImage();
    private Image img_center_2 = new ImageIcon(getClass().getResource("/Character/paopao2.png")).getImage();
    private Image img_center_3 = new ImageIcon(getClass().getResource("/Character/paopao3.png")).getImage();
    private Image img_center_4 = new ImageIcon(getClass().getResource("/Character/paopao4.png")).getImage();
    private Image img_center_5 = new ImageIcon(getClass().getResource("/Character/paopao5.png")).getImage();
    public int lifeTime;
    private int size;
    private int right = 0, left = 0, up = 0, down = 0;
    public boolean isRemove = false;
    private int tempRight, tempLeft, tempUp, tempDown;
    private boolean isDrawLeft;
    private boolean isDrawRight;
    private boolean isDrawUp;
    private boolean isDrawDown;
    private boolean endUp = false;
    private boolean endDown = false;
    private boolean endLeft = false;
    private boolean endRight = false;

    public Bombbang(int x, int y, int lifeTime, int size, boolean isDrawRight, boolean isDrawLeft, boolean isDrawDown, boolean isDrawUp) {
        this.x = x;
        this.y = y;
        this.size = size;
        tempRight = size;
        tempUp = size;
        tempDown = size;
        tempLeft = size;
        this.isDrawRight = isDrawRight;
        this.isDrawLeft = isDrawLeft;
        this.isDrawUp = isDrawUp;
        this.isDrawDown = isDrawDown;
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
        lifeTime -= 20;
    }

    private void removeBrick(ArrayList<OBJECT> list, Brick brick) {//hàm xóa
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

    private boolean Right0(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.y == y) {
                if (obj.x == x + 45 * size) {
                    if (obj instanceof Bomb ) {
                        ((Bomb) obj).lifeTime = 15;
                        ((Bomb) obj).impactLeftBomb = true;
                    } else if (obj instanceof Brick && right == 0) {
                        removeBrick(TEST.listObject, (Brick) obj);
                        right++;
                    }
                    tempRight = size;
                    return true;
                }
            }
        }
        return false;
    }

    private boolean Left0(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.y == y) {
                if (obj.x == x - 45 * size) {
                    if (obj instanceof Bomb) {
                        ((Bomb) obj).lifeTime = 15;
                        ((Bomb) obj).impactRightBomb = true;
                        endLeft = true;
                    } else if (obj instanceof Brick && left == 0) {
                        removeBrick(TEST.listObject, (Brick) obj);
                        left++;
                    }
                    tempLeft = size;
                    return true;
                }
            }
        }
        return false;
    }
    private boolean Up0(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.x == x) {
                if (obj.y == y - 45 * size) {
                    if(obj instanceof  Wall){
                        tempUp = size-1;
                        return true;
                    }
                    else{
                        if (obj instanceof Bomb) {
                            ((Bomb) obj).lifeTime = 15;
                            ((Bomb) obj).impactDownBomb = true;
                            endUp = true;
                        } else if (obj instanceof Brick && up == 0) {
                            removeBrick(TEST.listObject, (Brick) obj);
                            up++;
                        }
                        tempUp = size;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    private boolean Down0(ArrayList<OBJECT> list, int x, int y, int size) {
        for (OBJECT obj : list) {
            if (obj.x == x) {
                if (obj.y == y + 45 * size) {
                    if (obj instanceof Bomb) {
                        ((Bomb) obj).lifeTime = 15;
                        ((Bomb) obj).impactUpBomb = true;
                        endDown = true;
                    } else if (obj instanceof Brick && down == 0) {
                        removeBrick(TEST.listObject, (Brick) obj);
                        down++;
                    }
                    tempDown = size;
                    return true;
                }
            }
        }
        return false;
    }
    public void removeBomb(ArrayList<OBJECT> list, Bomb bomb) {
        Iterator<OBJECT> ite = list.iterator();
        while (ite.hasNext()) {
            OBJECT object = ite.next();
            if (object instanceof Bomb) {
                if (bomb.equals(object)) {
                    ite.remove();
                    break;
                }
            }
        }
    }

    private void removeMonster(ArrayList<Character> list, Ballom ballom) {
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
        if(lifeTime <=800 && lifeTime > 600) g2.drawImage(img_center_2, x, y, 45, 45, null);
        if(lifeTime <=600 && lifeTime > 400) g2.drawImage(img_center_3, x, y, 45, 45, null);
        if(lifeTime <=400 && lifeTime > 200) g2.drawImage(img_center_4, x, y, 45, 45, null);
        if(lifeTime <= 200) g2.drawImage(img_center_5, x, y, 45, 45, null);
        int i;
        for (i = 1; i <= tempUp; i++) {
            if (!Up0(TEST.listObject, this.x, this.y, i) && isDrawUp) {
                if(i==tempUp) {
                    if(!endUp) g2.drawImage(img_up, x, y - 45 * i, 45, 45, null);
                    else g2.drawImage(img_up_2, x, y - 45 * i, 45, 45, null);
                }
                else g2.drawImage(img_up_2, x, y - 45 * i, 45, 45, null);
            } else {
                break;
            }
        }
        for (i = 1; i <= tempDown; i++) {
            if (!Down0(TEST.listObject, this.x, this.y, i) && isDrawDown) {
                if(i==tempDown){
                    if(!endDown)  g2.drawImage(img_down, x, y + 45 * i, 45, 45, null);
                    else  g2.drawImage(img_down_2, x, y + 45 * i, 45, 45, null);
                }
                else g2.drawImage(img_down_2, x, y + 45 * i, 45, 45, null);
            } else {
                break;
            }
        }
        for (i = 1; i <= tempRight; i++) {
            if (!Right0(TEST.listObject, this.x, this.y, i) && isDrawRight) {
                if(i==tempRight) {
                    if(!endRight) g2.drawImage(img_right, x + 45 * i, y, 45, 45, null);
                    else g2.drawImage(img_right_2, x + 45 * i, y, 45, 45, null);
                }
                else g2.drawImage(img_right_2, x + 45 * i, y, 45, 45, null);
            } else {
                break;
            }
        }
        for (i = 1; i <= tempLeft; i++) {
            if (!Left0(TEST.listObject, this.x, this.y, i) && isDrawLeft) {
                if(i==tempLeft) {
                    if(!endLeft) g2.drawImage(img_left, x - 45 * i, y, 45, 45, null);
                    else g2.drawImage(img_left_2, x - 45 * i, y, 45, 45, null);
                }
                else g2.drawImage(img_left_2, x - 45 * i, y, 45, 45, null);
            } else {
                break;
            }
        }
        monsterRight(TEST.listMonster,this.x,this.y, tempRight);
        monsterLeft(TEST.listMonster,this.x,this.y, tempLeft);
        monsterUp(TEST.listMonster,this.x,this.y, tempUp);
        monsterDown(TEST.listMonster,this.x,this.y, tempDown);
    }

}
