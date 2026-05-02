import java.awt.*;
import javax.swing.*;

public class GameUtil {
    static int MAP_W = 11;
    static int MAP_H = 11;
    static int OFFSET = 45;
    static int SQUARE_LENGTH = 50;
    static int TILE_INSET = 2;
    static int TILE_RADIUS = 10;
    static int PANEL_PADDING = 8;
    static int PANEL_RADIUS = 16;
    static int TOP_SECTION_GAP = 30;
    static int INFO_PANEL_WIDTH = 342;
    static int INFO_PANEL_HEIGHT = 44;
    //-1 0 1-8
    static int[][] MAP_BOTTOM = new int[MAP_W + 2][MAP_H + 2];
    // -1 0 1 2
    static int[][] TOP_BOTTOM = new int[MAP_W + 2][MAP_H + 2];
    static int CAT_MAX = 20;
    static int MOUSE_X = 0;
    static int MOUSE_Y = 0;
    static int HOVER_X = 0;
    static int HOVER_Y = 0;
    static int PRESS_X = 0;
    static int PRESS_Y = 0;
    static boolean LEFT = false;
    static boolean RIGHT = false;
    static int status = 0;
    static boolean firstClick = true;
    static long startTime = 0;
    static int elapsedSeconds = 0;

    static Color COLOR_BG = new Color(245, 245, 247);
    static Color COLOR_GRID = new Color(220, 220, 225);
    static Color COLOR_PANEL = new Color(255, 255, 255);
    static Color COLOR_PANEL_BORDER = new Color(228, 228, 231);
    static Color COLOR_SHADOW = new Color(0, 0, 0, 18);
    static Color COLOR_HOVER = new Color(255, 255, 255, 70);
    static Color COLOR_PRESSED = new Color(0, 0, 0, 32);
    static Color COLOR_TEXT = new Color(82, 82, 91);

    static int boardX() {
        return OFFSET;
    }

    static int boardY() {
        return restartButtonY() + tileSize() + TOP_SECTION_GAP + INFO_PANEL_HEIGHT + TOP_SECTION_GAP;
    }

    static int boardWidth() {
        return MAP_W * SQUARE_LENGTH;
    }

    static int boardHeight() {
        return MAP_H * SQUARE_LENGTH;
    }

    static int tileX(int x) {
        return boardX() + (x - 1) * SQUARE_LENGTH + TILE_INSET;
    }

    static int tileY(int y) {
        return boardY() + (y - 1) * SQUARE_LENGTH + TILE_INSET;
    }

    static int tileSize() {
        return SQUARE_LENGTH - 2 * TILE_INSET;
    }

    static int restartButtonX() {
        return boardX() + (boardWidth() - tileSize()) / 2;
    }

    static int restartButtonY() {
        return 28;
    }

    static int gridXFromMouse(int mouseX) {
        if (mouseX <= boardX() || mouseX >= boardX() + boardWidth()) {
            return 0;
        }
        return (mouseX - boardX()) / SQUARE_LENGTH + 1;
    }

    static int gridYFromMouse(int mouseY) {
        if (mouseY <= boardY() || mouseY >= boardY() + boardHeight()) {
            return 0;
        }
        return (mouseY - boardY()) / SQUARE_LENGTH + 1;
    }

    static boolean isInsideBoardCell(int x, int y) {
        return x >= 1 && x <= MAP_W && y >= 1 && y <= MAP_H;
    }

    static void updateHover(int mouseX, int mouseY) {
        int gridX = gridXFromMouse(mouseX);
        int gridY = gridYFromMouse(mouseY);
        if (isInsideBoardCell(gridX, gridY)) {
            HOVER_X = gridX;
            HOVER_Y = gridY;
        } else {
            clearHover();
        }
    }

    static void clearHover() {
        HOVER_X = 0;
        HOVER_Y = 0;
    }

    static void updatePress(int mouseX, int mouseY) {
        int gridX = gridXFromMouse(mouseX);
        int gridY = gridYFromMouse(mouseY);
        if (isInsideBoardCell(gridX, gridY)) {
            PRESS_X = gridX;
            PRESS_Y = gridY;
        }
    }

    static void clearPress() {
        PRESS_X = 0;
        PRESS_Y = 0;
    }

    static void startTimer() {
        if (startTime == 0) {
            startTime = System.currentTimeMillis();
        }
    }

    static void updateElapsedTime() {
        if (!firstClick && status == 0 && startTime > 0) {
            elapsedSeconds = (int) ((System.currentTimeMillis() - startTime) / 1000);
        }
    }

    static Image maozhua = loadImage("pic/maozhua.png");
    static Image coin = loadImage("pic/coin.png");
    static Image mouse = loadImage("pic/mouse.png");
    static Image top = loadImage("pic/yellow.png");
    static Image zero = loadImage("pic/cheese.png");
    static Image one = loadImage("pic/1.png");
    static Image two = loadImage("pic/2.png");
    static Image three = loadImage("pic/3.png");
    static Image four = loadImage("pic/4.png");
    static Image five = loadImage("pic/5.png");
    static Image six = loadImage("pic/6.png");
    static Image seven = loadImage("pic/7.png");
    static Image eight = loadImage("pic/8.png");
    static Image Continue = loadImage("pic/cheese.png");
    static Image Success = loadImage("pic/mouse.png");
    static Image False = loadImage("pic/cat.png");

    static Image loadImage(String path) {
        return new ImageIcon(path).getImage();
    }

    static void resetInputState() {
        MOUSE_X = 0;
        MOUSE_Y = 0;
        clearHover();
        clearPress();
        LEFT = false;
        RIGHT = false;
    }

    static void resetGameState() {
        resetInputState();
        firstClick = true;
        status = 0;
        startTime = 0;
        elapsedSeconds = 0;
    }
}
