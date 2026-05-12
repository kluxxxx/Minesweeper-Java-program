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
    private JButton btnMenu;
    private JButton btnSettings;
    
    private SegmentedDisplay timeDisplay;
    private SegmentedDisplay minesDisplay;
    
    /**DECLARE GAME INSTANCE VARIABLES**/
    private Player player;
    private Minefield mineField;
    private int intStartMilis;
    private int intMilisElapsed;
    private boolean isRunning;
    private boolean hasClicked;

    /**CONSTRUCTOR**/
    public Game() {
        super(null);
        
        this.frame = new JFrame("Minesweeper v1.0");
        this.frame.setContentPane(this);
        this.frame.setSize(600,600);
        this.frame.show();
        
        this.mineField = new Minefield((short)10, (short)15, 20);
        
        this.generateUI();
        
        this.player = new Player();
        
        this.intStartMilis = 0;
        this.intMilisElapsed = 0;
        this.hasClicked = false;
    }
    
    private void generateUI() {
        final Border LOWERED = this.customBorder(0,false);
        final Border RAISED = this.customBorder(0,true);
        final int PADDING = 10;
        final int WIDTH = this.getWidth();
        final int HUD_HEIGHT =  WIDTH / 9;
        final int BTN_SIZE = HUD_HEIGHT - PADDING * 2;
        
        this.hud = new JPanel(null);
        this.hud.setLocation(PADDING,PADDING);
        this.hud.setSize(WIDTH - PADDING * 2, HUD_HEIGHT);
        this.hud.setBorder(BorderUtils.lowered(5,this.hud));
        this.add(this.hud);
        
        
        this.mineField.setLocation(PADDING,PADDING * 2 + this.hud.getHeight());
        this.mineField.generateGrid(WIDTH - PADDING * 2);
        this.mineField.setBorder(BorderUtils.lowered(5,this.mineField));
        this.add(this.mineField);
        
        this.btnMenu = new JButton("☻");
        this.btnMenu.setSize(BTN_SIZE, BTN_SIZE);
        this.btnMenu.setLocation(this.hud.getWidth() /2 - BTN_SIZE/2, PADDING);
        this.btnMenu.setBorder(BorderUtils.raised(5,this.btnMenu));
        this.hud.add(btnMenu);
        
        this.minesDisplay = new SegmentedDisplay(BTN_SIZE, (byte) 3, 5);
        this.minesDisplay.setLocation(this.hud.getWidth() /2 + BTN_SIZE/2 + PADDING, PADDING);
        this.hud.add(minesDisplay);
        
        this.timeDisplay = new SegmentedDisplay(BTN_SIZE, (byte) 3, 5);
        this.timeDisplay.setLocation(this.hud.getWidth() /2 - BTN_SIZE/2 - this.timeDisplay.getWidth() - PADDING, PADDING);
        this.hud.add(timeDisplay);
        
        
        this.setPreferredSize(new Dimension(
            WIDTH,
            this.hud.getHeight() + this.mineField.getHeight() + PADDING * 3
        ));
        
        //Resize the frame to include its content pane
        this.frame.pack();
        this.setBorder(BorderUtils.raised(5,this));
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
        
        if (this.hasClicked) {
            
        }
        else {
            //this.mineField.generateMines();
            System.out.println("First Click");
            
            this.hasClicked = true;
        }
        
        //clicked.openTile();
        
    }
}