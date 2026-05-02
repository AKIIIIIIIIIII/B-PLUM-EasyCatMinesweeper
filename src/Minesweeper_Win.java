// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//设置窗口，初始化
public class Minesweeper_Win extends JFrame {
    MapBottom mapBottom = new MapBottom();
    MapTop mapTop = new MapTop();
    int w = GameUtil.MAP_W * GameUtil.SQUARE_LENGTH + 2 * GameUtil.OFFSET;
    int h = GameUtil.MAP_H * GameUtil.SQUARE_LENGTH + 5 * GameUtil.OFFSET;
    JPanel gamePanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            mapBottom.paintSelf(g);
            mapTop.paintSelf(g);
        }
    };

    public void Launch(){
        gamePanel.setPreferredSize(new Dimension(w, h));
        gamePanel.setFocusable(true);
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                GameUtil.updatePress(e.getX(), e.getY());
                switch (GameUtil.status) {
                    case 0:
                        if (SwingUtilities.isLeftMouseButton(e) && mapTop.isRestartButton(e.getX(), e.getY())) {
                            mapBottom.regame();
                            mapTop.regame();
                        } else if (SwingUtilities.isLeftMouseButton(e)) {
                            mapTop.handleClick(e.getX(), e.getY(), true);
                        } else if (SwingUtilities.isRightMouseButton(e)) {
                            mapTop.handleClick(e.getX(), e.getY(), false);
                        }
                        gamePanel.repaint();
                        break;
                    case 1:
                    case 2:
                        if (SwingUtilities.isLeftMouseButton(e) && mapTop.isRestartButton(e.getX(), e.getY())) {
                            mapBottom.regame();
                            mapTop.regame();
                            gamePanel.repaint();
                        }
                        break;
                    default:
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                GameUtil.clearPress();
                gamePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                GameUtil.clearHover();
                GameUtil.clearPress();
                gamePanel.repaint();
            }
        });
        gamePanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                GameUtil.updateHover(e.getX(), e.getY());
                gamePanel.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                GameUtil.updateHover(e.getX(), e.getY());
                gamePanel.repaint();
            }
        });
        new Timer(1000, e -> {
            if (!GameUtil.firstClick && GameUtil.status == 0) {
                gamePanel.repaint();
            }
        }).start();
        this.setContentPane(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setTitle("Minesweeper");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        Minesweeper_Win Window = new Minesweeper_Win();
        Window.Launch();
    }
}
