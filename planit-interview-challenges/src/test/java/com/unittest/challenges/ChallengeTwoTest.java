package com.unittest.challenges;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.planit.challenges.ChallengeTwo;

public class ChallengeTwoTest {

	@Test
	public void validateOccurences() {

		String input = "ABCACBBCAABCABC";

		char expected = 'a';
		char actual = ChallengeTwo.maximumOccurence(input);

		Assert.assertEquals(expected, actual);

	}

}
