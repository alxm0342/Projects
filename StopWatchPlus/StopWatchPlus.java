/*
 * Name:    Mendez, Alexis
 * Project: 2
 * Due:     10/24/2011
 * Course:  CS-245-f11
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StopWatchPlus implements ActionListener {
    
    //Initialize global Label and Variable for start time
    JLabel jlabTime;
    JLabel jlabLog;
    JButton jbtnTime;
    JButton jbtnStart;
    JButton jbtnReset;
    long start;
    String[] laptimes = new String[3];
    int count = 0;
    
    StopWatchPlus(){
        
        //Create new JFrame container
        JFrame jfrm = new JFrame("Stopwatch Plus");
        
        //Specify FlowLayout for the layout manager.
        jfrm.setLayout(new BorderLayout());
        
        //Initialize the frame size
        jfrm.setSize(220, 140);
        
        //Retrieve the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Calculate a centered X-position based on screensize
        int centeredX = (int) ((screenSize.getWidth())/2)-(220/2);
        //Calculate a centered Y-position based on screensize
        int centeredY = (int) ((screenSize.getHeight())/2)-(140/2);
        //Set the frame to display at the center of the screen
        jfrm.setLocation(centeredX, centeredY);
        
        //Exit application when user closes the frame
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Make a time, start and reset button
        jbtnTime = new JButton("Time");
        jbtnStart = new JButton("Start");
        jbtnReset = new JButton("Reset");
        jbtnTime.setEnabled(false);
        jbtnReset.setEnabled(false);
        
        
        //Add action listeners to buttons
        jbtnTime.addActionListener(this);
        jbtnStart.addActionListener(this);
        jbtnReset.addActionListener(this);
        
        //Initialize labels with text
        jlabTime = new JLabel("0 secs", SwingConstants.CENTER);
        jlabTime.setPreferredSize(new Dimension(230, 20));
        jlabLog = new JLabel("lap logs");
        jlabLog.setPreferredSize(new Dimension(230, 50));
        jlabLog.setVerticalAlignment(SwingConstants.TOP);
        
        //Add all controls to the frame
        jfrm.add(jlabTime, BorderLayout.NORTH);
        jfrm.add(jbtnTime, BorderLayout.WEST);
        jfrm.add(jbtnStart, BorderLayout.CENTER);
        jfrm.add(jbtnReset, BorderLayout.EAST);
        jfrm.add(jlabLog, BorderLayout.SOUTH);
        
        //Make the frame visible and non-resizable
        jfrm.setVisible(true);
        jfrm.setResizable(false);
    }
    
    public void actionPerformed(ActionEvent ae){
        
        //Get an instance of the current system time
        Calendar cal = Calendar.getInstance();
        
        //Actions performed when the "Start" button is pushed
        if (ae.getActionCommand().equals("Start"))
        {
            //Store start time and inform user timing has started
            start = cal.getTimeInMillis();
            jlabTime.setText("Stopwatch is running");
            jbtnStart.setText("Stop");
            jbtnTime.setEnabled(true);
            jbtnReset.setText("Lap");
            jbtnReset.setEnabled(true);
            
            //Initialize all laptimes to be empty strings
            laptimes[0] = "";
            laptimes[1] = "";
            laptimes[2] = "";
        }
        
        //Actions performed when the "Stop" button is pushed
        else if (ae.getActionCommand().equals("Stop"))
        {
            //Compute the elapsed time and display to the user
            jlabTime.setText(((double) (ae.getWhen() - start)/1000) + " secs");
            //Reset initial settings of "Start" and "Time" buttons
            jbtnStart.setText("Start");
            jbtnTime.setEnabled(false);
            //Allow user to reset program with "Reset" button
            jbtnReset.setText("Reset");
        }
        
        //Actions performed when the "Time" button is pushed
        else if (ae.getActionCommand().equals("Time"))
        {
            //Display the current elapsed time
            jlabTime.setText(((double) (ae.getWhen() - start)/1000) + " secs");
        }
        
        //Actions performed when the "Reset" button is pushed
        else if (ae.getActionCommand().equals("Reset"))
        {
            //Reset all values to initial settings
            jlabTime.setText("0 secs");
            jlabLog.setText("lap logs");
            jbtnReset.setText("Lap");
            jbtnReset.setEnabled(false);
            jbtnTime.setEnabled(false);
            count = 0;
        }
        
        //Actions performed when the "Lap" button is pushed
        else if (ae.getActionCommand().equals("Lap"))
        {
            count ++;
            jlabLog.setText("<html>");
            for (int i=2; i>=0; i--)
            {
                if (i==0){
                    laptimes[i] = ("lap " + (count) + ": " + ((ae.getWhen() - start)/1000) + " secs.");
                }
                else {
                    laptimes[i] = laptimes[i-1];
                }
            }
            jlabLog.setText("<html>" + laptimes[0] + "<br>" + laptimes[1] + "<br>" + laptimes[2] + "</html>");
        }
    }
    
    public static void main(String[] args) {
        //Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new StopWatchPlus();
            }
        });
    }
}