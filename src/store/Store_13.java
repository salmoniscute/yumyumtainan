package store;

import main.GamePanel;

public class Store_13 extends Store_super {
    public Store_13(GamePanel gp) {

        super(gp);
        name = "| 雙生綠豆沙牛奶 |";
        cost = 80;
        worldX = gamePanel.tileSize * 20;
        worldY = gamePanel.tileSize * 22;

        photoX = gamePanel.tileSize * 8;
        photoY = gamePanel.tileSize * 6;


        setStoreImage();
        setStoreInfo();

    }

    public void setStoreImage() {
        photo1 = setup("/store/store13", gamePanel.tileSize * 4, gamePanel.tileSize * 5);
    }

    public void setStoreInfo() {

        about = "來台南基本上不會錯過的一家店～很多時候要喝到一杯綠豆沙牛奶得排隊抽號碼牌！\n"
                + "不過這次去五分鐘內就美食到手了，當天是週五差不多下午三點半給各位參考！";

        item = "\n"
                + "#綠豆沙牛奶\n"
                + "綠豆沙很綿密，不會死甜，但又不致於蓋過牛奶的香氣，\n"
                + "兩個元素的味道都夠濃郁。很好喝！\n"
                + "加珍珠的話見仁見智，個人覺得珍珠的黑糖味稍微搶過綠豆沙牛奶本身的風采，\n"
                + "但同行小夥伴覺得加珍珠口感更豐富而且別有一番滋味。\n";

        info = "\n"
                + "地址：台南市中西區民族路二段281號\n"
                + "營業：11:00-18:00\n"
                + "公休：週一、週二\n"
                + "價位：💰\n"
                + "評分：🌕🌕🌕🌕🌗";

    }
}
