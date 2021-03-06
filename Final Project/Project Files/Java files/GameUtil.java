package virtualPet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


/**
 * Finds Image based on a String path. Format: "name.extension"
 * 
 * @author Jonathan Bodily
 *
 */
public class GameUtil {
	public static Image getImage(String path) {
		BufferedImage bi = null;
		try {
			URL u=GameUtil.class.getClassLoader().getResource(path);
			bi = ImageIO.read(u);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return bi;
		}
	
}