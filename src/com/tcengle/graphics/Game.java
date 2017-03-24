package com.tcengle.graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.tcengle.entities.Enemy;
import com.tcengle.entities.Player;
import com.tcengle.entities.TestBlock;
import com.tcengle.game.Controller;
import com.tcengle.game.GameObject;
import com.tcengle.game.ObjectId;
import com.tcengle.input.InputHandler;
import com.tcengle.input.Loader;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private final String TITLE = "UnReality";
	private Thread thread;
	private final int WIDTH = 1024;
	private final int HEIGHT = WIDTH / 9 * 16;
	private final int SCALE = 1;
	private final int MAX_FRAMES = 90;
	private double NS_PER_TICK = 1000000000 / MAX_FRAMES;
	private int ticks;
	private int tickCount;
	private int fps;
	private long elapsedTime;
	private BufferedImage image;
	private int[] pixels;
	private JFrame frame = new JFrame(TITLE);
	private BufferStrategy bs;
	private InputHandler input = new InputHandler(this);
	private BufferedImage spriteSheet;
	private BufferedImage xionSheet;
	private Controller control;;
	private Textures tex;


	public Game() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setVisible(true);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	}

	// load resources
	public void init() {
		control = new Controller();
		Loader loader = new Loader();

		try {
			spriteSheet = loader.ImageLoader("/Test_Sprite_Sheet_Full.png");
			xionSheet = loader.ImageLoader("/Sprite draft emilia neutral.png");
		} catch (IOException e) {
			e.printStackTrace();

		}
		tex = new Textures(this);

		Player p = new Player(this, 600, 500,28, 128, xionSheet, input,ObjectId.PLAYER);
		Enemy shroom = new Enemy(this, 100, 100, spriteSheet,ObjectId.ENEMY);
		control.addGameObject(p);
		control.addGameObject(shroom);
		control.addGameObject(new TestBlock(500,500,ObjectId.WALL));
		
	}

	// starts game loop thread
	public synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();

	}

	// stops game loop thread
	public synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	// game loop
	public void run() {
		// TODO seperate logic from drawing
		// TODO Fix, is Broken
		init();
		this.requestFocus();
		long next = 0;
		long now = 0;

		int MAX_FRAMESKIP = 0;
		long timer = System.currentTimeMillis();
		int loops = 0;
		next = System.nanoTime();
		while (running) {
			// elapsed = timers.getTickCount();
			// only reset if frames skipped
			if (loops >= MAX_FRAMESKIP) {
				loops = 0;
			}

			now = System.nanoTime();

			// System.out.print("NOW = " + now);
			// System.out.println("NEXT" + next);

			// update only if time and no skips
			while (now >= next && loops <= MAX_FRAMESKIP) {
				tick();
				ticks++;
				next += NS_PER_TICK;
				loops++;

			}
			// render only if time
			if (next >= now) {
				render();
				fps++;
			}
			// display by second updates
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("Frames" + fps + " Ticks" + ticks);
				fps = 0;
				ticks = 0;
			}
		}

	}

	//
	// displays images to screen
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		control.render(g);
		g.dispose();
		bs.show();
	}

	private void tick() {
		tickCount++;
		control.tick();

	}

	// gets a sprite sheet
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	// starts the game
	public static void main(String[] args) {
		new Game().start();
	}

	// returns the tick
	public int getTick() {
		return ticks;
	}
	public Controller getControl(){
		return control;
	}

}
