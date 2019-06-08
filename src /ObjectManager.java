import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	MainCharacter bow;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Enemy> aliens = new ArrayList<Enemy>();
	boolean isAlive = true;
	long enemyTimer = 0;
	float enemySpawnTime = 1250;
	int score = 0;
	long startTime = System.currentTimeMillis();
	long timeSinceStart;

	public int getScore() {
		return this.score;
	}

	ObjectManager(MainCharacter bow) {
		this.bow = bow;
	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.grass, 0, 0, 1500, 1000, null);
		bow.draw(g);
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).draw(g);
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}

	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			addEnemy(new Enemy(Game.width, new Random().nextInt(840), 160, 160));

			enemyTimer = System.currentTimeMillis();
		}
	}

	void update() {
		bow.update();
		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
		}

		timeSinceStart = System.currentTimeMillis() - startTime;
		enemySpawnTime= 1250-(timeSinceStart/300);
	}

	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);

	}

	void addEnemy(Enemy alien) {
		aliens.add(alien);

	}

	void purgeObjects() {
		for (int i = aliens.size() - 1; i >= 0; i--) {
			if (aliens.get(i).x < 0) {
				aliens.remove(i);
				bow.isAlive = false;
			} else if (aliens.get(i).isAlive == false) {
				aliens.remove(i);
			}

		}
		for (int i = projectiles.size() - 1; i >= 0; i--) {
			if (projectiles.get(i).x > 1500) {
				projectiles.remove(i);
			} else if (projectiles.get(i).isAlive == false) {
				projectiles.remove(i);
			}
		}

	}

	void checkCollision() {
		for (Enemy a : aliens) {

			if (bow.collisionBox.intersects(a.collisionBox)) {

				bow.isAlive = false;

			}

		}
		for (Enemy a : aliens) {
			for (Projectile projectiles : projectiles) {
				if (projectiles.collisionBox.intersects(a.collisionBox)) {
					projectiles.isAlive = false;
					a.isAlive = false;
					score++;
				}

			}

		}
	}
}
