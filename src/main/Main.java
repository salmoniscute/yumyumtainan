package main;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("YUMYUM Tainan");
		window.getContentPane().setBackground(new Color(255, 236, 241));

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.setUpGame();
		gamePanel.startGameThread();

	}

}
