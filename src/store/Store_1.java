package store;

import main.GamePanel;

public class Store_1 extends Store_super {

    public Store_1(GamePanel gp) {

        super(gp);
        name = "| 葉明致麵舖 |";
        cost = 170;
        worldX = gamePanel.tileSize * 25;
        worldY = gamePanel.tileSize * 20;

        photoX = gamePanel.tileSize * 6;
        photoY = gamePanel.tileSize * 6;


        setStoreImage();
        setStoreInfo();

    }

    public void setStoreImage() {
        photo1 = setup("/store/store1", gamePanel.tileSize * 4, gamePanel.tileSize * 5);

    }

    public void setStoreInfo() {

        about = "隱身在巷內的超美麵鋪，屋內採天花板挑高、許多木製及金屬裝潢的歐式老工業風。\n"
                + "在如此漂亮的店裡吃著平價美味的麵實在是很特別的體驗，很推薦各位來看看～";

        item = "\n"
                + "#驚魂手打麵\n"
                + "另外加點溏心蛋的話小碗是70元，份量以一般麵店的小碗來說蠻大份的，但價位也因此稍高。\n"
                + "不過整體覺的不錯：醬料的鹹淡剛好，拌上口感Q彈的手打麵吃起來十分涮嘴，樸實簡單的好味道。\n"
                + "\n"
                + "#麻醬麵\n"
                + "麻醬很濃郁，味道偏重，但又不會過濃導致難拌開跟口感上乾乾的，是很滑順香氣足夠的麻醬。"
                + "\n";

        info = "\n"
                + "地址：台南市中西區青年路\n"
                + "營業：10:30-21:00\n"
                + "公休：週一\n"
                + "價位：💰💰\n"
                + "評分：🌕🌕🌕🌕🌑";

    }


}
