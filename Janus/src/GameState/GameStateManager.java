/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import Database.Usuario;

/**
 *
 * @author weeec
 */
public class GameStateManager {

    public static GameState[] gameStates;
    public static String names[];
    public static int currentState;

    public static final int Number = 6;
    public static final int MainMenu = 0;
    public static final int WhatMenu = 1;
    public static final int DungeonsMenu = 2;
    public static final int Transition = 3;
    public static final int Stage1 = 4;
    public static final int Stage2 = 5;
    Usuario usuario;

    public GameStateManager(Usuario usuario) {
        this.usuario = usuario;
        names = new String[2];
        names[0] = "STAGE1";
        names[1] = "STAGE2";
        gameStates = new GameState[Number];

        currentState = MainMenu;
        loadState(currentState, 0);

    }

    private void loadState(int state, int destiny) {
        if (state == Stage1) {
            gameStates[state] = new Stage1(this);
        }
        if (state == Stage2) {
           
            gameStates[state] = new Stage2(this);
        }
        if (state == MainMenu) {
            try {
                gameStates[state] = new MainMenu(this, usuario);
            } catch (Exception e) {
            }
            
        }
        if (state == WhatMenu) {
            gameStates[state] = new WhatMenu(this);
        }
        if (state == DungeonsMenu) {
            gameStates[state] = new DungeonsMenu(this);
        }
        if (state == Transition) {
            gameStates[state] = new TransitionStage(this, destiny);
        }

    }

    public void unloadState(int state) {
        gameStates[state] = null;
    }

    public void setState(int state, int destiny) {
        unloadState(currentState);
        currentState = state;
        loadState(currentState, destiny);
        //gameStates[currentState].init();
    }

    public void update() {
        try {
            gameStates[currentState].update();
        } catch (Exception e) {
        }
    }

    public void draw(java.awt.Graphics2D g) {
        try {
            gameStates[currentState].draw(g);
        } catch (Exception e) {
        }
    }

    public void keyPressed(int k) {
        gameStates[currentState].keyPressed(k);
    }

    public void keyReleased(int k) {
        gameStates[currentState].keyReleased(k);
    }

    public void mouseClick() {
        gameStates[currentState].mouseClick();
    }

    public void mouseMove(int x, int y) {
        gameStates[currentState].mouseMoved(x, y);
    }

}
