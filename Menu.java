import javax.swing.*;
import java.awt.*;

/**
 * Write a description of class Menu here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Menu extends JPanel
{
    //Constants
    final Color DEFAULT = new Color(188,188,188);
    final Color SHADOWS = new Color(122,122,122);
    final Color HIGHLIGHTS = new Color(254,254,254);
    
    //Swing objects
    private JToggleButton btnEasy, btnMedium, btnHard, btnCustom;
    
    
    public Menu(int w, int h) {
        super(null);
        this.setPreferredSize(new Dimension(w, h));
        this.setBorder(BorderFactory.custom(4,SHADOWS,HIGHLIGHTS,this));
        
        
        this.generateUI();
        
    }
    
    public void generateUI() {
        final int SPACING = 14;
        
        this.btnEasy = new JToggleButton("EASY");
        this.btnEasy.setSize(this.getWidth() - SPACING * 2, 50);
        this.btnEasy.setBackground(DEFAULT);
        this.btnEasy.setBorder(BorderFactory.custom(4,SHADOWS,HIGHLIGHTS,this.btnEasy));
        this.add(this.btnEasy);
        
        this.btnMedium =  this.appendBtn(this.btnEasy, SPACING);
        this.btnHard =  this.appendBtn(this.btnMedium, SPACING);
        this.btnCustom =  this.appendBtn(this.btnHard, SPACING);
    }
    
    private JToggleButton appendBtn(AbstractButton prev, int SPACING) {
        JToggleButton btn = new JToggleButton();
        btn.setSize(prev.getSize());
        btn.setBackground(DEFAULT);
        btn.setLocation(
            prev.getX(),
            prev.getY() + prev.getHeight() + SPACING
        );
        
        btn.setBorder(BorderFactory.custom(4,SHADOWS,HIGHLIGHTS,btn));
        this.add(btn);
        
        return btn;
    }
    
}