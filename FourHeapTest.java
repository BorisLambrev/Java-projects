package ca.mcgill.cccs315.assignment3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FourHeapTest {
	private FourHeap<Integer, Integer> heap;

	@BeforeEach
	void setUp() throws Exception {
		heap = new FourHeap<Integer, Integer>();
	}

	@Test
	void testUp() {
		for(int i = 1; i <= 10; i++) {
			heap.insert(i, i);
		}
		assertEquals(10, heap.size());
		for(int i = 1; i <= 10; i++) {
			assertEquals(i, heap.removeMin().getValue());
		}
	}
	
	@Test
	void testDown() {
		for(int i = 10; i >= 1; i--) {
			heap.insert(i, i);
		}
		assertEquals(10, heap.size());
		for(int i = 1; i <= 10; i++) {
			assertEquals(i, heap.removeMin().getValue());
		}
	}
	
	@Test
	void testIt() {
		for(int i = 10; i >= 1; i--) {
			heap.insert(i, i);
			System.out.println(heap.size());
		}
		assertEquals(10, heap.size());
		for(int i = 1; i <= 10; i++) {
			assertEquals(i, heap.removeMin().getValue());
		}
	}
}
