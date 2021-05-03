// Name: Daniel Li
// USC NetID: lidw
// CS 455 PA4
// Fall 2018

import java.util.*;
import java.io.*;

/**
 * WordFinder class: This is the main method to execute this program. It chooses the specific dictionary
 * to be used in this program. If the dictionary file does not exist, or there is other IO error, it will
 * print the error message. This class contains 2 methods, which are calculate and uniqueMulti.
 * Calculate: execute the whole program.
 * uniqueMulti: to get the unique string and its corresponding multiset.
 *
 */
public class WordFinder {
	
	/*Create 2 variables to store the unique string and the multiset.*/
	private static String unique = "";
	private static int[] mult;
	
	/*If user specifies the dictionary, the program will use it. Otherwise it will use "sowpods.txt"
	 * as the dictionary.*/
	public static void main(String[] args) throws IOException{
		String dict;
		try {
					
			if(args.length > 0) {
				dict = args[0];				
				calculate(dict);
			}
			else {
				dict = "sowpods.txt";				
				calculate(dict);
			}
			
		}
		/*If the program catches any error below, it will print the messages.*/
		catch(FileNotFoundException e) {
			System.out.println("Dictionary does not exist.");
		}
		catch(IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
	}
	
	/**
	 * To execute the whole program.
	 * @param dict the dictionary to be used.
	 * @throws IOException throws the exception if exists.
	 */
	public static void calculate(String dict) throws IOException{
		/*create an AnagramDictionary instance with the file*/
		AnagramDictionary ana = new AnagramDictionary(dict);
		System.out.println("Type . to quit.");
        /*create a Scanner instance*/
        Scanner in = new Scanner(System.in);
        /*Initiate run to be true. While the user input '.', it will be set to false
		 * and the program will be terminated.*/
		boolean run = true;
		
		while(run) {
			System.out.print("Rack? ");
			/*create rawString to store what the user inputs.*/
            String rawString = in.nextLine();

			/*Terminate the program if user inputs '.'*/
			if(rawString.equals(".")) {
				run = false;
				break;
			}
			
			/*create inString to store the clean version of the word, which is without any special character.*/
			String inString = rawString.replaceAll("[^a-zA-Z]+", "");
			/*Sort the inString in alphabetical order.*/
			inString = AnagramDictionary.sortString(inString);
			/*Execute the uniqueMulti method to get the unique string and the multiset.*/
			uniqueMulti(inString);
			
			/*With the unique string and multiset, get all the valid anagrams and store them in validStr.*/
			Rack rack = new Rack();
			ArrayList<String> combo = rack.getArray(unique, mult);
			Iterator<String> iter = combo.iterator();
			ArrayList<String> validStr = new ArrayList<String>();
			while(iter.hasNext()) {
				/*Add all the anagrams into validStr if it's not blank.*/
				ArrayList<String> temp = ana.getAnagramsOf(iter.next());
				if(temp.size() == 0) {
					continue;
				}
				else {
					validStr.addAll(temp);
				}
			}
			/*Print out the result.*/
			System.out.println("We can make " + validStr.size() + " from \"" + rawString + "\"");
			/*Run the getResult method only if it can find 1 or more words.*/
			if(validStr.size() > 0) {
				rack.getResult(validStr);
			}
			/*Reset the unique string to be blank.*/
			unique = "";
            
		}
        /*close the scanner*/
        in.close();
	}
	
	/**
	 * This method get the unique string and the multiset with String s.
	 * @param s the clean and sorted version of the word that user inputs.
	 */
	public static void uniqueMulti(String s) {
		/*convert the string to characters.*/
		char[] chars = s.toCharArray();
		/*create a map to store the characters*/
		Map<Character, Integer> cmap = new HashMap<Character, Integer>();
		/*If the character doesn't exist in the map, add it and put the value to be 1.
		 * Otherwise, add 1 to the value.*/
		for(char c: chars) {
			if(cmap.containsKey(c)) {
				cmap.put(c, cmap.get(c)+1);
			}
			else {
				cmap.put(c, 1);
				unique += c;
			}
		}
		
		/*get the corresponding multise from the map.*/
		mult = new int[unique.length()];
		for(int i = 0; i < unique.length(); i++) {
			mult[i] = cmap.get(unique.charAt(i));
		}
		
	}
	
}
