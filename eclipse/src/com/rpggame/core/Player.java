package com.rpggame.core;

import java.util.Random;

import com.rpggame.core.conf.Armors;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Levels;
import com.rpggame.core.conf.Spells;
import com.rpggame.core.conf.Types;
import com.rpggame.core.conf.Weapons;

public class Player extends Character {

	private byte race;
	private byte classe;
	private byte level;
	private int money;

	// primary variable attributes
	// (1)strength, (2)magic, (3)dexterity, (4)endurance
	private short[] primaryAttributes = new short[4];
	// effects during combat
	private short[] affectedPrimaryAttributes = new short[4];

	private byte equippedWeapon;
	private byte equippedArmor[] = new byte[4];

	// private byte equippedNecklace = Armor.RUSTY_SWORD;

	/**
	 * Constructor of the class
	 * 
	 * @param name
	 * @param race
	 * @param classe
	 */
	public Player(String name, byte race, byte classe) {
		super();
		this.name = name;
		this.race = race;
		this.classe = classe;
		this.money = 0;
		this.experience = 0;
		this.level = 1;
		this.healthCurr = 0;
		this.healthMax = 0;
		this.manaCurr = 0;
		this.manaMax = 0;
		this.equippedWeapon = Weapons.NOWEAPON;
		this.equippedArmor[Armors.SHIELD] = Armors.NOSHIELD;
		this.equippedArmor[Armors.HELM] = Armors.NOHELM;
		this.equippedArmor[Armors.BODY] = Armors.NOBODY;
		this.equippedArmor[Armors.BOOTS] = Armors.NOBOOTS;
		this.affectedAttackMultiplier = 100;
		this.affectedDefenseMultiplier = 100;
		this.spellsArray = new byte[48];
	}

	/**
	 * @return the race
	 */
	public byte getRace() {
		return race;
	}

