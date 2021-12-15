package virtualPet;

import java.util.TimerTask;

/**
 * Specifies the actions to be taken at regular intervals to change pet stats. Extends TimerTask.
 * 
 * @author Jonathan Bodily
 *
 */
public class StatTime extends TimerTask {

	Pet pet;
	
	/**
	 * Constructs StatTime object and takes a Pet object as a parameter to allow Pet fields to be manipulated.
	 * 
	 * @param pet	A Pet object.
	 */
	public StatTime(Pet pet) {
		
		this.pet = pet;
	}
	
	/**
	 * Overrides run method in TimerTask. Lowers Pet mood, fullness, and sleep unless the field is 0 or less.
	 */
	@Override
	public void run() {
		
		if (pet.mood > 0) {
		pet.mood--;
		}
		
		if (pet.fullness > 0) {
		pet.fullness--;
		}
		
		if (pet.sleep > 0) {
		pet.sleep--;
		}
		
		if (pet.sleeping) {
			pet.sleep+=8;
		}
		
	}

}
