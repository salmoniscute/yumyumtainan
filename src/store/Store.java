package store;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Store {
    GamePanel gamePanel;

    private String name = "";
    private int cost = 0;

    private BufferedImage storeImage;
    private BufferedImage photo1;
    private int photoX, photoY;

    private final boolean collision;

    private int worldX, worldY;
    public final Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public final int solidAreaDefaultX, solidAreaDefaultY;

    private String about = "";
    private String item = "";
    private String info = "";

    private final int photoWidth;
    private final int photoHeight;
    private final int imageWidth;
    private final int imageHeight;

    public Store(GamePanel gp) {
        this.gamePanel = gp;

        solidArea.x = 35;
        solidArea.y = 35;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 76;
        solidArea.height = 109;

        collision = true;

        photoWidth = gamePanel.tileSize * 4;
        photoHeight = gamePanel.tileSize * 5;
        imageWidth = gamePanel.tileSize * 3;
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
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
            image = utilityTool.scaleImage(image, width, height);
        } catch (IOException e) {
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

    public void setAll(String name, int cost, int worldX, int worldY, String xOry, int modify, String path_storeImage) {
        if (xOry.equals("x")) {
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
        if (xOry.equals("y")) {
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

    public void setAll(String name, int cost, int worldX, int worldY, String xOry, int modifyX, int modifyY, String path_storeImage) {
        if (xOry.equals("xy")) {
            this.name = name;
            this.cost = cost;
            this.worldX = gamePanel.tileSize * worldX;
            this.worldY = gamePanel.tileSize * worldY;
            this.photoX = gamePanel.tileSize * 8 + modifyX;
            this.photoY = gamePanel.tileSize * 6 + modifyY;
            this.storeImage = setup(path_storeImage, imageWidth, imageHeight);
        }
    }

    public void loadPhoto(int i) {
        this.photo1 = setup("/store/store" + (i + 1), photoWidth, photoHeight);
    }


    ///////////////////////////////////////////// Encapsulation ///////////////////////////////////////////////////

    ////////////////////// Name
    public String getName(){
        return name;
    }

    ////////////////////// Cost
    public int getCost(){
        return cost;
    }

    ////////////////////// Photo1
    public BufferedImage getPhoto1(){
        return photo1;
    }

    ////////////////////// Photo Position
    public int getPhotoX(){
        return photoX;
    }
    public int getPhotoY(){
        return photoY;
    }

    /////////////////////// Collision
    public boolean getCollision(){
        return collision;
    }

    ////////////////////// World Position
    public int getWorldX(){
        return worldX;
    }
    public int getWorldY(){
        return worldY;
    }

    //////////////////////// About
    public String getAbout(){
        return about;
    }
    public String getItem(){
        return item;
    }
    public String getInfo(){
        return info;
    }



}
