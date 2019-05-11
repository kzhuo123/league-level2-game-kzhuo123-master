import java.awt.Dimension;

import javax.swing.JFrame;

public class Game {

	JFrame frame;
	final static int width = 1500;
	final static int height = 1000;
	GamePanel panel;

	public static void main(String[] args) {
		Game run = new Game();
		run.setup();
	}

	Game() {
		frame = new JFrame();
		frame.setVisible(true);
		panel = new GamePanel();
		frame.add(panel);
		frame.addKeyListener(panel);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		
		frame.pack();
	}

	void setup() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
