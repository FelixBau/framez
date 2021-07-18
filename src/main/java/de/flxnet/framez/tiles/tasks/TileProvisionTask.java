package de.flxnet.framez.tiles.tasks;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import de.flxnet.framez.helpers.NMSHelper;
import de.flxnet.framez.tiles.Tile;
import de.flxnet.framez.tiles.TileDirection;
import de.flxnet.framez.tiles.TileImageRender;
import de.flxnet.framez.tiles.TileRenderPackage;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPosition;
import net.minecraft.world.level.World;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TileProvisionTask implements Runnable {

	@Getter @Setter
	private List<Player> players;
	
	@Getter @Setter
	private Tile tile;
	
	public TileProvisionTask(Tile tile, List<Player> players) {
		this.tile = tile;
		this.players = players;
	}
	
	@Override
	public void run() {
		if(!tile.isRendered()) {
			TilePrepareTask prepareTask = new TilePrepareTask(tile);
			prepareTask.run();
		}
		
		for(Player player : players) {
			CraftPlayer craftPlayer = (CraftPlayer) player;
			World world = craftPlayer.getHandle().getWorld();
			Location startLocation = tile.getLocation().toBukkitLocation();
			
			TileDirection tileDirection = tile.getLocation().getDirection();
			
			int row = 0;
			int column = 0;
			
			for(TileImageRender render : tile.getTileRenders()) {
				
				TileRenderPackage renderPackage = null;
				
				if(column == tile.getColumns()) {
					column = 0;
					row++;
				}
				
				if(tileDirection == TileDirection.NORTH) {
					Location placeLocation = startLocation.clone().subtract(column, 0, 0).add(0, row, 0);
					renderPackage = render.getRenderPackage(world, fromLocation(placeLocation));
				}
				
				if(tileDirection == TileDirection.SOUTH) {
					Location placeLocation = startLocation.clone().add(column, row, 0);
					renderPackage = render.getRenderPackage(world, fromLocation(placeLocation));
				}
				
				if(tileDirection == TileDirection.WEST) {
					Location placeLocation = startLocation.clone().add(0, 0, column).add(0, row, 0);
					renderPackage = render.getRenderPackage(world, fromLocation(placeLocation));
				}
				
				if(tileDirection == TileDirection.EAST) {
					Location placeLocation = startLocation.clone().subtract(0, 0, column).add(0, row, 0);
					renderPackage = render.getRenderPackage(world, fromLocation(placeLocation));
				}
				
				
				
				NMSHelper.sendPacket(player, renderPackage.getMapPacket());
				NMSHelper.sendPacket(player, renderPackage.getSpawnEntityPacket());
				NMSHelper.sendPacket(player, renderPackage.getEntityMetadataPacket());
				
				column++;
				
			}

		}
	}
	
	public BlockPosition fromLocation(Location location) {
		return new BlockPosition(location.getX(), location.getY() + 1, location.getZ());
	}

}
