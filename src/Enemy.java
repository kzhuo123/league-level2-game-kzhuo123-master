import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject{

	Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void draw(Graphics g) {
		g.setColor(Color.YELLOW);

		g.fillRect(x, y, width, height);

	}

	void update() {
		  super.update();
		x-=10;
		
	}
}