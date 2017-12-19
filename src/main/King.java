package main;
public class King extends Piece {

    public King(int x, int y, String name , String color) {
        super(x, y, name , color, 32767);
    }

    public boolean checkMove(int x, int y, Board b) {

        if(b.getCell(x,y).getColor().equals(this.getColor()))
            return false;

        int old_x = super.getX();
        int old_y = super.getY();
        int new_x = x;
        int new_y = y;

        if (b.hasPiece(new_x, new_y)) {
            if (b.getCell(new_x, new_y).getColor() == super.getColor()) {
                return false;
            }
        }
        if (Math.abs(new_x - old_x) <= 1 && Math.abs(new_y - old_y) <= 1 && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
            System.out.println("Allowed move: " + getType() + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");
            return true;
        }
        if (super.getColor() == "K") { //white
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

        } else if (super.getColor() == "k") { //black
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

    public String getType() {
        return "King";
    }

}
