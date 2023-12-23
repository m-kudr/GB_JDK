package Task_ServerChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ChatWindow extends JFrame {
    private static final String TITLE = "Chat client settings"; // заголовок окна
    JButton btnConnect = new JButton("Connect");
    JButton btnDisconnect = new JButton("Disconnect");
    JButton btnSend = new JButton("SEND MESSAGE");

    public void safeToFile(String pathFile, String text) {
        try (FileWriter file = new FileWriter(pathFile, true)) {
            file.write(text + '\n');
            file.flush();
            //System.out.println("The file has been written");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String readFile(String pathFile) {
        String text = "";
        try (FileReader reader = new FileReader(pathFile)) {
            int c; // читаем посимвольно
            while ((c = reader.read()) != -1) {
                text += (char) c;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return text;
    }

    public ChatWindow(int scrWidth, int scrHeight) throws FileNotFoundException {
        String pathFile = ".//history.log";      //файл истории чата
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        int windowWidth = 400;                          // ширина окна
        int windowHeight = 600;                         // высота окна
        setLocation(scrWidth / 2 - windowWidth / 2,
                scrHeight / 2 - windowHeight / 2);  // положение окна
        setSize(windowWidth, windowHeight);             // размер окна
        setTitle(TITLE);
        setResizable(false);                            // фиксированный размер окна
        //setBackground(Color.RED);

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

        JTextField fldServer = new JTextField();
        grdCenter.add(fldServer);
        fldServer.setText("localhost");

        JLabel lblState = new JLabel("Status:");
        grdCenter.add(lblState);

        JLabel lblCurState = new JLabel("Not connected");
        grdCenter.add(lblCurState);

        grdCenter.add(btnConnect);
        grdCenter.add(btnDisconnect);
        btnDisconnect.setEnabled(false);
        add(grdCenter, BorderLayout.NORTH);

        JTextArea txtArea = new JTextArea();
        txtArea.setAutoscrolls(true); //?
        txtArea.setEditable(false);
        txtArea.setLineWrap(true);
        add(txtArea, BorderLayout.CENTER);
        // определяем объект
        /* File myFile = new File(pathFile);
        System.out.println("File name: " + myFile.getName());
        System.out.println("Parent folder: " + myFile.getParent());
        if (myFile.exists())
            System.out.println("File exists");
        else
            System.out.println("File not found");
        //System.out.println("File size: " + myFile.length());
        if (myFile.canRead()) {
            System.out.println("File can be read");
            txtArea.setText(readFile(pathFile));
        } else {
            System.out.println("File can not be read");
            try {
                System.out.println("Try create file");
                boolean created = myFile.createNewFile();
                if (created) {
                    System.out.println("File has been created");
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        if (myFile.canWrite())
            System.out.println("File can be written");
        else
            System.out.println("File can not be written"); */

        JPanel panBottom = new JPanel(new GridLayout(1, 2));

        JTextField fldMessage = new JTextField();
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
                String str = fldLogin.getText() + ": " + fldMessage.getText();
                txtArea.append(str + "\n");
                safeToFile(pathFile, str);
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
                txtArea.setText(readFile(pathFile));
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