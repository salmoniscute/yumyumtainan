package main;

import entity.NPC_cadre;
import monster.MON_task;
import object.OBJ_food;
import store.Store_1;
import store.Store_2;
import store.Store_3;
import store.Store_4;
import store.Store_5;
import store.Store_6;
import store.Store_7;

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
		gamePanel.monster[0].worldX=gamePanel.tileSize*35;
		gamePanel.monster[0].worldY=gamePanel.tileSize*40;
		gamePanel.monster[1]=new MON_task(gamePanel);
		gamePanel.monster[1].worldX=gamePanel.tileSize*35;
		gamePanel.monster[1].worldY=gamePanel.tileSize*42;
		
		
	}
	public void setStore() {
		gamePanel.store[0]=new Store_1(gamePanel);
		gamePanel.store[1]=new Store_2(gamePanel);
		gamePanel.store[2]=new Store_3(gamePanel);
		gamePanel.store[3]=new Store_4(gamePanel);
		gamePanel.store[4]=new Store_5(gamePanel);
		gamePanel.store[5]=new Store_6(gamePanel);
		gamePanel.store[6]=new Store_7(gamePanel);
		
	}

}








