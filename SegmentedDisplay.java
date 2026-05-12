import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class SegmentedDisplay here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class SegmentedDisplay extends JPanel
{
    private int spacing;
    
    public SegmentedDisplay(int h, byte d, int s) {
        super();
        this.setSize(h / 2 * d + spacing * (d + 1),  h);
        this.setBackground(Color.BLACK);
    }
    
    @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
    }
    
    
}