package de.flxnet.framez.tiles;

import lombok.Getter;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.network.protocol.game.PacketPlayOutMap;
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity;
import net.minecraft.world.level.World;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TileRenderPackage {

	@Getter
	private World world;
	
	@Getter
	private BlockPosition blockPosition;
	
	@Getter
	private PacketPlayOutMap mapPacket;
	
	@Getter
	private PacketPlayOutSpawnEntity spawnEntityPacket;
	
	@Getter
	private PacketPlayOutEntityMetadata entityMetadataPacket;
	
	public TileRenderPackage(World world, BlockPosition blockPosition, PacketPlayOutMap mapPacket, PacketPlayOutSpawnEntity spawnEntityPacket, PacketPlayOutEntityMetadata entityMetadataPacket) {
		this.world = world;
		this.blockPosition = blockPosition;
		this.mapPacket = mapPacket;
		this.spawnEntityPacket = spawnEntityPacket;
		this.entityMetadataPacket = entityMetadataPacket;
	}
	
}
