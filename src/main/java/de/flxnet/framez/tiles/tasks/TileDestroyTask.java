package de.flxnet.framez.tiles.tasks;

import java.util.List;

import org.bukkit.entity.Player;

import de.flxnet.framez.helpers.ConsoleHelper;
import de.flxnet.framez.helpers.NMSHelper;
import de.flxnet.framez.tiles.Tile;
import de.flxnet.framez.tiles.TileImageRender;
import lombok.Getter;
import lombok.Setter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TileDestroyTask implements Runnable {

	@Getter @Setter
	private List<Player> players;
	
	@Getter @Setter
	private List<Tile> tiles;
	
	@Getter @Setter
	private boolean force;
	
	public TileDestroyTask(List<Player> players, List<Tile> tiles, boolean force) {
		this.players = players;
		this.tiles = tiles;
		this.force = force;
	}
	
	@Override
	public void run() {
		int counter = 0;
		for(Player player : players) {
			for(Tile tile : tiles) {
				if((!tile.isImageLoaded() || !tile.isRendered()) && !force) continue;
				for(TileImageRender render : tile.getTileRenders()) {
					NMSHelper.sendPacket(player, render.getItemFrameDestroyData());
					counter++;
				}
			}
		}
		ConsoleHelper.consoleInfo("Sent " + counter + " tile destroy packets for " + players.size() + " players and " + tiles.size() + " tiles");
	}

}
