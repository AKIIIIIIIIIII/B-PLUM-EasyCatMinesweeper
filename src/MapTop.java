import java.awt.*;

public class MapTop {
    static int temp_x,temp_y;
    MapBottom mapBottom = new MapBottom();
    void regame(){
        for (int i = 0; i < GameUtil.TOP_BOTTOM.length ; i++) {
            for (int j = 0; j < GameUtil.TOP_BOTTOM[i].length ; j++) {
                GameUtil.TOP_BOTTOM[i][j] = 0;
            }
        }
        GameUtil.resetGameState();
    }
    void logic(){
        temp_x = 0;
        temp_y = 0;
        if (GameUtil.MOUSE_X >GameUtil.OFFSET && GameUtil.MOUSE_Y > 3*GameUtil.OFFSET) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET)/GameUtil.SQUARE_LENGTH + 1;
            temp_y = (GameUtil.MOUSE_Y - GameUtil.OFFSET * 3)/GameUtil.SQUARE_LENGTH + 1;
        }
        if (temp_x >= 1 && temp_x <= GameUtil.MAP_W && temp_y >= 1 && temp_y <= GameUtil.MAP_H) {
            if (GameUtil.LEFT) {
                if (GameUtil.TOP_BOTTOM[temp_x][temp_y] == 0) {
                    if (GameUtil.firstClick) {
                        mapBottom.createMap(temp_x, temp_y);
                    }
                    GameUtil.TOP_BOTTOM[temp_x][temp_y] = -1;
                    OpenSpace(temp_x,temp_y);
                }
                GameUtil.LEFT = false;
            }else if (GameUtil.RIGHT) {
                if (GameUtil.TOP_BOTTOM[temp_x][temp_y] == 0) {
                    GameUtil.TOP_BOTTOM[temp_x][temp_y] = 1;
                }else if (GameUtil.TOP_BOTTOM[temp_x][temp_y] == 1) {
                    GameUtil.TOP_BOTTOM[temp_x][temp_y] = 0;
                }else if (GameUtil.TOP_BOTTOM[temp_x][temp_y] == -1){
                    OpenNum(temp_x,temp_y);
                }
                GameUtil.RIGHT = false;
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
        logic();
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                if (GameUtil.TOP_BOTTOM[i][j] == 0) {
                    g.drawImage(GameUtil.top,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                } else if (GameUtil.TOP_BOTTOM[i][j] == 1) {
                    g.drawImage(GameUtil.coin,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }else if (GameUtil.TOP_BOTTOM[i][j] == 2) {
                    g.drawImage(GameUtil.mouse,
                            GameUtil.OFFSET + (i-1) * GameUtil.SQUARE_LENGTH + 1,
                            3 * GameUtil.OFFSET + GameUtil.SQUARE_LENGTH * (j-1) + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
            }
        }
        switch (GameUtil.status){
            case 0:
                g.drawImage(GameUtil.Continue,
                        (GameUtil.OFFSET * 2 + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W-1))/2,
                        GameUtil.OFFSET,
                        GameUtil.SQUARE_LENGTH,
                        GameUtil.SQUARE_LENGTH,
                        null);
                break;
            case 1:
                g.drawImage(GameUtil.Success,
                        (GameUtil.OFFSET * 2 + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W-1))/2,
                        GameUtil.OFFSET,
                        GameUtil.SQUARE_LENGTH,
                        GameUtil.SQUARE_LENGTH,
                        null);
                break;
            case 2:
                g.drawImage(GameUtil.False,
                        (GameUtil.OFFSET * 2 + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W-1))/2,
                        GameUtil.OFFSET,
                        GameUtil.SQUARE_LENGTH,
                        GameUtil.SQUARE_LENGTH,
                        null);
                break;
                default:

        }
    }

}
