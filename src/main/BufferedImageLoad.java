package main;
/*
 * Loads the image that will be used 
 * to load the sprites in
 */
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoad {

	private BufferedImage img;
	
	/*
	 * @param path, where the image is located
	 */
	public BufferedImage loadImage(String path) throws IOException{
		img = ImageIO.read(getClass().getResource(path));
		return img;
	}
}
