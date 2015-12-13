/*
 * Name:        Mendez, Alexis
 * Project:     3
 * Due:         11/9/2011
 * Course:      CS-245-f11
 * Description: This is an example of the JPanels, JLabels, and JButtons used to
 *              create a primitive integer calculator.
 */
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
  
public class JCalc implements ActionListener, KeyListener { 
    
    //Left operand
    int left = 0;
    //Right operand
    int right = 0;
    //Flag for modifying left operand
    boolean isLeft = true;
    //Flag to clear the display the next time a digit is pressed
    boolean clearFlag = true;
    //Counter for cycling through the displays
    int count = 0;
    //String holder for the cycling display
    String dispContents = "";
    //Operation to be performed, 99 is the default to do nothing
    int operation = 99;
    //Global label for the keyboard display
    JLabel output = new JLabel();
    
    //Create keyboard buttons
    JButton one = new JButton("1");
    JButton two = new JButton("2");
    JButton three = new JButton("3");
    JButton four = new JButton("4");
    JButton five = new JButton("5");
    JButton six = new JButton("6");
    JButton seven = new JButton("7");
    JButton eight = new JButton("8");
    JButton nine = new JButton("9");
    JButton zero = new JButton("0");
    JButton equals = new JButton("=");
    JButton add = new JButton("+");
    JButton subtract = new JButton("-");
    JButton divide = new JButton("/");
    JButton multiply = new JButton("*");
    JButton clear = new JButton("C");
    
    
    //Performs and displays calculations, returns the calculation as an integer
    public static int calculate (int leftOp, int rightOp, int operation){
        int total = 0;
        
        switch (operation){
            //addition
            case 0:
                total = leftOp + rightOp;   break;
            //subtraction
            case 1:
                total = leftOp - rightOp;   break;
            //multiplication
            case 2:
                total = leftOp * rightOp;   break;
            //division
            case 3:
                total = leftOp / rightOp;   break;
            //default case if the user did not select an operation
            case 99:
                total = leftOp;             break;
        }
        
        return total;
    }
    
