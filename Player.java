import java.io.*;
/**
 * Write a description of class Player here.
 *
 * @author (Nathan Huang)
 * @version (5/11/26)
 */
public class Player implements java.io.Serializable
{
    // declare all instance variables
    final String FILE_NAME = "Player.txt";
    int intHighScore;
    int intGamesPlayed;
    int intTotalTimePlayed;
    int intGamesWon;
    int intGamesLost;
    
    //set default values based off default constructor
    public Player(){
        int intHighScore = 0;
        int intGamesPlayed = 0;
        int intTotalTimePlayed = 0;
        int intGamesWon = 0;
        int intGamesLost = 0;
    }
    
    // create a method that will update the playes statistic based off of the previous game played and save it to file
    public void saveToFile(Game game){
        //update total time aplication has been runned for on that device
        //intTotalTimePlayed += game.getTime();
        
        
    }
    
    // create a method that will load pevious game stats from file to game
    public void loadFromFile(){
        try{
            Player player = new Player();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            player = (Player)in.readObject();
            in.close();
  
            
        }catch(FileNotFoundException e) {
            
        }catch(IOException e) {
            System.out.println("Error: Cannot read from file");
        }catch (ClassNotFoundException e){
            System.out.println("Error: EOF encountered, file may be corrupt");
        }
    }
}