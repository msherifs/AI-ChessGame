package main;

import java.io.Serializable;

public class Bishop extends Piece implements Serializable{

    public Bishop() {

    }
    public Bishop (int x, int y,String name, String color)
    {
        super(x,y,name,color, 325, 30);
    }
    public boolean checkMove(int x, int y, Board b) {

        if(b.getCell(x,y).getColor().equals(this.getColor()))
            return false;

        int old_x = super.getX();
        int old_y = super.getY();
        int new_x = x;
        int new_y = y;

        if(b.hasPiece(new_x,new_y)) {
            if(b.getCell(new_x,new_y).getColor()== super.getColor()){
                return false;

            }
        }
        // check that the bishop moves any number of spaces in a diagonal fashion.
        if(Math.abs(new_x-old_x) == Math.abs(new_y-old_y) && (new_x>=0 && new_x <=7) && (new_y>=0 && new_y<=7))
        {
            if (new_x - old_x > 0 && new_y - old_y > 0) {
                for (int i = 1; i < new_x - old_x; i++) {
                    if (b.hasPiece(old_x + i, old_y + i)) {
                        return false;
                    }
                }
                return true;
            }
            else if (new_x - old_x < 0 && new_y - old_y < 0) {
                for (int i = 1; i < old_x - new_x; i++) {
                    if (b.hasPiece(new_x + i, new_y + i)) {
                        return false;
                    }
                }
                return true;
            }
            else if (((new_x - old_x) > 0) && ((new_y - old_y) < 0)) {
                for (int i = 1; i < new_x - old_x; i++) {
                    if (b.hasPiece(old_x + i, old_y - i)) {
                        return false;
                    }
                }
                return true;
            }
            else if (new_x - old_x < 0 && new_y - old_y > 0) {
                for (int i = 1; i < Math.abs(new_x - old_x); i++) {
                    if (b.hasPiece(old_x - i, old_y + i)) {
                        return false;
                    }
                }
                return true;
            }
            return true;

        }
        return false;

    }
    public String getType() {

        return "Bishop";
    }
    public String getUni() {
        if (this.getColor().equals("w")) {
            return "♗";
        } else return "♝";
    }

    public void calculateAttackAndDefense(Board b) {
        attackValue = 0;
        defenceValue = 0;

        for (int i = x + 1, j = y + 1; i < 8 && j < 8; i++, j++) {
            if (b.hasPiece(i, j)) {
                Piece piece = b.getCell(i, j);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = x - 1, j = y + 1; i >= 0 && j < 8; i--, j++) {
            if (b.hasPiece(i, j)) {
                Piece piece = b.getCell(i, j);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = x + 1, j = y - 1; i < 8 && j >= 0; i++, j--) {
            if (b.hasPiece(i, j)) {
                Piece piece = b.getCell(i, j);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

        for (int i = x - 1, j = y - 1; i >= 0 && j >= 0; i--, j--) {
            if (b.hasPiece(i, j)) {
                Piece piece = b.getCell(i, j);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue += this.actionValue - piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue += piece.actionValue - this.actionValue;
                }
                break;
            }
        }

    }

}
