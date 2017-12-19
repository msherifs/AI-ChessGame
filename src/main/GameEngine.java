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
        run();
    }


    private void run(){
        while(gameStatus == IN_GAME){
            System.out.println(playerName + "'s Turn");
            playerMove = new Move(sc.nextLine());
            playerMove.makeMove(gameBoard);
            checkVictory();
            if (gameStatus == HUMAN_WINNER){
                showWinner();
                break;
            }
            aiEngine.makeMove(gameBoard);
            checkVictory();
            if (gameStatus == AI_WINNER){
                showWinner();
                break;
            }
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

    private void showWinner(){
        switch(gameStatus){
            case HUMAN_WINNER:
                System.out.println("White has beaten the black !");
                break;
            case AI_WINNER:
                System.out.println("Black has beaten the white !");
                break;
        }
    }

    private enum GameState {
        IN_GAME, AI_WINNER, HUMAN_WINNER
    }



    private void makeActualMove (int oldX , int oldY , int newX , int newY){

    Piece movingPiece =this.gameBoard.getPiece(oldX,oldY);
    this.gameBoard.setCell(oldX,oldY) = null;
    this.gameBoard.setCell(newX, newY) = movingPiece ;
    movingPiece.setX(newX);
    movingPiece.setY(newY);


    }
}
