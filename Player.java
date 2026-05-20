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
    final String FILE_NAME;
    int intHighScoreEasy;
    int intHighScoreMedium;
    int intHighScoreHard;
    int intGamesPlayed;
    int intTotalTimePlayed;
    int intGamesWon;
    int intGamesLost;
    
    
    /**CONSTRUCTOR**/
    public Player(String n){
        this.intHighScoreEasy = Integer.MAX_VALUE;
        this.intHighScoreMedium = Integer.MAX_VALUE;
        this.intHighScoreHard = Integer.MAX_VALUE;
        this.intGamesPlayed = 0;
        this.intTotalTimePlayed = 0;
        this.intGamesWon = 0;
        this.intGamesLost = 0;
        this.FILE_NAME = n.toLowerCase() + ".txt";
    }
    
    /**CONSTRUCTOR DEFAULT**/
    public Player(){
        this.intHighScoreEasy = Integer.MAX_VALUE;
        this.intHighScoreMedium = Integer.MAX_VALUE;
        this.intHighScoreHard = Integer.MAX_VALUE;
        this.intGamesPlayed = 0;
        this.intTotalTimePlayed = 0;
        this.intGamesWon = 0;
        this.intGamesLost = 0;
        this.FILE_NAME = "";
    }
    
    //create a method that will update all statistics before its saved to file
    public void updateStats(Game game, String strDifficulty){    
        //update total time aplication has been runned for on that device
        this.intTotalTimePlayed += game.getTime();
            
        //create an if statement that will check game method won or lost and update the total games wona and lost counter and if game is won and time is lower then highscore counter will be updated
        if(game.isWon()){
            this.intGamesWon++;
            switch(strDifficulty){
                case"EASY":
                 {
                    if(intHighScoreEasy > game.getTime()){
                        intHighScoreEasy = game.getTime();
                    }
                 }break;
                case"MEDIUM":
                 {
                    if(intHighScoreMedium > game.getTime()){
                        intHighScoreMedium = game.getTime();
                    }
                 }break;
                case"HARD":
                 {
                    if(intHighScoreMedium > game.getTime()){
                        intHighScoreMedium = game.getTime();
                    }
                 }break;
            }
        }else{
            this.intGamesLost++;
        }
            
        //update total games played
        this.intGamesPlayed++;
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
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.FILE_NAME));
            player = (Player)in.readObject();
            in.close();
            this.intHighScoreEasy = player.intHighScoreEasy;
            this.intHighScoreMedium = player.intHighScoreMedium;
            this.intHighScoreHard = player.intHighScoreHard;
            this.intGamesPlayed = player.intGamesPlayed;
            this.intTotalTimePlayed = player.intTotalTimePlayed;
            this.intGamesWon = player.intGamesWon;
            this.intGamesLost = player.intGamesLost;
        }catch(FileNotFoundException e) {
            //if player is new to the game then player defaults will be set and saved to player file
            saveToFile();
        }catch(IOException e) {
            
        }catch (ClassNotFoundException e){
            System.out.println("Error: Object'c class does not match");
        }
        System.out.println(this.toString());
    }
    
    //create a to string that will return the users statistics to be displayed through gui elements as well as calculate the player winrate
    public String toString(){
        return "----------PLAYER STATS----------\n Total Games Played: " + intGamesPlayed + "\n Games Won: " + intGamesWon + "\n Games Lost: " + intGamesLost + "\n----------PLAYER HIGHSCORES----------\n EASY MODE: " 
        + intHighScoreEasy + "S\n MEDIUM MODE: " + intHighScoreMedium + "S\n HARD MODE: " + intHighScoreHard + "S\n Win Loss Ratio: " + String.format("%.2f",intGamesWon/intGamesLost * 1f) + "% \n Total Time Played: " + intTotalTimePlayed + "S";
    }
    
    
}