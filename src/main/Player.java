package main;
/*
 * Creates the player object
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Player extends GameObject {
	Random r = new Random();
	Handler handler;

	private BufferedImage playerSprite;
	/*
	 * Creates the player, giving it and ID, and locating the 
	 * Sprite position from the sheet
	 */
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
		playerSprite = ss.grabImage(2, 1, 64, 64);
		
	}

	/*
	 * Sets the bounds of the player that can be used to detect collisions
	 */
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}

	/*
	 * Updates the players position
	 * Also disallows them from going beyond the edge 
	 * of the window
	 * checking if there has been a collision with another object
	 */
	public void tick() {
		x += velX;
		y += velY;
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		collision();
	}

	/*
	 * Checks if the objects has collided with another
	 * if so updating the corresponding elements
	 */
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ID.BasicEnemy || temp.getId() == ID.FastEnemy
					|| temp.getId() == ID.SmartEnemy || temp.getId() == ID.LevelBoss) {
				if (getBounds().intersects(temp.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
		}
	}

	public void render(Graphics g) {
		/*
		 * g.setColor(Color.white);
		 * g.fillRect((int) x, (int) y, 32, 32);
		 */
		
		g.drawImage(playerSprite, (int)x, (int)y, null);
	}
}
