package main;

import java.util.ArrayList;

public class AIEngine {
	/*
	public int minMax(int depth, int alpha, int beta, Board board, String turn){
		if (depth == 0){
			int estimate = evalutate(board);
			return estimate;
		}

		if (turn.equals("w")) {
			ArrayList<Move> moves = new ArrayList<Move>();

			for(int i = 0; i<8; i++){
				for(int j=0; j<8; j++){
					if(board.hasPiece(i,j)){
						if(board.getCell(i,j).getColor().equals(turn)){
							Piece piece = board.getSquare(i,j);
							for(int k =0; k<8; k++){
								for(int l=0; l<8; l++){
									if(piece.checkLegalMove(k, l, board)){
										moves.add(new Move(i,j,k,l,piece)); //adds moves to the arraylist as they are calculated
									}
								}
							}
						}
					}
				}
			}
			int nBeta = beta;
			for (Move mv : moves) {
				Board nextBoard = new Board(board);
				//domove
				nBeta = Math.min(nBeta, minMax(depth - 1, alpha, beta, nextBoard, "b"));
				if (nBeta <= alpha) break;
			} 
		} else {
			ArrayList<Move> moves = new ArrayList<Move>();

			for(int i = 0; i<8; i++){
				for(int j=0; j<8; j++){
					if(board.hasPiece(i,j)){
						if(board.getCell(i,j).getColor().equals(turn)){
							Piece piece = board.getSquare(i,j);
							for(int k =0; k<8; k++){
								for(int l=0; l<8; l++){
									if(piece.checkLegalMove(k, l, board)){
										moves.add(new Move(i,j,k,l,piece)); //adds moves to the arraylist as they are calculated
									}
								}
							}
						}
					}
				}
			}
			int nAlpha = alpha;
			for (Move mv : moves) {
				Board nextBoard = new Board(board);
				//domove
				nAlpha = Math.max(nAlpha, minMax(depth - 1, alpha, beta, nextBoard, "w"));
				if (nAlpha <= alpha) break;
			} 
		}
	}*/

	public int minMax(int depth, int alpha, int beta, Board board, String turn){
		if (depth == 0){
			int estimate = evalutate(board);
			return estimate;
		}

		ArrayList<Move> moves = new ArrayList<Move>();

		for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
				if(board.hasPiece(i,j)){
					if(board.getCell(i,j).getColor().equals(turn)){
						Piece piece = board.getSquare(i,j);
						for(int k =0; k<8; k++){
							for(int l=0; l<8; l++){
								if(piece.checkMove(k, l, board)){
									moves.add(new Move(i,j,k,l,piece)); //adds moves to the arraylist as they are calculated
								}
							}
						}
					}
				}
			}
		}
		int temp = turn.equals("w") ? beta : alpha;
		String nTurn = turn.equals("b") ? "b" : "w";

		for (Move mv : moves) {
			Board nextBoard = new Board(board);
			//domove
			if (turn.equals("w"))
				temp = Math.min(temp, minMax(depth - 1, alpha, beta, nextBoard, nTurn));
			else
				temp = Math.max(temp, minMax(depth - 1, alpha, beta, nextBoard, nTurn));

			if (temp <= alpha) break;
		}
		return temp;
	}
}