package Game;

//import Game.GameWindow;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int scrWidth = graphicsDevice.getDisplayMode().getWidth();
        int scrHeight = graphicsDevice.getDisplayMode().getHeight();
        int scrCenterX = scrWidth / 2;
        int scrCenterY = scrHeight / 2;
        //System.out.println("w=" + scrWidth + ", h=" + scrHeight);

        new GameWindow(scrCenterX, scrCenterY, scrWidth, scrHeight);
        //System.out.println("Method main() is over");
    }
}