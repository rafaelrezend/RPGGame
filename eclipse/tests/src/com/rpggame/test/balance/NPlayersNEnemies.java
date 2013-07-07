/**
 * N PLAYERS N ENEMIES Create many players and many weak enemies. 
 * They fight against each other until one dies.
 */

package com.rpggame.test.balance;

import com.rpggame.core.CombatEvents;
import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Types;
import com.rpggame.core.conf.Weapons;

import android.test.AndroidTestCase;
import android.util.Log;

public class NPlayersNEnemies extends AndroidTestCase {
	
	String scenarioName = "NPlayersNEnemies";

	Player player;
	Enemy enemy;

	public void testCombat() throws Throwable {

		for (int i = 0; i < 5; i++) {
			Log.v(scenarioName, "COMBAT #" + i);

			// set new player as human fighter
			player = new Player("Hero", Types.RACE_HUMAN, Types.CLASS_FIGHTER);
			// set enemy as goblin warrior
			enemy = new Enemy(Enemies.GOBLIN_WARRIOR);

			// max health for player = 50
			player.setHealthMax((short) 50);
			player.setHealthCurrToHealthMax();
			// steel sword equipped
			player.equipWeapon(Weapons.STEEL_SWORD);

			Log.v(scenarioName, "Health: Hero = " + player.getHealthCurr()
					+ " Enemy = " + enemy.getHealthCurr());

			while ((player.getHealthCurr() > 0) && (enemy.getHealthCurr() > 0)) {

				if (player.getHealthCurr() > 0) {
					Log.v(scenarioName,
							"Hero attacks Goblin Warrior: "
									+ Errorcode.ERRORMESSAGE[CombatEvents
											.charAttackChar(player, enemy)]);
					Log.v(scenarioName, "Health: Hero = " + player.getHealthCurr()
							+ " Enemy = " + enemy.getHealthCurr());
				}

				if (enemy.getHealthCurr() > 0) {
					Log.v(scenarioName,
							"Goblin Warrior attacks Hero: "
									+ Errorcode.ERRORMESSAGE[CombatEvents
											.charAttackChar(enemy, player)]);
					Log.v(scenarioName, "Health: Hero = " + player.getHealthCurr()
							+ " Enemy = " + enemy.getHealthCurr());
				}
			}

			if (player.getHealthCurr() > enemy.getHealthCurr())
				Log.v(scenarioName, "HERO WON\n");
			else
				Log.v(scenarioName, "GOBLIN WON\n");

		}

	}

}
