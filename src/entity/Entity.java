package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

abstract public class Entity {


    GamePanel gamePanel;
    protected BufferedImage up1, up2, up3, down1, down2, down3, right1, right2, right3, left1, left2, left3;
    protected BufferedImage attackUp1, attackDown1, attackLeft1, attackRight1;
    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    protected int solidAreaDefaultX, solidAreaDefaultY;
    protected String dialogues[] = new String[20];
    public BufferedImage image;
    protected boolean collision = false;
    protected int type = 0;//0=player,1=npc,2=monster

    //state
    protected int worldX, worldY;
    protected String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean attacking = false;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;

    //counter
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;
    //character
    public String name;
    public int speed;

    //monster
    public int maxLife;
    public int life;


    public Entity(GamePanel gp) {
        this.gamePanel = gp;
    }

    public abstract void setAction();

    public abstract void damageReaction();

    public abstract void setDialogue();

    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        if (dialogueIndex == 1) {
            gamePanel.player.setPlayerMoney("-", 550);
        }
        if (dialogueIndex == 6) {
            gamePanel.player.setPlayerMoney("+", 100);
        }
        gamePanel.ui.setCurrentDialogue(dialogues[dialogueIndex]);
        dialogueIndex++;

        //面對面講話
        switch (gamePanel.player.direction) {
            case "up": {
                direction = "down";
                break;
            }
            case "down": {
                direction = "up";
                break;
            }
            case "left": {
                direction = "right";
                break;
            }
            case "right": {
                direction = "left";
                break;
            }
        }


    }

    public void update() {
        setAction();
        collisionOn = false;
        gamePanel.collisionChecker.checkTile(this);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        gamePanel.collisionChecker.checkPlayer(this);
        gamePanel.collisionChecker.checkStore(this, false);
        gamePanel.collisionChecker.checkObject(this, false);

        if (collisionOn == false) {
            switch (direction) {
                case "up": {
                    worldY -= speed;
                    break;
                }
                case "down": {
                    worldY += speed;
                    break;
                }
                case "left": {
                    worldX -= speed;
                    break;
                }
                case "right": {
                    worldX += speed;
                    break;
                }
            }

        }

        spriteCounter++;
        if (spriteCounter > 20) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

            switch (direction) {
                case "up": {
                    if (spriteNum == 1) {
                        image = up2;
                    }
                    if (spriteNum == 2) {
                        image = up3;
                    }

                    break;
                }
                case "down": {
                    if (spriteNum == 1) {
                        image = down2;
                    }
                    if (spriteNum == 2) {
                        image = down3;
                    }
                    break;
                }
                case "left": {
                    if (spriteNum == 1) {
                        image = left2;
                    }
                    if (spriteNum == 2) {
                        image = left3;
                    }
                    break;
                }
                case "right": {
                    if (spriteNum == 1) {
                        image = right2;
                    }
                    if (spriteNum == 2) {
                        image = right3;
                    }
                    break;
                }

            }
            //monster hp bar
            if (type == 2 && hpBarOn == true) {

                double oneScale = (double) gamePanel.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 6, gamePanel.tileSize + 2, 9);
                g2.setColor(new Color(255, 0, 0));
                g2.fillRect(screenX, screenY - 5, (int) hpBarValue, 7);

                hpBarCounter++;
                if (hpBarCounter > 300) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }

            }
            //緩衝
            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying == true) {
                dyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            changeAlpha(g2, 1f);

        }

    }

    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i && dyingCounter <= i * 2) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i * 3 && dyingCounter > i * 2) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i * 5 && dyingCounter > i * 4) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter <= i * 7 && dyingCounter > i * 6) {
            changeAlpha(g2, 0f);
        }
        if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
            changeAlpha(g2, 1f);
        }
        if (dyingCounter > i * 8) {
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return image;
    }

    /////////////////////////////////////////////// Encapsulation ///////////////////////////////////////////////

    ////////////////////////// Solid Area
    public Rectangle getSolidArea(){
        return solidArea;
    }

    ////////////////////////// up down left right image
    public BufferedImage getDirectionImage (String name){
        switch (name) {
            case "up1" : return up1;
            case "up2" : return up2;
            case "up3" : return up3;
            case "down1" : return down1;
            case "down2" : return down2;
            case "down3" : return down3;
            case "left1" : return left1;
            case "left2" : return left2;
            case "left3" : return left3;
            case "right1" : return right1;
            case "right2" : return right2;
            case "right3" : return right3;
            default : return null;
        }
    }

    /////////////////////////// SolidArea default value
    public int getSolidAreaDefault (String str){
        if(str == "x"){
            return solidAreaDefaultX;
        }
        if (str == "y"){
            return solidAreaDefaultY;
        }
        return 0;
    }

    /////////////////////////// Collision
    public boolean getCollision(){
        return collision;
    }

    /////////////////////////// World X Y
    public int getWorld(String xOry) {
        if(xOry == "x"){
            return worldX;
        }
        if(xOry == "y"){
            return worldY;
        }
        return 0;
    }
    public void setWorld(String xOry, int i){
        if(xOry == "x"){
            worldX = i;
        }
        if(xOry == "y"){
            worldY = i;
        }
    }

    ///////////////////////////////////// direction
    public String getDirection() {
        return direction;
    }

}
