/**Name: Alex Mendez - aamendez
 * Date: 11/30/11
 * Class: CS380 - Gershman
 * Description: HW4, a SHA-1 hashed authenticated quote server that listens on port 8080
 */

import java.util.*;
import java.io.*;
import java.net.*;
import java.security.*;

public class QuoteServer {

   /**
    * @param args the command line arguments
    */
   public static Scanner keyboard = new Scanner(System.in);
   public static boolean shutDown = false;
   
   public static String printQuote() {
      Random randomGenerator = new Random();
      int randomNumber = randomGenerator.nextInt(20);
      String theQuote = "";
      
      switch (randomNumber){
         case 0:
            theQuote = "If you keep on believing, the dreams that you wish will come true. -Cinderella."; break;
         case 1:
            theQuote = "I'd rather die tomorrow than live a hundred years without knowing you. -Pocahontas"; break;
         case 2:
            theQuote = "The past can hurt. You can either run from it or learn from it. -The Lion King"; break;
         case 3:
            theQuote = "Did you ever stop to think, and forget to start again? -Winnie the Pooh"; break;
         case 4:
            theQuote = "First rule of leadership: everything is your fault. -Hopper, Finding Nemo"; break;
         case 5:
            theQuote = "A man should never neglect his family for business. -Walt Disney"; break;
         case 6:
            theQuote = "A bear, no matter how hard he tries, grows tubby without exercise. -Winnie the Pooh"; break;
         case 7:
            theQuote = "Ohana means family, family means nobody gets left behind. Or forgotten. -Lilo"; break;
         case 8:
            theQuote = "All you need is a little faith, trust, and pixie dust. -Peter Pan"; break;
         case 9:
            theQuote = "All our dreams can come true if we have the courage to pursue them. -Walt Disney"; break;
         case 10:
            theQuote = "The flower that blooms in adversity is the most rare and beautiful of all. -Mulan"; break;
         case 11:
            theQuote = "Keep your chin up, someday there will be happiness again. -Robin Hood"; break;
         case 12:
            theQuote = "Things will look better in the morning. -The Jungle Book"; break;
         case 13:
            theQuote = "The seaweed is always greener in somebody else's lake. -The Little Mermaid"; break;
         case 14:
            theQuote = "In every job that must be done there is an element of fun. -Mary Poppins"; break;
         case 15:
            theQuote = "Reach for the sky! -Toy Story"; break;
         case 16:
            theQuote = "Nothing's impossible. -Alice In Wonderland"; break;
         case 17:
            theQuote = "What do you do when things go wrong? Oh! You sing a song! -Snow White"; break;
         case 18:
            theQuote = "A lie keeps growing and growing until it's as plain as the nose on your face. -Pinnocchio"; break;
         case 19:
            theQuote = "Reflect before you act. -Mulan"; break;
      }
      
      return (theQuote);
   }
   
   public static String convertToHex(byte[] data){
      StringBuffer buf = new StringBuffer();
      for(int i = 0; i< data.length; i++){
         int halfbyte = (data[i] >>> 4) & 0x0F;
         int two_halfs = 0;
         do{
            if ((0 <= halfbyte) && (halfbyte <= 9)){
               buf.append((char) ('0' + halfbyte));
            }
            else{
               buf.append((char) ('a' + (halfbyte - 10)));
            }
            halfbyte = data[i] & 0x0F;
            
         } while (two_halfs++ < 1);
      }
      return buf.toString();
   }
   
