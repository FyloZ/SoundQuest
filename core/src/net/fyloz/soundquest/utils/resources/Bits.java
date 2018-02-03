package net.fyloz.soundquest.utils.resources;

public class Bits {
	public static final short PHYSIC_ENTITY = 1;
	public static final short PLAYER_ENTITY = 2;
	public static final short WORLD_ENTITY = 3;
	public static final short COLLECTABLE_ENTITY = 4;

	public static final short PHYSIC_ENTITY_MASK = PHYSIC_ENTITY | PLAYER_ENTITY;
	public static final short PLAYER_MASK = COLLECTABLE_ENTITY | PHYSIC_ENTITY | WORLD_ENTITY;
	public static final short COIN_MASK = PLAYER_ENTITY | PHYSIC_ENTITY | WORLD_ENTITY;
	public static final short CANONBALL_MASK = PLAYER_ENTITY | PHYSIC_ENTITY | WORLD_ENTITY | COLLECTABLE_ENTITY;

	/**
	 * ANCIEN!!! A FAIRE SI POSSIBLE
	 * 
	 * private static final short bitsArray[] = { PHYSIC_ENTITY, PLAYER_ENTITY,
	 * WORLD_ENTITY, COLLECTABLE_ENTITY };
	 * 
	 * public static short getBits(short exclude) { short result = 0; for (short bit
	 * : bitsArray) { if (bit != exclude) { result = bit; } } return result;
	 * 
	 * }
	 * 
	 * public static short[] getBits(short[] exclude) { int i = 0; short[] result =
	 * new short[bitsArray.length - exclude.length]; short finalResult = 0; for
	 * (short bit : bitsArray) { for (short excluded : exclude) { if (bit !=
	 * excluded) { result[i] = bit; i++; } } }
	 * 
	 * return result; }
	 **/
}
