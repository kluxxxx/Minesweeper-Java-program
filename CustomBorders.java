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
public class CustomBorders 
{
    public static Border generate(int t, JComponent c) {
        return new MatteBorder(t,t,t,t,bevelIcon(c));
    }
    
    private static Icon bevelIcon(JComponent c){
        BufferedImage bImage = new BufferedImage(
            c.getWidth(),
            c.getHeight(),
            BufferedImage.TYPE_INT_ARGB
        );
        
        
        
        int[] mid1 = new int[] {
            
            
        };
        
        Graphics2D g2D = (Graphics2D) bImage.createGraphics();
        
        //Enable anti-aliasing
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2D.setColor(c.getBackground().darker());
        g2D.fillRect(0,0,c.getWidth(),c.getHeight());
        
        
        g2D.setColor(c.getBackground().brighter());
        g2D.fillPolygon(
            new int[] {0, c.getWidth(), 0},
            new int[] {0, 0, c.getHeight()},
            3
        );
        
        
        g2D.dispose();
        return new ImageIcon(bImage);
    }
}