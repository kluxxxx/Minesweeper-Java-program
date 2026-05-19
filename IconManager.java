import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.*;

/**
 * Write a description of class IconManager here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class IconManager
{
    static final String FOLDER = "sprites";
    
    public static Image loadImage(String strFileName) {
        //Declare a variable to store the image
        Image img = null;
        
        try
        {
            //Load the image ("sprites//" directs the file reader to the sprites folder)
            img = ImageIO.read(new File(FOLDER + "//"+strFileName));
        }
        catch (java.io.FileNotFoundException fnfe)
        {
            fnfe.printStackTrace();
        }
        catch (java.io.IOException ioe) 
        {
            ioe.printStackTrace();
        }
        
        return img;
    }
    
    //Load icons
    public static Icon loadIcon(String strFileName) {
        return new ImageIcon(loadImage(strFileName));
    }
    
    public static Icon loadIcon(String strFileName, int intWidth, int intHeight) {
        return new ImageIcon(loadImage(strFileName).getScaledInstance(
            intWidth, 
            intHeight,
            Image.SCALE_SMOOTH
        ));
    }
    
    public static Icon loadNumber(byte bytNum, int intWidth, int intHeight) {
        bytNum = (byte) Math.max(1, Math.min(bytNum, 8));
        
        return loadIcon(bytNum + ".png", intWidth, intHeight);
        
        
    }
}