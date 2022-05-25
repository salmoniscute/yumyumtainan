package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_task extends Entity{
	GamePanel gamePanel;
	
	public MON_task(GamePanel gamePanel) {
		
		super(gamePanel);
		this.gamePanel=gamePanel;
		
		name = "task";
		speed=5;
		type=2;
		
		solidArea.x=3;
		solidArea.y=18;
		solidArea.width=42;
		solidArea.height=30;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		maxLife=20;
		life=maxLife;
		
		getMONImage();
		setAction();
	}
	public void getMONImage() {
		up2=setup("/monster/task.back2",gamePanel.tileSize,gamePanel.tileSize);
		up3=setup("/monster/task.back3",gamePanel.tileSize,gamePanel.tileSize);
		down2=setup("/monster/task.front2",gamePanel.tileSize,gamePanel.tileSize);
		down3=setup("/monster/task.front3",gamePanel.tileSize,gamePanel.tileSize);
		left2=setup("/monster/task.left2",gamePanel.tileSize,gamePanel.tileSize);
		left3=setup("/monster/task.left3",gamePanel.tileSize,gamePanel.tileSize);
		right2=setup("/monster/task.right2",gamePanel.tileSize,gamePanel.tileSize);
		right3=setup("/monster/task.right3",gamePanel.tileSize,gamePanel.tileSize);
	}
	public void setAction() {
			actionLockCounter++;
			
			if (actionLockCounter==120) {
				Random random = new Random();
				int i = random.nextInt(100)+1;
				if (i<=25) {
					direction="up";
				}
				if (i>25&&i<=50) {
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
	public void damageReaction() {
		actionLockCounter=0;
		direction=gamePanel.player.direction;
	}

}







