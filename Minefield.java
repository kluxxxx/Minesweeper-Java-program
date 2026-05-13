
/**
 * Write a description of class Minefield here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// import arrayList to make the minfield grid
import java.util.ArrayList; 

// import javax swing stuff
import javax.swing.*;  

import java.awt.event.ActionListener; 
public class Minefield extends JPanel
{
    // create arraylist of tiles for the main grid
    private ArrayList<Tile> lstTiles; 
    
    // declare instance variables of type short to store minfield dimensions
    private short shrHeight, shrWidth; 
    
  
    // declare instance variable of type int for the amount of mines in the minefield
    private int intMines; 

    // constructor for class mineField 
    public Minefield(short w, short h, int m)
    {
        // initialize instance variables
        this.shrHeight = h; 
        this.shrWidth = w; 
        this.intMines = m; 
    }
    
    
    // getters
    public ArrayList<Tile> getTiles() 
    {
        return this.lstTiles;
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

    // setters
    public void setTiles(ArrayList<Tile> t) 
    {
        this.lstTiles = t;
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

    // code generate grid method
    public void generateGrid(int intWidth, ActionListener al)
    {
        float fltTileSize = intWidth / this.shrWidth; 
        
        this.setSize(intWidth, (int)(this.shrHeight * fltTileSize)); 

        for(short i = 0; i < this.shrHeight; i++) 
        {
            for(short j = 0; j < this.shrWidth; j++) 
            {
                this.lstTiles.add(new Tile(i, j, false, TileState.CLOSED)); 
            }
        }
        

    }
}