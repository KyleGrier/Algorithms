/*
 * Name: 
 * EID:
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.*;  

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program1 extends AbstractProgram1 {
    /**
     * Determines whether a candidate Matching represents a solution to the
     * Stable Marriage problem. Study the description of a Matching in the
     * project documentation to help you with this.
     */
    public boolean isStableMatching(Matching marriage) {
        /* TODO implement this function */
    	ArrayList<Integer> test = marriage.getWorkerMatching();
    	ArrayList<ArrayList<Integer>> work_prefer = marriage.getWorkerPreference();
    	ArrayList<ArrayList<Integer>> job_prefer = marriage.getJobPreference();
    	for(int i = 0; i < test.size(); i++){
    		int job = test.get(i);
    		ArrayList<Integer> job_pref = job_prefer.get(job);
    		ArrayList<Integer> work_pref = work_prefer.get(i);
    		int wrank1 = job_pref.indexOf(i);
    		int jrank1 = work_pref.indexOf(job);
    		for(int j = i + 1; j < test.size(); j++){
    			int job2 = test.get(j);
        		ArrayList<Integer> job_pref2 = job_prefer.get(j);
        		ArrayList<Integer> work_pref2 = work_prefer.get(job2);
        		int wrank2 = job_pref2.indexOf(j);
        		int jrank2 = work_pref2.indexOf(job2);
        		int comparerank1 = job_pref2.indexOf(i);
        		int comparerank2 = work_pref2.indexOf(job);
        		int comparerank3 = job_pref.indexOf(j);
        		int comparerank4 = work_pref.indexOf(job2);
        		boolean test1 = (comparerank1 < wrank2 && comparerank3 < wrank1);
        		boolean test2 = (comparerank2 < jrank2 && comparerank4 < jrank1); 
        		if(test1 && test2){
        			return false;
        		}
        		
    		}
    	}
    	return true;
    }

    /**
     * Determines a solution to the Stable Marriage problem from the given input
     * set. Study the project description to understand the variables which
     * represent the input to your solution.
     * 
     * @return A stable Matching.
     */
    public Matching stableHiringGaleShapley(Matching marriage) {
    	long start_time = System.nanoTime();
    	
    	ArrayList<Integer> the_hard = new ArrayList<Integer>();
    	ArrayList<Integer> the_lazy = new ArrayList<Integer>();
    	ArrayList<Boolean> hardworkers = marriage.getWorkerHardworking();
    	ArrayList<ArrayList<Integer>> workPrefer = marriage.getWorkerPreference();
    	ArrayList<ArrayList<Integer>> jobPrefer = marriage.getJobPreference(); 
    	ArrayList<Integer> the_full = new ArrayList<Integer>();
    	ArrayList<Integer> part_jobs = new ArrayList<Integer>();
    	ArrayList<Boolean> fulljobs = marriage.getJobFulltime();
    	ArrayList<contract> thecontracts = new ArrayList<contract>();
        Queue<worker> hard_queue = new LinkedList<worker>();
        Queue<worker> lazy_queue = new LinkedList<worker>();
        int almostanswer[] = new int[marriage.getJobCount()];
        for(int i = 0; i < jobPrefer.size(); i++){
        	contract acontract = new contract(i, jobPrefer.get(i));
        	thecontracts.add(acontract);
        }
        for(int i = 0; i < hardworkers.size(); i++){
        	if(hardworkers.get(i)){
        		the_hard.add(i);
        	}else{
        		the_lazy.add(i);
        	}
        	/*
        	if(fulljobs.get(i)){
        		the_full.add(i);
        	}else{
        		part_jobs.add(i);
        	}
        	*/
        }
        
        for(int i = 0; i < the_hard.size(); i++){
        	int aworker = the_hard.get(i);
        	hard_queue.add(new worker(aworker, workPrefer.get(aworker)));
        }
        for(int i = 0; i < the_lazy.size(); i++){
        	int aworker = the_lazy.get(i);
        	lazy_queue.add(new worker(aworker, workPrefer.get(aworker)));
        }
        while(hard_queue.size() > 0){ // need to add removed member to queue before we remove old
        	worker applicant = hard_queue.peek();
        	int target = applicant.nextJob();
        	contract thetarget = thecontracts.get(target);
        	if(thetarget.isHitched()){
        		worker traveler = thetarget.proposal(applicant);
        		if(!applicant.equals(traveler)){
        			hard_queue.add(traveler);
        			hard_queue.poll();
        		}
        	}else{
        		thetarget.firstProposal(applicant);
        		hard_queue.poll();
        	}
        }
        while(lazy_queue.size() > 0){
        	worker applicant = lazy_queue.peek();
        	int target = applicant.nextJob();
        	contract thetarget = thecontracts.get(target);
        	if(thetarget.isHitched()){
        		worker traveler = thetarget.proposal(applicant);
        		if(!applicant.equals(traveler)){
        			lazy_queue.add(traveler);
        			lazy_queue.poll();
        		}
        	}else{
        		thetarget.firstProposal(applicant);
        		lazy_queue.poll();
        	}
        }
        for(int i = 0; i < thecontracts.size(); i++){
        	contract currentcon = thecontracts.get(i);
        	int abride = currentcon.getJob();
        	int agroom = currentcon.getWorker();
        	almostanswer[agroom] = abride;
        }
        ArrayList<Integer> answer = new ArrayList<Integer>();
        for(int i = 0; i < almostanswer.length; i++){
        	answer.add(almostanswer[i]);
        }
        marriage.setWorkerMatching(answer);
        long end_time = System.nanoTime();
        long final_tim = (end_time - start_time);
        double  final_time = (double) final_tim; 
        final_time = final_time/1000000;
        System.out.print(final_time);
        return marriage;
    }
}
