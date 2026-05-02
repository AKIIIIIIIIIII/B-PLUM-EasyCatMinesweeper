import java.awt.*;

public class MapTop {
    MapBottom mapBottom = new MapBottom();

    void regame(){
        for (int i = 0; i < GameUtil.TOP_BOTTOM.length ; i++) {
            for (int j = 0; j < GameUtil.TOP_BOTTOM[i].length ; j++) {
                GameUtil.TOP_BOTTOM[i][j] = 0;
            }
        }
        GameUtil.resetGameState();
    }

    boolean isRestartButton(int mouseX, int mouseY) {
        return mouseX >= GameUtil.restartButtonX()
                && mouseX <= GameUtil.restartButtonX() + GameUtil.SQUARE_LENGTH
                && mouseY >= GameUtil.restartButtonY()
                && mouseY <= GameUtil.restartButtonY() + GameUtil.SQUARE_LENGTH;
    }

    void handleClick(int mouseX, int mouseY, boolean isLeftButton) {
        int gridX = 0;
        int gridY = 0;
        if (mouseX > GameUtil.OFFSET && mouseY > 3 * GameUtil.OFFSET) {
            gridX = (mouseX - GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
            gridY = (mouseY - GameUtil.OFFSET * 3) / GameUtil.SQUARE_LENGTH + 1;
        }
        if (gridX < 1 || gridX > GameUtil.MAP_W || gridY < 1 || gridY > GameUtil.MAP_H) {
            return;
        }

        if (isLeftButton) {
            if (GameUtil.TOP_BOTTOM[gridX][gridY] == 0) {
                if (GameUtil.firstClick) {
                    mapBottom.createMap(gridX, gridY);
                }
                GameUtil.TOP_BOTTOM[gridX][gridY] = -1;
                OpenSpace(gridX, gridY);
            }
        } else {
            if (GameUtil.firstClick) {
                return;
            }
            if (GameUtil.TOP_BOTTOM[gridX][gridY] == 0) {
                GameUtil.TOP_BOTTOM[gridX][gridY] = 1;
            } else if (GameUtil.TOP_BOTTOM[gridX][gridY] == 1) {
                GameUtil.TOP_BOTTOM[gridX][gridY] = 0;
            } else if (GameUtil.TOP_BOTTOM[gridX][gridY] == -1) {
                OpenNum(gridX, gridY);
            }
        }

        bool();
        Success();
    }

    void OpenSpace(int x, int y){
        if (GameUtil.MAP_BOTTOM[x][y]==0) {//周围没有雷
            for (int i = x-1; i <= x+1; i++) {
                for (int j = y-1; j <= y+1; j++) {
                    if (GameUtil.TOP_BOTTOM[i][j] != -1) {
                        GameUtil.TOP_BOTTOM[i][j] = -1;
                        if (i >= 1 && i <= GameUtil.MAP_W && j >= 1 && j <= GameUtil.MAP_H) {
                            OpenSpace(i,j);
                        }
                    }
                }
            }
        }
    }
    void OpenNum(int x, int y){
        int cnt = 0;
        if (GameUtil.MAP_BOTTOM[x][y]>0) {//周围多雷
            for (int i = x-1; i <= x+1; i++) {
                for (int j = y-1; j <=y+1 ; j++) {
                    if (GameUtil.TOP_BOTTOM[i][j] == 1) {
                        cnt++;
                    }
                }
            }
            if (cnt == GameUtil.MAP_BOTTOM[x][y] ) {
                for (int i = x-1; i <= x+1; i++) {
                    for (int j = y-1; j <=y+1 ; j++) {
                        if (GameUtil.TOP_BOTTOM[i][j]!=1) {
                            GameUtil.TOP_BOTTOM[i][j] = -1;
                        }
                        if (i >= 1 && i <= GameUtil.MAP_W && j >= 1 && j <= GameUtil.MAP_H) {
                            OpenSpace(i,j);
                        }
                    }
                }
            }
        }
    }
    //失败判定
    boolean bool(){
        for (int i = 1; i <= GameUtil.MAP_W ; i++) {
            for (int j = 1; j <= GameUtil.MAP_H ; j++) {
                if (GameUtil.TOP_BOTTOM[i][j] == -1 && GameUtil.MAP_BOTTOM[i][j] == -1) {
                    Seebool();
                    GameUtil.status = 2;
                    return true;
                }
            }
        }
        return false;
    }
    void Seebool(){
        for (int i = 1; i <= GameUtil.MAP_W ; i++) {
            for (int j = 1; j <= GameUtil.MAP_H ; j++) {
                if (GameUtil.TOP_BOTTOM[i][j] == 0) {
                    GameUtil.TOP_BOTTOM[i][j] = -1;
                }
                if (GameUtil.TOP_BOTTOM[i][j] == 1 && GameUtil.MAP_BOTTOM[i][j] != -1) {
                    GameUtil.TOP_BOTTOM[i][j] = 2;
                }
            }
        }
    }
    //成功判断
    boolean Success(){
        int cnt = 0;
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H ; j++) {
                if (GameUtil.TOP_BOTTOM[i][j]!=-1) {
                    cnt++;
                }
            }
        }
        if (cnt == GameUtil.CAT_MAX) {
       //     System.out.println("success!");
            GameUtil.status = 1;
            for (int i = 1; i <= GameUtil.MAP_W; i++) {
                for (int j = 1; j <= GameUtil.MAP_H ; j++) {
                    if (GameUtil.TOP_BOTTOM[i][j]==0) {
                        GameUtil.TOP_BOTTOM[i][j]=1;
                    }
                }
            }
            return true;
        }
        return false;
    }
    void paintSelf(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.TOP_BOTTOM[i][j] == 0) {
                    drawRoundedTile(g2, GameUtil.top, i, j);
                } else if (GameUtil.TOP_BOTTOM[i][j] == 1) {
                    drawRoundedTile(g2, GameUtil.coin, i, j);
                }else if (GameUtil.TOP_BOTTOM[i][j] == 2) {
                    drawRoundedTile(g2, GameUtil.mouse, i, j);
                }
            }
        }
        drawRestartButton(g2);
        g2.dispose();
    }

    void drawRoundedTile(Graphics2D g2, Image image, int x, int y) {
        int tileX = GameUtil.tileX(x);
        int tileY = GameUtil.tileY(y);
        int tileSize = GameUtil.tileSize();
        Shape oldClip = g2.getClip();
        g2.setClip(new java.awt.geom.RoundRectangle2D.Double(
                tileX, tileY, tileSize, tileSize, GameUtil.TILE_RADIUS, GameUtil.TILE_RADIUS));
        g2.drawImage(image, tileX, tileY, tileSize, tileSize, null);
        g2.setClip(oldClip);
    }

    void drawRestartButton(Graphics2D g2) {
        int buttonX = GameUtil.restartButtonX();
        int buttonY = GameUtil.restartButtonY();
        int buttonSize = GameUtil.tileSize();
        g2.setColor(GameUtil.COLOR_SHADOW);
        g2.fillRoundRect(buttonX + 2, buttonY + 4, buttonSize, buttonSize, GameUtil.TILE_RADIUS, GameUtil.TILE_RADIUS);
        g2.setColor(GameUtil.COLOR_PANEL);
        g2.fillRoundRect(buttonX, buttonY, buttonSize, buttonSize, GameUtil.TILE_RADIUS, GameUtil.TILE_RADIUS);
        g2.setColor(GameUtil.COLOR_PANEL_BORDER);
        g2.drawRoundRect(buttonX, buttonY, buttonSize, buttonSize, GameUtil.TILE_RADIUS, GameUtil.TILE_RADIUS);

        Image buttonImage = GameUtil.Continue;
        switch (GameUtil.status){
            case 1:
                buttonImage = GameUtil.Success;
                break;
            case 2:
                buttonImage = GameUtil.False;
                break;
            default:
                break;
        }
        Shape oldClip = g2.getClip();
        g2.setClip(new java.awt.geom.RoundRectangle2D.Double(
                buttonX, buttonY, buttonSize, buttonSize, GameUtil.TILE_RADIUS, GameUtil.TILE_RADIUS));
        g2.drawImage(buttonImage, buttonX, buttonY, buttonSize, buttonSize, null);
        g2.setClip(oldClip);
    }

}
