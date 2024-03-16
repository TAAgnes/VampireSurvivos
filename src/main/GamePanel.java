//Tạo giao diện cho game
package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable  {
    //Screen Settings
    final int originalTilesize =16;
    final int scale = 3;
    
    public final int tileSize = originalTilesize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    TileManager tileM = new TileManager(this);
    KeyHander keyH = new KeyHander();
    Thread gamThread;
    Player player = new Player(this,keyH);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public final int FPS = 60;

    public void startGameThread(){

        gamThread = new Thread(this);
        gamThread.start();
    }

    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gamThread != null){

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if(delta >=1){
                update();
                repaint();
                delta--;
            }

            if (timer >= 1000000000){
                System.out.println("FPS: "+ drawCount);
                drawCount = 0;
                timer = 0;
            }
        }

    }
    public void update(){
        player.upadte();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        player.draw(g2);
        g2.dispose();
    }
}
