package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import main.Game.GAMESTATE;
/*
 * Handles the main menu executions , and the endgame
 * if the user wants to play again
 */
public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	/*
	 * Declare the menu buttons
	 */
	private Rectangle playButton = new Rectangle((Game.WIDTH / 2) - 95, 150, 150, 50);
	private Rectangle helpButton = new Rectangle((Game.WIDTH / 2) - 95, 250, 150, 50);
	private Rectangle quitButton = new Rectangle((Game.WIDTH / 2) - 95, 350, 150, 50);
	private Rectangle helpRectangle = new Rectangle((Game.WIDTH / 2) - 195, 200, 400, 250);
	private Rectangle menuButton = new Rectangle((Game.WIDTH / 2) - 95, 600, 150, 50);
	private Rectangle tryAgain = new Rectangle((Game.WIDTH / 2) - 95, 400, 160, 50);
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	//Checks if the player has pressed within a rectangle
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (mouseOver(mouseX, mouseY, (Game.WIDTH / 2) - 95, 150, 150, 50)) {
			game.state = GAMESTATE.Game;
			handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		}
		if (mouseOver(mouseX, mouseY, (Game.WIDTH / 2) - 95, 250, 150, 50)) {
			game.state = GAMESTATE.Help;
		}
		if(mouseOver(mouseX, mouseY,(Game.WIDTH / 2) - 95, 400, 160, 50)){
			hud.setLevel(1);
			hud.setScore(0);
			game.state = GAMESTATE.Game;
		}
	}
	//Returns the location of the mouse and true if within the bounds of the params
	private boolean mouseOver(int mouseX, int mouseY, int x, int y, int width, int height) {
		if (mouseX > x && mouseX < x + width) {
			if (mouseY > y && mouseY < y + height) {
				return true;
			} else
				return false;
		} else {
			return false;
		}
	}

	public void tick() {

	}
	//Renders all the things the user will see
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		/*
		 * Game title and params
		 */
		Font f1 = new Font("arial", Font.BOLD, 50);
		// Menu button fonts
		Font f2 = new Font("arial", Font.BOLD, 35);
		g.setFont(f2);

		/*
		 * Label the buttons and place them in their rectangle
		 */
		if (game.state == GAMESTATE.Menu) {
			
			g.setFont(f1);
			g.setColor(Color.white);
			g.drawString("SURVIVE ", (Game.WIDTH / 2) - 125, 100);

			g.drawString("Play", playButton.x + 28, playButton.y + 40);
			g.drawString("Help", helpButton.x + 28, helpButton.y + 40);
			g.drawString("Quit", quitButton.x + 28, quitButton.y + 40);

			// Draw the buttons to the menu
			g2.draw(playButton);
			g2.draw(helpButton);
			g2.draw(quitButton);
		} else if (game.state == GAMESTATE.Help) {
			g.setColor(Color.white);
			g.setFont(f1);
			g.drawString("Controls" ,(Game.WIDTH / 2) - 125, 100);
			g.setFont(f2);
			
			g.drawString("W = Up", helpRectangle.x + 30, helpRectangle.y + 45);
			g.drawString("A = Left", helpRectangle.x + 30, helpRectangle.y + 105);
			g.drawString("S = Down", helpRectangle.x + 30, helpRectangle.y + 165);
			g.drawString("D = Right", helpRectangle.x + 30, helpRectangle.y + 225);
			g.drawString("Menu", menuButton.x + 30, menuButton.y + 35);

			g2.draw(helpRectangle);
			g2.draw(menuButton);
		}else if(game.state == GAMESTATE.GameOver){
			g.setColor(Color.white);
			g.setFont(f1);
			
			g.drawString("GAME OVER" ,(Game.WIDTH / 2) - 165, 120);
			g.setFont(f2);	
			
			g.drawString("Final Level: " + hud.getLevel(), (Game.WIDTH / 2) - 140, 220);
			g.drawString("Final Score: " + hud.getScore(), (Game.WIDTH / 2) - 150, 320);
			
			g.drawString("Try Again" ,(Game.WIDTH / 2) - 95, 443);
			g2.draw(tryAgain);
		}
	}

}
