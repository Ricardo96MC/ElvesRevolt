package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/*
 * Creates a boss type enemy
 */
public class LevelBoss extends GameObject {
	private Handler handler;
	private GameObject player;

	//Creates the boss object
	public LevelBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 24, 24);
	}
	/*
	 * LevelBoss tries to track down to the player, and is faster
	 */
	public void tick() {

		x += velX;
		y += velY;

		float diffX = x - player.getX() - 5;
		float diffY = y - player.getY() - 5;
		float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		velX = ((-3 / distance) * diffX);
		velY = ((-3 / distance) * diffY);
	}

	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect((int) x, (int) y, 24, 24);
	}
}