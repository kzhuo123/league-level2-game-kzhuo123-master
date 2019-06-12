import java.awt.Color;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	public static BufferedImage[] bad;
	
	public static BufferedImage[] wizard;

	public static BufferedImage[] bulletImg;

	public static BufferedImage grass;
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	int space=0;

	
	MainCharacter bow = new MainCharacter(250, 750, 200, 200);
	

	Font titleFont;
	Font secondFont;
	Font bigFont;
	ObjectManager object = new ObjectManager(bow);

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 50);
		secondFont = new Font("Arial", Font.PLAIN, 30);
		bigFont = new Font("Arial", Font.PLAIN, 100);
		
		timer = new Timer(1000 / 60, this);
		startGame();
		try {

			wizard = new BufferedImage[6];

			for (int i = 1; i <= wizard.length; i++) {
				
				wizard[i-1] = ImageIO.read(this.getClass().getResourceAsStream("knight"+i+".png"));
			}
			
			bulletImg = new BufferedImage[3];

			for (int i = 1; i <= bulletImg.length; i++) {
				bulletImg[i-1] = ImageIO.read(this.getClass().getResourceAsStream("fireball"+i+".png"));
			}
			
			bad = new BufferedImage[4];
			
			for (int i = 1; i <= bad.length; i++) {
				bad[i-1] = ImageIO.read(this.getClass().getResourceAsStream("bad"+i+".png"));
			}
			
			grass = ImageIO.read(this.getClass().getResourceAsStream("grass.png"));
			

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	void startGame() {
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {

			drawMenuState(g);

		} else if (currentState == GAME_STATE) {

			drawGameState(g);

		} else if (currentState == END_STATE) {

			drawEndState(g);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (currentState == MENU_STATE) {

			updateMenuState();

		} else if (currentState == GAME_STATE) {

			updateGameState();

		} else if (currentState == END_STATE) {

			updateEndState();

		}
		repaint();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("a");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10&&currentState==0) {
			currentState = GAME_STATE;
		}
			if (e.getKeyCode() == 10&&currentState==2) {
				currentState = MENU_STATE;
			
		}
		
		if (e.getKeyCode() == e.VK_UP) {
			bow.down = true;
		}
		if (e.getKeyCode() == e.VK_DOWN) {
			bow.up = true;
		}

		if (e.getKeyCode() == e.VK_SPACE) {
			object.addProjectile(new Projectile((bow.x + 25), bow.y + 20, 50, 50));
			System.out.println("space was pressed");
			bow.isAttacking=true;
			
		}
		if (e.getKeyCode() == e.VK_SPACE &&currentState == GAME_STATE) {
			space++;
		}
		
	}

	void updateMenuState() {

	}

	void updateGameState() {
		object.update();
		object.manageEnemies();
		object.purgeObjects();
		object.checkCollision();
		if (!bow.isAlive) {
			currentState = END_STATE;
		}
	}

	void updateEndState() {

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Game.width, Game.height);
		g.setColor(Color.BLACK);
		g.setFont(bigFont);
		g.drawString("DEFEND GAME", 300, 200);
		g.setFont(bigFont);
		g.drawString("Press ENTER to Start", 200, 500);
		g.setFont(secondFont);
		g.drawString("Use arrow keys to move", 550, 700);
		g.drawString("Use space to attack", 550, 800);
		g.drawString("Don't let monsters get past or touch you", 450, 900);
		
	}

	void drawGameState(Graphics g) {
		object.draw(g);
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("Kills= "+ object.getScore(), 550, 50);
		g.drawString("Attacks used= "+ space, 550, 100);
	}

	void drawEndState(Graphics g) {
	
		g.setColor(Color.RED);

		g.fillRect(0, 0, Game.width, Game.height);
		g.setColor(Color.BLACK);
		g.setFont(bigFont);
		g.drawString("GAME OVER", 400, 100);
		if (object.getScore()==0) {
			g.drawString("Score= 0" ,450, 300);
		}
		else g.drawString("Score= "+ object.getScore()*((float)object.getScore())/space, 450, 300);
	
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == e.VK_UP) {
			bow.down = false;
		}
		if (e.getKeyCode() == e.VK_DOWN) {
			bow.up = false;
		}
	}

}
