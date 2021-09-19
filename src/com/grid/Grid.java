package com.grid;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
    private Block[][] blocks;
    private int n;
    private int m;

    public Grid(int n, int m){
        this.n = n;
        this.m = m;
        blocks = new Block[n][n];
        init();

    }
    public void setSize(int n, int m){
        this.n = n;
        this.m = m;
        blocks = new Block[n][n];
        init();

    }

    private void init(){
        for(int i = 0; i < n; i++){
            blocks[i] = new Block[n];
            for(int j = 0; j < n; j++){
                blocks[i][j] = new Block();
            }
        }
        addMines(m);

    }
    private Pair<Integer, Integer> placeMine(){
        while(true){
            Random r1 = new Random();
            Random r2 = new Random();
            int i = r1.nextInt(n);
            int j = r2.nextInt(n);
            if(blocks[i][j].getType() == BlockType.MINE)
                continue;
            else{
                blocks[i][j].makeMine();
                return new Pair<>(i, j);
            }


        }
    }

    private void updateGrid(Pair<Integer, Integer> p){
        for(Direction d: Direction.values()){
            int i = p.getKey() + d.pair.getKey();
            int j = p.getValue() + d.pair.getValue();
            if(i >= n || i < 0 || j >= n || j < 0)
                continue;
            Block block = blocks[i][j];
            if(block.getType() != BlockType.MINE){
                block.increment();
            }
        }


    }
    public void addMines(int m){

        List<Pair<Integer, Integer>> mines = new ArrayList<>();
        while(m > 0){
            Pair<Integer, Integer> p = placeMine();
            mines.add(p);
            m--;

        }
        for(int i = 0; i < mines.size(); i++){
            updateGrid(mines.get(i));

        }



    }
    private void dfs(int i, int j){
        if(i >= n || i < 0 || j >= n || j < 0)
            return;
        if(blocks[i][j].getType() == BlockType.NUMBER){
            blocks[i][j].toggleState();
            return;
        }
        if(blocks[i][j].getState() == BlockState.SEEN)
            return;
        else{
            blocks[i][j].toggleState();
            int r;
            int c;
            for(Direction d: Direction.values()){
                r = i + d.pair.getKey();
                c = j + d.pair.getValue();
                if(r >= n || r < 0 || c >= n || c < 0)
                    continue;
                dfs(r, c);
            }
        }

    }
    public boolean toggle(int i, int j){
        if(blocks[i][j].getType() == BlockType.MINE)
            return true;

        dfs(i, j);
        return false;

    }

    public Block get(int i, int j){
        if(i >= n || i < 0 || j >= n || j < 0)
            throw new ArrayIndexOutOfBoundsException("Out of bounds call made");
        return blocks[i][j];
    }

    public void flipAll(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                this.blocks[i][j].toggleState();

            }
        }

    }

}
