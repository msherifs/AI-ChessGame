package main;

import java.util.ArrayList;

/**
 * HAL9000 Engine
 */

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

	int bf = 0;
	int cutoffs = 0;

	/**
	 * this function evaluates a board and returns a number that represents the state of the board in prespective of the AI,
	 * +INF for the Best Move the AI could Play, -INF for the Worst Move the AI Could Play (The Best for the Opponent)
	 *
	 * @param b The Board
	 * @return  Representation of the board
	 */

	private int evaluate(Board b) {
		int white = 0;
		int black = 0;

		boolean endGamePhase = b.endGamePhase();

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (b.hasPiece(i, j)) {
					//calculatePiece
					Piece piece = b.getCell(i, j);
					if (piece.getColor().equals("b"))
						black += calculatePiece(piece, i, j, endGamePhase);
					else
						white += calculatePiece(piece, i, j,  endGamePhase);
				}
			}
		}

		return black - white; //returns blackscore-whitescore, black player tries to maximize, white player tries to minimize
	}

	/**
	 * Helper function for the evaluate function, calculates a value for each piece
	 * @param piece   The piece
	 * @param row     row position of the piece
	 * @param col     col position of the piece
	 * @param endGame if the game is finished
	 * @return Value representation for the piece
	 */

	private int calculatePiece(Piece piece, int row, int col, boolean endGame) {

		int x = (piece.getColor().equals("b")) ? row : col;
		int y = (piece.getColor().equals("b")) ? col : row;

		int score = 0;

		score += piece.getValue(); //TODO: Add getValue to each piece

		score += piece.getDefenceValue();
		score -= piece.getAttackValue();

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

	/**
	 * this is the minimax algorithm function, it takes the following params to estimate the best move for the AI,
	 * it uses a utility function to get a evaluation for a current board, to determine if this state is a good move or bad
	 *
	 * @param depth max depth to search in
	 * @param alpha estimation of worst move that the AI could do
	 * @param beta  estimation of best  move that the AI could do
	 * @param b     The Root board state of the current depth
	 * @param turn  To identify if the player is  Maximizing or Minimizing; b=max, w=min
	 * @return      the alpha or beta of the current call
	 */

	public int minMax(int depth, int alpha, int beta, Board b, String turn){
		if (depth == 0){
//			System.out.println(b);
			int ev = evaluate(b);
//			System.out.println(ev);
//			System.out.println("<<<<<<<<<<<<EVALUATED BOARD>>>>>>>>>>>>");
//			System.out.println("<<<<<<<ALP: " + alpha + " BET: " + beta + " EVAL: " + ev +">>>>>>>");
			return ev;
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
				bf++;
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
//					System.out.println("<<<CUTOFF>>>,<<<MINIMIZING>>>\n@BETA: " + beta + " ALP: " + alpha + " DEPTH :" + depth);
					break;
				}
			}
//			System.out.println("<<<<<<< AVG Branching Factor is " + bf);
			bf = 0;
			GameEngine.makeActualMove(moves.get(bestMoveIndex).getOldX(), moves.get(bestMoveIndex).getOldY(), moves.get(bestMoveIndex).getNewX(), moves.get(bestMoveIndex).getNewY(), b);
			return v;
		} else {
			int v = Integer.MAX_VALUE;
			int bestMove = Integer.MAX_VALUE;
			int bestMoveIndex = -1;
			int stepEval;
			for (int i = 0 ; i < moves.size() ; i++) {
				bf++;
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
//					System.out.println("<<<CUTOFF>>>,<<<MAXIMIZING>>>\n@BETA: " + beta + " ALP: " + alpha + " DEPTH :" + depth);
					break;
				}
			}
//			System.out.println("<<<<<<< AVG Branching Factor is " + bf);
			bf = 0;
			GameEngine.makeActualMove(moves.get(bestMoveIndex).getOldX(), moves.get(bestMoveIndex).getOldY(), moves.get(bestMoveIndex).getNewX(), moves.get(bestMoveIndex).getNewY(), b);
			return v;
		}
	}
}