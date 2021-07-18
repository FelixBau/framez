package de.flxnet.framez;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.collect.Lists;

import de.flxnet.framez.commands.TileCommand;
import de.flxnet.framez.helpers.ConsoleHelper;
import de.flxnet.framez.helpers.TileHelper;
import de.flxnet.framez.listeners.FramezListener;
import de.flxnet.framez.tiles.TileManager;
import lombok.Getter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class FramezPlugin extends JavaPlugin {

	@Getter
	private static FramezPlugin instance;
	
	@Getter
	private TileManager tileManager;
	
	@Override
	public void onEnable() {
		instance = this;
		this.tileManager = new TileManager();
		
		initializeCommands();
		initializeEventListeners();
		
		TileHelper.initializeTiles(Lists.newArrayList(Bukkit.getOnlinePlayers()));
		
		ConsoleHelper.consoleInfo("Plugin enabled!");
	}
	
	@Override
	public void onDisable() {
		ConsoleHelper.consoleInfo("Plugin disabled!");
	}
	
	private void initializeCommands() {
		PluginCommand tileCommand = getCommand("tile");
		TileCommand tileCommandExecutor = new TileCommand();
		tileCommand.setExecutor(tileCommandExecutor);
		tileCommand.setTabCompleter(tileCommandExecutor);
	}
	
	private void initializeEventListeners() {
		PluginManager pluginManager = getServer().getPluginManager();
		pluginManager.registerEvents(new FramezListener(), this);
	}
	
}
