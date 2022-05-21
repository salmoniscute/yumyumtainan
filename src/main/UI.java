package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.PublicKey;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.MediaSize.Other;
import javax.swing.JTextField;

public class UI {
	
	GamePanel gamePanel;
	Font arial_40,arial_80B;
	Graphics2D g2;
	//BufferedImage  money;
	public boolean messageOn;
	public String message ="" ;
	//int messageCounter=0;
	public boolean gameFinished=false;
	public String currentDialogue="";
	public int commandNum=0;
	public int titlescreenState=0;
	
	
	public UI(GamePanel gp) {
		
		this.gamePanel=gp;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B=new Font("Arial", Font.BOLD, 80);
		/*try {
			money= ImageIO.read(getClass().getResourceAsStream("/object/money.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	public void showMessage(String text) {
		message = text;
		messageOn=true;
	}
	
	public void draw(Graphics2D g2) {
		this.g2=g2;
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		//title state
		if(gamePanel.gameState==gamePanel.titleState) {
			drawTitleScreen();
		}
		//play state
		if(gamePanel.gameState==gamePanel.playState) {
			//do play
		}
		//pause state
		if (gamePanel.gameState==gamePanel.pauseState) {
			drawPauseScreen();
		}
		//dialogue
		if(gamePanel.gameState==gamePanel.dialogueState) {
			drawDialogueScreen();
		}
		
		/*if (gameFinished==true) {
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			String text;
			int textlength;
			int x,y;
			
			text= "You have no money!";
			textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x=gamePanel.screenWidth/2-textlength/2;
			y=gamePanel.screenHeight/2-gamePanel.tileSize*3;
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);

			text= "Oh no!!!";
			textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			x=gamePanel.screenWidth/2-textlength/2;
			y=gamePanel.screenHeight/2+gamePanel.tileSize*3;
			g2.drawString(text, x, y);
			
			gamePanel.gameThread=null;
			
		}
		
		else{
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(money, gamePanel.tileSize/2, gamePanel.tileSize/2, gamePanel.tileSize, gamePanel.tileSize,null);
			g2.drawString(" X "+gamePanel.player.hasMoney, 70, 60);
			
			//message
			if (messageOn==true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gamePanel.tileSize/2, gamePanel.tileSize*5);
				
				messageCounter++;
				
				if (messageCounter>120) {
					messageCounter=0;
					messageOn=false;
				}
			}
		}*/
		
		
		
	}
	public void drawTitleScreen() {
		
		if(titlescreenState==0) {
			g2.setColor(new Color(255,249,248));
			g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
			
			g2.setFont(g2.getFont().deriveFont(Font.BOLD));
			String text="按下Enter鍵以開始遊戲。";
			int x=getXForCenterText(text);
			int y=gamePanel.tileSize*3;
			
			//shadow
			g2.setColor(Color.PINK);
			g2.drawString(text, x+2, y+2);
			
			//main color
			g2.setColor(Color.black);
			g2.drawString(text, x, y);
			
			//logo image
			x=gamePanel.screenWidth/2-gamePanel.tileSize;
			y+=gamePanel.tileSize*2;
			g2.drawImage(gamePanel.player.down1, x, y,gamePanel.tileSize*2,gamePanel.tileSize*2,null);
			
			//menu
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30));
			text="New Game";
			x=getXForCenterText(text);
			y+=gamePanel.tileSize*4;
			g2.drawString(text, x, y);
			if(commandNum==0) {
				g2.drawString(">", x-gamePanel.tileSize, y);
			}
			
			text="Load Game";
			x=getXForCenterText(text);
			y+=gamePanel.tileSize;
			g2.drawString(text, x, y);
			if(commandNum==1) {
				g2.drawString(">", x-gamePanel.tileSize, y);
			}
			
			text="Quit";
			x=getXForCenterText(text);
			y+=gamePanel.tileSize;
			g2.drawString(text, x, y);
			if(commandNum==2) {
				g2.drawString(">", x-gamePanel.tileSize, y);
			}
		}
		else if (titlescreenState==1) {
			g2.setColor(new Color(255,249,248));
			g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
			
			g2.setColor(Color.black);
			//引言
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,18));
			String text="從前從前，在遠的要命王國裡，住著一隻喜愛吃東西的泰迪熊。";
			int x=gamePanel.tileSize*1;
			int y=gamePanel.tileSize;
			g2.drawString(text, x, y);
			text= "它聽說台南有著很多很多的美食，於是它決定來到這個傳說中的美食之都。";
			y+=gamePanel.tileSize/2;
			g2.drawString(text, x, y);
			text="究竟它會在這裡遇到什麼冒險呢.......";
			y+=gamePanel.tileSize/2;
			g2.drawString(text, x, y);
			
			JTextField inputName = new JTextField(50);
			x+=gamePanel.tileSize*5;
			y+=gamePanel.tileSize/2;
			
			text="How to play";
			x=getXForCenterText(text);
			y+=gamePanel.tileSize;
			g2.drawString(text, x, y);
			if(commandNum==0) {
				g2.drawString(">", x-gamePanel.tileSize, y);
			}
			
			text="Back";
			x=getXForCenterText(text);
			y+=gamePanel.tileSize;
			g2.drawString(text, x, y);
			if(commandNum==1) {
				g2.drawString(">", x-gamePanel.tileSize, y);
			}
	
		}
		else if(titlescreenState==2) {
			g2.setColor(new Color(255,249,248));
			g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
			
			g2.setColor(Color.black);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,18));
			String text="早上好。";
			int x=gamePanel.tileSize*1;
			int y=gamePanel.tileSize;
			g2.drawString(text, x, y);
			
			
			
		}
	}
	public void drawPauseScreen() {
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text="PAUSED";
		int x,y;
		x=getXForCenterText(text);
		y=gamePanel.screenHeight/2;
		g2.drawString(text, x, y);
		
	}
	public void drawDialogueScreen() {
		
		//window
		int x=gamePanel.tileSize*2;
		int y=gamePanel.tileSize/2;
		int width=gamePanel.screenWidth-gamePanel.tileSize*4;
		int height=gamePanel.tileSize*3;
		
		drawSubWindow(x,y,width,height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		x+=gamePanel.tileSize;
		y+=gamePanel.tileSize;
		
		for(String line:currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+=40;
		}
		
	}
	public void drawSubWindow(int x,int y,int width,int height) {
		
		Color color=new Color(0,0,0,150);
		g2.setColor(color);
		g2.fillRoundRect(x, y, width, height,35,35);
		
		color=new Color(255,255,255);
		g2.setColor(color);
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	public int getXForCenterText(String text) {
		int textlength=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x=gamePanel.screenWidth/2-textlength/2;
		return x;
	}
	

}








