package gustavoaguilar.main;

import gustavoaguilar.main.SPMain.STATE;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoard implements KeyListener {

	Player p;
	SPMain game;
	
	public boolean left,right,up,down;
	
	public KeyBoard(SPMain game, Player p){
		this.game = game;
		this.p = p;
	}
	
	public void keyPressed(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_LEFT )left = true;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT )right = true;
		
		if(e.getKeyCode() == KeyEvent.VK_UP ){
			
			if(game.State == STATE.MENU){
				Sounds.Hit.play();
				game.SelectMenu -=1;
			}
			if(game.State == STATE.CREDITS){
				Sounds.Hit.play();
				game.SelectCredits -=1;
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN ){
			if(game.State == STATE.MENU){
				Sounds.Hit.play();
				game.SelectMenu +=1;
			}
			if(game.State == STATE.CREDITS){
				Sounds.Hit.play();
				game.SelectCredits +=1;
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE )if(game.State == STATE.GAME){
			Sounds.Bip.play();
			game.State = STATE.MENU;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE ){
			Sounds.Bip.play();
			if(game.SelectMenu == 1){
				game.reset();
				game.State = STATE.GAME;
				game.SelectMenu = 0;
			}
			
			if(game.SelectMenu == 2){
				game.State = STATE.CREDITS;
				game.SelectMenu = 0;
			}
			
			if(game.SelectMenu == 3)System.exit(0);
			
			if(game.SelectCredits == 2){
				game.State = STATE.MENU;
				game.SelectCredits = 0;
			}
		
			if(game.State == STATE.GAMEOVER)game.State = STATE.MENU;
			
		}

	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT )left = false;
		if(e.getKeyCode() == KeyEvent.VK_RIGHT )right = false;

	}

	public void keyTyped(KeyEvent e) {
		
	}

}
