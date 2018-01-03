package main;

/*
 * Course: CSC2620
 * Project Description: A game that revolves around the player
 * avoiding enemies for as along as possible. Trying to claim
 * the highest score possible which will then be saved into a file
 * ordered from high to low upon death
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1550691097823471818L;
	// Sets the width of window
	public static final int WIDTH = 1080;
	// sets the height of window
	public static final int HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	// flag to check if the game is in play
	private boolean running = false;

	public static boolean paused = false;

	//All Class instances that the game will use 
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Help help;
	private Pause pause;
	private Store store;

	/*
	 * Enumerations of tghe different possible gameStates
	 * that allowd for easier transition
	 */
	public enum GAMESTATE {
		Menu, Game, GameOver, LeaderBoard, Pause, Help, Store
	};

	// Initializes the starting state
	public static GAMESTATE state = GAMESTATE.Menu;

	public static BufferedImage spriteSheet;

	/*
	 * Handles the game creation according the set state
	 */
	public Game() {
		handler = new Handler();

		hud = new HUD();
		menu = new Menu(this, handler, hud);
		help = new Help(this, handler);
		store = new Store(handler, hud);
		
		//Adds all the listeners into the game
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(help);
		this.addMouseListener(store);

		new Window(WIDTH, HEIGHT, "Elves Attack.", this);
		
		//Loads the spriteSheet that will be used
		BufferedImageLoader loader = new BufferedImageLoader();
		spriteSheet = loader.loadImage("/FinalSpriteSheet2.png");

		r = new Random();

		spawner = new Spawn(handler, hud);
		if (state == GAMESTATE.Game) {
			handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
		}
	}

	/*
	 * Method that begins the game thread and sets the running boolean to true
	 * running in parallel with the stop method
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	/*
	 * method that stops the game running in parallel to the start method
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Main game loop that caps frames/systems to 60 fps Accessed by the thread
	 * in the start() method
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	/*
	 * Updates the game UI according to what is viewed on screen
	 */
	private void tick() {

		if (state == GAMESTATE.Game) {
			if (!paused) {
				
				hud.tick();
				spawner.tick();
				handler.tick();
				
				if (HUD.HEALTH <= 0) {
					HUD.HEALTH = 100;
					handler.clearEnemies();
					state = GAMESTATE.GameOver;
				}
			}
		} else if (state == GAMESTATE.Menu || state == GAMESTATE.Help || state == GAMESTATE.GameOver) {
			handler.tick();
			menu.tick();

		}
	}

	/*
	 * Renders the game UI according to its current state triple render to make
	 * sure all objects render in time without overlapping/ creating faults
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(4);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		if (paused) {
			g.setColor(Color.white);;
			g.drawString("PAUSED", WIDTH / 2, HEIGHT / 2);
		}
		// loads the game UI
		if (state == GAMESTATE.Game) {
			hud.render(g);
			handler.render(g);
		}
		// loads the menu UI
		else if (state == GAMESTATE.Menu || state == GAMESTATE.Help || state == GAMESTATE.GameOver) {
			menu.render(g);
			// handler.render(g);
		} else if (state == GAMESTATE.Pause) {
			pause.render(g);
		} else if (state == GAMESTATE.Store) {	
			store.render(g);
		}
		g.dispose();
		bs.show();

	}

	/*
	 * Sets limits to certain values on objects ie health bar depreciation, wont
	 * go past the set size/bottom of it
	 */
	public static float clamp(float var, float min, float max) {
		if (var >= max) {
			return var = max;
		} else if (var < min) {
			return var = min;
		} else
			return var;
	}

	// Main that runs the game
	public static void main(String args[]) {
		new Game();
	}
}