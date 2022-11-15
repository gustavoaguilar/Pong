package gustavoaguilar.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class DoublePoints {

	private int x;
	private int y;
	
	private int size = 30;
	
	private boolean enabled;
	
	SPMain game;
	Random rand = new Random();
	
	public DoublePoints(SPMain game){
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
			game.DPEnabled = true;
			setEnabled(false);
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.drawRect(x, y, size, size);
		g.setFont(new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT,15));
		g.drawString("x2", x+8, y+15);
		g.setFont(new Font("Arial",Font.LAYOUT_LEFT_TO_RIGHT,9));
		g.drawString("Points", x+2, y+25);
		
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
