import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

import java.util.*; // used for getting clock info

public class GUI {
    private JFrame mainFrame;
    private JLabel statusLabel;
    private JLabel timeElapsed, timeGoal, dayOfWeek;
    private JPanel classPanel1, classPanel2;
    private Date currentTime; // stores the init time for a given running timer
    private javax.swing.Timer timer1, timer2;

    public GUI(){
        prepareGUI();

        timer1 = new javax.swing.Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentTime = Calendar.getInstance().getTime();
                statusLabel.setText(currentTime.toString());
            }
        });

        this.timer2 = new javax.swing.Timer(1000, null);
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

        statusLabel = new JLabel("A label",JLabel.CENTER);        
        statusLabel.setSize(350,100);
        
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

        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);  
    }


    private void clockUnit(){
        // clock 1 

        JButton startButton = new JButton("Start");
        startButton.setActionCommand("Start");
        startButton.addActionListener(new ButtonClickListener()); 
        classPanel1.add(startButton);    

        // clock 2
 
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
            
                timer1.start();
            } else if( command.equals( "Stop" ) )  {

                timer1.stop();
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }  	
        }	
	
    }

    
}

// https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html