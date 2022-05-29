package store;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Store_super {

    GamePanel gamePanel;
    UtilityTool utilityTool = new UtilityTool();

    public String name = "";
    public int cost = 0;

    public BufferedImage storeImage;
    public BufferedImage photo1, photo2;
    public int photoX, photoY;

    public boolean collision = false;

    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;

    public String about = "";
    public String item = "";
    public String info = "";

    public Store_super(GamePanel gp) {
        this.gamePanel = gp;
        storeImage = setup("/object/door", gamePanel.tileSize, gamePanel.tileSize);

        solidArea.x = 8;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        collision = true;
    }

    public void setStoreInfo() {

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = storeImage;
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        g2.drawImage(image, screenX, screenY, null);

    }

    public BufferedImage setup(String imagePath, int width, int height) {

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
}
