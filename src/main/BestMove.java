package main;

public class BestMove {
    private int value;
    private Move move;

    public BestMove(int value) {
        this.value = value;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
