import java.util.Scanner;

/**
 * CCCS-300-766
 * 
 * EmailValidation: This program validates an email address.
 * 
 * @author Boris Lambrev
 * @version (2021-10-18)
 * 
 * McGill ID 261053070
 * boris.lambrev@mail.mcgill.ca
 */

public class EmailValidation {
	
	public static void main (String [] args) {
		
		String email;
		boolean isValid;
		
		System.out.print("Hello, please enter a new email : ");//Message of presentation
		
		Scanner in = new Scanner(System.in);
		email = in.nextLine();//Email user can enter
		
		isValid = isValidEmail(email);
		
		if(isValid) {
			System.out.println("Your new email is "+ email +"!");
		}else {
			System.out.print("Sorry, your email is invalid.");
		}
		
	}
	
	public static boolean isAlphabetical(char c) {
		
        boolean isValid = false;
		
		if ((c >= 'A' && c <= 'Z')||(c >= 'a' && c <= 'z')) {
			isValid = true;
		}
			
		return isValid;
	}
	
	public static boolean isAlphanumeric(char c) {
		
		boolean isValid = false;
		
		if ((c >= 'A' && c <= 'Z')||(c >= 'a' && c <= 'z')||(c >= '0' && c <= '9')) {
			isValid = true;
		}
			
		return isValid;
	}
	
	public static boolean isValidPrefixChar(char c) {
		
		boolean isValid = isAlphanumeric(c);
		
		if (isValid == false) {
			if ((c == '-')||(c == '_')||(c == '.')) {
				isValid = true;
			}
		}
		
		return isValid;
	}
	
	public static boolean isValidDomainChar(char c) {
		
		boolean isValid = isAlphanumeric(c);
		
		if (isValid == false) {
			if ((c == '-')||(c == '.')) {
				isValid = true;
			}
		}
		
		return isValid;
	
	}
	
	public static boolean exactlyOneAt(String email) {
		
		boolean isValid = false;
		int numberOfAt = 0; 
		
		if(email.contains("@")) {
			for (int i = 0; i < email.length(); i++) {
				if (email.charAt(i) == '@') {
					numberOfAt++;
				}			
			}
			if(numberOfAt == 1) {//if there is only one '@' it is valid
				isValid = true;
			}
		}
		
		
		return isValid;
	}
	
	public static String getPrefix(String email) {
		
		int i = 0;//Index that locates character number in String email
		
		while(email.charAt(i) != '@') {//Verification of each character
			i++;
		}
		
		return email.substring(0,i);//Cut from the first letter 0 to the last character right before '@'
	}
	
	public static String getDomain(String email) {//Same process as getPrefix
		
		int i = 0;
		
		while(email.charAt(i) != '@') {
			i++;
		}
		
		return email.substring(i + 1, email.length());//Cut from character right after '@'
	}                                                 //to last character
	
	public static boolean isValidPrefix(String prefix) {
		
		boolean isValid = false;
		int j = 0;//Avoids for a false to go back to true when we go back to characters
		int k = 0;//Prevents an underscore, period or dash to go one after the other for example ".."
		
		for (int i = 0; i < prefix.length(); i++) {
			
			if((isAlphanumeric(prefix.charAt(i))) && (j == 0)){
                isValid = true;//any alphanumeric character is valid
			}else if ((isValidPrefixChar(prefix.charAt(i)) && (k == 0) && (j == 0))) {
				k = 1;
				isValid = true;//one underscore, period or dash must be followed  				               //by one or many alphanumeric character(s)
			}       		   //by an alphanumeric character
			else {
				j = 1;
				isValid = false;//if a character is not alphanumeric, underscore, period nor dash
			}                   
			
		}
		
		return isValid;
	}
	
	public static boolean isValidDomain(String domain) {
		
		boolean isValid = false;
		int j = 0;
		int indexOfPeriod = 0;
		String firstP = ""; //First portion
		String secondP = ""; //Second portion
		
		indexOfPeriod = domain.lastIndexOf(".");//Split the domain in two portions
		
		if(indexOfPeriod != -1) {//Prevents the risk of StringIndexOutOfBoundsException
			firstP = domain.substring(0, indexOfPeriod);
		}                                               
		
		if (firstP.equals("")){//Prevents the risk of having a first portion that is empty string
			j = 1;             //Example this prevents returning true for first portion ".com"
			isValid = false;
		}
		
		if(firstP.length() >= 1 && isAlphanumeric(domain.charAt(0))&& 
				isAlphanumeric(firstP.charAt(firstP.length() - 1))) {//Validates if the first portion 
			for (int i = 0; i < firstP.length(); i++) {//has >= 1 character(s) and starts and ends with letter
				if((isValidDomainChar(firstP.charAt(i)) && j == 0)) {
					isValid = true;
				}
				else {
					j = 1;// same validation system as isValidPrefix for periods and dashes only this time
					isValid = false;
				}
			}
		}else {
			j = 1;//If first portion character is not alphanumeric
			isValid = false;
		}
		
		indexOfPeriod = indexOfPeriod + 1;//Increment by one character index to avoid the period
		
		secondP = domain.substring(indexOfPeriod, domain.length());
		
		if(secondP.length() >= 2 && isAlphanumeric(domain.charAt(domain.length() - 1))) {
			for (int i = 0; i < secondP.length(); i++) {//Same conditions on lengths and characters as firstP
				if((isAlphabetical(secondP.charAt(i)) && j == 0)) {//Only letters are valid 
					isValid = true;
				}
				else {
					j = 1;
					isValid = false;
				}
			}
		}else {
			j = 1;//If second portion character is not a letter
			isValid = false;
		}
		
		return isValid;
	}
	
	public static boolean isValidEmail(String email) {
		
		boolean isValid = false;
		
		if (exactlyOneAt(email)) {
			
			if(isValidPrefix(getPrefix(email)) && isValidDomain(getDomain(email))) {
				isValid = true;                                 
			}
		}
		
		return isValid;
	}
	
}	