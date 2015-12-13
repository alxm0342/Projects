import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;

/** Alexis Mendez
 *  CS240 - Lab 1 (Name Generator)
 *  1/15/2012
 */

//Data Structure of students
class student {
   public String lastName;
   public String firstName;
   public int occured;
   
   //Constructor
   student (String last, String first){
      lastName = last;
      firstName = first;
      occured = 0;
   }
}


//Custome file filter only approves "input.txt"
class JavaFileFilter extends FileFilter {
   
    //Returns true only if input file is valid
    public boolean accept(File file) {
         if (file.getName().equals("name.txt")){
            return true;
         }
         if (file.isDirectory()) {
            return true;
         }
         //Return false and filter out file extensions not stated above
         else {
            return false;
         }
    }
    
    //Description of what the file filter accepts
   public String getDescription(){
        return "name.txt";
    }
}

public class nameselector implements ActionListener {
   
   //Global string array to hold the names of the students
   static student [] names = new student[40];
   static int size;
   static String output = "";
   JTextArea jTxtNames;
   int count = 0;
   int nameCount = 0;
   
   //Swing Constructor
   nameselector (File file){
   
      //Global components
      JFrame mainFrame;
      JPanel controlPanel;
      JScrollPane displayPanel;
      
      
      inputNames(file.getAbsolutePath());
      //Retrieve the screen dimensions
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      //Calculate a centered X-position based on screensize
      int centeredX = (int) ((screenSize.getWidth())/2)-(420/2);
      //Calculate a centered Y-position based on screensize
      int centeredY = (int) ((screenSize.getHeight())/2)-(350/2);
      // Create a new JFrame container. 
      mainFrame = new JFrame("Lab 1 - Name Generator");
      // Give the frame an initial size. 
      mainFrame.setSize(420, 350);
      //Define the layout of the main panel
      mainFrame.setLayout(new FlowLayout());
      //Terminate the program when the user closes the application. 
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //Make the main frame non-resizeable
      mainFrame.setResizable(false);
      //Set the frame to display at the center of the screen
      mainFrame.setLocation(centeredX, centeredY);
      
      //Construct output label
      output = ("Number of students in the class: " + size + 
              "\n\n" + randomName() + "\n\n");
      jTxtNames = new JTextArea(output);
      //Disable user from manually editing output
      jTxtNames.setEditable(false);
      
      //Construct panels
      controlPanel = new JPanel();
      displayPanel = new JScrollPane(jTxtNames);
      controlPanel.setPreferredSize(new Dimension(380, 40));
      displayPanel.setPreferredSize(new Dimension(380, 260));
      //displayPanel.setBounds(0, 0, 320, 480);
      
      //Construct control buttons
      JButton btnName = new JButton("Next");
      JButton btnExit = new JButton("Exit");
      JButton btnList = new JButton("List");
      JButton btnHelp = new JButton("Help");
      btnName.addActionListener(this);
      btnExit.addActionListener(this);
      btnList.addActionListener(this);
      btnHelp.addActionListener(this);
      
      //Add components to panels, add panels to frames
      controlPanel.add(btnName);
      controlPanel.add(btnList);
      controlPanel.add(btnHelp);
      controlPanel.add(btnExit);
      mainFrame.add(controlPanel);
      mainFrame.add(displayPanel);
      
      // Display the frame. 
      mainFrame.setVisible(true); 
   }
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
   public void actionPerformed(ActionEvent ae){
     //If-statements to handle different user-commands
     //Exit Button
     if (ae.getActionCommand().equals("Exit")){
         int repeats = 0;
         for (int i=0; i < size; i++){
            if (names[i].occured > 1){
               repeats ++;
            }
         }
         JOptionPane.showMessageDialog(null, "<html>The system has generated " + nameCount + " name(s) with "
                     + repeats + " repetitions.</html>", "Exit", JOptionPane.INFORMATION_MESSAGE);
         System.exit(0);
     }
     //Next Button
     else if (ae.getActionCommand().equals("Next")){
        output = (randomName());
        jTxtNames.append (output + "\n\nCommand?\n\n");
     }
     //List button
     else if (ae.getActionCommand().equals("List")){
        output = list();
        jTxtNames.append("\n" + output + "Command?\n\n");
     }
     //Help Button
     else if (ae.getActionCommand().equals("Help")){
        JOptionPane.showMessageDialog(null, "<html>Next: Next random name"
                + "<br>List: List all the unique names that have been called, as well as the number of times"
                + "<br>Help: Display this message"
                + "<br>Exit: Exit the program</html>", "Help", JOptionPane.INFORMATION_MESSAGE);
     }
   }
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
   //Generates a random name, adds that name to the data output and
   //increments the instances of this name coming up
   public String randomName() {
      Random randomGenerator = new Random();
      int randomNumber = randomGenerator.nextInt(size);
      names[randomNumber].occured ++;
      nameCount ++;
      return (names[randomNumber].firstName + " " + names[randomNumber].lastName);
   }
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
   //Traverses array to find unique names and the number of times generated
   public String list() {
      String strList = "";
      count = 0;
      for (int i=0; i < size; i++){
         if (names[i].occured > 0){
            count ++;
            jTxtNames.append(count + ". " + names[i].firstName + " " + names[i].lastName + " (" + names[i].occured + ")\n");
         }
      }
      return strList;
   }
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
   //Reads data from file into student array
   public static void inputNames(String filePath){
       try {
            FileReader filereader = new FileReader(filePath);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            StringTokenizer nameTokens;
            String tmpLast;
            String tmpFirst;
            
            try {
            nameTokens = new StringTokenizer(bufferedreader.readLine());
               while (nameTokens.hasMoreTokens()){
                  tmpLast = nameTokens.nextToken(",");
                  tmpFirst = nameTokens.nextToken();
                  names[size] = new student(tmpLast, tmpFirst);
                  while (nameTokens.hasMoreTokens()) {
                     String checkToken = nameTokens.nextToken();
                     if (checkToken.equals("null")){}
                     else {
                        names[size].firstName += checkToken;
                     }
                  }
                  //System.out.println(names[size][1] + " " + names[size][0]);
                  size ++;
                  nameTokens = new StringTokenizer(bufferedreader.readLine());
               }
            }
            catch (IOException e){}
       }
       
       //Handles file not found exception
       catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error: File Not Found");
       }
   }
   //-------------------------------------------------------------------
   //-------------------------------------------------------------------
   public static void main(String[] args){
      //Open Dialog Box to search for input file. 
      SwingUtilities.invokeLater(new Runnable() {            
         public void run() {
                
            JFileChooser jfc = new JFileChooser();
                
            //Set the file filter
            jfc.setFileFilter(new JavaFileFilter());
            
            int result = jfc.showOpenDialog(null);
            
            //if "name.txt" is valid, run the program
            if(result == JFileChooser.APPROVE_OPTION) {
               new nameselector(jfc.getSelectedFile().getAbsoluteFile());
            }
         }
      });
   }
}