// Alexis Mendez
// CS431, Section 01 - Young
// 11/15/12
// Project 1: A representation of process scheduling algorithms

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class ProcessScheduler {
	static FileReader filereader;
	static BufferedReader bufferedreader;
	static JobList inJobList;

	//---------------------------------------------------------------------------------
	public static void readInput(String filepath) {
		try {
			//open input file
			filereader = new FileReader(filepath);
			bufferedreader = new BufferedReader(filereader);
			String inLine = "";
            
         //read first line of input text
         try{inLine = bufferedreader.readLine();}
         catch (IOException e){}
         //read input lines
         while (inLine!=null) {
         	//save first input line (Job Name)
             String tempName = inLine;
             //retrieve second input line (Job Length)
             try{inLine = bufferedreader.readLine();}
             catch (IOException e){}
             //save input line as job length
             int tempLength = Integer.parseInt(inLine);
             Job newJob = new Job(tempName, tempLength);
             inJobList.add(newJob);
             try{inLine = bufferedreader.readLine();}
             catch (IOException e){}
         }
		}
		catch(FileNotFoundException e){
	        JOptionPane.showMessageDialog(null, "Error: File Not Found");
	   }
	}
	//---------------------------------------------------------------------------------
	public static void firstComeFirstServe() {
		JobList fcfsList = inJobList.copy();
		Job runMe = fcfsList.getHead().getNext();
		int totalTime = 0;
		int jobsRan = 0;
		float avgTime = 0;
		DecimalFormat df = new DecimalFormat("#.##");
   	System.out.print("First Come First Serve: "); 
		while (runMe != null) {
			System.out.print("[" + totalTime + "-" + runMe.getJobName() + "-("
					+ runMe.getTimeLeft() + ")-");
			int runTime = runMe.run();
			for (int i=0; i < runTime; i++) {
				totalTime ++;
			}
			System.out.print("(Done)-" + totalTime + "]");
			runMe = runMe.getNext();
			avgTime += totalTime;
			jobsRan ++;
		}
		avgTime = avgTime/jobsRan;
		System.out.print(" Total Time: " + df.format(totalTime) +
				", Average Completion Time: "	+ df.format(avgTime) + " \n");
	}
	//---------------------------------------------------------------------------------
	//---------------------------------------------------------------------------------
	public static void shortestJobFirst() {
		JobList sjfList = inJobList.copy();
		Job runMe;
		int totalTime = 0;
		int jobsRan = 0;
		float avgTime = 0;
		DecimalFormat df = new DecimalFormat("#.##");
   	System.out.print("Shortest Job First:     "); 
		while (!sjfList.isEmpty()) {
			Job cursor = sjfList.getHead().getNext();
			runMe = cursor;
			for (int i=0; i < sjfList.getSize(); i++) {
				if (cursor.getJobLength() < runMe.getJobLength()) {
					runMe = cursor;
				}
				cursor = cursor.getNext();
			}
			System.out.print("[" + totalTime + "-" + runMe.getJobName() + "-("
					+ runMe.getTimeLeft() + ")-");
			int runTime = runMe.run();
			for (int i=0; i < runTime; i++) {
				totalTime ++;
			}
			System.out.print("(Done)-" + totalTime + "]");
			sjfList.delete(runMe);
			avgTime += totalTime;
			jobsRan ++;
		}
		avgTime = avgTime/jobsRan;
		System.out.print(" Total Time: " + df.format(totalTime) +
				", Average Completion Time: "	+ df.format(avgTime) + " \n");
	}
	//---------------------------------------------------------------------------------
	public static void roundRobin(int slice) {
		JobList rrList = inJobList.copy();
		Job runMe;
		int totalTime = 0;
		int jobsRan = 0;
		float avgTime = 0;
		DecimalFormat df = new DecimalFormat("#.##");
   	System.out.print("Round Robin (" + slice + "):        "); 
		while (!rrList.isEmpty()) {
			runMe = rrList.getHead().getNext();
			int jobsRemaining = rrList.getSize();
			for (int i=0; i < jobsRemaining; i++) {
				System.out.print("[" + totalTime + "-" + runMe.getJobName() + "-("
						+ runMe.getTimeLeft() + ")-");
				int ranFor = runMe.runFor(slice);
				totalTime += ranFor;
				for (int j=0; j < ranFor; j++) {
				}
				Job wasRun = runMe;
				runMe = runMe.getNext();
				if (wasRun.getTimeLeft() == 0){
					System.out.print("(Done)-" + totalTime + "]");
					avgTime += totalTime;
					rrList.delete(wasRun);
					jobsRan ++;
				}
				else {
					System.out.print("(" + wasRun.getTimeLeft() + ")-" + totalTime + "]");
				}
			}
		}
		avgTime = avgTime/jobsRan;
		System.out.print(" Total Time: " + df.format(totalTime) +
				", Average Completion Time: "	+ df.format(avgTime) + " \n");
	}
	//---------------------------------------------------------------------------------
   public static void main(String[] args) {
   	//process first input file
   	inJobList = new JobList();
   	readInput("testdata1.txt");
   	System.out.println("Test Data 1: ");
   	firstComeFirstServe();
   	shortestJobFirst();
   	roundRobin(3);
   	roundRobin(5);
   	System.out.println();
   	
   	//process second input file
   	inJobList = new JobList();
		readInput("testdata2.txt");
   	System.out.println("Test Data 2: ");
   	firstComeFirstServe();
   	shortestJobFirst();
   	roundRobin(3);
   	roundRobin(5);
   	System.out.println();

   	//process third input file
		inJobList = new JobList();
		readInput("testdata3.txt");
   	System.out.println("Test Data 3: ");
   	firstComeFirstServe();
   	shortestJobFirst();
   	roundRobin(3);
   	roundRobin(5);
   	System.out.println();
   }
}
