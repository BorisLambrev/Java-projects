package ca.mcgill.cccs315.assignment3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnSortedPriorityQueueQueueTest {

	private UnSortedPriorityQueueQueue<Integer> queue;

	@BeforeEach
	void setUp() throws Exception {
		queue = new UnSortedPriorityQueueQueue<>();
	}

	@Test
	void testStack() {
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		assertEquals(1, queue.first());
		assertEquals(1, queue.dequeue());
		assertEquals(2, queue.dequeue());
		assertEquals(3, queue.dequeue());
	}

}
