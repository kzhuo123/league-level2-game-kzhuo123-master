import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	MainCharacter bow;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Enemy> aliens = new ArrayList<Enemy>();
	boolean isAlive = true;
	long enemyTimer = 0;
	float enemySpawnTime = 3000;

	ObjectManager(MainCharacter bow) {
		this.bow = bow;
	}

	void draw(Graphics g) {
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
			addEnemy(new Enemy(new Random().nextInt(Game.height), 900, 50, 900));

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
	}

	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);

	}

	void addEnemy(Enemy alien) {
		aliens.add(alien);

	}

	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).x < 100) {
				isAlive = false;
				
			}
		}
	}

	void checkCollision() {
		for (Enemy a : aliens) {

			if (bow.collisionBox.intersects(a.collisionBox)) {
				System.out.println("penis");
				bow.isAlive = false;

			}
	
			}
		
		}
		}
	
