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
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle clip = g2.getClipBounds();
        g2.setColor(GameUtil.COLOR_BG);
        if (clip == null) {
            g2.fillRect(0, 0,
                    GameUtil.MAP_W * GameUtil.SQUARE_LENGTH + 2 * GameUtil.OFFSET,
                    GameUtil.MAP_H * GameUtil.SQUARE_LENGTH + 4 * GameUtil.OFFSET);
        } else {
            g2.fillRect(clip.x, clip.y, clip.width, clip.height);
        }

        int boardX = GameUtil.boardX();
        int boardY = GameUtil.boardY();
        int boardW = GameUtil.boardWidth();
        int boardH = GameUtil.boardHeight();

        g2.setColor(GameUtil.COLOR_SHADOW);
        g2.fillRoundRect(
                boardX - GameUtil.PANEL_PADDING,
                boardY - GameUtil.PANEL_PADDING + 3,
                boardW + 2 * GameUtil.PANEL_PADDING,
                boardH + 2 * GameUtil.PANEL_PADDING,
                GameUtil.PANEL_RADIUS,
                GameUtil.PANEL_RADIUS);
        g2.setColor(GameUtil.COLOR_PANEL);
        g2.fillRoundRect(
                boardX - GameUtil.PANEL_PADDING,
                boardY - GameUtil.PANEL_PADDING,
                boardW + 2 * GameUtil.PANEL_PADDING,
                boardH + 2 * GameUtil.PANEL_PADDING,
                GameUtil.PANEL_RADIUS,
                GameUtil.PANEL_RADIUS);
        g2.setColor(GameUtil.COLOR_PANEL_BORDER);
        g2.drawRoundRect(
                boardX - GameUtil.PANEL_PADDING,
                boardY - GameUtil.PANEL_PADDING,
                boardW + 2 * GameUtil.PANEL_PADDING,
                boardH + 2 * GameUtil.PANEL_PADDING,
                GameUtil.PANEL_RADIUS,
                GameUtil.PANEL_RADIUS);

        g2.setColor(GameUtil.COLOR_GRID);
        g2.setStroke(new BasicStroke(1.0f));
        for (int i = 0; i <= GameUtil.MAP_W; i++) {
            g2.drawLine(
                    boardX + i * GameUtil.SQUARE_LENGTH,
                    boardY,
                    boardX + i * GameUtil.SQUARE_LENGTH,
                    boardY + boardH);
        }
        for (int j = 0; j <= GameUtil.MAP_H; j++) {
            g2.drawLine(boardX,
                    boardY + GameUtil.SQUARE_LENGTH*j,
                    boardX + boardW,
                    boardY + j * GameUtil.SQUARE_LENGTH);
        }

        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                drawRoundedTile(g2, getCellImage(GameUtil.MAP_BOTTOM[i][j]), i, j);
            }
        }
        g2.dispose();
    }

    Image getCellImage(int value) {
        switch (value) {
            case -1:
                return GameUtil.maozhua;
            case 0:
                return GameUtil.zero;
            case 1:
                return GameUtil.one;
            case 2:
                return GameUtil.two;
            case 3:
                return GameUtil.three;
            case 4:
                return GameUtil.four;
            case 5:
                return GameUtil.five;
            case 6:
                return GameUtil.six;
            case 7:
                return GameUtil.seven;
            case 8:
                return GameUtil.eight;
            default:
                return null;
        }
    }

    void drawRoundedTile(Graphics2D g2, Image image, int x, int y) {
        if (image == null) {
            return;
        }
        int tileX = GameUtil.tileX(x);
        int tileY = GameUtil.tileY(y);
        int tileSize = GameUtil.tileSize();
        Shape oldClip = g2.getClip();
        g2.setClip(new java.awt.geom.RoundRectangle2D.Double(
                tileX, tileY, tileSize, tileSize, GameUtil.TILE_RADIUS, GameUtil.TILE_RADIUS));
        g2.drawImage(image, tileX, tileY, tileSize, tileSize, null);
        g2.setClip(oldClip);
    }
}
