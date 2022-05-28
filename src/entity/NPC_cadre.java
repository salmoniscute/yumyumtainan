package entity;

import java.util.Random;

import main.GamePanel;

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
			
			down2=setup("/cadre/r.front1",gamePanel.tileSize,gamePanel.tileSize);
			down3=setup("/cadre/r.front2",gamePanel.tileSize,gamePanel.tileSize);
			
			up2=setup("/cadre/r.back1",gamePanel.tileSize,gamePanel.tileSize);
			up3=setup("/cadre/r.back2",gamePanel.tileSize,gamePanel.tileSize);
			
			left2=setup("/cadre/r.left1",gamePanel.tileSize,gamePanel.tileSize);
			left3=setup("/cadre/r.left2",gamePanel.tileSize,gamePanel.tileSize);
		
			right2=setup("/cadre/r.right1",gamePanel.tileSize,gamePanel.tileSize);
			right3=setup("/cadre/r.right2",gamePanel.tileSize,gamePanel.tileSize);
			
			
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
		dialogues[0]="總召:\n嗨 "+gamePanel.player.playerName+"！\n很高興認識你。\n之後有活動的話一定要來參加喔！";
		dialogues[1]="總召:\n歡迎參加系上活動。\n請支付550元工人費用。";
		dialogues[2]="總召:\n怎麼了嗎？\n雖然有些事我沒必要一一告訴你，\n但對於活動有任何疑問還是歡迎來問我。";
		dialogues[3]="總召:\n明天就是二驗了，記得各組進度要到70趴！\n加油！";
		dialogues[4]="總召:\n由於疫情因素，系卡Ｋ將以線上模式舉行。";
		dialogues[5]="總召:\n（辦系卡Ｋ不小心噴掉6000元...)\n活動經費好像不太夠了...";
		dialogues[6]="總召:\n謝謝你願意來參加慶功宴。\n這邊支付你100元的快篩費用。";

	}
	public void speak() {
		super.speak();
	}

}






