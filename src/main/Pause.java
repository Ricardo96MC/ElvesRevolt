package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;


public class Pause extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	
	private Rectangle resumeButton = new Rectangle((Game.WIDTH / 2) - 95, 150, 150, 50);
	private Rectangle helpButton = new Rectangle((Game.WIDTH / 2) - 95, 250, 150, 50);
	private Rectangle menuButton = new Rectangle((Game.WIDTH / 2) - 95, 350, 150, 50);
	private Rectangle exitButton = new Rectangle((Game.WIDTH / 2) - 95, 450, 150, 50);
	
	public Pause(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		Graphics g;
		int mouseX = e.getX();
		int mouseY = e.getY();

		if (mouseOver(mouseX, mouseY, (Game.WIDTH / 2) - 95, 250, 150, 50)) {
			JOptionPane.showMessageDialog(game, "W = Up \n A = Left \n S = Down \n D = Down");
		}
	}

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
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		Font f1 = new Font("arial", Font.BOLD, 50);
		g.setFont(f1);
		g.setColor(Color.white);
		g.drawString("Pause ",( Game.WIDTH / 2 )- 125, 100);
		
		//Pause button fonts
		Font f2 = new Font("arial", Font.BOLD, 35);
		g.setFont(f2);
		
		g.drawString("Resume" , resumeButton.x + 30, resumeButton.y + 35);
		g.drawString("Help", helpButton.x + 30, helpButton.y + 35);
		g.drawString("Main Menu" , menuButton.x + 30 , menuButton.y + 35);
		g.drawString("Exit", exitButton.x + 30, exitButton.y + 35);
		
		g2.draw(resumeButton);
		g2.draw(helpButton);
		g2.draw(menuButton);
		g2.draw(exitButton);
	}
}
