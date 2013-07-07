/**
 * SINGLE PLAYER N ENEMIES Create one player and many weak enemies. Player keep
 * fighting against enemies until it dies.
 */

package com.rpggame.test.balance;

import android.test.AndroidTestCase;
import android.util.Log;

import com.rpggame.core.CombatEvents;
import com.rpggame.core.Enemy;
import com.rpggame.core.Player;
import com.rpggame.core.conf.Enemies;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Types;
import com.rpggame.core.conf.Weapons;

public class SPlayerNEnemies extends AndroidTestCase {
	
	String scenarioName = "SPlayerNEnemies";
	
	Player player;
	Enemy enemy;

	public void testCombat() throws Throwable{

		// set player: health = 200, using Steel Sword
		player = new Player("Hero", Types.RACE_HUMAN, Types.CLASS_FIGHTER);
		player.setHealthMax((short) 50);
		player.setHealthCurrToHealthMax();
		player.equipWeapon(Weapons.RUSTY_SWORD);

		int defeatedEnemies = 0;

		while (player.getHealthCurr() > 0) {
			Log.v(scenarioName, "COMBAT #" + defeatedEnemies);

			enemy = new Enemy(Enemies.GOBLIN);

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

			if (player.getHealthCurr() > enemy.getHealthCurr()) {
				Log.v(scenarioName, "HERO WON\n");
				defeatedEnemies++;
			} else
				Log.v(scenarioName, "GOBLIN WON\n");
		}
	}

}
