package virtualPet;

import java.awt.Image;

/**
 * The eat behavior. Extends Behavior.
 * 
 * @author Jonathan Bodily
 *
 */
public class Eat extends Behavior{

	/**
	 * Constructs object with score and initializes Image[] with Images.
	 */
	public Eat() {
		
		super(0);
		
		imgs = new Image[30];
		
		for (int j = 1; j < 6; j++) {
			imgs[(j-1)*6] = GameUtil.getImage("Eat" + j + ".png");
			
			for (int i = (((j-1)*6)+1); i < (j*6); i++) {
				imgs[i] = imgs[(j-1)*6];
			}
		}
	}
}
