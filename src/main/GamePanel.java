package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;
import entity.Entity;
import entity.Player;
import store.Store_super;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	//screen setting
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize*scale;
	
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth= maxScreenCol*tileSize;
	public final int screenHeight= maxScreenRow*tileSize;
	
	//world setting
	public final int maxWorldCOL=50;
	public final int maxWorldRow =50;
	public final int worldWidth = tileSize*maxWorldCOL;
	public final int worldHeight = tileSize*maxWorldRow;
	
	
	//FPS
	int FPS = 60;
	
	////system
	
	TileManager tileManager = new TileManager(this);
	
	public KeyHandler keyHandler = new KeyHandler(this);
	
	public CollisionChecker collisionChecker = new CollisionChecker(this);

	public Sound sound = new Sound();
	
	public Assetsetter assetsetter = new Assetsetter(this);
	
	public UI ui = new UI(this);
	
	Thread gameThread;
	
	////entity and object
	
	public Player player = new Player(this,keyHandler);
	public Entity npc[]=new Entity[10];
	public Entity objects[]= new Entity[10];
	public Entity monster[]=new Entity[10];
	ArrayList<Entity> entityList=new ArrayList<>();
	public Store_super store[]=new Store_super[10];
	
	//game state
	public int gameState;
	public final int getPlayerInfoState = -1;
	public final int titleState=0;
	public final int  playState=1;
	public final int pauseState=2;
	public final int dialogueState=3;
	public final int characterState=4;
	public final int storeState=5;


	
	///////////
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyHandler);
		this.setFocusable(true);
		
	}
	
	public void setUpGame() {
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
		
		int drawInterval = 1000000000/FPS;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null){

            currentTime = System.nanoTime();

            if(currentTime - lastTime >= drawInterval) {

                update();

                repaint();

                lastTime = currentTime;
            }
        }
	}
	
	public void update() {
	
		if (gameState==playState) {
			//player
			player.update();
			//npc
			for(int i=0;i<npc.length;i++) {
				if (npc[i]!=null) {
					npc[i].update();
				}
			}
			for(int i=0;i<monster.length;i++) {
				if (monster[i]!=null) {
					if(monster[i].alive==true && monster[i].dying==false) {
						monster[i].update();
					}
					if(monster[i].alive==false) {
						monster[i]=null;
					}
				}
			}
		}
		
		if (gameState==pauseState) {
			//nothing
		}
		
		
		
	}
	
	////draw 
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		//title screen
		if(gameState==titleState) {
			ui.draw(g2);
		}
		
		//others
		else {
			///tile
			
			tileManager.draw(g2);
			
			//store
			for(int i=0;i<store.length;i++) {
				if(store[i]!=null) {
					store[i].draw(g2);
				}
			}
			
			//entity
			entityList.add(player);
			for(int i=0;i<npc.length;i++) {
				if(npc[i]!=null) {
					entityList.add(npc[i]);
				}
			}
			for(int i=0;i<objects.length;i++) {
				if(objects[i]!=null) {
					entityList.add(objects[i]);
				}
			}
		
			for(int i=0;i<monster.length;i++) {
				if(monster[i]!=null) {
					entityList.add(monster[i]);
				}
			}
			
			//sort
			Collections.sort(entityList,new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					int result =Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});
			//draw entity
			for (int i=0;i<entityList.size();i++) {
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
		
		sound.setFile(i);
		sound.play();
		sound.loop();
		
	}
	public void stopMusic() {
		sound.stop();
	}

}
