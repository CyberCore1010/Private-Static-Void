package Game;

import Backend.Window;
import Objects.ObjectHandler;

public class GameHandler {

    public static int level = 0;

    /**This is simply the main method. It creates the handler and sends it to the game. It also adds the game to a new
     * Window object and runs LevelSelect's selectLevel method to set up the level.
     *
     * @param args - Not used
     */
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");

        ObjectHandler objectHandler = new ObjectHandler();
        Game.GameView game = new Game.GameView(objectHandler);
        new Window(game, "Private Static Void - Made by 1704397");
        Game.LevelSelect.selectLevel(level, objectHandler);
    }
}