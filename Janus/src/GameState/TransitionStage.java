/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author weeec
 */
public class TransitionStage extends GameState{
     
    private int destiny;
    private long startTime;
    private long now;
    int i = 0;
    Font f;
    public TransitionStage(GameStateManager gsm,int destiny) {
                this.destiny = destiny;
		this.gsm = gsm;
               
	}
    
    
    
    @Override
    public void init() {
       startTime = System.nanoTime();
    }
    
    

    @Override
    public void update() {
         //MELHORA ESSA BOSTA LOGO
          i++;
          if(i > 85){
              
            gsm.setState(destiny,destiny);
            
            }
          
    }

     public Rectangle Screen(){
            return new Rectangle(0,0,800,600);
        }
    @Override
    public void draw(Graphics2D g) {
            g.setColor(Color.black);
            g.fill(Screen());
            f = new Font("Arial", Font.BOLD, 50);
            g.setFont(f);
            g.setColor(Color.white);
            String str = gsm.names[destiny-4];
            g.drawString(str, 300, 60);
    }

    @Override
    public void keyPressed(int k) {
        
    }

    @Override
    public void keyReleased(int k) {
       
    }
    
}
