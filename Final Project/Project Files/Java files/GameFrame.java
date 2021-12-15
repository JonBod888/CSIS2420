package virtualPet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Constructs the Frame window and manages the other classes. Extends Frame and implements MouseListener.
 * 
 * @author Jonathan Bodily
 *
 */
@SuppressWarnings("serial")
public class GameFrame extends Frame implements MouseListener{
	
	BehaviorLinkedList bll = new BehaviorLinkedList();
	BehaviorTree bt = new BehaviorTree();
	Random random = new Random();
	
	//background image
	Image bg = GameUtil.getImage("bg.jpg");
	
	Pet pet = new Pet(300.0, 320.0, 51, 51, 51);
	
	//reactionary behaviors, only in response to player action
	Behavior eat = new Eat();
	Behavior being_pet = new Being_Pet();
	boolean eatTrue = false;
	boolean petTrue = false;
	
	//buttons
	Button btnPet = new Button(600, 45, GameUtil.getImage("Button_Pet.png"), pet);
	Button btnFeed = new Button(600, 95, GameUtil.getImage("Button_Feed.png"), pet);
	Button btnSave = new Button(600, 145, GameUtil.getImage("Button_Save.png"), pet);
	
	//contains the winning node to determine pet's next move
	BehaviorTreeNode nextAction;
	
	//used for timing the next action
	int count = 93;
	
	//used to avoid nullpointerexceptions in the first iteration
	boolean firstTree = true;
	boolean firstTry = true;
	
	//creates a Timer object that will be used to lower pet's stats every 5 seconds
	Timer timer = new Timer();
	TimerTask statTime = new StatTime(pet);
	
	
/**
 * Paints Images on Frame
*/
	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.drawImage(bg, 0, 0, null);
		
