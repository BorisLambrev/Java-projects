import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

/**
 * CCCS-300-766
 * 
 * BullsAndCows: This program generates a random secret four digit number and
 * the user has to guess the number by entering four digit numbers.
 * 
 * @author Boris Lambrev
 * @version (2021-11-11)
 * 
 * McGill ID 261053070 boris.lambrev@mail.mcgill.ca
 */

public class BullsAndCows {

	public static void main(String[] args) {

		playBullsAndCows(123);// Game method called along with
							  // seed number entered in order to start the game

	}

	public static boolean contains(int[] arrayOfInt, int specificInt) {

		boolean isValid = false;

		for (int i = 0; i < arrayOfInt.length; i++) {
			if (arrayOfInt[i] == specificInt) {
				isValid = true;
			}
		}

		return isValid;
	}

	public static int[] generateSecretDigits(int seed) {

		int[] arraysGenerated = new int[4];

		Random rand = new Random();

		while (arraysGenerated[0] == arraysGenerated[1] || arraysGenerated[1] == arraysGenerated[2]
				|| arraysGenerated[2] == arraysGenerated[3]) {

			arraysGenerated[0] = rand.nextInt((9 - 0) + 1) + 0;// Numbers from 0 to maximum number
			arraysGenerated[1] = rand.nextInt((9 - 0) + 1) + 0;// In game I chose 9 (0-9).
			arraysGenerated[2] = rand.nextInt((9 - 0) + 1) + 0;
			arraysGenerated[3] = rand.nextInt((9 - 0) + 1) + 0;

		} // Loops always until all integers in array are different from each other,
		// no repeated numbers.

		return arraysGenerated;
	}

	public static int getNumOfBulls(int[] secret, int[] guess) throws IllegalArgumentException {
		// If integers repeat themselves or the array integer length does not equal 4,
		// We throw one of two IllegalArgumentExceptions.
		int numOfBulls = 0;
		boolean isRepeated = false;

		for (int i = 0; i < guess.length; i++) {
			if (i + 1 != guess.length) {
				if (guess[i] == guess[i + 1]) {

					throw new IllegalArgumentException("no repeat digit");

				}
			}
		}

		if (secret.length == guess.length && guess.length == 4) {
			for (int i = 0; i < secret.length; i++) {
				if (secret[i] == guess[i]) {
					numOfBulls++;
				}
			}
		} else if (guess.length != 4 && isRepeated == false) {

			throw new IllegalArgumentException("length error");

		}

		return numOfBulls;

	}

	public static int getNumOfCows(int[] secret, int[] guess) throws IllegalArgumentException {
		// Same exceptions as getNumOfBulls
		int numOfCows = 0;
		boolean isRepeated = false;

		for (int i = 0; i < guess.length; i++) {
			if (i + 1 != guess.length) {
				if (guess[i] == guess[i + 1]) {
					throw new IllegalArgumentException("no repeat digit");
				}
			}
		}

		if (secret.length == guess.length && guess.length == 4) {

			for (int i = 0; i < secret.length; i++) {
				if (contains(secret, guess[i]) == true) {// Called contains method in case
					numOfCows++; // secret number contains guessed number
					if (secret[i] == guess[i]) {
						numOfCows--;
					}
				}
			}

		} else if (guess.length != 4 && isRepeated == false) {
			throw new IllegalArgumentException("length error");
		}

		return numOfCows;

	}

