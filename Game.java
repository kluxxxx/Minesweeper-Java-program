import javax.swing.*;
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
    
    /**DECLARE GAME INSTANCE VARIABLES**/
    private Player player;
    private Minefield mineField;
    private String intStartMilis;
    private int intMilisElapsed;
    private boolean isRunning;

    /**CONSTRUCTOR**/
    public Game() {
        super(null);
        this.setBackground(Color.WHITE);
        
        
        this.frame = new JFrame("Minesweeper v1.0");
        this.frame.setSize(1000,1000);
        this.frame.setContentPane(this);
        this.frame.show();
        

        this.player = new Player();
        this.mineField = new Minefield();
        this.intStartMilis = "UNKNOWN";
        this.intMilisElapsed = 0;
    }

    /**GETTERS**/
    public Player getPlayer() {
        return this.player;
    }
    public Minefield getMineField() {
        return this.mineField;
    }
    public String getStartMil() {
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