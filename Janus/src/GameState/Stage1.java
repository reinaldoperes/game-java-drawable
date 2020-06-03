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
import Database.Usuario;
import Enemy.Enemy_Hinotama;
import Enemy.Enemy_Square;
import Enemy.Monster;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import Graphic.*;
import Object.*;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author weeec
 */
public class Stage1 extends GameState {

    UsuarioDAO dao;
    
  

    public Stage1(GameStateManager gsm) {
        try {
            dao = new UsuarioDAO();
            questDAO = new QuestionDAO();
            quest = new Question();
            //Conexao.desconectar();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Enemy_Square.count = 0;
        gate1 = new Gate(2200, 325, 5, gsm);
        enemies = new ArrayList<Monster>();
        tiles = new ArrayList<Tile>();
        dropCoins = new ArrayList<StageCoin>();
        dropPotions = new ArrayList<Potion>();
        slots = new Slots();
        this.gsm = gsm;
        player = new Player(50, 250);
        mid = new MidGame(gsm);
        midgame = false;
        init();
        player.stag = 1;

    }

    public void init() {
        Assets.init();
        CreateMap();

    }

    public void CreateMap() {
        addTile1(new Tile_1(-100, 390));
        addTile1(new Tile_1(0, 420));
        addTile1(new Tile_1(330, 420));
        addTile1(new Tile_1(660, 440));
        addTile1(new Tile_1(960, 420));
        addTile1(new Tile_1(1060, 390));
        addTile1(new Tile_1(1260, 390));
        addTile2(new Tile_2(1560, 390));
        addTile2(new Tile_2(2020, 350));
        addEnemy(new Enemy_Square(780, 250));//*ver função addEnemy
        addEnemy2(new Enemy_Hinotama(420, 325));//*ver função addEnemy2
        addEnemy2(new Enemy_Hinotama(1580, 290));//*ver função addEnemy2 
    }

    public void update() {
       
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
            gsm.setState(4, 0);
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
        g.drawImage(Assets.bg, 0, 0, null);//plano de fundo

        slots.draw(g);

        g.drawImage(Assets.coin, 10, 40, 30, 30, null);
        Font f = new Font("Arial", Font.BOLD, 30);
        g.setFont(f);
        g.setColor(Color.BLACK);
        g.drawString("X" + Integer.toString(dao.showCoins()), 40, 65);

        gate1.draw(g);
        if (player.getLifebar().getCurrentLife() > 0) {
            player.draw(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            Monster xe = enemies.get(i);
            xe.draw(g);
        }

        for (int i = 0; i < tiles.size(); i++) {
            Tile te = tiles.get(i);
            te.draw(g);
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
        if (quizz && !frami) {
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
            }
        }
        if (k == KeyEvent.VK_ESCAPE) {
            if (!midgame) {
                midgame = true;
            } else {
                midgame = false;
            }
        }
        if (k == KeyEvent.VK_UP) {
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

}
