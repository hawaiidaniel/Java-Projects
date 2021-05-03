import java.util.Scanner;
import java.util.Map;

/**
 * Finds frequency of each word in a file.  
 * (Version for Exercise 3.)
 *
 * Optional command line argument is the threshold for the number of
 * letters a word must have to be printed with its number of occurrences.
 *
 * If argument isn't given, prints all words and number of occurrences.
 *
 */

public class ConcordDriver3 {

   public static int num;
   public static void main(String[] args) {

      int threshold = 0;

      if (args.length > 0) {
         threshold = Integer.parseInt(args[0]);
      }

      Concord concord = new Concord();
		
      Scanner in = new Scanner("i am apple red apple red green blue i am red apple");
		
      concord.addData(in);		
		

      // add code here to print out the selected entries
      num = threshold;
      LargeWordPred testLarge = new LargeWordPred();
	  concord.printSatisfying(System.out, testLarge);
   }

}


// add new class here
class LargeWordPred implements Predicate{
	
	int a = ConcordDriver3.num;
	public boolean predicate(Map.Entry<String,Integer> item) {
		if(item.getValue() >= a) {
			return true;
		}
		else {
			return false;
		}
	}
}