package ui;

import java.awt.*;

public class BlockFactory {
    public static Block createBlock() {
        return new Block(Math.random() < 0.9 ? 2 : 4, new Dimension(UI.SIZE, UI.SIZE));
    }

    public static Block createBlock(int value) {
        return new Block(value, new Dimension(UI.SIZE, UI.SIZE));
    }
}
