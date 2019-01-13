package Backend;

import Game.GameHandler;
import Game.GameView;
import Game.LevelSelect;
import Objects.GameObject;
import Objects.ObjectHandler;
import Objects.ObjectID;

import Backend.SFXplayer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

public class KeyInput extends KeyAdapter{
    private ObjectHandler objectHandler;

    /**
     * @param objectHandler - takes the object handler as a parameter so it can set variables in it.
     */
    KeyInput(ObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        /*
        This code is used to set the players movement boolean values. Used in unison with the keyReleased to make the
        player move when the key is pressed and stop when released. This is a better way of moving the player as it
        allows for smoother movement.
         */
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            objectHandler.setUp(true);
        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            objectHandler.setLeft(true);
            if(objectHandler.isOptionsMenu()) {
                SFXplayer.playSFX("ButtonHover");
                SFXplayer.gain += 5;
                if(SFXplayer.gain > 80) {
                    SFXplayer.gain = 0;
                }
                SFXplayer.resetVolume();
            }
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            objectHandler.setDown(true);
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            objectHandler.setRight(true);
            if(objectHandler.isOptionsMenu()) {
                SFXplayer.playSFX("ButtonHover");
                SFXplayer.gain -= 5;
                if(SFXplayer.gain < -6) {
                    SFXplayer.gain = -6;
                }
                SFXplayer.resetVolume();
            }
        }

        if(key == KeyEvent.VK_ESCAPE) {
            if(objectHandler.isOptionsMenu()) {
                SFXplayer.playSFX("ButtonClick");
                objectHandler.setOptionsMenu(false);
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        //See similar installment in "keyPressed"
        if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            objectHandler.setUp(false);
        }
        if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            objectHandler.setLeft(false);
        }
        if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            objectHandler.setDown(false);
        }
        if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            objectHandler.setRight(false);
        }

        if(key == KeyEvent.VK_F) {
            objectHandler.setFlashLightOn(!objectHandler.isFlashLightOn());
            for(GameObject object : objectHandler.object) {
                if(object.getId() == ObjectID.playerLight) {
                    object.activate();
                }
            }
        }
        if(key == KeyEvent.VK_NUMPAD5) {
            GameView.debug = !GameView.debug;
        }
        if(key == KeyEvent.VK_ENTER) {
            if(objectHandler.isDialogActive()&&!objectHandler.isGamePaused()) {
                SFXplayer.playSFX("ButtonHover");
                if((objectHandler.getEventStage() == 0 && objectHandler.getDialogStage() < 5) || objectHandler.getEventStage() != 0) {
                    int temp = objectHandler.getDialogStage();
                    temp++;
                    objectHandler.setDialogStage(temp);
                }
            } else if(objectHandler.isGamePaused()) {
                SFXplayer.playSFX("ButtonClick");
                objectHandler.setGamePaused(false);
                objectHandler.object.clear();
                GameHandler.level = 0;
                LevelSelect.selectLevel(GameHandler.level, objectHandler);
            }
        }
        if(key == KeyEvent.VK_ESCAPE) {
            if(GameHandler.level != 0) {
                SFXplayer.playSFX("ButtonHover");
                objectHandler.setGamePaused(!objectHandler.isGamePaused());
                if(!(GameHandler.level == 1 && objectHandler.getDialogStage() < 5)) {
                    objectHandler.setCanMove(!objectHandler.isCanMove());
                }
            }
        }
    }
}
