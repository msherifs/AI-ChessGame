package main;


public abstract class Piece {
    protected int x, y;
    protected String name;
    protected String color;
    protected boolean selected;
    protected int value;
    protected int actionValue; //subtracted in case of defending and added in case of attacking
    protected int attackValue;
    protected int defenceValue;

    public Piece(int x, int y, String name, String color, int value, int actionValue) {
        this.x = x;
        this.y = y;
        this.selected = false;
        this.name = name;
        this.color = color;
        this.value = value;
        attackValue = 0;
        defenceValue = 0;
        this.actionValue = actionValue;
    }

    public int getDefenceValue() {
        return defenceValue;
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getActionValue() {
        return actionValue;
    }

    public int getValue() {
        return value;
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

    public abstract void calculateAttackAndDefense(Board b);

    @Override
    public String toString() {
        return getName();
    }
}
