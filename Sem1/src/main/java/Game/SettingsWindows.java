package Game;

import javax.swing.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class SettingsWindows extends JFrame {
    private static final int WINDOW_HEIGHT = 230;
    private static final int WINDOW_WIDTH = 350;
    private static final String LABEL_FIELD_SIZE = "Размерность игрового поля (3-10):  ";
    private static final String WIN_STRIP_SIZE = "Установленная длина (3-10):  ";
    JButton btnStart = new JButton("Start new game");
    JLabel lGameMode = new JLabel("Выберите режим игры");
    JLabel lGameFields = new JLabel(LABEL_FIELD_SIZE + "3");
    //JLabel lGameFieldsValue = new JLabel("3");
    JSlider slField = new JSlider(3, 10);
    JRadioButton rbHumVsPC = new JRadioButton("Человек с компьютером");
    JRadioButton rbHumVsHum = new JRadioButton("Человек с человеком");
    ButtonGroup bg = new ButtonGroup();
    JLabel lWinCount = new JLabel("Выберите длину для победы");
    JLabel lSetWinCount = new JLabel(WIN_STRIP_SIZE + "3");
    JSlider slWinCount = new JSlider(3, 10);
    JPanel panBottom;

    SettingsWindows(GameWindow gameWindow) {
        //setLocationRelativeTo(gameWindow);
        //центровка окна
        setLocation(gameWindow.getLocation().x + gameWindow.getWidth() / 2 - WINDOW_WIDTH / 2,
                gameWindow.getLocation().y + gameWindow.getHeight() / 2 - WINDOW_HEIGHT / 2);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT); //размер окна
        setTitle("Game Settings");
        panBottom = new JPanel(new GridLayout(9, 1));
        rbHumVsPC.setRolloverEnabled(true);
        rbHumVsPC.setSelected(true);
        bg.add(rbHumVsPC);
        bg.add(rbHumVsHum);
        panBottom.add(lGameMode);
        panBottom.add(rbHumVsPC);
        panBottom.add(rbHumVsHum);
        panBottom.add(lGameFields);
        slField.setValue(3);
        panBottom.add(slField);
        panBottom.add(lWinCount);
        panBottom.add(lSetWinCount);
        panBottom.add(slWinCount);
        slWinCount.setValue(3);
        panBottom.add(btnStart);
        slField.addChangeListener(e -> lGameFields.setText(LABEL_FIELD_SIZE + slField.getValue()));
        slWinCount.addChangeListener(e -> lSetWinCount.setText(WIN_STRIP_SIZE + slWinCount.getValue()));
        btnStart.addActionListener(e -> {
            //System.out.printf("StartNewGame(%d,%d,%d,%d)", 0, slField.getValue(), slField.getValue(), slWinCount.getValue());
            if (slWinCount.getValue() <= slField.getValue()) {
                gameWindow.startNewGame(rbHumVsPC.isSelected() ? 0 : 1, slField.getValue(), slField.getValue(), slWinCount.getValue());
                setVisible(false);
            }
        });
        add(panBottom);
    }
}
