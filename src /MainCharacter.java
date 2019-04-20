import java.awt.Color;
import java.awt.Graphics;

public class MainCharacter extends GameObject {
	int speed = 8;
	boolean up = false;
	boolean down = false;

	MainCharacter(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		if (up) {
			y+=speed;
		}
		if (down) {
		y-=speed;
		}
	
		super.update();
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.MainImg, x, y, width, height, null);

	}

}
