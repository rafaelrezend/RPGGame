/**
 * COMBAT ATTACK TEST Create one player and one enemy. Set them to
 * attack each other.
 */

package com.rpggame.test;

import com.rpggame.core.CombatEvents;
import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Types;
import com.rpggame.core.conf.Weapons;

import android.test.AndroidTestCase;

public class CombatAttackTest extends AndroidTestCase {
	
	Player player;
	Enemy enemy;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);
		// max health for player = 50
		player.setHealthMax((short) 50);
		player.setHealthCurrToHealthMax();
		// steel sword equipped
		player.equipWeapon(Weapons.STEEL_SWORD);
		
		enemy = new Enemy(Enemies.GOBLIN_WARRIOR);
	}
	
	public void testPlayerAttackEnemy() throws Throwable {
		
		assertEquals(Errorcode.SUCCESS, CombatEvents.charAttackChar(player, enemy));
		int maxExpectedHealth = enemy.getHealthMax() - (10 - enemy.getDefense());
		int minExpectedHealth = enemy.getHealthMax() - (14 - enemy.getDefense());
		assertTrue((enemy.getHealthCurr() >= minExpectedHealth) && (enemy.getHealthCurr() <= maxExpectedHealth));
	}
	
	public void testEnemyAttackPlayer() throws Throwable {
		
		CombatEvents.charAttackChar(enemy, player);
		int maxExpectedHealth = player.getHealthMax() - (12 - player.getDefense());
		int minExpectedHealth = player.getHealthMax() - (17 - player.getDefense());
		assertTrue((player.getHealthCurr() >= minExpectedHealth) && (player.getHealthCurr() <= maxExpectedHealth));
	}

}
