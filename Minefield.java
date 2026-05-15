
/**
 * Write a description of class Minefield here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// import Arraylist to store available tiles
import java.util.ArrayList; 

// import javax swing stuff
import javax.swing.*; 
import javax.swing.border.*; 

// import java.awt event stuff
import java.awt.event.MouseListener; 

// import java awt
import java.awt.*; 

public class Minefield extends JPanel
{
    // create 2D array of tiles for the main grid
    private Tile[][] arrGrid; 
    
    // declare instance variables of type short to store minfield dimensions
    private short shrHeight, shrWidth; 
    
    // declare arralist of type tiles to store list of available tiles
    private ArrayList<Tile> lstAvailable = new ArrayList<Tile>(), lstMines = new ArrayList<Tile>();  
  
    // declare instance variable of type int for the amount of mines in the minefield
    private int intMines; 

    // constructor for class mineField 
    public Minefield(short w, short h, int m)
    {
        // call superclass JPanel constructor 
        super(null);
        
        // initialize instance variables
        this.shrHeight = h; 
        this.shrWidth = w; 
        this.intMines = m; 
    }
    
    
    // getters
    public Tile[][] getTiles() 
    {
        return this.arrGrid;
    }
    
    public short getGridWidth() 
    {
        return this.shrWidth;
    }
    
    public short getGridHeight() 
    {
        return this.shrHeight;
    }
    
    public int getMines() 
    {
        return this.intMines;
    }

    public ArrayList<Tile> getAvailableTiles() 
    {
        return this.lstAvailable;
    }
    
    
    // setters
    public void setTiles(Tile[][] t) 
    {
        this.arrGrid = t;
    }
    
    public void setWidth(short w) 
    {
        this.shrWidth = w;
    }
    
    public void setHeight(short h) 
    {
        this.shrHeight = h;
    }
    
    public void setMines(int m) 
    {
        this.intMines = m;
    }

    public void setAvailableTiles(ArrayList<Tile> l) 
    {
        this.lstAvailable = l; 
    }
    
    
    // code generate grid method
    public void generateGrid(MouseListener ml, Color highlights, Color shadows, int tileSize)
    {
        // initialize arrGrid
        this.arrGrid = new Tile[this.shrHeight][this.shrWidth]; 
        
        this.lstAvailable.clear(); 
        
        // float fltTileSize = (intWidth - 5 * 2f) / this.shrWidth; 
        
        this.setSize((this.shrWidth * tileSize) + 4 * 2, (this.shrHeight * tileSize) + 4 * 2); 
        
        for(short i = 0; i < this.shrHeight; i++) 
        {
            for(short j = 0; j < this.shrWidth; j++) 
            {
                Tile template = new Tile(i, j, false, TileState.CLOSED);
                template.setSize(tileSize, tileSize);
                template.setLocation((j * tileSize)+4, ( i * tileSize)+4);
                template.setBackground(this.getBackground());
                template.setBorder(BorderFactory.custom(4,highlights,shadows,template));
                template.addMouseListener(ml);
                
                this.arrGrid[i][j] = template; 
                this.add(template);
                
                this.lstAvailable.add(this.arrGrid[i][j]); 
                
            }
        }
    }
    
    
    public void generateMines(int intNumMines, short shrRow, short shrCol)
    {
        Tile addmine; 
        
        for(int i = shrRow - 1 ; i <= shrRow + 1; i++) 
        {
            for(int j = shrCol - 1 ; j <= shrCol + 1; j++) 
            {
                
                if (i >= 0 && i < this.shrHeight && j >= 0 && j < this.shrWidth)
                {
                    this.lstAvailable.remove(this.arrGrid[i][j]); 
                    
                  
                }
            }
        }
        
        for (int i = 0; i < intNumMines; i++)
        {
            addmine = this.lstAvailable.get((int)(Math.random() * this.lstAvailable.size())); 
            
            addmine.setIsMine(true); 
            
            // addmine.setBackground(Color.BLUE); 
            
            this.lstAvailable.remove(addmine); 
            
            this.lstMines.add(addmine); 
        }
    }
    
    // code method to search for available tiles from arraylist
    public void searchMines(short shrRow, short shrCol)
    {
        for (short i = 0; i < this.lstAvailable.size(); i++)
        {
            
        }
    }
    
    // code method to recursively check of surrounding tile has mine neighbours
    public boolean openTile(Tile tile, AbstractBorder openBorder)
    {
       short shrRow, shrCol; 
       byte minesCount =0 ; 
       
       if(tile.getIsMine() == true)
       {
           return true; 
       }
       
       if( !tile.isOpen())
       {
           
            boolean b;            
           
           tile.setState(TileState.OPEN);  
            shrRow = tile.getRow(); 
            shrCol = tile.getColumn(); 
               
            tile.setBorder(openBorder);
            
           for(int i = shrRow - 1 ; i <= shrRow + 1; i++) 
            {
             for(int j = shrCol - 1 ; j <= shrCol + 1; j++) 
             {
                
                if (i >= 0 && i < shrHeight && j >= 0 && j < shrWidth && !this.arrGrid[i][j].equals(tile))
                {
                    if (this.arrGrid[i][j].getIsMine() == true)
                    {
                        minesCount += 1; 
                    }
                }
             }
            }
            
           if (minesCount == 0)
           {

               for(int i = shrRow - 1 ; i <= shrRow + 1; i++) 
                {
                     for(int j = shrCol - 1 ; j <= shrCol + 1; j++) 
                     {
                         if (i >= 0 && i < shrHeight && j >= 0 && j < shrWidth && !this.arrGrid[i][j].equals(tile))
                        {
                            
                            openTile(this.arrGrid[i][j], openBorder); 
                        }
                 }
                }
           }
           else 
           {
               tile.setText("" + minesCount); 
           }
       }
       
       return false; 
    }
    
    // method to reveal the location of all mines when game is lost
    public void revealMines ()
    {
        for(int i = 0 ; i < this.shrWidth; i++) 
            {
             for(int j = 0 ; j < this.shrHeight; j++) 
             {
                    if (this.arrGrid[j][i].getIsMine() == true)
                    {
                        this.arrGrid[j][i].setBackground(Color.RED); 
                    }
             }
            }
    }
    
    // check if won
    public boolean ifWon()
    {
        for(int i = 0 ; i < this.shrWidth; i++) 
            {
             for(int j = 0 ; j < this.shrHeight; j++) 
             {
                    if (this.arrGrid[j][i].getIsMine() == false && this.arrGrid[j][i].getState() == TileState.CLOSED)
                    {
                        return false; 
                    }
             }
            }
        
            System.out.println("ok"); 
        return true; 
    }
    
    public void flagTile(Tile tileClicked)
    {
        if(tileClicked.getState() != TileState.OPEN)
        {
             if (tileClicked.getState() == TileState.FLAGGED)
             {
                 tileClicked.setState(TileState.CLOSED); 
                 
                 tileClicked.setText(""); 
             }
             else
             {
                 tileClicked.setState(TileState.FLAGGED); 
                 
                 tileClicked.setText("f"); 
             }
             
        }
    }
    
    public void revealHint()
    {
        int i = (int)(Math.random() * this.lstMines.size());
        
        this.lstMines.get(i).setBackground(Color.RED);
    }
}