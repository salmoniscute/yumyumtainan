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

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCOL][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world1.txt");

    }

    public void getTileImage() {

        setup(0, "ground", false);
        setup(1, "flower", true);
        setup(2, "water", true);
        setup(3, "grass", false);
        setup(4, "flower2", true);
        setup(5, "bridge", false);
        setup(6, "brush", true);


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


            screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX
                    && worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX
                    && worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY
                    && worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

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








