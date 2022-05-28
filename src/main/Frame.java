package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements ActionListener{
	public JPanel panel;
	public JButton button;
	public JLabel label;
	public JTextField textField;
	public GamePanel gamePanel;

    Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(336,153);
		this.setResizable(false);
		this.setTitle("YUMYUM Tainan");
		this.getContentPane().setBackground(new Color(255, 236, 241));
		


		gamePanel = new GamePanel();
		this.add(gamePanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		gamePanel.setUpGame();

		panel = new JPanel();
		panel.setBackground(new Color(255, 236, 241));
		panel.setBounds(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
		panel.setLayout(null);


		label = new JLabel("在遊戲開始之前，請先設定玩家名稱吧！");
		label.setFont(new Font("Arial", Font.BOLD,30));
		label.setForeground(Color.black);
		label.setBounds(108, 200, 1000, 100);

		textField = new JTextField();
		textField.setBounds(109,280,400,50); //看起來我在調數字的時候 是用湊的 那就直接乘開吧

		button = new JButton();
		button.setBounds(504, 280, 50,50);
		button.addActionListener(this);

		panel.add(textField);
		panel.add(label);
		panel.add(button);
		this.add(panel);

		gamePanel.startGameThread();
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			gamePanel.player.playerName = textField.getText();

			if(gamePanel.player.playerName.length() == 0){
				label.setText(" 姓名欄不能是空的ㄛ！");
			}
			else {
				gamePanel.ui.titleScreenState = 1;
				gamePanel.setVisible(true);
				button.setEnabled(false);
				textField.setEnabled(false);
				panel.setEnabled(false);
			}
		}
	}

}