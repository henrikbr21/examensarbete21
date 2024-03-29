package engine;

import java.util.ArrayList;

/**
 * This class is used to avoid initializing new objects of type MoveArrayList in order to minimize interference from the
 * garbage collection performed by the JVM.
 */

public class MoveArrayListManager {
    private final ArrayList<MoveArrayList> moveArrayLists;
    private static final MoveArrayListManager manager = new MoveArrayListManager();
    private static long obtainCount = 0;
    private static long renounceCount = 0;

    public static MoveArrayList obtainMoveArrayList() {
        synchronized (manager) {
            obtainCount++;
            final int size = manager.moveArrayLists.size();
            if (size > 0) {
                return manager.moveArrayLists.remove(size - 1);
            }
            return new MoveArrayList();
        }
    }

    public static void renounceMoveArrayList(MoveArrayList moveArrayList) {
        synchronized (manager) {
            renounceCount++;
            moveArrayList.clear();
            manager.moveArrayLists.add(moveArrayList);
        }
    }

    public static void dumpCounts() {
        System.out.println("Obtain count: " + obtainCount + " Renounce count: " + renounceCount);
    }

    public static int size() {
        synchronized (manager) {
            return manager.moveArrayLists.size();
        }
    }

    private MoveArrayListManager() {
        this.moveArrayLists = new ArrayList<>(200);
    }

    public void reset() {
        obtainCount = 0;
        renounceCount = 0;
        moveArrayLists.clear();
    }
}
