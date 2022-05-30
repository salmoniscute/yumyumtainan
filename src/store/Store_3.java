package store;

import main.GamePanel;

public class Store_3 extends Store_super {

    public Store_3(GamePanel gp) {

        super(gp);
        name = "| 二空涼麵 |";
        cost = 120;
        worldX = gamePanel.tileSize * 25;
        worldY = gamePanel.tileSize * 24;

        photoX = gamePanel.tileSize * 8;
        photoY = gamePanel.tileSize * 6 + 10;


        setStoreImage();
        setStoreInfo();

    }

    public void setStoreImage() {
        photo1 = setup("/store/store3", gamePanel.tileSize * 4, gamePanel.tileSize * 5);

    }

    public void setStoreInfo() {

        about = "佇立在玉井老街盡頭的小店，店內牆上裝飾著滿滿從前顧客來店內用餐的老照片，\n"
                + "空間雖小但很溫馨。"
                + "來玉井別忘了來試試傳承五十年的眷村好滋味(˶‾᷄ ⁻̫ ‾᷅˵)";

        item = "\n"
                + "#涼麵\n"
                + "是屬於麻醬量比較少的涼麵，所以麵體表面不會大量包覆著濃濃的麻醬，吃起來也相對清爽。\n"
                + "店內有自助的調味區，可以自行調整自己想吃的口味，\n"
                + "當天就加了很多菜脯，酸酸辣辣的拌著涼麵很消暑～\n"
                + "\n"
                + "#麻辣鴨血\n"
                + "很推薦來這裡一定要點上一碗！鴨血跟豆腐都以高湯滷得很入味也很多汁。\n"
                + "不會到很辣很麻，不太吃辣的人還是可以點點看。"
                + "\n";

        info = "\n"
                + "地址：台南市玉井區中華路125號之1號\n"
                + "營業：11:00-18:00\n"
                + "公休：無\n"
                + "價位：💰\n"
                + "評分：🌕🌕🌕🌕🌑";

    }


}
