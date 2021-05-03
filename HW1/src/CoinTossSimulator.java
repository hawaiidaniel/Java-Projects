// Name: Daniel Li
// USC NetID: lidw@usc.edu
// CS 455 PA1
// Fall 2018

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;
public class CoinTossSimulator {
	
	//instance variable declaration
	//numTrials: number of trials
	//TwoHeads: number of 2 heads
	//TwoTails: number of 2 tails
	//HeadTails: number of one head and one tail
	private int numTrials, TwoHeads, TwoTails, HeadTails;

   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {
	   
	   //let all the variables to be 0
	   numTrials = 0;
	   TwoHeads = 0;
	   TwoTails = 0;
	   HeadTails = 0;

   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
	   
	   //for-loop starts from i=1, each loop i=i+1, until i = numTrials
	   for (int i = 1; i <= numTrials; i++) {
		   
		   //create object of class Random
		   Random rand = new Random();
		   //declare integer variables rand1 and rand2 and let them to be random integer 0 or 1
		   int rand1 = rand.nextInt(2);
		   int rand2 = rand.nextInt(2);
		   //declare integer variable result and let it to be rand1 + rand2
		   int result = rand1 + rand2;
		   
		   //for each loop, if result = 0, number of TwoHeads plus 1; if result = 1, number of 
		   //HeadTails plus 1; if result = 2, number of TwoTails plus 1
		   if (result == 0) {
			   TwoHeads++;
		   } else if (result == 1) {
			   HeadTails++;
		   } else {
			   TwoTails++;
		   }
		   
	   }
	   //after for-loop, the total number of numTrials will plus the numTrials from this run method
	   this.numTrials = this.numTrials + numTrials;
   }
	  


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return numTrials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return TwoHeads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return TwoTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return HeadTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   
	   numTrials = 0;
	   TwoHeads = 0;
	   TwoTails = 0;
	   HeadTails = 0;   

   }

}
