package de.flxnet.framez.tiles;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import de.flxnet.framez.FramezPlugin;
import de.flxnet.framez.helpers.ConsoleHelper;
import de.flxnet.framez.helpers.JsonHelper;
import de.flxnet.framez.helpers.PersistenceHelper;
import lombok.Getter;
import lombok.Setter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TileManager {

	@Getter
	private List<Tile> tiles;
	
	@Getter @Setter
	private boolean log = false;
	
	public TileManager() {
		tiles = Lists.newArrayList();
		if(!load()) ConsoleHelper.consoleError("Could not load tiles from file!");
		
		Bukkit.getScheduler().runTaskTimer(FramezPlugin.getInstance(), task -> {
			boolean saved = save();
			if(!saved) ConsoleHelper.console("§cTiles could not be saved in scheduled task!");
			if(log) ConsoleHelper.console("§aTiles have been saved.");
		}, 20 * 1, 20 * 30);
	}
	
	public Tile getTile(String name) {
		return tiles.stream().filter(d -> d.getName().equals(name)).findAny().get();
	}
	
	public boolean addTile(Tile tile) {
		return tiles.add(tile) && save();
	}
	
	public boolean deleteTile(String name) {
		return tiles.remove(getTile(name));
	}
	
	public boolean save() {
		String json = JsonHelper.getGson().toJson(tiles);
		try {
			Files.write(json, PersistenceHelper.getAndCreateDataFile("tiles.json"), Charsets.UTF_8);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean load() {
		try {
			Tile[] savedTiles = JsonHelper.getGson().fromJson(new FileReader(PersistenceHelper.getAndCreateDataFile("tiles.json")), Tile[].class);
			if(savedTiles != null) tiles = Lists.newArrayList(savedTiles); return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
}
