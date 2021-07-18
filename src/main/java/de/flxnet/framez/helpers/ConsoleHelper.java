package de.flxnet.framez.helpers;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.flxnet.framez.FramezPlugin;
import de.flxnet.framez.tiles.Tile;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class ConsoleHelper {

	public static String prefix = "§f[§2Frame§4Z§f] §r";
	
	public static void player(Player player, String message) {
		player.sendMessage(prefix + message);
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 */
	public static void playerSuccess(Player player, String message) {
		player(player, "§a" + message);
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 */
	public static void playerWarning(Player player, String message) {
		player(player, "§6" + message);
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 */
	public static void playerError(Player player, String message) {
		player(player, "§4Error: §c" + message);
	}
	
	/**
	 * 
	 * @param player
	 * @param message
	 */
	public static void playerInfo(Player player, String message) {
		player(player, "§3" + message);
	}
	
	/**
	 * 
	 * @param player
	 */
	public static void playerNoPermission(Player player) {
		player(player, "§cYou do not have the permission to use this command.");
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void console(String message) {
		Bukkit.getConsoleSender().sendMessage(prefix + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void consoleSuccess(String message) {
		console("§a" + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void consoleWarning(String message) {
		console("§6" + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void consoleError(String message) {
		console("§4Error: §c" + message);
	}
	
	/**
	 * 
	 * @param message
	 */
	public static void consoleInfo(String message) {
		console("§3" + message);
	}
	
	/**
	 * 
	 * @param player
	 */
	public static void playerHelpPage(Player player) {
		player.sendMessage("");
		player.sendMessage(" §7*----------------------------------------*");
		player.sendMessage(" §bi§eMind §7(v" + FramezPlugin.getInstance().getDescription().getVersion() + ")");
		player.sendMessage("");
		player.sendMessage(" §a/task list §9[-complete] §7- Lists your current tasks");
		player.sendMessage(" §a/task create §c<text> §7- Create a new task with given text");
		player.sendMessage(" §a/task delete §c<id> §7- Delete a task with given id");
		player.sendMessage(" §a/task complete §c<id> §7- Complete a task with given id");
		player.sendMessage("");
		player.sendMessage(" §7*----------------------------------------*");
	}

	/**
	 * 
	 * @param player
	 * @param tile
	 */
	public static void sendInteractiveTileInformation(Player player, Tile tile) {
		
		TextComponent tileMessage = new TextComponent();
		
		TextComponent divider = new TextComponent(" | ");
		divider.setColor(ChatColor.GRAY);
		
		TextComponent tileName = new TextComponent(tile.getName());
		tileName.setColor(ChatColor.GREEN);
		tileName.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§7rows=§b" + tile.getRows() + "§7, columns=§b" + tile.getColumns())));
		
		TextComponent imageSource = new TextComponent("@");
		imageSource.setColor(ChatColor.LIGHT_PURPLE);
		imageSource.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§e" + tile.getImageSource().toString())));
		imageSource.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, tile.getImageSource().toString()));
		
		tileMessage.addExtra(tileName);
		tileMessage.addExtra(divider);
		tileMessage.addExtra(imageSource);
		
		player.spigot().sendMessage(tileMessage);
	}
	
}
