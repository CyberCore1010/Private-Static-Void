package Game;

import Objects.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameView extends JComponent {
    //This is the Object handler which is set in the constructor. It's passed throughout the entire program, and is used to manipulate the objects in the game
    public static ObjectHandler objectHandler;

    //This is the camera which is an object of type Camera from my Camera class. It's used for updating the camera each tick of the game
    public static Camera camera;

    //These variables are self explanatory. They're fonts to be used in the game, and are set in the
    //GameView constructor.
    public static Font TitleFont;
    public static Font MenuFont;
    public static Font DialogFont;

    //These variables are simply used to tell the program that it is running. It also creates a new Thread object which
    //Is used for separating the game time from the game logic
    private static boolean isRunning = true;
    private Thread thread;

    //Finally this variable is just for me, as it allows me to enable debug mode to help me fix or test issues.
    public static boolean debug = false;

    /**
     * This is the constructor for the GameView object. It is the main core of my game, and houses the update and
     * render methods for my game, as well as the timer that runs it all.
     * <p>
     * The constructor will first set the fonts to be used in the menus, followed by creating the Camera object, which
     * starts at the position 0,0 however immediately changes to the players location. The constructor will then create
     * a new thread which contains the "start" method below.
     *
     * @param objectHandler - Passes the object handler in so this class can manipulate objects
     */
    GameView(ObjectHandler objectHandler) {
        GameView.objectHandler = objectHandler;
        try {
            TitleFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/Fonts/Base 02.ttf")).deriveFont(100f);
            MenuFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/Fonts/Base 02.ttf")).deriveFont(50f);
            DialogFont = Font.createFont(Font.TRUETYPE_FONT, new File("res/Fonts/retro.ttf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(TitleFont);
            ge.registerFont(MenuFont);
            ge.registerFont(DialogFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        camera = new Camera(0, 0);
        thread = new Thread(this::start);
        thread.start();
    }

    /**This method is the method that controls the time used in the game. It basically works on the basis that each
     * time the game loops it will check the difference between the current system time and the last system time
     * and then runs the update method on those game objects for the time difference between the two. Finally it will
     * render the objects, showing them on the screen. It also has a timer which increases each time, which just shows
     * how much time has passed since the game has started. as of now it doesn't really do anything however it can be
     * used with System.out for debugging purposes.
     */
    private void start() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                update();
                delta--;
            }
            repaint();

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }
        }
        stop();
    }

    /**This is the update method which is called from the game timer in the "start" method. It basically will first
     * find the player in the handler and send it to the camera so that it can change it's position to the cameras
     * players current position. Afterwords it runs the update method in the object handler
     */
    private void update() {
        if(!(GameHandler.level == 0)) {
            for(GameObject tempObject : objectHandler.object) {
                if(tempObject.getId() == ObjectID.player) {
                    camera.update(tempObject);
                }
            }
        } else {
            camera.setX(0);
            camera.setY(0);
        }

        objectHandler.update();
    }

    /**This method is called from the start method when the game is no longer running and simply joins the two threads
     * together to prevent system problems.
     */
    private void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        ////////DRAWING AREA////////

        g2d.translate(-camera.getX(), -camera.getY());
        objectHandler.render(g); //displays objects passed from handler
        //g.setColor(Color.RED);
        //g.fillRect(GameObject.mousePoint.x, GameObject.mousePoint.y, 50, 50);
        g2d.translate(0, 0);

        ////////MENU DRAWING////////
        g2d.dispose();
        g.dispose();
    }
}