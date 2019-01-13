package Objects;

import Backend.Window;
import Game.GameView;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class PauseHandler extends GameObject {
    private int staticTime = 0;
    private BufferedImage Static;

    private ObjectHandler objectHandler;

    /**
     * This is the abstract GameObject class. All other classes extend to this one in some manor. It contains abstract
     * methods to be used with every game object. The constructor simply sets the variables that will be used by each
     * GameObject, such as the x and y position.
     *
     * @param x        - x location of the object
     * @param y        - y location of the object
     * @param Z        - Z location of the object
     * @param width    - width of the object
     * @param height   - height of the object
     * @param id       - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     */
    public PauseHandler(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler) {
        super(x, y, Z, width, height, id, blocking);
        Static = objectHandler.static0;
        this.objectHandler = objectHandler;
    }


    @Override
    @SuppressWarnings("Duplicates")
    public void update() {
        if (staticTime == 0 || staticTime == 20) {
            Static = objectHandler.static0;
            staticTime = 0;
        } else if(staticTime == 5) {
            Static = objectHandler.static1;
        } else if(staticTime == 10) {
            Static = objectHandler.static2;
        } else if(staticTime == 15) {
            Static = objectHandler.static3;
        }
        staticTime++;
    }

    @Override
    public void render(Graphics g) {
        if(objectHandler.isGamePaused()) {
            g.setColor(new Color(0, 0, 0, 0.5f));
            g.fillRect((int)GameView.camera.getX()-100, (int)GameView.camera.getY(), Window.gameWidth+200, Window.gameHeight);

            g.setColor(Color.BLACK);
            g.fillRect((int)GameView.camera.getX()+((Window.gameWidth/4)+(Window.gameWidth/8)),
                    (int)GameView.camera.getY()+(Window.gameHeight/4)+(Window.gameHeight/8), Window.gameWidth/4, Window.gameHeight/4);
            g.setColor(Color.GRAY);
            g.drawRect((int)GameView.camera.getX()+((Window.gameWidth/4)+(Window.gameWidth/8)),
                    (int)GameView.camera.getY()+ (Window.gameHeight/4)+(Window.gameHeight/8), Window.gameWidth/4, Window.gameHeight/4);

            g.setFont(GameView.MenuFont);
            g.drawString("Press Enter to",(int)(GameView.camera.getX()+((Window.gameWidth/4)+(Window.gameWidth/8)))+5,
                    (int)(GameView.camera.getY()+ (Window.gameHeight/4)+(Window.gameHeight/8))+60);
            g.drawString("go back to the", (int)(GameView.camera.getX()+((Window.gameWidth/4)+(Window.gameWidth/8)))+5,
                    (int)(GameView.camera.getY()+ (Window.gameHeight/4)+(Window.gameHeight/8))+135);
            g.drawString("main menu", (int)(GameView.camera.getX()+((Window.gameWidth/4)+(Window.gameWidth/8)))+5,
                    (int)(GameView.camera.getY()+ (Window.gameHeight/4)+(Window.gameHeight/8))+210);

            g.drawImage(Static, (int)GameView.camera.getX()-100, (int)GameView.camera.getY(), Window.gameWidth+200, Window.gameHeight, null);
        }
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public void activate() {

    }

    @Override
    public void reset() {

    }
}
