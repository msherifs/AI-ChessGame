package main;

import java.io.Serializable;

public class EmptyPiece extends Piece implements Serializable{

    public EmptyPiece(){

    }

    public EmptyPiece(int x, int y){
        super(x, y, " ", "e", 0, 0);
    }

    @Override
    public boolean checkMove(int x, int y, Board b) {
        return false;
    }

    @Override
    public String getType() {
        return "e";
    }
    public String getUni() {

        return ("♢");

    }
    public void calculateAttackAndDefense(Board b) {

    }
}
