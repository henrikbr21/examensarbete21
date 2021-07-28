package engine;

import java.util.ArrayList;
import java.util.Comparator;

public class MoveArrayList {
    private ArrayList<Move> arrayList;
    private int size;

    public MoveArrayList() {
        this.arrayList = new ArrayList<Move>(200);
    }

    public Move add(int from, int to) {
        return add(from, to, 'U', 'U', false, 0);
    }

    public Move add(int from, int to, char fromPiece, char toPiece, boolean checking, int score) {
        Move move = null;
        if (this.size < this.arrayList.size()) {
            move = this.arrayList.get(this.size);
            move.from = from;
            move.to = to;
            move.fromPiece = fromPiece;
            move.toPiece = toPiece;
            move.checking = checking;
            move.score = score;
        } else {
            move = new Move(from, to, fromPiece, toPiece, checking, score);
            if (!this.arrayList.add(move)) {
                move = null;
            }
        }
        ++this.size;
        return move;
    }

    public Move get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        return this.arrayList.get(index);
    }

    public void sort(Comparator<? super Move> comparator) {
        this.arrayList.subList(0, size).sort(comparator);
    }

    public boolean contains(Move move) {
        return this.arrayList.subList(0, size).contains(move);
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.size = 0;
    }
}
