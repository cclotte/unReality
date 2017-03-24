/**
 * 
 */
package com.tcengle.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import com.tcengle.game.GameObject;
import com.tcengle.game.ObjectId;

/**
 * @author 100815
 *
 */
public class TestBlock extends GameObject {
	
	private int width = 64;
	private int height = 64;
	/**
	 * @param x
	 * @param y
	 * @param id
	 */
	public TestBlock(int x, int y, ObjectId id) {
		super(x, y, id);
	}

	/* (non-Javadoc)
	 * @see com.monzohno.game.GameObject#tick(java.util.LinkedList)
	 */
	@Override
	public void tick(LinkedList<GameObject> object) {
			
	}

	/* (non-Javadoc)
	 * @see com.monzohno.game.GameObject#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);	
		g.fillRect((int)x,(int)y, 64, 64);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.draw(getBounds());
	}

	/* (non-Javadoc)
	 * @see com.monzohno.game.GameObject#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
	}
	
	

}
