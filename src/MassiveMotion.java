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

    //variables for canvas logistics
    public static int timerDelay;
    public static int windowX;
    public static int windowY;
    public static String listType;

    //variables for star object
    public static int starSize;
    public static int starPosX;
    public static int starPosY;
    public static int starVelocityX;
    public static int starVelocityY;

    //variables for comet object
    public static int bodySize;
    public static int bodyVelocity;
    public static double genX;
    public static double genY;
    //list used to keep track of CB's (Celestial Bodies) on canvas
    List<Celestial> list;

     public MassiveMotion(String propfile) {
        prop = new Properties();
        try{
            FileInputStream file = new FileInputStream(propfile);
            prop.load(file);

            //assign key to instance variables for CB:
            //temp variable to see if key is in property file
            String tdStr = prop.getProperty("timer_delay");
            String wXStr = prop.getProperty("window_size_x");
            String wYStr = prop.getProperty("window_size_y");
            String lStr = prop.getProperty("list");
            String sSizeStr = prop.getProperty("star_size");
            String sPosxStr = prop.getProperty("star_position_x");
            String sPosyStr = prop.getProperty("star_position_y");
            String sVelxStr = prop.getProperty("star_velocity_x");
            String sVelyStr = prop.getProperty("star_velocity_y");
            String bSizeStr = prop.getProperty("body_size");
            String bVelStr = prop.getProperty("body_velocity");
            String gXStr = prop.getProperty("gen_x");
            String gYStr = prop.getProperty("gen_y");

            //making sure keys are valid then assigning them to variables
            if(tdStr != null){
                timerDelay = Integer.parseInt(prop.getProperty("timer_delay").trim()); //variable for timer key
            }else{
                throw new IllegalStateException("timer_delay is missing in the property file!");
            }
            //checking window size
           if(wXStr != null && wYStr != null){
            windowX = Integer.parseInt(prop.getProperty("window_size_x").trim());
            windowY = Integer.parseInt(prop.getProperty("window_size_y").trim());
           }else{
            throw new IllegalStateException("Window X and Y are missing in the property file!");
           }
           //checking list type
           if(lStr != null ){
            listType = prop.getProperty("list");   
           }else{
            throw new IllegalStateException("List type is missing in the property file!");
           }
            //chacking Star Data:
           if( sSizeStr != null ){
            starSize = Integer.parseInt(prop.getProperty("star_size").trim());   
           }else{
            throw new IllegalStateException("Star Size is missing in the property file!");
           }

           if(sPosxStr != null && sPosyStr != null){
            starPosX = Integer.parseInt(prop.getProperty("star_position_x").trim());
            starPosY = Integer.parseInt(prop.getProperty("star_position_y").trim());   
           }else{
            throw new IllegalStateException("Star Positions X and Y are missing in the property file!");
           }

           if(sVelxStr != null && sVelyStr != null){
            starVelocityX = Integer.parseInt(prop.getProperty("star_velocity_x").trim());
            starVelocityY = Integer.parseInt(prop.getProperty("star_velocity_y").trim());   
           }else{
            throw new IllegalStateException("Star Velocities X and Y are missing in the property file!");
           }

           //Checking Comet Data
            if(bSizeStr != null){
                bodySize = Integer.parseInt(prop.getProperty("body_size").trim());  
           }else{
            throw new IllegalStateException("Body Size is missing in the property file!");
           }
           if(bVelStr != null){
            bodyVelocity = Integer.parseInt(prop.getProperty("body_velocity").trim());
           }else{
            throw new IllegalStateException("Body Velocity is missing in the property file!");
           } 

           if(gXStr != null && gYStr != null){
            genX = Double.parseDouble(prop.getProperty("gen_x").trim());
            genY = Double.parseDouble(prop.getProperty("gen_y").trim());
           }else{
            throw new IllegalStateException("genX and genY are missing in the property file!");
           }
           //setting timer based on timer_delay
           tm = new Timer(timerDelay, this); 
           //list to be used to keep track og CB's
           list = Celestial.makeList(listType);
           if(list == null){
            throw new IllegalStateException("Celestial list was not initialized. Cannot continue.");
            }
           Celestial star = new Celestial(starPosX,starPosY, starSize, starVelocityX, starVelocityY);
           list.add(star);
                        
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.
        //if list is empty dont paint anything:
        if(list == null){
            return;
        }
        //go through list and repaint them with their new locations
        for(int i = 0; i < list.size(); i++){
            Celestial curr = list.get(i);
            if(i == 0){
                g.setColor(Color.RED);// if star -> paint red
            }else{
                g.setColor(Color.BLUE); //if comet -> paint blue  
            }
            g.fillOval(curr.currX, curr.currY,curr.bodySize, curr.bodySize);
        }
        // Recommend you leave the next line as is
        tm.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //get probability of making a new CB:
        Random rand = new Random();
        double genXProb = rand.nextDouble();//random double for calculating probabilitie of generating on the x axis
        double genYProb = rand.nextDouble();//random double for calculating probabilitie of generating on the y axis

        if(genXProb < genX){
            //populate new CB on the X axis in randome location
            int axisX = rand.nextInt();
            int locX = 0;
            //deciding which end of the x axis to spawn CB
            if(axisX % 2 == 0){
                locX = windowX;
            }
            int locY = rand.nextInt(windowY);//random y coordinate in range of the canvas frame
            int velocityX = rand.nextInt(bodyVelocity *2 + 1) - bodyVelocity;
            int velocityY = rand.nextInt(bodyVelocity * 2 + 1) - bodyVelocity;
            Celestial c = new Celestial(locX, locY, bodySize, velocityX, velocityY);//creating new celestial body on X axis
            //adding new CB to the list
            list.add(c);
        }
        if(genYProb < genY){
            //populate new CS on the Y axis in randome location
            int locX = rand.nextInt(windowX) ;
            int locY = 0;
            int axisY = rand.nextInt();
            //deciding which end of the y axis to spawn CB
            if(axisY % 2 == 0){
                locY = windowY;
            }
            int velocityX = rand.nextInt(bodyVelocity * 2 + 1) - bodyVelocity;
            int velocityY = rand.nextInt(bodyVelocity * 2 + 1) - bodyVelocity;
            Celestial c = new Celestial(locX, locY, bodySize, velocityX, velocityY);
            //adding new CB to the list
            list.add(c);
        }

        //traversing through list and Moving Celestial bodies
        if(list != null){
            for(int i = list.size() -1; i >= 0; i--){
            Celestial curr = list.get(i);
            //moving celestial body
            curr.move();
            //if CB has moved outside of the canvas remove it from the list
            if(curr.currX > windowX || curr.currX < 0 || curr.currY > windowY || curr.currY < 0){
                list.remove(i);
            }
            
            }
        }
        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        MassiveMotion mm = new MassiveMotion(args[0]);
        //MassiveMotion mm = new MassiveMotion("MassiveMotion.txt");
       
        //setting up canvas
        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.windowX, mm.windowY); 
        jf.add(mm);
        jf.setVisible(true);
        //starting timer
        mm.tm.start();
      
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
    }
}
