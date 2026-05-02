//随机生成地雷
public class BottomCat {
    int[] Cat = new int[GameUtil.CAT_MAX*2];
    boolean Flag = true;
    int x,y;
    void NewCat() {
        for (int i = 0; i < GameUtil.CAT_MAX * 2; i = i + 2) {
            x = (int) (Math.random() * GameUtil.MAP_W + 1);
            y = (int) (Math.random() * GameUtil.MAP_H + 1);
            for (int j = 0; j < i; j = j + 2) {
                if (x == Cat[j] && y == Cat[j + 1]) {
                    Flag = false;
                    i = i - 2;
                    break;
                }
            }
            if (Flag) {
                Cat[i] = x;
                Cat[i + 1] = y;
                GameUtil.MAP_BOTTOM[Cat[i]][Cat[i + 1]] = -1;
            }
            Flag = true;
        }
    }
}
