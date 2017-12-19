package main;
public class Knight extends Piece {

    public Knight(int x, int y, String name , String color) {
        super(x, y, name , color, 320);
    }

    public boolean checkMove(int x, int y, Board b) {


        if(b.getCell(x,y).getColor().equals(this.getColor()))
            return false;

        int old_x =  super.getX();
        int old_y =  super.getY();
        int new_x =  x;
        int new_y =  y;

        if (b.hasPiece(new_x, new_y)) {
            if (b.getCell(new_x, new_y).getColor().equals(super.getColor())) {
                return false;
            }
        }
        //checks that the knight moves 2 on x and 1 on y or vise versa (1,2) or (2,1)

        if (Math.abs(new_x - old_x) == 2 && Math.abs(new_y - old_y) == 1 && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
            //System.out.println("Allowed move: " + getType() + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

            return true;
        } else if (Math.abs(new_x - old_x) == 1 && Math.abs(new_y - old_y) == 2 && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
            //System.out.println("Allowed move: " + getType() + " from: (" + old_x + "," + old_y + ") to (" + new_x + "," + new_y + ")");

            return true;
        } else {
            return false;
        }
    }

    public String getType() {

        return "Knight";
    }
}
