import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GamePlay extends JPanel implements KeyListener,ActionListener{

	private static final long serialVersionUID = 1L;
	private int [] snakeXLength = new int [750];
	private int [] snakeYLength = new int [750];
	
	//.........................define default.........................//
	private int lengthOfSnake = 3;
	
	//.........................for moving snake.........................//
	private boolean right = false;
	private boolean left = false;
	private boolean up = false;
	private boolean down = false;

	
	//.........................for snake image.........................//
	private ImageIcon mouth;	
	private ImageIcon body;	
	
	//.........................this is for when right, left, top and down face are will be different.........................//
//	private ImageIcon rightmouth;
//	private ImageIcon leftmouth;
//	private ImageIcon upmouth;
//	private ImageIcon downmouth;
//	private ImageIcon snakeimage;
	
	//.........................image for ball.........................//
	private ImageIcon enemy;
	
	//.........................when start the game.........................//
	private int moves = 0;
	
	//.........................game score, and maxScore.........................//
	private int scores = 0;
	protected int maxScore = 0;
	
	//.........................to control game speed.........................
	private Timer timer;
	private int delay = 150;
	
	//.........................many position of ball.........................//
	private int [] enemeyXpose = {25, 50, 75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400,
								425, 450, 475, 500, 525, 550}; 
	
	private int [] enemeyYpose = {75, 100, 125, 150, 175, 200, 225, 250, 275, 300, 325, 350, 375, 400, 425, 
								450, 475, 500, 525, 550, 575};
	
	//.........................random position of ball.........................//
	private Random random = new Random();
	private int Xpose = random.nextInt(enemeyXpose.length-1);
	private int Ypose = random.nextInt(enemeyYpose.length-1);
	
		
	public GamePlay () {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(true);
		
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		
		if(moves == 0) {
			snakeXLength[0] = 125;
			snakeXLength[1] = 100;
			snakeXLength[2] = 75;
			
			snakeYLength[0] = 100; 
			snakeYLength[1] = 100; 
			snakeYLength[2] = 100; 
		}
		
		
		//.........................create a title of game.........................//
		
		g.setColor(new Color( 33, 97, 140 ));
		g.fillRect(0, 0, 600, 70);
		
		//.........................paint Scores........................//
		
		enemy = new ImageIcon("enemy.png");
		enemy.paintIcon(this, g, 20, 25);
	    
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.ITALIC, 30));
		g.drawString(Integer.toString(scores), 55,47);
		
		
		//.........................create a body of game ground.........................//
		for(int i=3; i < 24; ) {
			for(int j=1; j < 22; ) {
				if(i % 2 == 0) {
					g.setColor(new Color( 33, 97, 140 ));
					g.fillRect(j*25, i*25, 25, 25);
					
					g.setColor(new Color ( 40, 116, 166 ));
					g.fillRect((j+1)*25, (i)*25, 25, 25);
				}else {
					g.setColor(new Color ( 40, 116, 166 ));
					g.fillRect(j*25, i*25, 25, 25);
					
					g.setColor(new Color( 33, 97, 140 ));
					g.fillRect((j+1)*25, (i)*25, 25, 25);
					
				}
				j +=2;
			}
			 i ++;
		}		
		
		
		//.........................initialize a snake mouth and body.........................//
		
		mouth = new ImageIcon("white.png");
		mouth.paintIcon(this, g, snakeXLength[0], snakeYLength[0]);
		
		for(int i=0; i<lengthOfSnake; i++) {
			
			
			//.........................this is for when right, left, top and down face are will be different.........................//
//			if(i == 0 && right) {
//				rightmouth = new ImageIcon("rightmouth.png");
//				rightmouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
//				
//			}
//			if(i == 0 && left) {
//				leftmouth = new ImageIcon("leftmouth.png");
//				leftmouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
//			}
//			if(i == 0 && up) {
//				upmouth = new ImageIcon("upmouth.png");
//				upmouth .paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
//			}
//			if(i == 0 && down) {
//				downmouth = new ImageIcon("downmouth.png");
//				downmouth.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
//			}
			
			if(i!=0) {
				body = new ImageIcon("green.png");
				body.paintIcon(this, g, snakeXLength[i], snakeYLength[i]);
			}
		}
		
		enemy = new ImageIcon("enemy.png");
		enemy.paintIcon(this, g, enemeyXpose[Xpose], enemeyYpose[Ypose]);
		
		
		//.........................when snake eat food.........................//
		
		if(enemeyXpose [Xpose] == snakeXLength[0] && enemeyYpose[Ypose] == snakeYLength[0]) {
			lengthOfSnake++;
			scores++;
			maxScore = Math.max(scores, maxScore);
			Xpose = random.nextInt(enemeyXpose.length-1);
			Ypose = random.nextInt(enemeyYpose.length-1);
		}
		
		
		//.........................when game is over.........................//
		
		for(int i=1; i<lengthOfSnake;i++) {
			if(snakeXLength[i] == snakeXLength[0] && snakeYLength[i] == snakeYLength[0]) {
				gameOver();
			}
		}
		g.dispose();
	}


	//.........................method from ActionListener.........................//
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(right){
			for(int i=lengthOfSnake-1; i>=0 ;i--) {
				snakeYLength[i+1] = snakeYLength[i];
			}
			
			for(int i=lengthOfSnake; i>=0;i--) {
				if(i == 0) {
					snakeXLength[i] = snakeXLength[i]+25;
				}else {
					snakeXLength[i] = snakeXLength[i-1];
				}
				
				if( snakeXLength[i] > enemeyXpose[enemeyXpose.length-1] ) {
					gameOver();
//					snakeXLength[i] = enemeyXpose[0];
				}
			}
			repaint();
		}
		
		if(left){
			for(int i=lengthOfSnake-1; i>=0 ;i--) {
				snakeYLength[i+1] = snakeYLength[i];
			}
			
			for(int i=lengthOfSnake; i>=0;i--) {
				if(i == 0) {
					snakeXLength[i] = snakeXLength[i]-25;
				}else {
					snakeXLength[i] = snakeXLength[i-1];
				}
				
				if( snakeXLength[i] < enemeyXpose[0] ) {
					gameOver();
//					snakeXLength[i] = enemeyXpose[enemeyXpose.length-1];
				}
			}
			repaint();
		}
		
		if(down){
			for(int i=lengthOfSnake-1; i>=0 ;i--) {
				snakeXLength[i+1] = snakeXLength[i];
			}
			
			for(int i=lengthOfSnake; i>=0;i--) {
				if(i == 0) {
					snakeYLength[i] = snakeYLength[i]+25;
				}else {
					snakeYLength[i] = snakeYLength[i-1];
				}
				
				if( snakeYLength[i] > enemeyYpose[enemeyYpose.length-1] ) {
					gameOver();
//					snakeYLength[i] = enemeyYpose[0];
				}
			}
			repaint();
		}
		
		if(up){
			for(int i=lengthOfSnake-1; i>=0 ;i--) {
				snakeXLength[i+1] = snakeXLength[i];
			}
			
			for(int i=lengthOfSnake; i>=0;i--) {
				if(i == 0) {
					snakeYLength[i] = snakeYLength[i]-25;
				}else {
					snakeYLength[i] = snakeYLength[i-1];
				}
				
				if( snakeYLength[i] < enemeyYpose[0] ) {
					gameOver();
//					snakeYLength[i] = enemeyYpose[enemeyYpose.length-1];
				}
			}
			repaint();
		}
		
	}

	//.........................method from KeyListener.........................//
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moves++;
			if(!left) {
				right = true;
			}else {
				right = false;
				left = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			moves++;
			
			if(!right) {
				left = true;
			}else {
				left = false;
				right = true;
			}
			up = false;
			down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			moves++;
			
			if(!up) {
				down = true;
			}else {
				down = false;
				up = true;
			}
			right = false;
			left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			moves++;
			
			if(!down) {
				up = true;
			}else {
				up = false;
				down = true;
			}
			right = false;
			left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			right = false;
			left = false;
			up = false;
			down = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void gameOver(){
		JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
	    topFrame.getContentPane().removeAll();
	    topFrame.add(new GameOver(topFrame, scores, Math.max(scores, maxScore)));
	    topFrame.revalidate();
	    topFrame.repaint();
	    
	    right = false;
		left = false;
		up = false;
		down = false;
		
	    lengthOfSnake = 3;
		scores = 0;
		moves = 0;
	}

}