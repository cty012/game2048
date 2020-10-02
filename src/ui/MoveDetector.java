package ui;

import utils.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveDetector implements KeyListener {
    private UI ui;

    public MoveDetector(UI ui) {
        this.ui = ui;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                ui.move(Direction.UP);
                break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                ui.move(Direction.LEFT);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                ui.move(Direction.DOWN);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                ui.move(Direction.RIGHT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
