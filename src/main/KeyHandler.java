package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gamePanel;
	
	public boolean upPressed,downPressed, rightPressed,leftPressed,enterPressed,attackPressed;
	
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
			titleState(code);
		}
		
		
			//play state
			else if(gamePanel.gameState==gamePanel.playState) {
				playState(code);
			}
				
			//pause state
			else if(gamePanel.gameState==gamePanel.pauseState) {
				pauseState(code);
			}
			
			//dialogue state
			else if(gamePanel.gameState==gamePanel.dialogueState) {
				dialogueState(code);
			}
			//character state
			else if(gamePanel.gameState==gamePanel.characterState) {
				characterState(code);
			}	
			else if(gamePanel.gameState==gamePanel.storeState) {
				storeState(code);
			}
		
	}
	
	public void titleState(int code) {
		
		if(gamePanel.ui.titleScreenState==0) {
			
			if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP ) {
				gamePanel.ui.commandNum--;
				if(gamePanel.ui.commandNum<0) {
					gamePanel.ui.commandNum=2;
				}
			}
			if (code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
				gamePanel.ui.commandNum++;
				if(gamePanel.ui.commandNum>2) {
					gamePanel.ui.commandNum=0;
				}
			}
			if (code==KeyEvent.VK_ENTER) {
				if(gamePanel.ui.commandNum==0) {
					gamePanel.ui.titleScreenState=1;
				}
				if(gamePanel.ui.commandNum==1) {
					//add later
				}
				if(gamePanel.ui.commandNum==2) {
					System.exit(0);
				}
				
			}
		}
		else if(gamePanel.ui.titleScreenState==1) {
			if (code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
				gamePanel.ui.commandNum--;
				if(gamePanel.ui.commandNum<0) {
					gamePanel.ui.commandNum=1;
				}
			}
			if (code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
				gamePanel.ui.commandNum++;
				if(gamePanel.ui.commandNum>1) {
					gamePanel.ui.commandNum=0;
				}
			}
			if (code==KeyEvent.VK_ENTER) {
				if(gamePanel.ui.commandNum==0) {
					gamePanel.ui.titleScreenState=2;
				}
				if(gamePanel.ui.commandNum==1) {
					gamePanel.ui.titleScreenState=0;
				}
			}
		}
		else if(gamePanel.ui.titleScreenState==2) {
			if (code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
				gamePanel.ui.commandNum--;
				if(gamePanel.ui.commandNum<0) {
					gamePanel.ui.commandNum=1;
				}
			}
			if (code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
				gamePanel.ui.commandNum++;
				if(gamePanel.ui.commandNum>1) {
					gamePanel.ui.commandNum=0;
				}
			}
			if (code==KeyEvent.VK_ENTER) {
				if(gamePanel.ui.commandNum==0) {
					gamePanel.gameState=gamePanel.playState;
				}
				if(gamePanel.ui.commandNum==1) {
					gamePanel.ui.titleScreenState=1;
				}
			}
		}
	}
	
	public void playState(int code) {
		
		if (code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
			downPressed = true;
					
		}
		if (code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
			leftPressed = true;
			
		}
		if (code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
			rightPressed = true;
			
		}
		if (code==KeyEvent.VK_P) {
			gamePanel.gameState=gamePanel.pauseState;
			
		}

		if (code==KeyEvent.VK_ENTER) {
			enterPressed=true;
			
		}
		if (code==KeyEvent.VK_K) {
			attackPressed=true;
			gamePanel.player.attacking=true;
			
		}
		if (code==KeyEvent.VK_C) {
			gamePanel.gameState=gamePanel.characterState;
			
		}
		
		
	
	}
		
	
	public void pauseState(int code) {
		
		if (code==KeyEvent.VK_P) {
			gamePanel.gameState=gamePanel.playState;
		
		}
		
	}
	public void dialogueState(int code) {
		
		if(code==KeyEvent.VK_ENTER) {
			gamePanel.gameState=gamePanel.playState;
		}
	}
	public void characterState(int code) {
		
		if (code==KeyEvent.VK_C) {
			gamePanel.gameState=gamePanel.playState;
			
		}
		
	}
	public void storeState(int code) {
		
		if(gamePanel.ui.storeScreenState==0) {
					
				if (code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
					gamePanel.ui.commandNum--;
					if(gamePanel.ui.commandNum<0) {
						gamePanel.ui.commandNum=1;
					}					
				}
				if (code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
					gamePanel.ui.commandNum++;
					if(gamePanel.ui.commandNum>1) {
						gamePanel.ui.commandNum=0;
					}
				}
				if (code==KeyEvent.VK_ENTER) {
					if(gamePanel.ui.commandNum==0) {
						gamePanel.ui.storeScreenState=1;
					}
					if(gamePanel.ui.commandNum==1) {
						gamePanel.gameState=gamePanel.playState;
					}			
				
						
				}
		}
		else if(gamePanel.ui.storeScreenState==1) {
				if (code==KeyEvent.VK_ENTER) {
					gamePanel.gameState=gamePanel.playState;		
					gamePanel.ui.commandNum=0;
					gamePanel.ui.storeScreenState=0;
				}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code =e.getKeyCode();
		if (code==KeyEvent.VK_W || code==KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN) {
			downPressed = false;
					
		}
		if (code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT) {
			leftPressed = false;
			
		}
		if (code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT) {
			rightPressed = false;
			
		}
	}
	
	
	
	
	
	
	

}
