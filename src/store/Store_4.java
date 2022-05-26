package store;

import main.GamePanel;

public class Store_4 extends Store_super {
	
	public Store_4(GamePanel gp) {
		
		super(gp);
		name="| 最初的地方 |";
		cost=250;
		worldX=gamePanel.tileSize*27;
		worldY=gamePanel.tileSize*21;
		
		photoX=gamePanel.tileSize*8;
		photoY=gamePanel.tileSize*6;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store4",gamePanel.tileSize*4,gamePanel.tileSize*5);
		
	}
	public void setStoreInfo() {
		
		about="這次來到了隱藏在小巷內的夯夯咖啡廳最初的地方，二樓是有點像是和房的裝潢，\n"
				+ "有榻榻米和暖和的日光，超適合三五好友來放鬆聊天～（不限時喔！）";
		
		item="\n"
				+ "#貝果拼盤\n"
				+ "貝果都有烤過所以香氣更凸顯，是屬於有嚼勁派的。口味不論是貝果或是果醬都很多種類，\n"
				+ "可以自己根據喜好組合，不無聊又吃不膩的拼盤很推薦大家點啊～\n"
				+ "\n"
				+ "#黑糖牛奶\n"
				+ "顏值超高的一杯！表層的黑糖被烤到稍微焦脆，攪拌均勻後整體有淡淡的黑糖香味，\n"
				+ "不會太甜，奶泡超濃又綿密的，好喝！\n"
				+ "\n"
				+ "#芝麻牛奶\n"
				+ "這杯只能做熱的喔～天冷來一杯真的很享受，\n"
				+ "即使有芝麻的香氣，奶香味還是很突出足夠。"
				+ "\n";
				
		info="\n"
				+ "地址：台南市中西區府前路一段122巷2號\n"
				+ "營業：11:30-18:30\n"
				+ "公休：周四\n"
				+ "價位：💰💰💰\n"
				+ "評分：🌕🌕🌕🌕🌗";
		
	}
	
}
