package Game;

import Backend.SFXplayer;
import Objects.MenuController;
import Objects.MenuObject;
import Objects.ObjectHandler;
import Objects.ObjectID;

import static Backend.Window.gameHeight;
import static Backend.Window.gameWidth;

public class Menu {
    /**This is the method that simply just adds all the objects needed for the game to run to the handler. It's called
     * from levelSelect.
     *
     * @param objectHandler handler is passed in so that it can add objects to it.
     */
    public static void setUp(ObjectHandler objectHandler) {
        SFXplayer.playSFX("Glitch2");
        objectHandler.object.add(new MenuController(0, 0, 0, gameWidth, gameHeight, ObjectID.menu, false, objectHandler));
        objectHandler.addObject(new MenuObject(100, 400, 0, 350, 60, ObjectID.menuObject, false, objectHandler, "Continue", false, () -> { }));

        objectHandler.addObject(new MenuObject(100, 470, 0, 350, 60, ObjectID.menuObject, false, objectHandler, "New Game", true, () -> {
            objectHandler.object.clear();
            GameHandler.level = 1;
            LevelSelect.selectLevel(GameHandler.level, objectHandler);
        }));

        objectHandler.addObject(new MenuObject(100, 540, 0, 350, 60, ObjectID.menuObject, false, objectHandler, "Load Game", false, () -> { }));

        objectHandler.addObject(new MenuObject(100, 610, 0, 350, 60, ObjectID.menuObject, false, objectHandler, "Options", true, () -> {
            objectHandler.setOptionsMenu(true);
        }));

        objectHandler.addObject(new MenuObject(100, 680, 0, 350, 60, ObjectID.menuObject, false, objectHandler, "Exit", true, () -> {
            System.exit(0);
        }));

        SFXplayer.backgroundMusic("Disintegrating Music Box - myuu");
    }
}
