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
public class Game extends JPanel implements ActionListener, MouseListener{
    
    final Color DEFAULT = new Color(188,188,188);
    final Color SHADOWS = new Color(122,122,122);
    final Color HIGHLIGHTS = new Color(254,254,254);
    
    /**DECLARE SWING COMPONENTS**/
    private JFrame frame;
    private JPanel hud;
    private JButton btnMenu;
    private JButton btnSettings;
    
    private JLabel timeDisplay;
    private JLabel minesDisplay;
    
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
        this.frame.setSize(400,600);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setIconImage(this.loadImage("minesweeper_icon.png"));
        this.frame.setResizable(false);
        //this.frame.setIconImage(new Image("textures//minesweeper_icon.png"));
        this.frame.show();
        
        this.mineField = new Minefield((short)10, (short)15, 20);
        
        this.generateUI();
        
        this.player = new Player();
        
        this.intStartMilis = 0;
        this.intMilisElapsed = 0;
        this.hasClicked = false;
    }
    
    private void generateUI() {        
        final int SPACING = 14;
        final int HUD_SPACING = 12;
        final int WIDTH = this.getWidth();
        final int HUD_HEIGHT =  70;
        final int HUD_SIZE = HUD_HEIGHT - HUD_SPACING * 2;
        
        
        
        this.hud = new JPanel(null);
        this.hud.setBackground(DEFAULT);
        this.hud.setLocation(SPACING,SPACING);
        this.hud.setSize(WIDTH - SPACING * 2, HUD_HEIGHT);
        this.hud.setBorder(BorderFactory.custom(5,SHADOWS,HIGHLIGHTS,this.hud));
        this.add(this.hud);
        
        
        this.mineField.setLocation(SPACING,(int)(SPACING * 1.7f) + this.hud.getHeight());
        this.mineField.setBackground(DEFAULT);
        this.mineField.generateGrid(WIDTH - SPACING * 2, this, HIGHLIGHTS, SHADOWS);
        this.mineField.setBorder(BorderFactory.custom(5,SHADOWS,HIGHLIGHTS,this.mineField));
        this.add(this.mineField);
        
        this.btnMenu = new JButton("☻");
        this.btnMenu.setBackground(DEFAULT);
        this.btnMenu.setSize(HUD_SIZE, HUD_SIZE);
        this.btnMenu.setLocation(this.hud.getWidth() /2 - HUD_SIZE/2, HUD_SPACING);
        this.btnMenu.setBorder(BorderFactory.custom(5,HIGHLIGHTS,SHADOWS,this.btnMenu));
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
        
        this.timeDisplay = new JLabel("000", JLabel.CENTER);
        this.timeDisplay.setOpaque(true);
        this.timeDisplay.setFont(new Font("Monospaced",Font.BOLD,(int)(HUD_SIZE * 0.9f)));
        this.timeDisplay.setSize((int)(HUD_SIZE * 1.9f), HUD_SIZE);
        this.timeDisplay.setBackground(Color.BLACK);
        this.timeDisplay.setForeground(Color.RED);
        this.timeDisplay.setBorder(BorderFactory.custom(2,SHADOWS,HIGHLIGHTS,this.timeDisplay));
        this.timeDisplay.setLocation(this.hud.getWidth() - this.timeDisplay.getWidth() - HUD_SPACING, HUD_SPACING);
        this.hud.add(timeDisplay);
        
        
        this.setPreferredSize(new Dimension(
            WIDTH,
            this.mineField.getY() + this.mineField.getHeight() + SPACING
        ));
        
        //Resize the frame to include its content pane
        this.setBackground(DEFAULT);
        this.frame.pack();
        this.setBorder(BorderFactory.custom(5,HIGHLIGHTS,SHADOWS,this));
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

    public Image loadImage(String strFileName) {
        //Declare a variable to store the image
        Image img = null;
        
        try
        {
            //Load the image ("sprites//" directs the file reader to the sprites folder)
            img = ImageIO.read(new File("sprites//"+strFileName));
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch (java.io.IOException ioe) 
        {
            ioe.printStackTrace();
        }
        
        return img;
    }
    
    //Load icons
    public Icon loadIcon(String strFileName) {
        return new ImageIcon(this.loadImage(strFileName));
    }
    
    public Icon loadIcon(String strFileName, int intWidth, int intHeight) {
        return new ImageIcon(this.loadImage(strFileName).getScaledInstance(
            intWidth, 
            intHeight,
            Image.SCALE_SMOOTH
        ));
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
            System.out.println("Second Click");
        }
        else {
            this.mineField.generateMines(30, clicked.getRow(), clicked.getColumn() );
            System.out.println("First Click");
            
            this.hasClicked = true;
        }
        
        this.mineField.openTile(clicked);
        clicked.setBorder(null);
        
        
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Detect if the use right clicked or left clicked
        //BUTTON1 = left click
        //BUTTON2 = middle click
        //BUTTON3 = r click
        if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("RIGHT CLICK");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e){
        
    }

    @Override
    public void mouseExited(MouseEvent e){
        
    }

    @Override
    public void mousePressed(MouseEvent e){
        
    }

    @Override
    public void    mouseReleased(MouseEvent e) {
        
    }
}