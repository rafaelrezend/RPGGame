/**
 * HANDLING HEALTH TEST Set an initial Health, check range (never below 0,
 * never above max health) and recharge.
 */

package com.rpggame.test;

import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Types;

import android.test.AndroidTestCase;

public class HandleHealthTest extends AndroidTestCase {

	Player player;
	Enemy enemy;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);
		player.setHealthMax((short) 200);
		player.setHealthCurrToHealthMax();

		enemy = new Enemy(Enemies.GOBLIN);
	}

	public void testPlayerSetHealthToMax() throws Throwable {

		assertEquals(0,
				player.increaseHealthCurr((short) (-player.getHealthCurr())));
		assertEquals(200, player.setHealthCurrToHealthMax());
		assertEquals(200, player.getHealthCurr());
	}

	// same methods of Player (inheritance from Character)
	public void testEnemySetHealthToMax() throws Throwable {

		assertEquals(0,
				enemy.increaseHealthCurr((short) (-player.getHealthCurr())));
		short enemyHealth = enemy.setHealthCurrToHealthMax();
		assertTrue((enemyHealth >= 22) && (enemyHealth <= 28));
		enemyHealth = enemy.getHealthCurr();
		assertTrue((enemyHealth >= 22) && (enemyHealth <= 28));
	}

	public void testPlayerRangeOfHealth() throws Throwable {
		// Current health must be between 0 and max
		assertEquals(50, player.increaseHealthCurr((short) -150));
		assertEquals(0, player.increaseHealthCurr((short) -250));
		assertEquals(150, player.increaseHealthCurr((short) 150));
		assertEquals(200, player.increaseHealthCurr((short) 150));
		assertEquals(200, player.getHealthCurr());
	}

	// same methods of Player (inheritance from Character)
	public void testEnemyRangeOfHealth() throws Throwable {
		// Current health must be between 0 and max
		short enemyHealth = enemy.setHealthCurrToHealthMax();
		assertEquals((enemyHealth - 15), enemy.increaseHealthCurr((short) -15));
		assertEquals(0, enemy.increaseHealthCurr((short) -250));
		assertEquals(15, enemy.increaseHealthCurr((short) 15));
		assertEquals(enemyHealth, enemy.increaseHealthCurr((short) 150));
	}
}

/*
 * public static void go() {
 * 
 * Player player = new Player("João", Types.RACE_ELF, Types.CLASS_MAGE);
 * 
 * player.setHealthMax((short) 200); player.setManaMax((short) 200);
 * 
 * player.setHealthCurrToHealthMax(); player.setManaCurrToManaMax();
 * 
 * // afflict damage to player Log.v("RPG", "Current HealthMax = " +
 * player.getHealthCurr()); Log.v("RPG", "After 150 damage  = " +
 * player.increaseHealthCurr((short) (-150)) + " (expected = 50)");
 * 
 * Log.v("RPG", "Current ManaMax = " + player.getManaCurr()); Log.v("RPG",
 * "After 180 spell = " + player.increaseManaCurr((short) (-180)) +
 * " (expected = 20)");
 * 
 * // killing player Log.v("RPG", "After 150 damage = " +
 * player.increaseHealthCurr((short) (-150)) + " (expected = 0)"); // trying to
 * use too much spell Log.v("RPG", "After 50 spell = " +
 * player.increaseManaCurr((short) (-150)) + " (expected = -1)");
 * 
 * // mana shouldn't be charged from player Log.v("RPG",
 * "Mana after failed spell = " + player.getManaCurr() + " (expected = 20)");
 * 
 * // reviving player Log.v("RPG", "After 150 health recover = " +
 * player.increaseHealthCurr((short) 150) + " (expected = 150)"); Log.v("RPG",
 * "After 150 mana recover   = " + player.increaseManaCurr((short) 150) +
 * " (expected = 170)");
 * 
 * // too much potion Log.v("RPG", "After 150 health recover = " +
 * player.increaseHealthCurr((short) 150) + " (expected = 200)"); Log.v("RPG",
 * "After 150 mana recover   = " + player.increaseManaCurr((short) 150) +
 * " (expected = 200)");
 * 
 * // afflict damage to player Log.v("RPG", "After 150 damage  = " +
 * player.increaseHealthCurr((short) (-150)) + " (expected = 50)"); Log.v("RPG",
 * "After 180 spell = " + player.increaseManaCurr((short) (-180)) +
 * " (expected = 20)");
 * 
 * // full recovery Log.v("RPG", "Full Health Recovery = " +
 * player.setHealthCurrToHealthMax() + " (expected = 200)"); Log.v("RPG",
 * "Full Mana Recovery   = " + player.setManaCurrToManaMax() +
 * " (expected = 200)"); }
 */