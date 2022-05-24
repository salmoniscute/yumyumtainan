package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gp) {
		
		this.gamePanel = gp;
		
	}
	
	public void checkTile(Entity entity) {
		
		int entityLeftWorldX = entity.worldX+entity.solidArea.x;
		int entityRightWorldX = entity.worldX+entity.solidArea.x+entity.solidArea.width;
		int entityTopWorldY = entity.worldY+entity.solidArea.y;
		int entityBottomWorldY = entity.worldY+entity.solidArea.y+entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
		int entityRightCol = entityRightWorldX/gamePanel.tileSize;
		int entityTopRow = entityTopWorldY/gamePanel.tileSize;
		int entityBottomRow = entityBottomWorldY/gamePanel.tileSize;
		
		int tileNum1,tileNum2;
		
		switch (entity.direction) {
		
			case "up": {
				entityTopRow = (entityTopWorldY-entity.speed)/gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
				if(gamePanel.tileManager.tile[tileNum1].collision==true||gamePanel.tileManager.tile[tileNum2].collision==true) {
					entity.collisionOn=true;
				}
				
				break;
			}
			case "down": {
				entityBottomRow = (entityBottomWorldY+entity.speed)/gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
				tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
				if(gamePanel.tileManager.tile[tileNum1].collision==true||gamePanel.tileManager.tile[tileNum2].collision==true) {
					entity.collisionOn=true;
				}
				break;
			}
			case "left": {
				entityLeftCol = (entityLeftWorldX-entity.speed)/gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
				tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
				if(gamePanel.tileManager.tile[tileNum1].collision==true||gamePanel.tileManager.tile[tileNum2].collision==true) {
					entity.collisionOn=true;
				}
				break;
			}
			case "right": {
				entityRightCol = (entityRightWorldX+entity.speed)/gamePanel.tileSize;
				tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
				tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
				if(gamePanel.tileManager.tile[tileNum1].collision==true||gamePanel.tileManager.tile[tileNum2].collision==true) {
					entity.collisionOn=true;
				}
				break;
			}
		}
		
		
		
	}
	public int checkObject(Entity entity,boolean player) {
		
		int index=999;
		
		for(int i=0;i<gamePanel.objects.length;i++) {
			if(gamePanel.objects[i]!=null) {
				//get entity's solid area position
				entity.solidArea.x = entity.worldX+entity.solidArea.x;
				entity.solidArea.y = entity.worldY+entity.solidArea.y;
				//get object's solid area position
				gamePanel.objects[i].solidArea.x+=gamePanel.objects[i].worldX;
				gamePanel.objects[i].solidArea.y+=gamePanel.objects[i].worldY;
				
				switch (entity.direction) {
					case "up": {
						entity.solidArea.y-=entity.speed;
						break; 
					}
					case "down": {
						entity.solidArea.y+=entity.speed;
						break;
					}
					case "left": {
						entity.solidArea.x-=entity.speed;
						break;
					}
					case "right": {
						entity.solidArea.x+=entity.speed;
						break;
					}
				
				}
				if(entity.solidArea.intersects(gamePanel.objects[i].solidArea)) {
					if(gamePanel.objects[i].collision==true) {
						entity.collisionOn=true;
					}
					if(player==true) {
						index=i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gamePanel.objects[i].solidArea.x = gamePanel.objects[i].solidAreaDefaultX;
				gamePanel.objects[i].solidArea.y = gamePanel.objects[i].solidAreaDefaultY;
			
			}
		
		
		
		}
		return index;
	}
	
	public int checkEntity(Entity entity,Entity[] target) {
		int index=999;
		
		for(int i=0;i<target.length;i++) {
			if(target[i]!=null) {
				//get entity's solid area position
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				//get target's solid area position
				target[i].solidArea.x+=target[i].worldX;
				target[i].solidArea.y+=target[i].worldY;
				
				switch (entity.direction) {
					case "up": {
						entity.solidArea.y-=entity.speed;
						break; 
					}
					case "down": {
						entity.solidArea.y+=entity.speed;
						break;
					}
					case "left": {
						entity.solidArea.x-=entity.speed;
						break;
					}
					case "right": {
						entity.solidArea.x+=entity.speed;
						break;
					}
					
				
				}
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if(target[i]!=entity) {
						entity.collisionOn=true;
						index=i;
					}
					
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			
			}
		
		
		
		}
		return index;
	}
	public void checkPlayer(Entity entity) {
		
		entity.solidArea.x = entity.worldX+entity.solidArea.x;
		entity.solidArea.y = entity.worldY+entity.solidArea.y;
		//get target's solid area position
		gamePanel.player.solidArea.x+=gamePanel.player.worldX;
		gamePanel.player.solidArea.y+=gamePanel.player.worldY;
		
		switch (entity.direction) {
			case "up": {entity.solidArea.y-=entity.speed;break; }
			case "down": {entity.solidArea.y+=entity.speed;break;}
			case "left": {entity.solidArea.x-=entity.speed;break;}
			case "right": {entity.solidArea.x+=entity.speed;break;}
		}
		if(entity.solidArea.intersects(gamePanel.player.solidArea)) {
			entity.collisionOn=true;
			
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
		gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
	
	}
		
	

}












