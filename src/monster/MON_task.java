package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_task extends Entity {
    GamePanel gamePanel;

    public MON_task(GamePanel gamePanel) {

        super(gamePanel);
        this.gamePanel = gamePanel;

        name = "task";
        speed = 6;
        type = 2;

        solidArea.x = 8;
        solidArea.y = 12;
        solidArea.width = 32;
        solidArea.height = 24;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        maxLife = 20;
        life = maxLife;

        getMONImage();
        setAction();
    }

    public void getMONImage() {
        up2 = setup("/monster/task.back2", gamePanel.tileSize, gamePanel.tileSize);
        up3 = setup("/monster/task.back3", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/monster/task.front2", gamePanel.tileSize, gamePanel.tileSize);
        down3 = setup("/monster/task.front3", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/monster/task.left2", gamePanel.tileSize, gamePanel.tileSize);
        left3 = setup("/monster/task.left3", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/monster/task.right2", gamePanel.tileSize, gamePanel.tileSize);
        right3 = setup("/monster/task.right3", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 60) {
            Random random = new Random();
            int i = random.nextInt(4) + 1;
            switch (i) {
                case 1:
                    direction = "up";
                    break;
                case 2:
                    direction = "down";
                    break;
                case 3:
                    direction = "left";
                    break;
                case 4:
                    direction = "right";
                    break;
                default:
                    break;
            }
            actionLockCounter = 0;
        }
    }

    public void damageReaction() {
        actionLockCounter = 0;
        if (!direction.equals(gamePanel.player.getDirection())) {
            direction = gamePanel.player.getDirection();
            return;
        }
        if (gamePanel.player.getDirection().equals("left") || gamePanel.player.getDirection().equals("right")) {
            switch (new Random().nextInt(2)) {
                case 0:
                    direction = "up";
                    break;
                case 1:
                    direction = "down";
                    break;
                default:
                    break;
            }
        } else if (gamePanel.player.getDirection().equals("up") || gamePanel.player.getDirection().equals("down")) {
            switch (new Random().nextInt(2)) {
                case 0:
                    direction = "right";
                    break;
                case 1:
                    direction = "left";
                    break;
                default:
                    break;
            }
        }
    }


    /////////////////////////////////////////////////////// Useless ///////////////////////////////////////////////////
    @Override
    public void setDialogue() {
    }

}







