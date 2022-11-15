package gustavoaguilar.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

public class SPMain extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 350;
	public static final int HEIGHT = 450;
	public final String TITLE = "Super Pong";
	public static int SCORE = 0;
	
	public int Counter = 0;
	public int CounterPW = 0;
	public int CounterSpeed = 0;
	
	private boolean running = false;
	private Thread thread;
	
	public enum STATE{
		MENU,
		GAME,
		CREDITS,
		GAMEOVER;
	};
	
	public int SelectMenu;
	public int SelectCredits;
	
	public STATE State = STATE.MENU;
	
	Random rand = new Random();
	
	Player player;
	Ball ball;
	KeyBoard kb;
	
	DoublePoints dp;
		public boolean DPEnabled;
	DoubleSize ds;	
		public boolean DSEnabled;
	Points10 p10;
	SlowDown sd;
		public boolean SDEnabled;
	
	Font font1 = new Font("Arial" ,Font.LAYOUT_LEFT_TO_RIGHT ,18);
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	public void init(){
		long a = Runtime.getRuntime().totalMemory();
		System.out.println(a);
		
		requestFocus();
		kb = new KeyBoard(this,player);
		addKeyListener(kb);
		player = new Player(this,kb);
		player.setWidth(50);
		
		ball = new Ball(this);
		
		dp = new DoublePoints(this);
		ds = new DoubleSize(this);
		p10 = new Points10(this);
		sd = new SlowDown(this);
				
	}
	
	private synchronized void start(){
		if(running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop(){
		if(!running){
			return;
		}
		running = false;
		try{
			thread.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run(){
		init();
		long lastTime = System.nanoTime();
		final double amountOfUpdates = 60;
		double ns = 1000000000 / amountOfUpdates;
		double delta = 0;
		
		int updates = 0;
		int frames = 0;
		
		long timer = System.currentTimeMillis();
		
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1){
				update();
				updates++;
				delta--;
				
				render();
				frames++;
				
			}

			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("Updates: " + updates + "   " + "Frames: " + frames + "   " +kb.up);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void update(){
		
		//SelectCredits
		if(State == STATE.MENU){
			if(SelectMenu<=0)SelectMenu = 1;
			if(SelectMenu>=3)SelectMenu = 3;
		}
		
		if(State == STATE.CREDITS){
			if(SelectCredits<=0)SelectCredits = 1;
			if(SelectCredits>=2)SelectCredits = 2;
		}
		
		//System.out.println(CounterSpeed + "    " + ball.speed);
		if(Counter >= 0 ){
			Counter--;
		}else{
			DPEnabled = false;
			DSEnabled = false;
			SDEnabled = false;
			ball.speed = ball.lastspeed;
		}
		if(CounterPW >= 0){
			CounterPW--;
		}else{
			int PWSel = rand.nextInt(5);
			if(PWSel == 0)p10.activate();
			if(PWSel == 1)dp.activate();
			if(PWSel == 2)ds.activate();
			if(PWSel == 3)sd.activate();
			if(PWSel == 4);
			
			CounterPW = 1800;
		}
		
		if(CounterSpeed >= 5 && ball.speed <= 12 && SDEnabled == false){
			ball.speed += 1;
			ball.lastspeed = ball.speed;
			CounterSpeed = 0;
		}
		
		if(State == STATE.GAME){
		player.update();
		ball.update();
		
		if(ds.isEnabled())ds.update();
		if(dp.isEnabled())dp.update();
		if(p10.isEnabled())p10.update();
		if(sd.isEnabled())sd.update();
		
		//player.setX(ball.getX());
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
			if(bs == null){
				createBufferStrategy(2);
				return;
			}
			
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		if(State == STATE.MENU){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial" , Font.LAYOUT_LEFT_TO_RIGHT , 25));
			
			if(SelectMenu == 1)g.setColor(Color.YELLOW);
			g.drawString("Start", 150, 170);
			g.setColor(Color.WHITE);
			if(SelectMenu == 2)g.setColor(Color.YELLOW);
			g.drawString("Credits", 140, 220);
			g.setColor(Color.WHITE);
			if(SelectMenu == 3)g.setColor(Color.YELLOW);
			g.drawString("Exit", 155, 270);
			g.setColor(Color.WHITE);
		}
		
		if(State == STATE.GAME){
		
		player.render(g);
		ball.render(g);
		
		if(dp.isEnabled())dp.render(g);
		if(ds.isEnabled())ds.render(g);
		if(p10.isEnabled())p10.render(g);
		if(sd.isEnabled())sd.render(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 436, 360, 436);
		g.setColor(Color.WHITE);
		g.drawLine(0, 435, 360, 435);
		g.setFont(font1);
		g.drawString("Score: " + SCORE, 0, 455);
		g.fillRect(120, HEIGHT - 10, Counter / 6 , HEIGHT - 20);
		g.drawRect(120, HEIGHT - 10, 100 , HEIGHT - 20);
		g.setFont(new Font("Arial" , Font.LAYOUT_LEFT_TO_RIGHT , 12));
		g.drawString("Press 'Esc' to back", 250, HEIGHT + 3);
		
		}
		
		if(State == STATE.CREDITS){
			g.setColor(Color.WHITE);
			
			if(SelectCredits == 1)g.setColor(Color.YELLOW);
			g.setFont(new Font("Arial" , Font.LAYOUT_LEFT_TO_RIGHT , 30));
			g.drawString("Created By:", 100, 30);
			g.drawString("OwlyBR", 120, 70);
			g.setFont(new Font("Arial" , Font.LAYOUT_LEFT_TO_RIGHT , 20));
			g.drawString("I hope you Enjoyed", 100, 150);
			g.drawString("Thanks :)", 145, 175);
			g.setColor(Color.WHITE);
			
			if(SelectCredits == 2)g.setColor(Color.YELLOW);
			g.drawString("Back", 155, 420);
		}
		
		if(State == STATE.GAMEOVER){
			g.setFont(new Font("Arial" , Font.LAYOUT_LEFT_TO_RIGHT , 40));
			g.setColor(Color.WHITE);
			g.drawString("Game Over !",70,100);
			g.setFont(new Font("Arial" , Font.LAYOUT_LEFT_TO_RIGHT , 25));
			g.drawString("Your Score : " + String.valueOf(SCORE),75,150);
			g.setColor(Color.YELLOW);
			g.drawString("Press 'Space' to continue...",30,430);
			
		}
		
		
		g.dispose();
		bs.show();
	}
	
	public void SlowDown(){
		ball.lastspeed = ball.speed;
		ball.speed = ball.lastspeed/2;
	}
	public void SlowDownOFF(){
		ball.speed = ball.lastspeed;
	}
	
	public static void main(String[] args) {
		SPMain game = new SPMain();
		game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		
		JFrame frame = new JFrame(game.TITLE + "  " + 1.1);
		frame.add(game);
		frame.pack();
		frame.setIconImage(Images.icon.getImage());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}

	public void reset() {
		SCORE = 0;
		ball.speed = 3;
		ball.lastspeed = ball.speed;
		ball.setXa(ball.speed);
		ball.setYa(ball.speed);
		ball.setY(0);
		ball.setX(rand.nextInt(WIDTH - 20));
		player.x = WIDTH / 2 - player.getWidth()/2;;
		Counter = 0;
		CounterPW = 0;
		
		if(dp.isEnabled())dp.setEnabled(false);
		if(ds.isEnabled())ds.setEnabled(false);
		if(sd.isEnabled())sd.setEnabled(false);
		if(p10.isEnabled())p10.setEnabled(false);
		
	}

	public void gameOver() {
		Sounds.GO.play();
		State = STATE.GAMEOVER;
		
	}
	
}
