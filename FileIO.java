import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * CCCS-300-766
 * 
 * FileIO: This program reads a character file or spell file OR writes a character file or 
 * spell file. 
 * 
 * @author Boris Lambrev
 * @version (2021-12-09)
 * 
 * McGill ID 261053070 boris.lambrev@mail.mcgill.ca
 */

public class FileIO {

	public static void main(String[] args) throws Exception{//Tested methods
		
		//Character bob = readCharacter("CharacterTest2");
		//System.out.println(bob);
		//ArrayList<Spell> s = readSpells("spellz");
		//System.out.println(s);
		
	}
	
	public static Character readCharacter(String fileName) {
		
		//Declaring ArrayList of each description line of a character
		//Create the character object
		ArrayList<String> linesOfChar = new ArrayList<String>();
		Character character = new Character("",0,0,0);
		
		try {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		String currentLine = br.readLine();//The line we want to bring in java from text file
		
		while(currentLine != null) {
			linesOfChar.add(currentLine);//We add each line from text file to our java
										 //In order to create the character and play him
			currentLine = br.readLine();
		}
		
		//Close the text once each line is read
		fr.close();
		br.close();
		
		}catch(FileNotFoundException fnfe) {
			System.out.println("This file cannot be found!");
			character = null;
		}catch(IOException ioe) {
			System.out.println("There seems to be an input/output error!");
			character = null;
		}
		
		double health = Double.parseDouble(linesOfChar.get(1));//line that has the health of character
		double attack = Double.parseDouble(linesOfChar.get(2));//line that has the attack of character
		int numWins = Integer.parseInt(linesOfChar.get(3));//line that shows his number of wins
		
		character = new Character(linesOfChar.get(0),health,attack,numWins);//Character is created
		
		return character;
	}
	
	public static ArrayList<Spell> readSpells(String fileName){
		
		ArrayList<Spell> spellList = new ArrayList<Spell>();//We increment the amount of spells we want
		
		String name = "";
		int [] minMaxDam = new int [2];//Minimum and maximum damage ([0] and [1]) 
		double chance = 0;
		
		try {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		
		String currentLine = br.readLine();//Read each line for gathering spells attributes
		
		while (currentLine != null) {
			name = makeWord(currentLine);//take name from text to java
			minMaxDam = damages(currentLine);//take minimum and maximum damages from text to java
			chance = damageChance(currentLine);//take the chance the attack succeeds from text to java
			Spell spellObj = new Spell(name,minMaxDam[0],minMaxDam[1],chance);//Created the spell
			spellList.add(spellObj);//Add each spell in a spell list
			currentLine = br.readLine();
		}
		
		fr.close();
		br.close();
		
		}catch(FileNotFoundException fnfe) {
			System.out.println("This file cannot be found!");
			spellList = null;
		}catch(IOException ioe) {
			System.out.println("There seems to be an input/output error!");
			spellList = null;
		}
		
		return spellList;
	}
	
	//This method gathers all the letters of the current line in the bufferedReader
	//and makes a Spell name only
	public static String makeWord(String lineOfString) {
		String word = "";
		char letter = lineOfString.charAt(0);
		int i = 0;
	
		while((letter >= 'A' && letter <= 'Z')||(letter >= 'a' && letter <= 'z') || letter == ' ') {
			word = word + letter;
			i++;
			letter = lineOfString.charAt(i);
		}
		
		return word;
	}
	
	//This method gathers only the attacks in the middle of the currentLine buffered
	//It returns the minimum attack number and maximum attack number
	//It records only attack values from 1 to 99
	public static int[] damages(String lineOfString) {

		char numberChar = lineOfString.charAt(0);
		String numberStr = "";//Number from a text
		String zeroPlusNum = "";//Variable helps adding a zero to left side of a number 
								//between 0 to 9, otherwise there is a NumberFormatException
								//if we parse " 5" into int 5. We need "05" to parse in to int 5
		int i = 0;
		int j = 0;
		int [] attacks = new int [2];
		
		while(i != lineOfString.length() && attacks[0] == 0) {
			
			if((numberChar >= '0' && numberChar <= '9')) {//If we detect a number
				
				numberStr = numberStr + numberChar;//Concatenate number
				
			}
			i++;
			if(i != lineOfString.length()) {
				numberChar = lineOfString.charAt(i);
			}
			if(!numberStr.equals("")) {
				if (!(numberChar >= '0' && numberChar <= '9')) {
					zeroPlusNum = "0" + numberStr;//if number is between 0 - 9 we add a "0" to the left
					attacks[0] = Integer.parseInt(zeroPlusNum);
				}else {
					numberStr = numberStr + numberChar;//if number is 2 digits, we concatenate
													   //2nd digit
					attacks[0] = Integer.parseInt(numberStr);
				}	
				numberStr = "";
				zeroPlusNum = "";
			}
		}
		
		j = i + 1;
		numberChar = lineOfString.charAt(j);
		numberStr = "";
		zeroPlusNum = "";
		
		while(j != lineOfString.length() && attacks[1] == 0) {//Repeat the process for maximum attack
			
			if((numberChar >= '0' && numberChar <= '9')) {
				
				numberStr = numberStr + numberChar;
				
			}
			j++;
			if(j != lineOfString.length()) {
				numberChar = lineOfString.charAt(j);
			}
			if(!numberStr.equals("")) {
				if (!(numberChar >= '0' && numberChar <= '9')) {
					zeroPlusNum = "0" + numberStr;
					attacks[1] = Integer.parseInt(zeroPlusNum);
				}else {
					numberStr = numberStr + numberChar;
					attacks[1] = Integer.parseInt(numberStr);
				}
				numberStr = "";
				zeroPlusNum = "";
			}
		}
		
		return attacks;
	}
	
	public static double damageChance(String lineOfString) {//We know the text is designed to show
															//the last 3 characters as our chance
															//0.0
		double chance = 0;
		String numberStr = lineOfString.substring(lineOfString.length() - 3, lineOfString.length());
		
		chance = Double.parseDouble(numberStr);
		
		return chance;
	}
	
	public static void writeCharacter(Character c, String file) {//Method doesn't work
		
		FileIO.readCharacter(file);
		c.increaseWins();
		
	    try {
	    	
	    	FileWriter fw = new FileWriter(file);
		    BufferedWriter wr = new BufferedWriter(fw);
		    
		    wr.write(c.toString());
		    
	        wr.close();
	    } catch(IOException e) {
	    	
	        e.printStackTrace();
	    }
	}
		
}
