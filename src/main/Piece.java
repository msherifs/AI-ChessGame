package main;


public abstract class Piece {
    private int x, y;
    private String name;
    private String color;
    private boolean selected;
    private int value;

    public int getValue() {
        return value;
    }

    public Piece(int x, int y, String name, String color, int value) {

        this.x = x;
        this.y = y;
        this.selected = false;
        this.name = name;
        this.color = color;
        this.value = value;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() { //return if piece selcted or not
        return selected;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    public abstract String getUni();
    public abstract String getType();

    public abstract boolean checkMove(int x, int y, Board b);


    @Override
    public String toString() {
        return getName();
    }

}
