package ui;

import utils.Position;

import javax.swing.*;
import java.awt.*;

public class Block extends JLabel {
    private int value;

    public Block(int value, Dimension dim) {
        super(Integer.toString(value), SwingConstants.CENTER);
        this.value = value;
        this.setSize(dim);
        this.setOpaque(true);
        this.updateColor();
        this.setFont(new Font("timesnewroman", Font.PLAIN, 26));
        this.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        this.setText(Integer.toString(value));
        this.updateColor();
    }

    public void updateColor() {
        int rgb = 240 - 6 * (int) (Math.log(this.value) / Math.log(2));
        this.setBackground(new Color(rgb, rgb, rgb));
    }

    public void relocate(Position pos) {
        this.setLocation(pos.x * (UI.SIZE + UI.MARGIN) + UI.MARGIN, pos.y * (UI.SIZE + UI.MARGIN) + UI.MARGIN);
    }
}
