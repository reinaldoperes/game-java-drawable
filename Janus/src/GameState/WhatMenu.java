package GameState;

import Graphic.Assets;
import Object.Slots;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author weeec
 */
public class WhatMenu extends GameState {
	
	
	
        Slots slots = new Slots();
	int mouseX;
        int mouseY;
        Font f;
	BufferedImage grand;
	public WhatMenu(GameStateManager gsm) {
		Assets.init();
		this.gsm = gsm;
		
		try {
                       grand = ImageIO.read(new File(".//data//grand.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public void init() {
           slots.dw = 400;
           
        }
        
         public void mouseMoved(int x,int y){
            mouseX = x;
            mouseY = y;
        }
	
	
	public void update() {
		
	}
        
       public Rectangle MouseStalker(){
          return new Rectangle(mouseX,mouseY,1,1);
       }
        
        public Rectangle Screen(){
            return new Rectangle(0,0,800,600);
        }
        
        public Rectangle HomeButton(){
            return new Rectangle(1,0,279,30);
        }
        public Rectangle WhatButton(){
            return new Rectangle(260,0,270,30);
        }
        
        public Rectangle DungeonsButton(){
            return new Rectangle(510,0,290,30);
        }
	
                
	public void draw(Graphics2D g) {
            //tela
            g.setColor(Color.gray);
            g.fill(Screen());
          
            
            //botao HOME
	    if(MouseStalker().intersects(HomeButton())){
                g.setColor(Color.blue);
            }else{
                g.setColor(Color.gray);
            }
            g.fill(HomeButton());
            f = new Font("Arial", Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Home", 100, 20);
            
            //botao WHATEVER
            
            g.setColor(Color.black);
            g.fill(WhatButton());
            f = new Font("Arial", Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Whatever", 350, 20);
            
            //botao DUNGEONS
             if(MouseStalker().intersects(DungeonsButton())){
                g.setColor(Color.blue);
            }else{
                g.setColor(Color.gray);
            }
            g.fill(DungeonsButton());
            f = new Font("Arial", Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Dungeons", 600, 20);
             //texto
            f = new Font("Arial", Font.BOLD, 15);
            g.setColor(Color.black);
            //g.drawString("whatever", 350, 200);
           
            
           
	}
	
	
	public void keyPressed(int k) {
		
	}
	public void keyReleased(int k) {}
        
        public void mouseClick(){
            if(MouseStalker().intersects(DungeonsButton())){
            gsm.setState(GameStateManager.DungeonsMenu,0);
            }
            if(MouseStalker().intersects(WhatButton())){
            gsm.setState(GameStateManager.WhatMenu,0);
            }
            if(MouseStalker().intersects(HomeButton())){
            gsm.setState(GameStateManager.MainMenu,0);
            }
        }
	
}