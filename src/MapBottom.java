import java.awt.*;
//
public class MapBottom {

    BottomCat bottomCat = new BottomCat();
    Num num = new Num();

    void regame(){
        for (int i = 0; i < GameUtil.MAP_BOTTOM.length ; i++) {
            for (int j = 0; j < GameUtil.MAP_BOTTOM[i].length ; j++) {
                GameUtil.MAP_BOTTOM[i][j] = 0;
            }
        }
        GameUtil.firstClick = true;
    }

    void createMap(int safeX, int safeY) {
        for (int i = 0; i < GameUtil.MAP_BOTTOM.length ; i++) {
            for (int j = 0; j < GameUtil.MAP_BOTTOM[i].length ; j++) {
                GameUtil.MAP_BOTTOM[i][j] = 0;
            }
        }
        bottomCat.NewCat(safeX, safeY);
        num.NewNum();
        GameUtil.firstClick = false;
    }
    void paintSelf(Graphics g){
        g.setColor(Color.ORANGE);
        for (int i = 0; i <= GameUtil.MAP_W; i++) {
            g.drawLine(
                    GameUtil.OFFSET+ i * GameUtil.SQUARE_LENGTH,
                    3*GameUtil.OFFSET,
                    GameUtil.OFFSET+ i* GameUtil.SQUARE_LENGTH,
                    3*GameUtil.OFFSET+GameUtil.MAP_H*GameUtil.SQUARE_LENGTH);
        }
        for (int j = 0; j <= GameUtil.MAP_H; j++) {
            g.drawLine(GameUtil.OFFSET,
                    3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*j,
                    GameUtil.OFFSET+GameUtil.MAP_W*GameUtil.SQUARE_LENGTH,
                    3*GameUtil.OFFSET + j * GameUtil.SQUARE_LENGTH);
        }
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.MAP_BOTTOM[i][j] == -1) {
                    g.drawImage(GameUtil.maozhua,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                } else if (GameUtil.MAP_BOTTOM[i][j] == 1) {
                    g.drawImage(GameUtil.one,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.MAP_BOTTOM[i][j] == 0) {
                    g.drawImage(GameUtil.zero,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.MAP_BOTTOM[i][j] == 2) {
                    g.drawImage(GameUtil.two,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.MAP_BOTTOM[i][j] == 3) {
                    g.drawImage(GameUtil.three,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.MAP_BOTTOM[i][j] == 4) {
                    g.drawImage(GameUtil.four,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.MAP_BOTTOM[i][j] == 5) {
                    g.drawImage(GameUtil.five,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.MAP_BOTTOM[i][j] == 6) {
                    g.drawImage(GameUtil.six,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.MAP_BOTTOM[i][j] == 7) {
                    g.drawImage(GameUtil.seven,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.MAP_BOTTOM[i][j] == 8) {
                    g.drawImage(GameUtil.eight,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
            }
        }
    }
}
