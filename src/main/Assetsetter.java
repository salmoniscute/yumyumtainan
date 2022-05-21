package main;

import entity.NPC_cadre;
import monster.MON_task;
import object.OBJ_food;

public class Assetsetter {
	
	GamePanel gamePanel;
	
	public Assetsetter(GamePanel gp) {
		this.gamePanel=gp;
	}
	
	public void setObject() {
		
		gamePanel.objects[0]=new OBJ_food(gamePanel);
		gamePanel.objects[0].worldX = 28*gamePanel.tileSize;
		gamePanel.objects[0].worldY=16*gamePanel.tileSize;
		
		gamePanel.objects[1]=new OBJ_food(gamePanel);
		gamePanel.objects[1].worldX = 30*gamePanel.tileSize;
		gamePanel.objects[1].worldY=22*gamePanel.tileSize;
		
	}
	public void setNPC() {
		gamePanel.npc[0]=new NPC_cadre(gamePanel);
		gamePanel.npc[0].worldX=gamePanel.tileSize*20;
		gamePanel.npc[0].worldY=gamePanel.tileSize*20;
		
	}
	
	public void setMonster() {
		gamePanel.monster[0]=new MON_task(gamePanel);
		gamePanel.monster[0].worldX=gamePanel.tileSize*23;
		gamePanel.monster[0].worldY=gamePanel.tileSize*23;
		gamePanel.monster[1]=new MON_task(gamePanel);
		gamePanel.monster[1].worldX=gamePanel.tileSize*25;
		gamePanel.monster[1].worldY=gamePanel.tileSize*25;
		
	}

}








