/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;
import Char.*;
import Database.Question;
import Database.QuestionDAO;
import Database.Usuario;
import Enemy.Monster;
import Object.Gate;
import Object.Potion;
import Object.Slots;
import Object.StageCoin;
import Object.Tile;
import java.util.ArrayList;
/**
 *
 * @author weeec
 */
public abstract class GameState {
	public static QuestionDAO questDAO;
        Question quest;
         public static boolean frami = false;
         public static boolean quizz =false;
	public static Gate gate1;
        public static ArrayList<Monster> enemies;
        public static ArrayList<Tile> tiles;
        public static ArrayList<StageCoin> dropCoins;
        public static ArrayList<Potion> dropPotions;
        public static Slots slots;
        public static MidGame mid;
        public static boolean fell;
        public static boolean midgame;
	protected GameStateManager gsm;
        public static Player player;
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
        Usuario usuario;


    void mouseClick() {
       
    }

    void mouseMoved(int x,int y) {
        
    }

    
  
    
	
}
