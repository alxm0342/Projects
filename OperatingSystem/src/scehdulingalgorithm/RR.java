package scehdulingalgorithm;

import java.util.ArrayList;
import java.util.List;

import entity.Job;

public class RR extends BaseSchedule implements schedulingIntl {

	private int timeSlice;
	private List<Job> RRList = new ArrayList<Job>();

	public int getTimeSlice() {
		return timeSlice;
	}

	public void setTimeSlice(int timeSlice) {
		this.timeSlice = timeSlice;
	}

	@Override
	public void run() {
		getJobs();
		doJobs();
	}

	protected void jobRun(List<Job> list) {
		int systemTime = 0;
		int i = 0 ;
		Job job; 
		while (true) {
			for ( ; i < list.size(); i++) {
				int restTime = list.get(i).getRestTime();
				if (restTime > timeSlice) {
					list.get(i).setRestTime(restTime - timeSlice);
					job = list.get(i);
					job.setEndTime(systemTime += timeSlice);
					Job jobAdd = copy(job);
					RRList.add(jobAdd);
				} else {
					job = list.get(i);
					job.setEndTime(systemTime += job.getRestTime());
					RRList.add(job);
					list.remove(i);
					if(i!=(list.size()))
						i--;
				}
			}
			if (list.isEmpty()) {
				break;
			} else {
				i = 0;
			}
		}
		showResult(RRList);
		RRList.clear();
	}
	
	protected Job copy(Job job) {
		Job copy = new Job();
		copy.setEndTime(job.getEndTime());
		copy.setRestTime(job.getRestTime());
		return copy;
	}
}
