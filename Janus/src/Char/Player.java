/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Char;

//import Audio.AudioPlayer;
import Enemy.Monster;
import Main.*;
import GameState.GameState;
import Graphic.Animations;
import Graphic.Assets;
import Object.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author weeec
 */
public class Player {
    public boolean hito = false;
    private int x;//posicao eixo x
    private int y;//posiçao eixo y
    private int vx=0;
    private int vy=0;
    private boolean right = true;//se estiver virado para direita
    private boolean movright = false;//se estiver se movendo para direita
    private boolean movleft = false;//se estiver se movendo para esquerda
    private boolean atack = false;//se estiver atacando
    private boolean danify = false;//causando dano
    private int checkpos =0;//posicao em relação ao mapa
    private int checkpos2 = 0;
    private boolean movemap =false;
    private boolean falling = true;
    private boolean jumping = false;
    private boolean alive = true;
    private boolean canJump = false;
  
    
    Animations Panimation = new Animations();
    private int Iright = 0;
    private int Ileft = 1;
    private int Wright = 2;
    private int Wleft = 3;
    private int atacking = 100;
    private int Current = 0;
    private LifeBar lifebar;
    public int stag=0;
    
    public Player(int x,int y){
        this.x = x;
        this.y = y;  
        checkpos = x;
        checkpos2 = y;
        Current = Iright;
        Panimation.setFrames(Assets.Rpidle);
        Panimation.setDelay(200);
        lifebar = new LifeBar(100.0);
       
       
    }
   
    public void update(){
      
        NextPosition();
       
        
        setFalling(true);
        setCanJump(false);
       
        if(checkpos2 >= 700){
            lifebar.damage(1000);
        }
        if((getCheckpos() > (JanusPanel.WIDTH/2)-100) && (getCheckpos() < 4700)  ){
            movemap = true;
        }else{
            movemap = false;
        }
        
        if(atack){
             

            if(!jumping){
            vx = 0;
            }
            if(Current != atacking){
                danify = true;
                Current = atacking;
                if(right){
                Panimation.setFrames(Assets.patack);
                Panimation.setDelay(100);
                }else{
                Panimation.setFrames(Assets.patack2);    
                Panimation.setDelay(100);
                }
                
            }else{
                if(Panimation.hasPlayedOnce()){
                    atack = false;
                }
            }
              
            }else if(movright && !jumping){
                    if(Current != Wright){
                        Current = Wright;
                        Panimation.setFrames(Assets.pwalk);
                        Panimation.setDelay(100);
                    } 
            }else if(movleft && !jumping){
                    if(Current != Wleft){
                        Current = Wleft;
                        Panimation.setFrames(Assets.pwalk2);
                        Panimation.setDelay(100);
                    } 
            }else if(jumping || falling)   {
                    if(right){
                        Current = Iright;
                        Panimation.setFrames(Assets.Rpidle);
                        Panimation.setDelay(200);
                    }else{
                        Current = Ileft;
                        Panimation.setFrames(Assets.Lpidle);
                        Panimation.setDelay(200);
                    }
                 
            }else if(!movleft && !right){
                Current = Ileft;
                Panimation.setFrames(Assets.Lpidle);
                Panimation.setDelay(200);
            }else{
                
                Current = Iright;
                Panimation.setFrames(Assets.Rpidle);
                Panimation.setDelay(200);
            }
            if(!GameState.quizz)  { 
            if(!movemap){
                 //if(jumping || falling){
                 //   x+=vx+3;
                 //   checkpos += vx+3; 
                 //}else{
                    x+=vx;
                    checkpos += vx;  
                 //}

             }else{
                 moveMap(getVx());
             }
            }
        Panimation.update();
        checkCollision();
    }
    
    public void draw(Graphics2D g){
        g.drawImage(Panimation.getImage(), x, y, null);
       
        
    }
           
    
    public void NextPosition(){
        if(!GameState.quizz){
        if(movleft){ vx = -4;
        }else if (movright){ vx = 4;
        }else{ vx = 0;} 
        }
         vy = (int) (vy + 2);
           checkpos2 += vy;
           moveMap2(-vy);
           if(vy > 8){
              vy = 8;
          }
    }
    
     public void jump(){
        jumping = true;
        vy -= 22;
        
    }
    
     //define um retangulo no personagem que sera usado na detecção de colisões
    public Rectangle Limits(){
      if(right){
         return new Rectangle(x+65,y,50,135);  
      }else{  
        return new Rectangle(x+65,y,50,135);
      }
    }
    //define um retangulo de colisão na area de ataque do personagem
    public Rectangle AtqRange(){
        //a area de ataque dele muda de lado qndo ele muda de lado
        if(isRight()){
        return new Rectangle(x+130,y+20,30,100);
        }else{
            return new Rectangle(x,y+50,30,100);
        }
    }
    
