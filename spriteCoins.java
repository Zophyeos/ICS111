import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;


public class spriteCoins {

	EZImage spriteSheet;

	int x = 0; // Position of Sprite
	int y = 0;
	int spriteWidth; // Width of each sprite
	int spriteHeight; // Height of each sprite
	int direction = 0; // Direction character is walking in
	int walkSequence = 0; // Walk sequence counter
	int cycleSteps; // Number of steps before cycling to next animation step
	int counter = 0; // Cycle counter

	
	spriteCoins(String imgFile, int startX, int startY, int width, int height, int steps) {
		x = startX;
		y = startY;
		spriteWidth = width;
		spriteHeight = height;
		cycleSteps = steps;
		spriteSheet = EZ.addImage(imgFile, x, y);
		setImagePosition();
	}

	private void setImagePosition() {
		spriteSheet.translateTo(x, y);

		spriteSheet.setFocus(walkSequence * spriteWidth, direction, walkSequence * spriteWidth + spriteWidth,
				direction + spriteHeight);
	}

	public void moveRight(int stepSize) {
		//x = x + stepSize;
		direction = spriteHeight *0;

		if ((counter % cycleSteps) == 0) {
			walkSequence++;
			if (walkSequence > 9)
				walkSequence = 0;
		}
		
		counter++;
		setImagePosition();
		
		//counter++;
	}

	public void go() {

		moveRight(2);

	}

}
