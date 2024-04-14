package Interface;

import effect.CacheDataLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameFrame extends JFrame {
    private static final int SCREEN_WIDTH = 1000;
    private static final int SCREEN_HEIGHT = 800;

    GamePanel gamePanel;

    public GameFrame() {
        super("VampireSurvivors");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = this.getToolkit();
        Dimension solution = toolkit.getScreenSize();

        try{
            CacheDataLoader.getInstance().LoadData();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        this.setBounds((solution.width - SCREEN_WIDTH)/2, (solution.height - SCREEN_HEIGHT)/2,SCREEN_WIDTH,SCREEN_HEIGHT);

        gamePanel = new GamePanel();
        addKeyListener(gamePanel);
        add(gamePanel);
    }

    public void startGame(){
        gamePanel.startGame();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.startGame();
    }
}
