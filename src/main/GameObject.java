package main;

/*
 * Handles all the game objects that
 * will be created within the game,
 */
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {
	protected float x, y;
	protected ID id;
	protected float velX, velY;

	/*
	 * @param x position of the object
	 * @param y pos of object
	 * @param the ID of the object
	 */
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	//Update method that all objects will need
	public abstract void tick();
	//Ensures that when an objects is created they also have the method to render it 
	public abstract void render(Graphics g);
	//Sets the bounds of the object that will help in collision detection
	public abstract Rectangle getBounds();

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public float getVelX() {
		return velX;
	}

	public float getVelY() {
		return velY;
	}
}