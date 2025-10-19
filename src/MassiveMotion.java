import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;
import java.util.Random;


public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    private Properties prop;
    public static int timerDelay;
    public static int windowX;
    public static int windowY;
    public static String listType;

    public static int starSize;
    public static int starPosX;
    public static int starPosY;
    public static double starVelocityX;
    public static double starVelocityY;

    public static int bodySize;
    public static int bodyVelocity;
    public static double genX;
    public static double genY;

    List<Celestial> list;


     public MassiveMotion(String propfile) {
        // TODO: insert your code to read from configuration file here.
        prop = new Properties();
        try{
            FileInputStream file = new FileInputStream(propfile);
            prop.load(file);
            //assign key to instance variables for CB:

            //establishing data(Canvas and Timer):
            timerDelay = Integer.parseInt(prop.getProperty("timer_delay"));
            windowX = Integer.parseInt(prop.getProperty("window_size_x"));
            windowY = Integer.parseInt(prop.getProperty("window_size_y"));
            listType = prop.getProperty("list");

            //Star Data:
            starSize = Integer.parseInt(prop.getProperty("star_size"));
            starPosX = Integer.parseInt(prop.getProperty("star_position_x"));
            starPosY = Integer.parseInt(prop.getProperty("star_position_y"));   
            starVelocityX = Double.parseDouble(prop.getProperty("star_velocity_x"));
            starVelocityY = Double.parseDouble(prop.getProperty("star_velocity_y"));

            //Commet Data:
            bodySize = Integer.parseInt(prop.getProperty("body_size"));
            bodyVelocity = Integer.parseInt(prop.getProperty("body_veloctiy"));
            genX = Double.parseDouble(prop.getProperty("get_x"));
            genY = Double.parseDouble(prop.getProperty("get_y"));

            //setting timer based on timer_delay
            tm = new Timer(timerDelay, this); // TODO: Replace the first argument with delay with value from config file.
            //list to be used to keep track og CB's
            list = Celestial.makeList(listType);
                        
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
      
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        // TODO: Paint each ball. Here's how to paint two balls, one after the other:
        g.setColor(Color.BLUE);
        g.fillOval(x1, y1, 20, 20);

        g.setColor(Color.RED);
        g.fillOval(x2, y2, 20, 20);

        // Recommend you leave the next line as is
        tm.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.

        //get probability of making a new CB:
        Random rand = new Random();
        double genXProb = rand.nextDouble(0-genX) + genX;
        double genYProb = rand.nextDouble(0-genY) + genY;
        if(genXProb < genX){
            //populate new CB on the X axis in randome location
            int locX = rand.nextInt(0 - windowX) + windowX;
            int locY = rand.nextInt(0 - windowX) + windowX;
            int velocityX = rand.nextInt(-bodyVelocity - bodyVelocity) + bodyVelocity;
            int velocityY = rand.nextInt(-bodyVelocity - bodyVelocity) + bodyVelocity;
            Celestial c = new Celestial(locX, locY, bodySize, velocityX, velocityY);
            //adding new CB to the list
            list.add(c);
        }
        if(genYProb < genY){
            //populate new CS on the Y axis in randome location
             //populate new CB on the X axis in randome location
            int locX = rand.nextInt(0 - windowY) + windowY;
            int locY = rand.nextInt(0 - windowY) + windowY;
            int velocityX = rand.nextInt(-bodyVelocity - bodyVelocity) + bodyVelocity;
            int velocityY = rand.nextInt(-bodyVelocity - bodyVelocity) + bodyVelocity;
            Celestial c = new Celestial(locX, locY, bodySize, velocityX, velocityY);
            //adding new CB to the list
            list.add(c);
        }

        if(list.size() != 0){
            Celestial curr = list.get(0);
            while(curr.next != null){
                //travers through list pf CB's

            }

        }
        
        // These two "if" statements keep the balls on the screen in case they go off one side.
        if (x1 > 640)
            x1 = 0;
        if (x2 < 0)
            x2 = 640;

        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
         MassiveMotion mm = new MassiveMotion(args[0]);
        //MassiveMotion mm = new MassiveMotion();

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(windowX, windowY); // TODO: Replace with the size from configuration!
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
