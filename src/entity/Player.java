package entity;

import main.GamePanel;
import main.KeyHander;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHander keyH;

    public Player(GamePanel gp, KeyHander keyH){
        this.gp = gp;
        this.keyH = keyH;
        getPlayerImage();

        setDefaultValues();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        health = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/sova.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/sova.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/sova.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/sova.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/sova.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/sova.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/sova.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/sova.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public  void upadte(){
        if(keyH.upPressed == true){
            direction = "up";
            y -= speed;
        } else if (keyH.downPressed == true) {
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed == true) {
            direction = "left";
            x -= speed;
        }
        else if (keyH.rightPressed == true){
            direction = "right";
            x += speed;
        }

    }

    public void draw(Graphics2D g2){

        /*g2.setColor(Color.white);
        g2.fillRect(x,y,vampire.tileSize,vampire.tileSize);*/

        BufferedImage image = null;

        switch (direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = up1;
                break;
            case "left":
                image = up1;
                break;
            case "right":
                image = up1;
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        int barWidth = gp.tileSize ; //Độ dài của thanh hp
        int barHeight = 8; //Độ rộng của thanh hp
        int xOffset = 0; //vị trí căn lề trái của thanh hp
        int yOffset = gp.tileSize - gp.tileSize; //vị trí căn lề trên dưới của thanh hp

        g2.setColor(Color.GRAY);
        g2.fillRect(x + xOffset, y + yOffset, barWidth, barHeight);

        int healthBarWidth = (int) ((float) health / (float) this.health * barWidth);
        g2.setColor(Color.GREEN);
        g2.fillRect(x + xOffset, y + yOffset, healthBarWidth, barHeight);
    }
}
