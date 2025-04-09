import java.awt.Color;


import javax.swing.JFrame;


public class Main {
	
	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		
		f.setTitle("Snack game");
		f.setBounds(1000,100,600,657);
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBackground(new Color( 133, 193, 233 ));
		
		GamePlay gamePlay = new GamePlay();
		//to add component in frame
		f.add(gamePlay);
	}
}