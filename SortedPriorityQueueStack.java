package ca.mcgill.cccs315.assignment3;

import java.security.PKCS12Attribute;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

import net.datastructures.Entry;
import net.datastructures.LinkedPositionalList;
import net.datastructures.Position;
import net.datastructures.PositionalList;
import net.datastructures.PriorityQueue;
import net.datastructures.SortedPriorityQueue;


/**
 * An implementation of a stack using the a sorted priority queue
 * @param <V> The type of content held in the stack
 */
public class SortedPriorityQueueStack<V> {//WE CAN REPLACE GENERIC TYPES, FOR EXAMPLE, ENTRY<K,V> LIKE I JUST DID HERE <Integer, V>
	
	//Main SortedPriorityQueueStack
	SortedPriorityQueue<Integer, V> sPQ = new SortedPriorityQueue<>();
	
	//Helper stacks to save backup of main SortedPriorityQueueStack, one for Integers and the other for Values
	//They help succeeding the tests on pop() and top() method
	Stack<Integer> s1 = new Stack<>();
	Stack<V> s2 = new Stack<>();

	//Counter key creator of Integers
	int keyCounter = 0;
	
	//Keeps track of the size of the SortedPriorityQueueStack
	int qty = 0;
	
	/**
	 * Construct an initially empty stack 
	 */
	public SortedPriorityQueueStack() {
		
	}
	
	/**
	 * Push value on to the stack
	 * Big-O analysis: Constant function O(1), to push a value is a task repeated the same way for any stack. It doesn't involve us 
	 * knowing the size of the stack in order to push/add a value. Pushing/adding a value in a stack in the 5th value is the same time
	 * complexity as pushing/adding a value in the last stack. 
	 * 
	 * @param value The element to be pushed on to the stack
	 */
	public void push(V value) {
		
		keyCounter++; //increasing the Integer key in order from 1 to nth number
		
		s1.push(keyCounter);
		s2.push(value);
		
		//Insert the value with its own Integer key, example we enter push(5) it's (1,5), if we push(6) it's (2,6) and so on.
		sPQ.insert(keyCounter, value);
		
		qty++;
	}
	
	/**
	 * Pop value from the stack
	 * Big-O analysis: Linear function O(n), because we need to pop the last value of a stack. So popping the last value, can take time 
	 * time to pop (remove) depending on the size of the stack, on how many values there are. 
	 * since removing the minimum value from a sorted priority queue is O(1)
	 * 
	 * @return The last element added to the stack
	 */
	public V pop() {
		
		if(sPQ.isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		
		qty--;
		
		V valueToPop = lastValue().min().getValue();
		
		s1.pop();
		s2.pop();
		
		//I tried return lastValue().removeMin().getValue(); but it would also delete the s1 and s2 last elements memories
		//So I used valueToPop so I can delete s1, s2 and the main StackPriorityQueueStack's last elements safely
		return valueToPop;
		
	}
	
	/**
	 * Look at but do not remove the last element added to the stack
	 * @return The last element added to the stack
	 */
	public V top() {
		
		if(sPQ.isEmpty()) {
			System.out.println("Stack is empty");
			return null;
		}
		
		return lastValue().min().getValue();
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
	
	public SortedPriorityQueue<Integer, V> lastValue(){
		
		SortedPriorityQueue<Integer, V> s = new SortedPriorityQueue<Integer, V>();
		
		s.insert(s1.lastElement(), s2.lastElement()); 
		
		return s;
	}

}
