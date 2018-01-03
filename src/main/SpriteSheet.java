package main;
/*
 * Handles how the sprite sheet is split such
 * that in the code the user only has to give the program
 * a row, column, and size so that it can be located
 */
import java.awt.image.BufferedImage;

public class SpriteSheet {

	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage image){
		this.sprite = image;
	}
	
	/*
	 * Sets the params when the coder wants to load a sprite from 
	 * the sheet 
	 */
	public BufferedImage grabImage(int col, int row, int width, int height){
		BufferedImage img = sprite.getSubimage((row * 64) -64, (col *64) - 64, width, height);
		return img;
	}

}
