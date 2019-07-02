/**
 *
 */
package cs3331.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Manages all events between View (GUI) and Model (Game State)
 * @author epadilla2
 *
 */
public class Controller implements KeyListener {

    /**
     * Defines action when a key is pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        //pause
        if  (keyCode == KeyEvent.VK_ESCAPE)
        {
            //your code goes here
            return;
        }

        switch(keyCode)
        {
            case KeyEvent.VK_DOWN:
                break;
            case KeyEvent.VK_LEFT:
                break;
            case KeyEvent.VK_RIGHT :
                break;
            case KeyEvent.VK_Z:
                break;
            case KeyEvent.VK_C:
                break;
            default:
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
