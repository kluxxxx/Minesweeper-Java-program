import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import java.io.File;

/**
 Write a description of Game here.

 @AUTHOR (Darrell)
 @CONTRIBUTORS: (Darrell Leung, Nathan Huang, Sharon Li)
 @DATE (2026/05/11)
*/
public class Game extends JPanel implements MouseListener{
    /**CONSTANTS**/
    final Color DEFAULT = new Color(188,188,188);
    final Color SHADOWS = new Color(122,122,122);
    final Color HIGHLIGHTS = new Color(254,254,254);
    
    final int SPACING = 14;
    final int HUD_SPACING = 12;
    final int HUD_HEIGHT =  70;
    final int HUD_SIZE = HUD_HEIGHT - HUD_SPACING * 2;
    
    /**DECLARE SWING COMPONENTS**/
    private JFrame frame;
    private JPanel hud;
    private JButton btnMenu;
    private JButton btnSettings;
    
    private Timer timer;
    private JLabel minesDisplay;
    
    /**DECLARE GAME INSTANCE VARIABLES**/
    private Player player;
    private Minefield mineField;
    private byte bytMinesRemaining = 40;
    private int intTimer;
    private boolean isRunning;
    private boolean hasClicked;
    private boolean isWon;

    /**CONSTRUCTOR**/
    public Game() {
        super(null);
        
        this.frame = new JFrame("Minesweeper v1.0");
        this.frame.setContentPane(this);
        this.frame.setSize(400,600);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setIconImage(IconManager.loadImage("minesweeper_icon.png"));
        this.frame.setResizable(false);
        //this.frame.setIconImage(new Image("textures//minesweeper_icon.png"));
        this.frame.show();
        
        this.mineField = new Minefield((short)10, (short)15, 40, 32);
        
        this.generateUI();
        
        this.player = new Player("darrell");
        
        this.intTimer = 0;
        this.hasClicked = false;
    }
    
    //create a method that will generate all the ui components for the playable game
    private void generateUI() {        
        
        
        //Generate the minefield grid
        this.mineField.setBackground(DEFAULT);
        this.mineField.generateGrid(this, HIGHLIGHTS, SHADOWS);
        final int WIDTH = this.mineField.getWidth() + SPACING * 2;
        
        this.hud = new JPanel(null);
        this.hud.setBackground(DEFAULT);
        this.hud.setLocation(SPACING,SPACING);
        this.hud.setSize(WIDTH - SPACING * 2, HUD_HEIGHT);
        this.hud.setBorder(BorderFactory.custom(4,SHADOWS,HIGHLIGHTS,this.hud));
        this.add(this.hud);
        
        
        this.mineField.setLocation(SPACING,(int)(SPACING * 1.7f) + this.hud.getHeight());
        
        this.mineField.setBorder(BorderFactory.custom(4,SHADOWS,HIGHLIGHTS,this.mineField));
        this.add(this.mineField);
        
        this.btnMenu = new JButton();
        this.btnMenu.setIcon(IconManager.loadIcon("smiley.png", HUD_SIZE - 15, HUD_SIZE - 15));
        this.btnMenu.setBackground(DEFAULT);
        this.btnMenu.setSize(HUD_SIZE, HUD_SIZE);
        this.btnMenu.setLocation(this.hud.getWidth() /2 - HUD_SIZE/2, HUD_SPACING);
        this.btnMenu.setBorder(BorderFactory.outlined(4,HIGHLIGHTS,SHADOWS,2,SHADOWS,this.btnMenu));
        this.hud.add(btnMenu);
        
        this.minesDisplay = new JLabel("010", JLabel.CENTER);
        this.minesDisplay.setOpaque(true);
        this.minesDisplay.setFont(new Font("Monospaced",Font.BOLD,(int)(HUD_SIZE * 0.9f)));
        this.minesDisplay.setSize((int)(HUD_SIZE * 1.9f), HUD_SIZE);
        this.minesDisplay.setBackground(Color.BLACK);
        this.minesDisplay.setForeground(Color.RED);
        this.minesDisplay.setBorder(BorderFactory.custom(2,SHADOWS,HIGHLIGHTS,this.minesDisplay));
        this.minesDisplay.setLocation(HUD_SPACING, HUD_SPACING);
        this.hud.add(minesDisplay);
        
        this.timer = new Timer();
        this.timer.setOpaque(true);
        this.timer.setFont(new Font("Monospaced",Font.BOLD,(int)(HUD_SIZE * 0.9f)));
        this.timer.setSize((int)(HUD_SIZE * 1.9f), HUD_SIZE);
        this.timer.setBackground(Color.BLACK);
        this.timer.setForeground(Color.RED);
        this.timer.setBorder(BorderFactory.custom(2,SHADOWS,HIGHLIGHTS,this.timer));
        this.timer.setLocation(this.hud.getWidth() - this.timer.getWidth() - HUD_SPACING, HUD_SPACING);
        this.hud.add(timer);
        
        
        this.setPreferredSize(new Dimension(
            WIDTH,
            this.mineField.getY() + this.mineField.getHeight() + SPACING
        ));
        
        //Resize the frame to include its content pane
        this.setBackground(DEFAULT);
        this.frame.pack();
        this.setBorder(BorderFactory.custom(4,HIGHLIGHTS,SHADOWS,this));
    }


