package store;

import main.GamePanel;

public class Store_5 extends Store_super{

	public Store_5(GamePanel gp) {
		
		super(gp);
		name="| 湧飯 |";
		cost=170;
		worldX=gamePanel.tileSize*26;
		worldY=gamePanel.tileSize*19;
		
		photoX=gamePanel.tileSize*8;
		photoY=gamePanel.tileSize*6;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store5",gamePanel.tileSize*4,gamePanel.tileSize*5);
		storeImage=setup("/store/house1",gamePanel.tileSize*2,gamePanel.tileSize*2);
	}
	public void setStoreInfo() {
		
		about="位在公園北路的小小店面，販售的是好吃健康的海南雞飯，cp值個人覺得蠻高的！";
		
		item="\n"
				+ "#椒麻雞腿飯\n"
				+ "雞腿排炸的很香脆，肉質是很漂亮的粉紅色，吃起來多汁又嫩，\n"
				+ "配上辣醬除了有解膩的效果，滋味又更加豐富。\n"
				+ "配菜很多樣，但味道還好。不過願意為了他的炸雞腿排回訪🤤\n"
				+ "\n"
				+ "#海南雞飯\n"
				+ "很特別的是有附上三種沾醬，分別是油蔥醬，醬油和酸酸辣辣的醬。"
				+ "\n";
				
		info="\n"
				+ "地址：台南市北區公園北路140-1號\n"
				+ "營業：11:00-14:00，16:00-20:00\n"
				+ "公休：無\n"
				+ "價位：💰💰\n"
				+ "評分：🌕🌕🌕🌕🌗";
		
	}
	
	

}
