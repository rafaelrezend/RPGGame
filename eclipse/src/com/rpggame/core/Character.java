/**
 * 
 */
package com.rpggame.core;

import com.rpggame.core.conf.Spells;

public abstract class Character {
	
	protected String name;

	// secondary variable attributes
	protected short healthCurr;
	protected short healthMax;
	protected short manaCurr;
	protected short manaMax;

	protected int experience;

	// multiplier during combat (100 = x1)
	protected short affectedAttackMultiplier;
	protected short affectedDefenseMultiplier;
	
	// player - each position references one spell. values are levels.
	// enemy - spells are given by pairs of spellId and spellLevel
	protected byte[] spellsArray;

	// magic resistance attributes (from 0 to 100%)
	// (0)fire, (1)water, (2)air, (3)earth
	protected byte[] magicResistance = new byte[4];
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set name of character
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * @return the healthMax
	 */
	public short getHealthMax() {
		return healthMax;
	}

	/**
	 * @param healthMax
	 *            the healthMax to set
	 */
	public void setHealthMax(short healthMax) {
		this.healthMax = healthMax;
	}

	/**
	 * @return the manaMax
	 */
	public short getManaMax() {
		return manaMax;
	}

	/**
	 * @param manaMax
	 *            the manaMax to set
	 */
	public void setManaMax(short manaMax) {
		this.manaMax = manaMax;
	}

	/**
	 * @return the healthCurr
	 */
	public short getHealthCurr() {
		return healthCurr;
	}

	/**
	 * @return the manaCurr
	 */
	public short getManaCurr() {
		return manaCurr;
	}

	/**
	 * Set healthCurr equals to healthMax
	 */
	public short setHealthCurrToHealthMax() {
		return this.healthCurr = this.healthMax;
	}

	/**
	 * Set manaCurr equals to manaMax
	 */
	public short setManaCurrToManaMax() {
		return this.manaCurr = this.manaMax;
	}

	/**
	 * Increase healthCurr of points (can be positive or negative)
	 * 
	 * @param points
	 * @return the healthCurr
	 */
	public short increaseHealthCurr(short points) {
		// Apply points
		this.healthCurr += points;
		// Case player died
		if (this.healthCurr < 0)
			this.healthCurr = 0;
		// Case health is bigger than max
		else if (this.healthCurr > this.healthMax)
			this.healthCurr = this.healthMax;
		return this.healthCurr;
	}

	/**
	 * Increase manaCurr (mana restoration) or decrease (negative value).
	 * Return -1 if withdraw mana is greater than current mana.
	 * Return max mana if added mana is greater than max allowed.
	 * 
	 * @param points
	 * @return manaCurr or -1 in case of not enough mana to be subtracted
	 */
	public short increaseManaCurr(short points) {
		// if it's a subtraction, check if player has enough mana
		if (this.manaCurr + points >= 0) {
			this.manaCurr += points;
			// if it's an addition, check if current mana doesn't overflow
			if (this.manaCurr > this.manaMax)
				this.manaCurr = this.manaMax;
			// return resulting mana
			return this.manaCurr;
		} else
			// not enough mana
			return -1;
	}

	/**
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * @return the affectedAttackMultiplier
	 */
	public short getAffectedAttackMultiplier() {
		return affectedAttackMultiplier;
	}

	/**
	 * @param affectedAttackMultiplier
	 *            the affectedAttackMultiplier to set
	 */
	public void setAffectedAttackMultiplier(short affectedAttackMultiplier) {
		this.affectedAttackMultiplier = affectedAttackMultiplier;
	}

	/**
	 * @return the affectedDefenseMultiplier
	 */
	public short getAffectedDefenseMultiplier() {
		return affectedDefenseMultiplier;
	}

	/**
	 * @param affectedDefenseMultiplier
	 *            the affectedDefenseMultiplier to set
	 */
	public void setAffectedDefenseMultiplier(short affectedDefenseMultiplier) {
		this.affectedDefenseMultiplier = affectedDefenseMultiplier;
	}
	
	/**
	 * Get the magic resistance of desired element
	 * @param index of Element
	 * @return magic resistance for desired element
	 */
	public byte getMagicResistance(byte index){
		return this.magicResistance[index];
	}
	
	
	/**
	 * Get the damage of a spell Return damage of spell or (-1) if mana is not
	 * enough or 0 if character doesn't have the spell
	 * 
	 * @param index
	 *            of the spell
	 * @return damage of spell or (-1) if mana is not enough or 0 if character
	 *         doesn't have the spell
	 */
	public short castSpell(byte index) {
		// Note: if player doesn't have the spell (level 0) its damage is zero
		if (this.getSpellLevel(index) > 0) {
			// if player has enough mana
			if (increaseManaCurr((short) (-Spells.MANA[index])) >= 0)
				return Spells.DAMAGE[index][spellsArray[index]];
			// player doesn't have enough mana
			return -1;
		} else
			// player doesn't have the spell
			return 0;
	}
	
	public abstract short attack(boolean isCritical);
	
	public abstract short getDefense();
	
	public abstract byte getSpellLevel(byte index);
	
	


}
