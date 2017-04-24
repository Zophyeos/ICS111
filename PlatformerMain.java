import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class PlatformerMain {

	public static void main(String[] args) {

		// init EZ
		EZ.initialize(700, 500);
		//EZ.setBackgroundColor(new Color(50,150,255));
		EZImage backdrop = EZ.addImage("space.jpg", 256, 256);/////////////////////////
		// container that will hold all EZ elements
		EZGroup root = EZ.addGroup();
		
		int scores = 0;
		EZText score = EZ.addText(150, 50, "Score  " + scores, Color.WHITE);
		score.fontSize = 50;
		Random randomGenerator = new Random();
		ArrayList<spriteCoins> coins = new ArrayList<spriteCoins>();
		for (int i = 0; i < 10; i++) {				  //randomGenerator.nextInt(500) //randomGenerator.nextInt(500)
			coins.add(new spriteCoins("coinsprite3.png", randomGenerator.nextInt(500), 317, 44,
					44, 4));
		}
		Random randomPos = new Random();
		/////////////////////////////////////////////////////////////
		
		int width = backdrop.getWidth();
		int height = backdrop.getHeight();
		backdrop.setFocus(0, 0, 900, 700);
		int x = 0;
		int y = 0;
		////////////////////////////////////////////////////////////
		// add elements
		Player me = new Player("zeroTest3.png", 200,320,50,80,3);
		EZImage player = EZ.addImage("chicken.png", 500, 200);
		
		EZRectangle picture1 = EZ.addRectangle(600, 350, 500, 30, Color.WHITE, true);
		EZRectangle picture2 = EZ.addRectangle(1300, 350, 500, 30, Color.WHITE, true);
		EZRectangle picture3 = EZ.addRectangle(2000, 350, 500, 30, Color.WHITE, true);
		EZRectangle picture4 = EZ.addRectangle(2700, 350, 500, 30, Color.WHITE, true);
		
		//EZImage picture3 = EZ.addImage("finger.png", 800, 0);
		//EZImage picture4 = EZ.addImage("asteroid.png", 1000, 200);
		
		// add elements to the root group
		root.addElement(player);
		root.addElement(picture1);
		root.addElement(picture2);
		root.addElement(picture3);
		root.addElement(picture4);
		//root.addElement(picture3);
		//root.addElement(picture4);

		// setup player speed
		float velocityX = 0f;
		float velocityY = 0f;
		float friction = 0.8f;
		
		
		// main loop
		while(true) {

			me.processStates();
			for (int i = 0; i < coins.size(); i++ ) {
				coins.get(i).go();
			}	
			/* move player */
			if(EZInteraction.isKeyDown('a')) {
				velocityX -= 2f;
				x -= 2;
				if (x < 0) 
					x = width / 2;
			}

			if(EZInteraction.isKeyDown('d')) {
				velocityX += 2f;
				x += 2;
				if (x > width / 2)
					x = 0;
			}

			if(EZInteraction.isKeyDown('w')) {
				player.translateBy(0f, -4f);	
				
			}

			if(EZInteraction.isKeyDown('s')) {
				player.translateBy(0f, 4f);	
			}

			backdrop.setFocus(x, y, x + 900, y + 700);
			
			// apply friction
			velocityX *= friction;
			velocityY *= friction;
			// move player
			player.translateBy(velocityX, velocityY);
			
			// NOTE: the other images don't move within the group
	//		System.out.println(picture11.getXCenter());

			// keep the root group following the opposite of the character
			root.translateTo(-player.getXCenter() + 200f, 0);
			
			// update screen
			EZ.refreshScreen();

		}
	}

}
