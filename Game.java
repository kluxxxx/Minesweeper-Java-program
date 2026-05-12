import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 Write a description of Game here.

 @AUTHOR (Darrell)
 @CONTRIBUTORS: (Darrell Leung, Nathan Huang, Sharon Li)
 @DATE (2026/05/11)
*/
public class Game extends JPanel implements ActionListener{

    /**DECLARE SWING COMPONENTS**/
    private JFrame frame;
    private JPanel hud;
    
    /**DECLARE GAME INSTANCE VARIABLES**/
    private Player player;
    private Minefield mineField;
    private int intStartMilis;
    private int intMilisElapsed;
    private boolean isRunning;

    /**CONSTRUCTOR**/
    public Game() {
        super(null);
        
        this.frame = new JFrame("Minesweeper v1.0");
        this.frame.setSize(800,600);
        this.frame.setContentPane(this);
        this.frame.show();
        
        this.generateUI();
        

        this.player = new Player();
        this.mineField = new Minefield((short)0, (short)0, 2);
        this.intStartMilis = 0;
        this.intMilisElapsed = 0;
    }
    
    private void generateUI() {
        final Border LOWERED = this.customBorder(0,false);
        final Border RAISED = this.customBorder(0,true);
        final int PADDING = 10;
        
        this.setBorder(BorderUtils.raised(5,this));
        
        this.hud = new JPanel(null);
        this.hud.setLocation(PADDING,PADDING);
        this.hud.setSize(this.getWidth() - PADDING * 2, 100);
        this.hud.setBorder(BorderUtils.raised(5,this.hud));
        this.add(this.hud);
        
        
    }
    
    private Border customBorder(int intThickness, boolean isRaised) {
        Border beveled;
        Border compound;
        if (isRaised) {
            beveled = new BevelBorder(BevelBorder.RAISED);
        }
        else {
            beveled = new BevelBorder(BevelBorder.LOWERED);
        }
        
        compound = beveled;
        for (int i = 0; i < intThickness; i++) {
            compound = new CompoundBorder(compound, beveled);
        }
        return compound;
        
    }

    /**GETTERS**/
    public Player getPlayer() {
        return this.player;
    }
    public Minefield getMineField() {
        return this.mineField;
    }
    public int getStartMilis() {
        return this.intStartMilis;
    }
    public int getMilElapsed() {
        return this.intMilisElapsed;
    }

    
    public void run() {
        final float FPS = 120;
        final int DT = (int) (1000f / FPS);
        
        this.isRunning = true;
        
        while(this.isRunning) {
            
            
            
            
            try
            {
                Thread.sleep(DT);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
            
        }
        
    }
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Tile clicked = (Tile) e.getSource();
        
        //clicked.openTile();
        
    }
}