
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
    
    /**GETTERS**/
    public ArrayList<Tile> getTiles() {
        return this.lstTiles;
    }
    public short getGridWidth() {
        return this.shrWidth;
    }
    public short geGridHeight() {
        return this.shrHeight;
    }
    public int getMines() {
        return this.intMines;
    }

    /**SETTERS**/
    public void setTiles(ArrayList<Tile> t) {
        this.lstTiles = t;
    }
    public void setWidth(short w) {
        this.shrWidth = w;
    }
    public void setHeight(short h) {
        this.shrHeight = h;
    }
    public void setMines(int m) {
        this.intMines = m;
    }

}