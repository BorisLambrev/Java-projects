import java.util.Arrays;
import java.util.Scanner;

public class Counting {

	public static void main(String[] args) {
		
		countingWithStep();

	}
	
	public static void countingMethod() {
		
		int n;
		
		System.out.println("When should I stop counting to ? ");
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		System.out.print("I am counting until "+ n +" : ");
		
		for (int i = 1; i <= n; i++) {
			System.out.print(i +" ");
		}

	}
	
	public static void countingWithStep() {
		
		int n;
		
		int step;
		
		System.out.println("When should I stop counting to ? ");
		
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		
		System.out.println("Which step should I use? ");
		
		step = in.nextInt();
		
		System.out.println("I am counting until "+ n +" with a step of "+ step);
		
		for (int i = 1; i <= n; i = i + step) {
			System.out.print(i +" ");
		}
		
	}

}
