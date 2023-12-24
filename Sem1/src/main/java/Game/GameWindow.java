package Game;

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
    SettingsWindows settings;
    JButton btnStart = new JButton("Start new game");
    JButton btnExit = new JButton("Exit");

    public GameWindow(int x, int y, int scrWidth, int scrHeight) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(scrHeight / 3, scrWidth / 3 + 50); //размер окна на 1/3 от размера экрана
        setLocation(x - getHeight() / 2, y - getWidth() / 2); //центровка окна
        //setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        //setLocation(scrWidth / 3, scrHeight / 3);
        setTitle("Игра \"Крестики-нолики\"");
        setResizable(false);
        toBack();
        map = new Map();
        settings = new SettingsWindows(this); //создание окна с настройками игры
        btnStart.addActionListener(e -> settings.setVisible(true));
        btnExit.addActionListener(e -> System.exit(0));

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);
        panBottom.add(btnExit);
        add(panBottom, BorderLayout.SOUTH);
        add(map);
        setVisible(true);
        settings.setVisible(true);
        //settings.toFront();
    }

    void startNewGame(int mode, int x, int y, int winLen) {
        map.setFieldSizeX(x);
        map.setFieldSizeY(y);
        map.startNewGame(mode, winLen);
    }
}
