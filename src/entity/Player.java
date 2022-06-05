package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    //////////////////////////////////////////////// Attribute ////////////////////////////////////////////////

    KeyHandler keyHandler;


    public final int screenX;
    public final int screenY;


    /////////////player state
    private String name = "";
    private int playerMoney = 0;
    private int getHoney = 0;
    private int killSheep = 0;
    private int enterStore = 0;
    private boolean canAddPlayerName = true;


    //////////////////////////////////////////////// constructor ///////////////////////////////////////////////
    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        this.gamePanel = gp;
        this.keyHandler = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle();
        solidArea.x = 16;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 16;
        solidArea.height = 20;

        attackArea.width = 40;
        attackArea.height = 40;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();

    }

    /////////////////////////////////////////////////// Methods ///////////////////////////////////////////////////

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 9;
        worldY = gamePanel.tileSize * 19;
        speed = 4;
        direction = "down";
        playerMoney = 1000;
    }

    public void getPlayerImage() {

        down1 = setup("/player/front1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/player/front2", gamePanel.tileSize, gamePanel.tileSize);
        down3 = setup("/player/front3", gamePanel.tileSize, gamePanel.tileSize);
        up1 = setup("/player/back1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/player/back2", gamePanel.tileSize, gamePanel.tileSize);
        up3 = setup("/player/back3", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/player/left1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/player/left2", gamePanel.tileSize, gamePanel.tileSize);
        left3 = setup("/player/left3", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/player/right1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/player/right2", gamePanel.tileSize, gamePanel.tileSize);
        right3 = setup("/player/right3", gamePanel.tileSize, gamePanel.tileSize);

    }

    public void getPlayerAttackImage() {

        attackUp1 = setup("/player/attack4", gamePanel.tileSize, gamePanel.tileSize + 25);

        attackDown1 = setup("/player/attack1", gamePanel.tileSize, gamePanel.tileSize + 25);

        attackLeft1 = setup("/player/attack3", gamePanel.tileSize + 35, gamePanel.tileSize);

        attackRight1 = setup("/player/attack2", gamePanel.tileSize + 35, gamePanel.tileSize);


    }

    public void update() {

        if (attacking) {
            attacking();
        } else if (keyHandler.downPressed() || keyHandler.upPressed() ||
                keyHandler.leftPressed() || keyHandler.rightPressed() || keyHandler.enterPressed()) {
            if (keyHandler.upPressed()) {
                direction = "up";
            } else if (keyHandler.downPressed()) {
                direction = "down";
            } else if (keyHandler.rightPressed()) {
                direction = "right";
            } else if (keyHandler.leftPressed()) {
                direction = "left";
            }

            //check tile collision

            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);

            //ckeck object collision

            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            //check store collision
            int storeIndex = gamePanel.collisionChecker.checkStore(this, true);
            if (keyHandler.enterPressed()) {
                openStore(storeIndex);
            }


            //check npc collision
            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);

            //check monster collision
            //int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);

            //

            if (!collisionOn && !keyHandler.enterPressed()) {
                switch (direction) {
                    case "up" -> worldY -= speed;

                    case "down" -> worldY += speed;

                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }

            }
            gamePanel.keyHandler.setEnterPressed(false);

            spriteCounter++;
            if (spriteCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            if (direction == "up") {
                direction = "stop_up";
            } else if (direction == "down") {
                direction = "stop_down";
            } else if (direction == "left") {
                direction = "stop_left";

            } else if (direction == "right") {
                direction = "stop_right";
            }


        }


    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            //save the current
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;
            //adjust player for the attack area
            switch (direction) {
                case "up": {
                    worldY -= attackArea.height;
                    break;
                }
                case "down": {
                    worldY += attackArea.height;
                    break;
                }
                case "left": {
                    worldX -= attackArea.width;
                    break;
                }
                case "right": {
                    worldX += attackArea.height;
                    break;
                }
            }
            //attack area become solid area
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
            damageMonster(monsterIndex);

            //after checking collision ,restore
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if (spriteCounter > 25) {
            spriteCounter = 0;
            spriteNum = 1;
            attacking = false;
        }
    }

    public void pickUpObject(int index) {

        if (index != 999) {
            String objectName = gamePanel.objects[index].name;
            switch (objectName) {
                case "honey": {
                    gamePanel.playSE(7); // make money sound
                    playerMoney += 100;
                    gamePanel.objects[index] = null;
                    getHoney++;
                    break;
                }

            }

        }

    }

    public void interactNPC(int i) {

        if (gamePanel.keyHandler.enterPressed() == true) {
            if (i != 999) {
                gamePanel.playSE(1);
                gamePanel.setGameState(gamePanel.dialogueState);
                gamePanel.npc[i].speak();
            }
			/*else {
					attacking=true;
			}*/
        }
        gamePanel.keyHandler.setEnterPressed(false);
    }

    public void damageMonster(int i) {

        if (i != 999) {
            if (!gamePanel.monster[i].invincible) {
                gamePanel.playSE(8); // Attack sound
                gamePanel.monster[i].life -= 1;
                gamePanel.monster[i].invincible = true;
                gamePanel.monster[i].damageReaction();

                if (gamePanel.monster[i].life <= 0) {
                    gamePanel.playSE(7); // make money sound
                    gamePanel.monster[i].dying = true;
                    playerMoney += 200;
                    killSheep++;
                }
            }


        }

    }

    public void openStore(int i) {

        if (i != 999) {

            //Open door sound
            Random random = new Random();
            int l = random.nextInt(2) + 3;
            gamePanel.playSE(l);

            gamePanel.setGameState(gamePanel.storeState);
            gamePanel.ui.setStoreNum(i);
            gamePanel.keyHandler.setStoreNum(i);
        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up": {
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = up2;
                    }
                    if (spriteNum == 2) {
                        image = up3;
                    }
                }
                if (attacking == true) {
                    tempScreenY -= gamePanel.tileSize - 25;
                    image = attackUp1;
                }
                break;
            }
            case "down": {
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = down2;
                    }
                    if (spriteNum == 2) {
                        image = down3;
                    }
                }
                if (attacking == true) {
                    image = attackDown1;
                }
                break;
            }
            case "left": {
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = left2;
                    }
                    if (spriteNum == 2) {
                        image = left3;
                    }
                }
                if (attacking == true) {
                    tempScreenX -= gamePanel.tileSize - 12;
                    image = attackLeft1;
                }
                break;
            }
            case "right": {
                if (attacking == false) {
                    if (spriteNum == 1) {
                        image = right2;
                    }
                    if (spriteNum == 2) {
                        image = right3;
                    }
                }
                if (attacking == true) {
                    image = attackRight1;
                }

                break;
            }
            case "stop_up": {
                image = up1;
                break;
            }
            case "stop_down": {
                image = down1;
                break;
            }
            case "stop_left": {
                image = left1;
                break;
            }
            case "stop_right": {
                image = right1;
                break;
            }


        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);
    }


    /////////////////////////////////////////////// Encapsulation  ////////////////////////////////////////////////


    //////////////////////////// Player Name
    public String getPLayerName() {
        return name;
    }

    public void addPlayerName(String str) {
        if (!canAddPlayerName) return;
        gamePanel.ui.setTimeStopper(0);
        name += str;
        name = name.substring(0, 1).toUpperCase() + name.substring(1);
        for (int i = 0; i < name.length(); i++) {
            int whiteSpaceIndex = name.indexOf(" ", i);
            if (whiteSpaceIndex + 2 >= name.length()) break;
            name = name.substring(0, whiteSpaceIndex + 1) + name.substring(whiteSpaceIndex + 1, whiteSpaceIndex + 2).toUpperCase() + name.substring(whiteSpaceIndex + 2);
        }
    }

    public void addPlayerName(boolean b) {
        canAddPlayerName = b;
    }

    public void clearPlayerName() {
        name = "";
    }

    public void backSpacePlayerName() {
        if (name.length() == 0) return;
        gamePanel.ui.setTimeStopper(0);
        name = name.substring(0, name.length() - 1);

    }


    ////////////////////////////////////// Player Money
    public void setPlayerMoney(String sign, int money) {
        if (sign == "+") {
            playerMoney += money;
            return;
        }
        if (sign == "-") {
            playerMoney -= money;
            return;
        }
    }

    public int getPlayerMoney() {
        return playerMoney;
    }


    ////////////////////////////////////// Honey
    public int getHoney() {
        return getHoney;
    }


    /////////////////////////////////////// Sheep count
    public int getSheepCount() {
        return killSheep;
    }


    /////////////////////////////////////// Store count
    public void setStoreCount(String sign, int i) {
        if (sign == "+") {
            enterStore += i;
            return;
        }
        if (sign == "-") {
            enterStore -= i;
            return;
        }
    }

    public int getStoreCount() {
        return enterStore;
    }


    /////////////////////////////////////////////////////// Useless /////////////////////////////////////////////////
    @Override
    public void setAction() {
    }

    @Override
    public void damageReaction() {
    }

    @Override
    public void setDialogue() {
    }


}








