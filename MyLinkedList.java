package ca.mcgill.cccs315.assignment1;

public class MyLinkedList {
    private Node head;
    private int size;

    
    /**
     * Constructor that initializes an empty
     * linked list
     */
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Add a single element to the list 
     * 
     * @param data The int to be added at the end of the list
     */
    public void add(int data) {
    	if(head == null) {
    		head = new Node(data);
    	}else {
    		Node current = head;
    		while (current.getNext() != null) {
    			current = current.getNext();
    		}
    		current.setNext(new Node(data));
    	}
    	
    	size++;
    }
    
    /**
     * Add an array of elements to the end of the list
     * 
     * @param data The array of elements to be added
     */
    public void add(int[] data) {
    	for(int i = 0; i < data.length; i++) {
    		add(data[i]);
    	}
    }
    
    /**
     * Add a list of elements to the end of the list
     * 
     * @param data The list of elements to be added
     */
    public void add(MyLinkedList l2) {
    	for(int i = 0; i < l2.size; i++) {
    		add(l2.get(i));
    	}
    }
    
    /**
     * Retrieve the element at the given index
     * 
     * @param index The position of the element starting at zero
     * @return The value contained in the node at the index
     */
    public int get(int index) {
    	Node current = head;
    	for(int i = 0; i < index; i++) {
    		current = current.getNext();
    	}
    	return current.getData();
    }

    /**
     * Remove every instance of data in the list
     * 
     * @param data The data to be removed from the list
     */
    public void remove(int data) {
    	if(head == null) {
    		return;
    	}
    	if(head.getData() == data) {
    		head = head.getNext();
    		size--;
    		return;
    	}
    	Node current = head;
    	while(current.getNext() != null && current.getNext().getData() != data) {
    		current = current.getNext();
    	}
    	if(current.getNext() != null) {
    		current.setNext(current.getNext().getNext());
    		size--;
    	}
    }
    
    
    /**
     * Determines whether the needle exists in the list

     * @param needle The value you are looking for
     * @return True if needle is found in the list. False otherwise
     */
    public boolean contains(int needle) {
    	if(head == null) {
    		return false;
    	}
    	
    	if(head.getData() == needle) {
    		return true;
    	}
    	
    	Node current = head;
    	while(current.getNext() != null && current.getNext().getData() != needle) {
    		current = current.getNext();
    	}
    	
    	if(current.getNext() != null) {
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * Check whether two lists have the same values
     * @param l1 The first list
     * @param l2 The second list
     * @return True if the lists contain the same elements in the same order. False otherwise
     */
	public static boolean equals(MyLinkedList l1, MyLinkedList l2) {

		if (l1.toString().equals(l2.toString())) {
			return true;
		}
		return false;

	}
    
    /**
     * Check whether this list is equal to the list provided as input
     * @param l2 The list to check
     * @return True if the lists contain the same elements in the same order. False otherwise
     */
    public boolean equals(MyLinkedList l2) {
    	
    	if(this.toString().equals(l2.toString())) {
    		return true;
    	}
    	return false;
    }
    
    /**
     * Check whether this list is equal to the list provided as input
     * @param o2 The object to check
     * @return True if the lists contain the same elements in the same order. False otherwise
     */
    @Override
    public boolean equals(Object o2) {
    	
    	MyLinkedList l2;
    	
    	if(o2 instanceof MyLinkedList) {
    		l2 = (MyLinkedList) o2;
    		if(this.toString().equals(l2.toString())) {
    			return true;
    		}
    	}
    	
    	return false;
    }

    /**
     * 
     * @return True if the list is empty
     */
    public boolean isEmpty() {
    	return size == 0;
    }
    
    /**
     * Find the minimum of a list in an iterative manner
     * @return Minimum of the list. Integer.MAX_VALUE if empty
     */
    public int findMinIterative() {
    
    	if(head == null) {
    		return 100;
    	}
    	
    	int min = head.getData();
    	Node current = head;
    	
    	while (current.getNext() != null) {
    		if(current.getData() < min) {
    			min = current.getData();
    		}
    		current = current.getNext();
    	}
    	return min;
    }
    
    
    /**
     * Find the minimum of a list in an recursive manner
     * @return Minimum of the list. Integer.MAX_VALUE if empty
     */
    public int findMinRecursive() {
    	
    	return findMinRecursiveHelper(head);
    }
    
    private int findMinRecursiveHelper(Node node) {
    	if(node.getNext() == null) {
    		return 100;
    	}
    	
    	return Math.min(node.getData(), findMinRecursiveHelper(node.getNext()));
    }

    /**
     * 
     * @return The size of the list
     */
    public int size() {
    	return size;
    }

    /**
     * @return The string representation of the list
     */
    @Override
    public String toString() {
    
    	StringBuilder result = new StringBuilder();
    	Node current = head;
    	while(current != null) {
    		result.append(current.getData()).append(" ");
    		current = current.getNext();
    	}
    	
    	return result.toString().trim();
    }
    
    
    
}

