package com.tcengle.input;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {
    private BufferedImage image;
    public String path;
    
    public BufferedImage ImageLoader(String path) throws IOException{
	image = ImageIO.read(getClass().getResourceAsStream(path));
	return image;
    }

}
