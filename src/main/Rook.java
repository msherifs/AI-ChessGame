package main;

public class Rook extends Piece{
	public Rook (int x, int y,String name, String color)
	{
		super(x,y,name,color, 500);
	}
	public boolean checkMove(int x,int y, Board b)
	{

		if(b.getCell(x,y).getColor().equals(this.getColor()))
			return false;

		int old_x = super.getX();
		int old_y = super.getY();
		int new_x = x;
		int new_y = y;

		if(b.hasPiece(new_x,new_y)) {
			if(b.getCell(new_x,new_y).getColor().equals(super.getColor())){
				return false;

			}
		}

		// check that the rook moves on either x axis or y axis(on a straight line) once or more step.
		if((old_x== new_x) && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7))
		{
			if(new_y>old_y){
				for (int i = 1; i < new_y - old_y; i++) {
					if (b.hasPiece(old_x, old_y + i)) {
						return false;
					}
				}
				return true;
			}
			if(old_y>new_y)
			{
				for (int i = 1; i < old_y - new_y; i++) {
					if (b.hasPiece(old_x, new_y + i)) {
						return false;
					}
				}
				return true;
			}
			if (new_y == old_y) {
				return true;
			}
			return true;

		}
		if ((old_y == new_y) && (new_x >= 0 && new_x <= 7) && (new_y >= 0 && new_y <= 7))
		{
			if(old_x<new_x)
			{
				for(int i=1;i<new_x-old_x;i++)
				{
					if(b.hasPiece(old_x+i,old_y))
						return false;
				}
				return true;
			}
			if (old_x>new_x)
			{
				for(int i=1;i<old_x-new_x;i++)
				{
					if(b.hasPiece(new_x+i,old_y))
						return false;
				}
				return true;

			}
			else if( old_x == new_x)
			{
				return true;
			}
		}

		return false;
	}
	public String getType() {

		return "Rook";
	}
	public String getUni() {
		if (this.getColor().equals("w")) {
			return "♖ ROOK";
		} else return "♜ ROOK";


	}


}



