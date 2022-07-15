package com.planit.challenges;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * Challenge 2:
Write a solution to find the character that has the highest number of occurrences within a certain string, ignoring
case. If there is more than one character with equal highest occurrences, return the character that appeared first
within the string.
For example:
Input: "Character"
Output: c
 */
public class ChallengeTwo {
	
	
    public static char maximumOccurence(String input) {
 
		input = input.toLowerCase();

		int maxOccur = 0;
		char result = '-';
		LinkedHashMap<Character, Integer> resultMap = new LinkedHashMap<Character, Integer>();
		for (Character letter : input.toCharArray()) {

			if (resultMap.containsKey(letter)) {
				// System.out.println("HashMap already contains letter="+letter);
				resultMap.put(letter, resultMap.get(letter) + 1);
			} else {
				resultMap.put(letter, 1);
			}

		}

		for (Map.Entry entry : resultMap.entrySet()) {

			if ((Integer) entry.getValue() > maxOccur) {

				result = (Character) entry.getKey();
				maxOccur = (Integer) entry.getValue();

			}

		}

		System.out.println("Character with Maximum Occurences in the word " + input + " is : " + result);
	return result;
    }
    
	public static void main(String[] args) {
		
		maximumOccurence(args[0]);
	}

		

}
