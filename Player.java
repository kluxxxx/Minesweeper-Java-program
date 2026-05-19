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
    
    /**CONSTRUCTOR**/
    public Player(){
        int intHighScore = Integer.MAX_VALUE;
        int intGamesPlayed = 0;
        int intTotalTimePlayed = 0;
        int intGamesWon = 0;
        int intGamesLost = 0;
    }
    
    //create a method that will update all statistics before its saved to file
    public void updateStats(Game game){
        Player player = new Player();
            
        //update total time aplication has been runned for on that device
        player.intTotalTimePlayed += game.getTime();
            
        //create an if statement that will check game method won or lost and update the total games wona and lost counter and if game is won and time is lower then highscore counter will be updated
        if(game.isWon()){
            player.intGamesWon++;
            if(game.getTime()< player.intHighScore ){
                player.intHighScore = game.getTime();
            }
        }else{
            player.intGamesLost++;
        }
            
        //update total games played
        player.intGamesPlayed++;
    }
    
    // create a method that will save player statistics to file
    public void saveToFile(){
        try{
            // use object output stream to write the whole object to file
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(this);
            out.close();
        }catch(FileNotFoundException e) {
            
        }catch(IOException e) {
            System.out.println("Error: Cannot read from file");
        }
    }
    
    // create a method that will load pevious game stats from file to game
    public void loadFromFile(){
        try{
            //use object input stream to load player statistics from file to object variables
            Player player = new Player();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            player = (Player)in.readObject();
            in.close();
        }catch(FileNotFoundException e) {
            //if player is new to the game then player defaults will be set and saved to player file
            saveToFile();
        }catch(IOException e) {
            
        }catch (ClassNotFoundException e){
            System.out.println("Error: Object'c class does not match");
        }
    }
    
    //create a to string that will return the users statistics to be displayed through gui elements as well as calculate the player winrate
    public String toString(){
        return "----------PLAYER STATS---------- \n Highscore: " + intHighScore + "S \n Total Games Played: " + intGamesPlayed + "\n Games Won: " + intGamesWon + "\n Games Lost: " + intGamesLost +
        "\n Win Loss Ratio: " + String.format("%.2f",intGamesWon/intGamesLost * 1f) + "% \n Total Time Played: " + intTotalTimePlayed + "S";
    }
    
    
}