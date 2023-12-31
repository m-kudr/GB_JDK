package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Map extends JPanel {
    private static final Random RANDOM = new Random();
    private static final int DOT_PADDING = 5;
    private int gameOverType;
    private static final int STATE_DRAW = 0;
    private static final int STATE_WIN_HUMAN = 1;
    private static final int STATE_WIN_AI = 2;
    private static final String MSG_DRAW = "Ничья!";
    private static final String MSG_WIN_HUMAN = "Вы победили!";
    private static final String MSG_WIN_AI = "Победил компьютер!";

    private final int HUMAN_DOT = 1;
    private final int AI_DOT = 2;
    private final int EMPTY_DOT = 0;

    private int fieldSizeY = 3;
    private int fieldSizeX = 3;
    private int[][] field;

    private static int panelWidth;
    private static int panelHeight;
    private int cellHeight;
    private int cellWidth;
    private boolean isGameOver;
    private boolean isInitialized;

    public void setGameOverType(int gameOverType) {
        this.gameOverType = gameOverType;
    }

    public void setFieldSizeY(int fieldSizeY) {
        this.fieldSizeY = fieldSizeY;
    }

    public void setFieldSizeX(int fieldSizeX) {
        this.fieldSizeX = fieldSizeX;
    }

    private void initMap() {
        field = new int[fieldSizeY][fieldSizeX];
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++)
                field[i][j] = EMPTY_DOT;
    }

    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < fieldSizeX && y >= 0 && y < fieldSizeY;
    }

    private boolean isEmptyCell(int x, int y) {
        return field[y][x] == EMPTY_DOT;
    }

    private void aiTurn() {
        int x, y;
        do {
            x = RANDOM.nextInt(fieldSizeX);
            y = RANDOM.nextInt(fieldSizeY);
        } while (!isEmptyCell(x, y));
        field[y][x] = AI_DOT;
    }

    private boolean isLineFull(int[] line) {
        if (line[0] != EMPTY_DOT) {
            int temp = line[0];
            for (int i = 1; i < line.length; i++) {
                if (line[i] != temp) return false;
            }
            System.out.println("--Full line--");
            return true;
        }
        return false;
    }

    private int[][] rotatedArray(int[][] array) {
        int[][] rotated = new int[array.length][array[0].length];
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                rotated[i][j] = array[j][i];
        return rotated;
    }

    private boolean isWin(int c) {
        /* проверка на выигрыш
        if (field[0][0] == c && field[0][1] == c && field[0][2] == c) return true;
        if (field[1][0] == c && field[1][1] == c && field[1][2] == c) return true;
        if (field[2][0] == c && field[2][1] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][0] == c && field[2][0] == c) return true;
        if (field[0][1] == c && field[1][1] == c && field[2][1] == c) return true;
        if (field[0][2] == c && field[1][2] == c && field[2][2] == c) return true;

        if (field[0][0] == c && field[1][1] == c && field[2][2] == c) return true;
        if (field[0][2] == c && field[1][1] == c && field[2][0] == c) return true;*/
        for (int y = 0; y < field.length; y++)
            if (isLineFull(field[y])) return true; // проверка по строкам

        int[][] fieldRot = rotatedArray(field); // переворачивание поля для проверки по столбцам

        for (int x = 0; x < fieldRot.length; x++)
            if (isLineFull(fieldRot[x])) return true; // проверка по столбцам

        int[] diagA = new int[field.length]; // получение диагонали A
        for (int i = 0; i < field.length; i++)
            diagA[i] = field[i][i];
        if (isLineFull(diagA)) return true; // проверка диагонали A

        int[] diagB = new int[field.length]; // получение диагонали B
        for (int i = 0; i < field.length; i++)
            diagB[i] = field[field.length - 1 - i][i];
        return isLineFull(diagB); // проверка диагонали B
    }

    Map() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });
        isInitialized = false;
    }

    private boolean isMapFull() {
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++)
                if (field[i][j] == EMPTY_DOT) return false;
        return true;
    }

    public void update(MouseEvent e) {
        if (isGameOver || !isInitialized) return;
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;
        //System.out.printf("x=%d, y=%d\n", cellX, cellY);
        if (!isValidCell(cellX, cellY) || !isEmptyCell(cellX, cellY)) return;
        field[cellY][cellX] = HUMAN_DOT;
        if (isGameEnd(HUMAN_DOT, STATE_WIN_HUMAN)) return;
        aiTurn();
        repaint();
        //if (isGameEnd(AI_DOT, STATE_WIN_AI)) {     }
    }

    private boolean isGameEnd(int dot, int gameOverType) {
        if (isWin(dot)) {
            this.gameOverType = gameOverType;
            isGameOver = true;
            repaint();
            return true;
        }
        if (isMapFull()) {
            this.gameOverType = STATE_DRAW;
            isGameOver = true;
            repaint();
            return true;
        }
        return false;
    }

    void startNewGame(int mode, int winLen) {
        initMap();
        isGameOver = false;
        isInitialized = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInitialized) return;
        panelWidth = getWidth();
        panelHeight = getHeight();
        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        g.setColor(Color.BLACK);
        for (int h = 0; h < fieldSizeY; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 0; w < fieldSizeX; w++) {
            int x = w * cellHeight;
            g.drawLine(x, 0, x, panelHeight);
        }
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_DOT) continue;

                if (field[y][x] == HUMAN_DOT) {
                    g.setColor(new Color(0x00ff00));
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellWidth - DOT_PADDING * 2);
                } else if (field[y][x] == AI_DOT) {
                    g.setColor(new Color(0xff0000));
                    g.fillOval(x * cellWidth + DOT_PADDING,
                            y * cellHeight + DOT_PADDING,
                            cellWidth - DOT_PADDING * 2,
                            cellWidth - DOT_PADDING * 2);
                } else {
                    throw new RuntimeException("Unexpected value " + field[y][x] + " in cell: x=" + x + " y=" + y);
                }
            }
        }
        if (isGameOver) showMessageGameOver(g);
    }

    private void showMessageGameOver(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, getHeight() / 2 - 40, getWidth(), 80);
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Times new roman", Font.BOLD, getHeight() / 10));
        switch (gameOverType) {
            case STATE_DRAW -> g.drawString(MSG_DRAW, 10, getHeight() / 2 + 15);
            case STATE_WIN_AI -> g.drawString(MSG_WIN_AI, 10, getHeight() / 2 + 15);
            case STATE_WIN_HUMAN -> g.drawString(MSG_WIN_HUMAN, 10, getHeight() / 2 + 15);
            default -> throw new RuntimeException("Unexpected gameOver state: " + gameOverType);
        }
    }
}
