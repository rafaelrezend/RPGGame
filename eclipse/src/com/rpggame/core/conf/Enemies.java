package com.rpggame.core.conf;

public final class Enemies {

	// ENEMY CODES
	public static final byte NOENEMY = 0;
	public static final byte GOBLIN = 1;
	public static final byte GOBLIN_WARRIOR = 2;
	public static final byte GOBLIN_MAGE = 3;
	public static final byte WOLF = 4;

	public static final String NAME[] = { "No Enemy", // NOENEMY
			"Goblin", 				// GOBLIN
			"Goblin Warrior", 		// GOBLIN_WARRIOR
			"Goblin Mage", 			// GOBLIN_MAGE
			"Wolf" 					// WOLF
	};

	public static final short AVG_ATTACK[] = { 0, // NOENEMY
			7, 			// GOBLIN
			15, 		// GOBLIN_WARRIOR
			4, 			// GOBLIN_MAGE
			5 			// WOLF
	};

	public static final short DEXTERITY[] = { 0, // NOENEMY
			7, 			// GOBLIN
			6, 			// GOBLIN_WARRIOR
			7, 			// GOBLIN_MAGE
			8 			// WOLF
	};

	public static final short DEFENSE[] = { 0, // NOENEMY
			1, 			// GOBLIN
			3, 			// GOBLIN_WARRIOR
			1, 			// GOBLIN_MAGE
			0			// WOLF
	};

	public static final short AVG_HEALTH[] = { 0, // NOENEMY
			25, 		// GOBLIN
			40, 		// GOBLIN_WARRIOR
			30, 		// GOBLIN_MAGE
			18 			// WOLF
	};

	public static final short MAX_MANA[] = { 0, // NOENEMY
			0, 			// GOBLIN
			0, 			// GOBLIN_WARRIOR
			40,			// GOBLIN_MAGE
			0			// WOLF
	};

	public static final short PROVIDED_EXP[] = { 0, // NOENEMY
			20, 		// GOBLIN
			40, 		// GOBLIN_WARRIOR
			50,			// GOBLIN_MAGE
			15			// WOLF
	};
	
	// enemies have a set of spell defined by SPELL_SET
	public static final byte ENEMY_SPELL_SET_ID[] = { 0, // NOENEMY
			0, 			// GOBLIN
			0, 			// GOBLIN_WARRIOR
			1, 			// GOBLIN_MAGE
			0			// WOLF
	};
	
	public static final short MAGIC_RESISTANCE[] = { 0x0000, // NOENEMY
			0x0000, 	// GOBLIN
			0x0000, 	// GOBLIN_WARRIOR
			0x0000,		// GOBLIN_MAGE
			0x0000		// WOLF
	};

	
	// [enemySpellSetId][pair(spell, level)]
	public static final byte ENEMY_SPELL_SET[][] = {
		{Spells.NO_SPELL, 0, Spells.NO_SPELL, 0, Spells.NO_SPELL, 0, Spells.NO_SPELL, 0}, // NOSPELL
		{Spells.FIRE_BALL, 1, 0, 0, 0, 0, 0, 0} // GOBLIN_MAGE 
	};
	

	public static final short DRAWING[] = {};

}
