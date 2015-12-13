package scehdulingalgorithm;

public class FCFS extends BaseSchedule implements schedulingIntl{
	

	@Override
	public void run(){
		getJobs();
		doJobs();	
	}
	
}
