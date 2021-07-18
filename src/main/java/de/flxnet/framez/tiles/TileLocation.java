package de.flxnet.framez.tiles;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.google.gson.annotations.Expose;

import lombok.Getter;
import lombok.Setter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TileLocation {

	@Expose
	@Getter @Setter
	private String worldName;
	
	@Expose
	@Getter @Setter
	private int x;
	
	@Expose
	@Getter @Setter
	private int y;
	
	@Expose
	@Getter @Setter
	private int z;
	
	@Expose
	@Getter @Setter
	private TileDirection direction;
	
	public TileLocation(String worldName, int x, int y, int z, TileDirection direction) {
		this.worldName = worldName;
		this.x = x;
		this.y = y;
		this.z = z;
		this.direction = direction;
	}
	
	public static TileLocation of(Location location, TileDirection direction) {
		return new TileLocation(location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ(), direction);
	}
	
	public Location toBukkitLocation() {
		return new Location(Bukkit.getWorld(this.worldName), this.x, this.y, this.z);
	}
	
	@Override
	public String toString() {
		return "TileLocation(world=" + worldName + ", x=" + x + ", y=" + y + ", z=" + z + ", direction=" + direction + ")";
	}
	
}
