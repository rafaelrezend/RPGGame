/**
 * CASTING SPELL TEST Try to cast spell without mana and level. Try to cast
 * without. Try to cast without level. Successfully cast fireball twice,
 * then fail due to out of mana.
 */

package com.rpggame.test;

import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Spells;
import com.rpggame.core.conf.Types;

import android.test.AndroidTestCase;

public class CastingSpellTest extends AndroidTestCase {

	Player player;
	Enemy enemy;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);

		enemy = new Enemy(Enemies.GOBLIN_MAGE);
	}

	public void testPlayerSpellWithoutManaAndLevel() throws Throwable {

		assertEquals(0, player.getManaCurr());
		assertEquals(0, player.getSpellLevel(Spells.FIRE_BALL));
		assertEquals(0, player.castSpell(Spells.FIRE_BALL));
	}

	public void testPlayerSpellWithoutLevel() throws Throwable {

		player.setManaMax((short) 20);
		assertEquals(20, player.setManaCurrToManaMax());
		assertEquals(0, player.getSpellLevel(Spells.FIRE_BALL));
		assertEquals(0, player.castSpell(Spells.FIRE_BALL));
	}

	public void testPlayerSpellWithoutMana() throws Throwable {

		assertEquals(0, player.getManaCurr());
		assertEquals(Errorcode.SUCCESS, player.increaseLevelSpellsArray(Spells.FIRE_BALL));
		assertEquals(Errorcode.SUCCESS,	player.increaseLevelSpellsArray(Spells.FIRE_BALL));
		assertEquals(2, player.getSpellLevel(Spells.FIRE_BALL));
		assertEquals(-1, player.castSpell(Spells.FIRE_BALL));
	}

	public void testPlayerSpellSuccessThenFail() throws Throwable {

		// setting enough mana for 2.5 Fireball Spell
		int requiredMana = (int) ((2.5) * Spells.MANA[Spells.FIRE_BALL]);
		player.setManaMax((short) requiredMana);
		assertEquals(requiredMana, player.setManaCurrToManaMax());

		// setting spell level
		assertEquals(Errorcode.SUCCESS,
				player.increaseLevelSpellsArray(Spells.FIRE_BALL));
		assertEquals(Errorcode.SUCCESS,
				player.increaseLevelSpellsArray(Spells.FIRE_BALL));
		assertEquals(2, player.getSpellLevel(Spells.FIRE_BALL));

		// successfully cast spell twice and check mana
		assertEquals(Spells.DAMAGE[Spells.FIRE_BALL][2],
				player.castSpell(Spells.FIRE_BALL));
		requiredMana -= Spells.MANA[Spells.FIRE_BALL];
		assertEquals(requiredMana, player.getManaCurr());

		assertEquals(Spells.DAMAGE[Spells.FIRE_BALL][2],
				player.castSpell(Spells.FIRE_BALL));
		requiredMana -= Spells.MANA[Spells.FIRE_BALL];
		assertEquals(requiredMana, player.getManaCurr());

		// not enough mana to cast again
		assertEquals(-1, player.castSpell(Spells.FIRE_BALL));
		assertEquals(requiredMana, player.getManaCurr());
	}

}

/*
 * public static void go() {
 * 
 * Player player = new Player("João", Types.RACE_ELF, Types.CLASS_MAGE);
 * 
 * short appliedMana = 30;
 * 
 * player.setManaMax(appliedMana);
 * 
 * //player doesn't have mana yet Log.v("RPG", "Current Mana: " +
 * player.getManaCurr() + " (expected = 0)"); Log.v("RPG",
 * "Level of Fireball = " + player.getSpellLevel(Spells.FIRE_BALL) +
 * " (expected = 0)"); Log.v("RPG", "Mana needed to cast = " +
 * Spells.MANA[Spells.FIRE_BALL] + " (expected = 10)"); Log.v("RPG",
 * "Trying to cast Fireball: damage = " +
 * player.castAttackSpell(Spells.FIRE_BALL) + " (expected = 0)");
 * 
 * Log.v("RPG", "Setting Mana to max: mana = " + player.setManaCurrToManaMax() +
 * " (expected = " + appliedMana + ")"); Log.v("RPG",
 * "Casting level 0 Fireball: damage = " +
 * player.castAttackSpell(Spells.FIRE_BALL) + " (expected = 0)");
 * 
 * Log.v("RPG", "Increasing 2 levels for Fireball.");
 * player.increaseLevelSpellsArray(Spells.FIRE_BALL);
 * player.increaseLevelSpellsArray(Spells.FIRE_BALL);
 * 
 * Log.v("RPG", "Level of Fireball = " + player.getSpellLevel(Spells.FIRE_BALL)
 * + " (expected = 2)"); Log.v("RPG", "Mana needed to cast = " +
 * Spells.MANA[Spells.FIRE_BALL] + " (expected = 10)"); Log.v("RPG",
 * "Casting level 2 Fireball: damage = " +
 * player.castAttackSpell(Spells.FIRE_BALL) + " (expected = 22)"); Log.v("RPG",
 * "Current Mana: " + player.getManaCurr() + " (expected = 20)");
 * 
 * Log.v("RPG", "Casting level 2 Fireball: damage = " +
 * player.castAttackSpell(Spells.FIRE_BALL) + " (expected = 22)"); Log.v("RPG",
 * "Current Mana: " + player.getManaCurr() + " (expected = 10)");
 * 
 * Log.v("RPG", "Casting level 2 Fireball: damage = " +
 * player.castAttackSpell(Spells.FIRE_BALL) + " (expected = 22)"); Log.v("RPG",
 * "Current Mana: " + player.getManaCurr() + " (expected = 0)");
 * 
 * Log.v("RPG", "Casting level 2 Fireball: damage = " +
 * player.castAttackSpell(Spells.FIRE_BALL) + " (expected = -1)"); Log.v("RPG",
 * "Current Mana: " + player.getManaCurr() + " (expected = 0)");
 * 
 * }
 */

