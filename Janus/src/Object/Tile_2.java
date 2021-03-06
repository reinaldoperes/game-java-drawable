/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import GameState.*;
import Graphic.*;
import Enemy.Monster;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author weeec
 */
public class Tile_2 extends Tile{
    
  
    public Tile_2(int x,int y){
        this.x = x;
        this.y = y;
    }
     //atualiza
    public void update(){
        checkPlayerCollision();
        checkEnemyCollision();
        checkCoinCollision();
        
    }
    
    //renderiza
    public void draw(Graphics2D g){
          g.drawImage(Assets.tile2, x, y, null);  
    }
    
    //define um retangulo atrelado ao inimigo para checar colisões
    public Rectangle Limits(){
        return new Rectangle(x+25,y+30,380,300);
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
        if(Limits().intersects(GameState.player.Limits())){
         
           
              if(!GameState.player.isMovemap()){
                GameState.player.setX(GameState.player.getX() - GameState.player.getVx());
                GameState.player.setCheckpos(GameState.player.getCheckpos() - GameState.player.getVx());
                
              }else{
                GameState.player.moveMap(GameState.player.getVx()*-1);
              }
            }       
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
    
    
}
