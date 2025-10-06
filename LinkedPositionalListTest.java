package ca.mcgill.ccccs315.assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LinkedPositionalListTest {
	LinkedPositionalList<Integer> unsorted;
	
	@BeforeEach
	void setUp() throws Exception {
		unsorted = new LinkedPositionalList<Integer>();
	}

	@Test
	void testSort1() {
		unsorted.addLast(1);
		unsorted.addLast(2);
		unsorted.addLast(3);
		unsorted.sort();
		assertEquals("(1, 2, 3)", unsorted.toString());
	}
	
	@Test
	void testSort2() {
		unsorted.addLast(3);
		unsorted.addLast(2);
		unsorted.addLast(1);
		unsorted.sort();
		assertEquals("(1, 2, 3)", unsorted.toString());
	}
	
	@Test
	void testSort3() {
		unsorted.addLast(1);
		unsorted.addLast(1);
		unsorted.addLast(1);
		unsorted.sort();
		assertEquals("(1, 1, 1)", unsorted.toString());
	}
	
	@Test
	void testSort4() {
		unsorted.addLast(1);
		unsorted.sort();
		assertEquals("(1)", unsorted.toString());
	}

}
