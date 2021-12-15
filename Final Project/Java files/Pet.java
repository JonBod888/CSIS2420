package virtualPet;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * A Pet object that includes double x and y coordinate values and int values representing mood, fullness, and sleep.
 * 
 * @author Jonathan Bodily
 *
 */
public class Pet {
	
	double x, y;
	int mood, fullness, sleep, count;
	int walkcount = 93;
	boolean left = true;
	boolean directionfound = false;
	boolean sleeping = false;
	Image hungry = GameUtil.getImage("Hungry.png");
	Image food = GameUtil.getImage("Food.png");
	Image heart = GameUtil.getImage("Heart.png");
	Image img;

	/**
	 * Constructs Pet object.
	 * 
	 * @param x			Value for the x-axis on frame.
	 * @param y			Value for the y-axis on frame.
	 * @param mood		Int representing pet's mood.
	 * @param fullness	Int representing pet's fullness(food).
	 * @param sleep		Int representing pet's sleep.
	 */
	public Pet(double x, double y, int mood, int fullness, int sleep) {
		
		this.x=x;
		this.y=y;
		
		this.mood = mood;
		this.fullness = fullness;
		this.sleep = sleep;
	}
	
	/**
	 * Specifies how to paint pet Images onto the screen.
	 * 
	 * @param g			A Graphics object.
	 * @param behavior	The Behavior object that determines what Images should be painted onto the screen.
	 */
	public void drawSelf(Graphics g, Behavior behavior) {
		
		//if walking, randomly determine direction every 93 frames
		if (behavior instanceof Walk) {
			
			if (!directionfound && walkcount > 92) {
				
				Random random = new Random();
				if (random.nextInt(2) == 0) {
					left = true;
					
				} else {
					left = false;
					
				}
				directionfound = true;
				
			}
			//if left is true, move image left, otherwise, move image to the right
			if (left) {
				if (x == 0) {
					left = false;
				}
				x--;
			} else {
				if (x == 572) {
					left = true;
				}
				x++;
			}
			if (directionfound) {
				walkcount--;
			}
		}
		
		//if behavior is not Beg, adjust height of pet
		if (!(behavior instanceof Beg)){
			y = 320;
			
		}
		
		//if sleeping, boolean sleeping is true, used to let StatTime class alter timer behavior
		if (behavior instanceof Sleep) {
			sleeping = true;
		} else {
			sleeping = false;
		}
		
		//if the Behavior only has an Image, paint that Image
		if (behavior.imgs == null) {
			
			//adjusts height of pet and add hungry emote during Beg behavior
			if (behavior instanceof Beg) {
				if (y == 320) {
					y-=36;
				}
				g.drawImage(hungry, (int)(x)+50, (int)(y)-50, null);
			}
			
			if (left) {
				g.drawImage(behavior.img, (int) x, (int) y, null);
				
				if (behavior instanceof Being_Pet) {
					g.drawImage(heart, (int)(x)-10, (int)(y)-10, null);
				}
			} else {
				//flips image if left is false
				g.drawImage(FlipImage.createFlipped((BufferedImage) behavior.img), (int) x, (int) y, null);
				
				if (behavior instanceof Being_Pet) {
					g.drawImage(heart, (int)(x)+82, (int)(y)-10, null);
				}
			}
			
		} else {
			
			//if the Behavior has a Image[], paint the Images in the Image[] in succession.
			if (count < 30) {
				
					if (left) {
						g.drawImage(behavior.imgs[count],(int)x,(int)y,null);
						count++;
						
						if (behavior instanceof Eat) {
							g.drawImage(food, (int)(x)+108, (int)y+98, null);
						}
					} else {
						//flips images if left is false
						g.drawImage(FlipImage.createFlipped((BufferedImage)behavior.imgs[count]),(int)x,(int)y,null);
						count++;
						
						if (behavior instanceof Eat) {
							g.drawImage(food, (int)(x)+-18, (int)y+98, null);
						}
					}
				
			} else {
				if (left) {
					g.drawImage(behavior.imgs[0],(int)x,(int)y,null);
					
					if (behavior instanceof Eat) {
						g.drawImage(food, (int)(x)+108, (int)y+98, null);
					}
				} else {
					g.drawImage(FlipImage.createFlipped((BufferedImage) behavior.imgs[0]), (int) x, (int) y, null);
					
					if (behavior instanceof Eat) {
						g.drawImage(food, (int)(x)+-18, (int)y+98, null);
					}
				}
				count = 0;
				
				//resets walkcount(frame counter for walking animation)
				if (walkcount < 1 || !(behavior instanceof Walk)) {
					directionfound = false;
					walkcount = 93;
				}
			}
		}
		
		
		}
		
	
}
