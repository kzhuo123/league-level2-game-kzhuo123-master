import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	MainCharacter bow;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Enemy> aliens = new ArrayList<Enemy>();
	boolean isAlive = true;
	long enemyTimer = 0;
	float enemySpawnTime = 1500;

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
			addEnemy(new Enemy(Game.width, new Random().nextInt(Game.height), 50, 50));

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
		for (int i =aliens.size()-1 ; i >=0; i--) {
			if (aliens.get(i).x < 0) {
				aliens.remove(i);
				bow.isAlive=false;
			}
			else if (aliens.get(i).isAlive==false) {
			aliens.remove(i);
		}
			
		}
		for (int i = projectiles.size()-1; i >= 0; i--) {
			if (projectiles.get(i).x > 1400) {
				projectiles.remove(i);
			}
			else if (projectiles.get(i).isAlive==false) {
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
				}

			}

		}
	}
}
