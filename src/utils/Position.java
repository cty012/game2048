package utils;

public class Position {
    public int x;
    public int y;

    public Position() {
    }

    public Position(int x, int y) {
        this();
        this.setPos(x, y);
    }

    @Override
    public String toString() {
        return "(x: " + x + ", y: " + y + ')';
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
