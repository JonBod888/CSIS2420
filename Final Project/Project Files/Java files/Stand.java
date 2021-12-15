package virtualPet;

/**
 * The stand behavior. Extends Behavior.
 * 
 * @author Jonathan Bodily
 * 
 */
public class Stand extends Behavior{

	/**
	 * Constructs object with score and initializes Image.
	 * ;
	 * @param score Determines "weight" of behavior.
	 */
	public Stand(int score) {
		
		super(score);
		img = GameUtil.getImage("Stand.png");

	}

}
