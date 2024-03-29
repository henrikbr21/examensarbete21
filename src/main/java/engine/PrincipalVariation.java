package engine;

import java.util.ArrayList;

/**
 * This class is used to represent a principal variation.
 */

public class PrincipalVariation {
    private final ArrayList<Move> pv;

    public PrincipalVariation() {
        pv = new ArrayList<>();
    }

    public void addMove(Move move) {
        pv.add(new Move(move));
    }

    public void addAllMoves(PrincipalVariation otherPV) {
        for (int i = 0; i < otherPV.size(); i++) {
            pv.add(new Move(otherPV.get(i)));
        }
    }

    public Move get(int index) {
        return pv.get(index);
    }

    public void clear() {
        pv.clear();
    }

    public int size() {
        return pv.size();
    }

}
