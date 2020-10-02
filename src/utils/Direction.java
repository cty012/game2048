package utils;

public enum Direction {
    NONE, UP, DOWN, LEFT, RIGHT;

    public int step() {
        switch (this) {
            case UP:
            case LEFT:
                return -1;
            case DOWN:
            case RIGHT:
                return 1;
            default:
                return 0;
        }
    }
}
