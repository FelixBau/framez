package de.flxnet.framez.tiles;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TileStore {

	public static int MAP_ID_COUNTER = 100000;
	
	public static int nextId() {
		int next = MAP_ID_COUNTER;
		MAP_ID_COUNTER++;
		return next;
	}
	
	public static int[] nextIds(int amount) {
		int[] ids = new int[amount];
		for(int i = 0; i < amount; i++) {
			ids[i] = MAP_ID_COUNTER;
			MAP_ID_COUNTER++;
		}
		return ids;
	}
	
}
