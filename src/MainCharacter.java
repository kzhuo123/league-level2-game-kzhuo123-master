import java.awt.Color;
import java.awt.Graphics;

public class MainCharacter extends GameObject {
	int speed = 20;
	public int y;

	MainCharacter(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {
		 g.drawImage(GamePanel.MainImg, x, y, width, height, null);

	}

}
