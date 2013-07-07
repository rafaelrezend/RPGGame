/**
 * TEST LEVELING SYSTEM If player reaches the minimal value for the next
 * level, then a new level is set. Once a level is reached, player cannot
 * decrease anymore. Even if player loses experience (by events on game,
 * like dying) the minimal experience reached is the minimal experience for
 * the current level.
 */

package com.rpggame.test;

import com.rpggame.core.Player;
import com.rpggame.core.conf.Types;

import android.test.AndroidTestCase;

public class LevelingSystemTest extends AndroidTestCase {

	Player player;

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);
	}

	public void testLevelingSystem() throws Throwable {

		assertEquals(1, player.getLevel());
		assertEquals(0, player.getExperience());

		player.addExperience(500);
		
		assertEquals(1, player.getLevel());
		assertEquals(500, player.getExperience());
		
		player.addExperience(520);
		
		assertEquals(2, player.getLevel());
		assertEquals(1020, player.getExperience());
		
		player.addExperience(-10);
		
		assertEquals(2, player.getLevel());
		assertEquals(1010, player.getExperience());

		player.addExperience(-30);
		
		assertEquals(2, player.getLevel());
		assertEquals(1000, player.getExperience());

		player.addExperience(0);
		
		assertEquals(2, player.getLevel());
		assertEquals(1000, player.getExperience());
		
		player.addExperience(-15000);
		
		assertEquals(2, player.getLevel());
		assertEquals(1000, player.getExperience());
		
		player.addExperience(1000);
		
		assertEquals(3, player.getLevel());
		assertEquals(2000, player.getExperience());
		
		player.addExperience(200);
		
		assertEquals(3, player.getLevel());
		assertEquals(2200, player.getExperience());
		
		player.addExperience(-300);
		
		assertEquals(3, player.getLevel());
		assertEquals(2000, player.getExperience());
		
		player.upToNextLevel();
		
		assertEquals(4, player.getLevel());
		assertEquals(4000, player.getExperience());

		player.upToNextLevel();
		
		assertEquals(5, player.getLevel());
		assertEquals(8000, player.getExperience());
		
		player.addExperience(-300);
		
		assertEquals(5, player.getLevel());
		assertEquals(8000, player.getExperience());
	}
}

/*
	public static void go() {

		Player player = new Player("Hero", Types.RACE_ELF,
				Types.CLASS_MAGE);

		int x = 0;

		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(0) + ", Exp = 0)");

		player.addExperience(x = 500);
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(500) + ", Exp = 500)");

		player.addExperience(x = 520);
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(1020) + ", Exp = 1020)");

		player.addExperience(x = (-10));
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(1010) + ", Exp = 1010)");

		player.addExperience(x = (-30));
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(1000) + ", Exp = 1000)");

		player.addExperience(x = 0);
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(1000) + ", Exp = 1000)");

		player.addExperience(x = (-1000));
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(1000) + ", Exp = 1000)");

		player.addExperience(x = 1000);
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(2000) + ", Exp = 2000)");

		player.addExperience(x = 200);
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(2200) + ", Exp = 2200)");

		player.addExperience(x = -300);
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(2000) + ", Exp = 2000)");

		player.upToNextLevel();
		Log.v("RPG",
				"Level: " + player.getLevel() + " - Experience: "
						+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(4000) + ", Exp = 4000)");

		player.upToNextLevel();
		Log.v("RPG",
				"Level: " + player.getLevel() + " - Experience: "
						+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(8000) + ", Exp = 8000)");

		player.addExperience(x = -300);
		Log.v("RPG",  "Add: " + x +	" - Level: " + player.getLevel()
				+ " - Experience: "	+ player.getExperience() + " (expected Level = " + Levels.getLevelFromExperience(8000) + ", Exp = 8000)");

	}
*/
