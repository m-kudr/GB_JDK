package Task_ServerChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private static final String TITLE = "Server control";
    JButton btnStart = new JButton("START");
    JButton btnStop = new JButton("STOP");
    boolean serverIsRun = false;

    public ServerWindow(int scrWidth, int scrHeight) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int windowWidth = scrWidth / 7;             // ширина окна
        int windowHeight = scrHeight / 8;           // высота окна
        setLocation(scrWidth - windowWidth - 20,
                scrHeight - windowHeight - 20);       // положение окна
        setSize(windowWidth, windowHeight);         // размер окна
        setTitle(TITLE);            // заголовок окна
        setResizable(false);                        // фиксированный размер окна
        setBackground(Color.RED);

        JLabel lStatus = new JLabel("СТАТУС СЕРВЕРА:");
        add(lStatus, BorderLayout.NORTH);

        JLabel state = new JLabel("ОСТАНОВЛЕН");
        state.setBackground(Color.RED);
        state.setFont(new Font("Arial", Font.BOLD, getHeight() / 5));
        add(state, BorderLayout.CENTER);

        JPanel panBottom = new JPanel(new GridLayout(1, 2));
        panBottom.add(btnStart);

        btnStop.setEnabled(false);
        panBottom.add(btnStop);
        add(panBottom, BorderLayout.SOUTH);
        setVisible(true);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverIsRun = true;
                System.out.println("SERVER STARTED!");
                btnStart.setEnabled(false);
                btnStop.setEnabled(true);
                btnStop.setSelected(true);
                state.setText("ЗАПУЩЕН");
                setBackground(Color.GREEN);
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                serverIsRun = false;
                System.out.println("SERVER STOPPED!");
                btnStart.setEnabled(true);
                btnStart.setSelected(true);
                btnStop.setEnabled(false);
                state.setText("ОСТАНОВЛЕН");
                setBackground(Color.RED);
                //System.exit(0);
            }
        });
    }
}
