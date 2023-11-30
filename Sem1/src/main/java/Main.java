import TestWindow.GameWindow;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int scrWidth = graphicsDevice.getDisplayMode().getWidth();
        int scrHeight = graphicsDevice.getDisplayMode().getHeight();
        //System.out.println("w=" + scrWidth + ", h=" + scrHeight);

        new GameWindow(scrWidth, scrHeight);
        //System.out.println("Method main() is over");
    }
}