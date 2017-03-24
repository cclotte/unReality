package com.tcengle.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	String path;
	BufferedImage sheet;
	int width;
	int height;

	public SpriteSheet(BufferedImage image) {
		this.sheet = image;
		this.height = sheet.getHeight();
		this.width = sheet.getWidth();
	}

	public BufferedImage getFrame(int col, int row, int width, int height) {
		BufferedImage image = sheet.getSubimage((col * width) - width,
				(row * width) - width, width, height);
		return image;

	}

	public BufferedImage getSheet() {
		return sheet;
	}

}
