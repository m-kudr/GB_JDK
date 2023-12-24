package Chat.Server;

import java.awt.*;

public class Main {
    private static final String APPLICATION_NAME = "Chat Server";
    private static final String ICON_STR = "./images/logo_weibo_icon_143180_32x32.png";

    public static void main(String[] args) {
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int scrWidth = graphicsDevice.getDisplayMode().getWidth();
        int scrHeight = graphicsDevice.getDisplayMode().getHeight();
        setTrayIcon();
        new ServerWindow(scrWidth, scrHeight); // запуск чат-сервера
        System.out.println("Start program \"Chat server\"");
    }

    private static void setTrayIcon() {
        if (!SystemTray.isSupported()) {
            return;
        }

        PopupMenu trayMenu = new PopupMenu();
        MenuItem itemExit = new MenuItem("Exit");
        MenuItem itemShow = new MenuItem("Show window");
        itemExit.addActionListener(e -> {
            System.exit(0);
        });

        trayMenu.add(itemExit);
        trayMenu.add(itemShow);

        Image icon = Toolkit.getDefaultToolkit().getImage(ICON_STR);
        TrayIcon trayIcon = new TrayIcon(icon, APPLICATION_NAME, trayMenu);
        trayIcon.setImageAutoSize(true);

        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        trayIcon.displayMessage(APPLICATION_NAME, "Application started!",
                TrayIcon.MessageType.INFO);
    }
}
