/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Main.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author weeec
 */
public class MidGame {
        int mX;
        int mY;
        
        Font f;
       
	GameStateManager gsm;
	public MidGame(GameStateManager gsm) {
		this.gsm = gsm;
	}
	
	public void init() {
           
        }
        
       
	
	
	public void update() {
		
	}
        
    
        
        public Rectangle Screen(){
            return new Rectangle(280,90,250,150);
        }
        
        public Rectangle HomeButton(){
            return new Rectangle(305,120,200,30);
        }
        
        
        public Rectangle ResumeButton(){
            return new Rectangle(305,170,200,30);
        }
	
                
	public void draw(Graphics2D g) {
            //tela
            mX =  JanusPanel.mousex;
            mY = JanusPanel.mousey;
            g.setColor(Color.green);
            g.fill(Screen());
            
            //botao HOME
	    if((mX>HomeButton().x && mX<(HomeButton().x + HomeButton().width))&&
                    (mY>HomeButton().y && mY<(HomeButton().y + HomeButton().height))){
               
                g.setColor(Color.black);
            }else{
                g.setColor(Color.gray);
            }
            g.fill(HomeButton());
            f = new Font("Arial", Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Home", 390, 140);
            
         
            //botao DUNGEONS
           
            if((mX>ResumeButton().x && mX<(HomeButton().x + ResumeButton().width))&&
                    (mY>ResumeButton().y && mY<(ResumeButton().y + ResumeButton().height))){
               g.setColor(Color.black);
            }else{
                g.setColor(Color.gray);
            }
            g.fill(ResumeButton());
            f = new Font("Arial", Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Resume", 390, 190);
            
            g.setColor(Color.black);
           
            
           
	}
	
	
	public void keyPressed(int k) {
		
	}
	public void keyReleased(int k) {}
        
        public void mouseClick(){
            
        }
        
        public void onClick(){
            if((mX>HomeButton().x && mX<(HomeButton().x + HomeButton().width))&&
                    (mY>HomeButton().y && mY<(HomeButton().y + HomeButton().height))){
               gsm.unloadState(GameStateManager.Stage1);
               gsm.setState(GameStateManager.MainMenu, 0);
               GameState.midgame = false;
            }
            if((mX>ResumeButton().x && mX<(HomeButton().x + ResumeButton().width))&&
                    (mY>ResumeButton().y && mY<(ResumeButton().y + ResumeButton().height))){
              
               GameState.midgame = false;
            }
        }
	
}
