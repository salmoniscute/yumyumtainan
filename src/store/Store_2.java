package store;

import main.GamePanel;

public class Store_2 extends Store_super{
	
	public Store_2(GamePanel gp) {
		
		super(gp);
		name="| 鳳姐鴨肉飯 |";
		cost=200;
		worldX=gamePanel.tileSize*25;
		worldY=gamePanel.tileSize*22;
		
		photoX=gamePanel.tileSize*7;
		photoY=gamePanel.tileSize*7-20;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store2",gamePanel.tileSize*3+20,gamePanel.tileSize*4+20);
		
	}
	public void setStoreInfo() {
		
		about="\n"
				+ "終於來踩點這家夯到不行的鴨肉飯了( ´▽` )ﾉ而且當天超幸運，排了超久的隊還好有排到鴨腿飯！\n"
				+ "（真的差一點就要向隅了）到底是否如傳聞中好吃呢～一起來看看吧！";
		
		item="\n"
				+ "#鴨肉飯\n"
				+ "米飯上淋有應該是鴨油之類的醬，真的是畫龍點睛，配上鴨肉，入口充滿油脂的香氣。\n"
				+ "帶有點鹹味的鴨肉拌上甜甜的醬，整體的味道讓人忍不住一口接一口！\n"
				+ "一旁有筍乾跟梅干菜作不同口味的搭配。\n"
				+ "\n"
				+ "#鴨腿飯\n"
				+ "鴨腿飯有額外附半顆滷蛋跟油豆腐，配料更豐富。\n"
				+ "打開盒蓋根本是視覺衝擊，鴨腿幾乎整個覆蓋住底下的飯菜，刀功很好，切的粗細入口剛剛好。"
				+ "\n";
				
		info="\n"
				+ "地址：台南市中西區民族路三段190號\n"
				+ "營業：11:00-18:30\n"
				+ "公休：週三、週四\n"
				+ "價位：💰💰\n"
				+ "評分：🌕🌕🌕🌕🌗";
		
	}
	
	

}
