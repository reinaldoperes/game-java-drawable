/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import Enemy.Monster;
import GameState.GameState;
import Graphic.Assets;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author weeec
 */
public class Tile_5 extends Tile{
    public Tile_5(int x,int y){
        this.x = x;
        this.y = y;
    }
    
     public void update(){
        checkPlayerCollision();
        checkEnemyCollision();
        checkCoinCollision();  
        checkPotionCollision();
    }
     
     //renderiza
    public void draw(Graphics2D g){
          g.drawImage(Assets.tile5, x, y-3, null);
    }
    
     public Rectangle Limits(){
        return new Rectangle(x,y,Assets.tile5.getWidth(),10);
    }
    
    public void checkPlayerCollision(){
        if(Limits().intersects(GameState.player.landLimits())){
            GameState.player.setVy(0);
            //corrige se o personagem fosse ficar enfiado no chao
            GameState.player.setVy(GameState.player.getVy()-((GameState.player.landLimits().y)-Limits().y));
            GameState.player.setFalling(false);
            GameState.player.setJumping(false);
            GameState.player.setCanJump(true);
            
        }
        //if(Limits().intersects(GameState.player.Limits())){
         
           
              //if(!GameState.player.isMovemap()){
               // GameState.player.setX(GameState.player.getX() - GameState.player.getVx());
               // GameState.player.setCheckpos(GameState.player.getCheckpos() - GameState.player.getVx());
                
             // }else{
              //  GameState.player.moveMap(GameState.player.getVx()*-1);
             // }
           // }       
        
    }
    
     public void checkEnemyCollision(){
        for(int i=0;i<GameState.enemies.size();i++){
            Monster xe = GameState.enemies.get(i);
             if(Limits().intersects(xe.Limits())){
                
                xe.setFall(0);
             }
        }
        
     }
     
     public void checkCoinCollision(){
        for(int i=0;i<GameState.dropCoins.size();i++){
            StageCoin ce = GameState.dropCoins.get(i);
             if(Limits().intersects(ce.Limits())){
                ce.setFall(0);
                //corrige se fosse ficar enfiado no chao
                ce.setY(ce.getY() - ((ce.Limits().y+ce.Limits().height+2)-Limits().y));
             }
        }
        }
     
      public void checkPotionCollision(){
        for(int i=0;i<GameState.dropPotions.size();i++){
            Potion pe = GameState.dropPotions.get(i);
             if(Limits().intersects(pe.Limits())){
                pe.setFall(0);
                //corrige se fosse ficar enfiado no chao
                pe.setY(pe.getY() - ((pe.Limits().y+pe.Limits().height+2)-Limits().y));
             }
        }
        }
     
}

;