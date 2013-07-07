package com.rpggame.core;

import java.util.Random;

import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Spells;
import com.rpggame.core.conf.Types;

public class Enemy extends Character{

	// enemy identifier
	private short enemyId;

	// primary variable attributes
	private short dexterity;
	private short affectedDexterity;

	private short avgAttack;
	private short defense;

	public Enemy(short enemyId) {
		this.enemyId = enemyId;
		this.name = Enemies.NAME[enemyId];
		this.dexterity = Enemies.DEXTERITY[enemyId];
		this.manaCurr = this.manaMax = Enemies.MAX_MANA[enemyId];
		this.experience = Enemies.PROVIDED_EXP[enemyId];
		this.avgAttack = Enemies.AVG_ATTACK[enemyId];
		this.defense = Enemies.DEFENSE[enemyId];
		this.affectedAttackMultiplier = 100;
		this.affectedDefenseMultiplier = 100;
		this.affectedDexterity = 100;
		this.setHealth();
		this.setSpellArray();
		this.setMagicResistance();
	}
	
	/**
	 * Set the spellArray from ENEMY_SPELL_SET
	 */
	private void setSpellArray() {
		spellsArray = new byte[8];
		if (Enemies.ENEMY_SPELL_SET_ID[enemyId] > 0)
			// get spellSet
			spellsArray = Enemies.ENEMY_SPELL_SET[Enemies.ENEMY_SPELL_SET_ID[enemyId]];
	}
	
	/**
	 * Set magic resistance based on the descriptive short Enemies.MAGIC_RESISTANCE.
	 */
	private void setMagicResistance() {
		this.magicResistance = new byte[4];
		this.magicResistance[0] = (byte)(((Enemies.MAGIC_RESISTANCE[enemyId] & 0xF)*100)/0xF);
		this.magicResistance[1] = (byte)((((Enemies.MAGIC_RESISTANCE[enemyId]>>4) & 0xF)*100)/0xF);
		this.magicResistance[2] = (byte)((((Enemies.MAGIC_RESISTANCE[enemyId]>>8) & 0xF)*100)/0xF);
		this.magicResistance[3] = (byte)((((Enemies.MAGIC_RESISTANCE[enemyId]>>12) & 0xF)*100)/0xF);
	}
	
	/**
	 * Set a random health based on a variance given by
	 * Types.ENEMY_HEALTH_VARIANCE
	 */
	private void setHealth() {
		Random rdn = new Random();
		// calculate variance
		short rdnByte = (short) (rdn.nextInt(2 * Types.ENEMY_HEALTH_VARIANCE) - Types.ENEMY_HEALTH_VARIANCE);
		this.healthCurr = this.healthMax = (short) (((100 + rdnByte) * Enemies.AVG_HEALTH[enemyId]) / 100);
	}

	/**
	 * Calculate the attack from an average value + variance
	 * 
	 * @return the attack value
	 */
	public short attack(boolean isCritical) {
		short finalAttack = 0;
		if (isCritical)
			finalAttack = (short)((this.avgAttack * (100 + Types.ENEMY_ATTACK_VARIANCE)) / 100);
		else {
			Random rdn = new Random();
			// calculate variance
			short rdnByte = (short) (rdn.nextInt(2 * Types.ENEMY_ATTACK_VARIANCE) - Types.ENEMY_ATTACK_VARIANCE);
			finalAttack = (short) (((100 + rdnByte) * this.avgAttack) / 100);
		}
		// return with multiplier
		return (short) ((finalAttack * this.affectedAttackMultiplier) / 100);
	}

	/**
	 * @return the enemyId
	 */
	public short getEnemyId() {
		return enemyId;
	}

	/**
	 * @return the dexterity
	 */
	public short getDexterity() {
		return (short) (dexterity * affectedDexterity / 100);
	}

	/**
	 * @return the defense
	 */
	public short getDefense() {
		return (short) ((this.defense * this.affectedDefenseMultiplier) / 100);
	}


	
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

	@Override
	public byte getSpellLevel(byte index) {
		for (int i = 0; i < this.spellsArray.length; i+=2)
			if (this.spellsArray[i] == index)
				return this.spellsArray[i+1];
		return 0; // enemy doesn't have the spell
	}
}
