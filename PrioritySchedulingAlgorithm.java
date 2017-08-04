/** PrioritySchedulingAlgorithm.java
 * 
 * A single-queue priority scheduling algorithm.
 *
 * @author: Charles Zhu
 * Spring 2016
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

import com.jimweller.cpuscheduler.Process;

public class PrioritySchedulingAlgorithm extends BaseSchedulingAlgorithm implements OptionallyPreemptiveSchedulingAlgorithm {
    
    private boolean preemptive;
    private ArrayList<Process> jobs;
    
    class CompareJob implements Comparator<Process>{
        public int compare(Process p1, Process p2){
            if (p1.priority != p2.priority)
                return (int) (p1.priority - p2.priority);
            else
                return (int) (p1.PID - p2.PID);
        }
    }
    
    CompareJob comparator = new CompareJob();

    
    PrioritySchedulingAlgorithm(){
        // Fill in this method
        /*------------------------------------------------------------*/
        activeJob = null;
        preemptive=false;
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
        Collections.sort(jobs,comparator);
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

        if (!isJobFinished() && !isPreemptive())
            return activeJob;
        
        activeJob = (Process) jobs.get(0);
        return activeJob;

        /*------------------------------------------------------------*/
    }

    public String getName(){
        return "Single-Queue Priority";
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
     * @param v Value to assign to preemptive.
     */
    public void setPreemptive(boolean v){
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/

        preemptive = v;

        /*------------------------------------------------------------*/
    }
    
}
