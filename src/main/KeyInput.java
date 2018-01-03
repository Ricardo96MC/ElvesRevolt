package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game.GAMESTATE;

/*
 * Handles all the user key input
 * that will move the player, open menus, or exit/pasue the program
 */
public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[5];
	Game game;

	public KeyInput(Handler handler, Game game) {
		this.handler = handler;

		this.game = game;

		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
		keyDown[4] = false;
	}

	/*
	 * Handles what to do when a key is pressed
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W) {
					tempObject.setVelY(-handler.speed);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_S) {
					tempObject.setVelY(handler.speed);
					keyDown[1] = true;
				}
				if (key == KeyEvent.VK_D) {
					tempObject.setVelX(handler.speed);
					keyDown[2] = true;
				}
				if (key == KeyEvent.VK_A) {
					tempObject.setVelX(-handler.speed);
					keyDown[3] = true;
				}
			}
		}
		//Closes the game
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		//Pauses the game
		if (key == KeyEvent.VK_P) {
			if (game.state == GAMESTATE.Game) {
				if (Game.paused) {
					Game.paused = false;
				} else {
					Game.paused = true;
				}
			}

		}
		/*
		 * Opens the store if in play or closes it
		 * if the player is already in the store
		 */
		if (key == KeyEvent.VK_SPACE) {

			if (Game.state == GAMESTATE.Game) {
				Game.paused = true;
				Game.state = GAMESTATE.Store;

			} else if (Game.state == GAMESTATE.Store) {

				Game.state = GAMESTATE.Game;
			}
		}
	}
	/*
	 * Handles what to do when a key is released
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getId() == ID.Player) {
				if (key == KeyEvent.VK_W)
					keyDown[0] = false; // tempObject.setVelY(0);
				if (key == KeyEvent.VK_S)
					keyDown[1] = false; // tempObject.setVelY(0);
				if (key == KeyEvent.VK_D)
					keyDown[2] = false; // tempObject.setVelX(0);
				if (key == KeyEvent.VK_A)
					keyDown[3] = false; // tempObject.setVelX(0);
				if (!keyDown[0] && !keyDown[1])
					tempObject.setVelY(0);
				if (!keyDown[2] && !keyDown[3])
					tempObject.setVelX(0);
			}
		}
	}
}