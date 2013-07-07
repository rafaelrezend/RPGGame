package com.rpggame.core.conf;

public class Weapons {
	
	// WEAPON CODES
	public static final byte NOWEAPON				= 0;
	public static final byte RUSTY_SWORD			= 1;
	public static final byte WOODEN_BOW			 	= 2;
	public static final byte WOODEN_STAFF			= 3;
	public static final byte IRON_SWORD				= 4;
	public static final byte IRON_BOW			 	= 5;
	public static final byte IRON_STAFF			 	= 6;
	public static final byte STEEL_SWORD 			= 7;
	public static final byte STEEL_BOW			 	= 8;
	public static final byte STEEL_STAFF			= 9;

	
	public static final String NAME[] = {
		"No Weapon", 			//NOWEAPON
		"Rusty Sword", 			//RUSTY_SWORD
		"Wooden Bow", 			//WOODEN_BOW
		"Wooden Staff", 		//WOODEN_STAFF
		"Iron Sword",			//IRON_SWORD
		"Iron Bow",				//IRON_BOW
		"Iron Staff",			//IRON_STAFF
		"Steel Sword",			//STEEL_SWORD
		"Steel Bow",			//STEEL_BOW
		"Steel Staff"};			//STEEL_STAFF
	
	public static final short MIN_ATTACK[] = {
		1,			//NOWEAPON
		3,			//RUSTY_SWORD
		2,			//WOODEN_BOW
		1,			//WOODEN_STAFF
		5,			//IRON_SWORD
		4,			//IRON_BOW
		3,			//IRON_STAFF
		10,			//STEEL_SWORD
		8,			//STEEL_BOW
		6};			//STEEL_STAFF
	
	public static final short MAX_ATTACK[] = {
		3,			//NOWEAPON
		6,			//RUSTY_SWORD
		5,			//WOODEN_BOW
		4,			//WOODEN_STAFF
		8,			//IRON_SWORD
		7,			//IRON_BOW
		5,			//IRON_STAFF
		14,			//STEEL_SWORD
		12,			//STEEL_BOW
		10};		//STEEL_STAFF
	
	public static final boolean IS_ONE_HAND[] = {
		true,			//NOWEAPON
		true,			//RUSTY_SWORD
		false,			//WOODEN_BOW
		false,			//WOODEN_STAFF
		true,			//IRON_SWORD
		false,			//IRON_BOW
		false,			//IRON_STAFF
		true,			//STEEL_SWORD
		false,			//STEEL_BOW
		false};			//STEEL_STAFF
	
	public static final short REQUIRED_LEVEL[] = {
		0,			//NOWEAPON
		0,			//RUSTY_SWORD
		0,			//WOODEN_BOW
		0,			//WOODEN_STAFF
		0,			//IRON_SWORD
		0,			//IRON_BOW
		0,			//IRON_STAFF
		0,			//STEEL_SWORD
		0,			//STEEL_BOW
		0};			//STEEL_STAFF

	public static final short DRAWING[] = {};
	
}
