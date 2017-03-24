/**
 * 
 */
package com.tcengle.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Random;

import com.tcengle.game.GameObject;
import com.tcengle.game.ObjectId;
import com.tcengle.graphics.Game;
import com.tcengle.graphics.SpriteSheet;

/**
 * @author Tyler
 *
 */
public class Enemy extends GameObject {

	private Game game;
	private int x;
	private int y;
	private int xVel;
	private int yVel;
	private SpriteSheet ss;
	
	private BufferedImage frame;
	public Enemy(Game game, int x, int y, BufferedImage ss, ObjectId id){
		super(x,y,id);
		this.game = game;
		this.ss = new SpriteSheet(ss);
		frame = this.ss.getFrame(1, 1, 32, 32);
	}
	
	public void tick(LinkedList<GameObject> obj) {
		yVel = 1;
		
		xVel = new Random().nextInt(1024);
		x+=xVel;
		y+=yVel;
		
	}

	public void render(Graphics g) {
		g.drawImage(frame, x, y, null);
		
	}
	
	public void setFrame(BufferedImage frame){
		this.frame = frame;
	}

	/* (non-Javadoc)
	 * @see com.monzohno.game.GameObject#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y,width,height);
	}

	

}
