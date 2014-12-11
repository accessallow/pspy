
 
/**
 * @author Kushal Paudyal
 * JavaScreenCaptureUtil.java
 *
 * This utility captures the screenshot and saves
 * the captured image to disk.
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
 
import javax.imageio.ImageIO;
 
public class ScreenCapture {
 
    public static String capture() throws Exception {
 
        /**
         * This class (Robot.java) is used to generate native system input events for the
         * purposes of test automation, self-running demos, and other
         * applications where control of the mouse and keyboard is needed.
         * The primary purpose of Robot is to facilitate automated testing
         * of Java platform implementations.
         */
        Robot robot = new Robot();
         
        /**
         * Get the current screen dimensions.
         */
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
         
         
        /**
         * Delay the robot for 5 seconds (5000 ms) allowing you to switch to proper
         * screen/window whose screenshot is to be taken.
         *
         * You can change the delay time as required.
         */
       // robot.delay(5000);
         
        /**
         * Create a screen capture of the active window and then create a buffered image
         * to be saved to disk.
         */
        Image image = robot.createScreenCapture(new Rectangle(0, 0, width,
                height));
 
        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        g.drawImage(image, 0, 0, width, height, null);
 
        /**
         * Filename where to save the file to.
         * I am appending formatted timestamp to the filename.
         */
        String fileNameToSaveTo = "screenCapture_"
                + createTimeStampStr() + ".PNG";
         
        /**
         * Write the captured image to a file.
         * I am using PNG format. You can choose PNG, JPG, GIF.
         */
        writeImage(bi, fileNameToSaveTo, "PNG");
 
        System.out.println("Screen Captured Successfully and Saved to:\n"+fileNameToSaveTo);
        image.flush();
	return fileNameToSaveTo;
 
    }
 
    /**
     * This method writes a buffered image to a file
     *
     * @param img -- > BufferedImage
     * @param fileLocation --> e.g. "C:/testImage.jpg"
     * @param extension --> e.g. "jpg","gif","png"
     */
    public static void writeImage(BufferedImage img, String fileLocation,
            String extension) {
        try {
            BufferedImage bi = img;
            File outputfile = new File(fileLocation);
            ImageIO.write(bi, extension, outputfile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    /**
     *
     * @return String representation of timestamp
     * in the format of yyyyMMdd_hhmmss (e.g. 20100426_111612)
     * @throws Exception
     */
    public static String createTimeStampStr() throws Exception {
        Calendar mycalendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_hhmmss");
        String timeStamp = formatter.format(mycalendar.getTime());
 
        return timeStamp;
 
    }
 
       /*
     * SANJAAL CORPS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE
     * SUITABILITY OF THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT
     * LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
     * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SANJAAL CORPS SHALL NOT BE
     * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING,
     * MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
     *
     * THIS SOFTWARE IS NOT DESIGNED OR INTENDED FOR USE OR RESALE AS ON-LINE
     * CONTROL EQUIPMENT IN HAZARDOUS ENVIRONMENTS REQUIRING FAIL-SAFE
     * PERFORMANCE, SUCH AS IN THE OPERATION OF NUCLEAR FACILITIES, AIRCRAFT
     * NAVIGATION OR COMMUNICATION SYSTEMS, AIR TRAFFIC CONTROL, DIRECT LIFE
     * SUPPORT MACHINES, OR WEAPONS SYSTEMS, IN WHICH THE FAILURE OF THE
     * SOFTWARE COULD LEAD DIRECTLY TO DEATH, PERSONAL INJURY, OR SEVERE
     * PHYSICAL OR ENVIRONMENTAL DAMAGE ("HIGH RISK ACTIVITIES"). SANJAAL CORPS
     * SPECIFICALLY DISCLAIMS ANY EXPRESS OR IMPLIED WARRANTY OF FITNESS FOR
     * HIGH RISK ACTIVITIES.
     */
 
}
