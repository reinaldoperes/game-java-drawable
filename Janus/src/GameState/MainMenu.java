/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Database.Usuario;
import Database.UsuarioDAO;
import Graphic.*;
import Object.Slots;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.imageio.ImageIO;

/**
 *
 * @author weeec
 */
public class MainMenu extends GameState {
    
    Slots slots = new Slots();
    UsuarioDAO dao;
    Usuario usuario;
    int mouseX;
    int mouseY;
    Font f;
    Assets asset;
    BufferedImage hino;
    BufferedImage excalibur;
    String x = "X";
    
    public MainMenu(GameStateManager gsm, Usuario usuario) throws SQLException {
        Assets.init();
        this.usuario = usuario;
        this.gsm = gsm;
        try {
            dao = new UsuarioDAO();
            excalibur = ImageIO.read(new File(".//data//TELA 1.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        init();
    }

    public void init() {
        //System.out.println(usuario.getCoins());
    }

    public void mouseMoved(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

    public void update() {

    }

    public Rectangle MouseStalker() {
        return new Rectangle(mouseX, mouseY, 1, 1);
    }

    public Rectangle Screen() {
        return new Rectangle(0, 0, 800, 600);
    }

    public Rectangle HomeButton(){
            return new Rectangle(0,0,400,30);
        }
        
        
        public Rectangle DungeonsButton(){
            return new Rectangle(400,0,400,30);
        }

    public void draw(Graphics2D g) {
        //tela
        g.setColor(Color.white);
        g.fill(Screen());
        g.drawImage(excalibur, 0, 0, null);

        //botao HOME
        g.setColor(Color.black);
        g.fill(MouseStalker());
        g.fill(HomeButton());
        f = new Font("Arial", Font.BOLD, 10);
        g.setFont(f);
        g.setColor(Color.white);
        g.drawString("Perfil", 100, 20);

        

        //botao DUNGEONS
        if (MouseStalker().intersects(DungeonsButton())) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.gray);
        }
        g.fill(DungeonsButton());
        f = new Font("Arial", Font.BOLD, 10);
        g.setFont(f);
        g.setColor(Color.white);
        g.drawString("Dungeons", 600, 20);

        //texto
        g.setColor(Color.black);
        f = new Font("Courier New", Font.BOLD, 60);
        g.setFont(f);
        g.drawString(usuario.getNome(), 110, 237);
        g.drawString(x + Integer.toString(dao.showCoins()), 250, 333);
        
        slots.draw2(g);
    }

    public void keyPressed(int k) {

    }

    public void keyReleased(int k) {
    }

    public void mouseClick() {
        if (MouseStalker().intersects(DungeonsButton())) {
            gsm.setState(GameStateManager.DungeonsMenu, 0);
        }
       
        if (MouseStalker().intersects(HomeButton())) {
            gsm.setState(GameStateManager.MainMenu, 0);
        }
    }

}
