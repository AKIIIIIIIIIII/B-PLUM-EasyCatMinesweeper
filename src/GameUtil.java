import java.awt.*;

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
    static Image maozhua = Toolkit.getDefaultToolkit().getImage("pic/maozhua.png");
    static Image coin = Toolkit.getDefaultToolkit().getImage("pic/coin.png");
    static Image mouse = Toolkit.getDefaultToolkit().getImage("pic/mouse.png");
    static Image top = Toolkit.getDefaultToolkit().getImage("pic/yellow.png");
    static Image zero = Toolkit.getDefaultToolkit().getImage("pic/cheese.png");
    static Image one = Toolkit.getDefaultToolkit().getImage("pic/1.png");
    static Image two = Toolkit.getDefaultToolkit().getImage("pic/2.png");
    static Image three = Toolkit.getDefaultToolkit().getImage("pic/3.png");
    static Image four = Toolkit.getDefaultToolkit().getImage("pic/4.png");
    static Image five = Toolkit.getDefaultToolkit().getImage("pic/5.png");
    static Image six = Toolkit.getDefaultToolkit().getImage("pic/6.png");
    static Image seven = Toolkit.getDefaultToolkit().getImage("pic/7.png");
    static Image eight = Toolkit.getDefaultToolkit().getImage("pic/8.png");
    static Image Continue = Toolkit.getDefaultToolkit().getImage("pic/cheese.png");
    static Image Success = Toolkit.getDefaultToolkit().getImage("pic/mouse.png");
    static Image False = Toolkit.getDefaultToolkit().getImage("pic/cat.png");
}
