package Task_ServerChat;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int scrWidth = graphicsDevice.getDisplayMode().getWidth();
        int scrHeight = graphicsDevice.getDisplayMode().getHeight();
        new ChatWindow(scrWidth, scrHeight);
        new ServerWindow(scrWidth, scrHeight);
        //System.out.println("Method main() is over");
    }
}
