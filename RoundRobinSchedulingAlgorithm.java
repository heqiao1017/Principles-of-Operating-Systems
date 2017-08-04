/** RoundRobinSchedulingAlgorithm.java
 * 
 * A scheduling algorithm that randomly picks the next job to go.
 *
 * @author: Kyle Benson
 * Winter 2013
 *
 */
package com.jimweller.cpuscheduler;

import java.util.*;

public class RoundRobinSchedulingAlgorithm extends BaseSchedulingAlgorithm {

    /** the time slice each process gets */
    private int quantum;
    private Vector<Process> jobs;
    private int current_index;
    private int quantumCounter;
    
    RoundRobinSchedulingAlgorithm() {
        // Fill in this method
        /*------------------------------------------------------------*/
        activeJob = null;
        quantum = 10;
        jobs = new Vector<Process>();
        current_index=-1;
        quantumCounter=quantum;

        /*------------------------------------------------------------*/
    }

    /** Add the new job to the correct queue. */
    public void addJob(Process p) {
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        jobs.add(p);


        /*------------------------------------------------------------*/
    }

    /** Returns true if the job was present and was removed. */
    public boolean removeJob(Process p) {
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        int index = jobs.indexOf(p);
        boolean to_return = jobs.remove(p);
        if (index >= 0 && current_index >= index )
        {
            if(current_index == index)
                quantumCounter = 0;
            current_index--;
            if (current_index >= 0){
                activeJob = jobs.get(current_index);
            }
            else{
                activeJob = null;
            }
        }
        
        return to_return;
        /*------------------------------------------------------------*/
    }

    /** Transfer all the jobs in the queue of a SchedulingAlgorithm to another, such as
    when switching to another algorithm in the GUI */
    public void transferJobsTo(SchedulingAlgorithm otherAlg) {
        throw new UnsupportedOperationException();
    }

    /**
     * Get the value of quantum.
     * 
     * @return Value of quantum.
     */
    public int getQuantum() {
        return quantum;
    }

    /**
     * Set the value of quantum.
     * 
     * @param v
     *            Value to assign to quantum.
     */
    public void setQuantum(int v) {
        this.quantum = v;
    }

    /**
     * Returns the next process that should be run by the CPU, null if none
     * available.
     */
    public Process getNextJob(long currentTime) {
        // Remove the next lines to start your implementation
        //throw new UnsupportedOperationException();
        
        // Fill in this method
        /*------------------------------------------------------------*/
        quantumCounter--;
        
        if (current_index >= 0 && !isJobFinished() && quantumCounter > 0) {
            return activeJob;
        }
        
        if (jobs.size() <= 0){
            activeJob = null;
            return activeJob;
        }

        if(current_index>=0 && current_index<(jobs.size()-1))
            current_index++;
        else
            current_index = 0;

        quantumCounter = quantum;
        activeJob = jobs.get(current_index);
        return activeJob;
        /*------------------------------------------------------------*/
    }

    public String getName() {
        return "Round Robin";
    }
    
}
