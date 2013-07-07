package com.rpggame.test;

import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Armors;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Types;
import com.rpggame.core.conf.Weapons;

import android.test.AndroidTestCase;

public class AtkDefMultiplierTest extends AndroidTestCase {
	
	Player player;
	Enemy enemy;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_HUMAN, Types.CLASS_FIGHTER);
		player.setHealthMax((short) 200);
		player.setHealthCurrToHealthMax();
		player.equipWeapon(Weapons.STEEL_SWORD);
		player.equipArmor(Armors.LEATHER_SHIELD);

		enemy = new Enemy(Enemies.GOBLIN_WARRIOR);
	}
	
	public void testPlayerMultipliers() throws Throwable {
		
		assertEquals(100, player.getAffectedAttackMultiplier());
		assertEquals(100, player.getAffectedDefenseMultiplier());
		
		assertEquals(14, player.attack(true));
		assertEquals(5, player.getDefense());
		
		// multipliers x2
		player.setAffectedAttackMultiplier((short)200);
		player.setAffectedDefenseMultiplier((short)200);
		
		assertEquals(200, player.getAffectedAttackMultiplier());
		assertEquals(200, player.getAffectedDefenseMultiplier());
		
		assertEquals(28, player.attack(true));
		assertEquals(10, player.getDefense());
		
		// dividers by 2
		player.setAffectedAttackMultiplier((short)50);
		player.setAffectedDefenseMultiplier((short)50);
		
		assertEquals(50, player.getAffectedAttackMultiplier());
		assertEquals(50, player.getAffectedDefenseMultiplier());
		
		assertEquals(7, player.attack(true));
		assertEquals(2, player.getDefense());
	}
	
	public void testEnemyMultipliers() throws Throwable {
		
		assertEquals(100, enemy.getAffectedAttackMultiplier());
		assertEquals(100, enemy.getAffectedDefenseMultiplier());
		
		assertEquals(17, enemy.attack(true));
		assertEquals(3, enemy.getDefense());
		
		// multipliers x2
		enemy.setAffectedAttackMultiplier((short)200);
		enemy.setAffectedDefenseMultiplier((short)200);
		
		assertEquals(200, enemy.getAffectedAttackMultiplier());
		assertEquals(200, enemy.getAffectedDefenseMultiplier());
		
		assertEquals(34, enemy.attack(true));
		assertEquals(6, enemy.getDefense());
		
		// dividers by 2
		enemy.setAffectedAttackMultiplier((short)50);
		enemy.setAffectedDefenseMultiplier((short)50);
		
		assertEquals(50, enemy.getAffectedAttackMultiplier());
		assertEquals(50, enemy.getAffectedDefenseMultiplier());
		
		assertEquals(8, enemy.attack(true));
		assertEquals(1, enemy.getDefense());
	}

}

/*

public static void go() {
	
	Player player = new Player("João", Types.RACE_ELF,
			Types.CLASS_MAGE);
	
	player.equipWeapon(Weapons.STEEL_SWORD);
	player.equipArmor(Armors.LEATHER_SHIELD);
	
	Log.v("RPG", "Critical Attack of Steel Sword = " + player.attack(true) + " (expected = 14)");
	Log.v("RPG", "Defense of Leather Shield = " + player.getDefense() + " (expected = 7)");
	
	player.setAffectedAttackMultiplier((short)200);
	player.setAffectedDefenseMultiplier((short)200);
	
	Log.v("RPG", "Critical Attack of Steel Sword x2 = " + player.attack(true) + " (expected = 28)");
	Log.v("RPG", "Defense of Leather Shield x2 = " + player.getDefense() + " (expected = 14)");
	
	player.setAffectedAttackMultiplier((short)50);
	player.setAffectedDefenseMultiplier((short)50);
	
	Log.v("RPG", "Critical Attack of Steel Sword x0.5 = " + player.attack(true) + " (expected = 7)");
	Log.v("RPG", "Defense of Leather Shield x0.5 = " + player.getDefense() + " (expected = 3)");
	
	Enemy enemy = new Enemy(Enemies.GOBLIN_WARRIOR);
	
	Log.v("RPG", "Critical Attack of Goblin Warrior = " + enemy.attack(true) + " (expected = 18)");
	Log.v("RPG", "Defense of Goblin Warrior = " + enemy.getDefense() + " (expected = 10)");
	
	enemy.setAffectedAttackMultiplier((short)200);
	enemy.setAffectedDefenseMultiplier((short)200);
	
	Log.v("RPG", "Critical Attack of Goblin Warrior x2 = " + enemy.attack(true) + " (expected = 36)");
	Log.v("RPG", "Defense of Goblin Warrior x2 = " + enemy.getDefense() + " (expected = 20)");
	
	enemy.setAffectedAttackMultiplier((short)50);
	enemy.setAffectedDefenseMultiplier((short)50);
	
	Log.v("RPG", "Critical Attack of Goblin Warrior x0.5 = " + enemy.attack(true) + " (expected = 9)");
	Log.v("RPG", "Defense of Goblin Warrior x0.5 = " + enemy.getDefense() + " (expected = 5)");
}

*/