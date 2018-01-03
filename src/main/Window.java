package main;
/*
 * Handles the basics of creating the game window
 */
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Window extends Canvas {
	
	private static final long serialVersionUID = -240840600533728354L;
	
	/*
	 * @param width. window width
	 * @param height, window height
	 * @param title, game title
	 * @param game, instance of the game
	 */
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		//begins the game once window is created
		game.start();
	}
}