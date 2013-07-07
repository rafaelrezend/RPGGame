/**
 * PLAYER ATTACK TEST Test Critical and Random attack with and without
 * weapons. Test getters of player.
 */

package com.rpggame.test;

import com.rpggame.core.Player;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Types;
import com.rpggame.core.conf.Weapons;

import android.test.AndroidTestCase;

public class HandlePlayerTest extends AndroidTestCase {
	
	Player player;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);
		player.setHealthMax((short) 200);
		player.setHealthCurrToHealthMax();

	}
	
	public void testPlayerGetters() throws Throwable {
		// most of getters were already tested in other scenarios
		assertEquals("Hero", player.getName());
		assertEquals(Types.RACE_ELF, player.getRace());
		assertEquals(Types.CLASS_MAGE, player.getClasse());
		assertEquals(100, player.getAffectedAttackMultiplier());
		assertEquals(100, player.getAffectedDefenseMultiplier());
	}
	
	public void testPlayerAttack() throws Throwable {
		
		// without sword
		for (int i = 0; i < 30; i++) {
			int attack = player.attack(false);
			assertTrue((attack >= 1) && (attack <= 3));
		}

		// critical without sword
		for (int i = 0; i < 30; i++)
			assertEquals(3, player.attack(true));
		
		assertEquals(Errorcode.SUCCESS, player.equipWeapon(Weapons.STEEL_SWORD));
		
		// without sword
		for (int i = 0; i < 30; i++) {
			int attack = player.attack(false);
			assertTrue((attack >= 10) && (attack <= 14));
		}

		// critical without sword
		for (int i = 0; i < 30; i++)
			assertEquals(14, player.attack(true));
	}

}

/*
public static void go() {

	Player player = new Player("João", Types.RACE_ELF,
			Types.CLASS_MAGE);

	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	Log.v("RPG", "Attack without weapon = " + player.attack(false)
			+ " (1 <expected < 3)");
	
	Log.v("RPG", "Critical attack without weapon = " + player.attack(true)
			+ " (expected = 3)");

	player.equipWeapon(Weapons.STEEL_SWORD);
	
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG", "Attack with Steel Sword = " + player.attack(false)
			+ " (10 < expected < 14)");
	Log.v("RPG",
			"Critical attack with Steel Sword = " + player.attack(true)
					+ " (expected = 14)");
}
*/
