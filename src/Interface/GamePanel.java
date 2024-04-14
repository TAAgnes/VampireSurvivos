package Interface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    Thread gameThread;
    public boolean isRunning = true;
    InputManager inputManager;

    public GamePanel(){
        inputManager = new InputManager();
    }

    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    int a = 0;
    @Override
    public void run() {
        long previousTime = System.nanoTime();
        long currentTime;
        long sleepTime;

        long period = 1000000000/60;

        while (isRunning){
            repaint();

            currentTime = System.nanoTime();
            sleepTime = period - (currentTime - previousTime);

            try{
                if(sleepTime > 0)
                    Thread.sleep(sleepTime / 1000000);
                else Thread.sleep(period / 2000000);
            } catch (Exception e) {}

            previousTime = System.nanoTime();
        }

    }

    public void paint(Graphics g){

    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        inputManager.setPressedButton(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        inputManager.setReleasedButton(e.getKeyCode());
    }

}
