package ca.mcgill.cccs315.assignment3;

/**
 * ****** Answers to this part's questions *******
 * 
 * 1. On inserts, do you expect net.datastructures.HeapPriorityQueue or FourHeap to perform better? Explain why.
 * 
 * -HeapPriorityQueue would perform better on inserts then FourHeap, because it will have less nodes to go through, it will be able to 
 * navigate from the biggest index of the array list or the last value all the way up to the root quicker. It won't have to turn or shift 
 * through more nodes. All the up heap and down heap will take longer with four heap, going through all those swapping's. 
 * -The other reason is, the more values we have in a FourHeap we will have to figure out more and more 
 * which number to insert to respect the heap-order. There can only be so many numbers used already to build a FourHeap and now inserting 
 * a value, we need to compare it to let's say many parents depending if FourHeap has a big number of parent's with four child's. It's
 * like we get more condensed on choosing the right number or value that fits for all nodes all the way up to the root to respect the 
 * heap-order. 
 *
 * 
 * 2. On remove, do you expect net.datastructures.HeapPriorityQueue or FourHeap to perform better? Explain why.
 * // Complete me
 * 
 * -HeapPriorityQueue would perform better on removing then FourHeap, if we want to remove the root or any value and swap values
 * all the way to the bottom of our FourHeap it will take more time than the HeapPriorityQueue. However it can depend on how
 * many height levels both have. But still if all internal nodes had 4 child's each it would be pretty long. 
 * -Also, both add and remove in a heap take log(n) time, if we have a FourHeap, we have better chances to have a longer and bigger
 * n, in the case if each parent had 4 child's instead of 2. a bigger n size for an array list can take more time for primitive operations
 * to go through. Even worst if we use a linkedlist instead of an array list to make a heap, where we must go from value after value to get 
 * to a specific value.  
 * 
 */

import java.util.ArrayList;
import java.util.Comparator;
import net.datastructures.AbstractPriorityQueue;
import net.datastructures.Entry;

public class FourHeap<K,V> extends AbstractPriorityQueue<K,V> {
	/** primary collection of priority queue entries */
	protected ArrayList<Entry<K,V>> heap = new ArrayList<>();

	/** Creates an empty priority queue based on the natural ordering of its keys. */
	public FourHeap() { 
		super(); 
	}

	/**
	 * Creates an empty priority queue using the given comparator to order keys.
	 * @param comp comparator defining the order of keys in the priority queue
	 */
	public FourHeap(Comparator<K> comp) { super(comp); }

	/**
	 * Creates a priority queue initialized with the respective
	 * key-value pairs.  The two arrays given will be paired
	 * element-by-element. They are presumed to have the same
	 * length. (If not, entries will be created only up to the length of
	 * the shorter of the arrays)
	 * @param keys an array of the initial keys for the priority queue
	 * @param values an array of the initial values for the priority queue
	 */
	public FourHeap(K[] keys, V[] values) {
		super();
		for (int j=0; j < Math.min(keys.length, values.length); j++)
			heap.add(new PQEntry<>(keys[j], values[j]));
		heapify();
	}

	// protected utilities
	//Binary tree or Heap is log(n) log base 2, here with Four Heap I logically used log base 4 to get the return values. 
	//But mostly, I figured out the algorithm for parent, left, right, third child and fourth child by drawing. With drawing 
	//A Four Heap and it's ArrayList, I was able to see and connect how a parent will be connected with the child's. All 2's changed to 4.
	protected int parent(int j) {
		return (j-1) / 4;
	}
	protected int left(int j) {
		return 4*j + 1;
	}
	protected int right(int j) {
		return 4*j + 2;
	}
	protected int thirdChild(int j) {
		return 4*j + 3;
	}
	protected int fourthChild(int j) {
		return 4*j + 4;
	}
	protected boolean hasLeft(int j) {
		return left(j) < heap.size();
	}
	protected boolean hasRight(int j) {
		return right(j) < heap.size();
	}
	protected boolean hasThirdChild(int j) {
		return thirdChild(j) < heap.size();
	}
	protected boolean hasFourthChild(int j) {
		return fourthChild(j) < heap.size();
	}

