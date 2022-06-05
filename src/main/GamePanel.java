package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;

import entity.Entity;
import entity.Player;
import store.Store;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    ////////////////////////////////////////////  Attribute /////////////////////////////////////////////////

    ////////////////////////// screen setting (All final)
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    //world setting
    public final int maxWorldCOL = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCOL;
    public final int worldHeight = tileSize * maxWorldRow;

    //////////////////////////////////////////// FPS
    final int FPS = 60;

    //////////////////////////////////////////// System

    TileManager tileManager = new TileManager(this);

    public KeyHandler keyHandler = new KeyHandler(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Sound sound = new Sound();
    public Sound music = new Sound();

    public Assetsetter assetsetter = new Assetsetter(this);

    public UI ui = new UI(this);

    Thread gameThread;

    //////////////////////////////////// Entities and Objects

    public Player player = new Player(this, keyHandler);
    public Entity npc[] = new Entity[10];
    public Entity objects[] = new Entity[10];
    public Entity monster[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();
    public Store store[] = new Store[15];

    //////////////////////////////////////// Game State
    private int gameState;
    public final int loadingState = -1;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;
    public final int storeState = 5;
    public final int finishState = 6;


    ////////////////////////////////////////////  Constructor /////////////////////////////////////////////////

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(255, 236, 241));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    /////////////////////////////////////////// Methods ////////////////////////////////////////////////////////

    public void setUpGame() {

        gameState = loadingState;

        assetsetter.setObject();
        assetsetter.setNPC();
        assetsetter.setMonster();
        assetsetter.setStore();


        playMusic(0);

        gameState = titleState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        int drawInterval = 1000000000 / FPS;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {


            currentTime = System.nanoTime();

            if (currentTime - lastTime >= drawInterval) {

                update();

                repaint();

                lastTime = currentTime;
            }
        }

    }

    public void update() {

        if (gameState == playState) {
            //player
            player.update();
            //npc
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (monster[i].isAlive() && !monster[i].isDying()) {
                        monster[i].update();
                    }
                    if (!monster[i].isAlive()) {
                        monster[i] = null;
                    }
                }
            }
        }

        if (gameState == pauseState) {
            //nothing
        }


    }

    ////draw

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        //title screen
        if (gameState == titleState) {
            ui.draw(g2);
        }

        //others
        else {
            ///tile

            tileManager.draw(g2);

            //store
            for (int i = 0; i < store.length; i++) {
                if (store[i] != null) {
                    store[i].draw(g2);
                }
            }

            //entity
            entityList.add(player);
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] != null) {
                    entityList.add(objects[i]);
                }
            }

            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }

            //sort
            Collections.sort(entityList, new Comparator<Entity>() {

                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.getWorld("y"), e2.getWorld("y"));
                    return result;
                }
            });
            //draw entity
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            //empty entitylist
            entityList.clear();


            //UI
            ui.draw(g2);
        }


        g2.dispose();


    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    /////////////////////////////////////////////// Encapsulation /////////////////////////////////////////////////


    //////////////////// Game State
    public void setGameState(int state) {
        gameState = state;
    }

    public int getGameState() {
        return gameState;
    }
}
