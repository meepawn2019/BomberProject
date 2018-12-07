package com.bomb.gui;

import GameSound.GameSound;

import javax.swing.*;
import java.awt.*;

public class Container extends JPanel {
    private static final String TAG_PLAYGAME = "tag_playgame";
    private static final String TAG_MENU = "tag_menu";
    private CardLayout mCardLayout;
    private GUI gui;
    private Menu mMenu;
    public TEST test;
    String[] map={"map1.txt","map2.txt","map3.txt","map4.txt"};

    public GUI getGui() {
        return gui;
    }

    public Container(GUI gui){
        this.gui = gui;
        setBackground(Color.WHITE);
        mCardLayout = new CardLayout();
        setLayout(mCardLayout);
        mMenu = new Menu(this);
        add(mMenu, TAG_MENU);
        test = new TEST(map[0   ], this);
        add(test, TAG_PLAYGAME);
        showMenu();
    }

    public void showMenu(){
        mCardLayout.show(Container.this, TAG_MENU);
        mMenu.requestFocus();
    }

    public void showGame(){
        mCardLayout.show(Container.this, TAG_PLAYGAME);
        test.requestFocus();
        GameSound gameSound = new GameSound("./src/GameSound/Village.wav");
        gameSound.loop();
    }

}
