package Backend;

import Game.GameView;
import Objects.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Objects.GameObject.mousePoint;

public class Window {
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static int gameWidth = (int)screenSize.getWidth()-50;
    public static int gameHeight = (int)screenSize.getHeight()-100;

    public Window(Component comp, String title) {
        JFrame frame = new JFrame(title);
        frame.setLocation((int)((screenSize.getWidth()/2)-(gameWidth/2)), (int)((screenSize.getHeight()/2)-(gameHeight/2)));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER, comp);
        frame.getContentPane().setBackground(Color.black);
        frame.setSize(gameWidth, gameHeight);

        frame.setVisible(true);
        frame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        frame.addKeyListener(new KeyInput(GameView.objectHandler));
        frame.addMouseListener(new MouseInput(GameView.objectHandler));
        frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Point temp = e.getPoint();
                mousePoint.x = (temp.x-8)+(int)GameView.camera.getX();
                mousePoint.y = (temp.y-31)+(int)GameView.camera.getY();
            }
        });
    }
}
