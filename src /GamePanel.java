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
	 public static BufferedImage alienImg;

     public static BufferedImage MainImg;

     public static BufferedImage bulletImg;

     public static BufferedImage spaceImg;
	Timer timer;
	final int MENU_STATE = 0;
	final int GAME_STATE = 1;
	final int END_STATE = 2;
	int currentState = MENU_STATE;
	MainCharacter bow = new MainCharacter(250, 750, 100, 100);
	
	Font titleFont;
	Font secondFont;
	ObjectManager object = new ObjectManager(bow);

	GamePanel() {
		titleFont = new Font("Arial", Font.PLAIN, 50);
		secondFont = new Font("Arial", Font.PLAIN, 30);

		timer = new Timer(1000 / 60, this);
		startGame();
		 try {

            

             MainImg = ImageIO.read(this.getClass().getResourceAsStream("archer.png"));

             bulletImg = ImageIO.read(this.getClass().getResourceAsStream("arrow.png"));

          

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
		if (e.getKeyCode() == 10) {
			currentState += 1;
			if (currentState > END_STATE) {

				currentState = MENU_STATE;

			}
		}
		if (e.getKeyCode() == e.VK_UP) {
			bow.down=true;
		}
		if (e.getKeyCode() == e.VK_DOWN) {
			bow.up=true;
		}

		if (e.getKeyCode() == e.VK_SPACE) {
			object.addProjectile(new Projectile((bow.x + 25), bow.y, 50, 50));
			System.out.println("space was pressed");

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
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("GAME", 10, 100);
		g.setFont(secondFont);
		g.drawString("Press ENTER to Start", 500, 300);
		
	}

	void drawGameState(Graphics g) {
		object.draw(g);
	}

	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 100, 100);
		g.setFont(secondFont);
		g.drawString("Press ENTER to Restart", 100, 300);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == e.VK_UP) {
			bow.down=false;
		}
		if (e.getKeyCode() == e.VK_DOWN) {
			bow.up=false;
		}
	}

}
