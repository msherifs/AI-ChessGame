package main;


import java.io.*;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("WELCOME TO CHESS GAME");
        System.out.println("Enter 1 To Start Game");
        System.out.println("Enter 2 To Load Game");
        char in = sc.nextLine().charAt(0);
        if (in == '1'){
            System.out.println("Enter Player Name :");
            String pname = sc.nextLine();
            System.out.println("Enter Difficulty, 1 for Easy; 2 for Medium; 3 for Hard");
            int diff = sc.nextInt();
            diff += 2;
            GameEngine gameEngine = new GameEngine(pname, diff);
        } else {
            System.out.println("Enter Filename :");
            String fname = sc.nextLine();
            Main.loadBoard(fname + ".hal9000");
        }
    }

    public static void saveGame(Board b, String playerName, String fileName, int diff) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName + ".hal9000"));
            os.writeObject(b.getBoard());
            os.writeObject(playerName);
            os.writeInt(diff);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadBoard(String fileName){
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            Object o = is.readObject();
            //Object o2 = is.readObject();
            System.out.println(o);
            //System.out.println(o2);
            Piece[][] loadedBoard = (Piece [][]) o;
            System.out.println(loadedBoard);
            String loadedString = (String) is.readObject();
            int diff = is.readInt();
            is.close();
            GameEngine gameEngine = new GameEngine(loadedString, new Board(loadedBoard), diff);
        }catch (IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}