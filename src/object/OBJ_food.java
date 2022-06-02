package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_food extends Entity {
    GamePanel gamePanel;

    public OBJ_food(GamePanel gPanel) {
        super(gPanel);
        this.gamePanel = gPanel;

        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        name = "honey";
        down2 = setup("/object/honey", gamePanel.tileSize, gamePanel.tileSize);

        collision = true;


    }

    //////////////////////////////////////////////////  Useless //////////////////////////////////////////////////////

    @Override
    public void setAction() {}

    @Override
    public void damageReaction() {}

    @Override
    public void setDialogue() {}

}
