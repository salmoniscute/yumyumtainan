package main;

import entity.NPC_cadre;
import monster.MON_task;
import object.OBJ_food;
import store.*;

public class Assetsetter {


    GamePanel gamePanel;

    public Assetsetter(GamePanel gp) {
        this.gamePanel = gp;
    }

    public void setObject() {

        gamePanel.objects[0] = new OBJ_food(gamePanel);
        gamePanel.objects[0].worldX = 28 * gamePanel.tileSize;
        gamePanel.objects[0].worldY = 16 * gamePanel.tileSize;

        gamePanel.objects[1] = new OBJ_food(gamePanel);
        gamePanel.objects[1].worldX = 30 * gamePanel.tileSize;
        gamePanel.objects[1].worldY = 22 * gamePanel.tileSize;

    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_cadre(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize * 20;
        gamePanel.npc[0].worldY = gamePanel.tileSize * 20;

    }

    public void setMonster() {
        gamePanel.monster[0] = new MON_task(gamePanel);
        gamePanel.monster[0].worldX = gamePanel.tileSize * 35;
        gamePanel.monster[0].worldY = gamePanel.tileSize * 40;
        gamePanel.monster[1] = new MON_task(gamePanel);
        gamePanel.monster[1].worldX = gamePanel.tileSize * 35;
        gamePanel.monster[1].worldY = gamePanel.tileSize * 42;
        gamePanel.monster[2] = new MON_task(gamePanel);
        gamePanel.monster[2].worldX = gamePanel.tileSize * 35;
        gamePanel.monster[2].worldY = gamePanel.tileSize * 43;
        gamePanel.monster[3] = new MON_task(gamePanel);
        gamePanel.monster[3].worldX = gamePanel.tileSize * 35;
        gamePanel.monster[3].worldY = gamePanel.tileSize * 44;
        gamePanel.monster[4] = new MON_task(gamePanel);
        gamePanel.monster[4].worldX = gamePanel.tileSize * 35;
        gamePanel.monster[4].worldY = gamePanel.tileSize * 45;
        gamePanel.monster[5] = new MON_task(gamePanel);
        gamePanel.monster[5].worldX = gamePanel.tileSize * 20;
        gamePanel.monster[5].worldY = gamePanel.tileSize * 21;


    }

    public void setStore() {
        gamePanel.store[0] = new Store(gamePanel);
        gamePanel.store[0].setAll("| 葉明致麵舖 |", 170, 18, 26, gamePanel.tileSize * 6, gamePanel.tileSize * 6, "/store/house1");
        gamePanel.store[0].loadPhoto(0);
        gamePanel.store[0].setStoreInfo(
                "隱身在巷內的超美麵鋪，屋內採天花板挑高、許多木製及金屬裝潢的歐式老工業風。\n"
                + "在如此漂亮的店裡吃著平價美味的麵實在是很特別的體驗，很推薦各位來看看～",

                "\n"
                + "#驚魂手打麵\n"
                + "另外加點溏心蛋的話小碗是70元，份量以一般麵店的小碗來說蠻大份的，但價位也因此稍高。\n"
                + "不過整體覺的不錯：醬料的鹹淡剛好，拌上口感Q彈的手打麵吃起來十分涮嘴，樸實簡單的好味道。\n"
                + "\n"
                + "#麻醬麵\n"
                + "麻醬很濃郁，味道偏重，但又不會過濃導致難拌開跟口感上乾乾的，是很滑順香氣足夠的麻醬。"
                + "\n",

                "\n"
                + "地址：台南市中西區青年路\n"
                + "營業：10:30-21:00\n"
                + "公休：週一\n"
                + "價位：💰💰\n"
                + "評分：🌕🌕🌕🌕🌑");

        gamePanel.store[1] = new Store(gamePanel);
        gamePanel.store[1].setAll("| 鳳姐鴨肉飯 |", 200, 25, 22, "/store/house1");
        gamePanel.store[1].loadPhoto(1);
        gamePanel.store[1].setStoreInfo(
                "終於來踩點這家夯到不行的鴨肉飯了( ´▽` )ﾉ而且當天超幸運，排了超久的隊還好有排到鴨腿飯！\n"
                        + "（真的差一點就要向隅了）到底是否如傳聞中好吃呢～一起來看看吧！",

                "\n"
                        + "#鴨肉飯\n"
                        + "米飯上淋有應該是鴨油之類的醬，真的是畫龍點睛，配上鴨肉，入口充滿油脂的香氣。\n"
                        + "帶有點鹹味的鴨肉拌上甜甜的醬，整體的味道讓人忍不住一口接一口！\n"
                        + "一旁有筍乾跟梅干菜作不同口味的搭配。\n"
                        + "\n"
                        + "#鴨腿飯\n"
                        + "鴨腿飯有額外附半顆滷蛋跟油豆腐，配料更豐富。\n"
                        + "打開盒蓋根本是視覺衝擊，\n"
                        + "鴨腿幾乎整個覆蓋住底下的飯菜，\n"
                        + "刀功很好，切的粗細入口剛剛好。"
                        + "\n",

                "\n"
                        + "地址：台南市中西區民族路三段190號\n"
                        + "營業：11:00-18:30\n"
                        + "公休：週三、週四\n"
                        + "價位：💰💰\n"
                        + "評分：🌕🌕🌕🌕🌗");



        gamePanel.store[2] = new Store(gamePanel);
        gamePanel.store[2].setAll("| 二空涼麵 |", 120, 25, 24, "x", 10, "/store/house1");
        gamePanel.store[2].loadPhoto(2);
        gamePanel.store[2].setStoreInfo(
                "佇立在玉井老街盡頭的小店，店內牆上裝飾著滿滿從前顧客來店內用餐的老照片，\n"
                        + "空間雖小但很溫馨。"
                        + "來玉井別忘了來試試傳承五十年的眷村好滋味(˶‾᷄ ⁻̫ ‾᷅˵)",

                "\n"
                        + "#涼麵\n"
                        + "是屬於麻醬量比較少的涼麵，所以麵體表面不會大量包覆著濃濃的麻醬，吃起來也相對清爽。\n"
                        + "店內有自助的調味區，可以自行調整自己想吃的口味，\n"
                        + "當天就加了很多菜脯，酸酸辣辣的拌著涼麵很消暑～\n"
                        + "\n"
                        + "#麻辣鴨血\n"
                        + "很推薦來這裡一定要點上一碗！鴨血跟豆腐都以高湯滷得很入味也很多汁。\n"
                        + "不會到很辣很麻，不太吃辣的人還是可以點點看。"
                        + "\n",

                "\n"
                        + "地址：台南市玉井區中華路125號之1號\n"
                        + "營業：11:00-18:00\n"
                        + "公休：無\n"
                        + "價位：💰\n"
                        + "評分：🌕🌕🌕🌕🌑");


        gamePanel.store[3] = new Store(gamePanel);
        gamePanel.store[3].setAll("| 最初的地方 |", 250, 27, 21, "/store/house1");
        gamePanel.store[3].loadPhoto(3);
        gamePanel.store[3].setStoreInfo(
                "這次來到了隱藏在小巷內的夯夯咖啡廳最初的地方，二樓是有點像是和房的裝潢，\n"
                        + "有榻榻米和暖和的日光，超適合三五好友來放鬆聊天～（不限時喔！）",

                "\n"
                        + "#貝果拼盤\n"
                        + "貝果都有烤過所以香氣更凸顯，是屬於有嚼勁派的。口味不論是貝果或是果醬都很多種類，\n"
                        + "可以自己根據喜好組合，不無聊又吃不膩的拼盤很推薦大家點啊～\n"
                        + "\n"
                        + "#黑糖牛奶\n"
                        + "顏值超高的一杯！表層的黑糖被烤到稍微焦脆，攪拌均勻後整體有淡淡的黑糖香味，\n"
                        + "不會太甜，奶泡超濃又綿密的，好喝！\n"
                        + "\n"
                        + "#芝麻牛奶\n"
                        + "這杯只能做熱的喔～天冷來一杯真的很享受，\n"
                        + "即使有芝麻的香氣，奶香味還是很突出足夠。"
                        + "\n",

                "\n"
                        + "地址：台南市中西區府前路一段122巷2號\n"
                        + "營業：11:30-18:30\n"
                        + "公休：周四\n"
                        + "價位：💰💰💰\n"
                        + "評分：🌕🌕🌕🌕🌗");


        gamePanel.store[4] = new Store(gamePanel);
        gamePanel.store[4].setAll("| 湧飯 |", 170, 26, 19, "/store/house1");
        gamePanel.store[4].setStoreInfo(
                "位在公園北路的小小店面，販售的是好吃健康的海南雞飯，cp值個人覺得蠻高的！",

                "\n"
                        + "#椒麻雞腿飯\n"
                        + "雞腿排炸的很香脆，肉質是很漂亮的粉紅色，吃起來多汁又嫩，\n"
                        + "配上辣醬除了有解膩的效果，滋味又更加豐富。\n"
                        + "配菜很多樣，但味道還好。不過願意為了他的炸雞腿排回訪🤤\n"
                        + "\n"
                        + "#海南雞飯\n"
                        + "很特別的是有附上三種沾醬，分別是油蔥醬，醬油和酸酸辣辣的醬。"
                        + "\n",

                "\n"
                        + "地址：台南市北區公園北路140-1號\n"
                        + "營業：11:00-14:00，16:00-20:00\n"
                        + "公休：無\n"
                        + "價位：💰💰\n"
                        + "評分：🌕🌕🌕🌕🌗");


        gamePanel.store[5] = new Store(gamePanel);
        gamePanel.store[5].setAll("| 開元路紅燒土魠魚羹  |", 150, 28, 19, "y", 20, "/store/house2");
        gamePanel.store[5].setStoreInfo(
                "之前因為害特斯拉總是當機而爆紅的土魠魚羹店，其實已經是在地屹立不搖30年的老店，\n"
                        + "用餐人潮總是絡繹不絕，不過好在翻桌速度也非常快！",

                "\n"
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
                        + "是配上店家自製的辣椒水。當天試了覺得還好。\n",

                "\n"
                        + "地址：台南市北區公園北路140-1號\n"
                        + "營業：11:00-14:00，16:00-20:00\n"
                        + "公休：無\n"
                        + "價位：💰💰\n"
                        + "評分：🌕🌕🌕🌕🌗");


        gamePanel.store[6] = new Store(gamePanel);
        gamePanel.store[6].setAll("| 唐家泡菜館  |", 200, 30, 20, "x", 20, "/store/house2");
        gamePanel.store[6].setStoreInfo(
                "在台南小有名氣的唐家泡菜館，熱愛泡菜的Bear 終於終於來踩點了\n"
                        + "離成大校區不遠！騎腳踏車十分鐘內到。\n"
                        + "生意真的很好但翻桌跟上菜速度都很快，不太需要等。",

                "\n"
                        + "#泡菜三鮮炒飯\n"
                        + "滿驚豔的！店家自己做的泡菜酸酸甜甜的超好吃，辣度應該是所有人都可以接受的，基本上不辣～\n"
                        + "炒飯本身粒粒分明，配上泡菜的脆度的咬下多汁感超讚！\n"
                        + "三鮮分別是蝦子、透抽和肉絲，吃得出來海鮮很新鮮。\n"
                        + "\n"
                        + "#蛤蜊湯\n"
                        + "頗貴，蛤蜊的量也不多，但很新鮮。不太建議點，\n"
                        + "但拜託點他們家有泡菜的東西，超好吃ಥ_ಥ\n",

                "\n"
                        + "地址：台南市東區裕農路18號\n"
                        + "營業：11:00-14:00，16:30-20:00\n"
                        + "公休：週日\n"
                        + "價位：💰💰\n"
                        + "評分：🌕🌕🌕🌕🌑");


        gamePanel.store[7] = new Store(gamePanel);
        gamePanel.store[7].setAll("| 叔炸甜不辣媽 |", 100, 24, 27, "/store/house2");
        gamePanel.store[7].setStoreInfo(
                "位在中西區的超可愛文青風炸甜不辣專賣店，除了基本的椒鹽口味，\n"
                        + "還有很多創意口味像是巧克力、炙燒起司等等，是下午茶跟宵夜的好選擇！",

                "\n"
                        + "#炸甜不辣\n"
                        + "撇除價格小貴這點，這真的是Bear 吃過最好吃的炸甜不辣！\n"
                        + "是走薄切路線的，所以炸起來超酥脆，從入口的脆接下來是魚漿的鮮甜和嚼勁。\n"
                        + "非常推梅粉口味，有機會想試試看其他特色口味。\n",

                "\n"
                        + "地址：台南市中西區中正路93號\n"
                        + "營業：15:00-22:00\n"
                        + "公休：週三\n"
                        + "價位：💰\n"
                        + "評分：🌕🌕🌕🌕🌗");


        gamePanel.store[8] = new Store(gamePanel);
        gamePanel.store[8].setAll("| shweshwe隨隨東南亞主題小店 |", 160, 26, 26, "xy", 10, 8, "/store/house2");
        gamePanel.store[8].setStoreInfo(
                "座落在青年路上的可愛小店，採開放式座位，\n"
                        + "店外種植了許多綠植還有裝飾茅草，可以感受到島嶼風情。\n"
                        + "唯一的小小缺點是小蟲子因此比較多，但真的是小缺點！",

                "\n"
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
                        + "鹹甜鹹甜交錯的味道就算單點也妥妥。",

                "\n"
                        + "地址：台南市中西區青年路48號\n"
                        + "營業：12:00-20:00\n"
                        + "公休：週一\n"
                        + "價位：💰💰\n"
                        + "評分：🌕🌕🌕🌕🌑");


        gamePanel.store[9] = new Store(gamePanel);
        gamePanel.store[9].setAll("| 覺丸拉麵 |", 220, 27, 28, "/store/house2");
        gamePanel.store[9].setStoreInfo(
                "店內位子蠻少的，用餐時段常常要等。\n"
                        + "位子是圍繞著廚房的吧台，所以可以看到製作一碗拉麵的過程，蠻特別且公開透明的用餐環境！",

                "\n"
                        + "#辣味豚骨拉麵\n"
                        + "麵跟叉燒的份量都蠻大的，吃完會很有飽足感。\n"
                        + "叉燒表現普通，雖然大塊但是有點柴，周圍的肉是軟嫩的但中間部分稍難咬開。\n"
                        + "湯頭部分覺得層次略微單薄。喜歡吃辣的可以點，吃完整個身體都熱起來！\n"
                        + "\n"
                        + "#鮮貝豚骨拉麵\n"
                        + "鮮貝拉麵不一樣的是湯中喝的到干貝的碎塊，整體湯頭的鹹味和鮮甜味的層次更加的凸顯。",

                "\n"
                        + "地址：台南市東區東興路275號\n"
                        + "營業：11:00-14:00，17:00-20:00\n"
                        + "公休：週二\n"
                        + "價位：💰💰\n"
                        + "評分：🌕🌕🌕🌗🌑");


        gamePanel.store[10] = new Store(gamePanel);
        gamePanel.store[10].setAll("| 皮嚓先生 |", 200, 21, 24, "/store/house3");
        gamePanel.store[10].setStoreInfo(
                "位在中西區小巷內超可愛的義式料理，從裝飾看得出來店長很喜歡貓跟多肉植物啊～\n"
                        + "以價位跟味道來說，完全是會想再二訪三訪的店！",

                "\n"
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
                        + "地瓜內餡甜而不膩，有展現出食材的原味。\n",

                "\n"
                        + "地址：台南市中西區中山路82巷6號\n"
                        + "營業：11:00-14:30，17:00-20:30\n"
                        + "公休：週日、週一\n"
                        + "價位：💰💰\n"
                        + "評分：🌕🌕🌕🌕🌗");


        gamePanel.store[11] = new Store(gamePanel);
        gamePanel.store[11].setAll("| 古城豆花庄 |", 110, 19, 24,"x", -20, "/store/house3");
        gamePanel.store[11].setStoreInfo(
                "講到台南的宵夜場絕對不會少了這家吧～\n"
                        + "不論是想吃甜的鹹的、冷的熱的，在古城豆花庄通通都找得到！",

                "\n"
                        + "#紅豆麵茶豆花\n"
                        + "平常喜歡麵茶的人非常推薦試試看這碗，Bear真的超級愛（也可能是因為很甜）\n"
                        + "完全沒想過麵茶跟蜜紅豆的搭配可以這麼好吃(˶‾᷄ ⁻̫ ‾᷅˵)這家的豆花是走綿密路線的喔！\n"
                        + "\n"
                        + "#牛奶豆花\n"
                        + "豆花滑順綿密的口感，搭上濃醇的鮮奶，再加點有嚼勁的小粉圓完全就是絕配！\n"
                        + "不太喜歡吃太甜的Nora熊覺得很可以，雖然是有奶類製品的豆花但吃起來很清爽。\n",

                "\n"
                        + "地址：台南市中西區尊王路82號\n"
                        + "營業：10:00-2:00\n"
                        + "公休：週三\n"
                        + "價位：💰💰\n"
                        + "評分：🌕🌕🌕🌕🌗");


        gamePanel.store[12] = new Store(gamePanel);
        gamePanel.store[12].setAll("| 雙生綠豆沙牛奶 |", 80, 20, 22, "/store/house3");
        gamePanel.store[12].setStoreInfo(
                "來台南基本上不會錯過的一家店～很多時候要喝到一杯綠豆沙牛奶得排隊抽號碼牌！\n"
                        + "不過這次去五分鐘內就美食到手了，當天是週五差不多下午三點半給各位參考！",

                "\n"
                        + "#綠豆沙牛奶\n"
                        + "綠豆沙很綿密，不會死甜，但又不致於蓋過牛奶的香氣，\n"
                        + "兩個元素的味道都夠濃郁。很好喝！\n"
                        + "加珍珠的話見仁見智，個人覺得珍珠的黑糖味稍微搶過綠豆沙牛奶本身的風采，\n"
                        + "但同行小夥伴覺得加珍珠口感更豐富而且別有一番滋味。\n",

                "\n"
                        + "地址：台南市中西區民族路二段281號\n"
                        + "營業：11:00-18:00\n"
                        + "公休：週一、週二\n"
                        + "價位：💰\n"
                        + "評分：🌕🌕🌕🌕🌗");

        gamePanel.store[13] = new Store(gamePanel);
        gamePanel.store[13].setAll("| 保安路米糕 |", 50, 17, 23, "/store/house3");
        gamePanel.store[13].setStoreInfo(
                "保安路米糕是在美食集中區台南國華街的傳統小吃，\n"
                        + "究竟是什麼懷舊古早味的魅力讓這家小小的店，\n"
                        + "得以在眾多競爭者的黃金地段屹立不搖將近一甲子呢？",

                "\n"
                        + "#米糕\n"
                        + "米糕的米飯吃起來QQ糯糯的，粒粒分明。\n"
                        + "上頭淋有肉燥、肉鬆增添香氣跟口感，一旁有水煮花生還有可以解膩的小黃瓜。\n"
                        + "小份的份量蠻小的喔！\n"
                        + "\n"
                        + "#鴨蛋\n"
                        + "個人喜歡鴨蛋勝過他們家的米糕，滷的很入味。\n",

                "\n"
                        + "地址：台南市中西區保安路16號\n"
                        + "營業：10:00-22:00\n"
                        + "公休：週三\n"
                        + "價位：💰\n"
                        + "評分：🌕🌕🌕🌗🌑");


        gamePanel.store[14] = new Store(gamePanel);
        gamePanel.store[14].setAll("| 福記肉圓 |", 60, 16, 25, gamePanel.tileSize * 8, gamePanel.tileSize * 5 + 25, "/store/house3");
        gamePanel.store[14].setStoreInfo(
                "這家是Bear踏溯台南的時候慕名前去嚐鮮的～\n"
                        + "所以離成大有點距離，建議搭乘大眾運輸工具或騎機車前往！\n"
                        + "十分推薦來一探傳統庶民小吃的魅力(˶‾᷄ ⁻̫ ‾᷅˵)",

                "\n"
                        + "#肉圓\n"
                        + "有別於大部分肉圓QQ的外皮，福記肉圓的外皮是在來米米漿製成的，\n"
                        + "吃起來是像碗粿軟糯的口感，澱粉的香味也更加明顯。內餡豬肉很大塊飽滿，\n"
                        + "口感紮實且肉香十足，整體搭配著特調的甜甜醬汁，早餐來一碗真的特別享受～\n"
                        + "不愧是在地屹立不搖的四十年老店！\n",
                "\n"
                        + "地址：台南市中西區府前路一段215號\n"
                        + "營業：6:30-18:30\n"
                        + "公休：週一\n"
                        + "價位：💰\n"
                        + "評分：🌕🌕🌕🌕🌑");
    }


}








