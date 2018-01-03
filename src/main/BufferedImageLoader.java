package main;
/*
 * Loads the spriteSheet into the game
 * once the game has been executed
 */
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	BufferedImage image;
	
	/*
	 * Trys to load the image into the game
	 * and notify thru console whether it was 
	 * found or not
	 */
	public BufferedImage loadImage(String path){
		try {
			image = ImageIO.read(getClass().getResource(path));
			System.out.println("Sprite Sheet Loaded");

		} catch (IOException e) {
			System.out.println("Image file not found");
			e.printStackTrace();
		}
		return image;
	}
	
}
