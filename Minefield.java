
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
    ArrayList<Tile> lstTiles; 
    
    // declare instance variables of type short to store minfield dimensions
    short shrHeight, shrWidth; 
    
    // declare instance variable of type int for the amount of mines in the minefield
    int intMines; 

    // constructor for class mineField 
    public Minefield(short h, short w, int m)
    {
        // initialize instance variables
        this.shrHeight = h; 
        this.shrWidth = w; 
        this.intMines = m; 
    }

}