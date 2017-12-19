import java.util.*;

public class Board {

private Piece[][] board ;

public Board(){

  //initializes the array with nulls , null postion mean that the  board is empty in this postion
  this.board = new Piece[8][8];
  for(int i = 0; i<8; i++){
    for(int j = 0; j<8; j++){
      board[i][j] = null;
    }
  }

 //black pieces
  setCell(0,4,new King(0,4,'k'));
  setCell(0,0,new Rook(0,0,'r'));
  setCell(0,1,new Knight(0,1,'n'));
  setCell(0,2,new Bishop(0,2,'b'));
  setCell(0,3,new Queen(0,3,'q'));
  setCell(0,5,new Bishop(0,5,'b'));
  setCell(0,6,new Knight(0,6,'n'));
  setCell(0,7, new Rook(0,7,'r'));
  for(int i = 0; i<8; i++){
    setCell(1,i,new Pawn(1,i,'p'));
  }

  //white pieces
  setCell(7,4,new King(7,4,'K'));
  setCell(7,0,new Rook(7,0,'R'));
  setCell(7,1,new Knight(7,1,'N'));
  setCell(7,2,new Bishop(7,2,'B'));
  setCell(7,3,new Queen(7,3,'Q'));
  setCell(7,5, new Bishop(7,5,'B'));
  setCell(7,6, new Knight(7,6,'N'));
  setCell(7,7,new Rook(7,7,'R'));
  for(int i=0;i<8;i++){
    setCell(6,i, new Pawn(6,i,'P'));
  }


}

// cloning a board
public Board(Board b){

  this.board = new Piece[8][8];

  for(int i = 0; i<8; i++){
    for(int j = 0; j<8; j++){
      this.board[i][j] = null;
    }
}

    for(int i = 0; i<8; i++){
      for(int j = 0; j<8; j++){
    if (b.hasPiece(i,j))
    {
      switch(b.getCell(i,j).getChar()){

        case 'k': this.board[i][j] = new King (i,j,'k');  break;
        case 'r': this.board[i][j] = new Rook (i,j,'r');  break;
        case 'n': this.board[i][j] = new Knight (i,j,'n');  break;
        case 'b': this.board[i][j] = new Bishop (i,j,'b');  break;
        case 'q': this.board[i][j] = new Queen (i,j,'q');  break;
        case 'p': this.board[i][j] = new Pawn (i,j,'p');  break;

        case 'K': this.board[i][j] = new King (i,j,'K');  break;
        case 'R': this.board[i][j] = new Rook (i,j,'R');  break;
        case 'N': this.board[i][j] = new Knight (i,j,'N');  break;
        case 'B': this.board[i][j] = new Bishop (i,j,'B');  break;
        case 'Q': this.board[i][j] = new Queen (i,j,'Q');  break;
        case 'P': this.board[i][j] = new Pawn (i,j,'P');  break;

      } }}}
}

public Piece getCell(int row, int col){
  return this.board[row][col];
}


public void setCell(int row, int col, Piece piece){
  this.board[row][col] = piece;
}

public void clearCell(int row, int col){
  this.board[row][col] = null;
}

public boolean hasPiece(int row, int col){
  if(getCell(row,col) != null){
    return true;
  }else{
    return false;
  }
}






}
