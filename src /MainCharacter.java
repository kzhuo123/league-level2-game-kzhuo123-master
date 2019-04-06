import java.awt.Color;
import java.awt.Graphics;

public class MainCharacter extends GameObject {
	int speed = 10;
	public int y;

	MainCharacter(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {
		g.setColor(Color.BLUE);

		g.fillRect(x, y, width, height);

	}

}
