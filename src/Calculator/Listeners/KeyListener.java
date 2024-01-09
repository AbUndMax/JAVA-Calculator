package Calculator.Listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static Calculator.Handlers.CallHandler.callHandler;

public class KeyListener implements java.awt.event.KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '*') {
            callHandler("×");
        }
        if (e.getKeyChar() == '/') {
            callHandler("÷");
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            callHandler("=");
        }
        callHandler(Character.toString(e.getKeyChar()));
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
