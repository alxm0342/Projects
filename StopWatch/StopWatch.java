/*
 * Name:    Mendez, Alexis
 * Project: 1
 * Due:     10/14/2011
 * Course:  CS-245-f11
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StopWatch implements ActionListener {
    
    //Initialize global Label and Variable for start time
    JLabel jlab;
    long start;
    
    StopWatch(){
        
        //Create new JFrame container
        JFrame jfrm = new JFrame("A Simple Stopwatch");
        
        //Create a label for Copyright information
        JLabel jlabCopy = new JLabel("(c) 2011 Alex Mendez");
        
        //Specify FlowLayout for the layout manager.
        jfrm.setLayout(new FlowLayout());
        
        //Initialize the frame size
        jfrm.setSize(230, 90);
        
        //Retrieve the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Calculate a centered X-position based on screensize
        int centeredX = (int) ((screenSize.getWidth())/2)-(230/2);
        //Calculate a centered Y-position based on screensize
        int centeredY = (int) ((screenSize.getHeight())/2)-(90/2);
        //Set the frame to display at the center of the screen
        jfrm.setLocation(centeredX, centeredY);
        
        //Exit application when user closes the frame
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Make a start and stop button
        JButton jbtnStart = new JButton("Start");
        JButton jbtnStop = new JButton("Stop");
        
        //Add action listeners to buttons
        jbtnStart.addActionListener(this);
        jbtnStop.addActionListener(this);
        
        //Add buttons to the frame
        jfrm.add(jbtnStart);
        jfrm.add(jbtnStop);
        
        //Initialize label with instructions
        jlab = new JLabel("Press Start to begin timing.");
        
        //Add initialized label to the frame
        jfrm.add(jlab);
        jfrm.add(jlabCopy);
        
        //Make the frame visible and non-resizable
        jfrm.setVisible(true);
        jfrm.setResizable(false);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        //Get an instance of the current system time
        Calendar cal = Calendar.getInstance();
        
        if (ae.getActionCommand().equals("Start"))
        {
            //Store start time and inform user timing has started
            start = cal.getTimeInMillis();
            jlab.setText("Stopwatch is running");
        }
        else
        {
            //Compute the elapsed time and display to the user
            jlab.setText("Elapsed time is " + ((double) (cal.getTimeInMillis() - start)/1000));
        }
    }
    
    public static void main(String[] args) {
        
        //Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new StopWatch();
            }
        });
    }
}
