/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import Graphic.*;
import GameState.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Devisate
 */
public class Enemy_Hinotama extends Monster {
  
    private long lastTime, timer;
    private boolean atack = false;
    private boolean atacked = false;
    private boolean left;
    private int dx = -1;
    
    public Enemy_Hinotama(int x,int y){
        this.drop = 1;
        this.x = x;
        this.y = y;
        this.life = 3;
        this.currentLife = life;
        this.atack = false;
        this.atacked = false;
        this.width = 100;
        timer = 0;
        lastTime = System.currentTimeMillis();       
    }
     public void update(){
        gravity();
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer>800){
        atack = false;
        }
        if(timer>1000){
        atacked = false;
        timer = 0;
        }
        if(!atacked && !atack)x += dx;
        move();
        if(currentLife<0)currentLife = 0;
        
        checkPlayerCollision();  
       
        
    }
     
    public void draw(Graphics2D g){
         g.drawImage(getCurrentAnimationFrame(), x, y,130,110,null);
         if(width > 50) g.setColor(Color.green);
         if(width <= 50) g.setColor(Color.yellow);
         if(width < 25) g.setColor(Color.red);
         g.fillRect(x, y-20, (int)width, 10);
         g.setColor(Color.black);
         g.drawRect(x, y-20, 100, 10);
         //g.draw(Limits());
        
    }
    
    public Rectangle Limits(){
        return new Rectangle(x+20,y,80,130);
    }
    
    public void gravity(){
           y += fall;
        }
    
    public void damage(double x){
        atacked = true;
        currentLife -= x;
        width = 100 - (((life-currentLife)/life)*100);
        if(!GameState.player.isRight()){
            this.x -= 10;
        }
        if(GameState.player.isRight()){
            this.x += 10;
        }
          
    }
    
    public void checkPlayerCollision(){
        //se o retangulo do personsion(){
        //se o retangulo do personagem colidir com o do inimigo 
            if(Limits().intersects(GameState.player.Limits())){
                //joga o personagem para trás e perde vida
                
                atack = true;
               if(x < GameState.player.getX()){
                  if(GameState.player.isMovemap()){
                   GameState.player.setX(GameState.player.getX()+50);
                   GameState.player.setCheckpos(GameState.player.getCheckpos()+50);
                  }else{
                     GameState.player.moveMap(50);
                  } 
                } 
                
               if(x > GameState.player.getX()){
                  if(!GameState.player.isMovemap()){
                  GameState.player.setX(GameState.player.getX()-50);
                  GameState.player.setCheckpos(GameState.player.getCheckpos()-50) ;
                  }else{
                   
                      GameState.player.moveMap(-50);
                  }
               }
                GameState.player.getLifebar().damage(10);//colidir no inimigo te tira 1vida completa
            }
    }
    
     public void move(){
        for(int i=0;i<GameState.tiles.size();i++){
            if(Limits().intersects(GameState.tiles.get(i).Limits())){
                if(Limits().x <= GameState.tiles.get(i).Limits().x){
                    dx = 1;
                    left = true;
                }
                if(Limits().x+Limits().width>= GameState.tiles.get(i).Limits().x+GameState.tiles.get(i).Limits().width){
                    dx = -1;
                    left = false;
                }
            }
        }
    }
     
     public BufferedImage getCurrentAnimationFrame(){
         if(atacked){
             return Assets.hinotamah1;
         }
         if(atack){
             return Assets.hinotamaa1;
         }
         if(dx > 0){
              return Assets.hinotama11;   
      }else{
               return Assets.hinotama1;   
      }
         
     }
     
}

    
    
    
    
     
     

