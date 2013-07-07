package com.rpggame.core.conf;

public final class Levels {
	
	public final static int PLAYER[] = {
		-1,				//LEVEL 0
		 0,				//LEVEL 1
		1000,			//LEVEL 2
		2000,			//LEVEL 3
		4000,			//LEVEL 4
		8000,			//LEVEL 5
		16000,			//LEVEL 6
		32000,			//LEVEL 7
		64000,			//LEVEL 8
	};
	
	
	public static byte getLevelFromExperience(int exp){
		byte level = 0;
		if (exp > PLAYER[PLAYER.length - 1])
			return (byte)(PLAYER.length - 1);
		while (PLAYER[level + 1] <= exp)
			level ++;
		return level;
	}

}
