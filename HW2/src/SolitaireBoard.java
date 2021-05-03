// Name: Daniel Li
// USC NetID: lidw
// CSCI455 PA2
// Fall 2018

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/
import java.util.*;

public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   
   /**
      Representation invariant:

      *All elements in the array should be greater than or equal to 0, and less than or equal to 45;
      *The sum of the array elements should be equal to 45;
      *The card number of each pile is located in the array where array[i-1] (eg. The first pile is located in the 0th of the array.

   */
   
   // <add instance variables here>
   /**partially filled array 'array' is created and its size is equal to CARD_TOTAL**/
  private int[] array = new int[CARD_TOTAL];
  /**create a new int temp and pile**/
  private int temp, pile;
  /**create a new Random instance rand**/
  Random rand = new Random();
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
	  
	   /*pass the elements of piles into array */
	  for(int i = 0; i < piles.size(); i++) {
		  array[i] = piles.get(i);
	  }
	  /*let pile = the size of piles*/
	  pile = piles.size(); 
      assert isValidSolitaireBoard(array);  
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   //let temp = the CARD_TOTAL number
	   temp = CARD_TOTAL;
	   
	   /*creating a random configuration with random size*/
	   for (int i = 0; i < CARD_TOTAL && temp > 0; i++) {
		   
		   /*initially the first number can be [1, CARD_TOTAL(included)]*/
		   array[i] = rand.nextInt(temp) + 1;
		   /*for each loop the temp would be deducted until it's equal to 0*/
		   temp = temp - array[i];
		   /*let pile = the size of the random configuration */
		   pile = i + 1;
	   }
	   assert isValidSolitaireBoard(array);
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
	   
	   /*local variable declaration*/
	   int[] array_temp = new int[CARD_TOTAL];
	   int temp_index = 0;
	   
	   for (int i = 0; i < pile; i++) {
		   
		   /*for each loop every element would be minus 1*/
		   array[i] = array[i] - 1;
		   /*use the array_temp to store the element*/
		   /*if element = 0 then the next element would replace so no need to store*/
		   if (array[i] != 0) {
			   array_temp[temp_index] = array[i];
			   /*update the size of array_temp*/
			   temp_index++;
		   }
	   }
	   /*let the last element of array_temp = the sum of card taken out*/
	   array_temp[temp_index] = pile;
	   /*update the pile number*/
	   pile = temp_index+1;
	   /*When finish loop, the array would store the current elements*/
	   array = array_temp;
	   
	   assert isValidSolitaireBoard(array);
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */
   
   public boolean isDone() {
	   /*local variable declaration*/
	   boolean done = true;
	   int[] array_temp = new int[CARD_TOTAL + 1];
	   
	   /*for each element in the array, let the array_temp[element]++*/
	   /*If it's done, the array_temp[1..9] should be equal to 1 individually*/
	   /*eg. if array[1]=5, array_temp[5]++*/
	   for (int i = 0; i < pile; i++) {
		   array_temp[array[i]]++;
	   }
	   
	   /*Check if each array_temp[1...NUM_FINAL_PILES] = 1, if not then not done yet*/
	   for (int j = 1; j < (NUM_FINAL_PILES + 1); j++) {
		   if (array_temp[j] != 1) {
			   done = false;
			   break;
		   }
	   }
	   
	   assert isValidSolitaireBoard(array);
	   return done;
	   
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
	   /*local variable declaration*/
	   String s;
	   String result = "";
	   
	   /*convert the int element in the array to string with a space, from first to last second*/
	   for (int i = 0; i < pile-1; i++) {
		   s = "" + array[i];
		   result += s+" ";
	   }
	   /*add the string of last element into the s, without space*/
	   s = "" + array[pile-1];
	   return result + s;
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard(int[] piles) {
      /*local variable declaration*/
      boolean valid = true;
      int sum = 0;
      
      
      for (int i = 0; i < NUM_FINAL_PILES; i++) {
    	  /*return false if element out of the range*/
    	  if (piles[i] < 0 || piles[i] > CARD_TOTAL) {
    		  
    		  valid = false;
    	  }
    	  /*denote sum as the sum of all elements*/
    	  sum += piles[i];
    	  
      }
      /*return false if sum != CARD_TOTAL*/
      if (sum != CARD_TOTAL) {
    		  valid = false;
    	  }  
      return valid;

   }
   

   // <add any additional private methods here>

}
