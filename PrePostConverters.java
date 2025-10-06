package ca.mcgill.cccs315.assignment2;

import java.util.Stack;

public class PrePostConverters {
	
	public static void main(String[] args) {
		
		//Tests taken from Assignment 2 inputs
		System.out.println(prefixToPostfix("+ 2 3"));
		System.out.println(prefixToPostfix("* 4 - 7 3"));
		System.out.println(prefixToPostfix("/ 8 0"));
		System.out.println(postfixToPrefix("3 4 +"));
		System.out.println(postfixToPrefix("6 3 / 2 - 8 +"));
		System.out.println(postfixToPrefix("4 5 * 7 + 2 - 0 +"));
		
		System.out.println(prefixToPostfix("\n\n"));
		
		//Tests I made
		System.out.println(prefixToPostfix("+ 88 30"));
		System.out.println(prefixToPostfix("* 10 - 4 9"));
		System.out.println(prefixToPostfix("- 33.2 5"));
		System.out.println(postfixToPrefix("2 6.6 -"));
		System.out.println(postfixToPrefix("5.1 4.55 - 9.7 +"));
		System.out.println(postfixToPrefix("9.9 344 *"));
	}
	
	/**
     * Takes as input a prefix string and returns its postfix representation
     * @param expression The prefix expression
     * @return The postfix representation of the prefix string
     */
	public static String prefixToPostfix(String expression) {
		
		Stack expSwitched = new Stack();//Expression switched
		String newExp = "";//New expression
		
		for(int i = 0; i < expression.length(); i++) {
			expSwitched.push(expression.charAt(i));
		}
		
		//Since Stack is (LIFO) last in first out, it simplifies switching data,
		//it can add/push each element in order from the expression, then pop each element 
		//from the last to the first.
		for(int i = 0; i < expression.length(); i++) {
			newExp = newExp + expSwitched.pop();
		}
		
		return newExp;
		
		//Linear function: the bigger our expression is the more we will go n times in the for loop to convert prefix. 
    }
	
	/**
     * Takes as input a postfix string and returns its prefix representation
     * @param expression The postfix expression
     * @return The prefix representation of the postfix string
     */
    public static String postfixToPrefix(String expression) {
    	
    	Stack expSwitched = new Stack();
		String newExp = "";
		
		for(int i = 0; i < expression.length(); i++) {
			expSwitched.push(expression.charAt(i));
		}
		
		for(int i = 0; i < expression.length(); i++) {
			newExp = newExp + expSwitched.pop();
		}
		
		return newExp;
		
		//Linear function: the bigger our expression is the more we will go n times in the for loop to convert postfix.
    }
    
}
