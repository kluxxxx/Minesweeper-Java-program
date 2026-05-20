import javax.swing.*;
import java.awt.*;
import java.util.*;

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
    
    final int V_SPACING = 14;
    final int H_SPACING = 50;
    
    final int BTN_SIZE = 40;
    
    //Swing objects
    private JButton btnEasy, btnMedium, btnHard, btnCustom;
    
    private ArrayList<JButton> lstButtons;
    
    private JButton btnStats, btnLeaderBoard, btnSmiley;
    
    private JSlider inputWidth, inputHeight, inputMines;
    
    //Other instance variables
    private  String strSize;
    private short shrWidth, shrHeight;
    
    
    
    public Menu(int w, int h) {
        super(null);
        this.setPreferredSize(new Dimension(w, h));
        this.setSize(w,h);
        this.setBackground(DEFAULT);
        this.setBorder(BorderFactory.custom(4,HIGHLIGHTS,SHADOWS,this));
        
        
        this.generateUI();
        this.btnEasy.doClick();
        
        
        JFrame test= new JFrame("Menu");
        test.setContentPane(this);
        test.pack();
        test.show();
        
    }
    
    public void generateUI() {
        this.lstButtons = new ArrayList<JButton>();
        
        JButton template = new JButton();
        template.setLocation(H_SPACING, V_SPACING);
        template.setSize(this.getWidth() - H_SPACING * 2, BTN_SIZE);
        
        this.btnEasy = this.appendBtn(template, "EASY");
        this.btnMedium =  this.appendBtn(this.btnEasy, "MEDIUM");
        this.btnHard =  this.appendBtn(this.btnMedium, "HARD");
        this.btnCustom =  this.appendBtn(this.btnHard, "CUSTOM");
        
        this.btnStats = new JButton("?");
        this.btnStats.setSize(BTN_SIZE,BTN_SIZE);
        this.btnStats.setLocation(V_SPACING, this.getHeight() - BTN_SIZE - V_SPACING);
        this.btnStats.setBackground(DEFAULT);
        this.btnStats.setBorder(BorderFactory.outlined(4,HIGHLIGHTS,SHADOWS,2,SHADOWS,btnStats));
        this.add(btnStats);
        
        this.btnLeaderBoard = new JButton("HS");
        this.btnLeaderBoard.setSize(BTN_SIZE,BTN_SIZE);
        this.btnLeaderBoard.setLocation(this.getWidth() - BTN_SIZE - V_SPACING, this.getHeight() - BTN_SIZE - V_SPACING);
        this.btnLeaderBoard.setBackground(DEFAULT);
        this.btnLeaderBoard.setBorder(BorderFactory.outlined(4,HIGHLIGHTS,SHADOWS,2,SHADOWS,btnLeaderBoard));
        this.add(btnLeaderBoard);
        
        this.btnSmiley = new JButton(IconManager.loadIcon("smiley.png", BTN_SIZE - 14, BTN_SIZE - 14));
        this.btnSmiley.setSize(BTN_SIZE,BTN_SIZE);
        this.btnSmiley.setLocation(this.getWidth() / 2 - BTN_SIZE / 2, V_SPACING);
        this.btnSmiley.setBackground(DEFAULT);
        this.btnSmiley.setBorder(BorderFactory.outlined(4,HIGHLIGHTS,SHADOWS,2,SHADOWS,btnSmiley));
        this.add(btnSmiley);
        
        final int SLIDER_WIDTH = template.getWidth() / 3;
        this.inputWidth = this.defaultSlider(10, 80, 15);  //min, max, val
        this.inputWidth.setSize(SLIDER_WIDTH, 100);
        this.inputWidth.setLocation(template.getX(), this.btnCustom.getY() + BTN_SIZE + V_SPACING);
        this.inputWidth.setBackground(DEFAULT);
        this.add(inputWidth);
        
        this.inputHeight = this.defaultSlider( 10, 80, 15);  //min, max, val
        this.inputHeight.setSize(SLIDER_WIDTH, 100);
        this.inputHeight.setLocation(template.getX() + SLIDER_WIDTH, this.btnCustom.getY() + BTN_SIZE + V_SPACING);
        this.inputHeight.setBackground(DEFAULT);
        this.add(inputHeight);
        
        this.inputMines = this.defaultSlider( 10, 80, 15);  //min, max, val
        this.inputMines.setSize(SLIDER_WIDTH, 100);
        this.inputMines.setLocation(template.getX() + SLIDER_WIDTH * 2, this.btnCustom.getY() + BTN_SIZE + V_SPACING);
        this.inputMines.setBackground(DEFAULT);
        this.add(inputMines);
        
    }
    
    private JSlider defaultSlider(int min, int max, int val) {
        JSlider slider = new JSlider(JSlider.VERTICAL, min, max, val); 
        slider.setBackground(DEFAULT);
        slider.setMinorTickSpacing(10);
        slider.setMajorTickSpacing(10);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setSnapToTicks(true);
        return slider;
    }
    
    private JButton appendBtn(AbstractButton prev, String text) {
        JButton btn = new JButton(text);
        btn.setSize(prev.getSize());
        btn.setBackground(DEFAULT);
        btn.setLocation(
            prev.getX(),
            prev.getY() + prev.getHeight() + V_SPACING
        );
        
        btn.setBorder(BorderFactory.outlined(4,HIGHLIGHTS,SHADOWS,2,SHADOWS,btn));
        this.add(btn);
        this.lstButtons.add(btn);
        
        btn.addActionListener(e-> {
            btn.setSelected(true);
            btn.setBorder(BorderFactory.outlined(4,SHADOWS,HIGHLIGHTS,2,SHADOWS,btn));
            //btn.setBackground(SHADOWS);
            for (JButton b : this.lstButtons) {
                 
                if (!b.equals(btn)) {
                    b.setSelected(false);
                    //b.setBackground(DEFAULT);
                    b.setBorder(BorderFactory.outlined(4,HIGHLIGHTS,SHADOWS,2,SHADOWS,btn));
                }
            }
        });
        
        return btn;
    }
    
}