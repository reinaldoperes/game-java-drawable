/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.awt.image.BufferedImage;

/**
 *
 * @author Devisate
 */
public class Assets {
    //essa classe meramente prepara as imagens q vao ser usadas
    //acho q aki pode deixar static e public porque fica + facil
    public static BufferedImage  sprtest,sprtest2,tile1,tile2,tile3,tile4,tile5,tile6,tile7,
     tile8,tile9,tile10,tile11,tile12,bg,coin,fall,jump,jump2,hinotama1,hinotama11,
     hinotamaa1,hinotamaa11,hinotamah1,bhinotama1,ghinotama1,slot,potion,gate;
    public static BufferedImage[] pwalk,pwalk2,patack,patack2,Rpidle,Lpidle;
   
 
    public static void init(){
        Rpidle = new BufferedImage[2];
        Lpidle = new BufferedImage[2];
        pwalk = new BufferedImage[4];
        pwalk2 = new BufferedImage[4];
        patack = new BufferedImage[2];
        patack2 = new BufferedImage[2];
  
        //imagem do personagi parado olhando pra direita
       //imagem do personagi parado olhando pra direita
       Rpidle[0] =  ImageLoader.loadImage(".//data//spr1.png");
       Rpidle[1] =  ImageLoader.loadImage(".//data//spr1.png");
        //imagem do personagi parado olhando pra esquerda
       Lpidle[0] =  ImageLoader.loadImage(".//data//spr11.png");
       Lpidle[1] =  ImageLoader.loadImage(".//data//spr11.png");
         
        //toda sequencia de imagens da animaçao de andar pra direita
        pwalk[0] =  ImageLoader.loadImage(".//data//sprw1.png");
        pwalk[1] =  ImageLoader.loadImage(".//data//sprw2.png");
        pwalk[2] =  ImageLoader.loadImage(".//data//sprw3.png");
        pwalk[3] =  ImageLoader.loadImage(".//data//sprw4.png");
       
        
        //toda sequencia de imagens da animaçao de andar pra esquerda
        pwalk2[0] =  ImageLoader.loadImage(".//data//sprw11.png");
        pwalk2[1] =  ImageLoader.loadImage(".//data//sprw22.png");
        pwalk2[2] =  ImageLoader.loadImage(".//data//sprw33.png");
        pwalk2[3] =  ImageLoader.loadImage(".//data//sprw44.png");
        
        
        //imagens da animaçao de ataque
        patack[0] =  ImageLoader.loadImage(".//data//spra1.png");
        patack[1] =  ImageLoader.loadImage(".//data//spra2.png");
        patack2[0] =  ImageLoader.loadImage(".//data//spra11.png");
        patack2[1] =  ImageLoader.loadImage(".//data//spra22.png");
        
       
        //tile
        tile1 =  ImageLoader.loadImage(".//data//tile1.png");
        tile2 =  ImageLoader.loadImage(".//data//tile2.png");
        tile3 =  ImageLoader.loadImage(".//data//tile3.png");
        tile4 =  ImageLoader.loadImage(".//data//tile4.png");
        tile5 =  ImageLoader.loadImage(".//data//tile5.png");
        tile6 =  ImageLoader.loadImage(".//data//tile6.png");
        tile7 =  ImageLoader.loadImage(".//data//tile7.png");
        tile8 =  ImageLoader.loadImage(".//data//tile8.png");
        tile9 =  ImageLoader.loadImage(".//data//tile9.png");
        tile10 =  ImageLoader.loadImage(".//data//tile10.png");
        tile11 =  ImageLoader.loadImage(".//data//tile11.png");
        tile12 =  ImageLoader.loadImage(".//data//tile12.png");
        
        bg =  ImageLoader.loadImage(".//data//back.png");
        coin =  ImageLoader.loadImage(".//data//coin.png"); 
        hinotama1 =  ImageLoader.loadImage(".//data//hinotama1.png");
        hinotama11 =  ImageLoader.loadImage(".//data//hinotama11.png"); 
        hinotamaa1 =  ImageLoader.loadImage(".//data//hinotamaa1.png");
        hinotamaa11 =  ImageLoader.loadImage(".//data//hinotamaa11.png");
        hinotamah1 =  ImageLoader.loadImage(".//data//hinotamah1.png");
        bhinotama1 = ImageLoader.loadImage(".//data//bhinotama1.png");
        ghinotama1 = ImageLoader.loadImage(".//data//ghinotama1.png");
        gate = ImageLoader.loadImage(".//data//3.png");
        
        potion = ImageLoader.loadImage(".//data//1.png");
      
       
    }
}