    /**GETTERS**/
    public Player getPlayer() {
        return this.player;
    }
    public Minefield getMineField() {
        return this.mineField;
    }
    public int getTime() {
        return this.timer.getTime();
    }
    public boolean isWon() {
        return this.isWon;
    }

    //create a method that will time the user from the start of the game to the end of there gmae and is stopped through victory or loss
    public void startTimer() {
    
        final int deltaT = 1000;
        
        this.intTimer = 0;
        this.isRunning = true;
        
        while(this.isRunning) {
            
            //Update timer
            this.intTimer++;
            
            //Update label
            this.timer.setText(""+intTimer);
            
            
            //Wait 1 second
            try
            {
                Thread.sleep(deltaT);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
            
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e){
        
    }

    @Override
    public void mouseExited(MouseEvent e){
        
    }

    @Override
    public void mousePressed(MouseEvent e){
        if (e.getButton() == MouseEvent.BUTTON1) {
            this.btnMenu.setIcon(IconManager.loadIcon("smiley_surprise.png", HUD_SIZE - 15, HUD_SIZE - 15));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Tile clicked = (Tile) e.getSource();
        
        final MatteBorder OPEN_BORDER = new MatteBorder(1,1,0,0,SHADOWS);
        //Detect if the use right clicked or left clicked
        //BUTTON1 = left click
        //BUTTON2 = middle click
        //BUTTON3 = r click
        if (e.getButton() == MouseEvent.BUTTON1) {
            //System.out.println("LEFT CLICK");
            
            boolean hasLost; 
            boolean hasWon; 
            
            if (this.hasClicked) {
                hasLost = this.mineField.openTile(clicked, OPEN_BORDER);
                hasWon = this.mineField.ifWon();
                
                if (hasLost)
                {
                    this.mineField.revealMines(clicked, OPEN_BORDER); 
                    this.btnMenu.setIcon(IconManager.loadIcon("smiley_lost.png", HUD_SIZE - 15, HUD_SIZE - 15));
                    this.timer.end();
                    
                    player.updateStats(this, false);
                    player.saveToFile();
                }
                else if (hasWon) {
                    System.out.println("yay"); 
                    this.btnMenu.setIcon(IconManager.loadIcon("smiley_won.png", HUD_SIZE - 15, HUD_SIZE - 15));
                    this.timer.end();
                    
                    player.updateStats(this, true);
                    player.saveToFile();
                }
                else {
                    this.btnMenu.setIcon(IconManager.loadIcon("smiley.png", HUD_SIZE - 15, HUD_SIZE - 15));
        
                }
                
            }
            else {
                this.mineField.generateMines(clicked.getRow(), clicked.getColumn() );
                
                this.hasClicked = true;
                
                this.mineField.openTile(clicked, new MatteBorder(1,1,0,0,SHADOWS));
                this.btnMenu.setIcon(IconManager.loadIcon("smiley.png", HUD_SIZE - 15, HUD_SIZE - 15));
        
                Thread timerThread = new Thread(this.timer);
                
                
                timerThread.start();
            }
            
        }
        else if (e.getButton() == MouseEvent.BUTTON3) {
            //System.out.println("RIGHT CLICK");
             
            
            bytMinesRemaining += this.mineField.flagTile(clicked); 
            
            System.out.println("" + bytMinesRemaining);
        }
    }
    
    
}