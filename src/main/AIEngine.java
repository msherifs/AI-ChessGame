package main;

import java.util.ArrayList;
import main.GameEngine;

public class AIEngine {

	private static int[][] pawnTable = new int[][]
			{
					{0, 0, 0, 0, 0, 0, 0, 0},
					{50, 50, 50, 50, 50, 50, 50, 50},
					{10, 10, 20, 30, 30, 20, 10, 10},
					{5, 5, 10, 27, 27, 10, 5, 5},
					{0, 0, 0, 25, 25, 0, 0, 0},
					{5, -5, -10, 0, 0, -10, -5, 5},
					{5, 10, 10, -25, -25, 10, 10, 5},
					{0, 0, 0, 0, 0, 0, 0, 0}
			};

	private static int[][] knightTable = new int[][]
			{
					{-50, -40, -30, -30, -30, -30, -40, -50},
					{-40, -20, 0, 0, 0, 0, -20, -40},
					{-30, 0, 10, 15, 15, 10, 0, -30},
					{-30, 5, 15, 20, 20, 15, 5, -30},
					{-30, 0, 15, 20, 20, 15, 0, -30},
					{-30, 5, 10, 15, 15, 10, 5, -30},
					{-40, -20, 0, 5, 5, 0, -20, -40},
					{-50, -40, -20, -30, -30, -20, -40, -50}
			};

	private static int[][] bishopTable = new int[][]
			{
					{-20, -10, -10, -10, -10, -10, -10, -20},
					{-10, 0, 0, 0, 0, 0, 0, -10},
					{-10, 0, 5, 10, 10, 5, 0, -10},
					{-10, 5, 5, 10, 10, 5, 5, -10},
					{-10, 0, 10, 10, 10, 10, 0, -10},
					{-10, 10, 10, 10, 10, 10, 10, -10},
					{-10, 5, 0, 0, 0, 0, 5, -10},
					{-20, -10, -40, -10, -10, -40, -10, -20}
			};

	private static int[][] kingTable = new int[][]
			{
					{-30, -40, -40, -50, -50, -40, -40, -30},
					{-30, -40, -40, -50, -50, -40, -40, -30},
					{-30, -40, -40, -50, -50, -40, -40, -30},
					{-30, -40, -40, -50, -50, -40, -40, -30},
					{-20, -30, -30, -40, -40, -30, -30, -20},
					{-10, -20, -20, -20, -20, -20, -20, -10},
					{20, 20, 0, 0, 0, 0, 20, 20},
					{20, 30, 10, 0, 0, 10, 30, 20}
			};

