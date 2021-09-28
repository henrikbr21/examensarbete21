package engine;

import java.util.ArrayList;
import java.util.Comparator;

public class MoveArrayList {
    private final ArrayList<Move> arrayList;
    private int size;

    public MoveArrayList() {
        this.arrayList = new ArrayList<>(200);
    }

    public void add(int from, int to) {
        add(from, to, 'U', 'U', false, 0);
    }

    public Move add(int from, int to, char fromPiece, char toPiece, boolean checking, int score) {
        Move move;
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

    public int indexOf(Move move){
        int i = 0;
        for(Move arrMove : arrayList){
            if(arrMove.from == move.from && arrMove.to == move.to)
                return i;
            i++;
        }
        return -1;
    }
}
