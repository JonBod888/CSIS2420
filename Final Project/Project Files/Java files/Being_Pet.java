package virtualPet;

/**
 * The being pet behavior. Extends Behavior.
 * 
 * @author Jonathan Bodily
 *
 */
public class Being_Pet extends Behavior{


	/**
	 * Constructs object with score and initializes Image with an Image.
	 */
	public Being_Pet() {
		
		super(0);
		
		img = GameUtil.getImage("Pet.png");
	}
}
