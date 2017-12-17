package main;

public class GameEngine {

    private GameState gameStatus = IN_GAME;
    private String playerName;
    private Board gameBoard;
    private Move playerMove;
    private AIEngine aiEngine;
    private Scanner sc = new Scanner(System.in);

    public ChessEngine(String playerName){
        this.playerName = playerName;
        gameBoard = new Board();
    }


    private void run(){
        while(gameStatus == IN_GAME){
            System.out.println(playerName + "'s Turn");
            playerMove = new Move(sc.nextLine());
            playerMove.makeMove(gameBoard);
            checkVictory();
            aiEngine.makeMove(gameBoard);
            checkVictory();
        }
    }

    private void checkVictory(){
        Piece piece;
        for (int i = 0; i < 8 ; i++){
            for (int j = 0; j < 8; j++) {
                if (board.hasPiece(i,j)){
                    Piece piece = board.getPiece(i,j);
                    if (piece.toString().equalsIgnoreCase("k")){
                        this.gameStatus = board.getColor() == 'b' ? HUMAN_WINNER : AI_WINNER;
                    }
                }   
            }
        }
    }

    private enum GameState {
        IN_GAME, AI_WINNER, HUMAN_WINNER
    }
}
