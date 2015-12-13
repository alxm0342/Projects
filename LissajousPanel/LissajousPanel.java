import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

/**
 * Name: Alex Mendez
 * Group: 4
 * Project: 1
 * Date: 11/30/11
 * Class: CS245
 */
/////////////////////////////////////////////////////////////////////
class LissaPanel extends JPanel {
   private static final long serialVersionUID = 1L;
   int a;
   int b;
   int t;
   double phase;
   double freqX;
   double freqY;
   //-------------------------------------------------------------------
   LissaPanel (int width, int height, double xFreq, double yFreq, int time, double shift){
      //Set the Lissa Panel to be opaque
      setOpaque(true);
      //Add a border to the Lissa Panel
      setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));
      //Set the size of the panel per the constructor
      setPreferredSize(new Dimension(width*2, height*2));
      //Define variable for graph
      a = width;
      b = height;
      phase = shift;
      freqX = xFreq;
      freqY = yFreq;
      t = time;
   }
   //-------------------------------------------------------------------
   protected void paintComponent (Graphics g){
      //Call to the superclass implementation
      super.paintComponent(g);
      
      //Variables for the x and y values of each iteration
      double xPoint;
      double yPoint;
      
      //Graph the curve for each point based on time
      for (int i = 0; i < t; i++){
         xPoint = (a*Math.sin(freqX*i)) + (a+2);
         yPoint = (b*Math.sin(freqY*i + phase)) + (b+2);
         g.drawLine((int)xPoint, (int)yPoint, (int)xPoint, (int)yPoint);
      }
   }
   //-------------------------------------------------------------------
}
/////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////
public class LissajousPanel {
   
   LissaPanel lp;
   double x;
   double y;
   double phase;
   JTextField jTxtX;
   JTextField jTxtY;
   JTextField jTxtPhase;
   JFrame mainFrame; 
   
   //-------------------------------------------------------------------
   LissajousPanel(){
      //Instantiate initial componenets
      JButton btnRender = new JButton("Render Curve");
      JLabel labFreqX = new JLabel("X-Frequency:");
      jTxtX = new JTextField();
      jTxtX.setText("000.0");
      JLabel labFreqY = new JLabel("Y-Frequency:");
      jTxtY = new JTextField();
      jTxtY.setText("000.0");
      JLabel labPhase = new JLabel("Phase Shift:");
      jTxtPhase = new JTextField();
      jTxtPhase.setText("0.00");
      JPanel displayPanel = new JPanel();
      JPanel controlPanel = new JPanel();
   
      //Initial values for the default curve of 0,0,0
      x = Double.parseDouble(jTxtX.getText());
      y = Double.parseDouble(jTxtY.getText());
      phase = Double.parseDouble(jTxtPhase.getText());
      
      //Instantiate the application frame
      mainFrame = new JFrame("Project 1 - Lissajous Application");
      
      //Retrieve the screen dimensions
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //Calculate a centered X-position based on screensize
      int centeredX = (int) ((screenSize.getWidth())/2)-(600/2);
      //Calculate a centered Y-position based on screensize
      int centeredY = (int) ((screenSize.getHeight())/2)-(500/2);
      
      
      // Give the frame an initial size. 
      mainFrame.setSize(600, 500);
      
      //Set the frame to display at the center of the screen
      mainFrame.setLocation(centeredX, centeredY);
      
      //Terminate the program when the user closes the application. 
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //Make the main frame non-resizeable
      mainFrame.setResizable(false);
      
      //Set frame layout
      mainFrame.setLayout(new FlowLayout());
      
      //Add components to the control panel
      controlPanel.add(labFreqX);
      controlPanel.add(jTxtX);
      controlPanel.add(labFreqY);
      controlPanel.add(jTxtY);
      controlPanel.add(labPhase);
      controlPanel.add(jTxtPhase);
      controlPanel.add(btnRender);
      mainFrame.add(controlPanel);
      
      //Create a new Lissa Panel
      lp = new LissaPanel(295, 184, x, y, 150000, phase);
      displayPanel.setPreferredSize(new Dimension(600, 400));      
      displayPanel.add(lp);
      mainFrame.add(displayPanel);

      //Add action listener for the render button
      btnRender.addActionListener(new ActionListener(){
         public void actionPerformed(ActionEvent e)
         {
            boolean goodValues = true;
            try{x = Double.parseDouble(jTxtX.getText());}
            catch (NumberFormatException nE){goodValues = false;}
            try{y = Double.parseDouble(jTxtY.getText());}
            catch (NumberFormatException nE){goodValues = false;}
            try{phase = Double.parseDouble(jTxtPhase.getText());}
            catch (NumberFormatException nE){goodValues = false;}
            
            //If all input values parse properly, repaint the curve
            if (goodValues){
               lp.freqX = x;
               lp.freqY = y;
               lp.phase += phase;
               lp.repaint();
            }
            //Handle invalid values within the input fields
            else {
               JOptionPane.showMessageDialog(mainFrame,
                       "Invalid values, please enter only decimal values");
            }
         }
      });
                  
      //Make the frame visible
      mainFrame.setVisible(true);
   }
   //-------------------------------------------------------------------
   //-------------------------------------------------------------------
   public static void main(String args[]) { 
      // Create the frame on the event dispatching thread. 
      SwingUtilities.invokeLater(new Runnable() { 
         public void run() { 
            new LissajousPanel(); 
         } 
      });
    }
  //-------------------------------------------------------------------
}
/////////////////////////////////////////////////////////////////////
