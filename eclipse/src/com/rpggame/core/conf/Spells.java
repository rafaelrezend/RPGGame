package com.rpggame.core.conf;

public final class Spells {
	
	//LEVEL
	public final static byte MAX_SPELL_LEVEL = 15;
	
	//AFFECTED ATTRIBUTES
	//AFFECTS_HEALTH (health points - positive:damage, negative:heal)
	//AFFECTS_MANA (mana points - positive:drain, negative:heal)
	//AFFECTS_DEXTERITY (percentage: 100 = 1x)
	//AFFECTS_ATTACK_MULT (percentage: 100 = 1x)
	//AFFECTS_DEFENSE_MULT (percentage: 100 = 1x)
	public final static byte AFFECTS_HEALTH 			= 0;
	public final static byte AFFECTS_MANA 				= 1;
	public final static byte AFFECTS_DEXTERITY 			= 2;
	public final static byte AFFECTS_ATTACK_MULT 		= 3;
	public final static byte AFFECTS_DEFENSE_MULT 		= 4;
	public final static byte AFFECTS_FIRE_RESISTANCE	= 5;
	public final static byte AFFECTS_WATER_RESISTANCE	= 6;
	public final static byte AFFECTS_AIR_RESISTANCE 	= 7;
	public final static byte AFFECTS_EARTH_RESISTANCE 	= 8;
	public final static byte RESTORE_HEALTH 			= 9;
	public final static byte RESTORE_DISPEL	 			= 10;
	
	//MAGIC TYPE
	public final static byte FIRE 	= 0;
	public final static byte WATER 	= 1;
	public final static byte AIR 	= 2;
	public final static byte EARTH 	= 3;

	// SPELLS INDEX of the main character
	public final static byte NO_SPELL = 0;
	// FIRE
	public final static byte FIRE_BALL = 1;
	public final static byte FIRE_SPELL_2 = 9;
	public final static byte FIRE_SPELL_3 = 17;
	public final static byte FIRE_SPELL_4 = 25;
	public final static byte FIRE_SPELL_5 = 26;
	public final static byte FIRE_SPELL_6 = 6;
	public final static byte FIRE_SPELL_7 = 7;
	public final static byte FIRE_SPELL_8 = 8;
	// WATER
	public final static byte HEAL_LIFE = 2;
	public final static byte WATER_SPELL_2 = 10;
	public final static byte WATER_SPELL_3 = 11;
	public final static byte WATER_SPELL_4 = 12;
	public final static byte WATER_SPELL_5 = 13;
	public final static byte WATER_SPELL_6 = 14;
	public final static byte WATER_SPELL_7 = 15;
	public final static byte WATER_SPELL_8 = 16;
	// AIR
	public final static byte DISPEL = 3;
	public final static byte AIR_SPELL_2 = 18;
	public final static byte AIR_SPELL_3 = 19;
	public final static byte AIR_SPELL_4 = 20;
	public final static byte AIR_SPELL_5 = 21;
	public final static byte AIR_SPELL_6 = 22;
	public final static byte AIR_SPELL_7 = 23;
	public final static byte AIR_SPELL_8 = 24;
	// EARTH
	public final static byte VULNERABILITY = 4;
	public final static byte STONE_SKIN = 5;
	public final static byte EARTH_SPELL_3 = 27;
	public final static byte EARTH_SPELL_4 = 28;
	public final static byte EARTH_SPELL_5 = 29;
	public final static byte EARTH_SPELL_6 = 30;
	public final static byte EARTH_SPELL_7 = 31;
	public final static byte EARTH_SPELL_8 = 32;
	
	//[spell index][spell level]
	public final static String NAME[] = { "No Spell", // NO_SPELL
		"Fireball", // FIRE_BALL
		"Heal Life", // HEAL_LIFE
		"Dispel", // DISPEL
		"Vulnerability", // VULNERABILITY
		"Stone Skin" // STONE_SKIN
	};
	
	//[spell index][spell level]
	public final static short DAMAGE[][] = {
		// 0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15
		{  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0}, // NO_SPELL
		{  0,  10,  15,  20,  25,  30,  35,  40,  45,  50,  55,  60,  65,  70,  75,  80}, // FIRE_BALL (-health)
		{  0,  15,  20,  25,  30,  35,  40,  45,  50,  60,  70,  80,  90, 100, 120, 150}, // HEAL_LIFE (+health)
		{  0,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1}, // DISPEL (remove effects)
		{100,  70,  70,  70,  50,  50,  50,  40,  40,  30,  20,  10,   0,   0,   0,   0}, // VULNERABILITY (-defense)
		{100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 215, 230, 250, 270, 300} // STONE_SKIN (+defense)
	};
	
	public final static byte MANA[] = { 0, // NO_SPELL
		10, 	// FIRE_BALL
		 8, 	// HEAL_LIFE
		 7, 	// DISPEL
		10, 	// VULNERABILITY
		12 		// STONE_SKIN
	};
	
	public final static byte ELEMENT[] = { FIRE, // NO_SPELL
		FIRE, 	// FIRE_BALL
		WATER, 	// HEAL_LIFE
		AIR, 	// DISPEL
		EARTH, 	// VULNERABILITY
		EARTH  	// STONE_SKIN
	};
	
	public final static byte AFFECTED_PARAMETER[] = { AFFECTS_HEALTH, // NO_SPELL
		 AFFECTS_HEALTH, 		// FIRE_BALL
		 RESTORE_HEALTH, 		// HEAL_LIFE (special case: affects health, but on himself)
		 RESTORE_DISPEL, 		// DISPEL (special case: reset all affected multipliers)
		 AFFECTS_DEFENSE_MULT, 	// VULNERABILITY
		 AFFECTS_DEFENSE_MULT 	// STONE_SKIN
	};
	
	public final static byte ACTIVE_TURNS[] = { 0, // NO_SPELL
		 0, // FIRE_BALL
		 0, // HEAL_LIFE
		 0, // DISPEL (special case: reset all multipliers)
		 5, // VULNERABILITY
		 5, // STONE_SKIN
	};
}
