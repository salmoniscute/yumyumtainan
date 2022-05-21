package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gamePanel;
	
	public boolean upPressed,downPressed,rightPresed,leftPressed,enterPressed;
	
	public KeyHandler(GamePanel gPanel) {
		
		this.gamePanel=gPanel;
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//title state
		if(gamePanel.gameState==gamePanel.titleState) {
			
			if(gamePanel.ui.titlescreenState==0) {
				if (code==KeyEvent.VK_W) {
					gamePanel.ui.commandNum--;
					if(gamePanel.ui.commandNum<0) {
						gamePanel.ui.commandNum=2;
					}
				}
				if (code==KeyEvent.VK_S) {
					gamePanel.ui.commandNum++;
					if(gamePanel.ui.commandNum>2) {
						gamePanel.ui.commandNum=0;
					}
				}
				if (code==KeyEvent.VK_ENTER) {
					if(gamePanel.ui.commandNum==0) {
						gamePanel.ui.titlescreenState=1;
					}
					if(gamePanel.ui.commandNum==1) {
						//add later
					}
					if(gamePanel.ui.commandNum==2) {
						System.exit(0);
					}
					
				}
			}
			else if(gamePanel.ui.titlescreenState==1) {
				if (code==KeyEvent.VK_W) {
					gamePanel.ui.commandNum--;
					if(gamePanel.ui.commandNum<0) {
						gamePanel.ui.commandNum=1;
					}
				}
				if (code==KeyEvent.VK_S) {
					gamePanel.ui.commandNum++;
					if(gamePanel.ui.commandNum>1) {
						gamePanel.ui.commandNum=0;
					}
				}
				if (code==KeyEvent.VK_ENTER) {
					if(gamePanel.ui.commandNum==0) {
						gamePanel.gameState=gamePanel.playState;
						//gamePanel.ui.titlecreenState=2;
					}
					if(gamePanel.ui.commandNum==1) {
						gamePanel.ui.titlescreenState=0;
					}
				}
			}
		
			
		}
		
		//play state
		else if(gamePanel.gameState==gamePanel.playState) {
			if (code==KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code==KeyEvent.VK_S) {
				downPressed = true;
						
			}
			if (code==KeyEvent.VK_A) {
				leftPressed = true;
				
			}
			if (code==KeyEvent.VK_D) {
				rightPresed = true;
				
			}
			if (code==KeyEvent.VK_P) {
					gamePanel.gameState=gamePanel.pauseState;
				
			}

			if (code==KeyEvent.VK_ENTER) {
				enterPressed=true;
				
			}
		}
		//pause state
		else if(gamePanel.gameState==gamePanel.pauseState) {
			if (code==KeyEvent.VK_P) {
				gamePanel.gameState=gamePanel.playState;
			
			}
		}
		
		//dialogue state
		else if(gamePanel.gameState==gamePanel.dialogueState) {
			if(code==KeyEvent.VK_ENTER) {
				gamePanel.gameState=gamePanel.playState;
			}
		}
		
		

		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code =e.getKeyCode();
		if (code==KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code==KeyEvent.VK_S) {
			downPressed = false;
					
		}
		if (code==KeyEvent.VK_A) {
			leftPressed = false;
			
		}
		if (code==KeyEvent.VK_D) {
			rightPresed = false;
			
		}
	}
	
	
	
	
	
	
	

}
