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
    public static int starVelocityX;
    public static int starVelocityY;

    public static int bodySize;
    public static int bodyVelocity;
    public static double genX;
    public static double genY;

    List<Celestial> list = new ArrayList<>();


     public MassiveMotion(String propfile) {
        // TODO: insert your code to read from configuration file here.
        prop = new Properties();
        try{
            FileInputStream file = new FileInputStream(propfile);
            prop.load(file);
            //assign key to instance variables for CB:

            //establishing data(Canvas and Timer):
            timerDelay = Integer.parseInt(prop.getProperty("timer_delay").trim());
            windowX = Integer.parseInt(prop.getProperty("window_size_x").trim());
            windowY = Integer.parseInt(prop.getProperty("window_size_y").trim());
            listType = prop.getProperty("list");

            //Star Data:
            starSize = Integer.parseInt(prop.getProperty("star_size").trim());
            starPosX = Integer.parseInt(prop.getProperty("star_position_x").trim());
            starPosY = Integer.parseInt(prop.getProperty("star_position_y").trim());   
            starVelocityX = Integer.parseInt(prop.getProperty("star_velocity_x").trim());
            starVelocityY = Integer.parseInt(prop.getProperty("star_velocity_y").trim());

            //Commet Data:
            bodySize = Integer.parseInt(prop.getProperty("body_size").trim());
            bodyVelocity = Integer.parseInt(prop.getProperty("body_velocity").trim());
            genX = Double.parseDouble(prop.getProperty("gen_x").trim());
            genY = Double.parseDouble(prop.getProperty("gen_y").trim());

            //setting timer based on timer_delay
            tm = new Timer(timerDelay, this); // TODO: Replace the first argument with delay with value from config file.
            //list to be used to keep track og CB's
            list = Celestial.makeList(listType);
            if(list == null){
                throw new IllegalStateException("Celestial list was not initialized. Cannot continue.");
            }
            Celestial star = new Celestial(starPosX,starPosY, starSize, starVelocityX, starVelocityY);
            list.add(star);
            //debugging
            System.out.println("Star created at (" + starPosX + "," + starPosY + ") size=" + starSize + " vel=(" + starVelocityX + "," + starVelocityY + ")");
            repaint();
                        
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
      
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        // TODO: Paint each ball. Here's how to paint two balls, one after the other:
        if(list == null){
            return;
        }
       
        for(int i = 0; i < list.size(); i++){
            Celestial curr = list.get(i);
            if(i == 0){
                g.setColor(Color.RED);
            }else{
                g.setColor(Color.BLUE);   
            }
            g.fillOval(curr.currX, curr.currY,curr.bodySize, curr.bodySize);
        }
        // Recommend you leave the next line as is
        //tm.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.

        //get probability of making a new CB:
        Random rand = new Random();
        double genXProb = rand.nextDouble();
        double genYProb = rand.nextDouble();
        if(genXProb < genX){
            //populate new CB on the X axis in randome location
            int locX = rand.nextInt(windowX);
            int locY = rand.nextInt(windowY);
            int velocityX = rand.nextInt(bodyVelocity *2 + 1) - bodyVelocity;
            int velocityY = rand.nextInt(bodyVelocity * 2 + 1) - bodyVelocity;
            Celestial c = new Celestial(locX, locY, bodySize, velocityX, velocityY);
            //adding new CB to the list
            list.add(c);
        }
        if(genYProb < genY){
            //populate new CS on the Y axis in randome location
            //populate new CB on the X axis in randome location
            int locX = rand.nextInt(windowX) ;
            int locY = rand.nextInt(windowY);
            int velocityX = rand.nextInt(bodyVelocity * 2 + 1) - bodyVelocity;
            int velocityY = rand.nextInt(bodyVelocity * 2 + 1) - bodyVelocity;
            Celestial c = new Celestial(locX, locY, bodySize, velocityX, velocityY);
            //adding new CB to the list
            list.add(c);
        }

        //traversing through list and Moving Celestial bodies
        if(list != null){
            for(int i = 0; i < list.size(); i++){
            Celestial curr = list.get(i);
            //moving celestial body
            curr.move();
        }

        }
        
        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        //MassiveMotion mm = new MassiveMotion(args[0]);
        MassiveMotion mm = new MassiveMotion("src/MassiveMotion.txt");
       
        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mm.setSize(windowX, windowY); // TODO: Replace with the size from configuration!
        mm.setBackground(Color.WHITE);
        jf.add(mm);
        jf.pack();
        jf.setVisible(true);
        mm.tm.start();
    }
}
