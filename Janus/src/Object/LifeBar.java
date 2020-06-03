/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Devisate
 */
public class LifeBar {
    private int x;
    private int  y;
    private double totalLife;
    private double currentLife;
    private double width;
    private double percentDmg;
    private double percentHeal;
    
    public LifeBar(double totalLife){
        this.x = 10;
        this.y = 10;
        this.totalLife = totalLife;
        currentLife = totalLife;
        width = 200;
    }
    
    public void update(){
        if(getCurrentLife()<0)setCurrentLife(0);
            
        
        if(getWidth() > 200)setWidth(200);
    }
    
    public void draw(Graphics2D g){
        if(getWidth() > 100) g.setColor(Color.green);
        if(getWidth() <= 100) g.setColor(Color.yellow);
        if(getWidth() < 50) g.setColor(Color.red);
        g.fillRect(getX(), getY(), (int)getWidth(), 20);
        g.setColor(Color.black);
        g.drawRect(getX(), getY(), 200, 20);
         Font f = new Font("Arial", Font.BOLD, 12);
                    g.setFont(f);
                    g.drawString((Integer.toString((int)getCurrentLife()))+"/"+Integer.toString((int)getTotalLife()), 80, 25);
    }
    
    public void damage(double x){
        setCurrentLife(getCurrentLife() - x);
       
        setWidth(200 - (((getTotalLife() - getCurrentLife()) / getTotalLife()) * 100) * 2);
        
       
    }
    
    public void heal(int x){
        setCurrentLife(getCurrentLife() + x);
        if(getCurrentLife() > getTotalLife())setCurrentLife(getTotalLife());
        setWidth(200 - (((getTotalLife() - getCurrentLife()) / getTotalLife()) * 100) * 2);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getTotalLife() {
        return totalLife;
    }

    public void setTotalLife(double totalLife) {
        this.totalLife = totalLife;
    }

    public double getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(double currentLife) {
        this.currentLife = currentLife;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getPercentDmg() {
        return percentDmg;
    }

    public void setPercentDmg(double percentDmg) {
        this.percentDmg = percentDmg;
    }

    public double getPercentHeal() {
        return percentHeal;
    }

    public void setPercentHeal(double percentHeal) {
        this.percentHeal = percentHeal;
    }
    
    
}
