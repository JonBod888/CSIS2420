package virtualPet;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Flips a BufferedImage horizontally
 * 
 * @author Jonathan Bodily
 *
 */
public class FlipImage {
	
	/**
	 * Swaps the image pixels horizontally and moves them over into the previous spot.
	 * 
	 * @param image	The BufferedImage to be flipped.
	 * @return		The flipped image.
	 */
	public static Image createFlipped(BufferedImage image)
	    {
	        AffineTransform at = new AffineTransform();
	        at.concatenate(AffineTransform.getScaleInstance(-1, 1));
	        at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(), 0));
	        return createTransformed(image, at);
	    }
	 
	/**
	 * Places the graphic information contained in the AffineTransform object into a new BufferedImage.
	 * 
	 * @param image	A BufferedImage to be flipped.
	 * @param at	An AffineTransform object that contains information for a flipped image.
	 * @return		The flipped image.
	 */
	 private static Image createTransformed(BufferedImage image, AffineTransform at)
		    {
		        BufferedImage newImage = new BufferedImage(
		            image.getWidth(), image.getHeight(),
		            BufferedImage.TYPE_INT_ARGB);
		        Graphics2D g = newImage.createGraphics();
		        g.transform(at);
		        g.drawImage(image, 0, 0, null);
		        g.dispose();
		        return newImage;
		    }
}
