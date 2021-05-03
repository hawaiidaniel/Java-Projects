// Name: Daniel Li
// USC NetID: lidw
// CS 455 PA4
// Fall 2018

import java.util.*;

/**
 * ScoreTable class: This class stores the score of each character in lower-case 
 *in a map. It contains a method computeScore. With the input word, it compute
 *the score of this word and return it.
 */
public class ScoreTable {
	
	/*create a map to store the scores of each character.*/
	private Map<Character, Integer> points;
	/**
	 * @param points A tree map to store the score of each character.
	 */
	public ScoreTable() {
		
		points = new TreeMap<Character, Integer>();
		/*Put the corresponding score of the character into the map.*/
		points.put('a', 1);
		points.put('e', 1);
		points.put('i', 1);
		points.put('o', 1);
		points.put('u', 1);
		points.put('l', 1);
		points.put('n', 1);
		points.put('s', 1);
		points.put('t', 1);
		points.put('r', 1);
		points.put('d', 2);
		points.put('g', 2);
		points.put('b', 3);
		points.put('c', 3);
		points.put('m', 3);
		points.put('p', 3);
		points.put('f', 4);
		points.put('h', 4);
		points.put('v', 4);
		points.put('w', 4);
		points.put('y', 4);
		points.put('k', 5);
		points.put('j', 8);
		points.put('x', 8);
		points.put('q', 10);
		points.put('z', 10);
	}
	
	/**
	 * Compute the score of the word.
	 * @param word A word that is going to be computed.
	 * @return An integer that is the score of the word.
	 */
	public int computeScore(String word) {
		int temp;
		int score = 0;
		/*Sum up the scores of every character by loop*/
		for(char c: word.toCharArray()) {
			temp = points.get(c);
			score += temp;
		}
		return score;
	}
	
}
