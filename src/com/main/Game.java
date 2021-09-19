package com.main;

import com.frontend.Board;
import com.grid.Grid;

public class Game {

    int DEFAULT_SIZE = 25;
    int DEFAULT_MINES = 75;

    public int n, m;
    public Board board;
    public Grid grid;
    public boolean initialized;
    private static Game instance;

    public Game(){
        reset(DEFAULT_SIZE, DEFAULT_MINES);

    }
    public void reset(int n, int m){
        this.n = n;
        this.m = m;
        grid = new Grid(n, m);
        board = new Board(n);
        init();

    }
    public void init(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++) {
                board.addButtonInfo(i, j, grid.get(i, j));
            }
        }

        board.setVisible();
        initialized = true;

    }

    public static Game getInstance(){
        if(instance == null)
            instance = new Game();
        return instance;

    }

    public void gameOver() {
        this.grid.flipAll();
    }
}
