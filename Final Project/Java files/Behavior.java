package virtualPet;

import java.awt.Image;

/**
 * The abstract parent class for all the different behaviors. Includes an Image field and Image[] field.
 * 
 * @author Jonathan Bodily
 *
 */
public abstract class Behavior {

	int score;
	Image img;
	Image[] imgs;
	
	/**
	 * 
	 * @param score The score given to a particular behavior, may change throughout.
	 */
	Behavior(int score) {
		
		this.score = score;
	}
}
	
