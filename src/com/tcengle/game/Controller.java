/**
 * 
 */
package com.tcengle.game;

import java.awt.Graphics;
import java.util.LinkedList;



/**
 * @author Monzohno
 * 
 */
public class Controller {
	
	public LinkedList<GameObject> GObjects = new LinkedList<>();
	
	private GameObject tempObject;
	
	public Controller(){
	}

	
	
	public void tick() {
		for(int i = 0; i<GObjects.size();i++){
			tempObject = GObjects.get(i);
			tempObject.tick(GObjects);
		}

	}

	public void render(Graphics g) {
		for(int i = 0; i<GObjects.size();i++){
			tempObject = GObjects.get(i);
			tempObject.render(g);
		}

	}
	public void addGameObject(GameObject obj){
		GObjects.add(obj);
	}
	public void removeGameObject(GameObject obj){
		GObjects.remove(obj);
		
	}
	

	
}
