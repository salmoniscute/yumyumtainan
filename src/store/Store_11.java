package store;

import main.GamePanel;

public class Store_11 extends Store_super {
    public Store_11(GamePanel gp) {

        super(gp);
        name = "| 皮嚓先生 |";
        cost = 200;
        worldX = gamePanel.tileSize * 21;
        worldY = gamePanel.tileSize * 24;

        photoX = gamePanel.tileSize * 8;
        photoY = gamePanel.tileSize * 6;


        setStoreImage();
        setStoreInfo();

    }

    public void setStoreImage() {
        photo1 = setup("/store/store11", gamePanel.tileSize * 4, gamePanel.tileSize * 5);
    }

    public void setStoreInfo() {

        about = "位在中西區小巷內超可愛的義式料理，從裝飾看得出來店長很喜歡貓跟多肉植物啊～\n"
                + "以價位跟味道來說，完全是會想再二訪三訪的店！";

        item = "\n"
                + "#白酒皮蛋豚肉燉飯\n"
                + "燉飯皮蛋的香氣非常濃郁，微微的酒香帶來層次。\n"
                + "米飯的部分燉到很軟很入味，但還是保有分明的口感。是很新奇特別的燉飯口味！\n"
                + "\n"
                + "#泰式牛肉義大利麵\n"
                + "喜歡酸辣的朋友不要錯過！酸度非常非常足夠，是很清新的檸檬還有香料的酸，不會很膩。\n"
                + "牛肉的牛味很重，配上酸酸辣辣又濃郁的醬汁超搭～\n"
                + "\n"
                + "#芝士地瓜派\n"
                + "現烤上桌真的一陣濃郁的酥皮香。\n"
                + "地瓜內餡甜而不膩，有展現出食材的原味。\n";

        info = "\n"
                + "地址：台南市中西區中山路82巷6號\n"
                + "營業：11:00-14:30，17:00-20:30\n"
                + "公休：週日、週一\n"
                + "價位：💰💰\n"
                + "評分：🌕🌕🌕🌕🌗";

    }

}
