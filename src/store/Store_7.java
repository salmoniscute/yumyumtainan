package store;

import main.GamePanel;

public class Store_7 extends Store_super{
	public Store_7(GamePanel gp) {
		
		super(gp);
		name="| 唐家泡菜館  |";
		cost=200;
		worldX=gamePanel.tileSize*30;
		worldY=gamePanel.tileSize*20;
		
		photoX=gamePanel.tileSize*8+20;
		photoY=gamePanel.tileSize*6;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store7",gamePanel.tileSize*4,gamePanel.tileSize*5);
		
	}
	public void setStoreInfo() {
		
		about="在台南小有名氣的唐家泡菜館，熱愛泡菜的Bear 終於終於來踩點了\n"
				+ "離成大校區不遠！騎腳踏車十分鐘內到。\n"
				+ "生意真的很好但翻桌跟上菜速度都很快，不太需要等。";
		
		item="\n"
				+ "#泡菜三鮮炒飯\n"
				+ "滿驚豔的！店家自己做的泡菜酸酸甜甜的超好吃，辣度應該是所有人都可以接受的，基本上不辣～\n"
				+ "炒飯本身粒粒分明，配上泡菜的脆度的咬下多汁感超讚！\n"
				+ "三鮮分別是蝦子、透抽和肉絲，吃得出來海鮮很新鮮。\n"
				+ "\n"
				+ "#蛤蜊湯\n"
				+ "頗貴，蛤蜊的量也不多，但很新鮮。不太建議點，\n"
				+ "但拜託點他們家有泡菜的東西，超好吃ಥ_ಥ\n";
				
		info="\n"
				+ "地址：台南市東區裕農路18號\n"
				+ "營業：11:00-14:00，16:30-20:00\n"
				+ "公休：週日\n"
				+ "價位：💰💰\n"
				+ "評分：🌕🌕🌕🌕🌑";
		
	}

}
