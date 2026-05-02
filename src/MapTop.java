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
                && mouseX <= GameUtil.restartButtonX() + GameUtil.tileSize()
                && mouseY >= GameUtil.restartButtonY()
                && mouseY <= GameUtil.restartButtonY() + GameUtil.tileSize();
    }

    void handleClick(int mouseX, int mouseY, boolean isLeftButton) {
        int gridX = 0;
        int gridY = 0;
        if (mouseX > GameUtil.OFFSET && mouseY > GameUtil.boardY()) {
            gridX = (mouseX - GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
            gridY = (mouseY - GameUtil.boardY()) / GameUtil.SQUARE_LENGTH + 1;
        }
        if (gridX < 1 || gridX > GameUtil.MAP_W || gridY < 1 || gridY > GameUtil.MAP_H) {
            return;
        }

        if (isLeftButton) {
            if (GameUtil.TOP_BOTTOM[gridX][gridY] == 0) {
                if (GameUtil.firstClick) {
                    GameUtil.startTimer();
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
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        GameUtil.updateElapsedTime();
        drawTopInfo(g2);
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.TOP_BOTTOM[i][j] == 0) {
                    drawRoundedTile(g2, GameUtil.top, i, j);
                    drawInteractionOverlay(g2, i, j);
                } else if (GameUtil.TOP_BOTTOM[i][j] == 1) {
                    drawRoundedTile(g2, GameUtil.coin, i, j);
                    drawInteractionOverlay(g2, i, j);
                }else if (GameUtil.TOP_BOTTOM[i][j] == 2) {
                    drawRoundedTile(g2, GameUtil.mouse, i, j);
                }
            }
        }
        drawRestartButton(g2);
        g2.dispose();
    }

    void drawTopInfo(Graphics2D g2) {
        int panelW = GameUtil.INFO_PANEL_WIDTH;
        int panelH = GameUtil.INFO_PANEL_HEIGHT;
        int panelX = GameUtil.boardX() + (GameUtil.boardWidth() - panelW) / 2;
        int panelY = GameUtil.restartButtonY() + GameUtil.tileSize() + GameUtil.TOP_SECTION_GAP;

        g2.setColor(GameUtil.COLOR_SHADOW);
        g2.fillRoundRect(panelX + 1, panelY + 2, panelW, panelH, 15, 15);
        g2.setColor(GameUtil.COLOR_PANEL);
        g2.fillRoundRect(panelX, panelY, panelW, panelH, 15, 15);
        g2.setColor(GameUtil.COLOR_PANEL_BORDER);
        g2.drawRoundRect(panelX, panelY, panelW, panelH, 15, 15);

        int itemW = panelW / 3;
        drawInfoItem(g2, panelX, panelY + 8, itemW, "Mines", String.valueOf(remainingMarks()));
        drawDivider(g2, panelX + itemW, panelY + 11, panelH - 22);
        drawInfoItem(g2, panelX + itemW, panelY + 8, itemW, "Time", GameUtil.elapsedSeconds + "s");
        drawDivider(g2, panelX + 2 * itemW, panelY + 11, panelH - 22);
        drawInfoItem(g2, panelX + 2 * itemW, panelY + 8, panelW - 2 * itemW, "Status", statusText());
    }

    void drawInfoItem(Graphics2D g2, int x, int y, int w, String label, String value) {
        g2.setColor(new Color(113, 113, 122));
        g2.setFont(new Font("Avenir Next", Font.PLAIN, 11));
        FontMetrics labelMetrics = g2.getFontMetrics();
        int labelX = x + (w - labelMetrics.stringWidth(label)) / 2;
        g2.drawString(label, labelX, y + 12);
        g2.setColor(GameUtil.COLOR_TEXT);
        g2.setFont(new Font("Avenir Next", Font.BOLD, 15));
        FontMetrics valueMetrics = g2.getFontMetrics();
        int valueX = x + (w - valueMetrics.stringWidth(value)) / 2;
        g2.drawString(value, valueX, y + 31);
    }

    void drawDivider(Graphics2D g2, int x, int y, int h) {
        g2.setColor(new Color(228, 228, 231));
        g2.drawLine(x, y, x, y + h);
    }

    int remainingMarks() {
        int marks = 0;
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.TOP_BOTTOM[i][j] == 1) {
                    marks++;
                }
            }
        }
        return Math.max(0, GameUtil.CAT_MAX - marks);
    }

    String statusText() {
        if (GameUtil.status == 1) {
            return "Clear";
        }
        if (GameUtil.status == 2) {
            return "Try again";
        }
        if (GameUtil.firstClick) {
            return "Ready";
        }
        return "Playing";
    }

    void drawInteractionOverlay(Graphics2D g2, int x, int y) {
        boolean pressed = GameUtil.PRESS_X == x && GameUtil.PRESS_Y == y;
        boolean hovered = GameUtil.HOVER_X == x && GameUtil.HOVER_Y == y;
        if (!pressed && !hovered) {
            return;
        }
        int tileX = GameUtil.tileX(x);
        int tileY = GameUtil.tileY(y);
        int tileSize = GameUtil.tileSize();
        if (pressed) {
            g2.setColor(GameUtil.COLOR_PRESSED);
        } else {
            g2.setColor(GameUtil.COLOR_HOVER);
        }
        g2.fillRoundRect(tileX, tileY, tileSize, tileSize, GameUtil.TILE_RADIUS, GameUtil.TILE_RADIUS);
        if (hovered) {
            g2.setColor(new Color(255, 255, 255, 120));
            g2.drawRoundRect(tileX, tileY, tileSize - 1, tileSize - 1, GameUtil.TILE_RADIUS, GameUtil.TILE_RADIUS);
        }
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
