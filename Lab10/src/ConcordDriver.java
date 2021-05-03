import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;
/**
 * Finds frequency of each word in a file.
 * Unlike the lecture version of this code, this one is smarter
 * about what it considers a word.
 *
 * Version for the lab.
 */

public class ConcordDriver {

   public static void main(String[] args) {

      Concord concord = new Concord();
      String str = "i am apple red apple red green blue i am red apple";
      Scanner in = new Scanner(str);
      
      concord.addData(in);		
		
      //concord.print(System.out);

      concord.printSorted(System.out);
      
//      Iterator<Map.Entry<String, Integer>> iter = concord.arr.iterator();
//      while(iter.hasNext()) {
//    	  System.out.println(concord.arr.iterator().next().getValue());
//      }
      
   }

}