	public static void playBullsAndCows(int seed) {// Same seed that generates generateSecretDigits()

		String answer = "n";// Game starts with not saying "y" to quit

		int guessNum = 0;// Number of guesses per try

		String playerNum = "";// Number entered by player in String

		boolean letterPresent = false;

		int wrongNumbers = 0;// The number of bulls or cows in case of a bad array integer,
								// Example, an array integer with letters or an array length != to 4.

		int bullsPts = 0;
		int cowsPts = 0;

		int[] playerNumInArr = new int[4];// Conversion from player's number String into array integer.

		int[] secretInArr = generateSecretDigits(9);// (Secret number in array of integer,
		// Ideally set to 9 for the game.

		while (!answer.equals("y")) {

			// Variables get reset at every guess
			guessNum++;
			bullsPts = 0;
			cowsPts = 0;
			letterPresent = false;

			if (guessNum == 1) {
				System.out.println("Welcome to the game! Have fun!");// Message of presentation
			}
			System.out
					.println("Guess #" + guessNum + ", Enter a four-digit number, " + "each digit should be unique : ");

			Scanner in = new Scanner(System.in);

			playerNum = in.nextLine();// Number entered is String, if it was int,
										// we couldn't enter 0198, it would return 198.

			for (int i = 0; i < playerNum.length(); i++) {// Verify if we have only have numbers.
				if (!Character.isDigit(playerNum.charAt(i))) {
					letterPresent = true;
				}
			}

			// If length of String != 4 or a letter is present we catch the exceptions
			// and print a message, the game continues, it does not crash
			if (playerNum.length() != 4 || letterPresent == true) {
				try {
					wrongNumbers = getNumOfBulls(secretInArr, transform4DigitsToArray(playerNum));
				} catch (IllegalArgumentException e) {
					System.out.println("You must enter a four-digit number! You just wasted one guess!");
				}
			} else {
				playerNumInArr = transform4DigitsToArray(playerNum);
			}

			if (playerNum.length() == 4 && letterPresent == false) {
				try {
					bullsPts = getNumOfBulls(secretInArr, playerNumInArr);
					cowsPts = getNumOfCows(secretInArr, playerNumInArr);
				} catch (IllegalArgumentException e) {
					System.out.println("You are not allowed to repeat the same number!");
				}
				if (playerNumInArr[0] != playerNumInArr[1] && playerNumInArr[1] != playerNumInArr[2]
						&& playerNumInArr[2] != playerNumInArr[3]) {
					System.out.println("Bulls:  " + bullsPts);
					System.out.println("Cows:  " + cowsPts);
				}
				if (bullsPts == 4) {
					System.out.println(
							"Congratulations, you guessed the secret number! It took you " + guessNum + " attempts.");
					answer = "y";// Quit the game once you won
				}
			}

			if (guessNum >= 5 && bullsPts != 4) {
				System.out.println("Do you want to quit the game? Answers: y/n ");
				answer = in.nextLine();
			}

			if (answer.equals("y") && bullsPts != 4) {
				System.out.println("You've decided to quit the game, after making " + guessNum + " attempts.");
			}
			// In case we want to see the secret digits
			// System.out.println(Arrays.toString(secretInArr));
		}
	}

	// Conversion method used to convert String number into array of integers
	public static int[] transform4DigitsToArray(String s) {

		int[] my4DigitNumberInArr = new int[4];

		if (s.length() == 4) {
			my4DigitNumberInArr[0] = Integer.parseInt(s.substring(0, 1));
			my4DigitNumberInArr[1] = Integer.parseInt(s.substring(1, 2));
			my4DigitNumberInArr[2] = Integer.parseInt(s.substring(2, 3));
			my4DigitNumberInArr[3] = Integer.parseInt(s.substring(3, 4));
		}

		return my4DigitNumberInArr;

	}

	// public static void printTests() throws IllegalArgumentException {
	
	// int[] x = {-2, 7};
	// int[] y = {9, 0, 2, 6};
	// int[] z = {};
	// System.out.println(contains(x,-3));
	// System.out.println(contains(y, 2));
	// System.out.println(contains(z, 5));
	
	// int [] a = generateSecretDigits(451);
	// int [] b = generateSecretDigits(73);
	// int [] c = generateSecretDigits(9802);
	// System.out.println(Arrays.toString(a));
	// System.out.println(Arrays.toString(b));
	// System.out.println(Arrays.toString(c));
	
	//int[] secret = { 2, 0, 6, 9 };
	//int[] guessOne = { 9, 5, 6, 2 };
	//int[] guessTwo = { 1, 0, 6, 2 };
	//int[] guessThree = { 1, 2, 3, 4, 5, 6 };
	//int[] guessFour = { 1, 3, 4, 4, 0, 5 };
	//int test1 = getNumOfBulls(secret, guessOne);
	//int test2 = getNumOfBulls(secret, guessTwo);
	//int test3 = getNumOfBulls(secret, guessThree);
	//int test4 = getNumOfBulls(guessThree, guessFour);
	//System.out.println(test1);
	//System.out.println(test2);
	//System.out.println(test3);
	//System.out.println(test4);
	
	//int test5 = getNumOfCows(secret, guessOne);
	//int test6 = getNumOfCows(secret, guessTwo);
	//int test7 = getNumOfCows(secret, guessThree);
	//int test8 = getNumOfCows(guessThree, guessFour);
	//System.out.println(test5);
	//System.out.println(test6);
	//System.out.println(test7);
	//System.out.println(test8);
	
	// }
}
