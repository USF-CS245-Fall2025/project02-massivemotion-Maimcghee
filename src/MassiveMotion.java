import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;


public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    private Properties prop;

    // TODO: Consider removing the next two lines (coordinates for two balls)
    protected int x1, y1;
    protected int x2, y2;


     public MassiveMotion(String propfile) {
        // TODO: insert your code to read from configuration file here.
        prop = new Properties();
        try{
            FileInputStream file = new FileInputStream(propfile);
            prop.load(file);
            //assign key to instance variables for CB:

            //establishing data(Canvas and Timer):
            int timerDelay = Integer.parseInt(prop.getProperty("timer_delay"));
            int windowX = Integer.parseInt(prop.getProperty("window_size_x"));
            int widnowY = Integer.parseInt(prop.getProperty("window_size_y"));
            String listType = prop.getProperty("list");

            //Star Data:
            int starSize = Integer.parseInt(prop.getProperty("star_size"));
            int starPosX = Integer.parseInt(prop.getProperty("star_position_x"));
            int starPosY = Integer.parseInt(prop.getProperty("star_position_y"));   
            double starVelocityX = Double.parseDouble(prop.getProperty("star_velocity_x"));
            double starVelocityY = Double.parseDouble(prop.getProperty("star_velocity_y"));

            //Commet Data:
            int bodySize = Integer.parseInt(prop.getProperty("body_size"));
            double bodyVelocity = Double.parseDouble(prop.getProperty("body_veloctiy"));
            double genX =  Double.parseDouble(prop.getProperty("get_x"));
            double genY =  Double.parseDouble(prop.getProperty("get_y"));
                        
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        tm = new Timer(timer_delay, this); // TODO: Replace the first argument with delay with value from config file.

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
        x1 = 100; y1 = 50;
        x2 = 200; y2 = 400;
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
        x1 += 10;
        x2 -= 15;
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
        // MassiveMotion mm = new MassiveMotion(args[0]);
        MassiveMotion mm = new MassiveMotion();

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(640, 480); // TODO: Replace with the size from configuration!
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
