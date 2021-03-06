public class Job {
		//Class variables
		private String jobName;
		private int jobLength;
		private int timeRan;
		private int timeLeft;
		private Job nextJob;
		
		//Default constructor
		Job() {
			jobName = "";
			jobLength = 0;
			timeRan = 0;
			timeLeft = 0;
			nextJob = null;
		}
		//Specified constructor
		Job(String name, int length) {
			jobName = name;
			jobLength = length;
			timeRan = 0;
			timeLeft = length;
			nextJob = null;
		}
		
		//Get Methods
		public String getJobName() {
			return this.jobName;
		}
		public int getJobLength() {
			return this.jobLength;
		}
		public int getTimeRan() {
			return this.timeRan;
		}
		public int getTimeLeft() {
			return this.timeLeft;
		}
		public Job getNext() {
			return this.nextJob;
		}
		
		//Set Methods
		public void setJobName(String name) {
			this.jobName = name;
		}
		public void setJobLength(int length) {
			this.jobLength = length;
		}
		public void setNext(Job next) {
			this.nextJob = next;
		}
		private void setTimeRan(int time) {
			if (timeRan < jobLength) {
				this.timeRan = time;
				this.timeLeft = this.jobLength - this.timeRan;
			}
		}
		
		//Functional methods
     	public String toString() {
     		String returnString = this.jobName + ": " + this.jobLength + " time units to complete. ("
     				+ this.timeRan + " units ran, " + this.timeLeft + " units remaining.)";
     		return returnString;
     	}
     	public boolean hasNext() {
     		if (this.nextJob != null) {
     			return true;
     		}
     		else {
     			return false;
     		}
     	}
     	public int run() {
     		this.timeRan = this.jobLength;
     		this.timeLeft = 0;
     		return this.timeRan;
     	}
     	public int runFor(int runTime) {
     		if (runTime <= timeLeft) {
     			this.timeLeft -= runTime;
     			this.timeRan += runTime;
     			return runTime;
     		}
     		else {
     			int runFor = this.timeLeft;
     			this.timeRan += runFor;
     			this.timeLeft = 0;
     			return runFor;
     		}
     	}
     	public Job copy() {
     		Job theCopy = new Job(this.getJobName(), this.getJobLength());
     		theCopy.setTimeRan(this.getTimeRan());
     		return theCopy;
     	}
	}