/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Char.Player;
import Database.UsuarioDAO;
import Database.Question;
import Database.QuestionDAO;
import Enemy.Enemy_Hinotama;
import Enemy.Enemy_Square;
import Enemy.Golden_Hinotama;
import Enemy.Monster;
import static GameState.GameState.dropPotions;
import static GameState.GameState.enemies;
import Graphic.Assets;
import Main.JanusPanel;
import Object.Gate;
import Object.Potion;
import Object.Slots;
import Object.StageCoin;
import Object.Tile;
import Object.Tile_1;
import Object.Tile_10;
import Object.Tile_11;
import Object.Tile_12;
import Object.Tile_2;
import Object.Tile_3;
import Object.Tile_4;
import Object.Tile_5;
import Object.Tile_6;
import Object.Tile_7;
import Object.Tile_8;
import Object.Tile_9;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author weeec
 */
public class Stage2 extends GameState {
   
   
   UsuarioDAO dao;
   public static boolean quizz2 = false;
    
   
  
    
    public Stage2(GameStateManager gsm) {
            Enemy_Square.count = 0;
                try {
            dao = new UsuarioDAO();
            questDAO = new QuestionDAO();
            quest = new Question();
            //Conexao.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
               
        gate1 = new Gate(5000, 325, 4, gsm);
        enemies = new ArrayList<Monster>();
        tiles = new ArrayList<Tile>();
        dropCoins = new ArrayList<StageCoin>();
        dropPotions = new ArrayList<Potion>();
        slots = new Slots();
        this.gsm = gsm;
        player = new Player(50, 350);
        player.stag = 2;
        mid = new MidGame(gsm);
        midgame = false;
        init();

    }

    public void init() {
        Assets.init();
        CreateMap();

    }

    public void CreateMap() {
        addTile3(new Tile_3(-100, 390));
        //addTile1(new Tile_1(150, 390));
        //addTile1(new Tile_1(400, 390));
        // addTile1(new Tile_1(650, 390));
        
        
        addTile4(new Tile_4(910,390));
        addTile5(new Tile_5(940,117));
        addPotion(new Potion(990,90));
       
        
        addTile6(new Tile_6(1210,217));
        addTile6(new Tile_6(1350,317));
       
       
        addTile7(new Tile_7(1760,390));
        addEnemy2(new Enemy_Hinotama(1890, 250));//*ver função addEnemy2
         addTile8(new Tile_8(2150,390));
          
         addTile6(new Tile_6(2400,300));
         addTile6(new Tile_6(2540,230));
         addTile6(new Tile_6(2680,180));
         addTile10(new Tile_10(2820,130));
        
         addEnemy2(new Enemy_Hinotama(3000, 10));//*ver função addEnemy2
          
           addTile6(new Tile_6(3630,180));  
           
           addTile10(new Tile_10(3790,140));
           
            addEnemy(new Enemy_Square(4050,270));//*ver função addEnemy
          
          addTile9(new Tile_9(2540,390)); 
         
         
        
         
          addTile11(new Tile_11(3274,90)); 
          addEnemy3(new Golden_Hinotama(3100, 200));//*ver função addEnemy2
          
         addTile12(new Tile_12(4330,390));   
          
          
         
         addEnemy2(new Enemy_Hinotama(520, 125));//*ver função addEnemy2
       
        
        
        
        
    }

    public void update() {
         //System.out.println(GameState.player.getX());
        if (player.getLifebar().getCurrentLife() > 0) {
            player.update();
        }

        for (int i = 0; i < enemies.size(); i++) {
            Monster xe = enemies.get(i);
            xe.setFall(1);
        }

        for (int i = 0; i < tiles.size(); i++) {

            Tile te = tiles.get(i);
            te.update();
        }

        for (int i = 0; i < enemies.size(); i++) {
            Monster xe = enemies.get(i);
            xe.update();
        }
        for (int i = 0; i < dropCoins.size(); i++) {
            StageCoin ce = dropCoins.get(i);
            ce.setFall(4);
            ce.update();
        }

        for (int i = 0; i < dropPotions.size(); i++) {
            Potion pe = dropPotions.get(i);
            pe.update();
        }

        if (player.getLifebar().getCurrentLife() <= 0) {
            quizz = false;
            dao.DieCoins();
            gsm.setState(5, 0);
        }

        //drops
        for (int i = 0; i < enemies.size(); i++) {

            if (enemies.get(i).getWidth() == 0) {//se a vida do inimigo acabar,
                quizz = false;
                frami = false;
                if (enemies.get(i).drop == 1) {
                    addCoins(new StageCoin(enemies.get(i).getX(), enemies.get(i).getY()));
                }
                if (enemies.get(i).drop == 2) {
                    dropPotions.add(new Potion(enemies.get(i).getX(), enemies.get(i).getY()));
                }
                enemies.remove(enemies.get(i));//mata ele
               
            }

        }

        player.getLifebar().update();

    }

    public void draw(Graphics2D g) {
        System.out.println(quizz2);
        g.drawImage(Assets.bg, 0, 0, null);//plano de fundo

        slots.draw(g);

        g.drawImage(Assets.coin, 10, 40, 30, 30, null);
        Font f = new Font("Arial", Font.BOLD, 30);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString("X" + Integer.toString(dao.showCoins()), 40, 65);

        gate1.draw(g);
        

        for (int i = 0; i < enemies.size(); i++) {
            Monster xe = enemies.get(i);
            xe.draw(g);
        }

        for (int i = 0; i < tiles.size(); i++) {
            Tile te = tiles.get(i);
            te.draw(g);
        }
        
        if (player.getLifebar().getCurrentLife() > 0) {
            player.draw(g);
        }

        for (int i = 0; i < dropCoins.size(); i++) {
            StageCoin ce = dropCoins.get(i);
            ce.draw(g);
        }

        for (int i = 0; i < dropPotions.size(); i++) {
            Potion ce = dropPotions.get(i);
            ce.draw(g);
        }

        player.getLifebar().draw(g);
        if (midgame) {
            mid.draw(g);

        }
        if ((quizz && !frami)) {
            try {
                player.setMovleft(false);
                player.setMovright(false);
                teste frame = new teste();
        
                frame.setVisible(true);
                 
                frami = true;
            } catch (Exception e) {
            }

        }
        
       
    }

    public void keyPressed(int k) {
        if (!quizz) {
            if (k == KeyEvent.VK_LEFT) {
                player.setMovleft(true);
                player.setRight(false);
                player.setMovright(false);
            }
            if (k == KeyEvent.VK_RIGHT) {
                player.setMovleft(false);
                player.setRight(true);
                player.setMovright(true);
            }
            if (k == KeyEvent.VK_X) {
                gate1.onAction();
                player.setAtack(true);
                //System.out.println(player.getX());
            }
        }
        if (k == KeyEvent.VK_ESCAPE) {
            if (!midgame) {
                midgame = true;
            } else {
                midgame = false;
            }
        }
        if (k == KeyEvent.VK_UP || k == KeyEvent.VK_SPACE) {
            if (!player.isAtack()) {
                if (player.isCanJump() == true) {
                    player.jump();
                }
            }
        }
        if (k == KeyEvent.VK_1) {
            slots.use(1);
        }

    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            player.setMovleft(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            player.setMovright(false);
        }

    }

    public void mouseClick() {
        mid.onClick();
        questDAO.onClick();
    }

    public void addEnemy(Enemy_Square e) {
        enemies.add(e);
    }

    public void addEnemy2(Enemy_Hinotama e) {
        enemies.add(e);
    }
    
    public void addEnemy3(Golden_Hinotama e) {
        enemies.add(e);
    }

    public static void addCoins(StageCoin c) {
        dropCoins.add(c);
    }

    public static void addPotion(Potion p) {
        dropPotions.add(p);
    }

    public void addTile1(Tile_1 t) {
        tiles.add(t);
    }

    public void addTile2(Tile_2 t) {
        tiles.add(t);
    }
    
    public void addTile3(Tile_3 t) {
        tiles.add(t);
    }
    
     public void addTile4(Tile_4 t) {
        tiles.add(t);
    }
     
    public void addTile5(Tile_5 t) {
        tiles.add(t);
    } 
    
    public void addTile6(Tile_6 t) {
        tiles.add(t);
    } 
    
     public void addTile7(Tile_7 t) {
        tiles.add(t);
    }
     
      public void addTile8(Tile_8 t) {
        tiles.add(t);
    }
      
    public void addTile9(Tile_9 t) {
        tiles.add(t);
    }  
    
    public void addTile10(Tile_10 t) {
        tiles.add(t);
    }  
    
    public void addTile11(Tile_11 t) {
        tiles.add(t);
    }  
    
     public void addTile12(Tile_12 t) {
        tiles.add(t);
    }  

}
