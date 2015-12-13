package test;

import scehdulingalgorithm.*;

public class test {

	public static void main(String[] args) {
		
		/*FCFS sfj = new FCFS();
		sfj.run();
		
		SJF sfj1 = new SJF();
		sfj1.run();*/
		
		RR rr = new RR();
		rr.setTimeSlice(3);
		rr.run();
		
	}
}
