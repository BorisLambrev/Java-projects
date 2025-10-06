import java.util.Scanner;

/**
 * CCCS-300-766
 * 
 * RightTriangle: This program creates a triangle from the right.
 * 
 * @author Boris Lambrev
 * @version (2021-10-18)
 * 
 * McGill ID 261053070
 * boris.lambrev@mail.mcgill.ca
 */

public class RightTriangle {

	public static void main(String[] args) {
		
        int n; //number entered by user for triangle size
		
		System.out.print("> run RightTriangle ");
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		System.out.print("\n");//Spacing
		
		printRightTriangle(n);

	}
	
	public static void printRightTriangle (int size) {
		
		int k = size - 1;
		
		if (size >= 0) {
			for (int i = 0; i < size; i++) {//i builds the up to down part of triangle
				for(int j = 0; j < size; j++) {//j builds left to right part of triangle
					if(size == j + 1 || size == i + 1 || j >= k) {
						System.out.print("*");//Layer building
					}else {
						System.out.print(" ");
					}
				}
				System.out.println();//New layer of triangle
				k--;//Decrement of k, helps situate where j should print "*"
			}
		}else {
			System.out.println("Error: input value must be >= 0");
		}
		
		System.out.print(">");
	
	}

}
