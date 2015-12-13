package scehdulingalgorithm;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import entity.Job;

public class SJF extends BaseSchedule implements schedulingIntl {
	
	@Override
	public void run() {
		getJobs();
		jobSort(jobList);
		doJobs();
	}

	private void jobSort(List<Job>[] jobList2) {
		for (int i = 0; i < jobList2.length; i++) {
			Collections.sort(jobList[i], new Comparator<Job>() {
				public int compare(Job arg0, Job arg1) {
					return arg0.getBurstTime().compareTo(arg1.getBurstTime());
				}
			});
		}
		
	}
}
