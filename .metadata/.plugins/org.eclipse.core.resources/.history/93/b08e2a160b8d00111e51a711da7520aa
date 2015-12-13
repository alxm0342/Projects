/** Alexis Mendez
 *  CS301 - Project 2 (Interpolation)
 *  11/16/2011
 */

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

//Custome file filter only approves "input.txt"
class JavaFileFilter extends FileFilter{
    
    //Returns true only if input file is valid
    public boolean accept(File file){
        if (file.getName().equals("input.txt")){
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
        return "input.txt";
    }
}

public class DividedDifference {
    static double[] x = new double[50];
    static double[] fx = new double[50];
    static double[][] a = new double[50][50];
    static int count = 0;
    
    public static void readInput(String filePath) {
        count = 0;
        
        try{
            FileReader filereader = new FileReader(filePath);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String xLine = "";
            String fxLine = "";
            
            //Divide input file into x and fx buffers
            try{xLine = bufferedreader.readLine();}
            catch (IOException e){}
            StringTokenizer xTokens = new StringTokenizer(xLine);
            
            try{fxLine = bufferedreader.readLine();}
            catch (IOException e){}
            StringTokenizer fxTokens = new StringTokenizer(fxLine);
            
            //Extract x values into array
            while (xTokens.hasMoreTokens())
            {
                x[count] = Double.parseDouble(xTokens.nextToken());
                count ++;
            }
            
            count = 0;
            
            //Exract fx values into array
            while (fxTokens.hasMoreTokens())
            {
                fx[count] = Double.parseDouble(fxTokens.nextToken());
                count ++;
            }
        }
        
        //Handles file not found exception
        catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error: File Not Found");
        }
    }
    
    public static void divideIt(){
        //double[][] a = new double[50][50];
        int n = count-1;
        
        for(int i=0; i <= n; i++){
            a[i][0] = fx[i];
        }
        for(int j=1; j <= n; j++){
            for(int i=0; i <= (n-j); i++){
                a[i][j] = (a[i+1][j-1] - a[i][j-1])/(x[i+j] - x[i]);
            }
        }        
    }
    
    public static void outputTable(){
        
        int n = count-1;
        
        //Print the title row of the divided difference table
        System.out.printf("%-16s \t", "x");
        System.out.printf("%-16s \t", "f(x)");
        
        for (int i = 0; i < n; i++){
            String fxString = "fx[,";
            for (int j = 0; j < i; j++){
                fxString += ",";
            }
            
            fxString += "]";    
            System.out.printf("%-16s \t", fxString);
        }
        
        System.out.println();
        
        //Print the polynomials to the divided difference table
        for (int i = 0; i <= n; i++){
            System.out.printf("%-16s \t", x[i] + "");
            for (int j = 0; j <= n; j++){
                System.out.printf("%-16s \t", a[i][j] + "");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    //Outputs the unsimplified polynomial
    public static void outputUnsimplified(){
        
        int n = count-1;
        String theOutput = ("p(x) = " + fx[0]);
        String polynomial = "";
        for(int i =0; i < n; i++){
            //Handles positive x values
            if (x[i] >= 0){
            polynomial = polynomial + "(x-" + x[i] + ")";
            }
            //Handles negative x values
            else{
            polynomial = polynomial + "(x+" + x[i] + ")";
            }
            
            if (a[0][i+1] >= 0){
            theOutput = theOutput + " + " + a[0][i+1] + polynomial;
            }
            else{
            theOutput = theOutput + " " + a[0][i+1] + polynomial;
            }
        }
        
        System.out.println(theOutput);
        System.out.println();
    }
    
    //Outputs the simplified polynomial
    public static void outputSimplified(){
        int n = count - 1;
        double finalxorders[] = new double[50];
        
        if (n == 0){
            System.out.println("p(x) = " + fx[0]);
            System.out.println();
        }
        else if (n == 1){
            if (fx[0] >= 0){
                System.out.println("p(x) = " + a[0][1] + "x +" + fx[0]);
            }
            else{
                System.out.println("p(x) = " + a[0][1] + "x " + fx[0]);
            }
        }
        else{
            for (int i = n-1; i >= 0; i--){
                double xorder[] = new double[50];
                double newxorder[] = new double[50];
                xorder[1] = 1;
                xorder[0] = -x[i];
                
                for (int k = i; k > 0; k--){
                    for (int j = 0; j <= i; j++){
                        //multiplies polynomial by x
                        newxorder[j+1] = xorder[j];
                    }
                    for (int j = 0; j <= i; j++){
                        //multiplies polynomial by x-value
                        xorder[j] = xorder[j]*(-x[k-1]);
                     }
                    //add common order polynomials
                    for (int j = 0; j <= i+1; j++){
                        xorder[j] = xorder[j] + newxorder[j];
                    }
                }
                
                //multiply simplified polynomial by coeficient
                for (int z = 0; z < count; z ++){
                    xorder[z] = a[0][i+1] * xorder[z];
                }
                
                //save into final coeficients
                for (int z = 0; z < count; z ++){
                    finalxorders[z] += xorder[z];
                }
            }
            
            String theOutput = ("p(x) = " + finalxorders[n] + "x^" + n);
            
            for (int out = n-1; out > 0; out--){
                if (finalxorders[out] > 0){
                    theOutput = (theOutput + " + " + finalxorders[out] + "x^" + out);
                }
                else if (finalxorders[out] < 0){
                    theOutput = (theOutput + " " + finalxorders[out] + "x^" + out);
                }
                else {}//coeffiecient is zero
            }
            if (fx[0] > 0){
                theOutput = (theOutput + " + " + fx[0]);
            }
            else if (fx[0] < 0){
                theOutput = (theOutput + " " + fx[0]);
            }
            else {}//fx[0] is zero
            
            //Display simplified polynomial
            System.out.println(theOutput);
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        
        //Open Dialog Box to search for input file. 
        SwingUtilities.invokeLater(new Runnable() {            
            public void run() {
                
                JFileChooser jfc = new JFileChooser();
                
                //Set the file filter
                jfc.setFileFilter(new JavaFileFilter());
                
                int result = jfc.showOpenDialog(null);
                
                //if "input.txt" is valid, run the program
                if(result == JFileChooser.APPROVE_OPTION)
                    readInput(jfc.getSelectedFile().getAbsolutePath());
                    divideIt();
                    outputTable();
                    outputUnsimplified();
                    outputSimplified();
            }
         }); 
        

    }
}