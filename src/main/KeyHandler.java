package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public String lastKeyPressed;
    public boolean anyKeyPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        lastKeyPressed = "d";
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = true;
            lastKeyPressed = "w";
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
            lastKeyPressed = "s";
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
            lastKeyPressed = "a";
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
            lastKeyPressed = "d";
        }
        if(KeyEvent.KEY_PRESSED != 0) {
            anyKeyPressed = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
