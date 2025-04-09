import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {

    private static final long serialVersionUID = 1L;
	
	//.........................image.........................//
    private ImageIcon enemy;
    private ImageIcon WiningCup;
    private ImageIcon green;
    private ImageIcon white;
    
    private Image enemyImage;
    private Image WiningCupImage;
    

    //.........................Button.........................//
	private JButton restartButton;
	private JButton playAgainButton;

	//.........................Score and maxScore.........................//
	protected int scores = 0;
	protected int maxScore = 0;
	
	public GameOver(JFrame f, int scores, int maxScore) {
		setLayout(null);
		
		this.scores = scores;
		this.maxScore = maxScore;

		//.........................set background color.........................//
		setBackground(new Color( 33, 97, 140 ));
		
		//.........................set score image.........................//
		enemy = new ImageIcon("enemy.png");	
		enemyImage = enemy.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		//.........................set wining cup image.........................//
		WiningCup = new ImageIcon("WiningCup.png");
		WiningCupImage = WiningCup.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		//.........................set play again button.........................//
		playAgainButton = new JButton("Play Again");
		playAgainButton.setFont(new Font("arial", Font.BOLD, 20));
		playAgainButton.setBounds(200, 375, 200, 50);
		playAgainButton.setBackground(Color.white);
		
		playAgainButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				f.getContentPane().removeAll();
				GamePlay gamePlay = new GamePlay();
				gamePlay.maxScore = maxScore;
				f.add(gamePlay);
				f.revalidate();
				f.repaint();
				gamePlay.requestFocusInWindow();
			}
		});
				
    	//.........................set restart button.........................//
		restartButton = new JButton("Restart Game");
		restartButton.setFont(new Font("arial", Font.BOLD, 20));
		restartButton.setBounds(200, 450, 200, 50);
		restartButton.setBackground(Color.white);
		
		restartButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.getContentPane().removeAll();
                GamePlay gamePlay = new GamePlay();
                f.add(gamePlay);
                f.revalidate();
                f.repaint();
                gamePlay.requestFocusInWindow();
			}
		});
		
		//.........................add button.........................//
		add(playAgainButton);
		add(restartButton);
		
	}
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //.........................set game over text.........................//
        g.setColor(Color.white);
        g.setFont(new Font("Serif", Font.CENTER_BASELINE, 30));
        g.drawString("Game Over", 200, 50);
        
        //.........................set center color.........................//
        g.setColor(new Color(133, 193, 233));
        g.fillRoundRect(150, 100, 300, 400, 30, 30);

        //........................set scores.........................//
        g.drawImage(enemyImage, 200, 150,this);
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.ITALIC, 30));
        g.drawString(Integer.toString(scores), 207, 230);
        
        //.........................set max score.........................//
        g.drawImage(WiningCupImage, 350, 150, this);
        g.setColor(Color.white);
        g.setFont(new Font("arial", Font.ITALIC, 30));
        g.drawString(Integer.toString(maxScore), 360, 230);
        
        //.........................make snake.........................//
        green = new ImageIcon("green.png");
        white = new ImageIcon("white.png");
        for(int i=6; i<11; i++) {
        	green.paintIcon(this, g, i*25, 300);
        }
        white.paintIcon(this, g, 275, 300);
        
        
        for(int i=6; i<18; i+=2) {
        	for(int j=13; j<21	; j++) {
        		if(j % 2 == 0) {
					g.setColor(new Color( 33, 97, 140 ));
			    	g.fillRect(i*25, j*25, 25, 25);
			    	
			    	g.setColor(new Color( 40, 116, 166 ));
			    	g.fillRect((i+1)*25, j*25, 25, 25);
        		}else {
        			g.setColor(new Color( 40, 116, 166 ));
			    	g.fillRect(i*25, j*25, 25, 25);
			    	
			    	g.setColor(new Color( 33, 97, 140 ));
			    	g.fillRect((i+1)*25, j*25, 25, 25);
        		}
        	}
        }
        
    }

}