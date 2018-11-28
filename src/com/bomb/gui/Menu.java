package com.bomb.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends JPanel {
    private int padding = 15;;
    private GUI gui;
    private Container container;
    private JLabel lbbackground;
    private JLabel lbPlayGame;
    private JLabel lbExit;
    private ImageIcon backgroundIcon;

    public Menu(Container container){
        this.container = container;
        this.gui = container.getGui();
        setBackground(Color.YELLOW);
        setLayout(null);
        initComps(gui);
        initbackground();
    }

    public void initComps(GUI mGui){
        lbPlayGame = setLabel((mGui.getWidth()-150)/2-30, (mGui.getHeight()-30)/2-150, "/Character/Play.png");
        /*lbOption = setLabel(lbPlayGame.getX(),lbPlayGame.getY() + lbPlayGame.getHeight()+padding, "/Images/Option.png");
        lbHigthScore = setLabel(lbOption.getX(),lbOption.getY() + lbOption.getHeight()+padding, "/Images/HightScore.png");*/
        lbExit = setLabel(lbPlayGame.getX(),lbPlayGame.getY() + lbPlayGame.getHeight()+padding, "/Character/Exit.png");

        lbPlayGame.addMouseListener(mMouseAdapter);
        //lbOption.addMouseListener(mMouseAdapter);
        //lbHigthScore.addMouseListener(mMouseAdapter);
        lbExit.addMouseListener(mMouseAdapter);

        add(lbPlayGame);
        //add(lbOption);
        //add(lbHigthScore);
        add(lbExit);

    }

    public void initbackground(){
        lbbackground = new JLabel();
        lbbackground.setBounds(0, -10, gui.getWidth(), gui.getHeight());
        lbbackground.setBackground(Color.BLACK);
        backgroundIcon = new ImageIcon(getClass().getResource("/Character/background_Menu.png"));
        lbbackground.setIcon(backgroundIcon);
        add(lbbackground);
    }

    public JLabel setLabel(int x, int y, String ImageIcon){
        JLabel label = new JLabel();
        ImageIcon Icon = new ImageIcon(getClass().getResource(ImageIcon));
        label.setBounds(x, y, Icon.getIconWidth(), Icon.getIconHeight());
        label.setIcon(Icon);
        return label;
    }

    private MouseAdapter mMouseAdapter = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource()==lbPlayGame){
                ImageIcon playIcon = new ImageIcon(getClass().getResource("/Character/Play2.png"));
                lbPlayGame.setIcon(playIcon);
            }
            /*if(e.getSource()==lbOption){
                ImageIcon optionIcon = new ImageIcon(getClass().getResource("/Images/Option2.png"));
                lbOption.setIcon(optionIcon);
            }
            if(e.getSource()==lbHigthScore){
                ImageIcon hightScoreIcon = new ImageIcon(getClass().getResource("/Images/HightScore2.png"));
                lbHigthScore.setIcon(hightScoreIcon);
            }*/
            if(e.getSource()==lbExit){
                ImageIcon exitIcon = new ImageIcon(getClass().getResource("/Character/Exit2.png"));
                lbExit.setIcon(exitIcon);
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource()==lbPlayGame){
                ImageIcon playIcon = new ImageIcon(getClass().getResource("/Character/Play.png"));
                lbPlayGame.setIcon(playIcon);
            }
           /* if(e.getSource()==lbOption){
                ImageIcon optionIcon = new ImageIcon(getClass().getResource("/Images/Option.png"));
                lbOption.setIcon(optionIcon);
            }
            if(e.getSource()==lbHigthScore){
                ImageIcon hightScoreIcon = new ImageIcon(getClass().getResource("/Images/HightScore.png"));
                lbHigthScore.setIcon(hightScoreIcon);
            }*/
            if(e.getSource()==lbExit){
                ImageIcon exitIcon = new ImageIcon(getClass().getResource("/Character/Exit.png"));
                lbExit.setIcon(exitIcon);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getSource()==lbExit){
                //GameSound.getIstance().getAudio(GameSound.MENU).stop();
                gui.dispose();
                //PlayGame.IS_RUNNING=false;
            }
            if(e.getSource()==lbPlayGame){
                container.showGame();
            }
            /*if(e.getSource()==lbOption){
                mContainer.setShowOption();
            }
            if(e.getSource()==lbHigthScore){
                mContainer.setShowHightScore();
            }*/
        }
    };


}
