package de.flxnet.framez.commands;

import java.net.URL;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import de.flxnet.framez.FramezPlugin;
import de.flxnet.framez.helpers.ConsoleHelper;
import de.flxnet.framez.helpers.TileHelper;
import de.flxnet.framez.tiles.Tile;
import de.flxnet.framez.tiles.TileLocation;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TileCommand implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage(ConsoleHelper.prefix + "§cThis command is for players only.");
			return true;
		}
		
		Player player = (Player) sender;
		
		// /tile list
		if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
			
			List<Tile> tiles = FramezPlugin.getInstance().getTileManager().getTiles();
			
			if(tiles.size() == 0) {
				ConsoleHelper.playerWarning(player, "Currently there are no tiles");
				return true;
			}
			
			ConsoleHelper.playerWarning(player, "Currently saved tiles:");
			for(Tile tile : tiles) {
				ConsoleHelper.sendInteractiveTileInformation(player, tile);
			}
			
			return true;
		}
		
		// /tile set <name> <url>
		if(args.length == 3 && args[0].equalsIgnoreCase("set")) {
			
			String name = args[1];
			String urlString = args[2];
			URL url = TileHelper.getUrl(urlString);
			
			Tile tile = FramezPlugin.getInstance().getTileManager().getTile(name);
			tile.resetImage(url);
			
			ConsoleHelper.playerSuccess(player, "Set new image source for tile §b" + tile.getName());
			TileHelper.destroyTile(tile, Lists.newArrayList(Bukkit.getOnlinePlayers()), true);
			TileHelper.provideTile(tile, Lists.newArrayList(Bukkit.getOnlinePlayers()));
			
			return true;
		}
		
		// /tile add <name> <url>
		if(args.length == 3 && args[0].equalsIgnoreCase("add")) {
			
			String name = args[1];
			String urlString = args[2];
			URL url = TileHelper.getUrl(urlString);
			Location location = player.getLocation();

			Tile tile = new Tile(name, url, TileLocation.of(location, TileHelper.getFacing(player.getFacing())));
			
			boolean added = FramezPlugin.getInstance().getTileManager().addTile(tile);
			
			if(!added) {
				ConsoleHelper.playerError(player, "Could not add tile '" + name + "'");
				return true;
			}
			
			ConsoleHelper.playerSuccess(player, "Added new tile §b" + tile.getName() + " §7(" + tile.getLocation().toString() + ")");
			TileHelper.provideTile(tile, Lists.newArrayList(Bukkit.getOnlinePlayers()));
			
			return true;
		}
		
		// /tile delete <name>
		if(args.length == 2 && args[0].equalsIgnoreCase("delete")) {
			
			String name = args[1];
			
			Tile tile = FramezPlugin.getInstance().getTileManager().getTile(name);
			TileHelper.destroyTile(tile, Lists.newArrayList(Bukkit.getOnlinePlayers()), false);
			
			boolean deleted = FramezPlugin.getInstance().getTileManager().deleteTile(name);
			if(!deleted) {
				ConsoleHelper.playerError(player, "Could not delete tile '" + name + "'");
				return true;
			}
			
			ConsoleHelper.playerSuccess(player, "Tile §b" + tile.getName() + " §ahas been deleted.");
			
			return true;
		}
		
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 1) return Lists.newArrayList("list", "set", "add", "delete");
		return null;
	}

}
