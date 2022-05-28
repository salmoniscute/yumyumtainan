package store;

import main.GamePanel;

public class Store_12 extends Store_super{
	public Store_12(GamePanel gp) {
		
		super(gp);
		name="| 古城豆花庄 |";
		cost=110;
		worldX=gamePanel.tileSize*19;
		worldY=gamePanel.tileSize*24;
		
		photoX=gamePanel.tileSize*8-20;
		photoY=gamePanel.tileSize*6;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store12",gamePanel.tileSize*4,gamePanel.tileSize*5);
	}
	public void setStoreInfo() {
		
		about="講到台南的宵夜場絕對不會少了這家吧～\n"
				+ "不論是想吃甜的鹹的、冷的熱的，在古城豆花庄通通都找得到！";
		
		item="\n"
				+ "#紅豆麵茶豆花\n"
				+ "平常喜歡麵茶的人非常推薦試試看這碗，Bear真的超級愛（也可能是因為很甜）\n"
				+ "完全沒想過麵茶跟蜜紅豆的搭配可以這麼好吃(˶‾᷄ ⁻̫ ‾᷅˵)這家的豆花是走綿密路線的喔！\n"
				+ "\n"
				+ "#牛奶豆花\n"
				+ "豆花滑順綿密的口感，搭上濃醇的鮮奶，再加點有嚼勁的小粉圓完全就是絕配！\n"
				+ "不太喜歡吃太甜的Nora熊覺得很可以，雖然是有奶類製品的豆花但吃起來很清爽。\n";
				
		info="\n"
				+ "地址：台南市中西區尊王路82號\n"
				+ "營業：10:00-2:00\n"
				+ "公休：週三\n"
				+ "價位：💰💰\n"
				+ "評分：🌕🌕🌕🌕🌗";
		
	}
}
