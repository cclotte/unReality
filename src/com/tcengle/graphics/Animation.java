package com.tcengle.graphics;

import java.awt.image.BufferedImage;

import com.tcengle.entities.Player;

public class Animation {
	private int row;
	private int col;
	private int frames;
	private double frameDuration;
	private SpriteSheet spriteSheet;
	private BufferedImage[] animation;
	private Player player;
	private int currentFrame;
	private int counter;
	private int width;
	private int height;
	private int elapsedTicks = 0;

	public Animation() {
		this(null, null,0, 0, 0, 0, 0,0);
	}

	public Animation(Player player, SpriteSheet spriteSheet, int row, int col,
			int frames, int width,int height, double frameDuration) {
		this.row = row;
		this.col = col;
		this.width = width;
		this.height = height;
		this.frames = frames;
		this.frameDuration = frameDuration;
		this.spriteSheet = spriteSheet;
		this.player = player;
		animation = new BufferedImage[frames];
		for (int i = 0; i < frames; i++) {
			animation[i] = spriteSheet.getFrame(col + i, row, width,height);
		}

	}
	//sets the image to specific frame
	public void setFrame(int frame) {
		player.setFrame(animation[frame]);
	}

	// TODO use A systemTimer object Instead of ticks
	// sets entity to next frame
	public void nextFrame(int tickCount, int speed) {

		elapsedTicks++;
		// System.out.println("Time Add" + elapsedTicks);
		if (elapsedTicks > frameDuration) {
			elapsedTicks = 0;
			//System.out.println("time Reset" + elapsedTicks);
		}
		if (frames <= currentFrame) {
			currentFrame = 0;
			//System.out.println("frame Reset");
		}
		if (elapsedTicks == frameDuration) {

			player.setFrame(animation[currentFrame]);
			/*System.out.println("Animating " + elapsedTicks + "Frames "
					+ currentFrame);*/
			currentFrame++;
		}
	}

}
