package engine;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * This class contains various utility methods most of which are related to string manipulation.
 */

public abstract class Util {
    private static long totalCollectionTime = 0;
    private static long totalCollectionCount = 0;

    public static String convertNumToCoord(int i) {
        String coord = "";
        int k = i % 8;
        int j = (i / 8) + 1;

        switch (k) {
            case 0:
                return coord = coord + "a" + j;
            case 1:
                return coord = coord + "b" + j;
            case 2:
                return coord = coord + "c" + j;
            case 3:
                return coord = coord + "d" + j;
            case 4:
                return coord = coord + "e" + j;
            case 5:
                return coord = coord + "f" + j;
            case 6:
                return coord = coord + "g" + j;
            case 7:
                return coord = coord + "h" + j;
        }
        return coord;
    }

    public static String convertNumToAlph(int i) {
        int k = i % 8;

        switch (k) {
            case 0:
                return "a";
            case 1:
                return "b";
            case 2:
                return "c";
            case 3:
                return "d";
            case 4:
                return "e";
            case 5:
                return "f";
            case 6:
                return "g";
            case 7:
                return "h";
        }
        return "ERROR";
    }

    public static int convertCoordToNum(String coord) {
        char coordLetter = coord.charAt(0);
        char coordNum = coord.charAt(1);

        int result = 0;
        switch (coordLetter) {
            case 'a':
                result = 0;
                break;
            case 'b':
                result = 1;
                break;
            case 'c':
                result = 2;
                break;
            case 'd':
                result = 3;
                break;
            case 'e':
                result = 4;
                break;
            case 'f':
                result = 5;
                break;
            case 'g':
                result = 6;
                break;
            case 'h':
                result = 7;
                break;
        }

        switch (coordNum) {
            case '1':
                result += (0 * 8);
                break;
            case '2':
                result += (1 * 8);
                break;
            case '3':
                result += (2 * 8);
                break;
            case '4':
                result += (3 * 8);
                break;
            case '5':
                result += (4 * 8);
                break;
            case '6':
                result += (5 * 8);
                break;
            case '7':
                result += (6 * 8);
                break;
            case '8':
                result += (7 * 8);
                break;
        }
        return result;
    }

    public static String padBinaryString(String string) {
        while (string.length() < 64) {
            string = "0" + string;
        }

        return string;
    }

    public static long boardFromArray(char[][] board) {
        long moves = 0L;

        int k = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 7; j >= 0; j--) {
                k++;

                String boardString = "0000000000000000000000000000000000000000000000000000000000000000";
                boardString = boardString.substring(k + 1) + "1" + boardString.substring(0, k);

                if (board[i][j] == '1')
                    moves += Util.stringToLong(boardString);
            }
        }
        return moves;
    }

    public static double[] PSTFromArray(double[][] array) {
        double[] PSTs = new double[64];

        int k = -1;
        for (int i = 0; i < 8; i++) {
            for (int j = 7; j >= 0; j--) {
                k++;
                PSTs[k] = array[j][i];
            }
        }
        return PSTs;
    }

    public static long stringToLong(String s) {
        if (s.charAt(0) == '0') {
            return Long.parseLong(s, 2);
        } else {
            return Long.parseLong("1" + s.substring(2), 2) * 2;
        }
    }

    public static void draw(long bitBoard) {
        String bitBoardString = Long.toBinaryString(bitBoard);
        bitBoardString = Util.padBinaryString(bitBoardString);
        int q = 64;

        for (int i = 0; i < 8; i++) {
            System.out.print("[");
            q = q - 8;

            for (int j = 0; j < 8; j++) {
                System.out.print(bitBoardString.charAt(q + j));
                if (j != 7)
                    System.out.print(",");
            }
            System.out.println("]");
        }
        System.out.println("-----------------");
    }

    public static ArrayList<String> cloneArrayList(ArrayList<String> pv) {
        ArrayList<String> copy = new ArrayList<String>();
        for (String move : pv) {
            copy.add(new String(move));
        }
        return copy;
    }

    public static MoveArrayList parseMoveString(String moveString) {
        StringTokenizer st = new StringTokenizer(moveString, " ");
        MoveArrayList moves = MoveArrayListManager.obtainMoveArrayList();

        while (st.hasMoreTokens()) {
            String singleMove = st.nextToken();
            moves.add(Util.convertCoordToNum(singleMove.substring(0, 2)),
                    Util.convertCoordToNum(singleMove.substring(2)));
        }
        return moves;
    }

    public static ArrayList<Move> parseMovesForDebugging(String moveString) {
        StringTokenizer st = new StringTokenizer(moveString, " ");
        ArrayList<Move> moves = new ArrayList<>();

        while (st.hasMoreTokens()) {
            String singleMove = st.nextToken();
            moves.add(new Move(Util.convertCoordToNum(singleMove.substring(0, 2)),
                    Util.convertCoordToNum(singleMove.substring(2))));
        }
        return moves;
    }

    public static GCStats dumpGCLogs() {
        MoveArrayListManager.dumpCounts();

        List<GarbageCollectorMXBean> gcMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        GarbageCollectorMXBean mxBean = gcMXBeans.get(0);
        GCStats stat = new GCStats(mxBean.getCollectionCount() - totalCollectionCount,
                mxBean.getCollectionTime() - totalCollectionTime);
        totalCollectionCount = mxBean.getCollectionCount();
        totalCollectionTime = mxBean.getCollectionTime();
        return stat;
    }

    public static void fixCastlingBoolean(Board board) {
        if ((AttackSets.WKStart & board.WK) == 0) {
            board.castleWKValid = false;
            board.castleWQValid = false;
        } else {
            if ((AttackSets.wRightRookStart & board.WR) == 0)
                board.castleWKValid = false;
            if ((AttackSets.wLeftRookStart & board.WR) == 0)
                board.castleWQValid = false;
        }
        if ((AttackSets.BKStart & board.BK) == 0) {
            board.castleBKValid = false;
            board.castleBQValid = false;
        } else {
            if ((AttackSets.bRightRookStart & board.BR) == 0)
                board.castleBKValid = false;
            if ((AttackSets.bLeftRookStart & board.BR) == 0)
                board.castleBQValid = false;
        }
    }

    public static long getMemoryUsasge() {
        Runtime rt = Runtime.getRuntime();
        rt.maxMemory();
        return rt.totalMemory() - rt.freeMemory();
    }

    public static boolean isEven(int nbr) {
        if (nbr % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
