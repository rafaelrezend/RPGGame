/**
 * COMBAT SPELL DEFENSE TEST Player casts VULNERABILITY against
 * enemy and against another player. Cast against a player with
 * positive and negative resistance.
 */

package com.rpggame.test;

import com.rpggame.core.CombatEvents;
import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Armors;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Spells;
import com.rpggame.core.conf.Types;

import android.test.AndroidTestCase;

public class CombatSpellDefenseTest extends AndroidTestCase {
	
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
		
		// increase spell level to 6
		player.increaseLevelSpellsArray(Spells.VULNERABILITY);
		player.increaseLevelSpellsArray(Spells.VULNERABILITY);
		player.increaseLevelSpellsArray(Spells.VULNERABILITY);
		player.increaseLevelSpellsArray(Spells.VULNERABILITY);
		player.increaseLevelSpellsArray(Spells.VULNERABILITY);
		player.increaseLevelSpellsArray(Spells.VULNERABILITY);
	}
	
	public void testPlayerSpellDefenseAgainstEnemy() throws Throwable {
		
		Enemy enemy = new Enemy(Enemies.GOBLIN_WARRIOR);
		assertEquals(3, enemy.getDefense());
		
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.VULNERABILITY, enemy));
		
		assertEquals(1, enemy.getDefense());
	}
	
	public void testPlayerSpellDefenseAgainstPlayer() throws Throwable {
		
		// setting up player with good defense
		Player player2 = new Player("Hero2", Types.RACE_HUMAN, Types.CLASS_FIGHTER);
		assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_SHIELD));
		assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_BOOTS));
		assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_BODY));
		assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_HELM));
		assertEquals(9, player2.getDefense());
		
		assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.VULNERABILITY, player2));
		
		assertEquals(4, player2.getDefense());
	}
	
	public void testPlayerSpellDefenseAgainstPlayerWithResistance() throws Throwable {
		
		int resistedMagic = 0;
		
		// cast Vulnerability 100 times and check if the average of resisted magic is around 50%.
		for (int i = 0; i < 100; i++) {
		
			// setting up player with good defense and magic resistance to Earth = 50%
			Player player2 = new Player("Hero2", Types.RACE_HUMAN, Types.CLASS_FIGHTER);
			assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_SHIELD));
			assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_BOOTS));
			assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_BODY));
			assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_HELM));
			player2.setMagicResistance(Spells.EARTH, (byte)50);
			assertEquals(9, player2.getDefense());
			
			player.setManaCurrToManaMax();
			
			if (CombatEvents.charCastSpellAgainstChar(player, Spells.VULNERABILITY, player2) == Errorcode.MAGIC_RESISTED)
				resistedMagic++;
		}
		assertTrue((resistedMagic > 30) && (resistedMagic < 70));
	}
	
	public void testPlayerSpellDefenseAgainstPlayerWithNegResistance() throws Throwable {
		
		// cast Vulnerability 100 times and check if there is no resistance
		for (int i = 0; i < 100; i++) {
		
			// setting up player with good defense and magic resistance to Earth = -50%
			Player player2 = new Player("Hero2", Types.RACE_HUMAN, Types.CLASS_FIGHTER);
			assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_SHIELD));
			assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_BOOTS));
			assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_BODY));
			assertEquals(Errorcode.SUCCESS, player2.equipArmor(Armors.LEATHER_HELM));
			player2.setMagicResistance(Spells.EARTH, (byte)-50);
			assertEquals(9, player2.getDefense());
			
			player.setManaCurrToManaMax();
			
			assertEquals(Errorcode.SUCCESS, CombatEvents.charCastSpellAgainstChar(player, Spells.VULNERABILITY, player2));
			assertEquals(4, player2.getDefense());
		}
	}

}
