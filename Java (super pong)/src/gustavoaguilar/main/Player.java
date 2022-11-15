package gustavoaguilar.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	
	int x;
	private static final int y = 420;
	private int speed = 6;
	
	private int width;
	private int height = 10;
	
	SPMain game;
	KeyBoard kb;
	
	public Player(SPMain game,KeyBoard kb){
		this.game = game;
		this.kb = kb;
		
		x = SPMain.WIDTH / 2 - width/2;
		
	}
	
	public void update(){
		if(x >= 0 ){
		 if(kb.left){
			x -= speed;
		 }
		}else x++;
		
		if(x <= SPMain.WIDTH - width+6){
		 if(kb.right){
			x += speed;
		 }
		}else x--;
		
		if(game.DSEnabled){
			if(width < 100){
				width+=2;
				x--;
			}
			
		}else{
			if(width > 50){
				width-=2;
				x++;
			}
		}
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.drawRect(x, y, width, height);
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
	
	public int getTopY(){
		return y;
	}
	
	public void setWidth(int value){
		width = value;
	}
	
	public int getWidth(){
		return width;
	}
	
	public void setX(int value){
		x =value -10;
	}
	
}
