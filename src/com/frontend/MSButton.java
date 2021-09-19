package com.frontend;

import com.grid.Block;
import com.grid.BlockState;
import com.grid.BlockType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MSButton extends JButton {
    public int i;
    public int j;
    public Block block;
    private Image image;
    private Image scaled;

    public MSButton(int i, int j){
        this.i = i;
        this.j = j;
        this.addComponentListener(new ResizeListener(this));
        this.addActionListener(new MSButtonClick(this));

    }

    public void addInfo(Block block){
        this.block = block;
        if(block.getType() == BlockType.MINE){
            if(block.getType() == BlockType.MINE){
                image = new ImageIcon("src/com/frontend/images/mine.png").getImage();
            }
            makeScaled();


        }
       // this.setText(String.valueOf(block.getNumber()));
    }
    public void drawNumber(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        // lets anti-alias for better quality
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // lets anti-alias for text too
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);



        // calc string x and y position
        Font font = new Font("TimesRoman", Font.BOLD, 20);

        String text = String.valueOf(block.getNumber());
        FontMetrics metrics = g2d.getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int textY = getWidth() / 2 - textWidth / 2;
        int textX = getHeight() / 2 - textHeight / 2 + metrics.getAscent();
        g2d.setFont(font);
        g2d.setColor(Color.RED);

        // draw the text
        g2d.drawString(text, textY, textX);
    }

    protected void makeScaled(){
        if(image == null)
            return;
        int width = getWidth();
        int height = getHeight();


        if (width > 0 && height > 0) {
            scaled = image.getScaledInstance(getWidth(), getHeight(),
                    Image.SCALE_SMOOTH);
        }
    }

    @Override
    public void invalidate(){
        super.invalidate();
        if(image == null)
            return;
        makeScaled();

        this.repaint();

    }

    public void drawMine(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(scaled, 0, 0, null);
        repaint();

    }

    private void drawBlank(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.GRAY);
        repaint();

    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        if (block.getState() == BlockState.UNSEEN) {
            return;
        }
        if(block == null){
            return;
        }
        switch(block.getType()){
            case NUMBER:
                drawNumber(g);
                break;
            case MINE:
                drawMine(g);
                break;
            case NONE:
                drawBlank(g);
                break;

        }




    }




    static class ResizeListener extends ComponentAdapter {
        private MSButton button;

        public ResizeListener(MSButton button){
            this.button = button;
        }



        public void componentResized(ComponentEvent e){
            button.makeScaled();
        }

    }



}
