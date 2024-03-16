package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[(int) Math.random()];

        getTileImage();
    }

    //Tạo map bất kỳ từ file ảnh tiles
    public void getTileImage(){
        try {
            // Array of tile image paths (modify as needed)
            String[] tilePaths = {
                    "/tiles/co.png",
                    "/tiles/cucda.png",
                    "/tiles/da.png",
                    "/tiles/da2.png",
                    "/tiles/dat.png",
                    "/tiles/tuong.png",
                    "/tiles/tuongreu.png",
                    "/tiles/nuoc.png",
                    "/tiles/dungnham.png"
            };

            for (int i = 0; i < tile.length; i++) {
                int randomIndex = (int) (Math.random() * tilePaths.length);
                tile[i] = new Tile();
                tile[i].image = ImageIO.read(getClass().getResourceAsStream(tilePaths[randomIndex]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y =0;
        while (col < gp.maxScreenCol && row < gp.maxScreenRow){
            g2.drawImage(tile[1].image,x,y,gp.tileSize,gp.tileSize,null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x=0;
                row++;
                y += gp.tileSize;
            }
        }
    }

}