   public static String SHA1(String convertMe) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      MessageDigest md;
      md = MessageDigest.getInstance("SHA-1");
      byte[] sha1Hash = new byte [40];
      md.update(convertMe.getBytes("iso-8859-1"), 0, convertMe.length());
      sha1Hash = md.digest();
      return convertToHex(sha1Hash);
   }
   
   public static boolean loginSuccess(String name, String pass) {
      String passHash = "";
      try{passHash = SHA1(pass);}
      catch (NoSuchAlgorithmException nS){}
      catch (UnsupportedEncodingException uS){}
      
      String logMe = name + ":" + passHash;
      String tryMe = "";
      
      try{
         //Open login file
         FileReader fileReader = new FileReader("C:\\Users\\Master\\Documents\\Programming Projects\\QuoteServer\\logins.txt");
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         
         try {
            tryMe = bufferedReader.readLine();
            while (tryMe != null){
               if (logMe.equals(tryMe)){
                  return true;
               }
               else{
                  tryMe = bufferedReader.readLine();
               }
            }   
         }
         catch(IOException e){}
      }
      catch(IOException e){}
      
      return false;
   }
   
   public static void main(String[] args) {      
   
      while (shutDown == false){
         String userName = "";
         String pw = "";

         try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true){
               boolean quitFlag = false;
               String command = "";
               StringTokenizer commandTokens;
               Socket connection = serverSocket.accept();
               //Establish the output stream
               InputStream ins = connection.getInputStream();
               OutputStream outs = connection.getOutputStream();
               PrintWriter out = new PrintWriter(outs, true);
               Scanner in = new Scanner(ins);

               out.println("Authenticated Quote Server");
               out.println("Mendez, Alexis - aamendez");

               while (quitFlag == false){

                  out.println("Ready.");
                  command = in.nextLine();
                  commandTokens = new StringTokenizer(command);
                  String cmd = "";
                  
                  try{cmd = commandTokens.nextToken();}
                  catch (NoSuchElementException nE){}
                  
                  if (cmd.equals("DISC")){
                     out.println("Goodbye.");
                     quitFlag = true;
                     connection.close();
                  }

                  else if (cmd.equals("AUTH")) {
                     
                     if (commandTokens.hasMoreTokens() == false){
                        out.println("Error: Bad Input");
                     }
                     
                     while (commandTokens.hasMoreTokens()){
                        out.println("Auth OK");

                        //Set the username
                        userName = commandTokens.nextToken();

                        command = in.nextLine();
                        commandTokens = new StringTokenizer(command);
                        
                        try{cmd = commandTokens.nextToken();}
                        catch (NoSuchElementException nE){}

                        if (cmd.equals("PASS")){

                           try{pw = commandTokens.nextToken();}
                           catch (NoSuchElementException nE){pw = null;}
                           
                           if (pw != null){
                              if (loginSuccess(userName, pw)){
                                 boolean logFlag = true;
                                 out.println("Authentication Successful");
                                 while (logFlag){
                                    out.println("Ready.");

                                    command = in.nextLine();
                                    commandTokens = new StringTokenizer(command);
                                    
                                    try{cmd = commandTokens.nextToken();}
                                    catch (NoSuchElementException nE){cmd = "";}

                                    if (cmd.equals("QUOT")){

                                       if (commandTokens.hasMoreTokens()){
                                          String num = commandTokens.nextToken();
                                          int repeats = 0;
                                          try {
                                             //Determine number of quotes
                                             repeats = Integer.parseInt(num);
                                          }
                                          //Handle negatives and stupid numbers
                                          catch (NumberFormatException nF){
                                             repeats = 0;
                                          }
                                          if (repeats > 0){
                                             //Print the specified number of quotes to the client screen
                                             for (int i = 0; i < repeats; i++){
                                                out.println("QBEG");
                                                out.println(printQuote());
                                                out.println("QEND");
                                             }
                                          }
                                          else{
                                             out.println("Error: Bad Input");
                                          }
                                       }
                                       else{
                                          out.println("QBEG");
                                          out.println(printQuote());
                                          out.println("QEND");
                                       }
                                    }
                                    else if (cmd.equals("DISC")){
                                       out.println("Goodbye.");
                                       logFlag = false;
                                       quitFlag = true;
                                       connection.close();
                                    }
                                    else {
                                       out.println("Error: Bad Input");
                                    }
                                 }
                              }
                              else {
                              out.println("Authentication Failed");
                              }
                           }
                           else {
                              out.println("Error: Bad Input");
                           }
                        }
                        else if (cmd.equals("DISC")){
                           out.println("Goodbye.");
                           quitFlag = true;
                           connection.close();
                        }
                        else {
                           out.println("Error: Bad Input");
                           try{commandTokens.nextToken();}
                           catch(NoSuchElementException nE){}
                        }
                     }
                  }
                  else {
                     out.println("Error: Bad Input");
                  }
               }
            }
         }
         catch (IOException e){
            System.out.println("Could not listen on port: 8080");
            System.exit(0);
         }
      }
      System.exit(0);
   }
}