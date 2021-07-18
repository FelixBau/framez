package de.flxnet.framez.helpers;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import de.flxnet.framez.FramezPlugin;
import de.flxnet.framez.tiles.Tile;
import de.flxnet.framez.tiles.TileDirection;
import de.flxnet.framez.tiles.tasks.TileDestroyTask;
import de.flxnet.framez.tiles.tasks.TilePrepareTask;
import de.flxnet.framez.tiles.tasks.TileProvisionTask;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TileHelper {

	public static void initializeTiles(List<Player> players) {
		List<Tile> tiles = FramezPlugin.getInstance().getTileManager().getTiles();
		tiles.forEach(tile -> {
			TileHelper.provideTile(tile, players);
		});
	}
	
	public static void prepareTile(Tile tile) {
		submitAsyncTask(new TilePrepareTask(tile));
	}
	
	public static void provideTile(Tile tile, List<Player> players) {
		submitAsyncTask(new TileProvisionTask(tile, players));
	}
	
	public static void destroyTile(Tile tile, List<Player> players, boolean force) {
		submitAsyncTask(new TileDestroyTask(Lists.newArrayList(players), Lists.newArrayList(tile), force));
	}
	
	public static void destroyAllTiles(List<Player> players, boolean force) {
		submitAsyncTask(new TileDestroyTask(players, FramezPlugin.getInstance().getTileManager().getTiles(), force));
	}
	
	public static URL getUrl(String urlString) {
		try {
			return new URL(urlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static TileDirection getFacing(BlockFace blockFace) {
		return TileDirection.valueOf(blockFace.toString());
	}
	
	private static void submitAsyncTask(Runnable runnable) {
		Bukkit.getScheduler().runTaskAsynchronously(FramezPlugin.getInstance(), runnable);
	}

}
