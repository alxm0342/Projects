package scehdulingalgorithm;

import java.util.List;

import entity.Job;
import entity.TestfileReader;

public class BaseSchedule {
	protected List<Job> jobList[] = new List[3];
	protected double ACT;
	
	protected void doJobs() {
		for(int i = 0 ; i < jobList.length; i++){
			jobRun(jobList[i]);
		}
		
	}
	
	protected void jobRun(List<Job> list) {
		int systemTime = 0;
		for(int i =0 ; i < list.size() ; i++){
			list.get(i).setEndTime(systemTime+list.get(i).getBurstTime());
			systemTime+=list.get(i).getBurstTime();
		}
		showResult(list);
		
	}
	
	protected void showResult(List<Job> list) {
		int totalTime= 0;
		System.out.print("JOB:\t\t\t");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print(list.get(i).getName()+"\t|");
			
		}
		System.out.println();
		System.out.print("Completion Time:\t");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.print(list.get(i).getEndTime()+"\t|");
			totalTime += list.get(i).getEndTime();
		}
		System.out.println();
		
		ACT =(double)totalTime/list.size();
		System.out.println("Average Completion Time="+ACT);
		System.out.println("------------------------------------------------");
	}
	
	protected void getJobs() {
		TestfileReader testfileReader = new TestfileReader();
		jobList[0] = testfileReader.getFile("testdata1.txt");
		jobList[1] = testfileReader.getFile("testdata2.txt");
		jobList[2] = testfileReader.getFile("testdata3.txt");	
	}
}
