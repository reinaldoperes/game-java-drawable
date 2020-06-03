/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemy;

import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Devisate
 */
public abstract class Monster {
    public int type;
    protected double life;
    protected int x;
    protected int y;
    protected int fall = 3;
    protected double currentLife;
    protected double width;
    public int drop;
    
    public void update(){
         
    }
    
    public void draw(Graphics2D g){
        
    }
    
    public void damage(double x){
        
    }
    public Rectangle Limits(){
        return null;
    }

    /**
     * @return the life
     */
    public double getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(double life) {
        this.life = life;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the fall
     */
    public int getFall() {
        return fall;
    }

    /**
     * @param fall the fall to set
     */
    public void setFall(int fall) {
        this.fall = fall;
    }

    /**
     * @return the currentLife
     */
    public double getCurrentLife() {
        return currentLife;
    }

    /**
     * @param currentLife the currentLife to set
     */
    public void setCurrentLife(double currentLife) {
        this.currentLife = currentLife;
    }

    /**
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(double width) {
        this.width = width;
    }
    
    
}
