import javax.swing.border.*;
import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * Write a description of class CustomBorder here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BorderFactory 
{
    public static Border raised(int t, JComponent c) {
        return new MatteBorder(t,t,t,t,bevelIcon(c, 
            c.getBackground().brighter().brighter(),
            c.getBackground().darker()
        ));
    }
    
    public static Border lowered(int t, JComponent c) {
        return new MatteBorder(t,t,t,t,bevelIcon(c, 
            c.getBackground().darker(), 
            c.getBackground().brighter().brighter()
        ));
    }
    
    public static Border custom(int t, Color topRight, Color bottomLeft, JComponent c) {
        return new MatteBorder(t,t,t,t,bevelIcon(c, 
            topRight, 
            bottomLeft
        ));
    }
    
    private static Icon bevelIcon(JComponent c, Color topRight, Color bottomLeft){
        BufferedImage bImage = new BufferedImage(
            Math.max(c.getWidth(),1),
            Math.max(c.getHeight(),1),
            BufferedImage.TYPE_INT_ARGB
        );
        
        int width = c.getWidth();
        int height = c.getHeight();
        
        int[] mid1, mid2;
        
        Graphics2D g2D = (Graphics2D) bImage.createGraphics();
        
        //Enable anti-aliasing
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setColor(bottomLeft);
        g2D.fillRect(0,0,c.getWidth(),c.getHeight());
        
        g2D.setColor(topRight);
        
        if (width < height) {
            g2D.fillPolygon(
                new int[] {0, width / 2, width},
                new int[] {0, width / 2, 0},
                3
            );
            g2D.fillPolygon(
                new int[] {0, width / 2, width / 2, 0},
                new int[] {0, width / 2, height - width / 2, height},
                4
            );
        }
        else {
            g2D.fillPolygon(
                new int[] {0, height / 2, 0},
                new int[] {0, height / 2, height},
                3
            );
            g2D.fillPolygon(
                new int[] {0, height / 2, width - height / 2, width},
                new int[] {0, height / 2, height / 2, 0},
                4
            );
        }
       

        g2D.dispose();
        return new ImageIcon(bImage);
    }
}