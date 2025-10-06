/**
 * CCCS-300-766
 * 
 * CoinMachine: This program returns the coin change after a customer entered money for an item.
 * 
 * @author Boris Lambrev
 * @version (2021-09-20)
 * 
 * McGill ID 261053070
 * boris.lambrev@mail.mcgill.ca
 */

public class CoinMachine {

	public static void main(String[] args) {
		
		//Inputs of amount dropped by customer and cost of item in terminal
		String x1 = args[0];
		String x2 = args[1];
		
		//Declared variables
		int amountDrop;
		int cost;
		int change;
		
		int toonie;
		int loonie;
		int quarter;
		int dime;
		int nickel;
		
		//Conversion from String to integer in order to calculate the numbers entered
		amountDrop = Integer.parseInt(x1);
		
		cost = Integer.parseInt(x2);
		
		//Calculations
		change = amountDrop - cost;
		
		System.out.println("\nAmount received: "+ amountDrop);
		System.out.println("Cost of the item: "+ cost);
		System.out.println("Required change: "+ change);
		
		toonie = change / 200;
		change = change - 200 * toonie;
		loonie = change / 100;
		change = change - 100 * loonie;
		quarter = change / 25;
		change = change - 25 * quarter;
		dime = change / 10;
		change = change - 10 * dime;
		nickel = change / 5;
		change = change - 5 * nickel;
		
		//Printing coin quantity
		System.out.println("\nChange:");
		System.out.println("    toonies x "+ toonie);
		System.out.println("    loonie x "+ loonie);
		System.out.println("    quarter x "+ quarter);
		System.out.println("    dime x "+ dime);
		System.out.println("    nickel x "+ nickel);

	}

}
