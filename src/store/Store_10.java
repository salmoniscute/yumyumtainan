package store;

import main.GamePanel;


public class Store_10 extends Store_super{
	
public Store_10(GamePanel gp) {
		
		super(gp);
		name="| 覺丸拉麵 |";
		cost=220;
		worldX=gamePanel.tileSize*27;
		worldY=gamePanel.tileSize*28;
		
		photoX=gamePanel.tileSize*8;
		photoY=gamePanel.tileSize*6;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store10",gamePanel.tileSize*4,gamePanel.tileSize*5);
		storeImage=setup("/store/house2",gamePanel.tileSize*2,gamePanel.tileSize*2);

	}
	public void setStoreInfo() {
		
		about="店內位子蠻少的，用餐時段常常要等。\n"
				+ "位子是圍繞著廚房的吧台，所以可以看到製作一碗拉麵的過程，蠻特別且公開透明的用餐環境！";
		
		item="\n"
				+ "#辣味豚骨拉麵\n"
				+ "麵跟叉燒的份量都蠻大的，吃完會很有飽足感。\n"
				+ "叉燒表現普通，雖然大塊但是有點柴，周圍的肉是軟嫩的但中間部分稍難咬開。\n"
				+ "湯頭部分覺得層次略微單薄。喜歡吃辣的可以點，吃完整個身體都熱起來！\n"
				+ "\n"
				+ "#鮮貝豚骨拉麵\n"
				+ "鮮貝拉麵不一樣的是湯中喝的到干貝的碎塊，整體湯頭的鹹味和鮮甜味的層次更加的凸顯。";
				
		info="\n"
				+ "地址：台南市東區東興路275號\n"
				+ "營業：11:00-14:00，17:00-20:00\n"
				+ "公休：週二\n"
				+ "價位：💰💰\n"
				+ "評分：🌕🌕🌕🌗🌑";
		
	}


}
