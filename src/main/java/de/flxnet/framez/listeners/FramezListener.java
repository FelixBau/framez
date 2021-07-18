package de.flxnet.framez.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.google.common.collect.Lists;

import de.flxnet.framez.helpers.TileHelper;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class FramezListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		TileHelper.initializeTiles(Lists.newArrayList(player));		
	}
	
	@EventHandler
	public void onPlayerItemHeld(PlayerItemHeldEvent e) {
		
	}
	
}
