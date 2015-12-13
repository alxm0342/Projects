import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Name: Alex Mendez
 * Group: 4
 * Project: 7
 * Date: 11/30/11
 * Class: CS245
 */

/////////////////////////////////////////////////////////////////////
class FontChecker implements ActionListener {
   //Define global label, frame and panel to be accessed by button actions
   JLabel mainLabel;
   JFrame mainFrame;
   JPanel displayPanel;
   @SuppressWarnings("rawtypes")
   JComboBox jFont;
   @SuppressWarnings("rawtypes")
   JComboBox jFontSize;
   
   //-------------------------------------------------------------------  
   @SuppressWarnings({ "unchecked", "rawtypes" })
FontChecker () {
      //Retrieve the screen dimensions
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //Calculate a centered X-position based on screensize
      int centeredX = (int) ((screenSize.getWidth())/2)-(640/2);
      //Calculate a centered Y-position based on screensize
      int centeredY = (int) ((screenSize.getHeight())/2)-(300/2);
      //String of usable fonts
      String fonts[] = {"Helvetica", "Arial", "Times New Roman", "Impact",
                        "Comic Sans MS", "Courier"} ;
      
      //String of font sizes
      String sizes[] = {"6", "8", "10", "12", "14", "16", "20", "24", "28",
                        "32", "48", "72"};
      
      // Create a new JFrame container. 
      mainFrame = new JFrame("Project 7 - Text and Background Selector"); 
 
      // Give the frame an initial size. 
      mainFrame.setSize(640, 300);
      
      //Define the layout of the main panel
      mainFrame.setLayout(new GridLayout(2,1,0,0));
 
      //Terminate the program when the user closes the application. 
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      //Make the main frame non-resizeable
      mainFrame.setResizable(false);
        
      //Set the frame to display at the center of the screen
      mainFrame.setLocation(centeredX, centeredY);
      
      //Construct panels
      displayPanel = new JPanel();
      JPanel controlPanel = new JPanel();
      JPanel fontFacePanel = new JPanel();
      
      //Construct Labels for components
      JLabel labBackground = new JLabel("Choose a Background:");
      JLabel labFontColor = new JLabel("Choose a Font Color:");
      JLabel labFont = new JLabel("Choose a Font:");
      JLabel labSize = new JLabel("Choose a Font Size:");
      
      //Set the text of a main label to contain english ASCII characters
      mainLabel = new JLabel("<html><p align = \"center\">The quick brown fox"
                                    + " jumps over the lazy dog.<br>"
                                    + "THE QUICK BROWN FOX JUMPS OVER THE"
                                    + " LAZY DOG.<br>0123456789</p></html>",
                                    SwingConstants.CENTER);
      mainLabel.setFont(new Font("Helvetica", mainLabel.getFont().getStyle(),
                                 12));
      
      displayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      displayPanel.add(mainLabel);
      
      //Construct Background Radio Buttons
      JRadioButton jBackgroundRed = new JRadioButton("Red BG");
      JRadioButton jBackgroundGreen = new JRadioButton("Green BG");
      JRadioButton jBackgroundBlue = new JRadioButton("Blue BG");
      JRadioButton jBackgroundBlack = new JRadioButton("Black BG");
      JRadioButton jBackgroundWhite = new JRadioButton("White BG");
      JRadioButton jBackgroundYellow = new JRadioButton("Yellow BG");
      jBackgroundRed.addActionListener(this);
      jBackgroundGreen.addActionListener(this);
      jBackgroundBlue.addActionListener(this);
      jBackgroundBlack.addActionListener(this);
      jBackgroundWhite.addActionListener(this);
      jBackgroundYellow.addActionListener(this);
      
      //Add Background buttons to a button group
      ButtonGroup bgBackgrounds = new ButtonGroup();
      bgBackgrounds.add(jBackgroundRed);
      bgBackgrounds.add(jBackgroundGreen);
      bgBackgrounds.add(jBackgroundBlue);
      bgBackgrounds.add(jBackgroundBlack);
      bgBackgrounds.add(jBackgroundWhite);
      bgBackgrounds.add(jBackgroundYellow);
      
      //Add Background buttons to a Flow-layout JPanel
      JPanel bgPanel = new JPanel();
      bgPanel.add(labBackground);
      bgPanel.add(jBackgroundRed);
      bgPanel.add(jBackgroundGreen);
      bgPanel.add(jBackgroundBlue);
      bgPanel.add(jBackgroundBlack);
      bgPanel.add(jBackgroundWhite);
      bgPanel.add(jBackgroundYellow);
      
      //Construct Font Color Buttons
      JRadioButton jFontRed = new JRadioButton("Red");
      JRadioButton jFontGreen = new JRadioButton("Green");
      JRadioButton jFontBlue = new JRadioButton("Blue");
      JRadioButton jFontBlack = new JRadioButton("Black");
      JRadioButton jFontWhite = new JRadioButton("White");
      JRadioButton jFontYellow = new JRadioButton("Yellow");
      jFontRed.addActionListener(this);
      jFontGreen.addActionListener(this);
      jFontBlue.addActionListener(this);
      jFontBlack.addActionListener(this);
      jFontWhite.addActionListener(this);
      jFontYellow.addActionListener(this);
      
      //Add Font Color Buttons to a Button Group
      ButtonGroup bgFontColors = new ButtonGroup();
      bgFontColors.add(jFontRed);
      bgFontColors.add(jFontGreen);
      bgFontColors.add(jFontBlue);
      bgFontColors.add(jFontBlack);
      bgFontColors.add(jFontWhite);
      bgFontColors.add(jFontYellow);
      
      //Add Font Color Buttons to a Flow-layout JPanel
      JPanel fontColorPanel = new JPanel();
      fontColorPanel.add(labFontColor);
      fontColorPanel.add(jFontRed);
      fontColorPanel.add(jFontGreen);
      fontColorPanel.add(jFontBlue);
      fontColorPanel.add(jFontBlack);
      fontColorPanel.add(jFontWhite);
      fontColorPanel.add(jFontYellow);
      
      //Contruct Font Size Dropdown list
      jFontSize = new JComboBox(sizes);
      jFontSize.setSelectedIndex(3);
      jFontSize.addActionListener(this);
      
      //Contruct Font Size Dropdown list
      jFont = new JComboBox(fonts);
      jFont.setSelectedIndex(0);
      jFont.addActionListener(this);
      
      //Add componenets to the font face panels
      fontFacePanel.add(labFont);
      fontFacePanel.add(jFont);
      fontFacePanel.add(labSize);
      fontFacePanel.add(jFontSize);
      
      //Add panels to the control panel
      controlPanel.add(bgPanel);
      controlPanel.add(fontColorPanel);
      controlPanel.add(fontFacePanel);
      
      //Add components to the main frame
      mainFrame.add(displayPanel);
      mainFrame.add(controlPanel);

      // Display the frame. 
      mainFrame.setVisible(true); 
  }
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  public void actionPerformed(ActionEvent ae){
     
     //If-statements to handle changing backgrounds
     if (ae.getActionCommand().equals("Red BG")){
        displayPanel.setBackground(Color.RED);
     }
     else if (ae.getActionCommand().equals("Green BG")){
        displayPanel.setBackground(Color.GREEN);
     }
     else if (ae.getActionCommand().equals("Blue BG")){
        displayPanel.setBackground(Color.BLUE);
     }
     else if (ae.getActionCommand().equals("Black BG")){
        displayPanel.setBackground(Color.BLACK);
     }
     else if (ae.getActionCommand().equals("White BG")){
        displayPanel.setBackground(Color.WHITE);
     }
     else if (ae.getActionCommand().equals("Yellow BG")){
        displayPanel.setBackground(Color.YELLOW);
     }
     
     //If-statements to handle changing font colors
     else if (ae.getActionCommand().equals("Red")){
        mainLabel.setForeground(Color.RED);
     }
     else if (ae.getActionCommand().equals("Green")){
        mainLabel.setForeground(Color.GREEN);
     }
     else if (ae.getActionCommand().equals("Blue")){
        mainLabel.setForeground(Color.BLUE);
     }
     else if (ae.getActionCommand().equals("Black")){
        mainLabel.setForeground(Color.BLACK);
     }
     else if (ae.getActionCommand().equals("White")){
        mainLabel.setForeground(Color.WHITE);
     }
     else if (ae.getActionCommand().equals("Yellow")){
        mainLabel.setForeground(Color.YELLOW);
     }
     
     //Handle changing the size of the font
     else if (ae.getActionCommand().equals("comboBoxChanged")){
        //Determine which size the user selected
        int size = jFontSize.getSelectedIndex();
        int font = jFont.getSelectedIndex();
        String fontName = mainLabel.getFont().getFontName();
        //Handle changing the font size
        switch (size){
           case 0: size = 6; break;
           case 1: size = 8; break;
           case 2: size = 10; break;
           case 3: size = 12; break;
           case 4: size = 14; break;
           case 5: size = 16; break;
           case 6: size = 20; break;
           case 7: size = 24; break;
           case 8: size = 28; break;
           case 9: size = 32; break;
           case 10: size = 48; break;
           case 11: size = 72; break;
        }
        
        //Handle changing the font face
        switch(font){
           case 0: fontName = "Helvetica"; break;
           case 1: fontName = "Arial"; break;
           case 2: fontName = "Times New Roman"; break;
           case 3: fontName = "Impact"; break;
           case 4: fontName = "Comic Sans MS"; break;
           case 5: fontName = "Courier New"; break;
        }
        mainLabel.setFont(new Font(fontName, mainLabel.getFont().getStyle(),
                                   size));
     }
  }
  //-------------------------------------------------------------------  
  //-------------------------------------------------------------------
  public static void main(String args[]) { 
         // Create the frame on the event dispatching thread. 
        SwingUtilities.invokeLater(new Runnable() { 
            public void run() { 
                new FontChecker(); 
            } 
        }); 
    }
  //-------------------------------------------------------------------
}
/////////////////////////////////////////////////////////////////////