     public Rectangle landLimits(){
        if(right){
        return new Rectangle(x+70,y+140,40,5);
        }else{
           return new Rectangle(x+70,y+140,40,5);
        }
    }
    
     public void checkCollision(){
        ArrayList<Monster> enemies = GameState.enemies;
        //passa por todos os inimigos do array checando se algum colidiu
        for(int i=0;i<enemies.size();i++){
              if(AtqRange().intersects(enemies.get(i).Limits()) ){
                  if(atack && danify){ 
                    if(Panimation.getImage() == Assets.patack[1] || Panimation.getImage() == Assets.patack2[1]){
                    enemies.get(i).damage(1);//tira 1 de vida
                    GameState.quizz = false;
                    
                    danify = false;
                    }
                }  
            }
              
        }
     }
             
             
        
        
             
   
    
    
    public void moveMap(int m){
         
           
              for(int i=0; i < GameState.tiles.size(); i++){
                       Tile te = GameState.tiles.get(i);
                        te.setX(te.getX()-m);
                    }
               for(int i=0; i < GameState.enemies.size(); i++){
                       Monster xe = GameState.enemies.get(i);
                        xe.setX(xe.getX()-m);
                    }
                for(int i=0; i < GameState.dropCoins.size(); i++){
                       StageCoin ce = GameState.dropCoins.get(i);
                        ce.setX(ce.getX()-m);
                    } 
                for(int i=0; i < GameState.dropPotions.size(); i++){
                       Potion pe = GameState.dropPotions.get(i);
                        pe.setX(pe.getX()-m);
                    } 
                GameState.gate1.setX(GameState.gate1.getX()-m);
                checkpos += m;
        
     }
    
    public void moveMap2(int n){
             if(stag==2){
             if(checkpos2<260){
              for(int i=0; i < GameState.tiles.size(); i++){
                       Tile te = GameState.tiles.get(i);
                        te.setY(te.getY()+n);
                    }
               for(int i=0; i < GameState.enemies.size(); i++){
                       Monster xe = GameState.enemies.get(i);
                        xe.setY(xe.getY()+n);
                    }
                for(int i=0; i < GameState.dropCoins.size(); i++){
                       StageCoin ce = GameState.dropCoins.get(i);
                        ce.setY(ce.getY()+n);
                    } 
                for(int i=0; i < GameState.dropPotions.size(); i++){
                       Potion pe = GameState.dropPotions.get(i);
                        pe.setY(pe.getY()+n);
                    } 
                GameState.gate1.setY(GameState.gate1.getY()+n);
             }else{
                 y = y-n;
             }
             }else{
                  for(int i=0; i < GameState.tiles.size(); i++){
                       Tile te = GameState.tiles.get(i);
                        te.setY(te.getY()+n);
                    }
               for(int i=0; i < GameState.enemies.size(); i++){
                       Monster xe = GameState.enemies.get(i);
                        xe.setY(xe.getY()+n);
                    }
                for(int i=0; i < GameState.dropCoins.size(); i++){
                       StageCoin ce = GameState.dropCoins.get(i);
                        ce.setY(ce.getY()+n);
                    } 
                for(int i=0; i < GameState.dropPotions.size(); i++){
                       Potion pe = GameState.dropPotions.get(i);
                        pe.setY(pe.getY()+n);
                    } 
                GameState.gate1.setY(GameState.gate1.getY()+n);
             }
        
     }
    
    
    public int getX() { return x;}
  
    public void setX(int x) { this.x = x;}

    public int getY() { return y;}

    public void setY(int y) { this.y = y;}

    public int getVx() { return vx;}

    public void setVx(int vx) { this.vx = vx;}
    
    public int getVy() { return vy;}

    public void setVy(int vy) { this.vy = vy;}
    
    public boolean isRight() { return right;}

    public void setRight(boolean right) { this.right = right;}

    public boolean isMovright() { return movright;}

    public void setMovright(boolean movright) {  this.movright = movright;}

    public boolean isMovleft() { return movleft;}

    public void setMovleft(boolean movleft) { this.movleft = movleft;}
    
    public boolean isAtack() { return atack;}

    public void setAtack(boolean atack) { this.atack = atack;}

    public int getCheckpos() { return checkpos;}

    public void setCheckpos(int checkpos) { this.checkpos = checkpos;}
    
     public int getCheckpos2() { return checkpos2;}

    public void setCheckpos2(int checkpos2) { this.checkpos2 = checkpos2;}
    
    public boolean isMovemap(){ return movemap;}
    
    public void setFalling(boolean x){ falling = x;}
    
    public boolean isFalling(){ return falling;}
    
    public void setJumping(boolean x){ jumping = x;}
    
    public boolean isJumping(){ return jumping;}
    
    public void setCanJump(boolean x){ canJump = x;}
    
    public boolean isCanJump(){ return canJump;}
    
    public LifeBar getLifebar(){ return lifebar;}
    
    public void mTest(){
        
    }
            
}
