
package Action;

import com.bomb.OBJECT.OBJECT;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


public class BigBang {
    private int x,y,time,size;
    private BufferedImage bangLeft,bangRight,bangUp,bangDown;
    public BigBang(int x,int y, int size , ArrayList<OBJECT> box){
        this.x=x;
        this.y=y;
        this.size=size;
        try {
            this.bangLeft=ImageIO.read(getClass().getResourceAsStream("/Character/bombbang_left_1.png"));
            this.bangRight=ImageIO.read(getClass().getResourceAsStream("/Character/bombbang_right_1.png"));
            this.bangUp=ImageIO.read(getClass().getResourceAsStream("/Character/bombbang_up_1.png"));
            this.bangDown=ImageIO.read(getClass().getResourceAsStream("/Character/bombbang_down_1.png"));
        } catch (IOException ex) {
            Logger.getLogger(BigBang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
