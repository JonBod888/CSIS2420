package virtualPet;

import java.awt.Image;

/**
 * The act cute behavior. Extends Behavior.
 * 
 * @author Jonathan Bodily
 *
 */
public class ActCute extends Behavior{

	/**
	 * Constructs object with score and initializes Image[] with Images.
	 * 
	 * @param score Determines "weight" of behavior.
	 */
	public ActCute(int score) {
		
		super(score);
		
		imgs = new Image[30];
		
		for (int j = 1; j < 6; j++) {
			imgs[(j-1)*6] = GameUtil.getImage("Cute" + j + ".png");
			
			for (int i = (((j-1)*6)+1); i < (j*6); i++) {
				imgs[i] = imgs[(j-1)*6];
			}
		}
	}
}
