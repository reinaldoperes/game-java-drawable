/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Graphic.*;
import GameState.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author weeec
 */
public class Potion {
    private int x;
    private int y;
    private int fall =4;
    private boolean visible = true;
    public Potion(int x,int y){
        this.x = x;
        this.y = y;
    }
    
    public void update(){
      
        setY(getY() + getFall());
      checkCollision();
    };
    public void draw(Graphics2D g){
        if(isVisible()){       
        g.drawImage(Assets.potion, getX(), getY(), null);
        }
    };
    
    public Rectangle Limits(){
        return new Rectangle(getX(), getY(),55,56);
    }
    
     public void checkCollision(){
          ArrayList<Potion> dropPotions = GameState.dropPotions;
          if(GameState.player.Limits().intersects(Limits())){
                GameState.slots.store(Assets.potion,"potion");
                visible = false;
                for (int i = 0; i < dropPotions.size(); i++) {
                  if(visible == false){
                  dropPotions.remove(dropPotions.get(i));//mata ele   
                 }
                }
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

    /**
     * @return the fall
     */
    public int getFall() {
        return fall;
    }

    /**
     * @param fall the fall to set
     */
    public void setFall(int fall) {
        this.fall = fall;
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
     
     
    
}
