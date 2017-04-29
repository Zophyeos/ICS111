import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PlatformerMain
{

	public static void main(String[] args) throws java.io.IOException
	{
		// init EZ
		EZ.initialize(950, 460);
		// EZ.setBackgroundColor(new Color(50,150,255));
		//EZImage backdrop = EZ.addImage("space.jpg", 256, 256);/////////////////////////
		// container that will hold all EZ elements
		EZGroup root = EZ.addGroup();

		////////////////////////////////
		Scanner fileScanner = new Scanner(new FileReader("level.txt"));
		FileWriter fw = new FileWriter("cleanlevel.txt");

		int width = fileScanner.nextInt();
		int height = fileScanner.nextInt();
		
		fw.write(width);
		fw.write(height);
		String inputText = fileScanner.nextLine();
		EZImage back = EZ.addImage("Back2.png", width * 7, height);
		ArrayList<EZImage> tiles = new ArrayList<EZImage>();
		////////////////////////////////

		int scores = 0;
		EZText score = EZ.addText(150, 50, "Score  " + scores, Color.WHITE);
		score.fontSize = 50;
		Random randomGenerator = new Random();
		ArrayList<spriteCoins> coins = new ArrayList<spriteCoins>();
	
		Random randomPos = new Random();
		/////////////////////////////////////////////////////////////

		ArrayList<Enemy1> Enemy= new ArrayList<Enemy1>();
	//	for (int i = 0; i < 10; i++){
	//		Enemy.add(new Enemy1("Danny.png",100, 100));
	//	}
	
		int widthPic = back.getWidth();
		int heightPic = back.getHeight();
		
		//backdrop.setFocus(0, 0, 900, 700);
		int x = 0;
		int y = 0;
		////////////////////////////////////////////////////////////
		// add elements
	//	EZImage coinsTest = EZ.addImage("zeroJump.png",100,100);
	//	int coinX = coinsTest.getXCenter();
		Player Zero = new Player("zeroSheet.png", 50, 385, 50, 80, 3);
		EZImage player = EZ.addImage("chicken.png", 500, 200);
		player.hide();

		EZImage pipe[] = new EZImage[6];
		EZImage block[] = new EZImage[6];

		// add elements to the root group
		root.addElement(player);
		
		// setup player speed
		float velocityX = 0f;
		float velocityY = 0f;
		float friction = 0.8f;
		
		for (int line = 0; line < height; line++)
		{
			
			inputText = fileScanner.nextLine();
			System.out.println(inputText);
			
			for (int i = 0; i < inputText.length(); i++)
			{

				char ch = inputText.charAt(i);
				
				switch (ch)
				{

				// Square tiles
				case 'P':
					
						Enemy.add(new Enemy1("Danny.png", 100, 370));
					
					Enemy.add(new Enemy1("Danny.png",i * 32, i * 32));
					//tiles.add(EZ.addImage("Danny.png", i * 32, line * 32 - 15));
					break;
				case 'C':
					coins.add(new spriteCoins("coinsprite3.png", i * 32, line * 32, 44, 44, 4));
					break;
				case 'L':
					tiles.add(EZ.addImage("LGrass.png", i * 32, line * 32));
					break;
				case 'M':
					tiles.add(EZ.addImage("MGrass.png", i * 32, line * 32));
					break;
				case 'R':
					tiles.add(EZ.addImage("RGrass.png", i * 32, line * 32));
					break;
				case 'D':
					tiles.add(EZ.addImage("Mdirt.png", i * 32, line * 32));
					break;
				case 'Y':
					tiles.add(EZ.addImage("Rdirt.png", i * 32, line * 32));
					break;
				case 'F':
					tiles.add(EZ.addImage("Ldirt.png", i * 32, line * 32));
					break;
				// Angle tiles
				case 'H':
					tiles.add(EZ.addImage("3.png", i * 32, line * 32));
					break;
				case 'J':
					tiles.add(EZ.addImage("4.png", i * 32, line * 32));
					break;
				case 'K':
					tiles.add(EZ.addImage("boto3.png", i * 32, line * 32));
					break;
				case 'Q':
					tiles.add(EZ.addImage("boto2.png", i * 32, line * 32));
					break;
				case 'W':
					tiles.add(EZ.addImage("boto5.png", i * 32, line * 32));
					break;
				case 'E':
					tiles.add(EZ.addImage("boto6.png", i * 32, line * 32));
					break;
				case 'T':
					tiles.add(EZ.addImage("boto9.png", i * 32, line * 32));
					break;
				default:
					// Do nothing
					break;
					
				}
				
			}

		}
		
		for (int i = 0; i < tiles.size(); i++)
		{
		//	if (Zero.isPointInElement(coins.get(i))) {
				
		//	}
			root.addElement(tiles.get(i));
			
			root.addElement(coins.get(0).spriteSheet);

		//	root.addElement(Enemy.get(i));
		//	root.addElement(coins.get(i));
		}
		for (int i = 0; i < coins.size(); i++ ) {
	//		if (!(coins.isPointInElement(tiles.get(i).getXCenter()))) {
				
				root.addElement(coins.get(i).spriteSheet);
				
	//		}
		}
		for (int i = 0; i < Enemy.size(); i ++) {
			root.addElement(Enemy.get(i).DannyPict);
		}
		// GROUP PULL
		root.pullToFront();
		Zero.spriteSheet.pullToFront();
		Zero.zeroJumpR.pullToFront();
		Zero.zeroLand.pullToFront();
		
		// main loop
		while (true)
		{

			Zero.processStates();
			for (int i = 0; i < coins.size(); i++)
			{
				coins.get(i).go();
				
			}
			for (int i = 0; i < Enemy.size(); i++){
				 Enemy.get(i).Move();
			 }

			// if (coins.)

			/* move player */
			if (EZInteraction.isKeyDown('a'))
			{
				velocityX -= 2f;
				x -= 2;
		//		coinX -= 2;
				if (x < 0)
					x = widthPic / 2;
			}

			if (EZInteraction.isKeyDown('d'))
			{
				velocityX += 2f;
				x += 2;
		//		coinX += 2;
				if (x > widthPic / 2)
					x = 0;
			}

			if (EZInteraction.isKeyDown('w'))
			{
				player.translateBy(0f, -4f);

			}

			if (EZInteraction.isKeyDown('s'))
			{
				player.translateBy(0f, 4f);
			}

			back.setFocus(x, y, x + 1000, y + 1100);

			// apply friction
			velocityX *= friction;
			velocityY *= friction;
			// move player
			player.translateBy(velocityX, velocityY);

			// NOTE: the other images don't move within the group
			// System.out.println(picture11.getXCenter());

			// keep the root group following the opposite of the character
			root.translateTo(-player.getXCenter() + 200f, 0);

			// update screen
			EZ.refreshScreen();
			EZ.setFrameRate(60);

		}
	}

}
