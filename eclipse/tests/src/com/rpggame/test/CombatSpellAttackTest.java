/**
 * COMBAT SPELL ATTACK TEST Player throws FIREBALL lvl 2 against enemy.
 * Enemy throws FIREBALL lvl1 against player.
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

public class CombatSpellAttackTest extends AndroidTestCase {
	
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
		
		// max mana for player = 50
		player.setManaMax((short) 50);
		player.setManaCurrToManaMax();
		
		// increase spell level
		player.increaseLevelSpellsArray(Spells.FIRE_BALL);
		player.increaseLevelSpellsArray(Spells.FIRE_BALL);
		
		// set fire resistance positive
		player.setMagicResistance(Spells.FIRE, (byte)25);
		
		enemy = new Enemy(Enemies.GOBLIN_MAGE);
	}
	
	public void testPlayerCastAgainstEnemy() throws Throwable {
		
		// trying to cast an unavailable spell
		assertEquals(Errorcode.SPELL_NOT_AVAILABLE_FOR_CHARACTER, CombatEvents.charCastSpellAgainstChar(player, Spells.DISPEL, enemy));
		// casting an available spell
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.FIRE_BALL, enemy));
		int expectedHealth = enemy.getHealthMax() - (Spells.DAMAGE[Spells.FIRE_BALL][2] * (100 - enemy.getMagicResistance(Spells.FIRE))/100);
		int expectedMana = player.getManaMax() - Spells.MANA[Spells.FIRE_BALL];
		assertEquals(expectedHealth, enemy.getHealthCurr());
		assertEquals(expectedMana, player.getManaCurr());
	}
	
	public void testEnemyCastAgainstPlayer() throws Throwable {
		
		// trying to cast an unavailable spell
		assertEquals(Errorcode.SPELL_NOT_AVAILABLE_FOR_CHARACTER, CombatEvents.charCastSpellAgainstChar(enemy, Spells.DISPEL, player));
		
		// casting an available spell
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(enemy, Spells.FIRE_BALL, player));
		int expectedHealth = player.getHealthMax() - (Spells.DAMAGE[Spells.FIRE_BALL][1] * (100 - player.getMagicResistance(Spells.FIRE))/100);
		int expectedMana = enemy.getManaMax() - Spells.MANA[Spells.FIRE_BALL];
		assertEquals(expectedHealth, player.getHealthCurr());
		assertEquals(expectedMana, enemy.getManaCurr());
		
		// casting a spell against a negative resistance
		assertEquals(50, player.setHealthCurrToHealthMax());
		player.setMagicResistance(Spells.FIRE, (byte)-50);
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(enemy, Spells.FIRE_BALL, player));
		expectedHealth = player.getHealthMax() - (Spells.DAMAGE[Spells.FIRE_BALL][1] * (100 - player.getMagicResistance(Spells.FIRE))/100);
		expectedMana -= Spells.MANA[Spells.FIRE_BALL];
		assertEquals(expectedHealth, player.getHealthCurr());
		assertEquals(expectedMana, enemy.getManaCurr());
	}

}
