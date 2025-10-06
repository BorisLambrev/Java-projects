package ca.mcgill.ccccs315.assignment4;

/**
 * ****** Answers to this part's questions *******
 * 
 * 1.	What is the time complexity of your implementation? Analyze and explain.  
 *
 *Time complexity of my implementation is explained through the input we enter in identifyDuplicates() and what happends in allDuplicates().
 *We enter any integer array in identifyDuplicates which is O(n), we don't know how big the size of the integer array is so we can have
 *different outputs. When ArrayList duplicates adds each integer of the integer array it is O(1) time, the run time is always the same.
 *Once we call allDuplicates() method, Once Queue is created it enqueues in constant time it adds one element at a time O(1),
 *it's time space is O(1) also like there is no extra space in a queue. 
 *Comparator<Integer> is O(nlog). It needs to know the number of elements in a list and then sorts out all the elements 
 *in the list, again the log is because it divides all elements from each other to sort them out. The quickSort() method uses partition
 *in order to split the queue in 3 parts, one queue L to have all the smaller integers compared to the pivot, one queue E to have the value
 *equal to the pivot and pivot G to gather all bigger values compared to the pivot. Each adding and removal is in O(1). All the
 *adding and removing together makes the quicksort method run in O(n) time, we don't know how many integers are added or removed. 
 *The recursion in the quicksort makes it O(logn) which involves sorting and taking some parts out of the queue. The recursion in 
 *the quicksort O(logn) and the adding and removal of numbers O(n) the quicksort in total runs in O(nlogn). Dequeue also is run in O(1). 
 *The TreeSet adding each element from the arraylist is O(logn). As it sorts elements and removes all duplicate values which affects
 *the size of it being different than the arraylist that gave it elements. 
 *ArrayList in the end using addAll() to get all the integers from the TreeSet is O(n), because it depends how many elements
 *the TreeSet added. 
 *
 *(I speak on analyzing what would have happen if I can use successfully quickSort class, but I had a hard time figuring it out 
 *so I analyze, it's not based on how I sorted and made the duplicates).
 * 
 * 
 * 
 * 
 * 
 */

import dsaj.sorting.QuickSort;
import net.datastructures.LinkedQueue;
import net.datastructures.Queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class IdentifyDuplicates implements Comparator{
	
	/**
	 * Identify duplicates from a array of integers
	 * 
	 * @param list the list that might contains duplicates
	 * @return A new list with all duplicates removed
	 */
	public static ArrayList<Integer> identifyDuplicates(int[] list) {
		
		if(list.length < 2) {
			System.out.println("Please create an array with 2 integers minimum");
			return null;
		}
		
		ArrayList<Integer> duplicates = new ArrayList<Integer>();
		int count = 0;
		
		while(count != list.length) {
			duplicates.add(list[count]);
			count++;
		}
		
		duplicates = allDuplicates(list);
		//duplicates = allDuplicates2(list); //Method that doesn't use quicksort()
		
		return duplicates;
	}
	
	public static ArrayList<Integer> allDuplicates(int[] a){
		
		//Will exchange with Queue
		ArrayList<Integer> a2 = new ArrayList<>();
		
		//Final ArrayList completed to be returned
		ArrayList<Integer> a3 = new ArrayList<>();

		int n = 0;
		int n2 = 1;
		
		Queue<Integer> qI = new LinkedQueue<Integer>();
		
		while(n != a.length) {
			qI.enqueue(a[n]);
			n++;
		}
		
		//I was not able to find out how to initialize or use properly Comparator<Integer>
		//Comparator<Integer> c = Comparator.naturalOrder();
		//QuickSort.quickSort(qI, c);
		
		n = 0;
		
		while(n != qI.size()) {
			a2.add(qI.dequeue());
			n++;
		}
		
		n = 0;
		n2 = 1;
		
		//TreeSet would sort values as well, but I use it to remove copies of the duplicate values
		TreeSet<Integer> tS = new TreeSet<>();
		
		while(n != a2.size() && n2 != a2.size()) {
			if(a2.get(n) == a2.get(n2)) {
				tS.add(a2.get(n));
			}
			
			n++;
			n2++;
		}
		
		//a3.addAll(tS);
		
		Collections.sort(a2, new IdentifyDuplicates());
		
		return a3;
		
	}
	
	//This succeeds the junit tests unfortunately without the use of quickSort() method
    public static ArrayList<Integer> allDuplicates2(int[] a){
		
		ArrayList<Integer> a2 = new ArrayList<>();
		ArrayList<Integer> a3 = new ArrayList<>();
		
		//Both used to get indexes from Arraylists and arrays
		int n = 0; 
		int n2 = 1;
		
		ArrayList<Integer> aL = new ArrayList<Integer>();
		
		while(n != a.length) {
			aL.add(a[n]);
			n++;
		}
		
		n = 0;
		
		while(n != aL.size()) {
			a2.add(aL.get(n));
			n++;
		}
		
		n = 0;
		n2 = 1;
		
		TreeSet<Integer> tS = new TreeSet<>();
		
		while(n != a2.size() && n2 != a2.size()) {
			if(a2.get(n) == a2.get(n2)) {
				tS.add(a2.get(n));
			}
			
			n++;
			n2++;
		}
		
		a3.addAll(tS);
		
		return a3;
		
	}
	
	public static <K,V> void main(String[] args) {
		
		ArrayList<Integer> a1 = new ArrayList<>();
		
		//2 tests ran well
		//Creates java. lang. OutOfMemoryError Java heap space
		int[] a = {2,2,5,11,24,57,33,33,44,44,5,5,6,7,8,9,190,0,0};

		a1 = identifyDuplicates(a);


		System.out.println(a1);

		
	}

	@Override
	public int compare(Object o1, Object o2) {

		return 0;
	}
}
