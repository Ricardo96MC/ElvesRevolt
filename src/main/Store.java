package main;
/*
 * This class handles how the store option is handled
 * in the game , updating prices and whatever
 * the player has decided to purchase
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Store extends MouseAdapter {

	private Handler handler;
	HUD hud;
	
	//Sets the initial cost of all upgrades
	private int COST = 1000;
	private int box1 = COST;
	private int box2 = COST;
	private int box3 = COST;

	public Store(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}

	/*
	 * This method handles all the drawing to the user
	 * interface and what options they have
	 * to select from in the store
	 */
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("STORE", (Game.WIDTH / 2) - 100, 50);
		
		
		// Upgrade Health
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Health Upgrade", 110, 120);
		g.drawString("Cost: " + box1, 110, 150);
		g.drawRect(100, 100, 100, 80);

		// Upgrade Speed
		g.drawString("Speed Upgrade", 260, 120);
		g.drawString("Cost: " + box2, 260, 140);
		g.drawRect(250, 100, 100, 80);

		// Refill health
		g.drawString("Replenish Health", 410, 120);
		g.drawString("Cost: " + box3, 410, 140);
		g.drawRect(410, 100, 100, 80);
		g.drawString("SCORE: " + hud.getScore(), (Game.WIDTH / 2) - 90, 75);

	}
	
	/*
	 * Handles all the input by checking that the player has clicked within
	 * the bounds of a box, and then updates accordingly
	 */
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		int funds = hud.getScore();
		
		//Health upgrade
		if(mouseX >= 100 && mouseX <= 200){
			if(mouseY >= 100 && mouseY <= 180){
				System.out.println("Box1");
				if(funds >= box1){
					hud.setScore(funds  - box1);
					//Increments the cost after each upgrade
					box1 += 500;
					//updates the Health bar to its new upgrade
					hud.bounds += 20;
					hud.HEALTH = (100 + (hud.bounds / 2));
				}
			}
		}
		//Speed Upgrade
		if(mouseX >= 250 && mouseX <= 350){
			if(mouseY >= 100 && mouseY <= 180){
				System.out.println("Box2");
				if(funds >= box2){
					hud.setScore(funds  - box2);
					//Increments the cost after each upgrade
					box2 += 500;
					//Increases the speed of the player
					handler.speed++;
				}
			}
		}
		//Refill health
		if(mouseX >= 400 && mouseX <= 500){
			if(mouseY >= 100 && mouseY <= 180){
				System.out.println("Box3");
				if(funds >= box1){
					hud.setScore(funds  - box3);
					//Refills the users health
					hud.HEALTH = (100 + (hud.bounds / 2));
				}
			}
		}
	}
}
