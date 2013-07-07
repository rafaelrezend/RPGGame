/**
 * EQUIP SHIELD TEST Shields can only be equipped with one-handed
 * weapons. Weapons have priority over shields. If one-handed weapon is
 * equipped, a shield can also be equipped. If two-handed weapon is
 * equippen, a shield cannot be equipped. If a shield is equipped and
 * then a two-handed weapon is equipped, the shield is automatically
 * removed. The opposite never happens, thus, the player must unequip
 * the two-handed weapon before equip a shield.
 */

package com.rpggame.test;

import com.rpggame.core.Player;
import com.rpggame.core.conf.Armors;
import com.rpggame.core.conf.Errorcode;
import com.rpggame.core.conf.Types;
import com.rpggame.core.conf.Weapons;

import android.test.AndroidTestCase;

public class EquipArmorTest extends AndroidTestCase {
	
	Player player;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		player = new Player("Hero", Types.RACE_ELF, Types.CLASS_MAGE);
	}
	
	public void testTwoHandAndShield() throws Throwable {
		
		assertEquals(Weapons.NOWEAPON, player.getEquippedWeapon());
		assertEquals(Armors.NOSHIELD, player.getEquippedArmor(Armors.SHIELD));
		
		assertEquals(Errorcode.SUCCESS, player.equipWeapon(Weapons.RUSTY_SWORD));
		assertEquals(Errorcode.SUCCESS, player.equipArmor(Armors.LEATHER_SHIELD));
		
		assertEquals(Weapons.RUSTY_SWORD, player.getEquippedWeapon());
		assertEquals(Armors.LEATHER_SHIELD, player.getEquippedArmor(Armors.SHIELD));
		
		assertEquals(Errorcode.SUCCESS, player.equipWeapon(Weapons.STEEL_STAFF));

		assertEquals(Weapons.STEEL_STAFF, player.getEquippedWeapon());
		assertEquals(Armors.NOSHIELD, player.getEquippedArmor(Armors.SHIELD));
		
		assertEquals(Errorcode.TWO_HANDED_WEAPON_EQUIPPED, player.equipArmor(Armors.LEATHER_SHIELD));
		
		assertEquals(Weapons.STEEL_STAFF, player.getEquippedWeapon());
		assertEquals(Armors.NOSHIELD, player.getEquippedArmor(Armors.SHIELD));
	}
	
	public void testEquipUnequipHelm() throws Throwable {
		
		assertEquals(Armors.NOHELM, player.getEquippedArmor(Armors.HELM));
		assertEquals(Errorcode.SUCCESS, player.equipArmor(Armors.LEATHER_HELM));
		
		assertEquals(Armors.LEATHER_HELM, player.getEquippedArmor(Armors.HELM));
		assertEquals(Errorcode.SUCCESS, player.equipArmor(Armors.NOHELM));
		
		assertEquals(Armors.NOHELM, player.getEquippedArmor(Armors.HELM));
	}
	
	public void testEquipUnequipBody() throws Throwable {
		
		assertEquals(Armors.NOBODY, player.getEquippedArmor(Armors.BODY));
		assertEquals(Errorcode.SUCCESS, player.equipArmor(Armors.LEATHER_BODY));
		
		assertEquals(Armors.LEATHER_BODY, player.getEquippedArmor(Armors.BODY));
		assertEquals(Errorcode.SUCCESS, player.equipArmor(Armors.NOBODY));
		
		assertEquals(Armors.NOBODY, player.getEquippedArmor(Armors.BODY));
	}
	
	public void testEquipUnequipBoots() throws Throwable {
		
		assertEquals(Armors.NOBOOTS, player.getEquippedArmor(Armors.BOOTS));
		assertEquals(Errorcode.SUCCESS, player.equipArmor(Armors.LEATHER_BOOTS));
		
		assertEquals(Armors.LEATHER_BOOTS, player.getEquippedArmor(Armors.BOOTS));
		assertEquals(Errorcode.SUCCESS, player.equipArmor(Armors.NOBOOTS));
		
		assertEquals(Armors.NOBOOTS, player.getEquippedArmor(Armors.BOOTS));
	}
	
	public void testEquipHigherLevelRequired() throws Throwable {
		
		assertEquals(Armors.NOSHIELD, player.getEquippedArmor(Armors.SHIELD));
		assertEquals(Errorcode.HIGHER_LEVEL_REQUIRED, player.equipArmor(Armors.IRON_SHIELD));
		assertEquals(Armors.NOSHIELD, player.getEquippedArmor(Armors.SHIELD));
	}
}

/*public static void go() {

	Player player = new Player("João", Types.RACE_ELF,
			Types.CLASS_MAGE);
	
	Log.v("RPG", "Current weapon = " + Weapons.NAME[player.getEquippedWeapon()] + " (expected No Weapon)");
	Log.v("RPG", "Current shield = " + Armors.NAME[player.getEquippedArmor(Armors.SHIELD)] + " (expected No Shield)");
	
	player.equipWeapon(Weapons.RUSTY_SWORD);
	player.equipArmor(Armors.LEATHER_SHIELD);
	
	Log.v("RPG", "One hand weapon equipped = " + Weapons.NAME[player.getEquippedWeapon()] + " (expected Rusty Sword)");
	Log.v("RPG", "Shield equipped = " + Armors.NAME[player.getEquippedArmor(Armors.SHIELD)] + " (expected Leather Shield)");
	
	player.equipWeapon(Weapons.STEEL_STAFF);

	Log.v("RPG", "Two hand weapon equipped = " + Weapons.NAME[player.getEquippedWeapon()] + " (expected Steel Staff)");
	Log.v("RPG", "Shield automatically removed = " + Armors.NAME[player.getEquippedArmor(Armors.SHIELD)] + " (expected No Shield)");

	player.equipArmor(Armors.LEATHER_SHIELD);
	Log.v("RPG", "Trying to equip shield: result = " + player.equipArmor(Armors.LEATHER_SHIELD) + " Equipped: " + Armors.NAME[player.getEquippedArmor(Armors.SHIELD)] + " (expected No Shield)");
	
}*/


	

