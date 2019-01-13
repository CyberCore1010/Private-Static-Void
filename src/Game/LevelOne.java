package Game;

import Backend.SFXplayer;
import Backend.Window;
import Objects.*;
import Objects.Rectangle;

import java.awt.*;

public class LevelOne {
    /**This is the method that simply just adds all the objects needed for the game to run to the handler. It's called
     * from levelSelect.
     *
     * @param objectHandler handler is passed in so that it can add objects to it.
     */
    public static void setUp(ObjectHandler objectHandler) {
        objectHandler.setCanMove(false);
        objectHandler.setVisible(false);
        objectHandler.setDialogActive(true);
        objectHandler.setDialogStage(0);
        objectHandler.setEventStage(0);

        buildBedroomBackground(objectHandler);

        objectHandler.addObject(new Player(0, 500, 2, 400, 400, ObjectID.player, false, objectHandler));

        buildBedroomForeground(objectHandler);

        objectHandler.addObject(new LightSource(100, 700, 4, 0, 0, ObjectID.playerLight, false, objectHandler, new Color(0.0f, 0.0f, 1.0f, 0.5f)));

        objectHandler.addObject(new Fade(-Window.gameWidth/2, 0, 7, Window.gameWidth*2, Window.gameHeight*2, ObjectID.fade, false, objectHandler, false));

        objectHandler.addObject(new EventHandler((int)GameView.camera.getX()+100, (int)GameView.camera.getY()+(Window.gameHeight-260),
                6, Window.gameWidth-230, 200, ObjectID.handler, false, objectHandler));

        objectHandler.addObject(new PauseHandler(0, 0, 8, 0, 0, ObjectID.handler, false, objectHandler));

        SFXplayer.backgroundMusic("Disintegrating - myuu");
    }

    private static void buildBedroomBackground(ObjectHandler objectHandler) {
        //Build background walls
        objectHandler.addObject(new Rectangle(-80, 350, 0, 1069, 530, ObjectID.rectangle, false, new Color(224, 224, 224)));
        objectHandler.addObject(new Rectangle(-80, 350, 0, 1069, 30, ObjectID.rectangle, false, new Color(0, 126, 178)));
        objectHandler.addObject(new Rectangle(-80, 880, 0, 1069, 30, ObjectID.rectangle, false, new Color(25, 0, 51)));

        //Build furniture
        objectHandler.addObject(new Furniture(0, 400, 1, 800, 400, ObjectID.curtain, false, objectHandler, false, false));
        objectHandler.addObject(new Furniture(200, 500, 1, 400, 400, ObjectID.drawer, false, objectHandler, false, false));
        objectHandler.addObject(new Furniture(0, 500, 1, 400, 400, ObjectID.deskChair, false, objectHandler, true, false));
        objectHandler.addObject(new Furniture(-248, 500, 1, 400, 400, ObjectID.bookShelf, false, objectHandler, false, false));
        objectHandler.addObject(new Furniture(-150, 500, 1, 400, 400, ObjectID.desk, false, objectHandler, true, false));
        objectHandler.addObject(new Furniture(768, 500, 1, 400, 400, ObjectID.lampShelf, false, objectHandler, false, false));
        objectHandler.addObject(new Furniture(600, 500, 1, 400, 400, ObjectID.bed, false, objectHandler, false, false));
    }

    private static void buildBedroomForeground(ObjectHandler objectHandler) {
        //Build foreground objects
        objectHandler.addObject(new Furniture(805, 500, 1, 400, 400, ObjectID.bedroomDoor, true, objectHandler, false, true));

        //Invisible Blockers
        objectHandler.addObject(new Blocker(-90, 350, 0, 10, 530, ObjectID.blocker, true));
    }
}
