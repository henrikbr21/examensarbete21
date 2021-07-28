package tests;

import static org.junit.Assert.*;
import engine.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

public class MoveArrayListManagerTest {
    private MoveArrayList arr;
    private final int moveCount = 7;
    private Move moves[] = new Move[moveCount];

    @Before
    public void setUp() {
        MoveArrayListManager.size(); // Load class and instantiate singleton.

        this.arr = MoveArrayListManager.obtainMoveArrayList();

        this.moves[0] = this.arr.add(0, 1, 'R', 'P', false, 50);
        this.moves[1] = this.arr.add(7, 9, 'P', 'R', true, 100);
        this.moves[2] = this.arr.add(5, 23, 'P', 'Q', true, 75);
        this.moves[3] = this.arr.add(7, 5, 'B', 'T', true, 33);
        this.moves[4] = this.arr.add(11, 33, 'K', 'T', true, 11);
        this.moves[5] = this.arr.add(17, 1, 'K', 'T', true, 55);
        this.moves[6] = this.arr.add(2, 3, 'P', 'K', true, 125);

        assertEquals(this.moveCount, this.arr.size());
    }

    @Test
    public void obtainRenounceThree() {
        assertEquals(this.moveCount, this.arr.size());
        assertEquals(0, MoveArrayListManager.size());

        MoveArrayList arrTemp1 = MoveArrayListManager.obtainMoveArrayList();
        assertEquals(0, MoveArrayListManager.size());
        assertEquals(0, arrTemp1.size());

        MoveArrayList arrTemp2 = MoveArrayListManager.obtainMoveArrayList();
        assertEquals(0, MoveArrayListManager.size());
        assertEquals(0, arrTemp2.size());

        MoveArrayListManager.renounceMoveArrayList(arrTemp2);
        arrTemp2 = null;
        assertEquals(1, MoveArrayListManager.size());

        MoveArrayListManager.renounceMoveArrayList(arrTemp1);
        arrTemp1 = null;
        assertEquals(2, MoveArrayListManager.size());

        MoveArrayList expectedArr = this.arr;
        MoveArrayListManager.renounceMoveArrayList(this.arr);
        this.arr = null;
        assertEquals(3, MoveArrayListManager.size());

        assertEquals(expectedArr, MoveArrayListManager.obtainMoveArrayList());
    }

    @Test
    public void sortAll() {
        this.arr.sort(new Comparator<Move>() {
            @Override
            public int compare(Move lhs, Move rhs) {
                if (lhs.score == rhs.score) {
                    return 0;
                }
                if (lhs.score < rhs.score) {
                    return 1;
                }
                return -1;
            }
        });

        assertEquals(moves[6], this.arr.get(0));
        assertEquals(moves[1], this.arr.get(1));
        assertEquals(moves[2], this.arr.get(2));
        assertEquals(moves[5], this.arr.get(3));
        assertEquals(moves[0], this.arr.get(4));
        assertEquals(moves[3], this.arr.get(5));
        assertEquals(moves[4], this.arr.get(6));
    }

    @Test
    public void containsAll() {
        for (int i = 0; i < this.moveCount; i++) {
            assertTrue(this.arr.contains(moves[i]));
        }
    }

    @Test
    public void containsSmaller() {
        MoveArrayListManager.renounceMoveArrayList(this.arr);
        this.arr = null;

        this.arr = MoveArrayListManager.obtainMoveArrayList();
        assertEquals(0, this.arr.size());

        Move move0 = this.arr.add(7, 5, 'B', 'T', true, 0);
        Move move1 = this.arr.add(2, 3, 'P', 'K', true, 0);
        Move move2 = this.arr.add(11, 33, 'K', 'T', true, 0);
        assertEquals(3, this.arr.size());

        assertTrue(this.arr.contains(move2));
        assertTrue(this.arr.contains(move1));
        assertTrue(this.arr.contains(move0));

        // First three Moves are reused i.e. move[0..2] == this.moves[0..2]
        for (int i = 0; i <= 2; i++) {
            assertTrue(this.arr.contains(moves[i]));
        }

        // The remaining four Moves are not reused i.e. this.moves[3..6] should not be contained.
        for (int i = 3; i < this.moveCount; i++) {
            assertFalse(this.arr.contains(moves[i]));
        }
    }

    @Test
    public void obtainRenounceObtainShrinkedSort() {
        MoveArrayListManager.renounceMoveArrayList(this.arr);
        this.arr = null;

        this.arr = MoveArrayListManager.obtainMoveArrayList();
        assertEquals(0, this.arr.size());

        Move move0 = this.arr.add(0, 1, 'T', 'P', false, 74);
        Move move1 = this.arr.add(7, 9, 'P', 'T', true, 50);
        Move move2 = this.arr.add(7, 9, 'P', 'T', true, 75);

        assertEquals(move0, this.arr.get(0));
        assertEquals(move1, this.arr.get(1));
        assertEquals(move2, this.arr.get(2));

        this.arr.sort(new Comparator<Move>() {
            @Override
            public int compare(Move lhs, Move rhs) {
                if (lhs.score == rhs.score) {
                    return 0;
                }
                if (lhs.score < rhs.score) {
                    return 1;
                }
                return -1;
            }
        });

        assertEquals(move2, this.arr.get(0));
        assertEquals(move0, this.arr.get(1));
        assertEquals(move1, this.arr.get(2));
    }
}
