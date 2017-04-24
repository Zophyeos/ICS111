import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class MainFunction {

	public static void main(String[] args) {
		
		EZ.initialize(1000, 720);
		EZ.setBackgroundColor(Color.BLACK);
		
		EZSound coinSound = EZ.addSound("coinsounds.wav");
		int scores=0;
		EZText score = EZ.addText(150, 50, "Score  " + scores, Color.BLACK);
		score.fontSize = 50;
		
		//EZImage backdrop = EZ.addImage("space.jpg", 256, 256);	//Background image
		Player me = new Player("zeroTest3.png", 500,500,50,80,3);	//Player image, control player
		
		
		// initialize the random number generator
		Random randomGenerator = new Random();
		ArrayList<spriteCoins> coins = new ArrayList<spriteCoins>();
		for (int i = 0; i < 10; i++) {

			coins.add(new spriteCoins("coinsprite3.png", randomGenerator.nextInt(500), randomGenerator.nextInt(500), 44,
					44, 4));	

		}

		Random randomPos = new Random();
		
		while (true) {
			
			me.processStates();
			
			for (int i = 0; i < coins.size(); i++) {

				//makes it spin
				coins.get(i).go();
				
/*				
				if (myPlayer.heropicture.isPointInElement(coins.get(i), coins.get(i))) {

					// box will be somewhere else
					coins.add(i,randomPos.nextInt(3000)+3000, randomPos.nextInt(3000)+3000);

					// increment the score by 1
					scores++;

					// show the score on the board
					score.msg = "Score" + scores;

					// play a box sound when the play touches the replenish box
					coinSound.play();
				}
*/				
			}

			EZ.refreshScreen();

		}
	}

}
