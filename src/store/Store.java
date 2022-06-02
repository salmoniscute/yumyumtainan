package store;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Store {


    GamePanel gamePanel;

    public String name = "";
    public int cost = 0;

    public BufferedImage storeImage;
    public BufferedImage photo1;
    public int photoX, photoY;

    public boolean collision = false;

    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;

    public String about = "";
    public String item = "";
    public String info = "";

    public int photoWidth;
    public int photoHeight;
    public int imageWidth;
    public int imageHeight;

    public Store(GamePanel gp) {
        this.gamePanel = gp;

        solidArea.x = 10;
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 76;
        solidArea.height = 70;

        collision = true;

        photoWidth = gamePanel.tileSize * 4;
        photoHeight = gamePanel.tileSize * 5;
        imageWidth = gamePanel.tileSize * 2;
        imageHeight = imageWidth;

    }

    public void setStoreInfo(String about, String item, String info) {
        this.about = about;
        this.item = item;
        this.info = info;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = storeImage;
        int screenX = worldX - gamePanel.player.getWorld("x") + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.getWorld("y") + gamePanel.player.screenY;
        g2.drawImage(image, screenX, screenY, null);

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

    public void setAll(String name, int cost, int worldX, int worldY, String path_storeImage) {
        this.name = name;
        this.cost = cost;
        this.worldX = gamePanel.tileSize * worldX;
        this.worldY = gamePanel.tileSize * worldY;
        this.photoX = gamePanel.tileSize * 8;
        this.photoY = gamePanel.tileSize * 6;
        //this.photo1 = setup(path_photo1, photoWidth, photoHeight);
        this.storeImage = setup(path_storeImage, imageWidth, imageHeight);
    }

    public void setAll(String name, int cost, int worldX, int worldY, int photoX, int photoY, String path_storeImage) {
        this.name = name;
        this.cost = cost;
        this.worldX = gamePanel.tileSize * worldX;
        this.worldY = gamePanel.tileSize * worldY;
        this.photoX = photoX;
        this.photoY = photoY;
        //this.photo1 = setup(path_photo1, photoWidth, photoHeight);
        this.storeImage = setup(path_storeImage, imageWidth, imageHeight);
    }

    public void setAll(String name, int cost, int worldX, int worldY, String which, int modify, String path_storeImage) {
        if(which == "x"){
            this.name = name;
            this.cost = cost;
            this.worldX = gamePanel.tileSize * worldX;
            this.worldY = gamePanel.tileSize * worldY;
            this.photoX = gamePanel.tileSize * 8 + modify;
            this.photoY = gamePanel.tileSize * 6;
            //this.photo1 = setup(path_photo1, photoWidth, photoHeight);
            this.storeImage = setup(path_storeImage, imageWidth, imageHeight);
            return;
        }
        if(which == "y"){
            this.name = name;
            this.cost = cost;
            this.worldX = gamePanel.tileSize * worldX;
            this.worldY = gamePanel.tileSize * worldY;
            this.photoX = gamePanel.tileSize * 8;
            this.photoY = gamePanel.tileSize * 6 + modify;
            //this.photo1 = setup(path_photo1, photoWidth, photoHeight);
            this.storeImage = setup(path_storeImage, imageWidth, imageHeight);
            return;
        }
        return;
    }

    public void setAll(String name, int cost, int worldX, int worldY,String which, int modifyX, int modifyY, String path_storeImage) {
        if (which == "xy") {
            this.name = name;
            this.cost = cost;
            this.worldX = gamePanel.tileSize * worldX;
            this.worldY = gamePanel.tileSize * worldY;
            this.photoX = gamePanel.tileSize * 8 + modifyX;
            this.photoY = gamePanel.tileSize * 6 + modifyY;
            //this.photo1 = setup(path_photo1, photoWidth, photoHeight);
            this.storeImage = setup(path_storeImage, imageWidth, imageHeight);
        }
    }

    public void loadPhoto(int i){
        this.photo1 = setup("/store/store" + (i+1), photoWidth, photoHeight);
    }
}
