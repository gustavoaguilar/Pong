package gustavoaguilar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SlowDown {
	

	private int x;
	private int y;
	
	private int size = 30;
	
	private boolean enabled;
	
	SPMain game;
	Random rand = new Random();
	Ball ball;
	
	public SlowDown(SPMain game){
		this.game = game;
	}
	
	public void activate(){
		setEnabled(true);
		x = rand.nextInt(SPMain.WIDTH - size);
		y = 0;
	}
	
	public void update(){
		y+=3;
		if(collision()){
			Sounds.PW.play();
			game.Counter = 600;
			game.SlowDown();
			game.SDEnabled = true;
			setEnabled(false);
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.CYAN);
		g.drawRect(x, y, size, size);
		g.setFont(new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT,11));
		g.drawString("Slow", x+2, y+12);
		g.drawString("Down", x+2, y+25);
		
	}
	
	private boolean collision(){
		return game.player.getBounds().intersects(getBounds());
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,size,size);
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