    //Action listener to handle button presses
    public void actionPerformed (ActionEvent ae){
        
        //displays the digit pressed and sets value needed to modify operand
        if (ae.getActionCommand().equals("1")){
            addDigit(1);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("2")){
            addDigit(2);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("3")){
            addDigit(3);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("4")){
            addDigit(4);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("5")){
            addDigit(5);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("6")){
            addDigit(6);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("7")){
            addDigit(7);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("8")){
            addDigit(8);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("9")){
            addDigit(9);
            clearFlag = false;
        }
        else if (ae.getActionCommand().equals("0")){
            addDigit(0);
            clearFlag = false;
        }
        
        //Handles if the user selects an operation:
        //Set the right operand to be modified next, selects operation,
        //sets display to be cleared, adds a tooltip to the display
        if (ae.getActionCommand().equals("+")){
            isLeft = false;
            operation = 0;
            clearFlag = true;
            output.setToolTipText(left + " +");
        }
        else if (ae.getActionCommand().equals("-")){
            isLeft = false;
            operation = 1;
            clearFlag = true;
            output.setToolTipText(left + " -");
        }
        else if (ae.getActionCommand().equals("*")){
            isLeft = false;
            operation = 2;
            clearFlag = true;
            output.setToolTipText(left + " *");
        }
        else if (ae.getActionCommand().equals("/")){
            isLeft = false;
            operation = 3;
            clearFlag = true;
            output.setToolTipText(left + " /");
        }
        
        //When "C" is pressed the display is cleared and operands are set to zero
        if (ae.getActionCommand().equals("C")){
            //Checks if the control key is pressed and cycles through displays
            if ((ae.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK){
                count ++;
                switch (count % 3){
                    case 1:
                        dispContents = output.getText();
                        output.setText("(c) 2011 Alex Mendez"); break;
                    case 2:
                        output.setText("version 0.1");          break;
                    case 0:
                        output.setText(dispContents);
                        dispContents = "";                      break;
                }
            }
            else{
                left = 0;
                right = 0;
                isLeft = true;
                operation = 99;
                clearFlag = true;
                output.setText("0");
            }
        }
        
        //Calls "Calculate" method if "=" key is pressed, prepares calculator for another operation
        if (ae.getActionCommand().equals("=")){
            left = calculate(left, right, operation);
            right = 0;
            isLeft = false;
            operation = 99;
            output.setText("" + left);
            output.setToolTipText("" + left);
        }
    }
    
    //Empty handlers, all keys must be typed to register as input
    public void keyReleased (KeyEvent e){}
    public void keyPressed (KeyEvent e){}
    
    //Handles keyboard input of numerical values
    public void keyTyped (KeyEvent e){
        switch (e.getKeyChar()){
            case '1':
                one.doClick();          break;
            case '2':
                two.doClick();          break;
            case '3':
                three.doClick();        break;
            case '4':
                four.doClick();         break;
            case '5':
                five.doClick();         break;
            case '6':
                six.doClick();          break;
            case '7':
                seven.doClick();        break;
            case '8':
                eight.doClick();        break;
            case '9':
                nine.doClick();         break;
            case '0':
                zero.doClick();         break;
            case 'c':
            case 'C':
            case KeyEvent.VK_ESCAPE:
                clear.doClick();        break;
            case '+':
                add.doClick();          break;
            case '-':
                subtract.doClick();     break;
            case '*':
                multiply.doClick();     break;
            case '/':
                divide.doClick();       break;
            case '=':
                equals.doClick();       break;
        }
    }
    
    //Adds a digit to the operand and displays the output
    public void addDigit(int addMe){
        //Handle the case of user enter digits for the first time
        if (clearFlag){
            output.setText("");
        }
        
        if (isLeft){
            left = (left*10) + addMe;
            output.setText(output.getText() + addMe);
            output.setToolTipText("" + left);
        }
        else{
            right = (right*10) + addMe;
            output.setText(output.getText() + addMe);
        }
    }
    
    //Default constructor
    JCalc () { 
 
        //Create a new JFrame container. 
        JFrame frm = new JFrame("JCalc");
        JPanel display = new JPanel();
        JPanel keyboard = new JPanel();
        
        //Initialize the size of the display and keyboard panels
        
        //Initialize the display panel look and feel
        display.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
        display.setBackground(Color.WHITE);
        output.setFont(new Font("Digital-7", Font.BOLD, 18));
        output.setForeground(Color.RED);
        output.setText("0");
        output.setHorizontalAlignment(SwingConstants.RIGHT);
        output.setPreferredSize(new Dimension(190, 110));
        output.setToolTipText("" + left);
        //Add output label to the display panel
        display.add(output);
        
        //Add action listeners and keylisteners to buttons
        one.addActionListener(this);
        one.addKeyListener(this);
        two.addActionListener(this);
        two.addKeyListener(this);
        three.addActionListener(this);
        three.addKeyListener(this);
        four.addActionListener(this);
        four.addKeyListener(this);
        five.addActionListener(this);
        five.addKeyListener(this);
        six.addActionListener(this);
        six.addKeyListener(this);
        seven.addActionListener(this);
        seven.addKeyListener(this);
        eight.addActionListener(this);
        eight.addKeyListener(this);
        nine.addActionListener(this);
        nine.addKeyListener(this);
        equals.addActionListener(this);
        equals.addKeyListener(this);
        add.addActionListener(this);
        add.addKeyListener(this);
        subtract.addActionListener(this);
        subtract.addKeyListener(this);
        divide.addActionListener(this);
        divide.addKeyListener(this);
        multiply.addActionListener(this);
        multiply.addKeyListener(this);
        clear.addActionListener(this);
        clear.addKeyListener(this);
        zero.addActionListener(this);
        zero.addKeyListener(this);
        
        //Set keyboard layout and add buttons to the keyboard panel
        keyboard.setLayout(new GridLayout(4, 4, 0, 0));
        keyboard.add(seven);
        keyboard.add(eight);
        keyboard.add(nine);
        keyboard.add(divide);
        keyboard.add(four);
        keyboard.add(five);
        keyboard.add(six);
        keyboard.add(multiply);
        keyboard.add(one);
        keyboard.add(two);
        keyboard.add(three);
        keyboard.add(subtract);
        keyboard.add(zero);
        keyboard.add(clear);
        keyboard.add(equals);
        keyboard.add(add);
        keyboard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        //Give the frame an initial size and layout 
        frm.setSize(200, 280);
        frm.setLayout(new GridLayout(2,1,0,0));
        
        //Retrieve the screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Calculate a centered X-position based on screensize
        int centeredX = (int) ((screenSize.getWidth())/2)-(200/2);
        //Calculate a centered Y-position based on screensize
        int centeredY = (int) ((screenSize.getHeight())/2)-(280/2);
        //Set the frame to display at the center of the screen
        frm.setLocation(centeredX, centeredY);
        frm.setResizable(false);
 
        //Terminate the program when the user closes the application. 
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
 
        //Set "=" as the default button
        frm.getRootPane().setDefaultButton(equals);
        
        //Add components to frame
        frm.add(display);
        frm.add(keyboard);
        
        //Display the frame.
        frm.setVisible(true);
  }

  public static void main(String args[]) { 
         //Create the frame on the event dispatching thread. 
        SwingUtilities.invokeLater(new Runnable() { 
            public void run() { 
                new JCalc(); 
            } 
        }); 
    } 
}
