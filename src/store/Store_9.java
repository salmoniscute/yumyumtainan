package store;

import main.GamePanel;

public class Store_9 extends Store_super{
	
	public Store_9(GamePanel gp) {
		
		super(gp);
		name="| shweshwe隨隨東南亞主題小店 |";
		cost=160;
		worldX=gamePanel.tileSize*26;
		worldY=gamePanel.tileSize*26;
		
		photoX=gamePanel.tileSize*8+10;
		photoY=gamePanel.tileSize*6+8;
		
		
		setStoreImage();
		setStoreInfo();
		
	}
	
	public void setStoreImage() {
		photo1=setup("/store/store9",gamePanel.tileSize*4,gamePanel.tileSize*5);
		
	}
	public void setStoreInfo() {
		
		about= "座落在青年路上的可愛小店，採開放式座位，\n"
				+ "店外種植了許多綠植還有裝飾茅草，可以感受到島嶼風情。\n"
				+ "唯一的小小缺點是小蟲子因此比較多，但真的是小缺點！";
		
		item="\n"
				+ "#印尼炒泡麵\n"
				+ "炒泡麵口味比較重，怕鹹的記得另外配一些小點心或飲料。\n"
				+ "可以去櫃檯拿泰式辣醬加入拌勻，增添辣度之外還會有不一樣風味ㄛ！\n"
				+ "\n"
				+ "#香蕉煎餅\n"
				+ "香蕉煎餅很好吃😋推薦大家一定要點點看美祿口味～原本以為會超甜，結果完全不會！\n"
				+ "是香蕉本身天然的甜味配搭些許煉乳和微甜微苦的巧克力粉，味道結合得很好。\n"
				+ "\n"
				+ "#咖椰吐司\n"
				+ "中間的鹹奶油在咬下後立刻融化與吐司融合\n"
				+ "(建議大家要剛上桌時趁熱吃）\n"
				+ "鹹甜鹹甜交錯的味道就算單點也妥妥。";
				
		info="\n"
				+ "地址：台南市中西區青年路48號\n"
				+ "營業：12:00-20:00\n"
				+ "公休：週一\n"
				+ "價位：💰💰\n"
				+ "評分：🌕🌕🌕🌕🌑";
		
	}

}
