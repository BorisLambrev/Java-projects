import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * CCCS-300-766
 * 
 * Character: This program builds a class on creating a game character.
 * 
 * @author Boris Lambrev
 * @version (2021-12-09)
 * 
 * McGill ID 261053070 boris.lambrev@mail.mcgill.ca
 */

public class Character {
	
	private String name;
	private double attackValue;
	private double maxHealth;
	private double currHealth;
	private static int numWins;
	private static ArrayList<Spell> spells;
	
	public Character(String name, double attackValue, double maxHealth, int numWins) {
		this.name = name;
		currHealth = maxHealth;
		this.attackValue = attackValue;
		this.numWins = numWins;
	}
	
	public String getName() {
		return name;
	}

	public double getAttackValue() {
		return attackValue;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public double getCurrHealth() {
		return currHealth;
	}

	public static int getNumWins() {
		return numWins;
	}
	
	public String toString() {//Makes a model, on the description of the Character in the game
		
		DecimalFormat df = new DecimalFormat("0.00");//Helps include 2 digits after the dot from
													 //the real number instead of just one digit
		
		return "Name : "+ name + "\nHealth : "+ df.format(currHealth) + "\nAttack : "+ 
		df.format(attackValue) + "\nNumber of Wins : "+ numWins;
	}
	
	public double getAttackDamage(int seed) {//Randomize the attack damage with a multiplication

		Random rand = new Random();
		
		double attackAM = 0;// Attack after multiplication
		
		attackAM = getAttackValue() * (0.7 + (1 - 0.7) * rand.nextDouble());
		
		return attackAM;
		
	}
	
	public double takeDamage(double damage) {//Damage the Character takes
		
		return currHealth -= damage; 
		
	}
	
	public static int increaseWins() {
		return numWins++;
	}
	
	public void setSpells(ArrayList<Spell> spells) {//Makes the player access his spells from 
													//spell file
		this.spells = spells;
	}
	
	public void displaySpells() {//Technically calls the method toString() on how to present the spells
		System.out.println(spells);
	}
	
	public double castSpell(String spellName, int index) {//Spell we research
		
		double damage = -1;
		index = 0;
		
		while(index < spells.size()) {
			if(spells.get(index).getName().equalsIgnoreCase(spellName)){
				damage = spells.get(index).getMagicDamage(123);
			}
			index++;
		}
		
		return damage;//Generates the attack power of a chosen spell
	}
	
}
