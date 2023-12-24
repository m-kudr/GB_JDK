package Chat.Server;

import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int scrWidth = graphicsDevice.getDisplayMode().getWidth();
        int scrHeight = graphicsDevice.getDisplayMode().getHeight();
        new ServerWindow(scrWidth, scrHeight); // запуск чат-сервера
    }
}
