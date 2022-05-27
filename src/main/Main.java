package main;

import javax.swing.*;
import java.awt.*;

public class Main {
	public JFrame window;
	public JLabel label;
	public JButton button;
	public JPanel panel;


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

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();



		panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setBounds(0,0,48 * 16, 48 * 12);
		panel.setLayout(null);

		button = new JButton();
		button.setBounds(150, 400, 500, 100);
		//button.addActionListener();*/

		label = new JLabel("Please Succese");
		label.setFont(new Font("Arial", Font.BOLD, 40));
		label.setForeground(Color.WHITE);
		label.setBounds(100, 30, 500,500);

		panel.add(label);
		panel.add(button);
		window.add(panel);


		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.setUpGame();

		gamePanel.startGameThread();
	}

}
