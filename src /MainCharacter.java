import java.awt.Color;
import java.awt.Graphics;

public class MainCharacter extends GameObject {
	int speed = 9;
	boolean up = false;
	boolean down = false;
	int frame=0;
	int framecounter=0;
	boolean isAttacking=false;

	MainCharacter(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	void update() {
		framecounter++;
		if (framecounter%3==0&&isAttacking) {
			frame++;
		}
		if (frame>5) {
			frame=0;
			isAttacking=false;
		}
		if (up) {
			y+=speed;
		}
		if (down) {
		y-=speed;
		}
	
		super.update();
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.wizard[frame], x, y, width, height, null);

	}

}
