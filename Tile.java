/**
 * Write a description of class Tile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

//import JButton to make tiles clickable
import javax.swing.JButton;
import javax.swing.*;

public class Tile extends JButton
{
   // declare instance variables of type short for the position of the tile in the minefield
   private short shrRow, shrCol; 
   
   // declare instance variable of type enum for the state of the tile
   private TileState tileState; 
   
   // declare instance variable of type boolean for if the tile contains a mine
   private boolean isMine; 
   
   // declare instance variable of type byte to store num neighbours
   // private byte bytNeighbours; 
   
   // code constructor for creating new Tile objects
   public Tile(short r, short c, boolean m, TileState s)
   {
       // initialize instance variables
       this.shrRow = r;
       this.shrCol = c; 
       this.isMine = m; 
       this.tileState = s; 
   }
   
   // code getters
   public short getRow() 
    {
        return this.shrRow;
    }
    
   public short getColumn() 
   {
        return this.shrCol;
   }
   
   public boolean getIsMine() 
   {
        return this.isMine;
   }
   
   public TileState getState() 
   {
        return this.tileState;
   }
   
   // setters
   public void setIsMine(boolean s) 
   {
         this.isMine = s;
   }
   
   public void setState(TileState t) 
   {
        this.tileState = t;
   }
   
   public void setRow(short r) 
    {
        this.shrRow = r;
    }
    
   public void setColumn(short c) 
   {
        this.shrCol = c;
   }
   
   // method to check if tile is open
   public boolean isOpen()
   {
       return this.tileState == TileState.OPEN; 
   }
   
   @Override
   public void setIcon(Icon icon) {
       super.setIcon(icon);
       super.setDisabledIcon(icon);
   }
}