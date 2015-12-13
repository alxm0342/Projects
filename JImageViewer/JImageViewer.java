/*
 * Name:        Mendez, Alexis
 * Homework:    2
 * Due:         11/16/2011
 * Course:      CS-245-f11
 * Description: This is an example of a JFileChooser with custom file filters
 *              and an ImageViewer that takes an image's dimensions into account.
 *              This program also employs a functional menubar with menus and menuitems
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;

//Custom File filter for JFileChooser
class JavaFileFilter extends FileFilter{
    
    //Returns true only if file ends with .GIF, .JPG, .JPEG, or .PNG or is a directory
    public boolean accept(File file){
        if (file.getName().endsWith(".JPG")){
            return true;
        }
        if (file.getName().endsWith(".jpg")){
            return true;
        }
        if (file.getName().endsWith(".JPEG")){
            return true;
        }
        if (file.getName().endsWith(".jpeg")){
            return true;
        }
        if (file.getName().endsWith(".GIF")){
            return true;
        }
        if (file.getName().endsWith(".gif")){
            return true;
        }
        if (file.getName().endsWith(".PNG")){
            return true;
        }
        if (file.getName().endsWith(".png")){
            return true;
        }
        if (file.isDirectory()){
            return true;
        }
        //Return false and filter out file extensions not stated above
        else{
        return false;
        }
    }
    
    //Description of what the file filter accepts
    public String getDescription(){
        return ".GIF, .JPG, .JPEG, or .PNG";
    }
}

class JImageViewer implements ActionListener {    
    //Create global JFrame
    JFrame frm;
    
    JImageViewer(String imageFs, String FileName) {
        //Import selected file as a local image icon object
        ImageIcon fileImage = new ImageIcon(imageFs);
        
        //Retrieve the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        //Create a new JFrame container. 
        frm = new JFrame("JImageViewer - " + FileName);

        //Give the frame an initial size equal to the size of the monitor, giving room for the task bar
        frm.setSize(screenSize.width, screenSize.height);
        
        //Shrinks the frame length and width if the image is smaller than the screen resolution
        if (fileImage.getIconWidth() < screenSize.width){
            frm.setSize(fileImage.getIconWidth(), frm.getHeight());
        }
        if (fileImage.getIconWidth() < screenSize.width){
            frm.setSize(frm.getWidth(), fileImage.getIconHeight());
        }
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        if (gs.length > 1) // second display?, go to it
        {
            frm.setLocation(gs[0].getDisplayMode().getWidth() + 100, 100);  // second screen
        } else
            frm.setLocationRelativeTo(null);
        //Terminate the program when the user closes the application. 
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel image = new JLabel(fileImage);
        image.setToolTipText(imageFs);
        
        image.addMouseListener(new MouseAdapter()  
        {  
            public void mouseReleased(MouseEvent me)  
            {  
                if (me.isPopupTrigger())  
                {  
                    String imageFs = JOptionPane.showInputDialog("Enter image name:");
                    
                    JLabel image = (JLabel)me.getSource();
                    image.setIcon(new ImageIcon(imageFs));
                }  
            }  
        }); 
        
        JScrollPane scroll = new JScrollPane(image);
        
        //Create menu components
        JMenuBar jmb = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiExit = new JMenuItem("Exit");
        JMenuItem jmiAbout = new JMenuItem("About");
        
        //set ActionListeners for menu Items
        jmiOpen.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiAbout.addActionListener(this);
        
        //set Mnemonics
        jmFile.setMnemonic('F');
        jmHelp.setMnemonic('H');
        jmiOpen.setMnemonic('O');
        jmiExit.setMnemonic('x');
        jmiAbout.setMnemonic('A');
        
        //set Accelerators
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        
        //File Menu Struture
        jmFile.add(jmiOpen);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        
        //Help Menu Structure
        jmHelp.add(jmiAbout);
        
        jmb.add(jmFile);
        jmb.add(jmHelp);
        
        //construct and add components to frame
        frm.setJMenuBar(jmb);
        frm.add(scroll);

        //Display the frame. 
        frm.setVisible(true);        
    }
    
    public void actionPerformed(ActionEvent ae){
        String selected = ae.getActionCommand();
        
        
        //Handles if user selects "Open"
        if (selected == "Open"){
            
            //Opens new file chooser and allows user to select a new image
            JFileChooser jfc = new JFileChooser();
                
            //Set the file filter
            jfc.setFileFilter(new JavaFileFilter());
                
            int result = jfc.showOpenDialog(null);
            
            if(result == JFileChooser.APPROVE_OPTION){
                //close existing frame
                frm.setVisible(false);
                //create new frame with newly selected image
                new JImageViewer(jfc.getSelectedFile().getAbsolutePath(), jfc.getSelectedFile().getName());
            }
        }
        //Handles if user selects "Exit", exits the program
        if (selected == "Exit"){
            System.exit(0);
        }
        //Handles if user selects "About", displays an informational dialog
        if (selected == "About"){
            JOptionPane.showMessageDialog(null, "Alex Mendez (C) copyright 2011", "About JImageViewer", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("java-icon.png"));
        }
    }
    
    public static void main(String args[]) {
        //Create the frame on the event dispatching thread. 
        SwingUtilities.invokeLater(new Runnable() {            
            public void run() {
                
                JFileChooser jfc = new JFileChooser();
                
                //Set the file filter
                jfc.setFileFilter(new JavaFileFilter());
                
                int result = jfc.showOpenDialog(null);

                if(result == JFileChooser.APPROVE_OPTION)
                    new JImageViewer(jfc.getSelectedFile().getAbsolutePath(), jfc.getSelectedFile().getName()); 
            }
         }); 
    }
}
