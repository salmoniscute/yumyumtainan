package main;

import javax.swing.JFrame;
import java.awt.*;

public class Main {
	public Container container;
	public JFrame window;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}

	public Main(){
		window = new JFrame();

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("YUMYUM Tainan");
		window.getContentPane().setBackground(new Color(255,236,241));

		container = new Container();
		container = window.getContentPane();

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.setUpGame();

		gamePanel.startGameThread();
	}

}
