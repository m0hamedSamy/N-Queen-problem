package com.company;

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    int n;
    int[][] board;

    public UI(int[][] board, int n){
        this.board = board;
        this.n = n;
    }

    public JPanel myPanel(){
        JPanel panel = new JPanel(new GridLayout(n, n));

        boolean evenRow = true;

        ImageIcon icon;
        JButton btn;
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                if(board[i][j] == 1) {
                    // please put the correct complete path of the image in order to make it appear on the GUI
                    icon = new ImageIcon("F:\\projects\\java\\n-queens\\src\\com\\company\\queen.png");
                    /*
                     if there is no image nor a "Q" letter appears on the board
                     then you can delete the following block of code(if, else)
                     and uncomment this one: */
                     //btn = new JButton("Q");

                    if(icon.getImageLoadStatus() != 4) {
                        btn = new JButton(icon);
                    }
                    else {
                        btn = new JButton("Q");
                    }

                }else {
                    btn = new JButton("");
                }

                btn.setPreferredSize(new Dimension(64, 64));
                btn.setFocusPainted(false);
                btn.setEnabled(false);
                if (evenRow == true) {
                    if (j == n-1) {
                        evenRow = false;
                    }
                    if (j % 2 == 0) {
                        btn.setBackground(Color.WHITE);
                        panel.add(btn);
                    } else {
                        btn.setBackground(Color.BLACK);
                        panel.add(btn);
                    }

                } else {
                    if (j == n-1) {
                        evenRow = true;
                    }
                    if (j % 2 == 0) {
                        btn.setBackground(Color.BLACK);
                        panel.add(btn);
                    } else {
                        btn.setBackground(Color.WHITE);
                        panel.add(btn);
                    }
                }
            }

        }
        return panel;
    }

}
