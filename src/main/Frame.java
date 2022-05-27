package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
	public JPanel panel;
	public JButton button;
	public JLabel label;
	public JTextField textField;
	public GamePanel gamePanel;

    Frame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("YUMYUM Tainan");
		this.getContentPane().setBackground(new Color(255, 236, 241));

		gamePanel = new GamePanel();
		this.add(gamePanel);
		this.pack();
		this.setVisible(true);

		gamePanel.setUpGame();

		panel = new JPanel();
		panel.setBackground(new Color(255, 236, 241));
		panel.setBounds(0, 0, 48 * 16, 48 * 12);
		panel.setLayout(null);

		String str = "Please Enter your name";
		label = new JLabel(str);
		label.setFont(new Font("Arial", Font.BOLD, 50));
		label.setForeground(Color.BLACK);
		label.setBounds(100, 200, 1000, 100);

		textField = new JTextField();
		//textField.setPreferredSize(new Dimension(250, 40));
		textField.setBounds(48*16/2 - 500/2 - 50/2,390,500,70);

		button = new JButton();
		button.setBounds(48*16/2 - 50/2 + 500/2, 390, 50, 70);
		button.addActionListener(this);

		panel.add(textField);
		panel.add(label);
		panel.add(button);
		this.add(panel);

		this.setLocationRelativeTo(null);


		gamePanel.startGameThread();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == button){
			gamePanel.player.playerName = textField.getText();
			if(gamePanel.player.playerName.length() == 0){
				label.setText("  Name can't be empty");
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
