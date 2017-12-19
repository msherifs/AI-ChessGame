package main;
public class Pawn extends Piece {
	
	public Pawn (int x, int y,String name, String color)
	{
		super(x,y,name,color);
	}
	public boolean checkMove(int x, int y , Board b) {
        int old_x =  super.getX();
        int old_y =  super.getY();
        int new_x =  x;
        int new_y =  y;
	
		if((old_x== new_x) && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7)) {
				if(old_y>new_y){
					if (b.hasPiece(old_x, new_y + 1) &&  !b.getCell(new_x,new_y).getColor().equals(b.getCell(old_x,old_y).getColor())) {
						return true;
					}
					else if (b.hasPiece(old_x, new_y + 1)) {
						return false;
					}else {
						return true;
					}
				}else if(old_y<new_y) {
					if (b.hasPiece(old_x, old_y + 1) && !b.getCell(new_x,new_y).getColor().equals(b.getCell(old_x,old_y).getColor())) {
						return true;
					}else if (b.hasPiece(old_x, old_y + 1)) {
						return false;
					}else {
					return true;
					}
				}
				else if (new_y == old_y) {
					return true;
				}
				return true;

		}
		return true;
	}

	 public String getType() {
        return "Pawn";
    }
	
}