package com.frontend;

import com.grid.Grid;
import com.main.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MSButtonClick implements ActionListener {
    private MSButton button;
    public MSButtonClick(MSButton button){
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Grid grid = Game.getInstance().grid;

        if(grid.toggle(button.i, button.j)){

            Game game = Game.getInstance();
            game.gameOver();

        }
        Board board = Game.getInstance().board;
        board.repaint();



    }
}
