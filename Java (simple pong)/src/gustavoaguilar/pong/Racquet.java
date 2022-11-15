package gustavoaguilar.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet {
	
	private static final int Y = 430;
	private static final int WIDTH = 60;
	private static final int HEIGHT = 10;
	
	private PongMain pong;
	int x = 145;
	double xa = 0;
	
	public Racquet(PongMain pong){
		this.pong = pong;
	}
	
	public void move(){
		if(x + xa > 0 && x + xa < pong.getWidth() - WIDTH ) x += xa;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x, Y, WIDTH, HEIGHT);
	}
	
	public void keyReleased(KeyEvent e){
		xa = 0;
	}
	
	public void keyPressed(KeyEvent e){
		if(!pong.gameover){
		if(e.getKeyCode() == KeyEvent.VK_LEFT) xa = - pong.speed;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) xa = pong.speed;
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,Y,WIDTH,HEIGHT);
	}
	
	public int getTopY(){
		return Y ;
	}
	

}
