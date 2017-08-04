/** SJFSchedulingAlgorithm.java
 * 
 * A shortest job first scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class SJFSchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    
    
    private ArrayList<Process> jobs;
    private boolean preemptive;

    class CompareJob1 implements Comparator<Process>{
        public int compare(Process p1, Process p2){
            if(p1.getBurstTime() > p2.getBurstTime())
                return 1;
            else if(p1.getBurstTime() < p2.getBurstTime())
                return -1;
            else if(p1.getPID() > p2.getPID())
                return 1;
            else if(p1.getPID() > p2.getPID())
                return -1;
            else
                return 0;
        }
    }
    class CompareJob2 implements Comparator<Process>{
        public int compare(Process p1, Process p2){
            if (p1.getInitBurstTime() < p2.getInitBurstTime())
                return -1;
            else if (p1.getInitBurstTime() > p2.getInitBurstTime())
                return 1;
            else if (p1.getPID() < p2.getPID())
                return -1;
            else if (p1.getPID() > p2.getPID())
                return 1;
            else
                return 0;
        }
    }
                
    CompareJob1 comparator1 = new CompareJob1();
    CompareJob2 comparator2 = new CompareJob2();
    

    
    SJFSchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
        activeJob = null;
        preemptive = false;
        jobs = new ArrayList<Process>();

        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue.*/
    public void addJob(Process p){
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/

        jobs.add(p);
        /*------------------------------------------------------------*/
    }
    
    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p){
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        return jobs.remove(p);

        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        throw new UnsupportedOperationException();
    }

    /** Returns the next process that should be run by the CPU, null if none available.*/
    public Process getNextJob(long currentTime){
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        if(activeJob == null || activeJob.isFinished())
        {
            if(!preemptive){
                Collections.sort(jobs,comparator2);
                activeJob = (Process) jobs.get(0);
            }
        }
        if(preemptive){
            Collections.sort(jobs,comparator1);
            activeJob = (Process) jobs.get(0);
        }




        return activeJob;
        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "Shortest Job First";
    }

    /**
     * @return Value of preemptive.
     */
    public boolean isPreemptive(){
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/

        return preemptive;

        /*------------------------------------------------------------*/
    }
    
    /**
     * @param v  Value to assign to preemptive.
     */
    public void setPreemptive(boolean  v){
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        preemptive = v;


        /*------------------------------------------------------------*/
    }
    
}
