package gustavoaguilar.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	Random rand = new Random();
	
	private int x;
	private int y = 0;
	private int xa = 3;
	private int ya = 3;
	
	int speed=3;
	int lastspeed = 3;
	
	private static final int DIAMETER = 20;

	SPMain game;
	Sounds snd;
	
	public Ball(SPMain game){
		this.game = game;
		setX(rand.nextInt(SPMain.WIDTH - 20));
	}
	
	public void update(){
				
		if(getX() + getXa() < 0){
			Sounds.Hit.play();
			setXa(speed);
		}
		if(getX() + getXa() > game.getWidth() - DIAMETER){
			Sounds.Hit.play();
			setXa(-speed);
		}
		if(getY() + getYa() < 0){
			Sounds.Hit.play();
			setYa(speed);
		}
		if(getY() + getYa() > game.getHeight() - DIAMETER){
			setYa(-speed);
			game.gameOver();
		}
		
		if(collision()){
			if(game.DPEnabled)SPMain.SCORE += 1;
			Sounds.Bip.play();
			SPMain.SCORE += 1;
			game.CounterSpeed +=1;
			setYa(-speed);
			if(getX() + DIAMETER/2 <= game.player.getBounds().x+10){
				setXa(-speed);
				//System.out.println("Esquerda");
			}
			if(getX() + DIAMETER/2 >= game.player.getBounds().x+game.player.getWidth()-10){
				setXa(speed);
				//System.out.println("Direita");
			}
			setY(game.player.getTopY() - DIAMETER);
		}
		
		setX(getX() + getXa());
		setY(getY() + getYa());
		
	}
	
	private boolean collision(){
		return game.player.getBounds().intersects(getBounds());
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.drawOval(getX(), getY(), DIAMETER, DIAMETER);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(getX(),getY(),DIAMETER,DIAMETER);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getXa() {
		return xa;
	}

	public void setXa(int xa) {
		this.xa = xa;
	}

	public int getYa() {
		return ya;
	}

	public void setYa(int ya) {
		this.ya = ya;
	}
	
	
}
