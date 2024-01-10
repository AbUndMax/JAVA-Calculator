package Calculator.Listeners;

import java.awt.event.KeyEvent;

import static Calculator.Handlers.CallHandler.callHandler;

public class KeyListener implements java.awt.event.KeyListener {

    // We don't need the keyTyped or keyReleased Method, so we simply let them empty.
    // The implementation is still needed though we have to fulfill the implement contract with KeyListener
    @Override
    public void keyTyped(KeyEvent e) {}

    // we add some easy translations for multiplication and division. I used the Symbols which are not found on the keyboard
    // simply for appearance choice.
    // then the callHandler Calls is called which will then route the input to the correct Handler
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '*') {
            callHandler("×");
        }
        else if (e.getKeyChar() == '/') {
            callHandler("÷");
        }
        else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            callHandler("=");
        }
        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            callHandler("⌫");
        }
        else {
            callHandler(Character.toString(e.getKeyChar()));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
