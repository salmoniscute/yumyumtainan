package store;

import main.GamePanel;

public class Store_14 extends Store_super {

	public Store_14(GamePanel gp) {
		
		super(gp);
		name="| 保安路米糕 |";
		cost=50;
		worldX=gamePanel.tileSize*17;
		worldY=gamePanel.tileSize*23;
		
		photoX=gamePanel.tileSize*8;
		photoY=gamePanel.tileSize*6;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store14",gamePanel.tileSize*4,gamePanel.tileSize*5);
		storeImage=setup("/store/house3",gamePanel.tileSize*2,gamePanel.tileSize*2);
	}
	public void setStoreInfo() {
		
		about="保安路米糕是在美食集中區台南國華街的傳統小吃，\n"
				+ "究竟是什麼懷舊古早味的魅力讓這家小小的店，\n"
				+ "得以在眾多競爭者的黃金地段屹立不搖將近一甲子呢？";
		
		item="\n"
				+ "#米糕\n"
				+ "米糕的米飯吃起來QQ糯糯的，粒粒分明。\n"
				+ "上頭淋有肉燥、肉鬆增添香氣跟口感，一旁有水煮花生還有可以解膩的小黃瓜。\n"
				+ "小份的份量蠻小的喔！\n"
				+ "\n"
				+ "#鴨蛋\n"
				+ "個人喜歡鴨蛋勝過他們家的米糕，滷的很入味。\n";
				
		info="\n"
				+ "地址：台南市中西區保安路16號\n"
				+ "營業：10:00-22:00\n"
				+ "公休：週三\n"
				+ "價位：💰\n"
				+ "評分：🌕🌕🌕🌗🌑";
		
	}

}
