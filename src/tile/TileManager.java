package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gamePanel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gamePanel = gp;

        tile = new Tile[40];
        mapTileNum = new int[gp.maxWorldCOL][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world1.txt");

    }

    public void getTileImage() {

        setup(0, "00", false);
        setup(1, "01", true);
        setup(2, "02", true);
        setup(3, "03", false);
        setup(4, "04", true);
        setup(5, "05", false);
        setup(6, "06", true);
        setup(7, "07", true);
        setup(8, "08", true);
        setup(9, "09", true);
        setup(10, "10", true);
        setup(11, "11", true);
        setup(12, "12", true);
        setup(13, "13", true);
        setup(14, "14", true);
        setup(15, "15", false);
        setup(16, "16", true);
        setup(17, "17", true);
        setup(18, "18", true);
        setup(19, "19", true);
        setup(20, "20", true);
        setup(21, "21", true);
        setup(22, "22", true);
        setup(23, "23", true);
        setup(24, "24", true);
        setup(25, "25", true);
        setup(26, "26", true);
        setup(27, "27", true);
        setup(28, "28", true);
        setup(29, "29", true);
        setup(30, "30", true);
        setup(31, "31", true);
        setup(32, "32", true);
        


    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool utilityTool = new UtilityTool();


        try {
            tile[index] = new Tile();
            tile[index].setTileImage(ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png")));
            tile[index].setTileImage(utilityTool.scaleImage(tile[index].getTileImage(), gamePanel.tileSize, gamePanel.tileSize));
            tile[index].setTileCollision(collision);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {


        try {
            InputStream iStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iStream));
            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCOL && row < gamePanel.maxWorldRow) {

                String line = bufferedReader.readLine();

                while (col < gamePanel.maxWorldCOL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCOL) {
                    col = 0;
                    row++;
                }


            }

            bufferedReader.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        int worldX;
        int worldY;

        int screenX;
        int screenY;

        while (worldCol < gamePanel.maxWorldCOL && worldRow < gamePanel.maxWorldCOL) {

            int tileNum = mapTileNum[worldCol][worldRow];


            worldX = worldCol * gamePanel.tileSize;
            worldY = worldRow * gamePanel.tileSize;


            screenX = worldX - gamePanel.player.getWorld("x")  + gamePanel.player.screenX;
            screenY = worldY - gamePanel.player.getWorld("y")  + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.getWorld("x")  - gamePanel.player.screenX
                    && worldX - gamePanel.tileSize < gamePanel.player.getWorld("x")  + gamePanel.player.screenX
                    && worldY + gamePanel.tileSize > gamePanel.player.getWorld("y")  - gamePanel.player.screenY
                    && worldY - gamePanel.tileSize < gamePanel.player.getWorld("y")  + gamePanel.player.screenY) {

                g2.drawImage(tile[tileNum].getTileImage(), screenX, screenY, null);
            }


            worldCol++;


            if (worldCol == gamePanel.maxWorldCOL) {
                worldCol = 0;

                worldRow++;

            }
        }


    }


}








