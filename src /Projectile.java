import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject {
	int speed = 10;
	int frame=0;
	int framecounter=0;
	
	Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	void update() {
		framecounter++;
		if (framecounter%7==0) {
			frame++;
		}
		if (frame>2) {
			frame=0;
		}
		  super.update();
		x += speed;
	}

	void draw(Graphics g) {
		  g.drawImage(GamePanel.bulletImg[frame], x, y, width, height, null);


	}

}
