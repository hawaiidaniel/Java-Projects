// Name: Daniel Li
// USC NetID: lidw
// CS 455 PA4
// Fall 2018

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {
   
	//create this map to store the anagrams for each unique string
	Map<String, ArrayList<String>> anagram;
   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
	   
	   /*Read the indicated dictionary*/
	   anagram = new HashMap<>();
	   File file = new File(fileName);
	   Scanner scanner = new Scanner(file);
	   
	   /*Read each line of the dictionary*/
	   while(scanner.hasNextLine()) {
		   ArrayList<String> strList = new ArrayList<String>();
		   String word = scanner.nextLine();
		   /*sort each word in alphabetical order*/
		   String sortedWord = sortString(word);
		   /*use the sorted version of the word as the key in the map*/
		   if(anagram.containsKey(sortedWord)) {
			   /*add the word to the ArrayList
			    *if the map contains the sorted word*/
			   anagram.get(sortedWord).add(word);
		   }
		   else {
			   /*add the sorted word as key in the map
			    * and add the word as the first value*/
			   strList.add(word);
			   anagram.put(sortedWord, strList);
		   }
	   }
   }
    /*A public method that sort the input string in alphabetical order*/
	public static String sortString(String inputString) 
    { 
        // convert input string to char array 
        char temp[] = inputString.toCharArray();         
        // sort tempArray 
        Arrays.sort(temp);      
        // return new sorted string 
        return new String(temp); 
    }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
	   /*create an ArrayList to store the anagrams*/
	   ArrayList<String> list = new ArrayList<String>();
	   /*return all value where the key is s,
	    * make the length of s > 0 to avoid any blank value*/
	   if(anagram.containsKey(s) && s.length() > 0) {
		   return anagram.get(s);
	   }
	   return list;
   }
   
   
}
