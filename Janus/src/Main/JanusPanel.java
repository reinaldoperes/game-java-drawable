/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author weeec
 */
import Database.Usuario;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import GameState.GameStateManager;

@SuppressWarnings("serial")
public class JanusPanel extends JPanel
	implements Runnable, KeyListener, MouseListener, MouseMotionListener{
	
	// dimensions
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static int mousex;
        public static int mousey;
        
	
	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// image
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;
        
	Usuario usuario;
	public JanusPanel(Usuario usuario) {
                super();
                this.usuario = usuario;
                addKeyListener(this);
                addMouseListener(this);
                addMouseMotionListener(this);
		gsm = new GameStateManager(usuario);
		
                setPreferredSize(
			new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
                
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			
			thread.start();
		}
	}
	
	private void init() {
		
                
		image = new BufferedImage(
					WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB
				);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		
	}
	
        @Override
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			update();
			
			start = System.nanoTime();
			
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try {
                        //System.out.println(wait);
			
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	private void update() {
		gsm.update();
	}
	private void draw() {
		gsm.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				WIDTH, HEIGHT,
				null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	public void keyPressed(KeyEvent key) {
		gsm.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		gsm.keyReleased(key.getKeyCode());
	}

    @Override
    public void mouseClicked(MouseEvent e) {
                gsm.mouseClick();
                
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
       
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       
           mousex = e.getX();
           mousey = e.getY();
           gsm.mouseMove(e.getX(),e.getY());
    }
        

}
