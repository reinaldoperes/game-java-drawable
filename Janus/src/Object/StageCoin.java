/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;
import GameState.*;
import Graphic.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import Database.*;

/**
 *
 * @author Devisate
 */
public class StageCoin {
    
    private int x;
    private int y;
    private int fall =4;
    Usuario coin = new Usuario();
    UsuarioDAO dao;
    
    public StageCoin(int x,int y){
        try {
            dao = new UsuarioDAO();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        this.x = x;
        this.y = y;
    }
    
    public void update(){
        setY(getY() + getFall());
      checkCollision();
    };
    public void draw(Graphics2D g){
        g.drawImage(Assets.coin, getX(), getY(),30,30, null);
    };
    
    public Rectangle Limits(){
        return new Rectangle(getX(), getY(),30,34);
    }
     
      public void checkCollision(){
        ArrayList<StageCoin> dropCoins = GameState.dropCoins;
        //passa por todos os inimigos do array checando se algum colidiu
        for(int i=0;i<dropCoins.size();i++){
            
            if(GameState.player.Limits().intersects(dropCoins.get(i).Limits())){
                //System.out.println("catou");
                dropCoins.remove(dropCoins.get(i));
                dao.updateCoins();
                  
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

  
    
}
