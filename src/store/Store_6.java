package store;

import main.GamePanel;

public class Store_6 extends Store_super {
    public Store_6(GamePanel gp) {

        super(gp);
        name = "| 開元路紅燒土魠魚羹  |";
        cost = 150;
        worldX = gamePanel.tileSize * 28;
        worldY = gamePanel.tileSize * 19;

        photoX = gamePanel.tileSize * 8 + 20;
        photoY = gamePanel.tileSize * 6;


        setStoreImage();
        setStoreInfo();

    }

    public void setStoreImage() {
        photo1 = setup("/store/store6", gamePanel.tileSize * 4, gamePanel.tileSize * 5);

    }

    public void setStoreInfo() {

        about = "之前因為害特斯拉總是當機而爆紅的土魠魚羹店，其實已經是在地屹立不搖30年的老店，\n"
                + "用餐人潮總是絡繹不絕，不過好在翻桌速度也非常快！";

        item = "\n"
                + "#紅燒土魠魚羹麵\n"
                + "不得不說炸土魠魚塊本身非常好吃！完全沒有腥味，炸衣的脆度也很夠，\n"
                + "即便泡在羹湯裡還是保有其口感和香氣。羹湯甜味的層次很突出，\n"
                + "可能有些北部口味的人會比較吃不習慣。想吃多一點土魠魚的人建議點羹就好！\n"
                + "\n"
                + "#肉燥飯\n"
                + "走肥肉路線的肉燥飯。\n"
                + "\n"
                + "#油豆腐\n"
                + "滷到很嫩的豆腐，好像有一個很推薦的吃法\n"
                + "是配上店家自製的辣椒水。當天試了覺得還好。\n";

        info = "\n"
                + "地址：台南市北區公園北路140-1號\n"
                + "營業：11:00-14:00，16:00-20:00\n"
                + "公休：無\n"
                + "價位：💰💰\n"
                + "評分：🌕🌕🌕🌕🌗";

    }

}
