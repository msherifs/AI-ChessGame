package main;

public class Pawn extends Piece {

    public Pawn(int x, int y, String name, String color) {
        super(x, y, name, color, 100);
    }

    public boolean checkMove(int x, int y, Board b) {

        if (b.getCell(x, y).getColor().equals(this.getColor()))
            return false;

        int old_x = super.getX();
        int old_y = super.getY();
        int new_x = x;
        int new_y = y;

        if ((new_x == old_x) && (new_y == old_y))
            return false;
//
//        if ((old_x == new_x) && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
//            if (old_y > new_y) {
//                if (b.hasPiece(old_x, new_y + 1) && !b.getCell(new_x, new_y).getColor().equals(b.getCell(old_x, old_y).getColor())) {
//                    return true;
//                } else if (b.hasPiece(old_x, new_y + 1)) {
//                    return false;
//                } else {
//                    return true;
//                }
//            } else if (old_y < new_y) {
//                if (b.hasPiece(old_x, old_y + 1) && !b.getCell(new_x, new_y).getColor().equals(b.getCell(old_x, old_y).getColor())) {
//                    return true;
//                } else if (b.hasPiece(old_x, old_y + 1)) {
//                    return false;
//                } else {
//                    return true;
//                }
//            } else if (new_y == old_y) {
//                return true;
//            }
//            return true;
//
//        }
//        return true;
        if(super.getColor().equals("w")){ //case that pawn is white
            if(old_y == 6){  //case that pawn is in original position
                //allows pawn to move two spaces ahead as long as there is no space in between
                if(Math.abs(new_x - old_x) == 1 && new_y == old_y -1 && b.hasPiece(new_x, new_y)){
                    return true;
                }else if(new_x == old_x && new_y == old_y -1 && !b.hasPiece(new_x, new_y)){
                    return true;
                }else if(new_x == old_x && new_y == old_y - 2 && !b.hasPiece(new_x, new_y)){
                    return true;
                }
                return false;
            }else{//case pawn is nt in original position
                //check if pawn is capturing another piece
                if(Math.abs(new_x - old_x) == 1 && new_y == old_y -1 && b.hasPiece(new_x, new_y)){
                    return true;
                    //check that pawn is moving forward one square
                }else if(new_x == old_x && new_y == old_y -1 && !b.hasPiece(new_x, new_y)){
                    return true;
                }
                return false;
            }
        }else{//case that pawn is black, repeat same rules as for white
            if(old_y == 1){
                if(Math.abs(new_x - old_x) == 1 && new_y == old_y +1 && b.hasPiece(new_x, new_y)){
                    System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }else if(new_x == old_x && new_y == old_y +1 && !b.hasPiece(new_x, new_y)){
                    System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }else if(new_x == old_x && new_y == old_y + 2 && !b.hasPiece(new_x, new_y)){
                    System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }
                return false;
            }else{//case pawn is nt in original position
                if(Math.abs(new_x - old_x) == 1 && new_y == old_y +1 && b.hasPiece(new_x, new_y)){
                    System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }else if(new_x == old_x && new_y == old_y +1 && !b.hasPiece(new_x, new_y)){
                    System.out.println("Legal move: " + getType()  + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

                    return true;
                }
                return false;
            }
        }
    }
    public String getUni() {
        if (this.getColor().equals("w")) {
            return "♙ PAWN ";
        } else return "♟ PAWN ";


    }

    public String getType() {
        return "Pawn";
    }

}