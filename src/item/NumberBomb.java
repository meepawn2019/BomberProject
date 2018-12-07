
package item;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class NumberBomb extends item{
    private Image img_1 = new ImageIcon(getClass().getResource("/Character/num_bomb_1.png")).getImage();
    private Image img_2 = new ImageIcon(getClass().getResource("/Character/num_bomb_2.png")).getImage();
    private Image img_3 = new ImageIcon(getClass().getResource("/Character/num_bomb_3.png")).getImage();
    public NumberBomb(int x,int y){
        this.x=x;
        this.y=y;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/Character/item_bomb.png"));
        } catch (IOException ex) {
            Logger.getLogger(NumberBomb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void drawItem(Graphics2D g2) {
        if(this.frames % 3 == 0) g2.drawImage(img_1, this.x, this.y,45 ,45, null);
        if(this.frames % 3 == 1) g2.drawImage(img_2, this.x, this.y,45 ,45, null);
        if(this.frames % 3 == 2) g2.drawImage(img_3, this.x, this.y,45 ,45, null);
        //g2.drawImage(this.image, this.x, this.y,45 ,45, null);
    }
    
}
