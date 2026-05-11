import javax.swing.*;
import java.awt.*;

/**
 Write a description of Game here.

 @AUTHOR (Darrell)
 @CONTRIBUTORS: (Darrell Leung, Nathan Huang, Sharon Li)
 @DATE (2026/05/11)
*/
public class Game extends JPanel{

    /**DECLARE SWING COMPONENTS**/
    private JFrame frame;
    
    /**DECLARE GAME INSTANCE VARIABLES**/
    private Player player;
    private Minefield mineField;
    private String intStartMilis;
    private int intMilisElapsed;

    /**DEFAULT CONSTRUCTOR**/
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

    /**SETTERS**/
    public void setPlayer(Player p) {
        this.player = p;
    }
}