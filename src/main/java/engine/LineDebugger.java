package engine;

import java.util.ArrayList;

public abstract class LineDebugger {
    public static ArrayList<Move> debugLine;
    public static ArrayList<Move> currentLine;
    public static ArrayList<Move> alphaLine;
    public static ArrayList<Move> betaLine;
    private static boolean[] matches;

    public static void setLine(ArrayList<Move> line) {
        debugLine = line;
        matches = new boolean[line.size()];
    }

    public static Move getMove(int depth) {
        return debugLine.get(depth - 1);
    }

    public static void match(int depth) {
        matches[depth - 1] = true;
    }

    public static void unmatch(int depth) {
        matches[depth - 1] = false;
    }

    public static boolean onLine(int depth) {
        for (int i = 0; i < depth - 1; i++) {
            if (!matches[i]) {
                return false;
            }
        }
        return true;
    }

    public static void setAlphaLine() {
        alphaLine.clear();
        for (Move move : currentLine) {
            alphaLine.add(new Move((move)));
        }
    }

    public static void setBetaLine() {
        betaLine.clear();
        for (Move move : currentLine) {
            betaLine.add(new Move((move)));
        }
    }

}
