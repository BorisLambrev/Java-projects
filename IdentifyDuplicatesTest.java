package ca.mcgill.ccccs315.assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class IdentifyDuplicatesTest {

	@Test
	void test1() {
		String result = "[1, 2, 3]"; 
		int[] list1 = {1, 1, 2, 2, 3, 3};
		
		ArrayList<Integer> dups = IdentifyDuplicates.identifyDuplicates(list1);
		assertEquals(dups.toString(), result);
	}
	
	@Test
	void test2() {
		String result = "[1]"; 
		int[] list1 = {1, 1, 1, 1};
		
		ArrayList<Integer> dups = IdentifyDuplicates.identifyDuplicates(list1);
		assertEquals(dups.toString(), result);
	}
	
	@Test
	void test3() {
		String result = "[]";
		int[] list1 = {1, 2, 3, 4};		
		ArrayList<Integer> dups = IdentifyDuplicates.identifyDuplicates(list1);
		assertEquals(dups.toString(), result);
	}
}
