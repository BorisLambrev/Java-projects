import java.util.Random;

/**
 * CCCS-300-766
 * 
 * Spell: This program builds a spell model with attributes and methods. 
 * 
 * @author Boris Lambrev
 * @version (2021-12-09)
 * 
 * McGill ID 261053070 boris.lambrev@mail.mcgill.ca
 */

public class Spell {
	
	private String name;
	private double minDam;
	private double maxDam;
	private double chance;
	
	public Spell(String name, double minDam, double maxDam, double chance) throws IllegalArgumentException{
		this.name = name;
		this.minDam = minDam;
		this.maxDam = maxDam;
		this.chance = chance;
		if(minDam < 0 || minDam > maxDam || chance < 0 || chance > 1) {
			throw new IllegalArgumentException();
		}
	}
	
	public String getName() {
		return name;
	}
	
	public double getMagicDamage(int seed) {
		
		Random rand = new Random();
		double attackFS = 0; //Attack from spell
		
		double seedInDouble = 0 + (1 - 0) * rand.nextDouble();
		
		if(seedInDouble > chance) {
			attackFS = 0;
		}else {
			attackFS = minDam + (maxDam - minDam) * rand.nextDouble();
		}
		
		return attackFS;
	}
	
	public String toString() {
		
		return "\nName: "+name + "  Damage: "+ minDam +
				"-" + maxDam + "  Chance: "+ chance * 100 + "%";
	}

}
