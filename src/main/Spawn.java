package main;
/*
 * This class handles all the ways enemies spawn in game
 */
import java.util.Random;

import main.BasicEnemy;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int 
	scoreKeep = 0;

	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	;

	

	/*
	 * Handles how enemies are spawned into the game according to certain
	 * circumstances that are met as the game progresses as in levels
	 */
	public void tick() {
		scoreKeep++;
		if (scoreKeep >= 250) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);			
			if (hud.getLevel() == 1) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,
						handler));
			} else if (hud.getLevel() % 3 == 0) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy,
						handler));
			} else if (hud.getLevel() % 4 == 0) {
				handler.addObject(
						new FastEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.FastEnemy, handler));
			} else if (hud.getLevel() % 5 == 0) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.SmartEnemy,
						handler));
			}else if(hud.getLevel() % 10 == 0){
				handler.addObject(new LevelBoss(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.LevelBoss, handler));
			}
		}
	}
}