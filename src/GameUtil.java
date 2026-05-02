import java.awt.*;
import javax.swing.*;

public class GameUtil {
    static int MAP_W = 11;
    static int MAP_H = 11;
    static int OFFSET = 45;
    static int SQUARE_LENGTH = 50;
    //-1 0 1-8
    static int[][] MAP_BOTTOM = new int[MAP_W + 2][MAP_H + 2];
    // -1 0 1 2
    static int[][] TOP_BOTTOM = new int[MAP_W + 2][MAP_H + 2];
    static int CAT_MAX = 20;
    static int MOUSE_X = 0;
    static int MOUSE_Y = 0;
    static boolean LEFT = false;
    static boolean RIGHT = false;
    static int status = 0;
    static boolean firstClick = true;
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
        LEFT = false;
        RIGHT = false;
    }

    static void resetGameState() {
        resetInputState();
        firstClick = true;
        status = 0;
    }
}