	/**
	 * @return the class
	 */
	public byte getClasse() {
		return classe;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money
	 *            the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	/**
	 * @return the level
	 */
	public byte getLevel() {
		return level;
	}

	/**
	 * Upgrade to next level
	 * 
	 * @return level
	 */
	public short upToNextLevel() {
		if (level < Types.MAX_CHARACTER_LEVEL) {
			this.level++;
			this.experience = Levels.PLAYER[this.level];
			return Errorcode.SUCCESS;
		}
		return Errorcode.CHARACTER_MAX_LEVEL_REACHED;
	}

	/**
	 * @param index
	 *            the primary attribute (strength, magic, dexterity, endurance)
	 * @return the value of attribute
	 */
	public short getPrimaryAttributes(byte index) {
		return (short) (this.primaryAttributes[index] + this.affectedPrimaryAttributes[index]);
	}

	/**
	 * @param index
	 *            the primary attribute (strength, magic, dexterity, endurance)
	 * @param points
	 *            amount to increase
	 */
	public short increasePrimaryAttributes(byte index, byte points) {
		if (this.primaryAttributes[index] + points <= Types.MAX_PRIMARY_ATTRIBUTES_LEVEL) {
			this.primaryAttributes[index] += points;
			return Errorcode.SUCCESS;
		}
		return Errorcode.PRIMARY_ATT_MAX_LEVEL_REACHED;
	}

	/**
	 * Apply effects on primary attributes during battle
	 * 
	 * @param index
	 *            of the primary attribute
	 * @param value
	 *            to be applied on attribute
	 */
	public void setAffectedPrimaryAttributes(short index, short value) {
		this.affectedPrimaryAttributes[index] = value;
	}

	/**
	 * Get affected primary attribute
	 * 
	 * @param index
	 *            of the primary attribute
	 * @return value
	 */
	public short getAffectedPrimaryAttributes(short index) {
		return this.affectedPrimaryAttributes[index];
	}

	/**
	 * Set values of affectedPrimaryAttributes to 0
	 */
	public void resetAffectedPrimaryAttributes() {
		for (int i = 0; i < this.affectedPrimaryAttributes.length; i++)
			this.affectedPrimaryAttributes[i] = 0;
	}

	/**
	 * @param experience
	 *            the experience to apply (can be positive or negative)
	 * @return total experience
	 */
	public int addExperience(int exp) {
		if (exp > 0) { // experience added
			this.experience += exp;
			// check if level increased
			for (byte i = this.level; this.experience >= Levels.PLAYER[i]; i++) {
				this.level = i;
			}
			// limit to the max allowed level
			if (this.level > Types.MAX_CHARACTER_LEVEL)
				this.level = Types.MAX_CHARACTER_LEVEL;
		} else { // experience removed
			// player cannot decrease his level!!!
			this.experience -= Math.min(
					(this.experience - Levels.PLAYER[this.level]), (-1 * exp));
		}
		return this.experience;
	}

	/**
	 * Equip a weapon
	 * 
	 * @param weapon
	 */
	public byte equipWeapon(byte weapon) {
		if (Weapons.REQUIRED_LEVEL[weapon] > this.level)
			return Errorcode.HIGHER_LEVEL_REQUIRED;
		// if weapon is two-handed, shield is removed
		if (!Weapons.IS_ONE_HAND[weapon])
			equippedArmor[Armors.SHIELD] = Armors.NOSHIELD;
		this.equippedWeapon = weapon;
		return Errorcode.SUCCESS;
	}

	/**
	 * @return the equippedWeapon
	 */
	public byte getEquippedWeapon() {
		return equippedWeapon;
	}

	/**
	 * Equip a shield
	 * 
	 * @param shield
	 * @return status of operation
	 */
	public byte equipArmor(byte armor) {
		// check the required level for user
		if (Armors.REQUIRED_LEVEL[armor] > this.level)
			return Errorcode.HIGHER_LEVEL_REQUIRED;
		// check if it's a shield and the weapon is two-handed
		if ((Armors.TYPE[armor] == Armors.SHIELD)
				&& (!Weapons.IS_ONE_HAND[equippedWeapon]))
			return Errorcode.TWO_HANDED_WEAPON_EQUIPPED;
		// armor can be equipped
		equippedArmor[Armors.TYPE[armor]] = armor;
		return Errorcode.SUCCESS;
	}

	/**
	 * @param index
	 *            of the armor
	 * @return the equippedArmor
	 */
	public byte getEquippedArmor(byte index) {
		return equippedArmor[index];
	}

	/**
	 * Get the maximum attack parameter
	 * 
	 * @return maximum attack value
	 */
	public short getAttackMax() {
		return (short) ((Weapons.MAX_ATTACK[equippedWeapon] * this.affectedAttackMultiplier) / 100);
	}

	/**
	 * Get the minimum attack parameter
	 * 
	 * @return minimum attack value
	 */
	public short getAttackMin() {
		return (short) ((Weapons.MIN_ATTACK[equippedWeapon] * this.affectedAttackMultiplier) / 100);
	}

	/**
	 * Calculate the attack from a random number
	 * 
	 * @param isCritical
	 *            denotes the critical condition
	 * @return the attack value
	 */
	public short attack(boolean isCritical) {
		short finalAttack = 0;
		if (isCritical) // in case of Rage attack or similar
			return this.getAttackMax();
		else { // regular random attack
			Random rdn = new Random();
			short rdnByte = (short) rdn.nextInt(this.getAttackMax()
					- getAttackMin() + 1);
			finalAttack = (short) (rdnByte + getAttackMin());
		}
		return (short) finalAttack;
	}

	/**
	 * @return the defense
	 */
	public short getDefense() {
		short finalDefense = 0;
		for (int i = 0; i < equippedArmor.length; i++)
			finalDefense += Armors.DEFENSE[equippedArmor[i]];
		// DefenseMultiplier applied to finalDefense
		return (short) ((finalDefense * this.affectedDefenseMultiplier) / 100);
	}

	/**
	 * @param index
	 *            of the spell
	 * @return the referenced spell
	 */
	public byte getSpellLevel(byte index) {
		return spellsArray[index];
	}

	/**
	 * @param index
	 *            of the spell
	 */
	public short increaseLevelSpellsArray(byte index) {
		if (spellsArray[index] < Spells.MAX_SPELL_LEVEL) {
			spellsArray[index]++;
			return Errorcode.SUCCESS;
		}
		return Errorcode.SPELL_MAX_LEVEL_REACHED;
	}

	/**
	 * @param magicResistance
	 *            the magicResistance to set
	 */
	public void setMagicResistance(byte index, byte value) {
		this.magicResistance[index] = (byte) Math.min(100, value);
	}

}
