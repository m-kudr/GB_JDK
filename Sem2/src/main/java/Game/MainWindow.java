package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow extends JFrame {
    //public abstract class Sprite implements Interactable;
    //public abstract class Background implements Interactable;

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;    // ширина окна
    private static final int WINDOW_HEIGHT = 600;   // высота окна
    static int itemCount = 5;                       // начальное количество объектов
    private static int maxItemCount = 15;           // максимальное количество объектов

    private final Sprite[] sprites = new Sprite[maxItemCount];
    boolean onPause = false;                        // флаг паузы

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        sprites[0] = new Background();

        for (int i = 1; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }
        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        canvas.setFocusable(true);
        canvas.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // обработчик нажатия кнопки "Пробел"
                //System.out.println("Pressed '" + e.getKeyCode() + "'");
                if (e.getKeyChar() == ' ') {
                    if (onPause == false) {
                        onPause = true;
                    } else onPause = false;
                }
                if (e.getKeyCode() == 38 && itemCount < maxItemCount) {
                    itemCount++; //System.out.println("itemCount =" + itemCount);
                }

                if (e.getKeyCode() == 40 && itemCount > 2) {
                    itemCount--; //System.out.println("itemCount =" + itemCount);
                }
            }
        });

        setVisible(true);
    }

    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime, int itemCount) {
        repaint();
        if (!onPause) { // остановка игры на паузе
            update(canvas, deltaTime);
        }
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i < itemCount; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i < itemCount; i++) {
            sprites[i].render(canvas, g);
        }
    }
}
