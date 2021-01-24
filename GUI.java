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
    private javax.swing.Timer timer1, timer2, timer3;

    public GUI(){
        prepareGUI();
        this.timer1 = new javax.swing.Timer(0, null);
        this.timer2 = new javax.swing.Timer(0, null);
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

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("OK");
        okButton.addActionListener(new ButtonClickListener()); 
        classPanel1.add(okButton);    

        // clock 2
 
        JButton submitButton2 = new JButton("Submit");
        submitButton2.setActionCommand("Submit");
        submitButton2.addActionListener(new ButtonClickListener()); 
        classPanel2.add(submitButton2);
  

        mainFrame.setVisible(true);  
    }

    private class ButtonClickListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();  
            
            if( command.equals( "OK" ))  {
                currentTime = this.timerInit();
                statusLabel.setText("Ok Button clicked at :" + currentTime);
            } else if( command.equals( "Submit" ) )  {
                statusLabel.setText("Submit Button clicked. \n Reading ol time : " + currentTime); 
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }  	
        }	
        
        private Date timerInit(){
        
            return Calendar.getInstance().getTime();
        }
	
    }

    
}

// https://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html