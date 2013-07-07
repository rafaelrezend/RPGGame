/**
 * HANDLE ENEMY TEST Show enemy name, some static attributes and random
 * attributes like health and attack. Health is fixed once it's
 * generated. Attack changes at every call.
 */

package com.rpggame.test;

import com.rpggame.core.Enemy;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Spells;

import android.test.AndroidTestCase;

public class HandleEnemyTest extends AndroidTestCase {
	
	Enemy enemy_mage, enemy_warrior;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		enemy_mage = new Enemy(Enemies.GOBLIN_MAGE);
		enemy_warrior = new Enemy(Enemies.GOBLIN_WARRIOR);
	}
	
	public void testEnemyGetters() throws Throwable {
		
		int enemyHealth = enemy_mage.getHealthCurr();
		assertTrue((enemyHealth >= 27) && (enemyHealth <= 33));
		assertEquals(enemyHealth, enemy_mage.getHealthCurr());
		
		assertEquals("Goblin Mage", Enemies.NAME[enemy_mage.getEnemyId()]);
		assertEquals(7, enemy_mage.getDexterity());
		assertEquals(1, enemy_mage.getDefense());
		assertEquals(100, enemy_mage.getAffectedAttackMultiplier());
		assertEquals(100, enemy_mage.getAffectedDefenseMultiplier());
		assertEquals(1, enemy_mage.getSpellLevel(Spells.FIRE_BALL));
	}
	
	public void testEnemyAttack() throws Throwable {
		
		for (int i = 0; i < 30; i++) {
			int attack = enemy_warrior.attack(false);
			assertTrue((attack >= 12) && (attack <= 17));
		}
		// critical
		assertEquals(17, enemy_warrior.attack(true));
	}

}

/*
public static void go() {

	Enemy enemy = new Enemy(Enemies.GOBLIN_WARRIOR);

	Log.v("RPG", "Enemy name = " + Enemies.NAME[enemy.getEnemyId()]
			+ " (expected name)");
	Log.v("RPG", "Enemy dexterity = " + enemy.getDexterity()
			+ " (expected = 6)");
	Log.v("RPG", "Enemy defense = " + enemy.getDefense()
			+ " (expected = 10)");
	Log.v("RPG", "Enemy health = " + enemy.getHealthCurr()
			+ " (36 < expected < 44)");
	Log.v("RPG", "Enemy attack = " + enemy.attack(false)
			+ " (12 < expected < 18)");
	Log.v("RPG", "Enemy attack = " + enemy.attack(false)
			+ " (12 < expected < 18)");
	Log.v("RPG", "Enemy attack = " + enemy.attack(false)
			+ " (12 < expected < 18)");
	Log.v("RPG", "Enemy critical attack = " + enemy.attack(true)
			+ " (expected = 18)");
	Log.v("RPG", "Enemy health = " + enemy.getHealthCurr()
			+ " (36 < expected < 44)");

}
*/