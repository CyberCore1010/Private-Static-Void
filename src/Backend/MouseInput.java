package Backend;

import Objects.ObjectHandler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
    private ObjectHandler objectHandler;

    /**All this class does is listen for the mouse button being pressed and if it is let the player attack. (The player
     * uses the boolean Attack which is set here to know when to attack
     *
     * @param objectHandler - takes the handler so it can change values in it
     */
    MouseInput(ObjectHandler objectHandler) {
        this.objectHandler = objectHandler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            objectHandler.setMouseClicked(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            objectHandler.setMouseClicked(false);
        }
    }
}