		playGame(g, c);
	}

	/**
	 * Paints pet images and displays pet stats on the screen. Also determines wait time between pet behaviors.
	 * 
	 * @param g		A Graphics object
	 * @param c		A Color object, used to determine the colors each image will be painted with.
	 */
	private void playGame(Graphics g, Color c) {
		
		if (firstTry) {
			
		} else {
			//paint being_pet behavior if Pet button was clicked
			if (petTrue) {
				pet.drawSelf(g, being_pet);
				if (count < 1) {
					petTrue = false;
				}
			//paint eat behavior if Eat button was clicked
			} else if (eatTrue){
				pet.drawSelf(g, eat);
				if (count < 1) {
					eatTrue = false;
				}
			//if sleep is less than 13, paint sleep action
			} else if (pet.sleep < 13){
				pet.drawSelf(g, bll.search(1));
				count+=10;
			//otherwise, paint next action	
			} else {
				//paints next pet behavior
				pet.drawSelf(g, nextAction.behavior);
			}
		}

		//paints pet stats
		g.setColor(Color.getHSBColor(.025f, .286f, 1f));
		Font font = new Font("Monospaced", Font.BOLD, 30);
		g.setFont(font);
		g.drawString("Mood: " + pet.mood, 30, 80);
		g.drawString("Fullness: " + pet.fullness, 30, 130);
		g.drawString("Sleep: " + pet.sleep, 30, 180);	
		
		//paints buttons
		btnPet.drawSelf(g);
		btnFeed.drawSelf(g);
		btnSave.drawSelf(g);

		//resets color
		g.setColor(c);
		
		if (firstTry) {
			firstTry = false;
		} else {
			//waits 93 frames before deciding on next action
			if (count > 0) {
				count--;
			} else {
				fillBehaviorTree();
				count = 93;
			}
		}
	}

	/**
	 * Paint thread for Graphics painting
	 *
	 */
	class PaintThread extends Thread {
		@Override
		public void run() {
			while (true) {
				repaint(); // repaint window
				try {
					Thread.sleep(40);// fps 1s=1000ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}		
	
	/**
	 * Sets up the parameters for the Frame window. Populates the BehaviorLinkedList and BehaviorTree for the first time.
	 * Starts the Timer object to change pet stats. Sets up active repaint window.
	 */
	public void launchFrame() {
		
		//set icon
		setIconImage(GameUtil.getImage("Stand.png"));
		// title
		setTitle("Virtual Pet");
		// set to visible to true
		setVisible(true);
		// set size
		setSize(700, 500);
		// set location;
		setLocation(300, 100);
		// disable maximize button
		setResizable(false);
		//make Behavior list
		makeBehaviorList();
		
		//fill tree
		fillBehaviorTree();
		
		addMouseListener(this);
		
		//start timer
		timer.schedule(statTime, 0, 5000);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
			new PaintThread().start();// active repaint window
			
		

	}

	/**
	 * Fixes flicker for Frame window
	 */
	private Image offScreenImage = null;

	public void update(Graphics g) {
		if (offScreenImage == null)
			offScreenImage = this.createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);// width and height

		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	/**
	 * Populates BehaviorLinkedList with Behaviors. Walk and Stand behaviors have a permanent score of 30.
	 */
	public void makeBehaviorList() {
		
		bll.append(0, new Walk(30));
		bll.append(1, new Sleep(0));
		bll.append(2, new ActCute(0));
		bll.append(3, new Beg(0));
		bll.append(4, new Sad(100));
		bll.append(5, new Stand(30));
		
	}
	
	/**
	 * Adjusts scores for Sleep, ActCute, Beg, and Sad behaviors based on current pet stats. Populates BehaviorTree with
	 * random behaviors and determines the next action based on behavior scores.
	 */
	public void fillBehaviorTree() {

		//Sleep is max sleep - current sleep stat
		//Beg is max fullness - current fullness
		//if mood is greater than 40, ActCute is current mood divided by 3 and Sad is not included
		//otherwise, ActCute is 1 and Sad is 100
		bll.search(1).score = (100 - pet.sleep);
		bll.search(3).score = (100 - pet.fullness);
		
		if (pet.mood > 40) {
			bll.search(2).score = pet.mood/3;
			
		} else {
			bll.search(2).score = 1;
			
		}
		
		if (firstTree) {
			
			//first iteration guarantees that Stand is first action
			Behavior stand = bll.search(5);
			
			bt.insert(bll.search(random.nextInt(6)));
			
			bt.insert(stand, stand, stand);
			
			for (int i = 0; i < 3; i++) {
				bt.insert(bll.search(random.nextInt(6)), bll.search(random.nextInt(6)), bll.search(random.nextInt(6)));
			}
			
			firstTree = false;
		
		} else {
			
			Behavior bh1 = bll.search(random.nextInt(6));
			Behavior bh2 = bll.search(random.nextInt(6));
			Behavior bh3 = bll.search(random.nextInt(6));
			
			Behavior sad = bll.search(4);
			Behavior beg = bll.search(3);
			
			//if mood is greater than 40 and fullness is greater than 50, don't include sad or beg behaviors
			if (pet.mood > 40 && pet.fullness > 50) {
				
				while (bh1 == sad || bh2 == sad || bh3 == sad || bh1 == beg || bh2 == beg || bh3 == beg) {
					
					bh1 = bll.search(random.nextInt(6));
					bh2 = bll.search(random.nextInt(6));
					bh3 = bll.search(random.nextInt(6));
				}
			} 
			
			//if just sad shouldn't be included
			if (pet.mood > 40) {
				
				while (bh1 == sad || bh2 == sad || bh3 == sad) {
					
					bh1 = bll.search(random.nextInt(6));
					bh2 = bll.search(random.nextInt(6));
					bh3 = bll.search(random.nextInt(6));
				}
			}
			
			//if just beg shouldn't be included
			if (pet.fullness > 50) {
				
				while (bh1 == beg || bh2 == beg || bh3 == beg) {
					
					bh1 = bll.search(random.nextInt(6));
					bh2 = bll.search(random.nextInt(6));
					bh3 = bll.search(random.nextInt(6));
				}
			}
			
			//fills tree with random Behaviors
			for (int i = 0; i < 4; i++) {
				bt.insert(bh1, bh2, bh3);
			}
			
		}
		
		//determines branch with highest score
		nextAction = bt.findBestAction();
		
		//empties tree and places winning action in the root
		bt.replaceRoot(nextAction);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		//when "Pet" button is clicked, improve mood and set petTrue to true, causing the Being_Pet behavior to occur. 
		if ((e.getX() >= (int)btnPet.x && e.getX() <= ((int)btnPet.x + btnPet.width)) && (e.getY() >= (int)btnPet.y && e.getY() <= ((int)btnPet.y + btnPet.height))) {
		
			pet.mood+= 10;		
			petTrue = true;		
			eatTrue = false;
			count = 31;
			
			if (pet.mood > 100 ) {
				
				pet.mood = 100;
			}
		}
		
		//when "Feed" button is clicked, raise fullness and set eatTrue to true, causing the Eat behavior to occur.
		if ((e.getX() >= (int)btnFeed.x && e.getX() <= ((int)btnFeed.x + btnFeed.width)) && (e.getY() >= (int)btnFeed.y && e.getY() <= ((int)btnFeed.y + btnFeed.height))) {
			
			pet.fullness+= 10;
			eatTrue = true;
			petTrue = false;
			count = 62;
			
			if (pet.fullness > 100) {
				
				pet.fullness = 100;
			}
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
