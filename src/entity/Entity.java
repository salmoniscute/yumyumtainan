package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

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
    protected String[] dialogues = new String[20];
    protected boolean collision = false;
    protected int type = 0;//0=player,1=npc,2=monster

    //state
    protected int worldX, worldY;
    protected String direction = "down";
    protected int spriteNum = 1;
    protected int dialogueIndex = 0;
    protected boolean attacking = false;
    protected boolean collisionOn = false;
    protected boolean invincible = false;
    protected boolean alive = true;
    protected boolean dying = false;
    protected boolean hpBarOn = false;

    //counter
    protected int spriteCounter = 0;
    protected int actionLockCounter = 0;
    protected int invincibleCounter = 0;
    protected int dyingCounter = 0;
    protected int hpBarCounter = 0;
    //character
    protected String name;
    protected int speed;

    //monster
    protected int maxLife;
    protected int life;

    ////////////////////////////////////////////// Constructor //////////////////////////////////////////////////////
    public Entity(GamePanel gp) {
        this.gamePanel = gp;
    }

    /////////////////////////////////////////////// Methods //////////////////////////////////////////////////////
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
        direction = switch (gamePanel.player.direction) {
            case "up" -> "down";
            case "down" -> "up";
            case "left" -> "right";
            default -> "left";
        };


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

        if (!collisionOn) {
            switch (direction) {
                case "up" -> worldY -= speed;
                case "down" -> worldY += speed;
                case "left" -> worldX -= speed;
                case "right" -> worldX += speed;
                default -> {}
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
        if (invincible) {
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
                case "up" -> {
                    if (spriteNum == 1) {
                        image = up2;
                    }
                    if (spriteNum == 2) {
                        image = up3;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        image = down2;
                    }
                    if (spriteNum == 2) {
                        image = down3;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        image = left2;
                    }
                    if (spriteNum == 2) {
                        image = left3;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        image = right2;
                    }
                    if (spriteNum == 2) {
                        image = right3;
                    }
                }
            }
            //monster hp bar
            if (type == 2 && hpBarOn) {

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
            if (invincible) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4f);
            }
            if (dying) {
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
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return image;
    }

    /////////////////////////////////////////////// Encapsulation ///////////////////////////////////////////////

    ////////////////////////// Solid Area
    public Rectangle getSolidArea() {
        return solidArea;
    }

    ////////////////////////// up down left right image
    public BufferedImage getDirectionImage(String name) {
        return switch (name) {
            case "up1" -> up1;
            case "up2" -> up2;
            case "up3" -> up3;
            case "down1" -> down1;
            case "down2" -> down2;
            case "down3" -> down3;
            case "left1" -> left1;
            case "left2" -> left2;
            case "left3" -> left3;
            case "right1" -> right1;
            case "right2" -> right2;
            case "right3" -> right3;
            default -> null;
        };
    }

    /////////////////////////// SolidArea default value
    public int getSolidAreaDefault(String str) {
        if (str.equals("x")) {
            return solidAreaDefaultX;
        }
        if (str.equals("y")) {
            return solidAreaDefaultY;
        }
        return 0;
    }

    /////////////////////////// Collision
    public boolean getCollision() {
        return collision;
    }

    /////////////////////////// World X Y
    public int getWorld(String xOry) {
        if (xOry.equals("x")) {
            return worldX;
        }
        if (xOry.equals("y")) {
            return worldY;
        }
        return 0;
    }

    public void setWorld(String xOry, int i) {
        if (xOry.equals("x")) {
            worldX = i;
        }
        if (xOry.equals("y")) {
            worldY = i;
        }
    }

    ///////////////////////////////////// direction
    public String getDirection() {
        return direction;
    }

    //////////////////////////////////// Attacking
    public void setAttacking(boolean b) {
        attacking = b;
    }

    //////////////////////////////////// CollisionOn
    public void setCollisionOn(boolean b) {
        collisionOn = b;
    }

    ///////////////////////////////////// Alive
    public boolean isAlive() {
        return alive;
    }

    ///////////////////////////////////// Dying
    public boolean isDying() {
        return dying;
    }

    ///////////////////////////////////// Speed
    public int getSpeed() {
        return speed;
    }
}
