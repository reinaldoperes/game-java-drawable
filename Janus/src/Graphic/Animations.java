/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphic;

import java.awt.image.BufferedImage;

/**
 *
 * @author weeec
 */
public class Animations {
	
	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	private boolean playedOnce;
	
	public Animations() {
		playedOnce = false;
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = System.nanoTime();
		playedOnce = false;
	}
	
	public void setDelay(long d) { delay = d; }
	public void setFrame(int i) { currentFrame = i; }
	
	public void update() {
                
		
		if(delay == -1) return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == getFrames().length) {
                        playedOnce = true;
                        if((frames == Assets.patack || frames == Assets.patack2)&& playedOnce){
                           if(frames == Assets.patack){
                               setFrames(Assets.Rpidle);
                               setDelay(10);
                           }else{
                               setFrames(Assets.Lpidle);
                               setDelay(10);
                           }
                         }else{
			currentFrame = 0;
                        }
			
		}
                
	}
	
	public int getFrame() { return currentFrame; }
        public int getF() { return getFrames().length; }
	public BufferedImage getImage() { return getFrames()[currentFrame]; }
	public boolean hasPlayedOnce() { return playedOnce; }

        public BufferedImage[] getFrames() {
            return frames;
        }
	
}
