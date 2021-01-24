import java.awt.*;
import java.awt.event.*;
import javax.swing.*; //https://docs.oracle.com/javase/8/docs/api/javax/swing/Timer.html
import java.util.*; // used for getting clock info
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GUI {
    private JFrame mainFrame;
    private JLabel timeElapsed, timeGoal, dayOfWeek;
    private JPanel classPanel1, classPanel2;
    private Date currentTime, initTime;
    private javax.swing.Timer timer1;
    // private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
    private SimpleDateFormat timeFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyy", Locale.ENGLISH);

    public GUI() {
        prepareGUI();
        initTime = null;
        timer1 = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                timeElapsed.setText(timeDiffer());
            }
        });
        timer1.setInitialDelay(0);

        // this.timer2 = new javax.swing.Timer(1000, null);
    }

    private String timeDiffer(){
        Date currentParsedDate;
        Date startParsedDate;
        String currentTimeString = Calendar.getInstance().getTime().toString();
        String startTimeString = initTime.toString();
        try{
            currentParsedDate = timeFormat.parse(currentTimeString);
            startParsedDate = timeFormat.parse(startTimeString);
        }catch(ParseException e){
            System.out.println("Error in timeDiffer() parsing: " + e);
            currentParsedDate = Calendar.getInstance().getTime();
            startParsedDate = Calendar.getInstance().getTime();
        }

        long differenceInMillis = currentParsedDate.getTime() - startParsedDate.getTime();

        long differenceInSeconds = (differenceInMillis/1000) % 60;
        long differenceInMinutes = (differenceInMillis/(1000 * 60)) % 60;
        long differenceInHours = (differenceInMillis/(1000 * 60 * 60)) % 24;

        String timeElapsed = differenceInHours + " : " + differenceInMinutes + " : " + differenceInSeconds;
        return timeElapsed;
        
    }
    public static void main(String[] args){
        // Initialize the gui class
        GUI gui = new GUI();  
        // call 
        gui.clockUnit();   
     
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Remote Learning Time Manager");
        mainFrame.setSize(700, 250);
        mainFrame.setLayout(new GridLayout(5, 2, 2, 2));

        timeElapsed = new JLabel("A label",JLabel.CENTER);        
        timeElapsed.setSize(350,100);
        
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
            }        
        });    

        // Make the control Panel here, but its used in the clock unit function
        classPanel1 = new JPanel();
        classPanel1.setLayout(new FlowLayout());
        classPanel2 = new JPanel();
        classPanel2.setLayout(new FlowLayout());


        mainFrame.add(classPanel1);
        mainFrame.add(classPanel2);

        mainFrame.add(timeElapsed);
        mainFrame.setVisible(true);  
    }


    private void clockUnit(){
        // clock 1 
        // -----start
        JButton startButton = new JButton("Start");
        startButton.setActionCommand("Start");
        startButton.addActionListener(new ButtonClickListener()); 
        classPanel1.add(startButton);    
        // -----stop
        JButton stopButton = new JButton("Stop");
        stopButton.setActionCommand("Stop");
        stopButton.addActionListener(new ButtonClickListener()); 
        classPanel2.add(stopButton);
  

        mainFrame.setVisible(true);  
    }

    private class ButtonClickListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();  
            
            if( command.equals( "Start" ))  {
                initTime = Calendar.getInstance().getTime();
                timer1.start();
            } else if( command.equals( "Stop" ) )  {

                timer1.stop();
            } else {
                timeElapsed.setText("Cancel Button clicked.");
            }  	
        }	
	
    }

    
}

// https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html