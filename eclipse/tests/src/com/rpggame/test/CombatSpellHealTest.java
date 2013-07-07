/**
 * COMBAT SPELL HEAL TEST Player casts HEAL LIFE on himself, on other player
 * and on Enemy
 */

package com.rpggame.test;

import com.rpggame.core.CombatEvents;
import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Spells;
import com.rpggame.core.conf.Types;

import android.test.AndroidTestCase;

public class CombatSpellHealTest extends AndroidTestCase {
	
	Player player;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);
		// max health for player = 50
		player.setHealthMax((short) 50);
		player.setHealthCurrToHealthMax();
		
		// max mana for player = 50
		player.setManaMax((short) 50);
		player.setManaCurrToManaMax();
		
		// increase spell level
		player.increaseLevelSpellsArray(Spells.HEAL_LIFE);
		player.increaseLevelSpellsArray(Spells.HEAL_LIFE);
	}
	
	public void testPlayerCastHealOnHimself() throws Throwable {
		
		assertEquals(20, player.increaseHealthCurr((short)-30));
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.HEAL_LIFE, player));
		assertEquals(40, player.getHealthCurr());
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.HEAL_LIFE, player));
		assertEquals(50, player.getHealthCurr());
	}
	
	public void testPlayerCastHealOnAnotherPlayer() throws Throwable {
		
		Player player2 = new Player("Hero2", Types.RACE_ORC, Types.CLASS_FIGHTER);
		
		// max health for player = 50
		player2.setHealthMax((short) 50);
		assertEquals(20, player2.increaseHealthCurr((short)20));
		
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.HEAL_LIFE, player2));
		assertEquals(40, player2.getHealthCurr());
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.HEAL_LIFE, player2));
		assertEquals(50, player2.getHealthCurr());
	}
	
	public void testPlayerCastHealOnEnemy() throws Throwable {
		
		Enemy enemy = new Enemy(Enemies.GOBLIN);
		int enemyHealthMax = enemy.getHealthMax();
		
		// max health for enemy around 25
		assertEquals((enemyHealthMax - 15), enemy.increaseHealthCurr((short)-15));
		
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.HEAL_LIFE, enemy));
		assertEquals(enemyHealthMax, enemy.getHealthCurr());
	}

}
