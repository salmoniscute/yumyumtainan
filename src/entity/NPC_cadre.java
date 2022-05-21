package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_cadre extends Entity{
	
	public NPC_cadre(GamePanel gp) {
		super(gp);
		direction="down";
		speed=1;
		solidArea.x=8;
		solidArea.y=16;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		getNPCImage();
		setDialogue();
	}
	public void getNPCImage() {
			
			down2=setup("/cadre/oldman_down_1",gamePanel.tileSize,gamePanel.tileSize);
			down3=setup("/cadre/oldman_down_2",gamePanel.tileSize,gamePanel.tileSize);
			
			up2=setup("/cadre/oldman_up_1",gamePanel.tileSize,gamePanel.tileSize);
			up3=setup("/cadre/oldman_up_2",gamePanel.tileSize,gamePanel.tileSize);
			
			left2=setup("/cadre/oldman_left_1",gamePanel.tileSize,gamePanel.tileSize);
			left3=setup("/cadre/oldman_left_2",gamePanel.tileSize,gamePanel.tileSize);
		
			right2=setup("/cadre/oldman_right_1",gamePanel.tileSize,gamePanel.tileSize);
			right3=setup("/cadre/oldman_right_2",gamePanel.tileSize,gamePanel.tileSize);
			
			
		}
	public void setAction() {
		
		actionLockCounter++;
		
		if (actionLockCounter==120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if (i<=25) {
				direction="up";
			}
			if (i>25&&i<-50) {
				direction="down";
			}
			if (i>50&&i<=75) {
				direction="left";
			}
			if (i>75&&i<=100) {
				direction="right";
			}
			actionLockCounter=0;
		}
		
		
	}
	public void setDialogue() {
		dialogues[0]="總召: 嗨，Teddybear。";
		dialogues[1]="總召: 歡迎參加系上活動。";
		dialogues[2]="總召: 請支付1000元活動費用。";
	}
	public void speak() {
		super.speak();
	}

}






