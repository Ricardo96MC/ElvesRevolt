package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class SmartEnemy extends GameObject {
	private Handler handler;
	private GameObject player;
	private BufferedImage sEnemySprite;
	

	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
		sEnemySprite = ss.grabImage(2, 2, 32, 32);
		
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player)
				player = handler.object.get(i);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 16, 16);
	}

	public void tick() {
		x += velX;
		y += velY;
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		float distance = (float) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		velX = ((-1 / distance) * diffX);
		velY = ((-1 / distance) * diffY);
	}

	public void render(Graphics g) {
		g.drawImage(sEnemySprite, (int) x, (int) y, null);
	}
}