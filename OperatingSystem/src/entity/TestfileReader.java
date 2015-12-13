package entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestfileReader {
	
	private List<Job> jobList;
	private String name;
	private int burstTime;
	
	public TestfileReader() {
	
	}

	public List<Job> getFile(String fileName){
		jobList =  new ArrayList<Job>();
		Job job;
		try {
			BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			while(true){
				int flag =2;
				String str;
				String [] jobTemp = new String[2];
				while(flag>0&&(str=in.readLine())!=null){
					jobTemp[flag-1]=str;
					flag--;
				}
				if(flag==0){
					name = jobTemp[1];
					burstTime = Integer.parseInt(jobTemp[0]);
					job=new Job(name,burstTime);
					job.setRestTime(burstTime);
					jobList.add(job);		
				}	
				if(flag==2)
					break;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jobList;
	}

}
