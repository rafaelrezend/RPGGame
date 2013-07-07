/**
 * HANDLE MAGIC RESISTANCE TEST Check if magic resistance value is
 * successfully retrieved from player and enemy.
 */

package com.rpggame.test;

import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Spells;
import com.rpggame.core.conf.Types;

import android.test.AndroidTestCase;

public class HandleMagicResistanceTest extends AndroidTestCase {

	Player player;
	Enemy enemy;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);

		enemy = new Enemy(Enemies.GOBLIN_MAGE);
	}

	public void testGettingEnemyMagicResistance() throws Throwable {

		assertEquals(0, player.getMagicResistance(Spells.FIRE));
		assertEquals(0, player.getMagicResistance(Spells.WATER));
		assertEquals(0, player.getMagicResistance(Spells.AIR));
		assertEquals(0, player.getMagicResistance(Spells.EARTH));
	}

	public void testGettingPlayerMagicResistance() throws Throwable {

		assertEquals(0, player.getMagicResistance(Spells.FIRE));
		assertEquals(0, player.getMagicResistance(Spells.WATER));
		assertEquals(0, player.getMagicResistance(Spells.AIR));
		assertEquals(0, player.getMagicResistance(Spells.EARTH));
	}

	public void testSettingPlayerMagicResistance() throws Throwable {

		player.setMagicResistance(Spells.FIRE, (byte) 22);
		player.setMagicResistance(Spells.WATER, (byte) 33);
		player.setMagicResistance(Spells.AIR, (byte)(-44));
		player.setMagicResistance(Spells.EARTH, (byte) 125);

		assertEquals(22, player.getMagicResistance(Spells.FIRE));
		assertEquals(33, player.getMagicResistance(Spells.WATER));
		assertEquals(-44, player.getMagicResistance(Spells.AIR));
		assertEquals(100, player.getMagicResistance(Spells.EARTH));
	}
	
	// TODO: ENEMY VALIDATION REQUIRED.

}

/*
 * public static void go() {
 * 
 * Player player = new Player("João", Types.RACE_ELF, Types.CLASS_MAGE);
 * 
 * Log.v("RPG", "Initial values of Magic Resistance = Earth:" +
 * player.getMagicResistance(Spells.EARTH) + " Air:" +
 * player.getMagicResistance(Spells.AIR) + " Water:" +
 * player.getMagicResistance(Spells.WATER) + " Fire:" +
 * player.getMagicResistance(Spells.FIRE) + " (expected: 0, 0, 0, 0)");
 * 
 * player.setMagicResistance(Spells.FIRE, (byte)23);
 * player.setMagicResistance(Spells.WATER, (byte)67);
 * player.setMagicResistance(Spells.EARTH, (byte)100);
 * 
 * Log.v("RPG", "Set values of Magic Resistance = Earth:" +
 * player.getMagicResistance(Spells.EARTH) + " Air:" +
 * player.getMagicResistance(Spells.AIR) + " Water:" +
 * player.getMagicResistance(Spells.WATER) + " Fire:" +
 * player.getMagicResistance(Spells.FIRE) + " (expected: 100, 0, 67, 23)");
 * 
 * 
 * Enemy enemy = new Enemy(Enemies.GOBLIN_MAGE);
 * 
 * Log.v("RPG", "Initial values of Magic Resistance = Earth:" +
 * enemy.getMagicResistance(Spells.EARTH) + " Air:" +
 * enemy.getMagicResistance(Spells.AIR) + " Water:" +
 * enemy.getMagicResistance(Spells.WATER) + " Fire:" +
 * enemy.getMagicResistance(Spells.FIRE) + " (expected: 6, 13, 20, 26)");
 * 
 * }
 */