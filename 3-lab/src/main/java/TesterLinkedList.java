import java.util.*;

public class TesterLinkedList extends Tester {
    /**
     * List that is being tested
     */
    private final LinkedList<Object> linkedList;

    public TesterLinkedList(LinkedList<Object> list, int N, int k) {
        super(N, k);
        this.linkedList = list;
    }

    /**
     * This method adds N objects to list
     */
    @Override
    protected void setup(int N) {
        for (int i = 0; i < N; i++) {
            linkedList.add(new Object());
        }
    }

    /**
     * This method returns the time it takes to add k elements to position
     * @param position a place where adds elem
     * @return time
     */
    @Override
    protected long testAddTo(Position position) {
        Date startTime = new Date();
        Object object;
        for(int i = 0; i < k; i++) {
            if (Position.BEGIN == position) {
                linkedList.addFirst(new Object());
            } else if (Position.END == position) {
                linkedList.addLast(new Object());
            } else {
                linkedList.add(linkedList.size() / 2, new Object());
            }
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    /**
     * This method returns the time it takes to get an element k times from position
     * @param position a place where adds elem
     * @return time
     */
    @Override
    protected long testGetFrom(Position position) {
        Date startTime = new Date();
        Object object;
        for(int i = 0; i < k; i++) {
            if (Position.BEGIN == position) {
                object = linkedList.getFirst();
            } else if (Position.END == position) {
                object = linkedList.getLast();
            } else {
                object = linkedList.get(linkedList.size() / 2);
            }
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    /**
     * This method returns the time it takes to remove k elements from position
     * @param position a place where adds elem
     * @return time
     */
    @Override
    protected long testRemoveFrom(Position position) {
        Date startTime = new Date();
        Object object;
        for(int i = 0; i < k; i++) {
            if (Position.BEGIN == position) {
                object = linkedList.removeFirst();
            } else if (Position.END == position) {
                object = linkedList.removeLast();
            } else {
                object = linkedList.remove(linkedList.size() / 2);
            }
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }
}
