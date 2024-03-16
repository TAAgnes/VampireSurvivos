package entity;

import main.GamePanel;
import main.KeyHander;

import javax.security.auth.kerberos.KerberosTicket;

public class Enemy extends Entity {
    GamePanel gp;
    KeyHander keyH;

    public Enemy(GamePanel gp, KeyHander keyH){
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
    }

    public void setDefaultValues(){
        x = 10;
        y = 10;
        health = 10;
        speed = 1;
    }
}