	private static int[][] kingTableEndGame = new int[][]
			{
					{-50, -40, -30, -20, -20, -30, -40, -50},
					{-30, -20, -10, 0, 0, -10, -20, -30},
					{-30, -10, 20, 30, 30, 20, -10, -30},
					{-30, -10, 30, 40, 40, 30, -10, -30},
					{-30, -10, 30, 40, 40, 30, -10, -30},
					{-30, -10, 20, 30, 30, 20, -10, -30},
					{-30, -30, 0, 0, 0, 0, -30, -30},
					{-50, -30, -30, -30, -30, -30, -30, -50}
			};

//	/*
//	public int minMax(int depth, int alpha, int beta, Board board, String turn){
//		if (depth == 0){
//			int estimate = evalutate(board);
//			return estimate;
//		}
//
//		if (turn.equals("w")) {
//			ArrayList<Move> moves = new ArrayList<Move>();
//
//			for(int i = 0; i<8; i++){
//				for(int j=0; j<8; j++){
//					if(board.hasPiece(i,j)){
//						if(board.getCell(i,j).getColor().equals(turn)){
//							Piece piece = board.getCell(i,j);
//							for(int k =0; k<8; k++){
//								for(int l=0; l<8; l++){
//									if(piece.checkLegalMove(k, l, board)){
//										moves.add(new Move(i,j,k,l,piece)); //adds moves to the arraylist as they are calculated
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//			int nBeta = beta;
//			for (Move mv : moves) {
//				Board nextBoard = new Board(board);
//				//domove
//				nBeta = Math.min(nBeta, minMax(depth - 1, alpha, beta, nextBoard, "b"));
//				if (nBeta <= alpha) break;
//			}
//		} else {
//			ArrayList<Move> moves = new ArrayList<Move>();
//
//			for(int i = 0; i<8; i++){
//				for(int j=0; j<8; j++){
//					if(board.hasPiece(i,j)){
//						if(board.getCell(i,j).getColor().equals(turn)){
//							Piece piece = board.getCell(i,j);
//							for(int k =0; k<8; k++){
//								for(int l=0; l<8; l++){
//									if(piece.checkLegalMove(k, l, board)){
//										moves.add(new Move(i,j,k,l,piece)); //adds moves to the arraylist as they are calculated
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//			int nAlpha = alpha;
//			for (Move mv : moves) {
//				Board nextBoard = new Board(board);
//				//domove
//				nAlpha = Math.max(nAlpha, minMax(depth - 1, alpha, beta, nextBoard, "w"));
//				if (nAlpha <= alpha) break;
//			}
//		}
//	}*/
//
//	public BestMove minMax(int depth, BestMove alpha, BestMove beta, Board board, String turn){
//		if (depth == 0){
//			int estimate = evaluate(board);
//			alpha.setValue(estimate);
//			return alpha;
//		}
//
//		ArrayList<Move> moves = new ArrayList<Move>();
//
//		for(int i = 0; i<8; i++){
//			for(int j=0; j<8; j++){
//				if(board.hasPiece(i,j)){
//					if(board.getCell(i,j).getColor().equals(turn)){
//						Piece piece = board.getCell(i,j);
//						for(int k =0; k<8; k++){
//							for(int l=0; l<8; l++){
//								if(piece.checkMove(k, l, board)){
//									Move mmv = new Move(i,j,k,l,piece);
//									//System.out.println(mmv);
//									moves.add(mmv); //adds moves to the arraylist as they are calculated
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//
//		BestMove temp = turn.equals("w") ? beta : alpha;
//		String nTurn = turn.equals("b") ? "w" : "b";
//
//		for (Move mv : moves) {
//			Board nextBoard = new Board(board);
//			temp.setMove(mv);
//			//domove
//			GameEngine.makeActualMove(mv.getOldX(), mv.getOldY(), mv.getNewX(), mv.getNewY(), nextBoard);
//			if (turn.equals("w"))
//				temp.setValue(Math.min(temp.getValue(), minMax(depth - 1, alpha, beta, nextBoard, nTurn).getValue()));
//			else
//				temp.setValue(Math.max(temp.getValue(), minMax(depth - 1, alpha, beta, nextBoard, nTurn).getValue()));
//
//			if (turn.equals("b")){
//				if (beta.getValue() <= temp.getValue()) break;
//			} else {
//				if (temp.getValue() <= alpha.getValue()) break;
//			}
//		}
////		System.out.println(temp.getMove());
////		Board nextBoard = new Board(board);
////		temp.setMove(temp.getMove());
////		//domove
////		//GameEngine.makeActualMove(temp.getMove().getOldX(), temp.getMove().getOldY(), temp.getMove().getNewX(), temp.getMove().getNewY(), nextBoard);
////		System.out.println(nextBoard);
//		return temp;
//	}
//
//	/*public int evaluate(Board b) {
//		int whiteScore = 0;
//		int blackScore = 0;
//
//		for (int i = 0; i < 8; i++) {
//			for (int j = 0; j < 8; j++) {
//				if (b.hasPiece(i, j)) {
//					//calculatePiece
//					Piece piece = b.getCell(i, j);
//					if (piece.getColor().equals("b"))
//						blackScore += calculatePiece(piece, i, j, false, false);
//					else
//						whiteScore += calculatePiece(piece, i, j, false, false);
//				}
//			}
//		}
//
//		return blackScore - whiteScore; //returns blackscore-whitescore, black player tries to maximize, white player tries to minimize
//	}*/
	
