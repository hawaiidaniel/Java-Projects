// Name: Daniel Li
// USC NetID: lidw
// CS 455 PA4
// Fall 2018

import java.util.*;

/**
   A Rack of Scrabble tiles
   This class contains 3 main method:
   1.getArray: execute the allSubsets method with k=0.
   2.allSubsets: finds all subsets of a unique string with multiset.
   3.getResult: compute scores for every word and display them
   				in order.
 */

public class Rack {
    
	private static final int k = 0;

	public ArrayList<String> getArray(String unique, int[] mult){
		return allSubsets(unique, mult, k);
	}
   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }
   
   /*Compute the scores of every word in the list and display
    * them in order*/
   public void getResult(ArrayList<String> list) {
	   
	   System.out.println("All of the words with their scores (sorted by score):");
	   /*create ScoreTable instance*/
	   ScoreTable table = new ScoreTable();
	   /*create a map to store the results, set the order reversely
	    * to display the scores in decreasing order*/
	   Map<Integer, ArrayList<String>> showMap = 
			   new TreeMap<Integer, ArrayList<String>>(Collections.reverseOrder());
	   /*create a ArrayList to store the corresponding words*/
	   ArrayList<String> showList;
	   
	   /*For every word in the list, change them to lowercase, compute the score.*/
	   for(String str: list) {
		   int score = table.computeScore(str.toLowerCase());
		   /*If the score of the word is the same as others, and exists in the map
		    * already, add the word to the ArrayList under the score that is the key*/
		   if(showMap.containsKey(score)) {
			   showList = showMap.get(score);
			   showList.add(str);
			   showMap.put(score, showList);
		   }
		   /*If not, put the score as the key and the word as the value in the map*/
		   else {
			   showList = new ArrayList<String>();
			   showList.add(str);
			   showMap.put(score, showList);
		   }
	   }
	   
	   /*For the words with the same score, sort them in alphabetical order*/
	   for(Map.Entry<Integer, ArrayList<String>> entry: showMap.entrySet()) {
		   Collections.sort(entry.getValue());
		   int key = entry.getKey();
		   ArrayList<String> temp = entry.getValue();
		   Iterator<String> it = temp.iterator();
		   /*Display the results with the score and word*/
		   while(it.hasNext()) {
			   System.out.println(key +": " + it.next());
		   }
	   }
   }

   
}
