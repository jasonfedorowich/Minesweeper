package com.grid;

public class Block {

    private BlockState state;
    private BlockType type;
    private int number;

    public Block(){
        state = BlockState.UNSEEN;
        type = BlockType.NONE;
        number = -1;
    }

    public void toggleState(){
        state = BlockState.SEEN;
    }

    public BlockType getType(){
        return type;
    }
    public BlockState getState(){
        return state;
    }

    public void makeMine(){
        type = BlockType.MINE;

    }

    public int getNumber(){
        return number;
    }

    public void increment(){
        if(type != BlockType.NUMBER){
            type = BlockType.NUMBER;
            number = 1;
        }else{
            number++;

        }
    }




}
