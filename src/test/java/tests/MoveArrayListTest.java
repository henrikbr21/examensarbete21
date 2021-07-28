package tests;

import static org.junit.Assert.*;
import engine.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.Before;

import java.util.Comparator;


public class MoveArrayListTest {
    private MoveArrayList arr;
    private Move move0;
    private Move move1;
    private Move move2;

    @Before
    public void setUp() {
        this.arr = new MoveArrayList();

        this.move0 = this.arr.add(0, 1, 'T', 'P', false, 100);
        this.move1 = this.arr.add(7, 9, 'P', 'T', true, 50);
        this.move2 = this.arr.add(7, 9, 'P', 'T', true, 75);
    }

    @Test
    public void add() {
        assertEquals(3, this.arr.size());
    }

    @Test
    public void get() {
        assertEquals(move1, this.arr.get(1));
    }

    @Test
    public void sort() {
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
        assertEquals(move0, this.arr.get(0));
        assertEquals(move2, this.arr.get(1));
        assertEquals(move1, this.arr.get(2));
    }
}
