package virtualPet;

/**
 * The beg behavior. Extends Behavior.
 * 
 * @author Jonathan Bodily
 *
 */
public class Beg extends Behavior{
	
	/**
	 * Constructs object with score and initializes Image.
	 * 
	 * @param score Determines "weight" of behavior.
	 */
	public Beg(int score) {
		
		super(score);
		img = GameUtil.getImage("Beg.png");
	}

}