	public int evaluate(Board b){
		int whitescore = 0;
		int blackscore = 0;

		/*
		 * Iterates through entire board.   
		 */
		for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
				if(b.hasPiece(i, j)){
					if(b.getCell(i, j).getColor().equals("w")){ //case that piece is white
						if(b.getCell(i,j).getType() == "Queen"){
							whitescore += 9;
						}else if(b.getCell(i,j).getType() == "Rook"){
							whitescore += 5;
						}else if(b.getCell(i,j).getType() == "Knight" || b.getCell(i,j).getType() == "Bishop"){
							whitescore += 3;
						}else if(b.getCell(i,j).getType() == "Pawn"){
							whitescore += 1;
						}else if(b.getCell(i,j).getType() == "King"){
							whitescore += 10000000;
						}
					}else if(b.getCell(i,j).getColor().equals("b")){ //case that piece is black
						if(b.getCell(i,j).getType() == "Queen"){
							blackscore += 9;
						}else if(b.getCell(i,j).getType() == "Rook"){
							blackscore += 5;
						}else if(b.getCell(i,j).getType() == "Knight" || b.getCell(i,j).getType() == "Bishop"){
							blackscore += 3;
						}else if(b.getCell(i,j).getType() == "Pawn"){
							blackscore += 1;
						}else if(b.getCell(i,j).getType() == "King"){
							blackscore += 10000000;
						}
					}
				}
			}
		}
		return blackscore-whitescore; //returns blackscore-whitescore, black player tries to maximize, white player tries to minimize
	}

	public int calculatePiece(Piece piece, int row, int col, boolean castled, boolean endGame) {

		int x = (piece.getColor().equals("b")) ? row : col;
		int y = (piece.getColor().equals("b")) ? col : row;

		int score = 0;

		score += piece.getValue(); //TODO: Add getValue to each piece

		if (piece.getType() == "Knight") {
			if (endGame) {
				score -= 10;
			}
			score += knightTable[x][y];
		} else if (piece.getType() == "Bishop") {
			if (endGame) {
				score += 10;
			}
			score += bishopTable[x][y];
		} else if (piece.getType() == "Pawn") {
			if ((x * y) % 8 == 0 || (x * y) % 8 == 7) { //rook pawns
				score -= 15;
			}
			score += pawnTable[x][y];
		} else if (piece.getType() == "King") {
			if (endGame) {
				score += kingTableEndGame[x][y];
			} else {
				score += kingTable[x][y];
			}
		}

		return score;
	}

