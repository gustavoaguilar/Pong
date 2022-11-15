package gustavoaguilar.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	private static final int DIAMETER = 30;
	
	Random ranx = new Random();
	
	int x = ranx.nextInt(320) +1;
	int y = 0;
	double xa = 1;
	double ya = 1;
	
	private PongMain pong;
	
	public Ball(PongMain pong){
		this.pong = pong;
	}
	
	void move(){
		
		if(!pong.gameover){
		
		if(x+xa < 0){
			Sound.Hit.play();;
			xa = pong.speed ;
		}
		if(x+xa > pong.getWidth() - DIAMETER) {
			Sound.Hit.play();;	
			xa = -pong.speed;
	}
		if(y+ya < 0){
			Sound.Hit.play();;
			ya = pong.speed;
		}
		if(y+ya > pong.getHeight() -DIAMETER){
			Sound.Hit.play();
			ya = -pong.speed;
		}
		
		if (y+ya >= 430) 
			pong.gameOver();
		
		if(collision()){
			Sound.Bip.play();
			pong.score += 1;
			if(pong.speed < 10)pong.speed += 1;
			ya = -pong.speed;
			y = pong.racquet.getTopY() - DIAMETER;
		}
		
		x += xa;
		y += ya;
		}
	}
	
	private boolean collision(){
		return pong.racquet.getBounds().intersects(getBounds());
	}
	
	
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillOval(x,y,DIAMETER,DIAMETER);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,DIAMETER,DIAMETER);
	}
			
	

}
