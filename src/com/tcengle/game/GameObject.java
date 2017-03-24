package com.tcengle.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


public abstract class GameObject {
	protected int width;
	protected int height;
	protected double x;
	protected double y;
	protected double velX;
		protected double velY;
	protected ObjectId id;
	
	public GameObject(int x, int y, ObjectId id){
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick(LinkedList<GameObject> object);
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
	/**
	 * @return the velX
	 */
	public double getVelX() {
		return velX;
	}

	/**
	 * @param velX the velX to set
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

	/**
	 * @return the velY
	 */
	public double getVelY() {
		return velY;
	}

	/**
	 * @param velY the velY to set
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}
		
	
	public ObjectId getId(){
		return id;
	}
		
	
}
