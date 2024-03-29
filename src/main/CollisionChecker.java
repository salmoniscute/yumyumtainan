package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gp) {

        this.gamePanel = gp;

    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.getWorld("x") + entity.getSolidArea().x;
        int entityRightWorldX = entity.getWorld("x") + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorld("y") + entity.getSolidArea().y;
        int entityBottomWorldY = entity.getWorld("y") + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {

            case "up": {
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if (gamePanel.tileManager.tile[tileNum1].getTileCollision() == true || gamePanel.tileManager.tile[tileNum2].getTileCollision() == true) {
                    entity.setCollisionOn(true);
                }

                break;
            }
            case "down": {
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].getTileCollision() == true || gamePanel.tileManager.tile[tileNum2].getTileCollision() == true) {
                    entity.setCollisionOn(true);
                }
                break;
            }
            case "left": {
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].getTileCollision() == true || gamePanel.tileManager.tile[tileNum2].getTileCollision() == true) {
                    entity.setCollisionOn(true);
                }
                break;
            }
            case "right": {
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].getTileCollision() == true || gamePanel.tileManager.tile[tileNum2].getTileCollision() == true) {
                    entity.setCollisionOn(true);
                }
                break;
            }
        }


    }

    public int checkObject(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gamePanel.objects.length; i++) {
            if (gamePanel.objects[i] != null) {
                //get entity's solid area position
                entity.getSolidArea().x = entity.getWorld("x") + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorld("y") + entity.getSolidArea().y;
                //get object's solid area position
                gamePanel.objects[i].getSolidArea().x += gamePanel.objects[i].getWorld("x");
                gamePanel.objects[i].getSolidArea().y += gamePanel.objects[i].getWorld("y");

                switch (entity.getDirection()) {
                    case "up": {
                        entity.getSolidArea().y -= entity.getSpeed();
                        break;
                    }
                    case "down": {
                        entity.getSolidArea().y += entity.getSpeed();
                        break;
                    }
                    case "left": {
                        entity.getSolidArea().x -= entity.getSpeed();
                        break;
                    }
                    case "right": {
                        entity.getSolidArea().x += entity.getSpeed();
                        break;
                    }

                }
                if (entity.getSolidArea().intersects(gamePanel.objects[i].getSolidArea())) {
                    if (gamePanel.objects[i].getCollision()) {
                        entity.setCollisionOn(true);
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                entity.getSolidArea().x = entity.getSolidAreaDefault("x");
                entity.getSolidArea().y = entity.getSolidAreaDefault("y");
                gamePanel.objects[i].getSolidArea().x = gamePanel.objects[i].getSolidAreaDefault("x");
                gamePanel.objects[i].getSolidArea().y = gamePanel.objects[i].getSolidAreaDefault("y");

            }


        }
        return index;
    }

    public int checkStore(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gamePanel.store.length; i++) {
            if (gamePanel.store[i] != null) {
                entity.getSolidArea().x = entity.getWorld("x") + entity.getSolidArea().x;
                entity.getSolidArea().y = entity.getWorld("y") + entity.getSolidArea().y;
                //get object's solid area position
                gamePanel.store[i].solidArea.x += gamePanel.store[i].getWorldX();
                gamePanel.store[i].solidArea.y += gamePanel.store[i].getWorldY();

                switch (entity.getDirection()) {
                    case "up": {
                        entity.getSolidArea().y -= entity.getSpeed();
                        break;
                    }
                    case "down": {
                        entity.getSolidArea().y += entity.getSpeed();
                        break;
                    }
                    case "left": {
                        entity.getSolidArea().x -= entity.getSpeed();
                        break;
                    }
                    case "right": {
                        entity.getSolidArea().x += entity.getSpeed();
                        break;
                    }

                }
                if (entity.getSolidArea().intersects(gamePanel.store[i].solidArea)) {
                    if (gamePanel.store[i].getCollision()) {
                        entity.setCollisionOn(true);
                    }
                    if (player) {
                        index = i;
                    }
                }
                entity.getSolidArea().x = entity.getSolidAreaDefault("x");
                entity.getSolidArea().y = entity.getSolidAreaDefault("y");
                gamePanel.store[i].solidArea.x = gamePanel.store[i].solidAreaDefaultX;
                gamePanel.store[i].solidArea.y = gamePanel.store[i].solidAreaDefaultY;

            }


        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                //get entity's solid area position
                entity.getSolidArea().x += entity.getWorld("x");
                entity.getSolidArea().y += entity.getWorld("y");
                //get target's solid area position
                target[i].getSolidArea().x += target[i].getWorld("x");
                target[i].getSolidArea().y += target[i].getWorld("y");

                switch (entity.getDirection()) {
                    case "up": {
                        entity.getSolidArea().y -= entity.getSpeed();
                        break;
                    }
                    case "down": {
                        entity.getSolidArea().y += entity.getSpeed();
                        break;
                    }
                    case "left": {
                        entity.getSolidArea().x -= entity.getSpeed();
                        break;
                    }
                    case "right": {
                        entity.getSolidArea().x += entity.getSpeed();
                        break;
                    }


                }
                if (entity.getSolidArea().intersects(target[i].getSolidArea())) {
                    if (target[i] != entity) {
                        entity.setCollisionOn(true);
                        index = i;
                    }

                }
                entity.getSolidArea().x = entity.getSolidAreaDefault("x");
                entity.getSolidArea().y = entity.getSolidAreaDefault("y");
                target[i].getSolidArea().x = target[i].getSolidAreaDefault("x");
                target[i].getSolidArea().y = target[i].getSolidAreaDefault("y");

            }


        }
        return index;
    }

    public void checkPlayer(Entity entity) {

        entity.getSolidArea().x = entity.getWorld("x") + entity.getSolidArea().x;
        entity.getSolidArea().y = entity.getWorld("y") + entity.getSolidArea().y;
        //get target's solid area position
        gamePanel.player.getSolidArea().x += gamePanel.player.getWorld("x");
        gamePanel.player.getSolidArea().y += gamePanel.player.getWorld("y");

        switch (entity.getDirection()) {
            case "up": {
                entity.getSolidArea().y -= entity.getSpeed();
                break;
            }
            case "down": {
                entity.getSolidArea().y += entity.getSpeed();
                break;
            }
            case "left": {
                entity.getSolidArea().x -= entity.getSpeed();
                break;
            }
            case "right": {
                entity.getSolidArea().x += entity.getSpeed();
                break;
            }
        }
        if (entity.getSolidArea().intersects(gamePanel.player.getSolidArea())) {
            entity.setCollisionOn(true);

        }
        entity.getSolidArea().x = entity.getSolidAreaDefault("x");
        entity.getSolidArea().y = entity.getSolidAreaDefault("y");
        gamePanel.player.getSolidArea().x = gamePanel.player.getSolidAreaDefault("x");
        gamePanel.player.getSolidArea().y = gamePanel.player.getSolidAreaDefault("y");

    }


}












