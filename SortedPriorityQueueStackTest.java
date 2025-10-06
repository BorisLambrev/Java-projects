package ca.mcgill.cccs315.assignment3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedPriorityQueueStackTest {
	private SortedPriorityQueueStack<Integer> stack;

	@BeforeEach
	void setUp() throws Exception {
		stack = new SortedPriorityQueueStack<>();
	}

	@Test
	void testStack() {
		stack.push(1);
		stack.push(2);
		stack.push(3);
		assertEquals(3, stack.top());
		assertEquals(3, stack.pop());
		assertEquals(2, stack.pop());
		assertEquals(1, stack.pop());
	}
}
