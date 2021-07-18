package de.flxnet.framez.tiles;

import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public enum TileDirection {

	NORTH(3),
	SOUTH(2),
	WEST(5),
	EAST(4),
	UP(1),
	DOWN(0);
	
	@Getter
	private int enumDirectionId;
	
	private TileDirection(int enumDirectionId) {
		this.enumDirectionId = enumDirectionId;
	}
	
}
