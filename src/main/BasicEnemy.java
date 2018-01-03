package main;
/*
 * Creates the basic enemy in game
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class BasicEnemy extends GameObject {
	private Handler handler;
	private BufferedImage bEnemySprite;

	/*
	 * Creates the basic enemy, 
	 * Setting the ID, velocity, and the position of the sprite
	 * it will be using in the SpriteSheet
	 */
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 5;
		velY = 5;
		
		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
		bEnemySprite = ss.grabImage(1, 1, 32, 32);
	}

	/*
	 * Gets the basicEnemy bounds
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	/*
	 * Updates position of object
	 * Prevents the object from leaving the screen
	 * and also redirects the velocity so that it bounces
	 * off the edges 
	 */
	public void tick() {
		x += velX;
		y += velY;
		if (y <= 0 || y >= Game.HEIGHT - 32)
			velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 16)
			velX *= -1;
	}

	/*
	 * Renders the enemy
	 */
	public void render(Graphics g) {
		
	/*	g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 16, 16);
		*/
		
		g.drawImage(bEnemySprite, (int) x ,(int) y, null); 
	}
}