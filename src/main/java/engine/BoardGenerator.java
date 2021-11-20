package engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BoardGenerator {
    private static Board board;
    private static Random rand = new Random();

    public static void setBoard(Board boardToBeSet) {
        board = boardToBeSet;
    }

    public static void generateKlasBoards(String dir, int nbr, int start) {
        Engine engine = new Engine(new TPT(1000000));
        Board simBoard = new Board(board);


        for (int i = 0; i < 10; i++) {
            PrincipalVariation pv = new PrincipalVariation();
            if (i % 2 == 0)
                engine.search(simBoard, "WHITE", 6, pv, false, new MeasurementData(), 0);
            else
                engine.search(simBoard, "BLACK", 6, pv, false, new MeasurementData(), 0);
            Move move = pv.get(pv.size() - 1);
            simBoard.makeMove(move.from, move.to);
            String[][] charArrays = generateCharArrays(simBoard);
            printBoard(charArrays, i, dir, nbr, start);
        }
    }

    public static void generateRandomBoards(String dir, int nbr, int start) {
        Engine engine = new Engine(new TPT(2));
        Board simBoard = new Board(board);

        for (int i = 0; i < 10; i++) {
            MoveArrayList moves;
            if (i % 2 == 0)
                moves = engine.findMoveList(simBoard, "WHITE");
            else
                moves = engine.findMoveList(simBoard, "BLACK");
            Move move = moves.get(rand.nextInt(moves.size() - 1));
            simBoard.makeMove(move.from, move.to);
            String[][] charArrays = generateCharArrays(simBoard);
            printBoard(charArrays, i, dir, nbr, start);
        }
    }

    private static void printBoard(String[][] board, int iteration, String dir, int nbr, int start) {
        File file = new File(dir);

        FileWriter fw = null;
        try {
            file.createNewFile();
            fw = new FileWriter(dir, true);

            fw.write("randomBoard = new char[][] { \n");
            for (int i = 0; i < 8; i++) {
                fw.write("  {");
                for (int j = 0; j < 8; j++) {
                    if (j != 7)
                        fw.write("'" + board[i][j] + "'" + ", ");
                    else
                        fw.write("'" + board[i][j] + "'");
                }
                fw.write("}, \n");
            }
            fw.write("}; \n");
            int writeNumber = start + iteration;
            fw.write("Board KLASBoard" + writeNumber + "= new Board(randomBoard); \n");
            fw.write("KLASBoards" + nbr + ".add(KLASBoard" + writeNumber + "); \n");
            fw.write("\n");
            /*
            Board randomBoard9 = new Board(randomBoard);
            randomBoards0.add(randomBoard9);
            boards.add(randomBoards0);
            */

            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String[][] generateCharArrays(Board board) {
        String[][] chessBoard = new String[8][8];
        for (int i = 0; i < 64; i++) {
            chessBoard[i / 8][i % 8] = " ";
        }
        for (int i = 0; i < 64; i++) {
            if (((board.WP >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "P";
            }
            if (((board.WN >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "N";
            }
            if (((board.WB >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "B";
            }
            if (((board.WR >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "R";
            }
            if (((board.WQ >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "Q";
            }
            if (((board.WK >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "K";
            }
            if (((board.BP >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "p";
            }
            if (((board.BN >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "n";
            }
            if (((board.BB >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "b";
            }
            if (((board.BR >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "r";
            }
            if (((board.BQ >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "q";
            }
            if (((board.BK >> i) & 1) == 1) {
                chessBoard[i / 8][i % 8] = "k";
            }
        }
        String[][] chessBoard2 = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard2[i][7 - j] = chessBoard[i][j];
            }
        }
        return chessBoard2;
    }

}
