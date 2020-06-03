
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
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author weeec
 */
public class DungeonsMenu extends GameState {
	
	
	

	int mouseX;
        int mouseY;
        Font f;
        Color c;
        BufferedImage dungeon;
	
	public DungeonsMenu(GameStateManager gsm) {
		
		this.gsm = gsm;
              try {
                dungeon = ImageIO.read(new File(".//data//TELA 2.png"));
            } catch (Exception e) {
                  e.printStackTrace();
                  System.out.println(e.getMessage());
            }	
	}
        
         public void mouseMoved(int x,int y){
            mouseX = x;
            mouseY = y;
        }
	
	
	public void init() {}
	
	public void update() {
		
	}
        
       public Rectangle MouseStalker(){
          return new Rectangle(mouseX,mouseY,1,1);
       }
        
        public Rectangle Screen(){
            return new Rectangle(0,0,800,600);
        }
        
        public Rectangle HomeButton(){
            return new Rectangle(0,0,400,30);
        }
        
        
        public Rectangle DungeonsButton(){
            return new Rectangle(400,0,400,30);
        }
	
        public Rectangle ButtonStage1(){
            return new Rectangle(300,200,200,40);
        }
        
        public Rectangle ButtonStage2(){
            return new Rectangle(300,350,200,40);
        }
                
	public void draw(Graphics2D g) {
            //tela
            g.setColor(Color.white);
            g.fill(Screen());
            g.drawImage(dungeon, 0,0,null);
            
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
            
            
            
            //botao DUNGEONS
            
            g.setColor(Color.black);
            g.fill(DungeonsButton());
            f = new Font("Arial", Font.BOLD, 10);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Dungeons", 600, 20);
            
            //botao Stage1
            if(MouseStalker().intersects(ButtonStage1())){
             c = new Color(131 ,111 ,255);
             g.setColor(c);
            }else{
             c = new Color(159, 182, 205);
             g.setColor(c);   
            }
            g.fill(ButtonStage1());
            f = new Font("Arial", Font.BOLD, 30);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Stage1", 350, 230);
            
             if(MouseStalker().intersects(ButtonStage2())){
             c = new Color(131 ,111 ,255);
             g.setColor(c);
            }else{
             c = new Color(159, 182, 205);
             g.setColor(c);   
            }
            g.fill(ButtonStage2());
            f = new Font("Arial", Font.BOLD, 30);
            g.setFont(f);
            g.setColor(Color.white);
            g.drawString("Stage2", 350, 380);
	}
	
	
	public void keyPressed(int k) {
		
	}
	public void keyReleased(int k) {}
        
        public void mouseClick(){
            if(MouseStalker().intersects(ButtonStage1())){
            gsm.setState(GameStateManager.Transition,GameStateManager.Stage1);
            }
            if(MouseStalker().intersects(ButtonStage2())){
            gsm.setState(GameStateManager.Transition,GameStateManager.Stage2);
            }
             if(MouseStalker().intersects(DungeonsButton())){
            gsm.setState(GameStateManager.DungeonsMenu,0);
            }
            
            if(MouseStalker().intersects(HomeButton())){
            gsm.setState(GameStateManager.MainMenu,0);
            }
        }
	
}
