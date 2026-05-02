// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Thread.*;

//设置窗口，初始化
public class Minesweeper_Win extends JFrame {
    Image Screen = null;
    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    int w = GameUtil.MAP_W * GameUtil.SQUARE_LENGTH + 2 * GameUtil.OFFSET;
    int h = GameUtil.MAP_H * GameUtil.SQUARE_LENGTH + 4 * GameUtil.OFFSET;
    public void Launch(){
    //逐次使窗口可见，设置大小，位置，名称，设置关闭方式;
        this.setVisible(true);
        this.setSize(w,h);
        this.setLocationRelativeTo(null);
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    //click
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (GameUtil.status){
                    case 0:
                        if(e.getButton() == 1){
                            GameUtil.MOUSE_X=e.getX();
                            GameUtil.MOUSE_Y=e.getY();
                            GameUtil.LEFT=true;
                        }else if (e.getButton() == 3) {
                            GameUtil.MOUSE_X=e.getX();
                            GameUtil.MOUSE_Y=e.getY();
                            GameUtil.RIGHT=true;
                        }
                    case 1:
                    case 2:
                        if(e.getButton() == 1){
                            if (e.getX()>=(GameUtil.OFFSET * 2 + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W-1))/2
                                    && e.getX() <= (GameUtil.OFFSET * 2 + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W-1))/2 + GameUtil.SQUARE_LENGTH
                                    && e.getY()>=GameUtil.OFFSET
                                    && e.getY()<= GameUtil.OFFSET + GameUtil.SQUARE_LENGTH) {
                               mapBottom.regame();
                               mapTop.regame();
                               GameUtil.status=0;
                            }
                        }
                        break;
                    default:
                }

            }
        });
        while (true){
            repaint();
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        Screen = this.createImage(w,h);
        Graphics Gscreen = Screen.getGraphics();
        mapBottom.paintSelf(Gscreen);
        mapTop.paintSelf(Gscreen);
        g.drawImage(Screen,0,0,null);
    }

    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        Minesweeper_Win Window = new Minesweeper_Win();
        Window.Launch();
    }
}