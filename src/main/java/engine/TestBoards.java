package engine;

import java.util.ArrayList;

public class TestBoards {
    public static ArrayList<ArrayList<Board>> boards = new ArrayList<>();
    private static ArrayList<Board> randomBoards0 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards1 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards2 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards3 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards4 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards5 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards6 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards7 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards8 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards9 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards10 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards11 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards12 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards13 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards14 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards15 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards16 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards17 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards18 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards19 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards20 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards21 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards22 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards23 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards24 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards25 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards26 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards27 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards28 = new ArrayList<Board>();
    private static ArrayList<Board> randomBoards29 = new ArrayList<Board>();

    public static void initBoards100(){
        char[][] randomBoard = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
        };
        Board randomBoard0 = new Board(randomBoard);
        randomBoards0.add(randomBoard0);

        randomBoard = new char[][] {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard1 = new Board(randomBoard);
        randomBoards0.add(randomBoard1);

        randomBoard = new char[][] {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard2 = new Board(randomBoard);
        randomBoards0.add(randomBoard2);

        randomBoard = new char[][] {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'P', 'P', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard3 = new Board(randomBoard);
        randomBoards0.add(randomBoard3);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'n', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'P', 'P', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard4 = new Board(randomBoard);
        randomBoards0.add(randomBoard4);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', 'p', 'p', 'p', 'p', 'p', 'p'},
                {'n', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', 'P', ' ', 'P', 'P', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard5 = new Board(randomBoard);
        randomBoards0.add(randomBoard5);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
                {'n', 'p', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', 'P', ' ', 'P', 'P', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard6 = new Board(randomBoard);
        randomBoards0.add(randomBoard6);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', ' ', 'p', 'p', 'p', 'p', 'p'},
                {'n', 'p', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', 'P', ' '},
                {' ', 'P', ' ', ' ', 'P', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard7 = new Board(randomBoard);
        randomBoards0.add(randomBoard7);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', ' ', 'p', 'p', ' ', 'p', 'p'},
                {'n', 'p', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', 'P', ' '},
                {' ', 'P', ' ', ' ', 'P', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard8 = new Board(randomBoard);
        randomBoards0.add(randomBoard8);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', ' ', ' ', 'p', 'p', ' ', 'p', 'p'},
                {'n', 'p', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', 'P', ' '},
                {'R', 'P', ' ', ' ', 'P', 'P', ' ', 'P'},
                {' ', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard9 = new Board(randomBoard);
        randomBoards0.add(randomBoard9);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', 'b', 'n', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard10 = new Board(randomBoard);
        randomBoards1.add(randomBoard10);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', ' ', 'n', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard11 = new Board(randomBoard);
        randomBoards1.add(randomBoard11);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', ' ', 'n', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', ' ', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard12= new Board(randomBoard);
        randomBoards1.add(randomBoard12);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', ' ', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard13= new Board(randomBoard);
        randomBoards1.add(randomBoard13);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', ' ', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard14 = new Board(randomBoard);
        randomBoards1.add(randomBoard14);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', ' ', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard15 = new Board(randomBoard);
        randomBoards1.add(randomBoard15);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', 'P'},
                {' ', ' ', ' ', 'Q', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {'R', 'N', 'B', ' ', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard16 = new Board(randomBoard);
        randomBoards1.add(randomBoard16);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'k', 'n', 'r'},
                {'p', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', 'P'},
                {' ', ' ', ' ', 'Q', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {'R', 'N', 'B', ' ', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard17 = new Board(randomBoard);
        randomBoards1.add(randomBoard17);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'k', 'n', 'r'},
                {'p', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', 'Q', 'P', 'P', ' '},
                {'R', 'N', 'B', ' ', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard18 = new Board(randomBoard);
        randomBoards1.add(randomBoard18);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'k', 'n', 'r'},
                {'p', 'p', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', 'Q', 'P', 'P', ' '},
                {'R', 'N', 'B', ' ', ' ', 'R', ' ', 'K'},
        };
        Board randomBoard19= new Board(randomBoard);
        randomBoards1.add(randomBoard19);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', ' ', ' ', 'n'},
                {' ', ' ', 'b', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'B', 'p', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard20 = new Board(randomBoard);
        randomBoards2.add(randomBoard20);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'B', 'p', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard21 = new Board(randomBoard);
        randomBoards2.add(randomBoard21);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'B', 'p', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard22 = new Board(randomBoard);
        randomBoards2.add(randomBoard22);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', 'b', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'B', 'p', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard23 = new Board(randomBoard);
        randomBoards2.add(randomBoard23);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', 'b', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'B', 'p', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'R', ' ', ' '},
        };
        Board randomBoard24 = new Board(randomBoard);
        randomBoards2.add(randomBoard24);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', ' ', 'r'},
                {'p', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', 'b', ' ', ' ', ' ', 'n'},
                {' ', 'p', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'B', 'p', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'R', ' ', ' '},
        };
        Board randomBoard25 = new Board(randomBoard);
        randomBoards2.add(randomBoard25);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', ' ', 'r'},
                {'p', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', 'b', ' ', ' ', ' ', 'n'},
                {'P', 'p', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', ' ', 'B', 'p', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'R', ' ', ' '},
        };
        Board randomBoard26 = new Board(randomBoard);
        randomBoards2.add(randomBoard26 );

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', ' ', 'r'},
                {'p', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', ' ', ' ', 'n'},
                {'P', 'p', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', 'b', 'B', 'p', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'R', ' ', ' '},
        };
        Board randomBoard27 = new Board(randomBoard);
        randomBoards2.add(randomBoard27);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', ' ', ' ', 'r'},
                {'p', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', ' ', ' ', 'n'},
                {'P', 'p', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', 'b', 'B', 'p', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', 'B', ' ', 'P', ' ', 'P'},
                {'R', 'N', ' ', 'Q', 'K', 'R', ' ', ' '},
        };
        Board randomBoard28 = new Board(randomBoard);
        randomBoards2.add(randomBoard28);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', ' ', ' ', 'r'},
                {'p', ' ', 'p', 'p', 'k', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', ' ', ' ', 'n'},
                {'P', 'p', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', 'b', 'B', 'p', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', 'B', ' ', 'P', ' ', 'P'},
                {'R', 'N', ' ', 'Q', 'K', 'R', ' ', ' '},
        };
        Board randomBoard29 = new Board(randomBoard);
        randomBoards2.add(randomBoard29);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'b', ' ', ' ', ' '},
                {'n', ' ', ' ', ' ', ' ', 'B', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'B', ' ', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', 'Q', ' ', 'R', 'K', ' '},
        };
        Board randomBoard30 = new Board(randomBoard);
        randomBoards3.add(randomBoard30);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'B', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'B', 'n', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', 'Q', ' ', 'R', 'K', ' '},
        };
        Board randomBoard31 = new Board(randomBoard);
        randomBoards3.add(randomBoard31);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'B', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'B', 'n', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'Q', 'R', 'K', ' '},
        };
        Board randomBoard32 = new Board(randomBoard);
        randomBoards3.add(randomBoard32);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'k', ' ', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'B', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'B', 'n', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'Q', 'R', 'K', ' '},
        };
        Board randomBoard33 = new Board(randomBoard);
        randomBoards3.add(randomBoard33);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'k', ' ', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'B', 'n', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'Q', 'R', 'K', ' '},
        };
        Board randomBoard34 = new Board(randomBoard);
        randomBoards3.add(randomBoard34);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'k', ' ', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'B', ' ', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'n', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'Q', 'R', 'K', ' '},
        };
        Board randomBoard35 = new Board(randomBoard);
        randomBoards3.add(randomBoard35);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'k', ' ', ' ', 'n', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'P'},
                {'B', ' ', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'n', ' ', 'P', 'P', ' '},
                {'R', ' ', ' ', ' ', 'Q', 'R', 'K', ' '},
        };
        Board randomBoard36 = new Board(randomBoard);
        randomBoards3.add(randomBoard36);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'k', ' ', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'B', ' ', ' ', 'n'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'P'},
                {'B', ' ', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'n', ' ', 'P', 'P', ' '},
                {'R', ' ', ' ', ' ', 'Q', 'R', 'K', ' '},
        };
        Board randomBoard37 = new Board(randomBoard);
        randomBoards3.add(randomBoard37);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'k', ' ', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'B', ' ', ' ', 'n'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'P'},
                {'B', ' ', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'n', ' ', 'P', 'P', ' '},
                {' ', ' ', 'R', ' ', 'Q', 'R', 'K', ' '},
        };
        Board randomBoard38 = new Board(randomBoard);
        randomBoards3.add(randomBoard38);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'k', ' ', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'b', ' ', ' ', 'B', ' ', ' ', 'n'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'n', 'P', ' ', ' ', ' ', 'P'},
                {'B', ' ', 'N', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'R', ' ', 'Q', 'R', 'K', ' '},
        };
        Board randomBoard39 = new Board(randomBoard);
        randomBoards3.add(randomBoard39);

        //HERE
        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', 'r', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'b', ' '},
                {' ', ' ', 'P', 'P', ' ', 'N', ' ', ' '},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', 'P'},
                {'R', ' ', ' ', 'Q', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard40 = new Board(randomBoard);
        randomBoards4.add(randomBoard40);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', 'r', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', 'N', ' ', ' '},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', 'P'},
                {'R', ' ', ' ', 'Q', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard41 = new Board(randomBoard);
        randomBoards4.add(randomBoard41);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', 'r', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', 'P'},
                {'R', ' ', ' ', 'Q', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard42 = new Board(randomBoard);
        randomBoards4.add(randomBoard42);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', ' ', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', 'r', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', 'P'},
                {'R', ' ', ' ', 'Q', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard43 = new Board(randomBoard);
        randomBoards4.add(randomBoard43);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', ' ', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', 'r', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', ' '},
                {'R', ' ', ' ', 'Q', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard44 = new Board(randomBoard);
        randomBoards4.add(randomBoard44);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', 'r', 'p', 'p', 'p'},
                {'n', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', ' '},
                {'R', ' ', ' ', 'Q', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard45 = new Board(randomBoard);
        randomBoards4.add(randomBoard45);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', 'r', 'p', 'p', 'p'},
                {'n', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', ' '},
                {'R', ' ', ' ', 'Q', ' ', 'K', 'N', 'R'},
        };
        Board randomBoard46 = new Board(randomBoard);
        randomBoards4.add(randomBoard46);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', 'r', 'p', 'p', ' '},
                {'n', ' ', ' ', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', ' '},
                {'R', ' ', ' ', 'Q', ' ', 'K', 'N', 'R'},
        };
        Board randomBoard47 = new Board(randomBoard);
        randomBoards4.add(randomBoard47);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', 'r', 'p', 'p', ' '},
                {'n', ' ', ' ', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', 'Q', 'K', 'N', 'R'},
        };
        Board randomBoard48 = new Board(randomBoard);
        randomBoards4.add(randomBoard48);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', ' ', ' ', 'k', ' '},
                {'p', 'p', 'p', ' ', 'r', 'p', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', 'b'},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', 'P', 'P', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', 'B', 'B', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', 'Q', 'K', 'N', 'R'},
        };
        Board randomBoard49 = new Board(randomBoard);
        randomBoards4.add(randomBoard49);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', 'b', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', 'Q', 'N', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', 'B', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard50 = new Board(randomBoard);
        randomBoards5.add(randomBoard50);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', 'b', ' '},
                {' ', 'Q', 'N', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', 'B', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard51 = new Board(randomBoard);
        randomBoards5.add(randomBoard51);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', 'b', ' '},
                {' ', 'Q', ' ', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', 'B', 'N', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard52 = new Board(randomBoard);
        randomBoards5.add(randomBoard52);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', 'Q', ' ', ' ', 'P', ' ', ' ', 'b'},
                {'P', 'P', ' ', 'B', 'N', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard53 = new Board(randomBoard);
        randomBoards5.add(randomBoard53);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', ' ', 'b'},
                {'P', 'P', ' ', 'B', 'N', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard54 = new Board(randomBoard);
        randomBoards5.add(randomBoard54);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', ' ', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', ' ', 'b'},
                {'P', 'P', ' ', 'B', 'N', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard55 = new Board(randomBoard);
        randomBoards5.add(randomBoard55);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', ' ', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {'Q', ' ', ' ', ' ', ' ', ' ', ' ', 'b'},
                {'P', 'P', ' ', 'B', 'N', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard56 = new Board(randomBoard);
        randomBoards5.add(randomBoard56);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', ' ', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'b'},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {'Q', ' ', ' ', ' ', ' ', ' ', ' ', 'b'},
                {'P', 'P', ' ', 'B', 'N', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard57 = new Board(randomBoard);
        randomBoards5.add(randomBoard57);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', ' ', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'b'},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', ' ', ' ', 'b'},
                {'P', 'P', ' ', 'B', 'N', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard58 = new Board(randomBoard);
        randomBoards5.add(randomBoard58);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'p', 'p', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'n', ' ', 'b'},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', 'n', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', ' ', ' ', 'b'},
                {'P', 'P', ' ', 'B', 'N', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'},
        };
        Board randomBoard59 = new Board(randomBoard);
        randomBoards5.add(randomBoard59);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', 'b', 'b', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {'q', ' ', 'p', ' ', ' ', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'K', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard60 = new Board(randomBoard);
        randomBoards6.add(randomBoard60);;

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'b', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', ' ', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', ' ', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'K', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard61 = new Board(randomBoard);
        randomBoards6.add(randomBoard61);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'b', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', ' ', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', ' ', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard62 = new Board(randomBoard);
        randomBoards6.add(randomBoard62);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'b', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'q', ' ', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard63 = new Board(randomBoard);
        randomBoards6.add(randomBoard63);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'b', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'q', ' ', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', 'N', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard64 = new Board(randomBoard);
        randomBoards6.add(randomBoard64);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'b', 'n', 'r', 'k', ' '},
                {'p', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'q', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', 'N', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard65 = new Board(randomBoard);
        randomBoards6.add(randomBoard65);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'b', 'n', 'r', 'k', ' '},
                {'p', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'q', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', 'N', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', 'K', ' ', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard66 = new Board(randomBoard);
        randomBoards6.add(randomBoard66);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'n', 'r', 'k', ' '},
                {'p', ' ', 'b', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'q', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', 'N', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', 'K', ' ', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard67 = new Board(randomBoard);
        randomBoards6.add(randomBoard67);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'n', 'r', 'k', ' '},
                {'p', ' ', 'b', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'q', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'N', ' ', ' '},
                {' ', ' ', 'B', 'N', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', ' ', 'K', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard68 = new Board(randomBoard);
        randomBoards6.add(randomBoard68);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'n', 'r', 'k', ' '},
                {'p', ' ', 'b', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', 'q', 'N', ' ', ' '},
                {' ', ' ', 'B', 'N', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', ' ', 'K', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', 'B', ' ', ' ', 'R'},
        };
        Board randomBoard69 = new Board(randomBoard);
        randomBoards6.add(randomBoard69);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', 'n', 'p', 'p', 'p', 'b'},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', 'P', 'B', ' ', 'P', 'B', 'P'},
                {'R', ' ', ' ', 'Q', ' ', 'R', 'K', ' '},
        };
        Board randomBoard70 = new Board(randomBoard);
        randomBoards7.add(randomBoard70);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', 'n', 'p', 'p', ' ', 'b'},
                {' ', ' ', 'N', ' ', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', 'P', 'B', ' ', 'P', 'B', 'P'},
                {'R', ' ', ' ', 'Q', ' ', 'R', 'K', ' '},
        };
        Board randomBoard71 = new Board(randomBoard);
        randomBoards7.add(randomBoard71);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', 'n', 'p', 'p', ' ', 'b'},
                {' ', ' ', 'N', ' ', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', 'B', 'P'},
                {'R', ' ', ' ', 'Q', 'B', 'R', 'K', ' '},
        };
        Board randomBoard72 = new Board(randomBoard);
        randomBoards7.add(randomBoard72);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', 'n', 'p', 'p', ' ', 'b'},
                {' ', ' ', 'N', ' ', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', 'B', 'P'},
                {'R', ' ', ' ', 'Q', 'B', 'R', 'K', ' '},
        };
        Board randomBoard73 = new Board(randomBoard);
        randomBoards7.add(randomBoard73);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', 'n', 'p', 'p', ' ', 'b'},
                {' ', ' ', 'N', ' ', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', 'Q', 'B', 'R', 'K', ' '},
        };
        Board randomBoard74 = new Board(randomBoard);
        randomBoards7.add(randomBoard74);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', ' ', ' ', 'n', 'p', 'p', ' ', 'b'},
                {' ', ' ', 'p', ' ', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', 'Q', 'B', 'R', 'K', ' '},
        };
        Board randomBoard75 = new Board(randomBoard);
        randomBoards7.add(randomBoard75);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', ' ', ' ', 'n', 'p', 'p', ' ', 'b'},
                {' ', ' ', 'p', ' ', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {'R', ' ', ' ', 'Q', 'B', 'R', 'K', ' '},
        };
        Board randomBoard76 = new Board(randomBoard);
        randomBoards7.add(randomBoard76);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', ' ', ' ', 'n', 'p', ' ', ' ', 'b'},
                {' ', ' ', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {'R', ' ', ' ', 'Q', 'B', 'R', 'K', ' '},
        };
        Board randomBoard77 = new Board(randomBoard);
        randomBoards7.add(randomBoard77);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', ' ', ' ', 'n', 'p', ' ', ' ', 'b'},
                {' ', ' ', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', 'P', 'P', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {'R', ' ', ' ', ' ', 'B', 'R', 'K', ' '},
        };
        Board randomBoard78 = new Board(randomBoard);
        randomBoards7.add(randomBoard78);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', ' ', ' ', 'n', 'p', ' ', ' ', 'b'},
                {' ', ' ', 'p', ' ', ' ', 'p', 'p', 'p'},
                {' ', 'r', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', 'P', 'P', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {'R', ' ', ' ', ' ', 'B', 'R', 'K', ' '},
        };
        Board randomBoard79 = new Board(randomBoard);
        randomBoards7.add(randomBoard79);

        //Here
        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', 'b', ' ', ' '},
                {'p', 'p', ' ', ' ', ' ', ' ', ' ', 'R'},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', 'N', 'p', 'P', ' ', 'b', 'N', ' '},
                {' ', ' ', 'n', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard80 = new Board(randomBoard);
        randomBoards8.add(randomBoard80);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', 'b', ' ', ' '},
                {'p', 'p', ' ', ' ', ' ', ' ', ' ', 'R'},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', 'N', 'p', 'P', 'n', 'b', 'N', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard81 = new Board(randomBoard);
        randomBoards8.add(randomBoard81);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', 'b', ' ', ' '},
                {'p', 'p', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', 'N', 'p', 'P', 'n', 'b', 'N', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard82 = new Board(randomBoard);
        randomBoards8.add(randomBoard82);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', 'b', ' ', ' '},
                {'p', 'p', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'p', ' '},
                {' ', 'N', 'p', 'P', 'n', ' ', 'N', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'b', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard83 = new Board(randomBoard);
        randomBoards8.add(randomBoard83);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', 'b', ' ', ' '},
                {'p', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'p', ' '},
                {' ', 'N', 'p', 'P', 'n', ' ', 'N', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'b', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard84 = new Board(randomBoard);
        randomBoards8.add(randomBoard84);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', ' ', ' ', ' '},
                {'p', 'p', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'p', ' '},
                {' ', 'N', 'p', 'P', 'n', ' ', 'N', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'b', ' ', ' ', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard85 = new Board(randomBoard);
        randomBoards8.add(randomBoard85);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', ' ', ' ', ' '},
                {'p', 'p', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'p', ' '},
                {' ', 'N', 'p', 'P', 'n', ' ', 'N', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', ' ', 'b', ' ', ' ', ' ', ' '},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard86 = new Board(randomBoard);
        randomBoards8.add(randomBoard86);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', ' ', ' ', ' '},
                {'p', 'p', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'p', ' '},
                {' ', 'N', 'p', 'P', 'n', ' ', 'N', ' '},
                {' ', ' ', 'b', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard87 = new Board(randomBoard);
        randomBoards8.add(randomBoard87);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', ' ', ' ', ' '},
                {'p', 'p', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'p', ' '},
                {' ', ' ', 'p', 'P', 'n', ' ', 'N', ' '},
                {' ', ' ', 'b', ' ', ' ', ' ', ' ', 'P'},
                {'N', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard88 = new Board(randomBoard);
        randomBoards8.add(randomBoard88);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', 'k', ' ', ' ', ' '},
                {'p', 'p', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'p', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', 'N', ' '},
                {' ', ' ', 'b', ' ', ' ', ' ', ' ', 'P'},
                {'N', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {'P', ' ', 'P', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard89 = new Board(randomBoard);
        randomBoards8.add(randomBoard89);

        //HERE
        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', ' ', 'p', ' ', ' ', ' ', 'p', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'K', 'R', ' ', 'B', 'R', ' '},
        };
        Board randomBoard90 = new Board(randomBoard);
        randomBoards9.add(randomBoard90);

        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {'p', ' ', 'p', ' ', ' ', ' ', 'p', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'K', 'R', ' ', 'B', 'R', ' '},
        };
        Board randomBoard91 = new Board(randomBoard);
        randomBoards9.add(randomBoard91);

        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {'p', ' ', 'p', ' ', ' ', ' ', 'p', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'P', ' ', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'K', 'R', ' ', 'B', 'R', ' '},
        };
        Board randomBoard92 = new Board(randomBoard);
        randomBoards9.add(randomBoard92);

        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', 'r', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {'p', ' ', 'p', ' ', ' ', ' ', 'p', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'P', ' ', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'K', 'R', ' ', 'B', 'R', ' '},
        };
        Board randomBoard93 = new Board(randomBoard);
        randomBoards9.add(randomBoard93);

        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', 'r', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {'p', ' ', 'p', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'K', 'R', ' ', 'B', 'R', ' '},
        };
        Board randomBoard94 = new Board(randomBoard);
        randomBoards9.add(randomBoard94);

        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', 'r', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', ' ', 'p', ' ', ' ', ' ', 'P', ' '},
                {'p', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'K', 'R', ' ', 'B', 'R', ' '},
        };
        Board randomBoard95 = new Board(randomBoard);
        randomBoards9.add(randomBoard95);

        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', 'r', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', ' ', 'p', ' ', ' ', ' ', 'P', ' '},
                {'p', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', 'R', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'K', ' ', ' ', 'B', 'R', ' '},
        };
        Board randomBoard96 = new Board(randomBoard);
        randomBoards9.add(randomBoard96);

        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', 'r', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', ' ', 'p', ' ', ' ', ' ', 'P', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', 'p', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'K', ' ', ' ', 'B', 'R', ' '},
        };
        Board randomBoard97 = new Board(randomBoard);
        randomBoards9.add(randomBoard97);;

        randomBoard = new char[][] {
                {'r', 'n', 'b', ' ', 'k', 'r', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', ' ', 'p', ' ', ' ', ' ', 'P', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', 'p', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard98 = new Board(randomBoard);
        randomBoards9.add(randomBoard98);

        randomBoard = new char[][] {
                {' ', 'n', 'b', ' ', 'k', 'r', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {'r', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', ' ', 'p', ' ', ' ', ' ', 'P', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', 'p', 'P', ' ', 'B', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard99 = new Board(randomBoard);
        randomBoards9.add(randomBoard99);

        boards.add(randomBoards0);
        boards.add(randomBoards1);
        boards.add(randomBoards2);
        boards.add(randomBoards3);
        boards.add(randomBoards4);
        boards.add(randomBoards5);
        boards.add(randomBoards6);
        boards.add(randomBoards7);
        boards.add(randomBoards8);
        boards.add(randomBoards9);


    }

    public static void initBoards200(){
        char[][] randomBoard =  {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'q', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {'P', 'P', ' ', 'Q', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard100= new Board(randomBoard);
        randomBoards10.add(randomBoard100);

        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {'P', 'P', ' ', 'Q', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard101= new Board(randomBoard);
        randomBoards10.add(randomBoard101);

        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {'P', 'P', ' ', 'Q', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard102= new Board(randomBoard);
        randomBoards10.add(randomBoard102);

        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'q', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {'P', 'P', ' ', 'Q', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard103= new Board(randomBoard);
        randomBoards10.add(randomBoard103);

        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'q', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'Q', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard104= new Board(randomBoard);
        randomBoards10.add(randomBoard104);

        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'p', ' ', ' ', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'q', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'Q', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard105= new Board(randomBoard);
        randomBoards10.add(randomBoard105);

        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'Q', ' ', ' ', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'q', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', ' ', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard106= new Board(randomBoard);
        randomBoards10.add(randomBoard106);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'b', ' ', ' ', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'q', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', ' ', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard107= new Board(randomBoard);
        randomBoards10.add(randomBoard107);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'b', ' ', ' ', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'q', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', 'P', ' ', ' ', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard108= new Board(randomBoard);
        randomBoards10.add(randomBoard108);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', 'p', 'b', ' ', ' ', 'p', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'q', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', 'P', ' ', ' ', 'P', ' ', 'B', 'P'},
                {'R', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard109= new Board(randomBoard);
        randomBoards10.add(randomBoard109);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', ' ', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'N', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard110= new Board(randomBoard);
        randomBoards11.add(randomBoard110);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'N', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard111= new Board(randomBoard);
        randomBoards11.add(randomBoard111);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', 'N', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard112= new Board(randomBoard);
        randomBoards11.add(randomBoard112);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', 'N', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard113= new Board(randomBoard);
        randomBoards11.add(randomBoard113);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', 'P', ' '},
                {' ', 'N', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard114= new Board(randomBoard);
        randomBoards11.add(randomBoard114);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', 'P', ' '},
                {' ', 'N', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'K', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard115= new Board(randomBoard);
        randomBoards11.add(randomBoard115);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', 'P', ' '},
                {' ', 'N', ' ', ' ', 'K', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard116= new Board(randomBoard);
        randomBoards11.add(randomBoard116);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', 'n', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', 'P', ' '},
                {' ', 'N', ' ', ' ', 'K', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard117= new Board(randomBoard);
        randomBoards11.add(randomBoard117);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', 'n', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', 'P', 'P'},
                {' ', 'N', ' ', ' ', 'K', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', ' ', ' '},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard118= new Board(randomBoard);
        randomBoards11.add(randomBoard118);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', ' '},
                {' ', ' ', 'n', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', 'n', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'P', ' ', 'P', 'P'},
                {' ', 'N', ' ', ' ', 'K', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', 'P', ' ', ' '},
                {'R', 'N', 'B', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard119= new Board(randomBoard);
        randomBoards11.add(randomBoard119);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', 'p'},
                {'p', 'p', 'n', 'p', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'N', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard120= new Board(randomBoard);
        randomBoards12.add(randomBoard120);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {' ', ' ', ' ', ' ', 'n', 'p', 'p', 'p'},
                {'p', 'p', ' ', 'p', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'N', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard121= new Board(randomBoard);
        randomBoards12.add(randomBoard121);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', 'k', 'b', ' ', 'r'},
                {' ', ' ', ' ', ' ', 'n', 'p', 'p', 'p'},
                {'p', 'p', ' ', 'p', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'N', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard122= new Board(randomBoard);
        randomBoards12.add(randomBoard122);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {' ', ' ', ' ', 'b', 'n', 'p', 'p', 'p'},
                {'p', 'p', ' ', 'p', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'N', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', 'Q', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard123= new Board(randomBoard);
        randomBoards12.add(randomBoard123);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {' ', ' ', ' ', 'b', 'n', 'p', 'p', 'p'},
                {'p', 'p', ' ', 'p', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', ' ', 'Q', ' ', ' '},
                {'P', 'P', 'N', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard124= new Board(randomBoard);
        randomBoards12.add(randomBoard124);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {' ', ' ', ' ', 'b', 'n', 'p', 'p', 'p'},
                {'p', 'p', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', 'P', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', ' ', 'Q', ' ', ' '},
                {'P', 'P', 'N', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard125= new Board(randomBoard);
        randomBoards12.add(randomBoard125);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {' ', ' ', ' ', 'b', 'n', 'p', 'p', 'p'},
                {'p', 'p', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', 'P', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'N', ' ', 'N', ' ', ' ', 'Q', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard126= new Board(randomBoard);
        randomBoards12.add(randomBoard126);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', 'n', 'r'},
                {' ', ' ', ' ', 'b', 'n', 'p', 'p', 'p'},
                {'p', 'p', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', 'P', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'N', ' ', 'N', ' ', ' ', 'Q', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard127= new Board(randomBoard);
        randomBoards12.add(randomBoard127);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', 'n', 'r'},
                {' ', ' ', ' ', 'b', 'n', 'p', 'p', 'p'},
                {'p', 'p', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'p', 'P', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'N', ' ', 'N', ' ', ' ', 'Q', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', 'R', 'B', ' ', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard128= new Board(randomBoard);
        randomBoards12.add(randomBoard128);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', 'n', 'r'},
                {' ', ' ', ' ', 'b', ' ', 'p', 'p', 'p'},
                {'p', 'p', ' ', ' ', 'p', ' ', 'n', ' '},
                {' ', ' ', ' ', 'p', 'P', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'N', ' ', 'N', ' ', ' ', 'Q', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', 'R', 'B', ' ', 'K', 'B', ' ', 'R'},
        };
        Board randomBoard129= new Board(randomBoard);
        randomBoards12.add(randomBoard129);

        //HERE
        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'P', 'P', ' ', ' '},
                {'q', 'p', ' ', 'p', ' ', ' ', 'P', ' '},
                {' ', 'b', ' ', ' ', ' ', ' ', 'Q', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard130= new Board(randomBoard);
        randomBoards13.add(randomBoard130);

        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', 'b', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'P', 'P', ' ', ' '},
                {'q', 'p', ' ', 'p', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'Q', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', 'Q', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard131= new Board(randomBoard);
        randomBoards13.add(randomBoard131);

        randomBoard = new char[][] {
                {' ', 'r', 'b', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', 'b', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'P', 'P', ' ', ' '},
                {'q', 'p', ' ', 'p', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'Q', ' '},
                {' ', ' ', ' ', ' ', ' ', 'Q', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard132= new Board(randomBoard);
        randomBoards13.add(randomBoard132);

        randomBoard = new char[][] {
                {' ', 'r', 'b', 'b', 'k', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'P', 'P', ' ', ' '},
                {'q', 'p', ' ', 'p', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'Q', ' '},
                {' ', ' ', ' ', ' ', ' ', 'Q', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard133= new Board(randomBoard);
        randomBoards13.add(randomBoard133);

        randomBoard = new char[][] {
                {' ', 'r', 'b', 'b', 'k', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'P', 'P', ' ', ' '},
                {'q', 'p', ' ', 'p', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'Q', ' '},
                {' ', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard134= new Board(randomBoard);
        randomBoards13.add(randomBoard134);

        randomBoard = new char[][] {
                {' ', 'r', 'b', 'b', 'k', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'P', 'P', ' ', ' '},
                {'q', 'p', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'Q', ' '},
                {' ', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard135= new Board(randomBoard);
        randomBoards13.add(randomBoard135);

        randomBoard = new char[][] {
                {' ', 'r', 'b', 'b', 'k', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', ' ', 'P', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {'q', 'p', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'Q', ' '},
                {' ', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard136= new Board(randomBoard);
        randomBoards13.add(randomBoard136);

        randomBoard = new char[][] {
                {' ', 'r', 'b', 'b', ' ', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', ' ', 'k', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {'q', 'p', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'Q', ' '},
                {' ', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard137= new Board(randomBoard);
        randomBoards13.add(randomBoard137);

        randomBoard = new char[][] {
                {' ', 'r', 'b', 'b', ' ', ' ', ' ', 'r'},
                {' ', ' ', ' ', ' ', ' ', 'k', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {'q', 'p', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'Q'},
                {' ', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard138= new Board(randomBoard);
        randomBoards13.add(randomBoard138);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'b', ' ', ' ', ' ', 'r'},
                {' ', ' ', ' ', 'b', ' ', 'k', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
                {'q', 'p', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', ' ', 'Q'},
                {' ', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
                {' ', ' ', 'K', ' ', ' ', 'B', ' ', 'R'},
        };
        Board randomBoard139= new Board(randomBoard);
        randomBoards13.add(randomBoard139);

        //HERE
        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', ' ', ' ', 'r'},
                {' ', ' ', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'P', 'b'},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'B', 'B', ' ', 'Q', 'P'},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard140= new Board(randomBoard);
        randomBoards14.add(randomBoard140);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', 'k', ' ', ' ', 'r'},
                {' ', 'r', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'P', 'b'},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'B', 'B', ' ', 'Q', 'P'},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard141= new Board(randomBoard);
        randomBoards14.add(randomBoard141);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', 'k', ' ', ' ', 'r'},
                {' ', 'r', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', ' ', 'P', 'P', 'P', 'b'},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'B', ' ', ' ', 'Q', 'P'},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard142= new Board(randomBoard);
        randomBoards14.add(randomBoard142);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'b', 'k', ' ', ' ', 'r'},
                {' ', 'r', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', ' ', 'P', 'P', 'P', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'B', ' ', ' ', 'Q', 'P'},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard143= new Board(randomBoard);
        randomBoards14.add(randomBoard143);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'b', 'k', ' ', ' ', 'r'},
                {' ', 'r', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'P', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'B', 'B', ' ', 'Q', 'P'},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard144= new Board(randomBoard);
        randomBoards14.add(randomBoard144);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'b', ' ', 'k', ' ', 'r'},
                {' ', 'r', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'P', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', 'P', 'B', 'B', ' ', 'Q', 'P'},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard145= new Board(randomBoard);
        randomBoards14.add(randomBoard145);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'b', ' ', 'k', ' ', 'r'},
                {' ', 'r', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'P', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', 'P'},
                {'P', 'P', 'P', 'B', 'B', ' ', 'Q', ' '},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard146= new Board(randomBoard);
        randomBoards14.add(randomBoard146);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'b', ' ', 'k', ' ', 'r'},
                {'r', ' ', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'P', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', 'P'},
                {'P', 'P', 'P', 'B', 'B', ' ', 'Q', ' '},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard147= new Board(randomBoard);
        randomBoards14.add(randomBoard147);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'b', ' ', 'k', ' ', 'r'},
                {'r', ' ', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', ' ', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', 'P'},
                {'P', 'P', 'P', 'B', 'B', ' ', 'Q', ' '},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard148= new Board(randomBoard);
        randomBoards14.add(randomBoard148);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'b', 'k', ' ', ' ', 'r'},
                {'r', ' ', ' ', 'b', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', 'p', ' ', 'n', 'p'},
                {'q', 'p', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', ' ', ' '},
                {' ', ' ', 'N', ' ', ' ', ' ', ' ', 'P'},
                {'P', 'P', 'P', 'B', 'B', ' ', 'Q', ' '},
                {' ', 'K', 'R', ' ', ' ', ' ', ' ', 'R'},
        };
        Board randomBoard149= new Board(randomBoard);
        randomBoards14.add(randomBoard149);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'N', 'p', ' ', ' ', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', 'Q', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard150= new Board(randomBoard);
        randomBoards15.add(randomBoard150);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'N', 'p', ' ', ' ', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', 'Q', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard151= new Board(randomBoard);
        randomBoards15.add(randomBoard151);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'N', 'p', ' ', ' ', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', 'P', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', 'Q', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard152= new Board(randomBoard);
        randomBoards15.add(randomBoard152);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'q', 'r', 'k', ' '},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'N', 'p', ' ', ' ', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', 'P', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', 'Q', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard153= new Board(randomBoard);
        randomBoards15.add(randomBoard153);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'q', 'r', 'k', ' '},
                {'p', 'p', 'p', 'p', ' ', 'p', 'p', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'N', 'p', ' ', ' ', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', 'P', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', ' ', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard154= new Board(randomBoard);
        randomBoards15.add(randomBoard154);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'q', 'r', 'k', ' '},
                {'p', 'p', 'p', 'p', ' ', 'p', ' ', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'N', 'p', ' ', 'p', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', 'P', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', ' ', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard155= new Board(randomBoard);
        randomBoards15.add(randomBoard155);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'q', 'r', 'k', ' '},
                {'p', 'p', 'p', 'p', ' ', 'p', ' ', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'N', 'p', ' ', 'p', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', 'P', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', 'P', 'P', 'B', 'P'},
                {' ', 'R', 'B', ' ', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard156= new Board(randomBoard);
        randomBoards15.add(randomBoard156);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', ' ', 'r', 'k', ' '},
                {'p', 'p', 'p', 'p', 'q', 'p', ' ', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', 'N', 'p', ' ', 'p', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', 'P', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', 'P', 'P', 'B', 'P'},
                {' ', 'R', 'B', ' ', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard157= new Board(randomBoard);
        randomBoards15.add(randomBoard157);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', ' ', 'r', 'k', ' '},
                {'p', 'p', 'N', 'p', 'q', 'p', ' ', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'p', ' ', 'p', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', 'P', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', 'P', 'P', 'B', 'P'},
                {' ', 'R', 'B', ' ', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard158= new Board(randomBoard);
        randomBoards15.add(randomBoard158);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', ' ', 'r', 'k', ' '},
                {'p', ' ', 'N', 'p', 'q', 'p', ' ', ' '},
                {' ', ' ', 'n', ' ', ' ', 'n', ' ', 'p'},
                {' ', 'p', ' ', ' ', 'p', ' ', 'p', ' '},
                {' ', 'P', 'P', 'b', ' ', ' ', 'P', ' '},
                {'P', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'Q', 'P', 'P', 'B', 'P'},
                {' ', 'R', 'B', ' ', 'K', ' ', 'N', 'R'},
        };
        Board randomBoard159= new Board(randomBoard);
        randomBoards15.add(randomBoard159);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'b', 'p', 'p', ' ', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', ' ', 'n', ' ', 'p'},
                {' ', 'P', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard160= new Board(randomBoard);
        randomBoards16.add(randomBoard160);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'q', 'r', 'k', ' '},
                {'b', 'p', 'p', ' ', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', 'p', ' ', 'n', ' ', 'p'},
                {' ', 'P', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard161= new Board(randomBoard);
        randomBoards16.add(randomBoard161);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'q', 'r', 'k', ' '},
                {'b', 'p', 'p', ' ', ' ', 'p', 'p', ' '},
                {'p', 'P', ' ', 'p', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard162= new Board(randomBoard);
        randomBoards16.add(randomBoard162);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'b', 'p', 'p', ' ', ' ', 'p', 'p', ' '},
                {'p', 'P', ' ', 'p', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {'R', ' ', 'B', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard163= new Board(randomBoard);
        randomBoards16.add(randomBoard163);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'b', 'p', 'p', ' ', ' ', 'p', 'p', ' '},
                {'p', 'P', ' ', 'p', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {' ', 'R', 'B', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard164= new Board(randomBoard);
        randomBoards16.add(randomBoard164);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'b', 'p', ' ', ' ', ' ', 'p', 'p', ' '},
                {'p', 'P', 'p', 'p', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {' ', 'R', 'B', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard165= new Board(randomBoard);
        randomBoards16.add(randomBoard165);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'b', 'p', ' ', ' ', ' ', 'p', 'p', ' '},
                {'p', 'P', 'p', 'p', ' ', 'n', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', 'B', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {' ', 'R', ' ', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard166= new Board(randomBoard);
        randomBoards16.add(randomBoard166);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'b', 'p', ' ', ' ', ' ', 'p', 'p', ' '},
                {' ', 'P', 'p', 'p', ' ', 'n', ' ', 'p'},
                {'p', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', 'B', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {' ', 'R', ' ', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard167= new Board(randomBoard);
        randomBoards16.add(randomBoard167);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'b', 'p', ' ', ' ', ' ', 'p', 'p', ' '},
                {' ', 'P', 'p', 'p', ' ', 'n', ' ', 'p'},
                {'p', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {' ', 'R', 'B', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard168= new Board(randomBoard);
        randomBoards16.add(randomBoard168);

        randomBoard = new char[][] {
                {'r', ' ', 'b', 'q', ' ', 'r', 'k', ' '},
                {'b', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'P', 'p', 'p', ' ', 'n', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', 'P', 'n', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'P', 'P', 'B', 'P'},
                {' ', 'R', 'B', 'Q', 'N', 'R', 'K', ' '},
        };
        Board randomBoard169= new Board(randomBoard);
        randomBoards16.add(randomBoard169);

        //HERE
        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', ' ', ' ', 'k', ' '},
                {' ', ' ', ' ', ' ', 'q', 'p', 'p', 'p'},
                {' ', 'p', ' ', 'b', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'p', 'n', ' ', ' ', ' '},
                {'N', ' ', 'B', 'N', 'n', ' ', ' ', ' '},
                {'P', 'Q', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard170= new Board(randomBoard);
        randomBoards17.add(randomBoard170);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', ' ', ' ', 'k', ' '},
                {' ', ' ', ' ', 'n', 'q', 'p', 'p', 'p'},
                {' ', 'p', ' ', 'b', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'p', ' ', ' ', ' ', ' '},
                {'N', ' ', 'B', 'N', 'n', ' ', ' ', ' '},
                {'P', 'Q', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard171= new Board(randomBoard);
        randomBoards17.add(randomBoard171);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', ' ', ' ', 'k', ' '},
                {' ', ' ', ' ', 'n', 'q', 'p', 'p', 'p'},
                {' ', 'p', ' ', 'b', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'B', ' ', ' ', ' ', ' '},
                {'N', ' ', ' ', 'N', 'n', ' ', ' ', ' '},
                {'P', 'Q', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard172= new Board(randomBoard);
        randomBoards17.add(randomBoard172);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', 'q', ' ', 'k', ' '},
                {' ', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', 'b', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'B', ' ', ' ', ' ', ' '},
                {'N', ' ', ' ', 'N', 'n', ' ', ' ', ' '},
                {'P', 'Q', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard173= new Board(randomBoard);
        randomBoards17.add(randomBoard173);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', 'q', ' ', 'k', ' '},
                {' ', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', 'b', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'B', ' ', ' ', ' ', ' '},
                {'N', 'Q', ' ', 'N', 'n', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard174= new Board(randomBoard);
        randomBoards17.add(randomBoard174);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', ' ', 'q', 'k', ' '},
                {' ', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', 'b', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'B', ' ', ' ', ' ', ' '},
                {'N', 'Q', ' ', 'N', 'n', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard175= new Board(randomBoard);
        randomBoards17.add(randomBoard175);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', ' ', 'q', 'k', ' '},
                {' ', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', 'b', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'B', ' ', ' ', ' ', ' '},
                {'N', 'Q', ' ', 'N', 'n', 'P', ' ', ' '},
                {'P', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', 'P'},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard176= new Board(randomBoard);
        randomBoards17.add(randomBoard176);

        randomBoard = new char[][] {
                {' ', 'b', ' ', ' ', ' ', 'q', 'k', ' '},
                {' ', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'B', ' ', ' ', ' ', ' '},
                {'N', 'Q', ' ', 'N', 'n', 'P', ' ', ' '},
                {'P', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', 'P'},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard177= new Board(randomBoard);
        randomBoards17.add(randomBoard177);

        randomBoard = new char[][] {
                {' ', 'b', ' ', ' ', ' ', 'q', 'k', ' '},
                {' ', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', 'b', ' ', ' ', ' '},
                {'p', 'P', ' ', 'B', ' ', ' ', ' ', ' '},
                {'N', 'Q', ' ', 'N', 'n', 'P', ' ', ' '},
                {'P', ' ', ' ', ' ', 'P', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard178= new Board(randomBoard);
        randomBoards17.add(randomBoard178);

        randomBoard = new char[][] {
                {' ', 'b', ' ', ' ', ' ', 'q', 'k', ' '},
                {' ', ' ', ' ', 'n', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', 'b', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', ' ', ' ', ' ', ' '},
                {'N', 'p', ' ', 'N', 'n', 'P', ' ', ' '},
                {'P', ' ', ' ', ' ', 'P', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {' ', ' ', 'B', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard179= new Board(randomBoard);
        randomBoards17.add(randomBoard179);

        //HERE
        randomBoard = new char[][] {
                {' ', ' ', 'r', 'q', ' ', 'r', 'k', ' '},
                {'p', 'b', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', ' ', ' ', 'R', ' ', 'K', ' '},
        };
        Board randomBoard180= new Board(randomBoard);
        randomBoards18.add(randomBoard180);

        randomBoard = new char[][] {
                {' ', ' ', 'r', 'q', ' ', 'r', 'k', ' '},
                {' ', 'b', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', 'p', 'n', ' ', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', ' ', ' ', 'R', ' ', 'K', ' '},
        };
        Board randomBoard181= new Board(randomBoard);
        randomBoards18.add(randomBoard181);

        randomBoard = new char[][] {
                {' ', ' ', 'r', 'q', ' ', 'r', 'k', ' '},
                {' ', 'b', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', 'B', 'n', ' ', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', ' ', ' ', 'R', ' ', 'K', ' '},
        };
        Board randomBoard182= new Board(randomBoard);
        randomBoards18.add(randomBoard182);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', 'r', 'k', ' '},
                {' ', 'b', ' ', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', 'B', 'n', ' ', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', ' ', ' ', 'R', ' ', 'K', ' '},
        };
        Board randomBoard183= new Board(randomBoard);
        randomBoards18.add(randomBoard183);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', 'r', 'k', ' '},
                {' ', 'b', ' ', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', 'n', ' ', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', ' ', ' ', 'R', ' ', 'K', ' '},
        };
        Board randomBoard184= new Board(randomBoard);
        randomBoards18.add(randomBoard184);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', 'r', 'k', ' '},
                {' ', 'b', ' ', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', ' ', ' ', 'R', ' ', 'K', ' '},
        };
        Board randomBoard185= new Board(randomBoard);
        randomBoards18.add(randomBoard185);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', 'r', 'k', ' '},
                {' ', 'b', ' ', 'q', ' ', 'p', 'p', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard186= new Board(randomBoard);
        randomBoards18.add(randomBoard186);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', 'r', 'k', ' '},
                {' ', 'b', ' ', 'q', ' ', 'p', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', 'p', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard187= new Board(randomBoard);
        randomBoards18.add(randomBoard187);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', 'r', 'k', ' '},
                {' ', 'b', ' ', 'q', ' ', 'p', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', 'p', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', 'R', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard188= new Board(randomBoard);
        randomBoards18.add(randomBoard188);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', 'r', ' ', 'k', ' '},
                {' ', 'b', ' ', 'q', ' ', 'p', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', 'p', ' '},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', 'n'},
                {' ', ' ', ' ', 'P', 'P', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'N', ' ', ' '},
                {'P', ' ', ' ', 'Q', ' ', 'P', 'P', 'P'},
                {' ', 'R', 'R', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard189= new Board(randomBoard);
        randomBoards18.add(randomBoard189);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', 'b', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'Q', 'P', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard190= new Board(randomBoard);
        randomBoards19.add(randomBoard190);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'b', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'Q', 'P', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard191= new Board(randomBoard);
        randomBoards19.add(randomBoard191);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'b', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'Q', 'P', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', ' ', 'K', 'R', ' ', ' '},
        };
        Board randomBoard192= new Board(randomBoard);
        randomBoards19.add(randomBoard192);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'q', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'b', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'Q', 'P', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', ' ', 'K', 'R', ' ', ' '},
        };
        Board randomBoard193= new Board(randomBoard);
        randomBoards19.add(randomBoard193);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'q', 'p', 'p', 'p'},
                {' ', ' ', 'p', 'b', 'p', 'n', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', 'B', ' ', 'Q', 'P', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', ' ', 'K', 'R', ' ', ' '},
        };
        Board randomBoard194= new Board(randomBoard);
        randomBoards19.add(randomBoard194);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'q', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', 'b', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', 'B', ' ', 'Q', 'P', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', ' ', 'K', 'R', ' ', ' '},
        };
        Board randomBoard195= new Board(randomBoard);
        randomBoards19.add(randomBoard195);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', ' ', ' ', 'q', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', 'b', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', 'B', ' ', 'Q', 'P', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', 'K', ' ', 'R', ' ', ' '},
        };
        Board randomBoard196= new Board(randomBoard);
        randomBoards19.add(randomBoard196);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', 'r', ' ', ' '},
                {'p', 'p', ' ', ' ', 'q', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', 'b', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', 'B', ' ', 'Q', 'P', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', 'K', ' ', 'R', ' ', ' '},
        };
        Board randomBoard197= new Board(randomBoard);
        randomBoards19.add(randomBoard197);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', 'r', ' ', ' '},
                {'p', 'p', ' ', ' ', 'q', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', 'n', ' ', ' '},
                {' ', ' ', 'b', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', 'P', 'B', ' ', 'Q', ' ', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', 'K', ' ', 'R', ' ', ' '},
        };
        Board randomBoard198= new Board(randomBoard);
        randomBoards19.add(randomBoard198);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'k', 'r', ' ', ' '},
                {'p', 'p', ' ', ' ', 'q', 'p', 'p', ' '},
                {' ', ' ', 'p', ' ', 'p', 'n', ' ', 'p'},
                {' ', ' ', 'b', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', 'P', ' '},
                {' ', ' ', 'P', 'B', ' ', 'Q', ' ', 'P'},
                {'P', 'P', ' ', ' ', ' ', 'P', ' ', ' '},
                {'R', ' ', 'B', 'K', ' ', 'R', ' ', ' '},
        };
        Board randomBoard199= new Board(randomBoard);
        randomBoards19.add(randomBoard199);

        boards.add(randomBoards10);
        boards.add(randomBoards11);
        boards.add(randomBoards12);
        boards.add(randomBoards13);
        boards.add(randomBoards14);
        boards.add(randomBoards15);
        boards.add(randomBoards16);
        boards.add(randomBoards17);
        boards.add(randomBoards18);
        boards.add(randomBoards19);
    }

    public static void initRandomBoards300(){

        char[][] randomBoard = {
                {'r', ' ', ' ', ' ', 'k', 'b', 'n', 'r'},
                {' ', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard200= new Board(randomBoard);
        randomBoards20.add(randomBoard200);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', 'b', 'n', 'r'},
                {' ', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', 'P'},
                {'R', 'N', 'B', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard201= new Board(randomBoard);
        randomBoards20.add(randomBoard201);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', 'b', 'n', 'r'},
                {' ', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'P', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard202= new Board(randomBoard);
        randomBoards20.add(randomBoard202);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', 'b', 'n', 'r'},
                {' ', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {'P', ' ', 'P', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard203= new Board(randomBoard);
        randomBoards20.add(randomBoard203);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', 'b', 'n', 'r'},
                {' ', 'p', 'p', 'b', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', ' ', 'P', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard204= new Board(randomBoard);
        randomBoards20.add(randomBoard204);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', ' ', 'n', 'r'},
                {' ', 'p', 'p', 'b', 'b', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', ' ', 'P', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard205= new Board(randomBoard);
        randomBoards20.add(randomBoard205);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', ' ', 'n', 'r'},
                {' ', 'p', 'p', 'b', 'b', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', ' ', 'P', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', ' ', 'K', ' ', 'R'},
        };
        Board randomBoard206= new Board(randomBoard);
        randomBoards20.add(randomBoard206);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', ' ', 'n', 'r'},
                {' ', ' ', 'p', 'b', 'b', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', ' ', 'P', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', ' ', 'K', ' ', 'R'},
        };
        Board randomBoard207= new Board(randomBoard);
        randomBoards20.add(randomBoard207);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', 'k', ' ', 'n', 'r'},
                {' ', ' ', 'p', 'b', 'b', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', ' ', 'P', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', ' ', 'K', ' ', 'R'},
        };
        Board randomBoard208= new Board(randomBoard);
        randomBoards20.add(randomBoard208);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', 'k', ' ', 'n', 'r'},
                {' ', 'r', 'p', 'b', 'b', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'p', ' ', ' ', ' ', ' ', ' '},
                {'P', 'P', ' ', ' ', ' ', ' ', 'N', ' '},
                {' ', ' ', 'P', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', 'B', ' ', ' ', 'K', ' ', 'R'},
        };
        Board randomBoard209= new Board(randomBoard);
        randomBoards20.add(randomBoard209);

        //HERE
        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', 'b', 'p', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', 'Q', ' ', 'R', 'K', ' '},
        };
        Board randomBoard210= new Board(randomBoard);
        randomBoards21.add(randomBoard210);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', 'p', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', 'Q', ' ', 'R', 'K', ' '},
        };
        Board randomBoard211= new Board(randomBoard);
        randomBoards21.add(randomBoard211);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', 'p', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard212= new Board(randomBoard);
        randomBoards21.add(randomBoard212);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', 'p', 'p', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard213= new Board(randomBoard);
        randomBoards21.add(randomBoard213);

        randomBoard = new char[][] {
                {'r', 'n', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', 'p', 'p', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', 'R', ' ', 'K', ' '},
        };
        Board randomBoard214= new Board(randomBoard);
        randomBoards21.add(randomBoard214);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', 'p', 'n', ' ', ' ', 'p', 'p', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', 'R', ' ', 'K', ' '},
        };
        Board randomBoard215= new Board(randomBoard);
        randomBoards21.add(randomBoard215);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'R', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', 'p', 'n', ' ', ' ', 'p', 'p', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard216= new Board(randomBoard);
        randomBoards21.add(randomBoard216);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'R', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', 'p', 'n', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', 'p', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard217= new Board(randomBoard);
        randomBoards21.add(randomBoard217);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'R', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', 'p', 'n', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', 'p', 'P', ' ', ' ', 'p', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard218= new Board(randomBoard);
        randomBoards21.add(randomBoard218);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', 'R', 'r', 'k', 'b'},
                {'p', ' ', ' ', 'p', ' ', ' ', ' ', 'p'},
                {' ', 'p', 'n', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'p', 'P', ' ', 'p', 'p', ' '},
                {' ', ' ', 'P', ' ', ' ', 'B', ' ', ' '},
                {' ', ' ', ' ', 'Q', ' ', ' ', 'P', 'B'},
                {'P', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard219= new Board(randomBoard);
        randomBoards21.add(randomBoard219);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', 'b', 'p'},
                {' ', ' ', ' ', 'p', 'n', ' ', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', 'B', ' ', ' ', ' '},
                {'P', 'P', 'Q', ' ', 'B', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard220= new Board(randomBoard);
        randomBoards22.add(randomBoard220);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', 'b', 'p'},
                {' ', ' ', ' ', 'p', 'n', ' ', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', 'B', ' ', ' ', ' '},
                {'P', 'P', 'Q', ' ', 'B', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard221= new Board(randomBoard);
        randomBoards22.add(randomBoard221);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', 'b', 'p'},
                {' ', ' ', ' ', 'p', 'n', ' ', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {'P', 'P', 'Q', ' ', 'B', 'P', 'P', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard222= new Board(randomBoard);
        randomBoards22.add(randomBoard222);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', ' ', 'p'},
                {' ', ' ', ' ', 'p', 'n', ' ', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {'P', 'b', 'Q', ' ', 'B', 'P', 'P', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard223= new Board(randomBoard);
        randomBoards22.add(randomBoard223);

        randomBoard = new char[][] {
                {' ', 'r', ' ', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', ' ', 'p'},
                {' ', ' ', ' ', 'p', 'n', ' ', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {'P', 'b', 'Q', ' ', 'B', 'P', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard224= new Board(randomBoard);
        randomBoards22.add(randomBoard224);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', ' ', 'p'},
                {' ', 'q', ' ', 'p', 'n', ' ', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', 'P', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {'P', 'b', 'Q', ' ', 'B', 'P', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard225= new Board(randomBoard);
        randomBoards22.add(randomBoard225);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', ' ', 'p'},
                {' ', 'q', ' ', 'p', 'n', ' ', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', 'P', ' '},
                {'P', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', 'b', 'Q', ' ', 'B', 'P', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard226= new Board(randomBoard);
        randomBoards22.add(randomBoard226);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', ' ', ' '},
                {' ', 'q', ' ', 'p', 'n', ' ', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', ' ', 'P', ' '},
                {'P', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', 'b', 'Q', ' ', 'B', 'P', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard227= new Board(randomBoard);
        randomBoards22.add(randomBoard227);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', 'b', 'p', 'p', ' ', ' '},
                {' ', 'q', ' ', 'p', 'n', ' ', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', 'B', 'P', ' '},
                {'P', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'b', 'Q', ' ', 'B', 'P', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard228= new Board(randomBoard);
        randomBoards22.add(randomBoard228);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', ' ', 'p', 'p', ' ', ' '},
                {' ', 'q', ' ', 'p', 'n', ' ', 'p', 'p'},
                {' ', 'b', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'P', ' ', 'P', 'B', 'P', ' '},
                {'P', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'b', 'Q', ' ', 'B', 'P', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard229= new Board(randomBoard);
        randomBoards22.add(randomBoard229);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'r', 'k', ' '},
                {'p', 'p', 'p', 'n', ' ', ' ', 'b', 'p'},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', ' ', ' ', 'p', ' ', 'p', ' ', ' '},
                {' ', 'P', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', 'Q', 'P', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', 'B', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard230= new Board(randomBoard);
        randomBoards23.add(randomBoard230);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', 'p', 'p', 'n', ' ', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', ' ', ' ', 'p', ' ', 'p', ' ', ' '},
                {' ', 'P', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', 'Q', 'P', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', 'B', 'P', 'P', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard231= new Board(randomBoard);
        randomBoards23.add(randomBoard231);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', 'p', 'p', 'n', ' ', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', ' ', ' ', 'p', ' ', 'p', ' ', ' '},
                {' ', 'P', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', 'Q', 'P', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', 'B', ' ', 'R', 'K', ' '},
        };
        Board randomBoard232= new Board(randomBoard);
        randomBoards23.add(randomBoard232);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', 'p', 'p', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', ' ', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'P', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', 'Q', 'P', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', 'B', ' ', 'R', 'K', ' '},
        };
        Board randomBoard233= new Board(randomBoard);
        randomBoards23.add(randomBoard233);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'q', ' ', 'r', 'k', 'b'},
                {'p', 'p', 'p', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', ' ', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'P', ' ', 'P', 'Q', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', 'P', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', 'B', ' ', 'R', 'K', ' '},
        };
        Board randomBoard234= new Board(randomBoard);
        randomBoards23.add(randomBoard234);

        randomBoard = new char[][] {
                {'r', ' ', 'q', ' ', ' ', 'r', 'k', 'b'},
                {'p', 'p', 'p', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', ' ', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'P', ' ', 'P', 'Q', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', 'P', 'N', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', 'B', ' ', 'R', 'K', ' '},
        };
        Board randomBoard235= new Board(randomBoard);
        randomBoards23.add(randomBoard235);

        randomBoard = new char[][] {
                {'r', ' ', 'q', ' ', ' ', 'r', 'k', 'b'},
                {'p', 'p', 'p', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', ' ', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'P', ' ', 'P', 'Q', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', 'B', ' ', 'R', 'K', ' '},
        };
        Board randomBoard236= new Board(randomBoard);
        randomBoards23.add(randomBoard236);

        randomBoard = new char[][] {
                {'r', ' ', 'q', ' ', ' ', 'r', 'k', 'b'},
                {'p', ' ', 'p', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', 'p', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'P', ' ', 'P', 'Q', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', 'N', ' ', 'P', 'P', 'P'},
                {'R', ' ', ' ', 'B', ' ', 'R', 'K', ' '},
        };
        Board randomBoard237= new Board(randomBoard);
        randomBoards23.add(randomBoard237);

        randomBoard = new char[][] {
                {'r', ' ', 'q', ' ', ' ', 'r', 'k', 'b'},
                {'p', ' ', 'p', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', 'p', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'P', ' ', 'P', 'Q', 'P', ' ', ' '},
                {' ', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', 'N', ' ', ' ', 'P', 'P'},
                {'R', ' ', ' ', 'B', ' ', 'R', 'K', ' '},
        };
        Board randomBoard238= new Board(randomBoard);
        randomBoards23.add(randomBoard238);

        randomBoard = new char[][] {
                {'r', ' ', 'q', ' ', ' ', ' ', 'k', 'b'},
                {'p', ' ', 'p', 'n', ' ', 'r', ' ', ' '},
                {' ', ' ', ' ', ' ', 'b', ' ', 'p', ' '},
                {' ', 'p', ' ', 'p', ' ', 'p', ' ', 'p'},
                {' ', 'P', ' ', 'P', 'Q', 'P', ' ', ' '},
                {' ', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', 'N', ' ', ' ', 'P', 'P'},
                {'R', ' ', ' ', 'B', ' ', 'R', 'K', ' '},
        };
        Board randomBoard239= new Board(randomBoard);
        randomBoards23.add(randomBoard239);

        //HERE
        randomBoard = new char[][] {
                {' ', ' ', 'r', 'r', ' ', ' ', 'k', ' '},
                {' ', 'b', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', 'P', 'P', ' '},
                {'P', ' ', 'N', 'B', ' ', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard240= new Board(randomBoard);
        randomBoards24.add(randomBoard240);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', ' ', ' ', 'k', ' '},
                {' ', 'b', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', 'P', 'P', ' '},
                {'P', ' ', 'N', 'B', ' ', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', ' ', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard241= new Board(randomBoard);
        randomBoards24.add(randomBoard241);

        randomBoard = new char[][] {
                {'r', ' ', ' ', 'r', ' ', ' ', 'k', ' '},
                {' ', 'b', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', 'P', 'P', ' '},
                {'P', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard242= new Board(randomBoard);
        randomBoards24.add(randomBoard242);

        randomBoard = new char[][] {
                {'r', 'r', ' ', ' ', ' ', ' ', 'k', ' '},
                {' ', 'b', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', 'P', 'P', ' '},
                {'P', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard243= new Board(randomBoard);
        randomBoards24.add(randomBoard243);

        randomBoard = new char[][] {
                {'r', 'r', ' ', ' ', ' ', ' ', 'k', ' '},
                {' ', 'b', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', 'P', 'P', 'P', ' '},
                {'P', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard244= new Board(randomBoard);
        randomBoards24.add(randomBoard244);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'r', ' ', 'k', ' '},
                {' ', 'b', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', 'P', 'P', 'P', ' '},
                {'P', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard245= new Board(randomBoard);
        randomBoards24.add(randomBoard245);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'r', ' ', 'k', ' '},
                {' ', 'b', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'P', ' ', ' '},
                {' ', 'P', ' ', 'B', 'P', ' ', 'P', ' '},
                {'P', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard246= new Board(randomBoard);
        randomBoards24.add(randomBoard246);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'r', ' ', 'k', ' '},
                {' ', ' ', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', 'b', ' ', 'P', ' ', ' '},
                {' ', 'P', ' ', 'B', 'P', ' ', 'P', ' '},
                {'P', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard247= new Board(randomBoard);
        randomBoards24.add(randomBoard247);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', 'r', ' ', 'k', ' '},
                {' ', ' ', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', 'b', 'B', 'P', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', ' ', 'P', ' '},
                {'P', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard248= new Board(randomBoard);
        randomBoards24.add(randomBoard248);

        randomBoard = new char[][] {
                {'r', ' ', 'r', ' ', ' ', ' ', 'k', ' '},
                {' ', ' ', 'b', ' ', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', 'p', 'n', ' ', ' '},
                {' ', 'p', ' ', 'b', 'B', 'P', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', ' ', 'P', ' '},
                {'P', ' ', ' ', 'B', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'Q', ' ', ' ', 'P'},
                {'R', 'N', ' ', ' ', ' ', 'R', 'K', ' '},
        };
        Board randomBoard249= new Board(randomBoard);
        randomBoards24.add(randomBoard249);

        //HERE
        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', ' ', 'q', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', 'P', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', 'R', 'R', ' ', ' ', ' '},
        };
        Board randomBoard250= new Board(randomBoard);
        randomBoards25.add(randomBoard250);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'n', ' ', ' ', ' ', ' '},
                {' ', ' ', 'B', 'P', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', 'P', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', 'R', 'R', ' ', ' ', ' '},
        };
        Board randomBoard251= new Board(randomBoard);
        randomBoards25.add(randomBoard251);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', ' ', ' ', 'p', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'n', ' ', ' ', ' ', ' '},
                {' ', 'P', 'B', 'P', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', 'R', 'R', ' ', ' ', ' '},
        };
        Board randomBoard252= new Board(randomBoard);
        randomBoards25.add(randomBoard252);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', ' ', ' ', ' ', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'n', ' ', 'p', ' ', ' '},
                {' ', 'P', 'B', 'P', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', 'R', 'R', ' ', ' ', ' '},
        };
        Board randomBoard253= new Board(randomBoard);
        randomBoards25.add(randomBoard253);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', ' ', ' ', ' ', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', 'n', 'R', 'p', ' ', ' '},
                {' ', 'P', 'B', 'P', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
        };
        Board randomBoard254= new Board(randomBoard);
        randomBoards25.add(randomBoard254);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', ' ', 'n', ' ', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'R', 'p', ' ', ' '},
                {' ', 'P', 'B', 'P', ' ', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
        };
        Board randomBoard255= new Board(randomBoard);
        randomBoards25.add(randomBoard255);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', 'p', ' ', ' ', 'n', ' ', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'P', 'B', 'P', 'R', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
        };
        Board randomBoard256= new Board(randomBoard);
        randomBoards25.add(randomBoard256);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', ' ', 'n', ' ', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'P', 'B', 'P', 'R', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
        };
        Board randomBoard257= new Board(randomBoard);
        randomBoards25.add(randomBoard257);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', ' ', 'n', ' ', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'P', 'B', 'P', 'R', ' ', ' ', ' '},
                {'q', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', ' ', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', ' ', ' ', 'R', ' ', ' '},
        };
        Board randomBoard258= new Board(randomBoard);
        randomBoards25.add(randomBoard258);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', 'r', 'k', ' '},
                {'p', ' ', ' ', ' ', 'n', ' ', 'p', 'p'},
                {' ', ' ', 'p', ' ', 'p', ' ', ' ', ' '},
                {' ', 'p', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'P', 'B', 'P', 'R', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'Q', ' ', 'P'},
                {'P', 'q', 'P', ' ', ' ', 'P', 'P', ' '},
                {'K', ' ', ' ', ' ', ' ', 'R', ' ', ' '},
        };
        Board randomBoard259= new Board(randomBoard);
        randomBoards25.add(randomBoard259);

        //HERE
        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {'p', 'b', 'n', ' ', 'b', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', 'q', 'P'},
                {' ', ' ', ' ', 'p', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', 'B', ' ', 'R', 'B', ' '},
                {' ', 'K', 'R', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard260= new Board(randomBoard);
        randomBoards26.add(randomBoard260);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {'p', 'b', 'n', ' ', 'b', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', 'q'},
                {' ', ' ', ' ', 'p', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {' ', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {'P', 'P', ' ', 'B', ' ', 'R', 'B', ' '},
                {' ', 'K', 'R', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard261= new Board(randomBoard);
        randomBoards26.add(randomBoard261);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {'p', 'b', 'n', ' ', 'b', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', 'q'},
                {' ', ' ', ' ', 'p', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', ' ', 'R', 'B', ' '},
                {' ', 'K', 'R', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard262= new Board(randomBoard);
        randomBoards26.add(randomBoard262);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {'p', 'b', 'n', ' ', ' ', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', 'q'},
                {' ', ' ', 'b', 'p', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', ' ', 'R', 'B', ' '},
                {' ', 'K', 'R', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard263= new Board(randomBoard);
        randomBoards26.add(randomBoard263);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {'p', 'b', 'n', ' ', ' ', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', 'q'},
                {' ', ' ', 'b', 'p', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', ' ', 'R', 'B', ' '},
                {'K', ' ', 'R', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard264= new Board(randomBoard);
        randomBoards26.add(randomBoard264);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {' ', 'b', 'n', ' ', ' ', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', 'q'},
                {'p', ' ', 'b', 'p', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', ' ', 'R', 'B', ' '},
                {'K', ' ', 'R', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard265= new Board(randomBoard);
        randomBoards26.add(randomBoard265);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {' ', 'b', 'n', ' ', ' ', ' ', ' ', 'p'},
                {' ', 'p', ' ', ' ', ' ', ' ', ' ', 'q'},
                {'p', ' ', 'b', 'B', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', ' ', 'R', ' ', ' '},
                {'K', ' ', 'R', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard266= new Board(randomBoard);
        randomBoards26.add(randomBoard266);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {' ', 'b', ' ', ' ', ' ', ' ', ' ', 'p'},
                {'n', 'p', ' ', ' ', ' ', ' ', ' ', 'q'},
                {'p', ' ', 'b', 'B', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', ' ', 'B', ' ', 'R', ' ', ' '},
                {'K', ' ', 'R', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard267= new Board(randomBoard);
        randomBoards26.add(randomBoard267);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {' ', 'b', ' ', ' ', ' ', ' ', ' ', 'p'},
                {'n', 'p', ' ', ' ', ' ', ' ', ' ', 'q'},
                {'p', ' ', 'b', 'B', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', 'R', 'B', ' ', 'R', ' ', ' '},
                {'K', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard268= new Board(randomBoard);
        randomBoards26.add(randomBoard268);

        randomBoard = new char[][] {
                {' ', ' ', 'r', ' ', ' ', ' ', ' ', 'k'},
                {' ', 'b', ' ', ' ', ' ', ' ', ' ', 'p'},
                {'n', 'p', ' ', 'b', ' ', ' ', ' ', 'q'},
                {'p', ' ', ' ', 'B', ' ', 'r', 'p', ' '},
                {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},
                {'P', ' ', 'N', ' ', 'P', ' ', ' ', ' '},
                {' ', 'P', 'R', 'B', ' ', 'R', ' ', ' '},
                {'K', ' ', ' ', 'Q', ' ', ' ', ' ', ' '},
        };
        Board randomBoard269= new Board(randomBoard);
        randomBoards26.add(randomBoard269);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'b', 'P', ' ', 'B', ' ', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'B', 'P', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard270= new Board(randomBoard);
        randomBoards27.add(randomBoard270);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', ' ', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'b', 'P', ' ', 'B', ' ', ' ', ' '},
                {' ', 'Q', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'B', 'P', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard271= new Board(randomBoard);
        randomBoards27.add(randomBoard271);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', ' ', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Q', 'b', 'P', ' ', 'B', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'B', 'P', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard272= new Board(randomBoard);
        randomBoards27.add(randomBoard272);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Q', 'b', 'P', ' ', 'B', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'B', 'P', 'P', ' ', 'P'},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard273= new Board(randomBoard);
        randomBoards27.add(randomBoard273);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Q', 'b', 'P', ' ', 'B', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'B', 'P', 'P', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard274= new Board(randomBoard);
        randomBoards27.add(randomBoard274);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', ' '},
                {'b', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'Q', ' ', 'P', ' ', 'B', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'B', 'P', 'P', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard275= new Board(randomBoard);
        randomBoards27.add(randomBoard275);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', 'p', 'p', ' '},
                {'b', ' ', 'P', ' ', ' ', ' ', ' ', ' '},
                {'Q', ' ', ' ', ' ', 'B', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'B', 'P', 'P', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard276= new Board(randomBoard);
        randomBoards27.add(randomBoard276);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'p', ' '},
                {'b', ' ', 'P', ' ', ' ', 'p', ' ', ' '},
                {'Q', ' ', ' ', ' ', 'B', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', 'B', 'P', 'P', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard277= new Board(randomBoard);
        randomBoards27.add(randomBoard277);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', 'k', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', ' ', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'p', ' '},
                {'B', ' ', 'P', ' ', ' ', 'p', ' ', ' '},
                {'Q', ' ', ' ', ' ', 'B', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', ' ', 'P', 'P', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard278= new Board(randomBoard);
        randomBoards27.add(randomBoard278);

        randomBoard = new char[][] {
                {'r', ' ', 'b', ' ', ' ', ' ', ' ', 'r'},
                {'p', 'p', 'p', 'p', 'q', 'k', ' ', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'p', ' '},
                {'B', ' ', 'P', ' ', ' ', 'p', ' ', ' '},
                {'Q', ' ', ' ', ' ', 'B', ' ', ' ', 'P'},
                {' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'P', 'P', ' ', ' ', 'P', 'P', ' ', ' '},
                {'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'},
        };
        Board randomBoard279= new Board(randomBoard);
        randomBoards27.add(randomBoard279);

        //HERE
        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', ' ', 'k', ' '},
                {'p', ' ', ' ', 'r', ' ', 'p', 'p', ' '},
                {' ', 'p', ' ', ' ', 'q', ' ', ' ', 'p'},
                {' ', ' ', 'q', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', 'R', ' ', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard280= new Board(randomBoard);
        randomBoards28.add(randomBoard280);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', ' ', 'k', ' '},
                {'p', ' ', ' ', 'r', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', ' ', 'q', ' ', 'p', 'p'},
                {' ', ' ', 'q', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', 'R', ' ', 'P', 'P', 'P'},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard281= new Board(randomBoard);
        randomBoards28.add(randomBoard281);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', ' ', 'k', ' '},
                {'p', ' ', ' ', 'r', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', ' ', 'q', ' ', 'p', 'p'},
                {' ', ' ', 'q', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', ' ', ' ', 'P'},
                {'P', ' ', ' ', 'R', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard282= new Board(randomBoard);
        randomBoards28.add(randomBoard282);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', ' ', 'k', ' '},
                {'p', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', 'r', 'q', ' ', 'p', 'p'},
                {' ', ' ', 'q', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
                {' ', 'P', ' ', ' ', 'P', ' ', ' ', 'P'},
                {'P', ' ', ' ', 'R', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard283= new Board(randomBoard);
        randomBoards28.add(randomBoard283);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', ' ', 'k', ' '},
                {'p', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', 'r', 'q', ' ', 'p', 'p'},
                {' ', ' ', 'q', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', 'P'},
                {' ', 'P', ' ', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', 'R', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard284= new Board(randomBoard);
        randomBoards28.add(randomBoard284);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', 'q', ' ', 'k', ' '},
                {'p', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', 'r', ' ', ' ', 'p', 'p'},
                {' ', ' ', 'q', 'p', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', 'P'},
                {' ', 'P', ' ', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', 'R', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard285= new Board(randomBoard);
        randomBoards28.add(randomBoard285);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', 'q', ' ', 'k', ' '},
                {'p', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', 'r', ' ', ' ', 'p', 'p'},
                {' ', ' ', 'q', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', 'P'},
                {' ', 'P', ' ', ' ', 'P', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard286= new Board(randomBoard);
        randomBoards28.add(randomBoard286);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', 'q', ' ', 'k', ' '},
                {'p', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', 'r', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'B', ' ', ' ', 'P'},
                {' ', 'P', ' ', ' ', 'q', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard287= new Board(randomBoard);
        randomBoards28.add(randomBoard287);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', 'q', ' ', 'k', ' '},
                {'p', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', 'r', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P'},
                {' ', 'P', ' ', 'B', 'q', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard288= new Board(randomBoard);
        randomBoards28.add(randomBoard288);

        randomBoard = new char[][] {
                {' ', ' ', ' ', 'r', ' ', ' ', 'k', ' '},
                {'p', ' ', ' ', ' ', ' ', 'p', ' ', ' '},
                {' ', 'p', ' ', 'r', ' ', ' ', 'p', 'p'},
                {' ', ' ', ' ', 'R', 'q', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', 'P'},
                {' ', 'P', ' ', 'B', 'q', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', 'P', 'P', ' '},
                {' ', ' ', ' ', 'R', ' ', ' ', 'K', ' '},
        };
        Board randomBoard289= new Board(randomBoard);
        randomBoards28.add(randomBoard289);

        //HERE
        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', ' ', 'k', ' ', ' '},
                {' ', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'R', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', 'P', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', 'P'},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard290= new Board(randomBoard);
        randomBoards29.add(randomBoard290);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', ' ', 'k', ' ', ' '},
                {'r', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'R', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', 'P', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', 'P'},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard291= new Board(randomBoard);
        randomBoards29.add(randomBoard291);

        randomBoard = new char[][] {
                {' ', ' ', ' ', ' ', ' ', 'k', ' ', ' '},
                {'r', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'R', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', 'P', ' ', 'P'},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard292= new Board(randomBoard);
        randomBoards29.add(randomBoard292);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', ' ', 'k', ' ', ' '},
                {' ', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'R', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'B', ' ', 'P', ' ', 'P'},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard293= new Board(randomBoard);
        randomBoards29.add(randomBoard293);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', ' ', 'k', ' ', ' '},
                {' ', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', 'B', ' ', 'p', ' ', ' ', 'R', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard294= new Board(randomBoard);
        randomBoards29.add(randomBoard294);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', ' ', 'k', ' ', ' '},
                {' ', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', 'B', ' ', 'p', ' ', ' ', 'R', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard295= new Board(randomBoard);
        randomBoards29.add(randomBoard295);

        randomBoard = new char[][] {
                {' ', 'r', ' ', ' ', ' ', 'k', ' ', ' '},
                {' ', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'R', ' '},
                {'B', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard296= new Board(randomBoard);
        randomBoards29.add(randomBoard296);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', ' ', 'k', ' ', ' '},
                {' ', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'R', ' '},
                {'B', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard297= new Board(randomBoard);
        randomBoards29.add(randomBoard297);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', ' ', 'k', ' ', ' '},
                {' ', ' ', ' ', 'r', ' ', 'p', 'p', 'p'},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'R', ' '},
                {' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard298= new Board(randomBoard);
        randomBoards29.add(randomBoard298);

        randomBoard = new char[][] {
                {'r', ' ', ' ', ' ', ' ', 'k', ' ', ' '},
                {' ', ' ', ' ', 'r', ' ', 'p', 'p', ' '},
                {'p', ' ', ' ', ' ', ' ', 'n', ' ', ' '},
                {' ', ' ', ' ', 'p', ' ', ' ', 'R', 'p'},
                {' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'B', ' ', ' ', ' ', 'P', ' ', 'P'},
                {'P', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
                {'R', ' ', ' ', ' ', ' ', ' ', 'K', ' '},
        };
        Board randomBoard299= new Board(randomBoard);
        randomBoards29.add(randomBoard299);


        boards.add(randomBoards20);
        boards.add(randomBoards21);
        boards.add(randomBoards22);
        boards.add(randomBoards23);
        boards.add(randomBoards24);
        boards.add(randomBoards25);
        boards.add(randomBoards26);
        boards.add(randomBoards27);
        boards.add(randomBoards28);
        boards.add(randomBoards29);
    }

}
