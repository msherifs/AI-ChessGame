package main;

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
  setCell(0,4,new King(0,4,"k","b"));
  setCell(0,0,new Rook(0,0,"r","b"));
  setCell(0,1,new Knight(0,1,"n","b"));
  setCell(0,2,new Bishop(0,2,"b","b"));
  setCell(0,3,new Queen(0,3,"q","b"));
  setCell(0,5,new Bishop(0,5,"b","b"));
  setCell(0,6,new Knight(0,6,"n","b"));
  setCell(0,7, new Rook(0,7,"r","b"));
  for(int i = 0; i<8; i++){
    setCell(1,i,new Pawn(1,i,"p","b"));
  }

  //white pieces
  setCell(7,4,new King(7,4,"K","w"));
  setCell(7,0,new Rook(7,0,"R","w"));
  setCell(7,1,new Knight(7,1,"N","w"));
  setCell(7,2,new Bishop(7,2,"B","w"));
  setCell(7,3,new Queen(7,3,"Q","w"));
  setCell(7,5, new Bishop(7,5,"B","w"));
  setCell(7,6, new Knight(7,6,"N","w"));
  setCell(7,7,new Rook(7,7,"R","w"));
  for(int i=0;i<8;i++){
    setCell(6,i, new Pawn(6,i,"P","w"));
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

        case 'k': this.board[i][j] = new King (i,j,"k","b");  break;
        case 'r': this.board[i][j] = new Rook (i,j,"r","b");  break;
        case 'n': this.board[i][j] = new Knight (i,j,"n","b");  break;
        case 'b': this.board[i][j] = new Bishop (i,j,"b","b");  break;
        case 'q': this.board[i][j] = new Queen (i,j,"q","b");  break;
        case 'p': this.board[i][j] = new Pawn (i,j,"p","b");  break;

        case 'K': this.board[i][j] = new King (i,j,"K","w");  break;
        case 'R': this.board[i][j] = new Rook (i,j,"R","w");  break;
        case 'N': this.board[i][j] = new Knight (i,j,"N","w");  break;
        case 'B': this.board[i][j] = new Bishop (i,j,"B","w");  break;
        case 'Q': this.board[i][j] = new Queen (i,j,"Q","w");  break;
        case 'P': this.board[i][j] = new Pawn (i,j,"P","w");  break;

      } }}}
}

public Piece getCell(int row, int col){
  return this.board[row][col];
}
    
    
    @Override
    public String toString(){
        String a =
        "  +0-1-2-3-4-5-6-7+\n" +
        "0 |"+this.board[0][0]+"|"+this.board[0][1]+"|"+this.board[0][2]+"|"+this.board[0][3]+"|"+this.board[0][4]+"|"+ this.board[0][5] +"|"+this.board[0][6]+"|"+this.board[0][7]+"| 0\n"+
        "1 |"+this.board[1][0]+"|"+this.board[1][1]+"|"+this.board[1][2]+"|"+this.board[1][3]+"|"+this.board[1][4]+"|"+ this.board[1][5] +"|"+this.board[1][6]+"|"+this.board[1][7]+"| 1\n"+
        "2 |"+this.board[2][0]+"|"+this.board[2][1]+"|"+this.board[2][2]+"|"+this.board[2][3]+"|"+this.board[2][4]+"|"+ this.board[2][5] +"|"+this.board[2][6]+"|"+this.board[0][0]+"| 2\n"+
        "3 |"+this.board[3][0]+"|"+this.board[3][1]+"|"+this.board[3][2]+"|"+this.board[3][3]+"|"+this.board[3][4]+"|"+ this.board[3][5] +"|"+this.board[3][6]+"|"+this.board[0][0]+"| 3\n"+
        "4 |"+this.board[4][0]+"|"+this.board[4][1]+"|"+this.board[4][2]+"|"+this.board[4][3]+"|"+this.board[4][4]+"|"+ this.board[4][5] +"|"+this.board[4][6]+"|"+this.board[0][0]+"| 4\n"+
        "5 |"+this.board[5][0]+"|"+this.board[5][1]+"|"+this.board[5][2]+"|"+this.board[5][3]+"|"+this.board[5][4]+"|"+ this.board[5][5] +"|"+this.board[5][6]+"|"+this.board[0][0]+"| 5\n"+
        "6 |"+this.board[6][0]+"|"+this.board[6][1]+"|"+this.board[6][2]+"|"+this.board[6][3]+"|"+this.board[0][4]+"|"+ this.board[6][5] +"|"+this.board[6][6]+"|"+this.board[0][0]+"| 6\n"+
        "7 |"+this.board[7][0]+"|"+this.board[7][1]+"|"+this.board[7][2]+"|"+this.board[7][3]+"|"+this.board[0][4]+"|"+ this.board[7][5] +"|"+this.board[7][6]+"|"+this.board[0][0]+"| 7\n"+
        "  +0-1-2-3-4-5-6-7+\n";
        
        return  a ;
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
