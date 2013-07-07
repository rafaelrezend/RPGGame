package com.rpggame.core.conf;

public final class Errorcode {
	
	public static final byte SUCCESS = 0;
	public static final byte TWO_HANDED_WEAPON_EQUIPPED = 1;
	public static final byte HIGHER_LEVEL_REQUIRED = 2;
	public static final byte CHARACTER_MAX_LEVEL_REACHED = 3;
	public static final byte PRIMARY_ATT_MAX_LEVEL_REACHED = 4;
	public static final byte SPELL_NOT_AVAILABLE_FOR_CHARACTER = 5;
	public static final byte SPELL_MAX_LEVEL_REACHED = 6;
	public static final byte ATTACK_BLOCKED = 7;
	public static final byte NOT_ENOUGH_MANA = 8;
	public static final byte MAGIC_RESISTED = 9;
	
	
	public static final String[] ERRORMESSAGE = {
		"Success",
		"You must remove your two-hands weapon first",
		"Higher level required",
		"Maximum level reached",
		"Maximum attribute level reached",
		"",
		"Maximum spell level reached",
		"Attack blocked!",
		"Not Enough Mana",
		"Magic Resisted"
	};
	
}
