/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ca.mcgill.ccccs315.assignment4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * ****** Answers to this part's questions *******
 * 
 * 1.	What is the time complexity of your implementation? Analyze and explain. 
 * 
  Many primitive operations are involved, I will analyze and explain the main ones of the algorithm. LinkedPositionalList having its
  elements quick sorted. Time complexity of LinkedPositionalList, quicksort method and what the method does to that list. 
  LinkedPositionalList is O(1) run in constant time. As it explains in the book in page 280, all its methods
  are run in O(1) in worst case time. The space usage of it is O(n) because all LinkedPositionalList can vary 
  in size and how many elements are in them. Another reason why it's O(1) is because its based of a DoublyLinkedList, with the
  addBetween method, and all methods mostly in a doubly linked list are constant time. A new node is always placed between 2 nodes or 
  at the beginning of the list or at the end of it regardless of the size of the LinkedPositionalList. 
 
  The quick sort method is O(nlogn) at the 1st primitive operation when picking
  randomly an element, we call pivot. We will use it to sort all number elements from left of pivot if they are smaller than the pivot and 
  all numbers bigger than pivot to the right of the pivot. Choosing the pivot is random based and we need the size of a list (Stack, 
  Array or LinkedList), because it all varies how many element numbers we have behind the pivot and after the pivot. All elements get moved
  around in order to compare them as much as possible. 
  
  For in-place quick sort, it is O(logn). We compare elements by dividing the list in 2 sublists from the pivot point all left of
  the pivot is smaller numbers and right of the pivot are bigger numbers than the pivot. Each sublist, self-sorts itself where elements 
  compare themselves in their own sublist then later merged back to the list they compare one last time to get the proper sorting done. A lot
  of swapping is done between each elements until all numbers are sorted in order from smallest to biggest. The recursive method in a quick
  sort() also explains why its O(logn), we make the sublist smaller by sublists length - 1. We do so in order to change the indice or position
  of each element to compare it with every other element. As the method calls itself, we use - 1 or + 1 in order to go one indice back or
  one indice after, otherwise the recursion will go on looping endlessly. In page 555 of our book, if list has elements already sorted 
  or reverse sorted, in-place quick sort will be O(n^2) in time worst case. According to GeeksforGeeks, This applies for both 
  Arrays and DoublyLinkedLists if they have elements that are already sorted and use quicksort() its a worst case of O(n^2). 
  We will only check the size and the order of the elements in the list and for the rest its a best case or average case of O(nlogn) if
  elements are not sorted.
  
  https://www.geeksforgeeks.org/java-program-for-quicksort-on-doubly-linked-list/
 *
 * 
 * 
 * 
 * 
 * 
 */

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import dsaj.sorting.QuickSort;
import net.datastructures.PositionalList;
import net.datastructures.Queue;
import net.datastructures.LinkedQueue;
import net.datastructures.Position;

