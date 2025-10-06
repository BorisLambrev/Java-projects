import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * CCCS-300-766
 * 
 * BattleGame: This program creates the environment of a Battle game.
 * 
 * @author Boris Lambrev
 * @version (2021-12-09)
 * 
 * McGill ID 261053070 boris.lambrev@mail.mcgill.ca
 */

public class BattleGame {
	
	private static Random randObj;

	public static void main(String[] args) {
		
		playGame("CharacterTest1","CharacterTest2","spellz");
		
	}
	
	public static void playGame(String playerFile, String monsterFile, String spellFile) {
		
		ArrayList<Spell> spells = FileIO.readSpells(spellFile);
		
		if(spells == null) {
			System.out.println("The game will be played without spells. ");
		}
		
		Scanner in = new Scanner (System.in);
		String command;
		
		String sN = ""; //Spell name
		double a1 = 0;
		double a2;
		double t1;
		double t2;
		
		boolean validAttack = false;
		
		Random obj;
		boolean isValid = false;
		
		Character c1 = FileIO.readCharacter(playerFile);
		
		c1.setSpells(spells);
		
		Character c2 = FileIO.readCharacter(monsterFile);
		
		if(c1 == null || c2 == null) {
			System.out.println("The game cannot be played!");
		}else {
			isValid = true;
		}
		
		System.out.println();
		System.out.println(c1);
		System.out.println();
		System.out.println(c2);
		
		System.out.println("\nHere are the available spells:");
		c1.displaySpells();
		
		while(c1.getCurrHealth() > 0 && c2.getCurrHealth() > 0 && isValid) {
			
			System.out.println("Enter a command: ");
			command = in.nextLine();
			
			if (command.equals("attack")) {
				a1 = c1.getAttackDamage(123);
				t2 = c2.takeDamage(a1);
				a2 = c2.getAttackDamage(123);
				t1 = c1.takeDamage(a2);
				attackMessage(c1,c2,a1,a2,t1,t2);
			}else if (command.equals("quit")) {
				System.out.println();
				System.out.println("Goodbye!");
				isValid = false;
			}else {
				
				for(int i = 0; i < spells.size(); i++) {
					if(command.equalsIgnoreCase(spells.get(i).getName())) {
						sN = command;
						a1 = spells.get(i).getMagicDamage(123);
						validAttack = true;
					}
				}
				
				if(validAttack) {
					t2 = c2.takeDamage(a1);
					a2 = c2.getAttackDamage(123);
					t1 = c1.takeDamage(a2);
					attackMessageWithSpell(c1,c2,a1,a2,t1,t2,sN);
					System.out.println();
				}else {
					t2 = c2.takeDamage(a1);
					a2 = c2.getAttackDamage(123);
					t1 = c1.takeDamage(a2);
					unknownSpell(c1,c2,a1,a2,t1,t2,command);
				}
		
			}
		}
		
		if(c1.getCurrHealth() <= 0) {
			System.out.println(c1.getName() +" was knocked out!");
			System.out.println("\nOh no! You lost!");
			System.out.println("Successfully wrote to file: "+ monsterFile);
			System.out.println(c2.getName() +" has won: "+ Character.increaseWins() +" times.");
		}
		else {
			System.out.println(c2.getName() +" was knocked out!");
			System.out.println("\nFantastic! You Killed the monster!");
			System.out.println("Successfully wrote to file: "+ playerFile);
			System.out.println(c1.getName() +" has won: "+ Character.increaseWins() +" times.");
		}
		
	}
	
	public static void attackMessage(Character c1, Character c2, double attack1, double attack2,
			double takeDamage1, double takeDamage2) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		System.out.print
		("\n"+ c1.getName() + " attacks for "+ df.format(attack1) +" damage!"+
		 "\n"+ c2.getName() + " current health is "+ df.format(takeDamage2) + "."+
		 "\n"+ c2.getName() + " attacks for "+ df.format(attack2) +" damage!"+
		 "\n"+ c1.getName() + " current health is "+ df.format(takeDamage1) + ".");
		System.out.println();
	}
	
	public static void attackMessageWithSpell(Character c1, Character c2, double attack1, 
			double attack2,double takeDamage1, double takeDamage2, String spellName) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		if(attack1 == 0) {
			System.out.println(c1.getName()+" tried to cast "+ spellName +", but they failed."+
			"\n"+ c2.getName() + " attacks for "+ df.format(attack2) +" damage!"+
			 "\n"+ c1.getName() + " current health is "+ df.format(takeDamage1) + ".");
			System.out.println();
		}else {
			System.out.print
			("\n"+ c1.getName() + " casts "+ spellName +" dealing " + df.format(attack1) +" damage!"+
			 "\n"+ c2.getName() + " current health is "+ df.format(takeDamage2) + "."+
			 "\n"+ c2.getName() + " attacks for "+ df.format(attack2) +" damage!"+
			 "\n"+ c1.getName() + " current health is "+ df.format(takeDamage1) + ".");
			System.out.println();
		}
		
	}
	
	public static void unknownSpell (Character c1, Character c2, double attack1, 
			double attack2,double takeDamage1, double takeDamage2, String spellName) {
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		System.out.println(c1.getName()+" tried to cast "+ spellName +", but they don't know that spell."+
				"\n"+ c2.getName() + " attacks for "+ df.format(attack2) +" damage!"+
				 "\n"+ c1.getName() + " current health is "+ df.format(takeDamage1) + ".");
				System.out.println();
	}
}
