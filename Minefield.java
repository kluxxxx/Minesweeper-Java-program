
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
public class Minefield extends JPanel
{
    // create arraylist of tiles for the main grid
    private ArrayList<Tile> lstTiles; 
    
    // declare instance variables of type short to store minfield dimensions
    private short shrHeight, shrWidth; 
    
    // declare instance variable of type int for the amount of mines in the minefield
    private int intMines; 

    // constructor for class mineField 
    public Minefield(short h, short w, int m, ArrayList<Tile> l)
    {
        // initialize instance variables
        this.shrHeight = h; 
        this.shrWidth = w; 
        this.intMines = m; 
        this.lstTiles = l; 
    }
    
    // default constructor 
    public Minefield()
    {
        // initialize instance variables
        this.shrHeight = 0; 
        this.shrWidth = 0; 
        this.intMines = 0; 
        this.lstTiles = new ArrayList<Tile>(); 
    }
    
    
}