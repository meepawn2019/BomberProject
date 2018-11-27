package com.bomb.gui;

import javax.swing.*;


public class GUI extends JFrame {

    //private TEST test = new TEST();
    TEST test;

    public GUI() {
        //setSize(WIDTHJF, HEI`GHTJF);
        String[] map={"map1.txt","map2.txt","map3.txt","map4.txt"};
        int n=0;
        do{
            test = new TEST(map[n]);
            add(test);
            pack();
            setLocationRelativeTo(null);
            addKeyListener(test.myAdapter);
            setResizable(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE) ;
            n++;
        }while(test.asd==true);

    }

    public static void main(String[] args) {
        new GUI().setVisible(true);
        
    }
}
