package ca.mcgill.cccs315.assignment3;
import net.datastructures.UnsortedPriorityQueue;

/**
 * An implementation of a queue using the an unsorted priority queue
 * @param <V> The type of content held in the queue
 */
public class UnSortedPriorityQueueQueue<V> {
	
	//Basically, similar coding like in SortedPriorityQueueStack except I didn't add LastValue() method since we don't need it
	//Queue doesn't call a last element when it deletes an element nor wants the last element on the first() method
	UnsortedPriorityQueue<Integer, V> uSPQ = new UnsortedPriorityQueue<>();
	
	int keyCounter = 0;
	
	int qty = 0;
	
	/**
	 * Construct an initially empty queue
	 */
	public UnSortedPriorityQueueQueue() {
		
	}
	
	/**
	 * Enqueue value onto the queue
	 * Big-O analysis: Constant function O(1), if we enqueue/add a value onto a queue its the same time complexity as adding the value in
	 * any place in the queue. 
	 * 
	 * @param value The element to be pushed on to the queue
	 */
	public void enqueue(V value) {
		
		keyCounter++;
		uSPQ.insert(keyCounter, value);
		qty++;
	}
	
	/**
	 * Remove the `oldest` element from the queue
	 * Big-O analysis: Constant function O(1), if we remove the oldest element its really constant operation or task. Because the program
	 * goes from left to right to read the queue values. 1, 2, 3, 4, 5. Every 1st element will be removed without needing to reach to 
	 * the end of the list of values like in the case of a stack where we need to go all the way to the end to remove an element.
	 * 
	 * @return The first element that was added to the queue.
	 */
	public V dequeue() {
		
		if(uSPQ.isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		
		qty--;
		
		return uSPQ.removeMin().getValue();//FIFO
	}
	
	/**
	 * Look at but do not remove  the `oldest` element from the queue
	 * @return The first element that was added to the queue.
	 */
	public V first() {
		
		if(uSPQ.isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		
		return uSPQ.min().getValue();
	}
	
	/**
	 * 
	 * @return The size of the stack
	 */
	public int size() {
		return qty;
	}
	
	/**
	 * 
	 * @return True if the stack is empty. False otherwise.
	 */
	public boolean isEmpty() {
		return qty == 0;
	}
	
	
}