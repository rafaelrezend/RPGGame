package com.rpggame.core.conf;

public class Armors {
	
	// TYPES
	public static final byte HELM = 0;
	public static final byte BODY = 1;
	public static final byte BOOTS = 2;
	public static final byte SHIELD = 3;

	// ARMORS
	public static final byte NOHELM = 0;
	public static final byte NOBODY = 1;
	public static final byte NOBOOTS = 2;
	public static final byte NOSHIELD = 3;
	public static final byte LEATHER_SHIELD = 4;
	public static final byte LEATHER_HELM = 5;
	public static final byte LEATHER_BODY = 6;
	public static final byte LEATHER_BOOTS = 7;
	public static final byte IRON_SHIELD = 8;

	public static final String NAME[] = {
		"No helm", // NOHELM
		"No armor", // NOBODY
		"No boots", // NOBOOTS
		"No shield", // NOSHIELD
		"Leather Shield", // LEATHER_SHIELD
		"Leather Helm", // LEATHER_HELM
		"Leather Body", // LEATHER_BODY
		"Leather Boots", // LEATHER_BOOTS
		"Iron Shield" // IRON SHIELD
	};

	public static final byte DEFENSE[] = {
		0, // NOHELM
		2, // NOBODY
		0, // NOBOOTS
		0, // NOSHIELD
		3, // LEATHER_SHIELD
		1, // LEATHER_HELM
		4, // LEATHER_BODY
		1, // LEATHER_BOOTS
		5 // IRON SHIELD
	};

	public static final byte REQUIRED_LEVEL[] = {
		0, // NOHELM
		0, // NOBODY
		0, // NOBOOTS
		0, // NOSHIELD
		0, // LEATHER_SHIELD
		0, // LEATHER_HELM
		0, // LEATHER_BODY
		0, // LEATHER_BOOTS
		3 // IRON SHIELD
	};
	
	public static final byte TYPE[] = {
		HELM, // NOHELM
		BODY, // NOBODY
		BOOTS, // NOBOOTS
		SHIELD, // NOSHIELD
		SHIELD, // LEATHER_SHIELD
		HELM, // LEATHER_HELM
		BODY, // LEATHER_BODY
		BOOTS, // LEATHER_BOOTS
		SHIELD // IRON SHIELD
	};
}
