package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class FastEnemy extends GameObject {
	private Handler handler;
	private BufferedImage fEnemySprite;

	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX = 2;
		velY = 9;
		SpriteSheet ss = new SpriteSheet(Game.spriteSheet);
		fEnemySprite = ss.grabImage(1, 2, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public void tick() {
		x += velX;
		y += velY;
		if (y <= 0 || y >= Game.HEIGHT - 32)
			velY *= -1;
		if (x <= 0 || x >= Game.WIDTH - 16)
			velX *= -1;
	}

	public void render(Graphics g) {
		//g.setColor(Color.CYAN);
		//g.fillRect((int) x, (int) y, 16, 16);
		g.drawImage(fEnemySprite, (int)x, (int)y, null);
	}
}
