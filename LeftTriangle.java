import java.util.Scanner;

/**
 * CCCS-300-766
 * 
 * LeftTriangle: This program creates a triangle from the left.
 * 
 * @author Boris Lambrev
 * @version (2021-10-18)
 * 
 * McGill ID 261053070
 * boris.lambrev@mail.mcgill.ca
 */

public class LeftTriangle {

	public static void main(String[] args) {
		
		int n; //number entered by user for triangle size
		
		System.out.print("> run LeftTriangle ");
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		System.out.print("\n");//Spacing
		
		printLeftTriangle(n);

	}
	
	public static void printLeftTriangle(int size) {
		
		if (size >= 0) {
			for (int i = 0; i < size; i++) {//i builds the up to down part of triangle
				for(int j = 0; j < size; j++) {//j builds left to right part of triangle
					if(i >= j && size >= 1) {
						System.out.print("*");//Layer building 
					}else {
						System.out.print(" ");
					}
				}
				System.out.println();//New layer of triangle
			}
		}else {
			System.out.println("Error: input value must be >= 0");
		}
		
		System.out.print(">");
	}

}
