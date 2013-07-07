package com.rpggame.core.conf;

public final class Types {

	// RACES of the main character (index)
	public final static byte RACE_HUMAN = 1;
	public final static byte RACE_ELF = 2;
	public final static byte RACE_ORC = 3;

	// CLASSES of the main character (index)
	public final static byte CLASS_FIGHTER = 1;
	public final static byte CLASS_ARCHER = 2;
	public final static byte CLASS_MAGE = 3;

	// LEVELS of character
	public final static byte MAX_CHARACTER_LEVEL = 15;
	// LEVELS of PPRIMARY ATTRIBUTES
	public final static byte MAX_PRIMARY_ATTRIBUTES_LEVEL = 100;

	// PRIMARY ATTRIBUTES of character (index)
	public final static byte ATT_STRENGTH = 1;
	public final static byte ATT_MAGIC = 2;
	public final static byte ATT_DEXTERITY = 3;
	public final static byte ATT_ENDURANCE = 4;
	
	// PRIMARY MULTIPLIERS
	//public final static byte STRENGTH_ATTACK = 1;

	// PERSONAL STORAGE
	public final static byte STORAGE_WEAPONS = 6;
	public final static byte STORAGE_ARMOR = 12;
	public final static byte STORAGE_ITEMS = 40;
	
	// Attack of the enemy varies from AVG_ATTACK +/- variance(%)
	public final static byte ENEMY_ATTACK_VARIANCE = 15;
	public final static byte ENEMY_HEALTH_VARIANCE = 10;

}
