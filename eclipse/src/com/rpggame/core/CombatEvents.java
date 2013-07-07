package com.rpggame.core;

import java.util.Random;

import android.util.Log;

import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Spells;

public final class CombatEvents {

	/**
	 * Attack: Get the damage from attacker and subtract from the health of
	 * defender.
	 * 
	 * @param char1
	 *            attacker
	 * @param char2
	 *            defender
	 * @return Success or Blocked attack
	 */
	public static byte charAttackChar(Character char1, Character char2) {
		// get damage of attacker
		short damage = char1.attack(false);
		// get defense of defender
		short defense = char2.getDefense();
		Log.v("RPG", "Final damage = attack(" + damage + ") - defense("
				+ defense + ") = " + (damage - defense));
		// get defense of defender
		if ((damage - defense) <= 0)
			return Errorcode.ATTACK_BLOCKED;
		// apply damage to defender health
		char2.increaseHealthCurr((short) (defense - damage));
		return Errorcode.SUCCESS;
	}

	/**
	 * Cast Spell Against Char: Spells can be a damage, restore, dispel or
	 * attribute modifier If Damage spell, the magic resistance absorbs part of
	 * damage. If Restore, magic resistance is ignored. If Dispel, magic
	 * resistance is ignored. If attribute modifier, magic resistance is ignored
	 * when attribute is getting improved. Otherwise, the resistance corresponds
	 * to the probability to skip the effect.
	 * 
	 * @param spell
	 *            index of spell
	 * @param char1
	 *            spell caster
	 * @param char2
	 *            defender
	 * @return Success attack or "not enough mana" error
	 */
	public static byte charCastSpellAgainstChar(Character char1, byte spell,
			Character char2) {
		// get spell damage from attacker
		short spellValue = char1.castSpell(spell);
		if (spellValue == -1)
			return Errorcode.NOT_ENOUGH_MANA;
		if (spellValue == 0)
			return Errorcode.SPELL_NOT_AVAILABLE_FOR_CHARACTER;

		byte defense;

		// perform according to affected parameter
		switch (Spells.AFFECTED_PARAMETER[spell]) {

		case Spells.AFFECTS_HEALTH: // affects health = damage
			defense = char2.getMagicResistance(Spells.ELEMENT[spell]);
			short spellDamage = (short) (spellValue * (100 - defense) / 100);
			Log.v("RPG", "Spell damage = spell(" + spellValue
					+ ") -  magic defense(" + defense + "%) = " + spellDamage);
			// apply damage to defender health
			char2.increaseHealthCurr((short) (-spellDamage));
			return Errorcode.SUCCESS;

		case Spells.RESTORE_HEALTH: // restore health ignores defense
			char2.increaseHealthCurr((short) spellValue);
			return Errorcode.SUCCESS;

		case Spells.RESTORE_DISPEL: // dispel = reset affected multipliers
			char2.setAffectedAttackMultiplier((short) 100);
			char2.setAffectedDefenseMultiplier((short) 100);
			// TODO: reset more parameters here
			return Errorcode.SUCCESS;

		case Spells.AFFECTS_ATTACK_MULT: // change the attack multiplier
			// if it's an improve effect (spellValue > 100) ignores magic
			// defense
			if (spellValue > 100) {
				char2.setAffectedAttackMultiplier(spellValue);
				return Errorcode.SUCCESS;
			}
			// reduce the attack multiplier according to magic defense
			Random rdnA = new Random();
			// calculate probability
			int rdnValueA = rdnA.nextInt(100);
			defense = char2.getMagicResistance(Spells.ELEMENT[spell]);
			if (rdnValueA < defense)
				return Errorcode.MAGIC_RESISTED;
			char2.setAffectedAttackMultiplier(spellValue);
			return Errorcode.SUCCESS;

		case Spells.AFFECTS_DEFENSE_MULT: // change the defense multiplier
			// if it's an improve effect (spellValue > 100) ignores magic
			// defense
			if (spellValue > 100) {
				char2.setAffectedDefenseMultiplier(spellValue);
				return Errorcode.SUCCESS;
			}
			// reduce the attack multiplier according to magic defense
			Random rdnD = new Random();
			// calculate probability
			int rdnValueD = rdnD.nextInt(100);
			defense = char2.getMagicResistance(Spells.ELEMENT[spell]);
			if (rdnValueD < defense)
				return Errorcode.MAGIC_RESISTED;
			char2.setAffectedDefenseMultiplier(spellValue);
			return Errorcode.SUCCESS;

		default:
			return Errorcode.SPELL_NOT_AVAILABLE_FOR_CHARACTER;
		}
	}

}
