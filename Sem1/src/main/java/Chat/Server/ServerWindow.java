package Chat.Server;

import javax.swing.*;
import java.awt.*;

//import static Chat.Server.Server.*;

public class ServerWindow extends JFrame implements ChatServerListener{
    private static final String TITLE = "Server control";
    JButton btnStart = new JButton("START");
    JButton btnStop = new JButton("STOP");
    JButton btnExit = new JButton("EXIT");

    Server server = new Server(this);

    public ServerWindow(int scrWidth, int scrHeight) {
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        int windowWidth = scrWidth / 7;             // ширина окна
        int windowHeight = scrHeight / 8;           // высота окна
        setLocation(scrWidth - windowWidth - 20,
                scrHeight - windowHeight - 20);   // положение окна
        setSize(windowWidth, windowHeight);         // размер окна
        setTitle(TITLE);            // заголовок окна
        setResizable(false);        // фиксированный размер окна
        setBackground(Color.RED);

        JLabel lStatus = new JLabel("СТАТУС СЕРВЕРА:");
        add(lStatus, BorderLayout.NORTH);

        JLabel state = new JLabel("ОСТАНОВЛЕН");
        state.setBackground(Color.RED);
        state.setFont(new Font("Arial", Font.BOLD, getHeight() / 5));
        add(state, BorderLayout.CENTER);

        JPanel panBottom = new JPanel(new GridLayout(1, 3));
        panBottom.add(btnStart);
        btnStop.setEnabled(false);
        panBottom.add(btnStop);
        panBottom.add(btnExit);
        add(panBottom, BorderLayout.SOUTH);
        setVisible(true);

        btnStart.addActionListener(e -> {
            server.Start();
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);
            btnStop.setSelected(true);
            state.setText("ЗАПУЩЕН");
            state.setBackground(Color.GREEN);
        });

        btnStop.addActionListener(e -> {
            server.Stop();
            btnStart.setEnabled(true);
            btnStart.setSelected(true);
            btnStop.setEnabled(false);
            state.setText("ОСТАНОВЛЕН");
            state.setBackground(Color.RED);  //System.exit(0);
        });

        btnExit.addActionListener(e -> {
            if (Server.isServerRun) {
                server.Stop();
            }
            System.out.println("Close program \"Chat server\"");
            System.exit(0);
        });
    }

    @Override
    public void onMessageReceive(String msg) {
        System.out.println(msg);
    }
}