/**
 * Implementation of a positional list stored as a doubly linked list.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class LinkedPositionalList<E> implements PositionalList<E> {
  //---------------- nested Node class ----------------
  /**
   * Node of a doubly linked list, which stores a reference to its
   * element and to both the previous and next node in the list.
   */
  private static class Node<E> implements Position<E> {

    /** The element stored at this node */
    private E element;               // reference to the element stored at this node

    /** A reference to the preceding node in the list */
    private Node<E> prev;            // reference to the previous node in the list

    /** A reference to the subsequent node in the list */
    private Node<E> next;            // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param p  reference to a node that should precede the new node
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> p, Node<E> n) {
      element = e;
      prev = p;
      next = n;
    }

    // public accessor methods
    /**
     * Returns the element stored at the node.
     * @return the stored element
     * @throws IllegalStateException if node not currently linked to others
     */
    public E getElement() throws IllegalStateException {
      if (next == null)                         // convention for defunct node
        throw new IllegalStateException("Position no longer valid");
      return element;
    }

    /**
     * Returns the node that precedes this one (or null if no such node).
     * @return the preceding node
     */
    public Node<E> getPrev() {
      return prev;
    }

    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() {
      return next;
    }

    // Update methods
    /**
     * Sets the node's element to the given element e.
     * @param e    the node's new element
     */
    public void setElement(E e) {
      element = e;
    }

    /**
     * Sets the node's previous reference to point to Node n.
     * @param p    the node that should precede this one
     */
    public void setPrev(Node<E> p) {
      prev = p;
    }

    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) {
      next = n;
    }
  } //----------- end of nested Node class -----------

  // instance variables of the LinkedPositionalList
  /** Sentinel node at the beginning of the list */
  private Node<E> header;                       // header sentinel

  /** Sentinel node at the end of the list */
  private Node<E> trailer;                      // trailer sentinel

  /** Number of elements in the list (not including sentinels) */
  private int size = 0;                         // number of elements in the list

  /** Constructs a new empty list. */
  public LinkedPositionalList() {
    header = new Node<>(null, null, null);      // create header
    trailer = new Node<>(null, header, null);   // trailer is preceded by header
    header.setNext(trailer);                    // header is followed by trailer
  }
  
  Integer[] arr;
  
  // private utilities
  /**
   * Verifies that a Position belongs to the appropriate class, and is
   * not one that has been previously removed. Note that our current
   * implementation does not actually verify that the position belongs
   * to this particular list instance.
   *
   * @param p   a Position (that should belong to this list)
   * @return    the underlying Node instance at that position
   * @throws IllegalArgumentException if an invalid position is detected
   */
  private Node<E> validate(Position<E> p) throws IllegalArgumentException {
    if (!(p instanceof Node)) throw new IllegalArgumentException("Invalid p");
    Node<E> node = (Node<E>) p;     // safe cast
    if (node.getNext() == null)     // convention for defunct node
      throw new IllegalArgumentException("p is no longer in the list");
    return node;
  }

  /**
   * Returns the given node as a Position, unless it is a sentinel, in which case
   * null is returned (so as not to expose the sentinels to the user).
   */
  private Position<E> position(Node<E> node) {
    if (node == header || node == trailer)
      return null;   // do not expose user to the sentinels
    return node;
  }

  // public accessor methods
  /**
   * Returns the number of elements in the list.
   * @return number of elements in the list
   */
  @Override
  public int size() { return size; }

  /**
   * Tests whether the list is empty.
   * @return true if the list is empty, false otherwise
   */
  @Override
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns the first Position in the list.
   *
   * @return the first Position in the list (or null, if empty)
   */
  @Override
  public Position<E> first() {
    return position(header.getNext());
  }

  /**
   * Returns the last Position in the list.
   *
   * @return the last Position in the list (or null, if empty)
   */
  @Override
  public Position<E> last() {
    return position(trailer.getPrev());
  }

  /**
   * Returns the Position immediately before Position p.
   * @param p   a Position of the list
   * @return the Position of the preceding element (or null, if p is first)
   * @throws IllegalArgumentException if p is not a valid position for this list
   */
  @Override
  public Position<E> before(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getPrev());
  }

  /**
   * Returns the Position immediately after Position p.
   * @param p   a Position of the list
   * @return the Position of the following element (or null, if p is last)
   * @throws IllegalArgumentException if p is not a valid position for this list
   */
  @Override
  public Position<E> after(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    return position(node.getNext());
  }

  // private utilities
  /**
   * Adds an element to the linked list between the given nodes.
   * The given predecessor and successor should be neighboring each
   * other prior to the call.
   *
   * @param pred     node just before the location where the new element is inserted
   * @param succ     node just after the location where the new element is inserted
   * @return the new element's node
   */
  private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
    Node<E> newest = new Node<>(e, pred, succ);  // create and link a new node
    pred.setNext(newest);
    succ.setPrev(newest);
    size++;
    return newest;
  }

  // public update methods
  /**
   * Inserts an element at the front of the list.
   *
   * @param e the new element
   * @return the Position representing the location of the new element
   */
  @Override
  public Position<E> addFirst(E e) {
    return addBetween(e, header, header.getNext());       // just after the header
  }

  /**
   * Inserts an element at the back of the list.
   *
   * @param e the new element
   * @return the Position representing the location of the new element
   */
  @Override
  public Position<E> addLast(E e) {
    return addBetween(e, trailer.getPrev(), trailer);     // just before the trailer
  }

  /**
   * Inserts an element immediately before the given Position.
   *
   * @param p the Position before which the insertion takes place
   * @param e the new element
   * @return the Position representing the location of the new element
   * @throws IllegalArgumentException if p is not a valid position for this list
   */
  @Override
  public Position<E> addBefore(Position<E> p, E e)
                                throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node.getPrev(), node);
  }

  /**
   * Inserts an element immediately after the given Position.
   *
   * @param p the Position after which the insertion takes place
   * @param e the new element
   * @return the Position representing the location of the new element
   * @throws IllegalArgumentException if p is not a valid position for this list
   */
  @Override
  public Position<E> addAfter(Position<E> p, E e)
                                throws IllegalArgumentException {
    Node<E> node = validate(p);
    return addBetween(e, node, node.getNext());
  }

  /**
   * Replaces the element stored at the given Position and returns the replaced element.
   *
   * @param p the Position of the element to be replaced
   * @param e the new element
   * @return the replaced element
   * @throws IllegalArgumentException if p is not a valid position for this list
   */
  @Override
  public E set(Position<E> p, E e) throws IllegalArgumentException {
    Node<E> node = validate(p);
    E answer = node.getElement();
    node.setElement(e);
    return answer;
  }

  /**
   * Removes the element stored at the given Position and returns it.
   * The given position is invalidated as a result.
   *
   * @param p the Position of the element to be removed
   * @return the removed element
   * @throws IllegalArgumentException if p is not a valid position for this list
   */
  @Override
  public E remove(Position<E> p) throws IllegalArgumentException {
    Node<E> node = validate(p);
    Node<E> predecessor = node.getPrev();
    Node<E> successor = node.getNext();
    predecessor.setNext(successor);
    successor.setPrev(predecessor);
    size--;
    E answer = node.getElement();
    node.setElement(null);           // help with garbage collection
    node.setNext(null);              // and convention for defunct node
    node.setPrev(null);
    return answer;
  }

  // support for iterating either positions and elements
  //---------------- nested PositionIterator class ----------------
  /**
   * A (nonstatic) inner class. Note well that each instance
   * contains an implicit reference to the containing list,
   * allowing us to call the list's methods directly.
   */
  private class PositionIterator implements Iterator<Position<E>> {

    /** A Position of the containing list, initialized to the first position. */
    private Position<E> cursor = first();   // position of the next element to report
    /** A Position of the most recent element reported (if any). */
    private Position<E> recent = null;       // position of last reported element

    /**
     * Tests whether the iterator has a next object.
     * @return true if there are further objects, false otherwise
     */
    public boolean hasNext() { return (cursor != null);  }

    /**
     * Returns the next position in the iterator.
     *
     * @return next position
     * @throws NoSuchElementException if there are no further elements
     */
    public Position<E> next() throws NoSuchElementException {
      if (cursor == null) throw new NoSuchElementException("nothing left");
      recent = cursor;           // element at this position might later be removed
      cursor = after(cursor);
      return recent;
    }

    /**
     * Removes the element returned by most recent call to next.
     * @throws IllegalStateException if next has not yet been called
     * @throws IllegalStateException if remove was already called since recent next
     */
    public void remove() throws IllegalStateException {
      if (recent == null) throw new IllegalStateException("nothing to remove");
      LinkedPositionalList.this.remove(recent);         // remove from outer list
      recent = null;               // do not allow remove again until next is called
    }
  } //------------ end of nested PositionIterator class ------------

  //---------------- nested PositionIterable class ----------------
  private class PositionIterable implements Iterable<Position<E>> {
    public Iterator<Position<E>> iterator() { return new PositionIterator(); }
  } //------------ end of nested PositionIterable class ------------

  /**
   * Returns an iterable representation of the list's positions.
   * @return iterable representation of the list's positions
   */
  @Override
  public Iterable<Position<E>> positions() {
    return new PositionIterable();       // create a new instance of the inner class
  }

  //---------------- nested ElementIterator class ----------------
  /* This class adapts the iteration produced by positions() to return elements. */
  private class ElementIterator implements Iterator<E> {
    Iterator<Position<E>> posIterator = new PositionIterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() { return posIterator.next().getElement(); } // return element!
    public void remove() { posIterator.remove(); }
  }

  /**
   * Returns an iterator of the elements stored in the list.
   * @return iterator of the list's elements
   */
  @Override
  public Iterator<E> iterator() { return new ElementIterator(); }

  // Debugging code
  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = header.getNext();
    while (walk != trailer) {
      sb.append(walk.getElement());
      walk = walk.getNext();
      if (walk != trailer)
        sb.append(", ");
    }
    sb.append(")");
    return sb.toString();
  }
  
  /**
   * Performs an in-place sort of the LinkedPositionalList
   */
  public void sort() { 
	  //Influenced by QuickSort.inplaceQuickSort() method but not calling the method
	  //The code of quickSort influence is in sortIntegers
	  Collections.reverseOrder();
  }
  
  public static void sortIntegers(Integer[] iA) {
	  
	  Random r = new Random();
	  
	  // Chooses an element to be a pivot at random between index[0] and index[array.lenght - 1] from the Integer array iA
	  // Chooses the index and not the value in the index of the array
	  int pivot = r.nextInt(iA.length) + 0;
	  
	  //Divide array in 2 sub-arrays
	  //(Unfortunately, s1 can raise negative array size exception)
	  Integer [] s1 = new Integer [pivot - 1];
	  Integer [] s2 = new Integer [iA.length - pivot];
	  LinkedPositionalList lpl = new LinkedPositionalList();
	  
	  //Tracks s2 indexes
	  int j = 0;
	  
	  for(int i = 0; i < s1.length + s2.length; i++) {
		  if(i != pivot) {
			  if(i < s1.length) {
				  s1[i] = iA[i];
			  }else {
				  s2[j] = iA[i];
				  j++;
			  }
		  }
	  }
	  
	  Comparator<Integer> c = Comparator.naturalOrder();
	  
	  //If we keep on testing depending what pivot is chosen by the Random class, we end up successfully executing code
	  //But most times there is an exception raised
	  QuickSort.quickSortInPlace(s1, c);
	  QuickSort.quickSortInPlace(s2, c);
	  
	  j = 0;
	  
	  for(int i = 0; i <= s1.length + s2.length; i++) {
		  if(i < s1.length) {
			  lpl.addLast(s1[i]);
		  }else if(j < s2.length) {
			  lpl.addLast(s2[j]);
			  j++;
		  }
	  }
	  
	  lpl.sort();
	  
	  System.out.println(lpl);
  }
  
  public static void recursivelySort(Integer[] a, int left, int right) {
	 
	  if(a[left] < a[right] && left != a.length && right != 0) {
		  a[left] = a[right];
	  }
	  
	  recursivelySort(a, left + 1, right - 1);
  }
  
  public static void main(String[] args) {
	  
	  //If clicking 'Run LinkedPositionalList' more than once, it executes (0, 6, 8, 33, 35, 44, 9) for that input
	  Integer[] a = {33,6,0,44,35,8,9,76};
	  sortIntegers(a);
  }
  
}
