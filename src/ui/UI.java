package ui;

import utils.Direction;
import utils.Position;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UI {
    public static final int SIZE = 120;
    public static final int MARGIN = 10;

    private final Random random;
    private final Position dim;
    private final JFrame frame;
    private final Block[][] blockArr;

    public UI() {
        this(4, 4);
    }

    public UI(int rows, int cols) {
        this.random = new Random();

        this.dim = new Position(rows, cols);
        this.blockArr = new Block[rows][cols];

        this.frame = new JFrame();
        this.frame.setLayout(null);
        this.frame.setTitle("2048");
        this.frame.setLocation(400, 400);
        this.frame.getContentPane().setPreferredSize(new Dimension(
                (UI.SIZE + UI.MARGIN) * this.dim.x + UI.MARGIN,
                (UI.SIZE + UI.MARGIN) * this.dim.y + UI.MARGIN));

        this.frame.addKeyListener(new MoveDetector(this));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
        this.frame.setResizable(false);
        this.generate(2);
        this.update();
    }

    public List<Position> getEmptyPos() {
        List<Position> empty = new ArrayList<>();
        for (int r = 0; r < this.dim.x; r++) {
            for (int c = 0; c < this.dim.y; c++) {
                if (this.get(r, c) == null) {
                    empty.add(new Position(r, c));
                }
            }
        }
        return empty;
    }

    public void update() {
        this.frame.pack();
        this.frame.setResizable(true);
        this.frame.setResizable(false);
    }

    // Game
    public void move(Direction dir) {
        boolean canMove = false;
        switch (dir) {
            case UP:
            case DOWN:
                canMove = this.moveVerticle(dir.step());
                break;
            case LEFT:
            case RIGHT:
                canMove = this.moveHorizontal(dir.step());
                break;
        }
        if (canMove) {
            this.generate();
            this.update();
        }
    }

    public boolean moveHorizontal(int step) {
        Block block;
        int target;
        boolean canMove = false;
        for (int col = 0; col < this.dim.y; col++) {
            target = step > 0 ? this.dim.x - 1 : 0;
            for (int row = target; row != (step > 0 ? -1 : this.dim.x); row -= step) {
                block = this.blockArr[row][col];
                if (block == null || row == target) continue;
                // Empty target -> move block to target
                if (this.blockArr[target][col] == null) {
                    this.blockArr[row][col] = null;
                    this.blockArr[target][col] = block;
                    block.relocate(new Position(target, col));
                    canMove = true;
                }
                // Same value -> combine
                else if (this.blockArr[target][col].getValue() == block.getValue()) {
                    this.blockArr[target][col].setValue(block.getValue() * 2);
                    this.removeBlock(new Position(row, col));
                    target -= step;
                    canMove = true;
                }
                // Not same value and not neighbors -> move block next to target
                else if (row + step != target) {
                    target -= step;
                    this.blockArr[row][col] = null;
                    this.blockArr[target][col] = block;
                    block.relocate(new Position(target, col));
                    canMove = true;
                }
                // Not same value and are neighbors -> do nothing
                else {
                    target -= step;
                }
            }
        }
        return canMove;
    }

    public boolean moveVerticle(int step) {
        Block block;
        int target;
        boolean canMove = false;
        for (int row = 0; row < this.dim.x; row++) {
            target = step > 0 ? this.dim.y - 1 : 0;
            for (int col = target; col != (step > 0 ? -1 : this.dim.y); col -= step) {
                block = this.blockArr[row][col];
                if (block == null || col == target) continue;
                // Empty target -> move block to target
                if (this.blockArr[row][target] == null) {
                    this.blockArr[row][col] = null;
                    this.blockArr[row][target] = block;
                    block.relocate(new Position(row, target));
                    canMove = true;
                }
                // Same value -> combine
                else if (this.blockArr[row][target].getValue() == block.getValue()) {
                    this.blockArr[row][target].setValue(block.getValue() * 2);
                    this.removeBlock(new Position(row, col));
                    target -= step;
                    canMove = true;
                }
                // Not same value and not neighbors -> move block next to target
                else if (col + step != target) {
                    target -= step;
                    this.blockArr[row][col] = null;
                    this.blockArr[row][target] = block;
                    block.relocate(new Position(row, target));
                    canMove = true;
                }
                // Not same value and are neighbors -> do nothing
                else {
                    target -= step;
                }
            }
        }
        return canMove;
    }

    // Add block
    public void addBlock(Position pos, Block block) {
        System.out.println(pos);
        block.relocate(pos);
        this.blockArr[pos.x][pos.y] = block;
        this.frame.add(block);
    }

    public void generate() {
        List<Position> empty = this.getEmptyPos();
        Position pos = empty.get(this.random.nextInt(empty.size()));
        this.addBlock(pos, BlockFactory.createBlock());
    }

    public void generate(int count) {
        for (int i = 0; i < count; i++) {
            this.generate();
        }
    }

    // Remove block
    public void removeBlock(Position pos) {
        Block block = this.get(pos);
        if (block == null) {
            return;
        }
        this.blockArr[pos.x][pos.y] = null;
        this.frame.remove(block);
    }

    // Find block
    public Block get(int row, int col) {
        return this.blockArr[row][col];
    }

    public Block get(Position pos) {
        return this.get(pos.x, pos.y);
    }
}
