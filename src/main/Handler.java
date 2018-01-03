package main;

import java.awt.Graphics;
import java.util.LinkedList;

/*
 * Handles all the objects in the game, storing them in a list
 * making sure that they all get rendered, locations updated
 * and allowing for complete removal of objects (excluding player)
 */
public class Handler {
	/*
	 * LinkedList over ArrayList as there is not a necessity for getters and
	 * setters, only the nedd ot be able to create and remove objects quickly
	 */
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	/*
	 * Handles the player speed initially and then allows for an easy way to
	 * upgrade it when purchased at the store
	 */
	public int speed = 5;

	/*
	 * Updates the objects location
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}

	/*
	 * Renders all objects and there set designs
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	/*
	 * Clears all the objects on screen excluding the player
	 */
	public void clearEnemies() {
		for (int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);
			if (temp.getId() == ID.Player) {
				object.clear();
				addObject(new Player((int) temp.getX(), (int) temp.getY(), ID.Player, this));
			}
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
