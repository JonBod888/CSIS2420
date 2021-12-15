package virtualPet;

import java.awt.Image;

/**
 * The sleep behavior. Extends Behavior.
 * 
 * @author Jonathan Bodily
 *
 */
public class Sleep extends Behavior{

	/**
	 * Constructs object with score and initializes Image[] with Images.
	 * 
	 * @param score Determines "weight" of behavior.
	 */
	public Sleep(int score) {
	
		super(score);
		
		imgs = new Image[30];
		imgs[0] = GameUtil.getImage("Sleep1.png");
		
		for (int i = 1; i < 15; i++) {
			imgs[i] = imgs[0];
		}
		
		imgs[15] = GameUtil.getImage("Sleep2.png");
		
		for (int i = 16; i < 30; i++) {
			imgs[i] = imgs[15];
		}
	}

}
