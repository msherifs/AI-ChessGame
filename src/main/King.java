package main;

import java.io.Serializable;

public class King extends Piece implements Serializable{

    public King(){

    }

    public King(int x, int y, String name, String color) {
        super(x, y, name, color, 32767, 1);
    }

    public boolean checkMove(int x, int y, Board b) {


        if (b.getCell(x, y).getColor().equals(this.getColor()))
            return false;



        int old_x = super.getX();
        int old_y = super.getY();
        int new_x = x;
        int new_y = y;

        //making the king unable to move to a certain place  if any piece of the opposite color can move to that place
        for (int i =0 ; i<8 ; i++){
            for (int j =0 ; j<8 ; j++){
                if (b.getCell(i,j).getType().equals("King"))
                    continue; //avoiding stack overflow

                //if the piece is pawn we have to check after moving the king virtually
                if (b.getCell(i,j).getType().equals("Pawn")){
                    Board VB = new Board(b);
                    VB.setCell(x,y,this);
                    for (int k =0 ; k<8 ; k++){
                        for (int l =0 ; l<8 ; l++){
                            if (VB.getCell(i,j).checkMove(new_x,new_y,VB) && !(VB.getCell(i,j).getColor().equals(this.color))){
                                //System.out.println("king can't move to  "+" ( "+new_x+","+new_y+" ) ");
                                return false;

                            }

                        }}

                    }


                if (b.getCell(i,j).checkMove(new_x,new_y,b) && !(b.getCell(i,j).getColor().equals(this.color))){
                    //System.out.println("king can't move to  "+" ( "+new_x+","+new_y+" ) ");
                    return false;

                }
            }
        }

        if (b.hasPiece(new_x, new_y)) {
            if (b.getCell(new_x, new_y).getColor().equals( super.getColor())) {
                return false;
            }
        }
        if (Math.abs(new_x - old_x) <= 1 && Math.abs(new_y - old_y) <= 1 && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
            //System.out.println("Allowed move: " + getType() + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");
            return true;
        }
        if (super.getColor().equals( "K")) { //white
            //check for the rook in correct postion to apply castling where move two pieces at same time
            if (b.hasPiece(7, 7)) {
                if (b.getCell(7, 7).getType().equals("Rook")) {
                    if (old_x == 4 && old_y == 7 && new_x == 6 && new_y == 7) {
                        if (!b.hasPiece(5, 7) && !b.hasPiece(6, 7)) {
                            return true;
                        }
                    }
                }
            }

        } else if (super.getColor() .equals( "k")){ //black
            if (b.hasPiece(7, 0)) {
                if (b.getCell(7, 0).getType().equals("Rook")) {
                    if (old_x == 4 && old_y == 0 && new_x == 6 && new_y == 0) {
                        if (!b.hasPiece(5, 0) && !b.hasPiece(6, 0)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void calculateAttackAndDefense(Board b) {
        defenceValue = 0;
        attackValue = 0;

        int[] xPos = new int[]{this.getX() + 1, this.getX() - 1, this.getX(), this.getX(), this.getX() + 1, this.getX() + 1, this.getX() - 1, this.getX() - 1};
        int[] yPos = new int[]{this.getY(), this.getY(), this.getY() + 1, this.getY() - 1, this.getY() + 1, this.getY() - 1, this.getY() - 1, this.getY() + 1};

        for (int i = 0; i < 8; i++) {
            if(xPos[i]<0 || xPos[i]>7 || yPos[i]<0 || yPos[i]>7)
                continue;
            if (b.hasPiece(xPos[i], yPos[i])) { //if there's a piece in my way
                Piece piece = b.getCell(xPos[i],yPos[i]);
                if (piece.getColor().equals(this.getColor())) { //if this piece is an ally
                    this.defenceValue +=this.actionValue-piece.getActionValue();
                } else { //if this piece is a foe
                    this.attackValue +=piece.actionValue-this.actionValue;
                }
            }
        }
    }

    public String getType() {
        return "King";
    }

    public String getUni() {
        if (this.getColor().equals("w")) {
            return "♔";
        } else return "♚";

    }
}
