package main;


import java.util.Scanner;


public class GameEngine {

    private GameState gameStatus = GameState.IN_GAME;
    private String playerName;
    private Board gameBoard;
    private int depth;
    private Scanner sc = new Scanner(System.in);
    private AIEngine ai;
    private boolean inputStatus = true;


    public GameEngine(String playerName, int depth){
        this.playerName = playerName;
        this.depth = depth;
        gameBoard = new Board();
        ai = new AIEngine();
        run();
    }

    public GameEngine(String playerName, Board loadedBoard, int depth){
        this.playerName = playerName;
        gameBoard = loadedBoard;
        ai = new AIEngine();
        run();
    }


    private void run(){
        while(gameStatus == GameState.IN_GAME){

            checkCheckMate();
            System.out.println(playerName + "'s Turn");
            System.out.println(this.gameBoard);
            checkAndMoveUser();
            //System.out.println(gameBoard);
            checkVictory();
            if (gameStatus == GameState.HUMAN_WINNER){
                showWinner();
                break;
            }

            ai.minMax(3, Integer.MIN_VALUE, Integer.MAX_VALUE, gameBoard, "b");
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

    private void checkCheckMate(){

        for(int i = 0; i<8; i++){
            for(int j=0; j<8; j++){
                if (gameBoard.hasPiece(i,j)){
                    Piece p = gameBoard.getCell(i,j);
                    for(int k = 0; k<8; k++) {
                        for (int l = 0; l < 8; l++) {
                            if(p.checkMove(k, l, gameBoard)&&gameBoard.getCell(k,l).getType().equals("King")){
                                System.out.println("CHECKMATE !!!!!! , "+playerName );

                            }
                        }
                    }
                }}}

    }

    private void checkAndMoveUser(){
        while (true) {
            System.out.println("Write down a move in format currentxy,nextxy, OR write save to save the game");
            String moveString = sc.nextLine();
            if (moveString.equals("save")){
                System.out.println("Enter file name to save :");
                String fname = sc.nextLine();
                Main.saveGame(gameBoard, playerName, fname, depth);
                System.exit(0);
            }
            if (gameBoard.hasPiece(Integer.parseInt("" + moveString.charAt(0)), Integer.parseInt("" + moveString.charAt(1)))){
                Piece pc = gameBoard.getCell(Integer.parseInt("" + moveString.charAt(0)), Integer.parseInt("" + moveString.charAt(1)));
                if (!pc.checkMove(Integer.parseInt("" + moveString.charAt(3)), Integer.parseInt("" + moveString.charAt(4)), gameBoard)){
                    System.out.println("Wrong Move, Try again");
                } else {
                    makeActualMove(Integer.parseInt("" + moveString.charAt(0)), Integer.parseInt("" + moveString.charAt(1)), Integer.parseInt("" + moveString.charAt(3)), Integer.parseInt("" + moveString.charAt(4)), gameBoard);
                    return;
                }
            } else {
                System.out.println("Wrong Move, Try again");
            }
        }
    }

    public static void makeActualMove (int oldX , int oldY , int newX , int newY, Board b){
        Piece movingPiece = b.getCell(oldX,oldY);
        b.setCell(oldX,oldY, new EmptyPiece(oldX, oldY));
        b.setCell(newX, newY, movingPiece);
        movingPiece.setX(newX);
        movingPiece.setY(newY);
        movingPiece.calculateAttackAndDefense(b);
    }

}
