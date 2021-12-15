package virtualPet;

/**
 * The sad behavior. Extends Behavior.
 * 
 * @author Jonathan Bodily
 *
 */
public class Sad extends Behavior{
	
	/**
	 * Constructs object with score and initializes Image.
	 * 
	 * @param score Determines "weight" of behavior.
	 */
	public Sad(int score) {
		
		super(score);
		img = GameUtil.getImage("Mad.png");
	}

}
