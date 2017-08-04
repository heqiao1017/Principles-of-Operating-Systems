//---------------------------------------------------------------
//File Synchro2.java
//-----------------------------------------------------------------

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.Scanner;

public class Synchro2 {

	static SemaphoreWrapper semaphore1;
	static SemaphoreWrapper semaphore2;

	static class ProcessA implements Runnable { 
		int _loops;
		ArrayList<Integer> _sleeping_durations;

		public ProcessA(int loops, ArrayList<Integer> sleeping_durations) {
			_loops = loops;
			_sleeping_durations = sleeping_durations;
		}

    	public void run() { 
	    	for (int i = 0; i< _loops ; i++){
	    		/* You might want to fill in your code here. */
	    		semaphore1.P(1);

	      		System.out.println ("A--> "+ i);
              	try {
	         		Thread.sleep(_sleeping_durations.get(i),0);
              	} catch (Exception e) {
                	System.out.print("Exception: ");
                	System.out.println(">" + e.toString() + "<");
            	}
            	semaphore2.V(1);
				
				/* You might want to fill in your code here. */

	    	} 
		}
	}

	static class ProcessB implements Runnable {
		int _loops;
		ArrayList<Integer> _sleeping_durations;

		public ProcessB(int loops, ArrayList<Integer> sleeping_durations) {
			_loops = loops;
			_sleeping_durations = sleeping_durations;
		}

		public void run() {	
	    	for (int i = 0; i< _loops ; i++) {
            	/* You might want to fill in your code here. */
				semaphore2.P(1);

				System.out.println ("B--> "+ i);
	    		try {
	    			Thread.sleep(_sleeping_durations.get(i),0);
	    		} catch (Exception e) {
	    			System.out.print("Exception: ");
	    			System.out.println(">" + e.toString() + "<");
	    		}
				semaphore1.V(1);
            	
            	/* You might want to fill in your code here. */

	    	}
	    } 
	}
	
	static public void main(String[] args) {
   	    try {
   	    	Scanner in = new Scanner(System.in);
   	    	int loops = in.nextInt();

   	    	semaphore1 = new SemaphoreWrapper(1 /* You are free to change the number here. */);
   	    	semaphore2 = new SemaphoreWrapper(1 /* You are free to change the number here. */);

   	    	ArrayList<Integer> sleeping_durations_A = new ArrayList<Integer>();
   	    	ArrayList<Integer> sleeping_durations_B = new ArrayList<Integer>();
   	    	for (int i=0; i < loops; ++i) {
   	    		int duration_A = in.nextInt();
   	    		int duration_B = in.nextInt();
   	    		sleeping_durations_A.add(duration_A);
   	    		sleeping_durations_B.add(duration_B);
   	    	}

   	    	Thread process_A = new Thread(new ProcessA(loops, sleeping_durations_A));
   	    	Thread process_B = new Thread(new ProcessB(loops, sleeping_durations_B));

   	    	// Start processes.
   	    	process_A.start();
   	    	process_B.start();

		} catch (Exception e) {
			System.out.print("Exception: ");
			System.out.println(">" + e.toString() + "<");
		}
	}
}
