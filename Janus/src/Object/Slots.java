/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;
import Database.ItemDAO;
import Graphic.ImageLoader;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import GameState.*;
import Graphic.Assets;

/**
 *
 * @author weeec
 */
public class Slots {
    BufferedImage[] images = new BufferedImage[3];//array de coraçoes,maximo no jogo = 10;
    int[] qntd = new int[3];
    String[] item = new String[3];
    ItemDAO dao;  
    BufferedImage slot = ImageLoader.loadImage(".//data//slot.png");;
    public int dw = 100;
    
    public Slots (){
        try {
             this.dao = new ItemDAO();
             dao.SelectItem();
             //System.out.println(dao.itemid[0]);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
          
       
         
          item[0] = "";
          item[1] = "";
          item[2] = "";
          qntd[0] = 0;
          qntd[1] = 0;
          qntd[2] = 0;
          for(int i = 0;i<3;i++){
        
            images[i] = slot;
          }
    }
    
    public void draw(Graphics2D g2d){
       
       if(dao.itemid[0] == 1){
          images[0] = Assets.potion;
          qntd[0] = dao.itemqntd[0];
       }
       if(dao.itemid[1] == 1){
           images[1] = Assets.potion;
           qntd[1] = dao.itemqntd[1];
       }
       if(dao.itemid[2] == 1){
           images[2] = Assets.potion;
           qntd[2] = dao.itemqntd[2];
       }
     //coloca na tela as imagens das vidas
      for(int i = 0;i<3;i++){  
      
     g2d.drawImage(images[i],i*70,dw,null);//sempre a proxima imagem será mais para o lado que á ultima,para não se sobreporem
     Font f = new Font("Arial", Font.BOLD, 10);
        g2d.setFont(f);
        if(qntd[i] != 0){
        g2d.drawString(Integer.toString(qntd[i]),i*70,110);
        }
      
      }
  
  }
   
     public void draw2(Graphics2D g2d){
       
       if(dao.itemid[0] == 1){
          images[0] = Assets.potion;
          qntd[0] = dao.itemqntd[0];
       }
       if(dao.itemid[1] == 1){
           images[1] = Assets.potion;
           qntd[1] = dao.itemqntd[1];
       }
       if(dao.itemid[2] == 1){
           images[2] = Assets.potion;
           qntd[2] = dao.itemqntd[2];
       }
     //coloca na tela as imagens das vidas
      for(int i = 0;i<3;i++){  
      
     g2d.drawImage(images[i],125+(i*100),460,null);//sempre a proxima imagem será mais para o lado que á ultima,para não se sobreporem
     Font f = new Font("Arial", Font.BOLD, 10);
        g2d.setFont(f);
        if(qntd[i] != 0){
        g2d.drawString(Integer.toString(qntd[i]),125+i*100,470);
        }
      
      }
  
  }
   public void store(BufferedImage img,String it){//trocar esse it pelo id do item
            
            if(images[0]==slot || images[0]==img){
             images[0] = img;
             qntd[0]++;
             item[0] = it;
                try {
                    dao.UpdateItem(1, qntd[0], 1);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
             
            }else if(images[1]==slot || images[1]==img){
             images[1] = img;
             qntd[1]++;
             item[1] = it;
             try {
                    dao.UpdateItem(2, qntd[1], 1);
                } catch (Exception e) {
                }
            }else if(images[2]==slot || images[2]==img){
             images[2] = img;
             qntd[2]++;
             item[2] = it;
             try {
                    dao.UpdateItem(3, qntd[2], 1);
                } catch (Exception e) {
                }
            }
            try {
             dao.SelectItem();
       } catch (Exception e) {
       }
            
     }
   
   public void use(int x){
       
        if(images[x-1] != slot){
            
            if(dao.itemid[0] == 1){
              
                GameState.player.getLifebar().heal(10);
                qntd[x-1] --;
                 try {
                    dao.UpdateItem(x, qntd[x-1], 1);
                } catch (Exception e) {
                     System.out.println(e.getMessage());
                }
            }
            
        }
       
            if(qntd[x-1]<=0){
                images[x-1]= slot;
                item[x-1] = "";
                qntd[x-1] = 0;
                  try {
                    dao.DItem(x);
                } catch (Exception e) {
                     System.out.println(e.getMessage());
                }
            }
            try {
           dao.SelectItem();
       } catch (Exception e) {
       }
        
   }
    
}
