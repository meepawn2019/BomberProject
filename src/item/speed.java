
package item;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class speed extends item{
    public speed(int x,int y){
        this.x=x;
        this.y=y;
        try {
            this.image=ImageIO.read(getClass().getResourceAsStream("/Character/item_shoe.png"));
        } catch (IOException ex) {
            Logger.getLogger(speed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void drawItem(Graphics2D g2) {
        g2.drawImage(this.image, this.x, this.y,45 ,45, null);
    }
    
}
