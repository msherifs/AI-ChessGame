package main;


import java.util.Scanner;


public class GameEngine {

    private GameState gameStatus = GameState.IN_GAME;
    private String playerName;
    private String moveString;
    private Board gameBoard;
    private Move playerMove;
    private AIEngine aiEngine;
    private BestMove alpha;
    private BestMove beta;
    private BestMove moveToDo;
    private Scanner sc = new Scanner(System.in);
    private AIEngine ai;
    private int sss;

    public GameEngine(String playerName){
        this.playerName = playerName;
        gameBoard = new Board();
        ai = new AIEngine();
        run();
    }


    private void run(){
        if (sss > 0){
            System.out.println("sssfaasd");
            return;
        } else {
            System.out.println("adsadsadsadA&A");

        }
        while(gameStatus == GameState.IN_GAME){

            System.out.println(playerName + "'s Turn");
            System.out.println(this.gameBoard);
            moveString = sc.nextLine();
            GameEngine.makeActualMove(Integer.parseInt("" + moveString.charAt(0)), Integer.parseInt("" + moveString.charAt(1)),
                    Integer.parseInt("" + moveString.charAt(3)), Integer.parseInt("" + moveString.charAt(4)), gameBoard);
            System.out.println(gameBoard);
            checkVictory();
            if (gameStatus == GameState.HUMAN_WINNER){
                showWinner();
                break;
            }
//            alpha = new BestMove(Integer.MAX_VALUE);
//            beta = new BestMove(Integer.MIN_VALUE);
//            moveToDo = ai.minMax(5, alpha, beta, gameBoard, "b");
//            System.out.println("Best Move: " + moveToDo.getMove() + "With value: " + moveToDo.getValue() );
//            GameEngine.makeActualMove(moveToDo.getMove().getOldX(), moveToDo.getMove().getOldY(), moveToDo.getMove().getNewX(), moveToDo.getMove().getNewY(), gameBoard);
            ai.minMax(5, Integer.MIN_VALUE, Integer.MAX_VALUE, gameBoard, "b");
            System.out.println(gameBoard);
            checkVictory();
            if (gameStatus == GameState.AI_WINNER){
                showWinner();
                break;
            }
        }
    }

    private void checkVictory(){
        int noKings = 0;
        GameState tempState = GameState.IN_GAME;
        for (int i = 0; i < 8 ; i++){
            for (int j = 0; j < 8; j++) {
                if (gameBoard.hasPiece(i,j)){
                    Piece piece = gameBoard.getCell(i,j);
                    if (piece.toString().equals("k")){
                        tempState = GameState.AI_WINNER;
                        noKings++;
                    }else if ( piece.toString().equals("K")) {
                        tempState = GameState.HUMAN_WINNER;
                        noKings++;
                    }
                }
            }
        }
        if (noKings == 2) {
            gameStatus = GameState.IN_GAME;
        } else {
            gameStatus = tempState;
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



    public static void makeActualMove (int oldX , int oldY , int newX , int newY, Board b){
        Piece movingPiece = b.getCell(oldX,oldY);
        b.setCell(oldX,oldY, new EmptyPiece(oldX, oldY));
        b.setCell(newX, newY, movingPiece);
        movingPiece.setX(newX);
        movingPiece.setY(newY);
    }
}
