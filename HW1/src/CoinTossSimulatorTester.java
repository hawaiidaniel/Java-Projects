// Name: Daniel Li
// USC NetID: lidw@usc.edu
// CS 455 PA1
// Fall 2018


//This CoinTossSimulatorTester class tests the results from the simulation of CoinTossSimulator.
public class CoinTossSimulatorTester {
	
	//This method here checks the numTrials = TwoHead + TwoTail + HeadTail. If it's correct, it prints "True"; if not, it prints "False".
	public static void SumTrials(int numTrial, int TwoHead, int TwoTail, int HeadTail) {
		if (numTrial == (TwoHead + TwoTail + HeadTail)) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		
	}
	
	
	public static void main(String[] args) {
		
		//Creating CoinTester as object of CoinTossSimulator
		CoinTossSimulator CoinTester = new CoinTossSimulator();
		
		//testing 0 trial
		CoinTester.run(0);
	    System.out.println("After Constructor:");
	    System.out.print("Number of Trials [exp:0]: ");
	    System.out.println(CoinTester.getNumTrials());
	    System.out.print("Number of Two-Heads: ");
	    System.out.println(CoinTester.getTwoHeads());
	    System.out.print("Number of Two-Tails: ");
	    System.out.println(CoinTester.getTwoTails());        
	    System.out.print("Number of Head-Tails: ");
	    System.out.println(CoinTester.getHeadTails());        
	    System.out.print("Number of Trials correct?: ");
	    CoinTossSimulatorTester.SumTrials(CoinTester.getNumTrials(), CoinTester.getTwoHeads(), CoinTester.getTwoTails(), CoinTester.getHeadTails());
	    System.out.println();
		
	    //creating an array of integers for testing
	    int exp = 0;
		int[] list = {1, 10, 100, 500, 1000};
		for (int i = 0; i < list.length; i++) {
			
			CoinTester.run(list[i]);
			exp += list[i];
		    System.out.println("After run(" + list[i] +"):");
		    System.out.print("Number of Trials: [exp:" + exp + "] ");
		    System.out.println(CoinTester.getNumTrials());
		    System.out.print("Number of Two-Heads: ");
		    System.out.println(CoinTester.getTwoHeads());
		    System.out.print("Number of Two-Tails: ");
		    System.out.println(CoinTester.getTwoTails());        
		    System.out.print("Number of Head-Tails: ");
		    System.out.println(CoinTester.getHeadTails());        
		    System.out.print("Number of Trials correct?: ");
		    CoinTossSimulatorTester.SumTrials(CoinTester.getNumTrials(), CoinTester.getTwoHeads(), CoinTester.getTwoTails(), CoinTester.getHeadTails());
		    System.out.println();
		}
		
		
		//Use the reset method to reset all the numbers to 0
		System.out.println("After reset:");
		CoinTester.reset();
		exp = 0;
		int[] list2 = {100, 250, 750, 1000};
		CoinTester.run(0);
	    System.out.print("Number of Trials [exp:0]: ");
	    System.out.println(CoinTester.getNumTrials());
	    System.out.print("Number of Two-Heads: ");
	    System.out.println(CoinTester.getTwoHeads());
	    System.out.print("Number of Two-Tails: ");
	    System.out.println(CoinTester.getTwoTails());        
	    System.out.print("Number of Head-Tails: ");
	    System.out.println(CoinTester.getHeadTails());        
	    System.out.print("Number of Trials correct?: ");
	    CoinTossSimulatorTester.SumTrials(CoinTester.getNumTrials(), CoinTester.getTwoHeads(), CoinTester.getTwoTails(), CoinTester.getHeadTails());
	    System.out.println();
	    
	    //Creating another array of integers for testing
		for (int i = 0; i < list2.length; i++) {
					
			CoinTester.run(list2[i]);
			exp += list2[i];
		    System.out.println("After run(" + list2[i] +"):");
		    System.out.print("Number of Trials: [exp:" + exp + "] ");
		    System.out.println(CoinTester.getNumTrials());
		    System.out.print("Number of Two-Heads: ");
		    System.out.println(CoinTester.getTwoHeads());
		    System.out.print("Number of Two-Tails: ");
		    System.out.println(CoinTester.getTwoTails());        
		    System.out.print("Number of Head-Tails: ");
		    System.out.println(CoinTester.getHeadTails());        
		    System.out.print("Number of Trials correct?: ");
		    CoinTossSimulatorTester.SumTrials(CoinTester.getNumTrials(), CoinTester.getTwoHeads(), CoinTester.getTwoTails(), CoinTester.getHeadTails());
		    System.out.println();
		}

	}
}
