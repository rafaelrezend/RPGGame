/**
 * HANDLING MANA TEST Set an initial Mana, consume it all with Spells and
 * recharge.
 */

package com.rpggame.test;

import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Spells;
import com.rpggame.core.conf.Types;

import android.test.AndroidTestCase;

public class HandleManaTest extends AndroidTestCase {
	
	Player player;
	Enemy enemy;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);
		player.setManaMax((short)50);
		player.setManaCurrToManaMax();
		// player has fireball level 2
		player.increaseLevelSpellsArray(Spells.FIRE_BALL);
		player.increaseLevelSpellsArray(Spells.FIRE_BALL);
		
		enemy = new Enemy(Enemies.GOBLIN_MAGE);
	}
	
	public void testPlayerSetManaToMax() throws Throwable {

		assertEquals(0, player.increaseManaCurr((short)(-player.getManaCurr())));
		assertEquals(50, player.setManaCurrToManaMax());
		assertEquals(50, player.getManaCurr());
	}
	
	// same methods of Player (inheritance from Character)
	public void testEnemySetManaToMax() throws Throwable {
		// enemy is created with a defined maximum mana
		assertEquals(0, enemy.increaseManaCurr((short)(-enemy.getManaCurr())));
		assertEquals(40, enemy.setManaCurrToManaMax());
		assertEquals(40, enemy.getManaCurr());
	}
	
	public void testPlayerRangeOfMana() throws Throwable {
		// Current mana must be between 0 and max
		assertEquals(-1, player.increaseManaCurr((short)-60));
		assertEquals(50, player.getManaCurr());
		assertEquals(10, player.increaseManaCurr((short)(-40)));
		assertEquals(50, player.increaseManaCurr((short)70));
	}
	
	// same methods of Player (inheritance from Character)
	public void testEnemyRangeOfMana() throws Throwable {
		// Current mana must be between 0 and max
		assertEquals(-1, enemy.increaseManaCurr((short)-60));
		assertEquals(10, enemy.increaseManaCurr((short)-30));
		assertEquals(40, enemy.increaseManaCurr((short)70));
	}
	
	public void testPlayerUseManaBySpells() throws Throwable {
		assertEquals(50, player.getManaCurr());
		assertEquals(1, player.increaseManaCurr((short)-49));
		assertEquals(-1, player.castSpell(Spells.FIRE_BALL));
		assertEquals(1, player.getManaCurr());
	}
	
	// same methods of Player (inheritance from Character)
	public void testEnemyUseManaBySpells() throws Throwable {
		assertEquals(40, enemy.getManaCurr());
		assertEquals(1, enemy.increaseManaCurr((short)-39));
		assertEquals(-1, enemy.castSpell(Spells.FIRE_BALL));
		assertEquals(1, enemy.getManaCurr());
	}
}
