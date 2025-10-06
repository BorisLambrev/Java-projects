package ca.mcgill.cccs315.assignment1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyLinkedListTest {
	private MyLinkedList list;
	private MyLinkedList list2;

	@BeforeEach
	void setUp() throws Exception {
		list = new MyLinkedList();
		list2 = new MyLinkedList();
	}
	
	@Test
	public void testAdd() {
		list.add(5);
        list.add(10);
        list.add(15);
        assertEquals("5 10 15", list.toString());
	}
	
	@Test
	public void testAddArray() {
		int[] arr = {5, 10, 15};
		list.add(arr);
        assertEquals("5 10 15", list.toString());
	}
	
	@Test
	public void testAddList() {
		list2.add(5);
        list2.add(10);
        list2.add(15);
		list.add(list2);
        assertEquals("5 10 15", list.toString());
	}

	@Test
	public void testRemove() {
		list.add(5);
        list.add(10);
        list.add(15);
        list.remove(10);
        assertEquals("5 15", list.toString());
	}
	
	@Test
	public void testGet() {
		list.add(5);
        list.add(10);
        list.add(15);
        assertEquals(10, list.get(1));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
        list.add(5);
        assertFalse(list.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, list.size());
        list.add(5);
        assertEquals(1, list.size());
        list.add(10);
        assertEquals(2, list.size());
	}
	
	@Test
	public void testEquals() {
		assertTrue(list.equals(list));
		
		list.add(5);
		list.add(10);
		list2.add(5);
		list2.add(10);
		assertTrue(list.equals(list2));
	}
}
