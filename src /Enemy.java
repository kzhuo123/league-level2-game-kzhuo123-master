import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends GameObject{
	int frame=0;
	int framecounter=0;
	
	Enemy(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void draw(Graphics g) {
		  g.drawImage(GamePanel.bad[frame], x, y, width, height, null);

	}

	void update() {
		framecounter++;
		if (framecounter%7==0) {
			frame++;
		}
		if (frame>3) {
			frame=0;
		}
		
		  super.update();
		x-=10;
		
	}
}