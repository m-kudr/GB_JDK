package Task_ServerChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChatWindow extends JFrame {
    private static final String TITLE = "Chat client settings";
    JButton btnConnect = new JButton("Connect");
    JButton btnDisconnect = new JButton("Disconnect");
    JButton btnSend = new JButton("SEND MESSAGE");

    public ChatWindow(int scrWidth, int scrHeight) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int windowWidth = 400;                          // ширина окна
        int windowHeight = 300;                         // высота окна
        setLocation(scrWidth / 2 - windowWidth / 2,
                scrHeight / 2 - windowHeight / 2);  // положение окна
        setSize(windowWidth, windowHeight);             // размер окна
        setTitle(TITLE);                                // заголовок окна
        setResizable(false);                            // фиксированный размер окна
        setBackground(Color.RED);

        JPanel grdCenter = new JPanel(new GridLayout(5, 2));

        JLabel lblLogin = new JLabel("Login:");
        grdCenter.add(lblLogin);

        JTextField fldLogin = new JTextField();
        grdCenter.add(fldLogin);
        fldLogin.setText("user");

        JLabel lblPassword = new JLabel("Password:");
        grdCenter.add(lblPassword);

        JTextField fldPassword = new JTextField();
        grdCenter.add(fldPassword);
        fldPassword.setText("pass");

        JLabel lblServer = new JLabel("Server (IP or name):");
        grdCenter.add(lblServer);
        grdCenter.add(lblServer);

        JTextField fldServer = new JTextField();
        fldServer.setText("localhost");
        grdCenter.add(fldServer);

        JLabel lblState = new JLabel("Status:");
        grdCenter.add(lblState);

        JLabel lblCurState = new JLabel("Not connected");
        grdCenter.add(lblCurState);

        grdCenter.add(btnConnect);
        grdCenter.add(btnDisconnect);
        btnDisconnect.setEnabled(false);
        add(grdCenter, BorderLayout.NORTH);

        //JTextPane txtPane = new JTextPane();
        //add(txtPane, BorderLayout.CENTER);
        JTextArea txtArea = new JTextArea();
        txtArea.setAutoscrolls(true); //?
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        add(txtArea, BorderLayout.CENTER);

        JPanel panBottom = new JPanel(new GridLayout(1, 2));

        JTextField fldMessage = new JTextField();
//        fldMessage.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                if(e.getKeyCode() == KeyEvent.VK_ENTER){
//                    // something like...
//                    //mTextField.getText();
//                    // or...
//                    //mButton.doClick();
//                }
//            }
//        });
        panBottom.add(fldMessage);
        fldMessage.setText("Hello!");
        fldMessage.setEnabled(false);

        panBottom.add(btnSend);
        btnSend.setEnabled(false);

        add(panBottom, BorderLayout.SOUTH);
        setVisible(true);

        fldMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("press");
                if (!fldMessage.getText().equals("")) {
                    btnSend.doClick();
                    fldMessage.setText("");
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtArea.append(fldLogin.getText() + ": " + fldMessage.getText() + "\n");
                fldMessage.setText("");
                Toolkit.getDefaultToolkit().beep();
            }
        });

        btnConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConnect.setEnabled(false);
                btnDisconnect.setEnabled(true);
                fldMessage.setEnabled(true);
                btnSend.setEnabled(true);
                lblCurState.setText("Connected");
                fldLogin.setEnabled(false);
                fldPassword.setEnabled(false);
                fldServer.setEnabled(false);
            }
        });

        btnDisconnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnConnect.setEnabled(true);
                btnDisconnect.setEnabled(false);
                fldMessage.setEnabled(false);
                btnSend.setEnabled(false);
                lblCurState.setText("Disconnected");
                fldLogin.setEnabled(true);
                fldPassword.setEnabled(true);
                fldServer.setEnabled(true);
            }
        });
    }
}