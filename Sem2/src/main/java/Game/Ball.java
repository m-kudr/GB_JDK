package Game;

import java.awt.*;
import java.util.Random;

public class Ball extends Sprite {
    private static Random rnd = new Random();
    private final Color color;
    private float vX;
    private float vY;

    Ball() {
        halfHeight = 20 + (float) (Math.random() * 50f); // высота шарика
        halfWidth = halfHeight; // ширина шарика
        color = new Color(rnd.nextInt()); // задаем случайный цвет шарика
        vX = 100f + (float) (Math.random() * 200f); // задаем скорость смешения  шарика по Х
        vY = 100f + (float) (Math.random() * 200f); // задаем скорость смешения  шарика по У
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) { // отрисовка шарика
        g.setColor(color);
        g.fillOval((int) getLeft(), (int) getTop(), (int) getWidth(), (int) getHeight());
    }

    @Override
    public void update(MainCanvas canvas, float deltaTime) {  // смешение шарика
        x += vX * deltaTime;
        y += vY * deltaTime;
        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX = -vX;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX = -vX;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY = -vY;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = -vY;
        }
    }
}
