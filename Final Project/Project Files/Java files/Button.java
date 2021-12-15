package virtualPet;


import java.awt.Graphics;
import java.awt.Image;


public class Button{

	double x, y;
	int width, height;
	Image img;
	Pet pet;
	
	public Button(double x, double y, Image img, Pet pet) {
		
		this.x = x;
		this.y = y;
		this.img = img;
		width = img.getWidth(null);
		height = img.getHeight(null);
		
		this.pet = pet;
	}
	
	public void drawSelf(Graphics g) {
		
		g.drawImage(img, (int) x, (int) y, null);
	}

}
