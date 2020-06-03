/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import GameState.GameStateManager;
import GameState.Stage1;
import Graphic.Assets;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author weeec
 */
public class Gate {
    private int x;
    private int y;
    Font f;
    private int destiny;
    GameStateManager gsm;
    private boolean visible = true;
    public Gate(int x,int y,int destiny,GameStateManager gsm){
        this.gsm = gsm;
        this.destiny = destiny;
        this.x = x;
        this.y = y;
    }
    
    public Rectangle Message(){
            return new Rectangle(250,20,250,30);
        }
    
    public void update(){
    };
    public void draw(Graphics2D g){
        if(Stage1.player.Limits().intersects(Limits())){  
             g.fill(Message());
            f = new Font("Arial", Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Enter(x)", 350, 40);
        }
        g.drawImage(Assets.gate, x, y, null);
        
    };
    
    public Rectangle Limits(){
        return new Rectangle(x, y,55,56);
    }
   
    public void onAction(){
          if(Stage1.player.Limits().intersects(Limits())){  
            gsm.setState(GameStateManager.Transition, destiny);
          }
    }
        
     
     

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

  
   
     
}
