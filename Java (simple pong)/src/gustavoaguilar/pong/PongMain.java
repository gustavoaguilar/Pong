package gustavoaguilar.pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PongMain extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Ball ball = new Ball(this);
	Racquet racquet = new Racquet(this);
	 int score = 0;
	 static double speed = 1;
	 boolean gameover = false;
	 		 
	 Random ranx = new Random();
	// Random BGColor = new Random();
	 
	
	private int getScore(){
		return score;
	}
	
	public PongMain(){
		/*	
		float r = BGColor.nextFloat();
		float g = BGColor.nextFloat();
		float b = BGColor.nextFloat();
		*/
		
		setBackground(Color.BLACK);
		//setBackground(new Color(r,g,b));
		
		Sound.GO.play();
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				racquet.keyReleased(e);
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				racquet.keyPressed(e);
			if(gameover)
				if(e.getKeyCode() == KeyEvent.VK_SPACE) Reset();
				
			}
		});
		setFocusable(true);
	}
	
	private void move(){
		ball.move();
		racquet.move();
	}
			
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		ball.paint(g2d);
		racquet.paint(g2d);
		
		g2d.setColor(Color.white);
		g2d.setFont(new Font("Arial", Font.BOLD , 30));
		if(!gameover)
		g2d.drawString(String.valueOf(getScore()), 10, 30);
		
		if(gameover == true){
			g2d.setColor(Color.white);
			g2d.setFont(new Font("Arial", Font.BOLD , 20));
			g2d.drawString("Game Over",getWidth()/2 - 50,getHeight()/2 - 50);
			g2d.drawString("Your Score : " + score,getWidth()/2 - 56,getHeight()/2 - 20);
			
			g2d.setFont(new Font("Arial", Font.BOLD , 12));
			g2d.drawString("Press Space to Continue",getWidth()/2 - 57,getHeight()/2 +10);
		}
	}
	
	public void Reset(){
		score = 0;
		speed = 1;
		ball.x = ranx.nextInt(320) +1;;
		ball.y = 0;
		ball.xa = speed;
		ball.ya = speed; 
		racquet.x = 145;
		racquet.xa = 0;
		gameover = false;
	}
	
	public void gameOver(){
		Sound.GO.play();
		gameover = true;
		
		ball.xa = 0;
		ball.ya = 0;
		racquet.xa = 0;
		//JOptionPane.showMessageDialog(this,  "Score : " + getScore(),"Game Over", JOptionPane.YES_NO_OPTION);
		
		//Reset();
	}
	
	public static void main(String[] args)	throws InterruptedException, IOException {
		JFrame frame = new JFrame("Pong 1.0");
		PongMain pong = new PongMain();
		
		frame.setSize(350, 550);
		frame.setResizable(false);
		frame.add(pong);
		//ImageIcon image = new ImageIcon("res/images/PongIco.png");
		frame.setIconImage(Images.icon.getImage());
		//frame.setIconImage(new ImageIcon("res/images/PongIco.png").getImage());
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while(true){
			//System.out.println(speed);
			pong.move();
			pong.repaint();
			Thread.sleep(5);
		}
		
	}
	
}
