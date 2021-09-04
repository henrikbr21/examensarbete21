package tests;

import static org.junit.Assert.*;
import engine.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class TPTTests{
    private TPT tpt;

    @Before
    public void setUp(){
        tpt = new TPT(10);
    }

    @Test
    public void testPutUntilMaxSize(){
        for(long i = 0; i < 10; i++){
            tpt.put(i, i, 5);
        }

        for(long i = 10; i < 16; i++){
            tpt.put(i, i, 5);
        }

        tpt.put(10, 10, 5);
        assertTrue(tpt.get(tpt.hashes[0]).hash == 10);
    }

    @Test
    public void test(){
        for(long i = 0; i < 10; i++){
            tpt.put(i, i, 5);
        }

        tpt.put(10, 10, 5);
        for(long i = 11; i < 16; i++){
            tpt.put(i, i, 5);
        }

        tpt.put(10, 10, 5);

        System.out.println("RefCount: " + tpt.get(10).refCount);
        assertTrue(tpt.get(10).refCount == 2);
    }

}
