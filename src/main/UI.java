package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class UI {

    ////////////////////////////////////////////////// Attribute /////////////////////////////////////////////////
    GamePanel gamePanel;
    Font arial_40, arial_80B;
    Graphics2D g2;
    BufferedImage logo, ig;
    private String currentDialogue = "";
    private int commandNum = 0;
    private int titleScreenState = 0;
    private int storeScreenState = 0;
    private int storeNum = 0;
    private int timeStopper = 0;
    private String header = "在遊戲開始之前，先設定玩家名稱吧！";

    ////////////////////////////////////////////// Constructor /////////////////////////////////////////////
    public UI(GamePanel gp) {

        this.gamePanel = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        try {
            ig = ImageIO.read(getClass().getResourceAsStream("/maps/ig.png"));
            logo = ImageIO.read(getClass().getResourceAsStream("/maps/logo.png"));


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        //loading state
        if (gamePanel.getGameState() == gamePanel.loadingState) {
            drawLoadingScreen();
        }
        //title state
        if (gamePanel.getGameState() == gamePanel.titleState) {
            drawTitleScreen();
        }
        //play state
        if (gamePanel.getGameState() == gamePanel.playState) {
            //play
        }
        //pause state
        if (gamePanel.getGameState() == gamePanel.pauseState) {
            drawPauseScreen();
        }
        //dialogue
        if (gamePanel.getGameState() == gamePanel.dialogueState) {
            drawDialogueScreen();
        }
        //character screen
        if (gamePanel.getGameState() == gamePanel.characterState) {
            drawCharacterScreen();
        }
        //store screen
        if (gamePanel.getGameState() == gamePanel.storeState) {
            drawStoreScreen(storeNum);
        }
        if (gamePanel.player.getPlayerMoney() < 0) {
            gamePanel.setGameState(gamePanel.finishState);
            drawFinishScreen();
        }

/////////////////////////////////////// Drawing Different State ///////////////////////////////////////////


    }


    public void drawLoadingScreen() {
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD));
        String text = "Loading...";
        int x = getXForCenterText(text);
        int y = gamePanel.tileSize * 5 - 10;

        //shadow
        g2.setColor(Color.pink);
        g2.drawString(text, x + 2, y + 2);

        //main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(25F));
        text = "鍵盤記得切換到英文輸入！";
        x = getXForCenterText(text);
        y += gamePanel.tileSize + 15;
        g2.drawString(text, x, y);

        y += gamePanel.tileSize - 5;
        x = gamePanel.tileSize * 3 + 15;

        g2.drawImage(gamePanel.npc[0].getDirectionImage("down2"), x, y, null);
        x += gamePanel.tileSize * 2;
        g2.drawImage(gamePanel.objects[0].getDirectionImage("down2"), x, y, null);
        x += gamePanel.tileSize * 2;
        g2.drawImage(gamePanel.player.getDirectionImage("down1"), x, y, null);
        x += gamePanel.tileSize * 2;
        g2.drawImage(gamePanel.objects[0].getDirectionImage("down2"), x, y, null);
        x += gamePanel.tileSize * 2;
        g2.drawImage(gamePanel.monster[0].getDirectionImage("down2"), x, y, null);

    }

    public void drawPauseScreen() {
        int x = gamePanel.tileSize * 4 + gamePanel.tileSize / 2;
        int y = gamePanel.tileSize * 4;
        g2.drawImage(logo, x, y, gamePanel.tileSize * 7, gamePanel.tileSize * 3, null);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 40F));
        String text = "PAUSED";
        x = getXForCenterText(text);
        y = gamePanel.tileSize * 8;
        g2.drawString(text, x, y);

    }

    public void drawDialogueScreen() {


        //window
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - gamePanel.tileSize * 4;
        int height = gamePanel.tileSize * 4 + 5;

        drawSubWindow(x, y, width, height, 0, 0, 0, 180, 3);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        x += gamePanel.tileSize;
        y += gamePanel.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawCharacterScreen() {

        //create a frame
        final int frameX = gamePanel.tileSize;
        final int frameY = gamePanel.tileSize;
        final int frameWidth = gamePanel.tileSize * 6;
        final int frameHeight = gamePanel.tileSize * 6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, 0, 0, 0, 180, 3);
        //text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(23F));

        int x = frameX + 20;
        int y = frameY + gamePanel.tileSize;

        //information
        g2.drawString("玩家 " + gamePanel.player.getPLayerName(), x, y);
        y += gamePanel.tileSize;
        g2.drawString("持有金額：" + gamePanel.player.getPlayerMoney() + " 元", x, y);
        y += gamePanel.tileSize;
        g2.drawString("探索美食次數：" + gamePanel.player.getStoreCount() + " 次", x, y);
        y += gamePanel.tileSize;
        g2.drawString("拾獲蜂蜜數：" + gamePanel.player.getHoney() + " 罐", x, y);
        y += gamePanel.tileSize;
        g2.drawString("擊死綿羊數：" + gamePanel.player.getSheepCount() + " 隻", x, y);
    }


    public void drawFinishScreen() {
        g2.setColor(new Color(255, 236, 241));
        g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);


        int x = gamePanel.tileSize * 4;
        int y = gamePanel.tileSize - 10;
        g2.drawImage(logo, x, y, gamePanel.tileSize * 6 / 2, gamePanel.tileSize * 3 / 2, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35));
        String text = "台南美食地圖";
        x = getXForCenterText(text) + 60;
        y = gamePanel.tileSize + 30;

        //shadow
        g2.setColor(Color.PINK);
        g2.drawString(text, x + 2, y + 2);

        //main color
        g2.setColor(Color.black);
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55));
        text = "遊戲結束";
        x = getXForCenterText(text);
        y += gamePanel.tileSize * 3;
        g2.drawString(text, x, y);


        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 22));
        text = "哎呀！你怎麼可以讓熊熊口袋空空呢";
        x = getXForCenterText(text);
        y += gamePanel.tileSize + 15;
        g2.drawString(text, x, y);

        x = gamePanel.tileSize;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 18));
        y += gamePanel.tileSize + 20;
        g2.drawString("玩家 " + gamePanel.player.getPLayerName(), x, y);
        y += gamePanel.tileSize;
        g2.drawString("持有金額：" + gamePanel.player.getPlayerMoney() + " 元", x, y);
        y += gamePanel.tileSize;
        g2.drawString("探索美食次數：" + gamePanel.player.getStoreCount() + " 次", x, y);
        y += gamePanel.tileSize;
        g2.drawString("拾獲蜂蜜數：" + gamePanel.player.getHoney() + " 罐", x, y);
        y += gamePanel.tileSize;
        g2.drawString("擊死綿羊數：" + gamePanel.player.getSheepCount() + " 隻", x, y);



        g2.drawImage(gamePanel.player.getDirectionImage("down1"), gamePanel.tileSize * 8, gamePanel.tileSize * 8, gamePanel.tileSize * 3, gamePanel.tileSize * 3, null);
        //	g2.drawImage(, gamePanel.tileSize*9, gamePanel.tileSize*8, gamePanel.tileSize*3, gamePanel.tileSize*3,  null);

        text = "重新開始";
        x = gamePanel.tileSize*13;
        y = gamePanel.tileSize * 10;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gamePanel.tileSize, y);
        }

        text = "離開";

        y += gamePanel.tileSize / 2 + 10;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gamePanel.tileSize, y);
        }
        //gamePanel.gameThread = null;
       // gamePanel.music.stop();

    }

    public void drawSubWindow(int x, int y, int width, int height, int r, int g, int b, int transparency, int stroke) {

        Color color = new Color(r, g, b, transparency);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        if (r > 100) {
            r = 0;
        } else {
            r += 255;
        }
        if (g > 100) {
            g = 0;
        } else {
            g += 255;
        }
        if (b > 100) {
            b = 0;
        } else {
            b += 255;
        }

        color = new Color(r, g, b);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(stroke));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }


    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(new Color(255, 236, 241));
            g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

            //logo image
            int x = gamePanel.tileSize ;
            int y = gamePanel.tileSize;
            g2.drawImage(logo, x, y, gamePanel.tileSize * 14, gamePanel.tileSize * 6, null);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD));
            String text = "台南美食地圖";
            x = getXForCenterText(text);
            y += gamePanel.tileSize * 7 + 15;

            //shadow
            g2.setColor(Color.PINK);
            g2.drawString(text, x + 2, y + 2);

            //main color
            g2.setColor(Color.black);
            g2.drawString(text, x, y);

            //menu
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 22));
            text = "進入遊戲";
            x = getXForCenterText(text);
            y += gamePanel.tileSize + 20;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "離開";
            x = getXForCenterText(text);
            y += gamePanel.tileSize / 2 + 10;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }

            x = gamePanel.tileSize;
            y -= gamePanel.tileSize / 2;
            g2.drawImage(ig, x, y, gamePanel.tileSize, gamePanel.tileSize, null);

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16));
            text = "yumyum_tb";
            y += gamePanel.tileSize;
            g2.drawString(text, x + gamePanel.tileSize, y - 18);
            text = "此遊戲的店家資訊皆是來自這個美食帳，歡迎前去看看！";
            y += gamePanel.tileSize / 3;
            g2.drawString(text, x + 5, y);

        } else if (titleScreenState == 1) {
            g2.setColor(new Color(255, 236, 241));
            g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35));
            String text = "台南美食地圖";
            int x = getXForCenterText(text);
            int y = gamePanel.tileSize + 20;

            //shadow
            g2.setColor(Color.PINK);
            g2.drawString(text, x + 2, y + 2);

            //main color
            g2.setColor(Color.black);
            g2.drawString(text, x, y);

            g2.setColor(Color.black);
            //引言
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18));
            text = "從前從前，在遠的要命王國裡，住著一隻喜愛吃東西的泰迪熊 " + gamePanel.player.getPLayerName() + " ";
            x = gamePanel.tileSize * 1;
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            text = "它聽說台南有著很多很多的美食，於是它決定來到這個傳說中的美食之都";
            y += gamePanel.tileSize / 2;
            g2.drawString(text, x, y);
            text = "比起當coding熊熊，它更喜歡當一隻美食熊熊";
            y += gamePanel.tileSize / 2;
            g2.drawString(text, x, y);
            text = "即便它明白，再吃下去，它就要逃脫不了大二的聖誕節魔咒了...";
            y += gamePanel.tileSize / 2;
            g2.drawString(text, x, y);
            text = "究竟把美食當作靈魂伴侶的" + gamePanel.player.getPLayerName() + "會在這裡遇到什麼冒險呢.......";
            y += gamePanel.tileSize / 2;
            g2.drawString(text, x, y);

            x = gamePanel.tileSize * 5 + 45;
            y += gamePanel.tileSize - 10;
            g2.drawImage(gamePanel.player.getDirectionImage("down1"), x, y, gamePanel.tileSize * 4, gamePanel.tileSize * 4, null);

            text = "遊戲解說";
            x = getXForCenterText(text);
            y += gamePanel.tileSize * 5 + 10;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "回上一頁";
            x = getXForCenterText(text);
            y += gamePanel.tileSize / 2 + 10;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }


        } else if (titleScreenState == 2) {
            g2.setColor(new Color(255, 236, 241));
            g2.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35));
            String text = "台南美食地圖";
            int x = getXForCenterText(text);
            int y = gamePanel.tileSize + 20;

            //shadow
            g2.setColor(Color.PINK);
            g2.drawString(text, x + 2, y + 2);

            //main color
            g2.setColor(Color.black);
            g2.drawString(text, x, y);

            g2.setColor(Color.black);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
            text = "早上好 " + gamePanel.player.getPLayerName() + " 現在你有初始基金1000元";
            x = gamePanel.tileSize;
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            text = "你可以透過Ｗ、Ｓ、Ａ、Ｄ鍵或上、下、左、右鍵在地圖中移動";
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            text = "按下Enter鍵可以與場上人物對話以及進入店面";
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            text = "Ｐ鍵可以暫停遊戲";
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            text = "Ｃ鍵可以獲得你目前的遊戲資訊";
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            text = "而作為一隻美食熊熊，口袋裡有足夠的錢是必須的";
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            text = "地圖中有隱藏熊熊最愛的";
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            g2.drawImage(gamePanel.objects[0].getDirectionImage("down2"), x + gamePanel.tileSize * 5 - 15, y - 20, gamePanel.tileSize / 2, gamePanel.tileSize / 2, null);
            text = "吃下它會增加你的錢錢";
            g2.drawString(text, x + gamePanel.tileSize * 5 + 15, y);
            text = "還可以按下Ｋ鍵來噴出熊熊大火攻擊綿羊";
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            g2.drawImage(gamePanel.monster[0].getDirectionImage("down2"), x + gamePanel.tileSize * 7 + 30, y - 20, gamePanel.tileSize / 2, gamePanel.tileSize / 2, null);
            text = "獲得錢錢！";
            g2.drawString(text, x + gamePanel.tileSize * 8 + 14, y);

            text = "開始遊戲";
            x = getXForCenterText(text);
            y += gamePanel.tileSize + 5;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "回上一頁";
            x = getXForCenterText(text);
            y += gamePanel.tileSize / 2 + 10;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }


        } else if (titleScreenState == 9) {

            timeStopper++;

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(gamePanel.tileSize * 3 + 5, gamePanel.tileSize * 5 + 5, gamePanel.tileSize * 10 - 10, gamePanel.tileSize * 3 / 2 - 10, 20, 20);

            /*g2.setColor(new Color(203, 67, 53, 100));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(gamePanel.tileSize * 3 + 5, gamePanel.tileSize * 5 + 5, gamePanel.tileSize * 10 - 10, gamePanel.tileSize * 3 / 2 - 10, 20, 20);
*/

            //new Color(203, 67, 53, 100)

            g2.setColor(new Color(255, 218, 220));
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(gamePanel.tileSize * 3, gamePanel.tileSize * 5, gamePanel.tileSize * 10, gamePanel.tileSize * 3 / 2, 20, 20);


            if (gamePanel.player.getPLayerName().length() > 1) {
                header = "在遊戲開始之前，先設定玩家名稱吧！";
            }
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40));
            int x = getXForCenterText(header);
            int y = gamePanel.tileSize * 4;
            g2.drawString(header, x, y);


            String text = gamePanel.player.getPLayerName();
            int textWidth = getTextWidth(text);

            if (textWidth >= gamePanel.tileSize * 7) {
                gamePanel.player.addPlayerName(false);
                g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 15));
                x = gamePanel.tileSize * 10;
                y = gamePanel.tileSize * 7;
                g2.drawString("名字太長囉!!", x, y);
            } else {
                gamePanel.player.addPlayerName(true);
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));
            x = gamePanel.tileSize * 3 + 10;
            y = gamePanel.tileSize * 6;
            g2.drawString(text, x, y);
            if (timeStopper <= 30) {
                g2.drawString(text + "|", x, y);
            } else if (timeStopper >= 60) {
                timeStopper = 0;
            }

            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20));
            text = "確認";
            x = getXForCenterText(text);
            y = gamePanel.tileSize * 9;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }


            text = "上一頁";
            x = getXForCenterText(text);
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }


        }

    }

    public void drawStoreScreen(int i) {

        if (storeScreenState == 0) {

            //create a frame
            final int frameX = gamePanel.tileSize * 3 + 10;
            final int frameY = gamePanel.tileSize * 3;
            final int frameWidth = gamePanel.tileSize * 9;
            final int frameHeight = gamePanel.tileSize * 5;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight, 255, 236, 241, 250, 2);

            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(18F));
            int x = frameX + 20;
            int y = frameY + gamePanel.tileSize;
            String text = "歡迎光臨 " + gamePanel.store[i].getName();
            g2.drawString(text, x, y);

            text = "探索這間台南美食需要 " + gamePanel.store[i].getCost() + " 元";
            y += gamePanel.tileSize;
            g2.drawString(text, x, y);


            g2.setFont(g2.getFont().deriveFont(16F));
            text = "好的，我要吃掉它";
            x = getXForCenterText(text);
            y += gamePanel.tileSize + 10;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }

            text = "算了我要減肥";
            x = getXForCenterText(text);
            y += gamePanel.tileSize - 5;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gamePanel.tileSize, y);
            }
        } else if (storeScreenState == 1) {
            //create a frame
            final int frameX = gamePanel.tileSize / 2;
            final int frameY = gamePanel.tileSize / 2;
            final int frameWidth = gamePanel.tileSize * 14 + 48;
            final int frameHeight = gamePanel.tileSize * 10 + 48;
            drawSubWindow(frameX, frameY, frameWidth, frameHeight, 255, 236, 241, 250, 2);

            String text;
            g2.setColor(Color.BLACK);
            g2.setFont(g2.getFont().deriveFont(18F));
            int x = frameX + 20;
            int y = frameY + gamePanel.tileSize - 10;

            g2.drawString(gamePanel.store[i].getName(), x, y);
            y += gamePanel.tileSize / 2;

            g2.setFont(g2.getFont().deriveFont(16F));
            for (String line : gamePanel.store[i].getAbout().split("\n")) {
                g2.drawString(line, x, y);
                y += 20;
            }
            for (String line : gamePanel.store[i].getItem().split("\n")) {
                g2.drawString(line, x, y);
                y += 20;
            }
            for (String line : gamePanel.store[i].getInfo().split("\n")) {
                g2.drawString(line, x, y);
                y += 20;
            }
            g2.drawImage(gamePanel.store[i].getPhoto1(), gamePanel.store[i].getPhotoX(), gamePanel.store[i].getPhotoY(), null);

            g2.setFont(g2.getFont().deriveFont(17F));
            text = "謝謝光臨";
            x = gamePanel.tileSize * 13;
            y = gamePanel.tileSize * 10;
            g2.drawString(text, x, y);


        }


    }


    //////////////////////////////////////////////////////// Methods ///////////////////////////////////////////////////////


    public int getXForCenterText(String text) {
        int textlength = getTextWidth(text);
        int x = gamePanel.screenWidth / 2 - textlength / 2;
        return x;
    }

    public int getTextWidth(String text) {
        return (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
    }

    public void changeHeaderTo(String str) {
        header = str;
    }


    /////////////////////////////////////////////// Encapsulation  ////////////////////////////////////////////////


    //////////////// Dialogue
    public void setCurrentDialogue(String str){
        currentDialogue = str;
    }


    /////////////// Command number
    public void setCommandNum(int i){
        commandNum = i;
    }
    public void setCommandNum(String sign, int i){
        if(sign == "+"){
            commandNum += i;
            return;
        }
        if(sign == "-"){
            commandNum -= i;
            return;
        }
    }
    public int getCommandNum(){
        return commandNum;
    }


    ///////////////////////// Title Screen State
    public void setTitleScreenState(int i) {
        titleScreenState = i;
    }
    public int getTitleScreenState(){
        return titleScreenState;
    }


    //////////////////////// Store Screen State
    public void setStoreScreenState(int i) {
        storeScreenState = i;
    }
    public int getStoreScreenState(){
        return storeScreenState;
    }

    ///////////////////////////////// TimeStopper
    public void setTimeStopper(int i){
        timeStopper = i;
    }

    //////////////////////////////// Store Number
    public void setStoreNum(int i){
        storeNum = i;
    }
}








