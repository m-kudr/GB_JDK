package TestWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {
    //private static final int WINDOW_HEIGHT = 555;
    //private static final int WINDOW_WIDTH = 507;
    //private static final int WINDOW_POSX = 800;
    //private static final int WINDOW_POSY = 300;
    Map map;
    SettingsWindows settings;//
    JButton btnStart = new JButton("Start New Game");
    JButton btnExit = new JButton("Exit");

    public GameWindow(int scrWidth, int scrHeight) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setLocation(WINDOW_POSX, WINDOW_POSY);
        //setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation(scrWidth / 3, scrHeight / 3);
        setSize(scrHeight / 3, scrHeight / 3 + 50);
        setTitle("Игра \"Крестики-нолики\"");
        setResizable(false);
        map = new Map();

        settings = new SettingsWindows(this);//
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setVisible(true);
            }
        });
        settings.setVisible(true);//

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);
        add(panBottom, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
    }

    void startNewGame(int mode, int x, int y, int winLen) {
        map.setFieldSizeX(x);
        map.setFieldSizeY(y);
        map.startNewGame(mode, winLen);
    }
}
