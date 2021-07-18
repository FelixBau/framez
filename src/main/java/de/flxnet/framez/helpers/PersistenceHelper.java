package de.flxnet.framez.helpers;

import java.io.File;
import java.io.IOException;

import de.flxnet.framez.FramezPlugin;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class PersistenceHelper {

	public static File getModelFolder() {
		File pluginDataFolder = FramezPlugin.getInstance().getDataFolder();
		if(!pluginDataFolder.exists()) pluginDataFolder.mkdirs();
		File modelFolder = new File(pluginDataFolder, "models");
		if(!modelFolder.exists()) modelFolder.mkdirs();
		return modelFolder;
	}
	
	public static File getAndCreateDataFile(String fileName) {
		File pluginDataFolder = FramezPlugin.getInstance().getDataFolder();
		if(!pluginDataFolder.exists()) pluginDataFolder.mkdirs();
		File dataFile = new File(pluginDataFolder, fileName);
		try {
			if(!dataFile.exists()) dataFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataFile;
	}
	
}