//	public void makeAiMove(Board board){
//		// TODO Auto-generated method stub
//		//generate all legal moves
//		Move bestMove; //keeps track of the best possible move AI has available
//		BestMove bestMoveScore; //score of that best move
//
//		ArrayList<Board> possibleBoards = new ArrayList<Board>(); //keeps track of the possible boards (boards with the possible moves made on them)
//		ArrayList<Move> moves  = new ArrayList<Move>(); //keeps track of all possible moves
//
//		/*
//		 * iterates through board, generates all possible moves and saves them in moves
//		 */
//		for(int i = 0; i<8; i++){
//			for(int j=0; j<8; j++){
//				if(board.hasPiece(i,j)){
//					Piece piece = board.getCell(i,j);
//					if(piece.getColor().equals("b")){
//						for(int k=0; k<8; k++){
//							for(int l=0; l<8; l++){
//								if(piece.checkMove(k,l, board)){ //k and l and multiplied by 62 because checkLegalMove takes the pixel positions as parameters
//									Move move = new Move(i,j,k,l,piece);
//									moves.add(move);
//									Board newBoard = new Board(board); //calls the copy constructor of the board clas
//									GameEngine.makeActualMove(move.getOldX(),move.getOldY(), move.getNewX(), move.getNewY(), newBoard); //performs move on the new board
//									possibleBoards.add(newBoard);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		//initializes bestMove to the first move in the
//		bestMove = moves.get(0);
//		bestMoveScore = minMax(1, new BestMove(Integer.MIN_VALUE), new BestMove(Integer.MAX_VALUE), possibleBoards.get(0), "w");
//
//		//call evaluateposition on each move
//		//keep track of the move with the best score
//		for(int i = 1; i<possibleBoards.size(); i++){
//			System.out.println("Evaluating move: " + moves.get(i).toString());
//				/*
//				 * calls evaluatePosition on each possible board and if the score is higher than previous,
//				 * reset the bestMove
//				 */
//			BestMove j = minMax(1, new BestMove(Integer.MIN_VALUE), new BestMove(Integer.MAX_VALUE), possibleBoards.get(i), "w");
//			if(j.getValue() >= bestMoveScore.getValue()){
//				bestMove = moves.get(i);
//				bestMoveScore.setValue(j.getValue());
//			}
//
//		}
//		GameEngine.makeActualMove(bestMove.getOldX(), bestMove.getOldY(), bestMove.getNewX(), bestMove.getNewY(), board);
//		//return true; //doMove performs the move on the original board and returns a string of that move
//
//
//	}


	public int minMax(int depth, int alpha, int beta, Board b, String turn){
		if (depth == 0){
			return evaluate(b);
		}

		ArrayList<Move> moves = new ArrayList<Move>();

		for(int i = 0; i<8; i++){
			for(int j=0; j<8; j++){
				if(b.hasPiece(i,j)){
					if(b.getCell(i,j).getColor().equals(turn)){
						Piece piece = b.getCell(i,j);
						for(int k =0; k<8; k++){
							for(int l=0; l<8; l++){
								if(piece.checkMove(k, l, b)){
									Move mmv = new Move(i,j,k,l,piece);
									//System.out.println(mmv);
									moves.add(mmv); //adds moves to the arraylist as they are calculated
								}
							}
						}
					}
				}
			}
		}

		if (turn.equals("b")){
			int bestMove = Integer.MIN_VALUE;
			int bestMoveIndex = -1;
			int stepEval;
			int v = Integer.MIN_VALUE;
			for (int i = 0 ; i < moves.size() ; i++) {
				Board newBoard = new Board(b);
				GameEngine.makeActualMove(moves.get(i).getOldX(), moves.get(i).getOldY(), moves.get(i).getNewX(), moves.get(i).getNewY(), newBoard);
				stepEval = minMax(depth - 1, alpha, beta, newBoard, "w");
				v = Math.max(v, stepEval);
				alpha = Math.max(v, alpha);
				if (stepEval > bestMove){
					bestMoveIndex = i;
					bestMove = stepEval;
				}
				if (beta <= alpha){
					break;
				}
			}
			GameEngine.makeActualMove(moves.get(bestMoveIndex).getOldX(), moves.get(bestMoveIndex).getOldY(), moves.get(bestMoveIndex).getNewX(), moves.get(bestMoveIndex).getNewY(), b);
			return v;
		} else {
			int v = Integer.MAX_VALUE;
			int bestMove = Integer.MAX_VALUE;
			int bestMoveIndex = -1;
			int stepEval;
			for (int i = 0 ; i < moves.size() ; i++) {
				Board newBoard = new Board(b);
				GameEngine.makeActualMove(moves.get(i).getOldX(), moves.get(i).getOldY(), moves.get(i).getNewX(), moves.get(i).getNewY(), newBoard);
				stepEval = minMax(depth - 1, alpha, beta, newBoard, "b");
				v = Math.min(v, stepEval);
				beta = Math.min(v, beta);
				if (stepEval < bestMove){
					bestMoveIndex = i;
					bestMove = stepEval;
				}
				if (beta <= alpha){
					break;
				}
			}
			GameEngine.makeActualMove(moves.get(bestMoveIndex).getOldX(), moves.get(bestMoveIndex).getOldY(), moves.get(bestMoveIndex).getNewX(), moves.get(bestMoveIndex).getNewY(), b);
			return v;
		}
	}
}