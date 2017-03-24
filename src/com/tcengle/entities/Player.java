package com.tcengle.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.tcengle.game.Controller;
import com.tcengle.game.GameObject;
import com.tcengle.game.ObjectId;
import com.tcengle.graphics.Animation;
import com.tcengle.graphics.Game;
import com.tcengle.graphics.SpriteSheet;
import com.tcengle.input.InputHandler;

public class Player extends GameObject {
	protected double x;
	protected double y;
	protected double xVel;
	protected double yVel;
	protected int width;
	protected int height;
	protected int direction;
	protected BufferedImage player;
	protected InputHandler input;
	protected int tickCount;
	protected boolean walking;
	protected Game game;
	protected ObjectId id;
	protected Controller control;

	private Animation walkingRight;
	private Animation walkingLeft;

	public Player(Game game, int x, int y, int width, int height,
			BufferedImage spriteSheet, InputHandler input, ObjectId id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.id = id;
		this.game = game;
		this.height = height;
		this.width = width;
		this.game = game;
		this.input = input;
		control = game.getControl();
		SpriteSheet ss = new SpriteSheet(spriteSheet);
		// walkingRight = new Animation(this,ss,1,1,2,64,9);
		// walkingLeft = new Animation(this,ss,1,1,2,64,9);
		player = ss.getFrame(1, 1, width, height);
		xVel = 1;
		yVel = 1;

	}

	// sets the image of the player
	public void setFrame(BufferedImage frame) {
		player = frame;
	}

	private void Collision(LinkedList<GameObject> obj) {

		for (int i = 0; i < obj.size(); i++) {

			GameObject tempObject = obj.get(i);

			if (tempObject.getId() != ObjectId.PLAYER) {

				// for enemies with multiple bounds use a list to check all or
				// have them move away
				// bottom
				if (getBoundsBottom().intersects(tempObject.getBounds())) {

					y = tempObject.getY() - (height) + 3;
					velY = 0;
					// System.out.println("Collision");
				}

				// top
				if (getBoundsTop().intersects(tempObject.getBounds())) {

					y = tempObject.getY() + (height / 2) -4 ;
					velY = 0;
					// System.out.println("Collision");
				}

				// left
				if (getBoundsLeft().intersects(tempObject.getBounds())) {

					x = tempObject.getX() - (width / 2) + 74;
					velX = 0;
					// System.out.println("Collision");
				}

				// right
				if (getBoundsRight().intersects(tempObject.getBounds())) {

					x = tempObject.getX() - (width / 2) - 10;
					velX = 0;
					// System.out.println("Collision");
				}

				// top left
				// if (getBoundsTopLeft().intersects(tempObject.getBounds())) {
				//
				// x = tempObject.getX() - (width/2) + 78;
				// velX = 0;
				// // System.out.println("Collision");
				// }

				// top right
				if (getBoundsTopRight().intersects(tempObject.getBounds())) {

					x = tempObject.getX() - (width / 2) - 6;
					velX = 0;
					// System.out.println("Collision");
				}
			}

		}

	}

	public void tick(LinkedList<GameObject> obj) {
		// collision -- top side left right collision not just one rect.
		Collision(obj);
		KeyInput();

		// put animations here to do it while moving and possibly set walking to
		// false
	}

	private void KeyInput() {

		if (input.up.isPressed()) {
			yVel = 1.5;
			y -= yVel;
		}
		if (input.down.isPressed()) {
			yVel = 1.5;
			y += yVel;
		}
		if (input.left.isPressed()) {
			direction = 1;
			xVel = 1.5;
			x -= xVel;
		}
		if (input.right.isPressed()) {
			direction = 0;
			xVel = 1.5;
			x += xVel;

		}

	}

	public void render(Graphics g) {
		g.drawImage(player, (int) x, (int) y, null);

		/*Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsTopLeft());
		g2d.draw(getBoundsTopRight());*/

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.monzohno.game.GameObject#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + width - 10, (int) y + height / 4,
				width / 4, height / 2 + 27);
	}

	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x - (width / 2) + 18, (int) y + 6, width / 10,
				height - 10);
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + (width / 3) - 2, (int) y + 5,
				width / 2 - 3, height / 8 - 7);
	}

	// public Rectangle getBoundsTopLeft() {
	// return new Rectangle((int) x - width / 2 + 15, (int) y, width / 18,
	// height / 4 - 2);
	// }

	public Rectangle getBoundsTopRight() {
		return new Rectangle((int) x + width / 2 + 6, (int) y + 6, width / 18,
				height / 4 - 8);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + 7, (int) y + height - 4, width - 13,
				height / 20 - 5);
	}

}
