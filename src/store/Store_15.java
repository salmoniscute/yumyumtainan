package store;

import main.GamePanel;

public class Store_15 extends Store_super{
	public Store_15(GamePanel gp) {
		
		super(gp);
		name="| 福記肉圓 |";
		cost=60;
		worldX=gamePanel.tileSize*16;
		worldY=gamePanel.tileSize*25;
		
		photoX=gamePanel.tileSize*8;
		photoY=gamePanel.tileSize*5+25;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store15",gamePanel.tileSize*4,gamePanel.tileSize*5);
	}
	public void setStoreInfo() {
		
		about="這家是Bear踏溯台南的時候慕名前去嚐鮮的～\n"
				+ "所以離成大有點距離，建議搭乘大眾運輸工具或騎機車前往！\n"
				+ "十分推薦來一探傳統庶民小吃的魅力(˶‾᷄ ⁻̫ ‾᷅˵)";
		
		item="\n"
				+ "#肉圓\n"
				+ "有別於大部分肉圓QQ的外皮，福記肉圓的外皮是在來米米漿製成的，\n"
				+ "吃起來是像碗粿軟糯的口感，澱粉的香味也更加明顯。內餡豬肉很大塊飽滿，\n"
				+ "口感紮實且肉香十足，整體搭配著特調的甜甜醬汁，早餐來一碗真的特別享受～\n"
				+ "不愧是在地屹立不搖的四十年老店！\n";
				
		info="\n"
				+ "地址：台南市中西區府前路一段215號\n"
				+ "營業：6:30-18:30\n"
				+ "公休：週一\n"
				+ "價位：💰\n"
				+ "評分：🌕🌕🌕🌕🌑";
		
	}

}
