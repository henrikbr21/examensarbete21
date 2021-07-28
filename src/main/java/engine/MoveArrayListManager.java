package engine;

import java.util.ArrayList;

public class MoveArrayListManager {
    private ArrayList<MoveArrayList> moveArrayLists;
    private static MoveArrayListManager manager = new MoveArrayListManager();

    public static MoveArrayList obtainMoveArrayList() {
        synchronized (manager) {
            final int size = manager.moveArrayLists.size();
            if (size > 0) {
                return manager.moveArrayLists.remove(size - 1);
            }
            return new MoveArrayList();
        }
    }

    public static void renounceMoveArrayList(MoveArrayList moveArrayList) {
        synchronized (manager) {
            moveArrayList.clear();
            manager.moveArrayLists.add(moveArrayList);
        }
    }

    public static int size() {
        synchronized (manager) {
            return manager.moveArrayLists.size();
        }
    }

    private MoveArrayListManager() {
        this.moveArrayLists = new ArrayList<MoveArrayList>(200);
    }
}