	/** Exchanges the entries at indices i and j of the array list. */
	protected void swap(int i, int j) {
		Entry<K,V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	/**
	 * Moves the entry at index j higher, if necessary, to restore the heap
	 * property.
	 */
	protected void upheap(int j) {

		// Re-use of the upheap in HeapPriorityQueue, it did not involve any left, right, 3rd or fourth child just the parent,
		// assuming it's correct to re-apply the same upheap(). Both FourHeap and HeapPriorityQueue have parents, so I trust its enough.
		while (j > 0) {
			int p = parent(j);
			if (compare(heap.get(j), heap.get(p)) >= 0)
				break;
			swap(j, p);
			j = p;
		}

	}

	/** Moves the entry at index j lower, if necessary, to restore 
	 * the heap property. 
	 */
	protected void downheap(int j) {

		// Small re-use of the concept from the HeapPriorityQueue's downheap, I added extra elements of 3rd child and 4th child 
		// I had to also get each variable out of the if statements because java won't recognize them in the next statements so I had to
		// initialize all variables outside the while loop
		// I failed the test, testUp(), after misplacing many variables in different
		// parts in the method, I couldn't figure out the failure.
		int rightIndex = right(j);
		int tChild = thirdChild(j);
		int fChild = fourthChild(j);

		while (hasLeft(j)) {
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;
			if (hasRight(j)) {
				if (compare(heap.get(leftIndex), heap.get(rightIndex)) > 0)
					smallChildIndex = rightIndex;
			}
			if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
				break;
			swap(j, smallChildIndex);
			j = smallChildIndex;
			if (hasThirdChild(j)) {
				if (compare(heap.get(rightIndex), heap.get(tChild)) > 0)
					smallChildIndex = tChild;
			}
			if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
				break;
			swap(j, smallChildIndex);
			j = smallChildIndex;
			if (hasFourthChild(j)) {
				if (compare(heap.get(tChild), heap.get(fChild)) > 0)
					smallChildIndex = fChild;
			}
			if (compare(heap.get(smallChildIndex), heap.get(j)) >= 0)
				break;
			swap(j, smallChildIndex);
			j = smallChildIndex;
		}
	}

	/** Performs a bottom-up construction of the heap in linear time. */
	protected void heapify() {
		int startIndex = parent(size()-1);    // start at PARENT of last entry
		for (int j=startIndex; j >= 0; j--)   // loop until processing the root
			downheap(j);
	}

	// public methods

	/**
	 * Returns the number of items in the priority queue.
	 * @return number of items
	 */
	@Override
	public int size() { 
		return heap.size();
	}

	/**
	 * Returns (but does not remove) an entry with minimal key.
	 * @return entry having a minimal key (or null if empty)
	 */
	@Override
	public Entry<K,V> min() {
		if (heap.isEmpty()) return null;
		return heap.get(0);
	}

	/**
	 * Inserts a key-value pair and return the entry created.
	 * @param key     the key of the new entry
	 * @param value   the associated value of the new entry
	 * @return the entry storing the new key-value pair
	 * @throws IllegalArgumentException if the key is unacceptable for this queue
	 */
	@Override
	public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
		checkKey(key);      // auxiliary key-checking method (could throw exception)
		Entry<K,V> newest = new PQEntry<>(key, value);
		heap.add(newest);                      // add to the end of the list
		upheap(heap.size() - 1);               // upheap newly added entry
		return newest;
	}

	/**
	 * Removes and returns an entry with minimal key.
	 * @return the removed entry (or null if empty)
	 */
	@Override
	public Entry<K,V> removeMin() {
		if (heap.isEmpty()) return null;
		Entry<K,V> answer = heap.get(0);
		swap(0, heap.size() - 1);              // put minimum item at the end
		heap.remove(heap.size() - 1);          // and remove it from the list;
		downheap(0);                           // then fix new root
		return answer;
	}
}

