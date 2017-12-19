package main;

public class AIEngine {
	public int minMax(int depth, int alpha, int beta, Board board, String turn){
		if (depth == 0){
			int estimate // == estimate(board);
			return estimate;
		}

		if (turn.equals("w")) {
			ArrayList<Move> moves = new ArrayList<Move>();

			for(int i = 0; i<8; i++){
				for(int j=0; j<8; j++){
					if(b.hasPiece(i,j)){
						if(b.getCell(i,j).getColor().equals(turn)){
							Piece piece = b.getSquare(i,j);
							for(int k =0; k<8; k++){
								for(int l=0; l<8; l++){
									if(piece.checkLegalMove(k, l, b)){
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
				nBeta(Math.min(nBeta, minMax(depth - 1, alpha, beta, nextBoard, "b")));
				if (nBeta <= alpha) break;
			} 
		} else {
			ArrayList<Move> moves = new ArrayList<Move>();

			for(int i = 0; i<8; i++){
				for(int j=0; j<8; j++){
					if(b.hasPiece(i,j)){
						if(b.getCell(i,j).getColor().equals(turn)){
							Piece piece = b.getSquare(i,j);
							for(int k =0; k<8; k++){
								for(int l=0; l<8; l++){
									if(piece.checkLegalMove(k, l, b)){
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
				nBeta(Math.max(nBeta, minMax(depth - 1, alpha, beta, nextBoard, "w")));
				if (nBeta <= alpha) break;
			} 
		}
	}
}