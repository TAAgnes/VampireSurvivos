package effect;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FrameImage {
    private String name;
    private BufferedImage image;

    public FrameImage(){
        this.name = null;
        this.image = null;
    }

    public FrameImage(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }
    private int getWidthImage() {
        return image.getWidth();
    }
    private int getHeightImage() {
        return getHeightImage();
    }

    public FrameImage(FrameImage frameImage){
        image = new BufferedImage(frameImage.getWidthImage(), frameImage.getHeightImage(), frameImage.image.getType());
        Graphics g = image.getGraphics();
        g.drawImage(frameImage.image, 0, 0, null);
        name = frameImage.name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void draw(int x, int y, Graphics2D g2){
        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
    }
}
