package main;

import java.io.Serializable;

public class Pawn extends Piece implements Serializable{

    public Pawn(){

    }

    public Pawn(int x, int y, String name, String color) {
        super(x, y, name, color, 100, 6);
    }

    public boolean checkMove(int row, int col, Board b) {

        if (b.getCell(row, col).getColor().equals(this.getColor()))
            return false;

        int old_row = super.getX();
        int old_col = super.getY();
        int new_row = row;
        int new_col = col;

        if ((new_row == old_row) && (new_col == old_col))
            return false;

        if(super.getColor().equals("w")){ //case that pawn is white
            if(old_row == 6){  //case that pawn is in original position
                //allows pawn to move two spaces ahead as long as there is no space in between
                if(Math.abs(new_col - old_col) == 1 && new_row == (old_row - 1) && b.hasPiece(new_row, new_col)){
                    return true;
                }else if(new_col == old_col && new_row == old_row -1 && !b.hasPiece(new_row, new_col)){
                    return true;
                }else if(new_col == old_col && new_row == old_row - 2 && !b.hasPiece(new_row, new_col)){
                    return true;
                }
                return false;
            }else{//case pawn is not in original position
                //check if pawn is capturing another piece
                if(Math.abs(new_row - old_row) == 1 && new_col == old_col -1 && b.hasPiece(new_row, new_col)){
                    return true;
                    //check that pawn is moving forward one square
                }else if(new_col == old_col && new_row == old_row -1 && !b.hasPiece(new_row, new_col)){
                    return true;
                }
                return false;
            }
        }else{//case that pawn is black, repeat same rules as for white
            if(old_row == 1){
                if(Math.abs(new_col - old_col) == 1 && new_row == (old_row +1) && b.hasPiece(new_row, new_col)){
                    //System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }else if(new_col == old_col && new_row == (old_row +1) && !b.hasPiece(new_row, new_col)){
                    //System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }else if(new_col == old_col && new_row == (old_row + 2) && !b.hasPiece(new_row, new_col)){
                    //System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }
                return false;
            }else{//case pawn is nt in original position
                if(Math.abs(new_col - old_col) == 1 && new_row == old_row +1 && b.hasPiece(new_row, new_col)){
                    //System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }else if(new_col == old_col && new_row == old_row +1 && !b.hasPiece(new_row, new_col)){
                    //System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }
                return false;
            }
        }
    }
    public String getUni() {
        if (this.getColor().equals("w")) {
            return "♙";
        } else return "♟";
    }

    public void calculateAttackAndDefense(Board b) {
        defenceValue = 0;
        attackValue = 0;

        if(x+1 < 8 && y+1 < 8 && b.hasPiece(x+1,y+1)){
            Piece piece = b.getCell(x+1,y+1);
            if(piece.getColor().equals(color)){
                defenceValue += this.getActionValue()-piece.getActionValue();
            }
            else{
                attackValue += piece.getActionValue()-this.getActionValue();
            }
        }

        if(x-1 >= 0 && y+1 < 8 && b.hasPiece(x-1,y+1)){
            Piece piece = b.getCell(x-1,y+1);
            if(piece.getColor().equals(color)){
                defenceValue += this.getActionValue()-piece.getActionValue();
            }
            else{
                attackValue += piece.getActionValue()-this.getActionValue();
            }
        }
    }

    public String getType() {
        return "Pawn";
    }

}