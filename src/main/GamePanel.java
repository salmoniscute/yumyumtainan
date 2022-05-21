package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import entity.Entity;
import entity.Player;
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
	
	//game state
	public int gameState;
	public final int titleState=0;
	public final int  playState=1;
	public final int pauseState=2;
	public final int dialogueState=3;
	
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
		playMusic(0);
		
		gameState =titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawcount = 0;
		
		
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			timer +=(currentTime-lastTime);
			lastTime = currentTime;
			
			if(delta>=1) {
				//update information
				update();
				
				//draw the screen with the update information
				repaint();
				delta--;
				drawcount++;
				
			}
			
			if(timer>=1000000000) {
				//System.out.println("FPS="+drawcount);
				drawcount = 0;
				timer = 0;
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
