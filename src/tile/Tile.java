package tile;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage image;
    private boolean collision = false;



    /////////////////////////////////////////////// Encapsulation  ////////////////////////////////////////////////

    ////////////////////// Image
    public void setTileImage(BufferedImage image){
        this.image = image;
    }
    public BufferedImage getTileImage(){
        return image;
    }

    ////////////////////// Collision
    public void setTileCollision(boolean b){
        collision = b;
    }
    public boolean getTileCollision(){
        return collision;
    }

}
