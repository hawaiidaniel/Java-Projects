// Name: Daniel Li
// USC NetID: lidw
// CSCI455 PA2
// Fall 2018


/**
   <add main program comment here>
*/

import java.util.*;
public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {
      
      boolean singleStep = false;
      boolean userConfig = true;
      /*create a new object trial*/
      SolitaireBoard trial;
      /*create a Scanner object in*/
      Scanner in = new Scanner(System.in);
      
      /*check if it need singleStep and userConfig*/
      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }
      
      /*in the userConfig mode*/
      if (userConfig == true) {
    	  
    	  /*print out initial message*/
    	  System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
    	  System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
    	  System.out.println("Please enter a space-separated list of positive integers followed by newline:");
    	  
    	  /*variable declaration*/
    	  Scanner in2 = null;
    	  ArrayList<Integer> arrayInput = new ArrayList<Integer>();
    	  
    	  /*check if input has next line*/
    	  if (in.hasNextLine()) {
    		  /*let inputString = user's input*/
    		  String inputString = in.nextLine();
    		  /*let in2 read from inputString*/
    		  in2 = new Scanner(inputString);
    		  
    		  /*variable declaration*/
    		  boolean equalCardNum = false;
    		  /*check if the configuration is equal to CARD_TOTAL*/
    		  while(!equalCardNum) {
	    		  /*loop while in2 has another token*/
	    		  while (in2.hasNext()) {
	    			  /*Check if in2 has another integer*/
	    			  if (in2.hasNextInt()) {
	    				  /*let a = the integer*/
	    				  int a = in2.nextInt();
	    				  /*Check if the integer out of range*/
	    				  if (a <= 0 || a > SolitaireBoard.CARD_TOTAL) {
	    					  
	    					  /*if out of range then run the error printing method*/
	    					  BulgarianSolitaireSimulator.printErrorMsg();
	    					  /*clear the arrayInput*/
	        	        	  arrayInput.clear();
	        	        	  /*let user input again*/
	        	        	  inputString = in.nextLine();
	        	        	  /*let in2 read from inputString again, then go back to while loop*/
	        	    		  in2 = new Scanner(inputString);
	    				  }
	    				  /*if the integer in the range then add it to the arrayInput*/
	    				  else {
	    					  arrayInput.add(a);
	    				  }
	    				  
	    			  }
	    			  /*if in2's next token is not integer*/
	    			  else {
	    				  
	    				  /*then run the error printing method*/
	    				  BulgarianSolitaireSimulator.printErrorMsg();
	    				  /*clear the arrayInput*/
	    	        	  arrayInput.clear();
	    	        	  /*let user input again*/
	    	        	  inputString = in.nextLine();
	    	        	  /*let in2 read from inputString again, then go back to while loop*/
	    	    		  in2 = new Scanner(inputString);
	    			  }	    			  
	    			  
	    		  }
	    		  /*variable declaration*/
	    		  int arraySum = 0;
    			  /*count the sum of all values in the arrayInput and assign it to arraySum*/
    			  for (int object: arrayInput) {
    				  arraySum += object;
    			  }
    			  
    			  /*check if arraySum out of range, if so do the following*/
    			  if (arraySum != SolitaireBoard.CARD_TOTAL) {
    				      				  
    				  /*then run the error printing method*/
    				  BulgarianSolitaireSimulator.printErrorMsg();
    				  /*clear the arrayInput*/
    				  arrayInput.clear();
    				  /*let user input again*/
    	        	  inputString = in.nextLine();
    	        	  /*let in2 read from inputString again, then go back to while loop*/
    	    		  in2 = new Scanner(inputString);
    			  }
    			  else {
    				  /*let user input again if input not equal to CARD_TOTAL*/
    				  equalCardNum = true;
    			  }
	    		  
    		  }
    		  
    	  }
    	  /*pass the arrayInput as parameter for the SolitaireBoard*/
    	  trial = new SolitaireBoard(arrayInput);
      }
      /*here userConfig is false and run the SolitaireBoard with random configuration*/
      else {
    	  trial = new SolitaireBoard();
      }

      // <add code here>
      /*check if singleStep if false*/
      if (singleStep == false) {
    	  /*run the playTillDone method with trial*/
    	  playTillDone(trial);
    	  
      }
      else {
    	  /*singleStep is true  and run the playOneRound method with trial and in*/
    	  playOneRound(trial, in);
      }
      
      
         

   }
   
   // <add private static methods here>
   /*private method to play one round, with parameter SolitaireBoard game and Scanner in*/
   private static void playOneRound(SolitaireBoard game, Scanner in) {
	   
	   /*print the initial configuration*/
	   System.out.print("Initial configuration: " + game.configString());
	   
	   /*local variable declaration*/
	   int step = 1;
	   /*Check if the game is done with loop*/
	   while (!game.isDone()) {
		   /*set in.nextLine here so we can hit enter and the next step would be printed next line*/
		   in.nextLine();
		   /*run playRound method*/
		   game.playRound();
		   /*print the current configuration*/
		   System.out.println("[" + step + "] Current configuration: " + game.configString());
		   /*print message*/
		   System.out.print("<Type return to continue>");
		   /*record how many step we run so far*/
		   step++;
	   }
	   /*if the game is done then next line print 'Done'*/
	   System.out.println("");
	   System.out.println("Done!");
   }

   
   /*private method to play till the game done, with parameter SolitaireBoard game*/
   private static void playTillDone(SolitaireBoard game) {
	   
	   /*print the initial configuration*/
	   System.out.println("Initial configuration: " + game.configString());
	   /*variable declaration*/
	   int step = 1;
	   /*Check if the game is done*/
	   while (!game.isDone()) {
		   /*run the playRound method*/
		   game.playRound();
		   /*print current configuration*/
		   System.out.println("[" + step + "] Current configuration: " + game.configString());
		   /*record how many step we run so far*/
		   step++;
	   }
	   /*print 'done' when game is done*/
	   System.out.println("Done!");
   }
   
   /*private method to print error message*/
   private static void printErrorMsg() {
	   System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "+SolitaireBoard.CARD_TOTAL);
 	   System.out.println("Please enter a space-separated list of positive integers followed by newline:");
   }
}
