package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
/*
 * Handles what the player will see while in play
 * Keeps track of the level and score such that it 
 * will be displayed upon loss, and score updated if 
 * something is purchased in the shop
 */
public class HUD {
	//Starting health
	public static float HEALTH = 100;
	//initial health color at full
	private float greenValue = 255;
	private int score = 0;
	private int level = 1;
	//Allows for the health bar to be extended/upgraded
	public int bounds = 0;
	/*
	 *  Clamps the health bar so that the color doesn't go past
	 *  the set size and stays within the rectangle
	 *  Changes the color of the bar is the player loses more health
	 *  Updates the score as time/ticks increase
	 */
	public void tick() {
		HEALTH = (int) Game.clamp(HEALTH, 0, 100 + bounds / 2);
		greenValue = HEALTH * 2;
		greenValue = (int) Game.clamp(greenValue, 0, 255);
		score++;
	}

	/*
	 * Creates the health bar, score count, and level count
	 * 
	 */
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75, (int) greenValue, 0));
		
		g.fillRect(15, 15, (int) HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + bounds, 32);
		
		Font f = new Font("arial", Font.PLAIN, 20);
		g.setFont(f);
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 84);
		g.drawString("Spacebar for Store" , 15, 104);
		g.drawString("Pause = p", 15, 124);
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getScore() {
		return score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}