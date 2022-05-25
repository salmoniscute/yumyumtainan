package store;

import main.GamePanel;

public class Store_1 extends Store_super {

	public Store_1(GamePanel gp) {
		
		super(gp);
		name="salmon";
		cost=300;
		worldX=gamePanel.tileSize*25;
		worldY=gamePanel.tileSize*20;
		
		solidArea.x=8;
		solidArea.y=16;
		solidAreaDefaultX=solidArea.x;
		solidAreaDefaultY=solidArea.y;
		solidArea.width=32;
		solidArea.height=32;
		
		collision=true;
		
		setStoreImage();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/object/door",gamePanel.tileSize*2,gamePanel.tileSize*2);
	}
	
	

}
