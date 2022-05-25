package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
	
	KeyHandler keyHandler;
	
	//player state
	public String playerName="";
	public int hasMoney=0;
	
	public final int screenX;
	public final int screenY;
	
	
	public Player(GamePanel gp,KeyHandler keyH) {
		
		super(gp);
		this.gamePanel=gp;
		this.keyHandler = keyH;
		
		screenX=gp.screenWidth/2-gp.tileSize/2;
		screenY=gp.screenHeight/2-gp.tileSize/2;
		
		solidArea = new Rectangle();
		solidArea.x=8;
		solidArea.y=16;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		
		attackArea.width=36;
		attackArea.height=36;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		
	}
	
	public void setDefaultValues() {
		worldX=gamePanel.tileSize*23;
		worldY=gamePanel.tileSize*21;
		speed = 4;
		direction="down";
		
		hasMoney=1000;
		
	}
	
	public void getPlayerImage() {
		
		down1=setup("/player/front1",gamePanel.tileSize,gamePanel.tileSize);
		down2=setup("/player/front2",gamePanel.tileSize,gamePanel.tileSize);
		down3=setup("/player/front3",gamePanel.tileSize,gamePanel.tileSize);
		up1=setup("/player/back1",gamePanel.tileSize,gamePanel.tileSize);
		up2=setup("/player/back2",gamePanel.tileSize,gamePanel.tileSize);
		up3=setup("/player/back3",gamePanel.tileSize,gamePanel.tileSize);
		left1=setup("/player/left1",gamePanel.tileSize,gamePanel.tileSize);
		left2=setup("/player/left2",gamePanel.tileSize,gamePanel.tileSize);
		left3=setup("/player/left3",gamePanel.tileSize,gamePanel.tileSize);
		right1=setup("/player/right1",gamePanel.tileSize,gamePanel.tileSize);
		right2=setup("/player/right2",gamePanel.tileSize,gamePanel.tileSize);
		right3=setup("/player/right3",gamePanel.tileSize,gamePanel.tileSize);
		
	}
	public void getPlayerAttackImage() {
		
		attackUp1=setup("/player/boy_attack_up_1",gamePanel.tileSize,gamePanel.tileSize*2);
		attackUp2=setup("/player/boy_attack_up_2",gamePanel.tileSize,gamePanel.tileSize*2);
		attackDown1=setup("/player/boy_attack_down_1",gamePanel.tileSize,gamePanel.tileSize*2);
		attackDown2=setup("/player/boy_attack_down_2",gamePanel.tileSize,gamePanel.tileSize*2);
		attackLeft1=setup("/player/boy_attack_left_1",gamePanel.tileSize*2,gamePanel.tileSize);
		attackLeft2=setup("/player/boy_attack_left_2",gamePanel.tileSize*2,gamePanel.tileSize);
		attackRight1=setup("/player/boy_attack_right_1",gamePanel.tileSize*2,gamePanel.tileSize);
		attackRight2=setup("/player/boy_attack_right_2",gamePanel.tileSize*2,gamePanel.tileSize);
		
	}

	
	public void update() {
		
		if(attacking==true) {
			attacking();
		}
		
		else if(keyHandler.downPressed==true||keyHandler.upPressed==true||
				keyHandler.leftPressed==true||keyHandler.rightPressed ==true||keyHandler.enterPressed==true) {
			if(keyHandler.upPressed == true) {
				direction="up";
				
			}
			else if(keyHandler.downPressed == true) {
				direction="down";
				
			}
			else if(keyHandler.rightPressed == true) {
				direction="right";		
				
			}
			else if(keyHandler.leftPressed == true) {
				direction="left";
				
				
			}
			
			//check tile collision
			
			collisionOn=false;
			gamePanel.collisionChecker.checkTile(this);
			
			//ckeck object collision
			
			int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
			pickUpObject(objectIndex);
			
			//check store collision
			int storeIndex=gamePanel.collisionChecker.checkStore(this, true);
			if(keyHandler.enterPressed==true) {
				openStore(storeIndex);
			}
			
			
			//check npc collision
			int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
			interactNPC(npcIndex);
			
			//check monster collision
			int monsterIndex=gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
			
			//
			
			if(collisionOn == false && keyHandler.enterPressed == false) {
				switch (direction) {
					case "up": {
						worldY-=speed;
						break;
					}
					case "down": {
						worldY+=speed;
						break;
					}
					case "left": {
						worldX-=speed;
						break;
					}
					case "right": {
						worldX+=speed;
						break;
					}
				}
				
			}
			gamePanel.keyHandler.enterPressed=false;
			
			spriteCounter++;
			if(spriteCounter>20) {
				if(spriteNum==1) {
					spriteNum=2;
				}
				else if (spriteNum==2) {
					spriteNum=1;
				}
				spriteCounter=0;
			}
		}
		else {
			if(direction=="up"){
				direction="stop_up";
			}
			else if (direction=="down") {
				direction = "stop_down";
			}
			else if (direction=="left") {
				direction="stop_left";
				
			}
			else if(direction=="right"){
				direction="stop_right";
			}
			
			
		}
		
		
		
	}
	public void attacking () {
		spriteCounter++;
		if(spriteCounter<=5) {
			spriteNum=1;
		}
		if(spriteCounter>5&&spriteCounter<=25) {
			spriteNum=2;
			
			//save the current
			int currentWorldX=worldX;
			int currentWorldY=worldY;
			int solidAreaWidth=solidArea.width;
			int solidAreaHeight=solidArea.height;
			//adjust player for the attack area
			switch (direction) {
			case "up": {worldY-=attackArea.height;break;}
			case "down":{worldY+=attackArea.height;break;}
			case "left":{worldX-=attackArea.width;break;}
			case "right":{worldX+=attackArea.height;break;}
			}
			//attack area become solid area
			solidArea.width=attackArea.width;
			solidArea.height=attackArea.height;
			
			int monsterIndex=gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
			damageMonster(monsterIndex);
			
			//after checking collision ,restore
			worldX=currentWorldX;
			worldY=currentWorldY;
			solidArea.width=solidAreaWidth;
			solidArea.height=solidAreaHeight;
		}
		if(spriteCounter>25) {
			spriteCounter=0;
			spriteNum=1;
			attacking=false;
		}
	}
	
	public void pickUpObject(int index) {
		
		if (index!=999) {
			String objectName = gamePanel.objects[index].name;
			switch (objectName) {
				case "honey": {
					hasMoney+=100;
					gamePanel.objects[index]=null;
					gamePanel.ui.showMessage("You got some food!");
					break;
				}
				
			}
			/*if (hasMoney<0) {
				gamePanel.ui.gameFinished=true;
				
			}*/
			
		}
		
	}
	
	public void interactNPC(int i) {
		
		if(gamePanel.keyHandler.enterPressed==true) {
			if (i!=999) {
				
					gamePanel.gameState=gamePanel.dialogueState;
					gamePanel.npc[i].speak();
				
			}
			/*else {
					attacking=true;
			}*/
		}
		gamePanel.keyHandler.enterPressed=false;
	}
	public void damageMonster(int i) {
		
		if(i!=999) {
			if(gamePanel.monster[i].invincible==false) {
				gamePanel.monster[i].life-=1;
				gamePanel.monster[i].invincible=true;
				gamePanel.monster[i].damageReaction();
				
				if(gamePanel.monster[i].life<=0) {
					gamePanel.monster[i].dying=true;
				}
			}
			
			
		}
		
	}
	public void openStore(int i) {
		
		if(i!=999) {
			gamePanel.gameState=gamePanel.storeState;
			gamePanel.ui.storeNum=i;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int tempScreenX=screenX;
		int tempScreenY=screenY;
		
		switch (direction) {
			case "up": {
				if(attacking ==false) {
					if(spriteNum==1) {image=up2;}
					if(spriteNum==2) {image=up3;}
				}
				if(attacking ==true){
					tempScreenY-=gamePanel.tileSize;
					if(spriteNum==1) {image=attackUp1;}
					if(spriteNum==2) {image=attackUp2;}
				}
				break;
			}
			case "down": {
				if(attacking ==false) {
					if(spriteNum==1) {image=down2;}
					if(spriteNum==2) {image=down3;}
				}
				if(attacking ==true) {
					if(spriteNum==1) {image=attackDown1;}
					if(spriteNum==2) {image=attackDown2;}
				}
				break;
					}
			case "left": {
				if(attacking ==false) {
					if(spriteNum==1) {image=left2;}
					if(spriteNum==2) {image=left3;}
				}
				if(attacking ==true) {
					tempScreenX-=gamePanel.tileSize;
					if(spriteNum==1) {image=attackLeft1;}
					if(spriteNum==2) {image=attackLeft2;}
				}
				break;
			}
			case "right": {
				if(attacking ==false) {
					if(spriteNum==1) {image=right2;}
					if(spriteNum==2) {image=right3;}
				}
				if(attacking ==true) {
					if(spriteNum==1) {image=attackRight1;}
					if(spriteNum==2) {image=attackRight2;}
				}
				
				break;
			}
			case "stop_up": {image = up1;
				break;
			}
			case "stop_down": {image = down1;
				break;
			}
			case "stop_left": {image=left1;
				break;
			}
			case "stop_right": {image = right1;
				break;
			}
			
		
		
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY,  null);
	}

}








