package store;

import main.GamePanel;

public class Store_8 extends Store_super {

    public Store_8(GamePanel gp) {

        super(gp);
        name = "| 叔炸甜不辣媽 |";
        cost = 100;
        worldX = gamePanel.tileSize * 24;
        worldY = gamePanel.tileSize * 27;

        photoX = gamePanel.tileSize * 8;
        photoY = gamePanel.tileSize * 6;


        setStoreImage();
        setStoreInfo();

    }

    public void setStoreImage() {
        photo1 = setup("/store/store8", gamePanel.tileSize * 4, gamePanel.tileSize * 5);
        storeImage = setup("/store/house2", gamePanel.tileSize * 2, gamePanel.tileSize * 2);
    }

    public void setStoreInfo() {

        about = "位在中西區的超可愛文青風炸甜不辣專賣店，除了基本的椒鹽口味，\n"
                + "還有很多創意口味像是巧克力、炙燒起司等等，是下午茶跟宵夜的好選擇！";

        item = "\n"
                + "#炸甜不辣\n"
                + "撇除價格小貴這點，這真的是Bear 吃過最好吃的炸甜不辣！\n"
                + "是走薄切路線的，所以炸起來超酥脆，從入口的脆接下來是魚漿的鮮甜和嚼勁。\n"
                + "非常推梅粉口味，有機會想試試看其他特色口味。\n";

        info = "\n"
                + "地址：台南市中西區中正路93號\n"
                + "營業：15:00-22:00\n"
                + "公休：週三\n"
                + "價位：💰\n"
                + "評分：🌕🌕🌕🌕🌗";

    }


}
