import javax.swing.*;

/**
 * Write a description of class Timer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Timer extends JLabel implements Runnable
{
    private int intTime;
    private boolean isRunning;
    private boolean isPaused;
    
    public Timer() {
        super("000", JLabel.CENTER);
        this.isRunning = false;
    }
    
    public int getTime() {
        return this.intTime;
    }
    
    public void end() {
        this.isRunning = false;
    }
    
    public void pause() {
        this.isPaused = true;
    }
    
    @Override
    public void run() {
        this.isRunning = true;
        this.isPaused = false;
        
        while (isRunning) {
            
            //System.out.println("RUNNING");
            
            //Update timer
            if (!this.isPaused) {
                this.intTime++;
                
                //Update label
                this.setText(String.format("%03d",intTime));
                
            }
            
            
            //Wait 1 second
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
            
        }
        
        System.out.println("Timer ended");
    }
    
    
    
}