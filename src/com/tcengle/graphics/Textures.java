/**
 * 
 */
package com.tcengle.graphics;

import java.awt.image.BufferedImage;

/**
 * @author Tyler
 *
 */
public class Textures {
	 
	public BufferedImage player, Enemy;
	
	public SpriteSheet ss;
	
	public Textures(Game game){
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private static void getTextures(){
		//TODO stub method
	}
}
