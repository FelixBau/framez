package de.flxnet.framez.tiles.tasks;

import de.flxnet.framez.helpers.ConsoleHelper;
import de.flxnet.framez.tiles.Tile;
import lombok.Getter;
import lombok.Setter;

/**
 * Software by FLXnet
 * More info at FLXnet.de
 * Copyright (c) 2015-2021 by FLXnet
 * @author Felix
 */
public class TilePrepareTask implements Runnable {
	
	@Getter @Setter
	private Tile tile;
	
	private long startTime;
	
	private long endTime;
	
	public TilePrepareTask(Tile tile) {
		this.tile = tile;
	}
	
	@Override
	public void run() {
		ConsoleHelper.console("§7Starting new TilePrepareTask §b(" + tile.getName() + ")");
		startTime = System.currentTimeMillis();
		
		boolean loaded = tile.loadImage();
		System.out.println("loaded=" + loaded);
		
		if(!loaded) {
			ConsoleHelper.console("§cFailed TilePrepareTask §b(" + tile.getName() + ") §4could not load image");
			return;
		}
		
		tile.calculateDimensions();
		tile.splitImage();
		tile.renderImage();
		
		endTime = System.currentTimeMillis();
		ConsoleHelper.console("§7Finished TilePrepareTask §b(" + tile.getName() + ")§7, took " + timeTaken() + "ms");
	}
	
	private long timeTaken() {
		return endTime - startTime;
	}
	
	/*
	@Override
	public void run() {
		ConsoleHelper.console("§6TilePrepareTask §b(" + tile.getName() + ")");
		
		ConsoleHelper.console("§7[STEP1] §2Loading image: §estarting");
		tile.loadImage();
		ConsoleHelper.console("§7[STEP1] §2Loading image: §afinished");
		
		ConsoleHelper.console("§7[STEP2] §2Calculating dimensions: §estarting");
		tile.calculateDimensions();
		ConsoleHelper.console("§7[STEP2] §2Calculating dimensions: §afinished");
		
		ConsoleHelper.console("§7[STEP3] §2Split image: §estarting");
		tile.splitImage();
		ConsoleHelper.console("§7[STEP3] §2Split image: §afinished");
		
		ConsoleHelper.console("§7[STEP4] §2Render image: §estarting");
		tile.renderImage();
		ConsoleHelper.console("§7[STEP4] §2Render image: §afinished");
		
		ConsoleHelper.console("§6TilePrepareTask: cols=" + tile.getColumns() + ", rows=" + tile.getRows() + ", loaded=" + tile.isImageLoaded() + ", rendered=" + tile.isRendered());
	}
	*/

}
