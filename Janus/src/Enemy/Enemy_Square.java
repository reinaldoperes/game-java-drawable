/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;
import Database.QuestionDAO;
import Graphic.Assets;
import GameState.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Devisate
 */
public class Enemy_Square extends Monster{
    
      QuestionDAO questDAO;
      public static int count = 0;
      private boolean done = false;
     public Enemy_Square(int x,int y){
        
        this.type = 2;
        this.x = x;
        this.y = y;
        this.life = 3;
        this.currentLife = life;
        this.width = 100;
        this.drop = 2;
        try {                 
                    questDAO = new QuestionDAO();          
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
    }
     
      public void update(){
        if(this.width <= 0){
            done = true;
        }
        gravity();
        checkPlayerCollision();
     }
      
     //renderiza
    public void draw(Graphics2D g){
        g.drawImage(Assets.bhinotama1, x, y,130,110,null);
       
        if(width > 50) g.setColor(Color.green);
         if(width <= 50) g.setColor(Color.yellow);
         if(width < 25) g.setColor(Color.red);
         g.fillRect(x+30, y-20, (int)width, 10);
         g.setColor(Color.black);
         g.drawRect(x+30, y-20, 100, 10);
    } 
    
     public Rectangle Limits(){
        return new Rectangle(x-30,y,160,150);
    }
    
    public void gravity(){
           y += fall;
        }
    
    public void damage(double x){
        
        currentLife -= x;       
        width = 100 - (((life-currentLife)/life)*100);  
    }
    
     public void checkPlayerCollision(){
        //se o retangulo do personsion(){
        //se o retangulo do personagem colidir com o do inimigo 
            if(Limits().intersects(GameState.player.Limits())){
                
                if(!GameState.quizz && count<=3){
                count++;
                GameState.quizz = true;
                 try {
                         
                    GameState.questDAO.showQuestion();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            }
                //joga o personagem para trás e perde vida
               /* if(x < GameState.player.getX()){
                  if(!GameState.player.isMovemap()){
                   GameState.player.setX(GameState.player.getX()+50);
                   GameState.player.setCheckpos(GameState.player.getCheckpos()+50);
                  }else{
                     GameState.player.moveMap(50);
                  } 
                } 
                
                if(x > GameState.player.getX()){
                  if(!GameState.player.isMovemap()){
                     GameState.player.setX(GameState.player.getX()-50);
                     GameState.player.setCheckpos(GameState.player.getCheckpos()-50);
                  }else{
                     GameState.player.moveMap(-50);
                  }
            }
                GameState.player.getLifebar().damage(20);//colidir no inimigo te tira vida
           }
            else if(Limits().intersects(GameState.player.landLimits())){
                 GameState.player.setVy(0);
             }*/
               
          
    }
    

