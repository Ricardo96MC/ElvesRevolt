package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import main.Game.GAMESTATE;

public class MouseInput implements MouseListener {
	private Handler handler;
	
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		/*public Rectangle playButton = new Rectangle((Game.WIDTH / 2) - 95, 150, 150, 50);
		public Rectangle helpButton = new Rectangle((Game.WIDTH / 2) - 95, 350, 150, 50);
		public Rectangle quitButton = new Rectangle((Game.WIDTH / 2) - 95, 250, 150, 50);
*/
		int mouseX = e.getX();
		int mouseY = e.getY();
		//Play Button
		if(mouseX >= Game.WIDTH / - 95 && mouseX < Game.WIDTH + 5){
			if(mouseY >= 150 && mouseY <= 300){
				Random r;
				r = new Random();
				//Play button has been pressed
				Game.state = GAMESTATE.Game;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
