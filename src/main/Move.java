// NOTE THAT this class represents the hypothetical moves that the AI makes to determine which move is better ,  not the actual moves on the board

// to make an actual move you HAVE TO change the ( x and y ) both on the board object (setCell) and the piece object it self (setX) .
package main;


public class Move {

  private int oldX;
  private int oldY;
  private int newX;
  private int newY;
  private Piece p;

 public Move(int oldX,int oldY,int newX, int newY,Piece p) {
     this.oldX = oldX;
     this.oldY = oldY;
     this.newX = newX;
     this.newY = newY;


     //cloning the piece to make a hypothetical move
     switch (p.getName()) {

         case "k":
             this.p = new King(p.getX(), p.getY(), "k", "w");
             break;
         case "r":
             this.p = new Rook(p.getX(), p.getY(), "r", "w");
             break;
         case "n":
             this.p = new Knight(p.getX(), p.getY(), "n", "w");
             break;
         case "b":
             this.p = new Bishop(p.getX(), p.getY(), "b", "w");
             break;
         case "q":
             this.p = new Queen(p.getX(), p.getY(), "q", "w");
             break;
         case "p":
             this.p = new Pawn(p.getX(), p.getY(), "p", "w");
             break;

         case "K":
             this.p = new King(p.getX(), p.getY(), "K", "b");
             break;
         case "R":
             this.p = new Rook(p.getX(), p.getY(), "R", "b");
             break;
         case "N":
             this.p = new Knight(p.getX(), p.getY(), "N", "b");
             break;
         case "B":
             this.p = new Bishop(p.getX(), p.getY(), "B", "b");
             break;
         case "Q":
             this.p = new Queen(p.getX(), p.getY(), "Q", "b");
             break;
         case "P":
             this.p = new Pawn(p.getX(), p.getY(), "P", "b");
             break;

     }
 }

      public int getOldX(){
    		return this.oldX;
    	}


    	public int getOldY(){
    		return this.oldY;
    	}


    	public int getNewX(){
    		return this.newX;
    	}


    	public int getNewY(){
    		return this.newY;
    	}


    	public Piece getPiece(){
    		return this.p;
    	}


    	public String toString(){
    		String text = this.p.getType() + " hypothetically moved from (" + oldX + "," + oldY + ") to (" + newX + "," + newY + ")";
    		return text;
    	}

 }














