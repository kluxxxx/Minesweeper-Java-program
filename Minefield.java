
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

import java.awt.event.ActionListener; 

import java.awt.*; 
public class Minefield extends JPanel
{
    // create 2D of tiles for the main grid
    private Tile[][] arrGrid; 
    
    // declare instance variables of type short to store minfield dimensions
    private short shrHeight, shrWidth; 
    
    // declare arralist of type tiles to store list of available tiles
    private ArrayList<Tile> lstAvailable = new ArrayList<Tile>();  
  
    // declare instance variable of type int for the amount of mines in the minefield
    private int intMines; 

    // constructor for class mineField 
    public Minefield(short w, short h, int m)
    {
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
    public void generateGrid(int intWidth, ActionListener al)
    {
        // initialize arrGrid
        this.arrGrid = new Tile[this.shrHeight][this.shrWidth]; 
        
        this.lstAvailable.clear(); 
        
        float fltTileSize = intWidth / this.shrWidth; 
        
        this.setSize(intWidth, (int)(this.shrHeight * fltTileSize)); 
        
        for(short i = 0; i < this.shrHeight; i++) 
        {
            for(short j = 0; j < this.shrWidth; j++) 
            {
                Tile template = new Tile(i, j, false, TileState.CLOSED);
                template.setSize((int)fltTileSize, (int) fltTileSize);
                template.setLocation((int) (j * fltTileSize), (int)( i * fltTileSize));
                template.addActionListener(al);
                
                
                this.arrGrid[i][j] = template; 
                this.add(template);
                
                this.lstAvailable.add(this.arrGrid[i][j]); 
                
            }
        }
    }
    
    
    public void generateMines(int intNumMines, short shrRow, short shrCol)
    {
        Tile addmine; 
        
        
        
        for(int i = shrRow - 1 ; i < shrRow + 1; i++) 
        {
            for(int j = shrCol - 1 ; j < shrCol + 1; j++) 
            {
                if (shrRow >= 0 && shrRow < shrHeight && shrCol >= 0 && shrCol < shrWidth)
                {
                    this.lstAvailable.remove(this.arrGrid[i][j]); 
                    
                    System.out.println( i + " " + j); 
                }
            }
        }
        
        for (int i = 0; i < intNumMines; i++)
        {
            addmine = this.lstAvailable.get((int)(Math.random() * this.lstAvailable.size())); 
            
            addmine.setIsMine(true); 
            
            addmine.setBackground(Color.RED); 
            
            this.lstAvailable.remove(addmine); 
            
        }
    }
    
    // code method to search for available tiles from arraylist
    public void searchMines(short shrRow, short shrCol)
    {
        for (short i = 0; i < this.lstAvailable.size(); i++)
        {
            
        }
    }
}