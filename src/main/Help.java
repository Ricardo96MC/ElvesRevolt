package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.GAMESTATE;
/*
 * Opens the help menu when activated
 * giving the user the controls on a way to return
 * to the menu
 */
public class Help extends MouseAdapter{

	private Game game;
	private Handler handler;

	//Creates the rectangles
	private Rectangle helpRectangle = new Rectangle((Game.WIDTH / 2) - 195, 200, 400, 250);
	private Rectangle menuButton = new Rectangle((Game.WIDTH / 2) - 95, 600, 150, 50);

	public Help(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	//Handles when the rectangle is pressed
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (mouseOver(mouseX, mouseY, (Game.WIDTH / 2) - 95, 600, 150, 50)) {
			game.state = GAMESTATE.Menu;
		}
		}
	//Checks the location of the mouse and returns true if withing the bounds of a rectangle
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
	
	//Renders all the Help Menu output
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Font f1 = new Font("arial", Font.BOLD, 50);
		g.setFont(f1);
		g.setColor(Color.white);
		g.drawString("Controls", (Game.WIDTH / 2) - 125, 100);

		// Help description
		Font f2 = new Font("arial", Font.BOLD, 30);
		g.drawString("W = Up", helpRectangle.x + 30, helpRectangle.y + 45);
		g.drawString("A = Left", helpRectangle.x + 30, helpRectangle.y + 105);
		g.drawString("S = Down", helpRectangle.x + 30, helpRectangle.y + 165);
		g.drawString("D = Right", helpRectangle.x + 30, helpRectangle.y + 225);
		g.drawString("Menu", menuButton.x + 12, menuButton.y + 40);

		g2.draw(helpRectangle);
		g2.draw(menuButton);
	}
}
