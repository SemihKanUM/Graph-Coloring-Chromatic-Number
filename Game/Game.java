package Game;

import javax.swing.*;
import java.awt.BorderLayout;


import Graph.Launch;

public class Game extends JFrame {

    public Board board;
    public Launch launch;


    public Game(){
        board = new Board();
        board.setGame(this);
    }


    public void startGame(){
        board.setup();
        initGame();
    }

    public void initGame(){

        add(board,BorderLayout.CENTER);
        setSize(800, 800);
    }

}