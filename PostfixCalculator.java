package ca.mcgill.cccs315.assignment2;

import java.util.Scanner;
import java.util.Stack;

public class PostfixCalculator {
	
	/**
	 * A sample main method to play with our application interactively
	 * 
	 * @param args The args array
	 */
    public static void main(String[] args) {
    	
    	int n = 3;
    	
    	while(n > 0) {
    		System.out.println("Enter a postfix notation expression: ");
        	
        	String notation;
        	
        	Scanner scanner = new Scanner(System.in);
        	notation = scanner.nextLine();
        	
        	System.out.print("Result: ");
        	System.out.println(evaluatePostfixExpression(notation));
        	n--;
        	System.out.println();
    	}
    }
    
    /**
     * A method that evaluates a postfix notation string consisting of the following valid
     * operations "+", "-", "/" and "*". In the case of an invalid String, an 
     * "IllegalArgumentException" is thrown.
     * 
     * @param expression A postfix notation string
     * @return The result of simplifying the expression
     */
    public static double evaluatePostfixExpression(String expression) {
    	Stack expLine = new Stack(); //Stack that evaluates the expressions and that pushes operands and operators in the Stack as long as 
		 							 //the elements are valid
    	double answer = 0;
    	double op1 = 0;
    	double op2 = 0;
    	int mTOO = 1; //mTOO: More than one operator abbreviation 
    	int lastOp = 0;
    	String manyExp = ""; //dividing an expression to many small ones in order to complete many equations if many operators present
    	
    	expression = xM(expression);
    	
    	if(!isOperator(expression.charAt(expression.length() - 1)+"")) {
    		throw new IllegalArgumentException();
    	}
    	
    	for(int i = 0; i < expression.length(); i++) {
    			//We know no matter how many operators we put in our expression, they are all designed the same way, every int, double or
    		    //operator will be separated by a white space. 
    			
    		if(expression.charAt(i) == ' ' && mTOO == 1) {
    			manyExp = operatorDivider(expression, 0, i + 1);
    			op1 = isDouble(manyExp);
    	        lastOp = i + 1;
    	        mTOO++;
    	        expLine.push(op1);
    		}else if (expression.charAt(i) == ' ' && mTOO > 1) {
    			manyExp = operatorDivider(expression, lastOp, i + 1);
    	        op2 = isDouble(manyExp);
    	        lastOp = i + 1;
    		}else if((isOperator(expression.charAt(i)+""))){
    	        answer = performOperation((double)expLine.pop(), op2, expression.charAt(i)+"");
        	    expLine.push(answer);
        	    lastOp = i + 1;
    	    }
    	}

    		
    		return answer;
        	
        	//Constant function O(1). We have 2 case scenarios. 1- we make a simple calculation where 2 3 + involves a lenght 
        	//of 5 everytime we want to calculate one line alone between 2 operands only. That is constant, we will use the same method of simple
        	//calculations for 2 operands and one operator. 
        	//However, 2- It can also be Logarithmic function O(Log(n)), if we involve an expression with more than one operator in it 
        	//(bigger than lenght of 5 expression) it won't be big O(1)
        	//but O(Log(n)), because then, with the stack we reduce the size of the expression more and more as we try to get an answer by
        	//simplifying calculation. For example * 4 - 7 3, we will go from right to left until all numbers are calculated till we reach an 
        	//answer. 
    }

    /**
     * 
     * @param token
     * @return True if the token provided is a supported operation
     */
    public static boolean isOperator(String token) {
    	if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
    		return true;
    	}else {
    		return false;
    	}
    	
    	//Constant function O(1): Because simply we come for one simple primitive operation, we choose one of the 4 operators
    	//return true or false, nothing involves a certain size of an array or anything for it to be Linear or any other function
    }
    
    public static double isDouble(String expression) {//Changes any int or double number from a string into a double.
    	
    	String nbrInStr = ""; //Number as String
    	double nbrParsed = 0;
    	int nbrSplit = 0;
    	int n = 0;
    	
    	for(int i = 0; i < expression.length(); i++) {
    		if((expression.charAt(i) >= '0' && expression.charAt(i) <= '9') || expression.charAt(i) == '.' && n == 0) {
    			nbrInStr = nbrInStr + expression.charAt(i);
    			nbrSplit = i + 1;
    			if(expression.substring(nbrSplit, nbrSplit + 1).equals(" ")) {
    				break;
    			}
    		}
    	}
    	
    	nbrParsed = Double.parseDouble(nbrInStr);
    	
    	return nbrParsed;
    }


    /**
     * 
     * @param operand1 The first operand to the operation
     * @param operand2 The second operand to the operation
     * @param operator The supported operation to perform
     * @return The result of applying the operation to operand1 and operand2.
     */
    public static double performOperation(double operand1, double operand2, String operator) {
    	double answer = 0;
    	
    	if(operator.equals("+")) {
    		answer = operand1 + operand2;
    	}else if(operator.equals("-")) {
    		answer = operand1 - operand2;
    	}else if(operator.equals("*")) {
    		answer = operand1 * operand2;
    	}else if(operator.equals("/")) {
    		
    		if(operand2 == 0) {
    			try {
    				
        		}catch(ArithmeticException ae) {
        			answer = operand1 / operand2;
        		} System.out.println("Error: Division by 0");
			}
    		
    		if(operand2 != 0) {
    			answer = operand1 / operand2;
    		}
    		
    	}
    	
    	return answer;
    	
    	//Constant function O(1), because in the end, we only consider 1 primitive operation where 2 operands get calculated. 
    	//if its 5 + 4 or 100 - 9 it will take the same time to run. However the Arithmetic exception might break the O(1) and is the only
    	//other primitive operation to consider, when the exception is thrown.
    }
    
    public static String operatorDivider(String expression, int fromOperator, int toOperand2) {//Returns a substring of the targeted 
    																						   //operand 1 and operand 2 in a string
    	
    	return expression.substring(fromOperator, toOperand2);
    }
    
    public static String xM(String expression) {//xMethod: changes each white space after an operator into 'x'
    											//So that we don't raise an exception while parsing a string to a number
    	
    	String newExp = "";
    	char[] arr = new char [expression.length()];
    	int k = expression.length() + 1;
    	
    	for(int i = 0; i < expression.length(); i++) {
    		if(i != k) {
    			arr[i] = expression.charAt(i);
    		}
    		
    		if(i != expression.length() - 1) {
    			if(isOperator(expression.charAt(i)+"")) {
        			k = i + 1;
        			arr[k] = 'x';
        		}
    		}
    		
    	}
    	
    	
    	for(int i = 0; i < expression.length(); i++) {
    		newExp = newExp + arr[i];
    	}
    	
    	return newExp;
    }
}
