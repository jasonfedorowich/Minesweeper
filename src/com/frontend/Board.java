package com.frontend;



import com.grid.Block;
import com.main.Game;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Board {

    private JButton[][] buttons;
    private JFrame frame;
    private JPanel panel;
    private int n;


    private static Board instance;


    public Board(int n){
        frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);


        this.n = n;
        panel = new JPanel();
        panel.setLayout(new GridLayout(n, n));

        init();
        frame.setVisible(false);
        frame.add(panel);




    }

    public void setVisible(){

        frame.setVisible(true);
    }

    public void repaint(){
        long m1 = System.currentTimeMillis();

        panel.repaint();
        frame.repaint();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n;j++){
                buttons[i][j].repaint();
            }
        }
        long m2 = System.currentTimeMillis();
        System.out.println(m2 - m1);

    }



    public void setSize(int n){
        frame.remove(panel);
        panel = new JPanel();
        panel.setLayout(new GridLayout(n, n));
        init();


    }


    public void init(){
        reset();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){

                panel.add(buttons[i][j]);
            }
        }
    }

    public void reset(){
        buttons = new JButton[n][n];

        for (int i = 0; i < n; i++){
            buttons[i] = new JButton[n];

            for(int j = 0; j < n; j++){
                buttons[i][j] = new MSButton(i, j);


            }
        }



    }

    public void addButtonInfo(int i, int j, Block block){
        if(i >= n || i < 0 || j >= n || j < 0)
            throw new ArrayIndexOutOfBoundsException("Out of bounds call made");
        ((MSButton)buttons[i][j]).addInfo(block);
    }

    public JButton get(int i, int j){
        if(i >= n || i < 0 || j >= n || j < 0)
            throw new ArrayIndexOutOfBoundsException("Out of bounds call made");
        return this.buttons[i][j];

    }

}
