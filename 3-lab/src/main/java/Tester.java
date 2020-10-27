import java.util.*;

/**
 * This class tests the speed of a list methods
 */
public abstract class Tester {

    /**
     * The count iterations which use in testing add, get, remove
     */
    protected final int k;

    /**
     * List that is being tested
     */
    private List<Object> list;

    public Tester(int N, int k) {
        this.k = k;
        setup(N);
    }

    public Tester(List<Object> list, int N, int k) {
        this.list = list;
        this.k = k;
        setup(N);
    }

    /**
     * This method adds N objects to list
     */
    protected void setup(int N) {
        for (int i = 0; i < N; i++) {
            list.add(new Object());
        }
    }

    /**
     * This method returns speed map of method add to begin, middle and end of list
     * @return map a speed map
     */
    public Map<Position, Long> testAdd() {
        Map<Position, Long> map = new HashMap<Position, Long>();
        map.put(Position.BEGIN, testAddTo(Position.BEGIN));
        map.put(Position.MIDDLE, testAddTo(Position.MIDDLE));
        map.put(Position.END, testAddTo(Position.END));
        return map;
    }

    /**
     * This method returns speed map of method get from begin, middle and end of list
     * @return  map a speed map
     */
    public Map<Position, Long> testGet() {
        Map<Position, Long> map = new HashMap<Position, Long>();
        map.put(Position.BEGIN, testGetFrom(Position.BEGIN));
        map.put(Position.MIDDLE, testGetFrom(Position.MIDDLE));
        map.put(Position.END, testGetFrom(Position.END));
        return map;
    }

    /**
     * This method returns speed map of method remove from begin, middle and end of list
     * @return map a speed map
     */
    public Map<Position, Long> testRemove() {
        Map<Position, Long> map = new HashMap<Position, Long>();
        map.put(Position.BEGIN, testRemoveFrom(Position.BEGIN));
        map.put(Position.MIDDLE, testRemoveFrom(Position.MIDDLE));
        map.put(Position.END, testRemoveFrom(Position.END));
        return map;
    }

    /**
     * This method returns the time it takes to add k elements to position
     * @param position a place where adds elem
     * @return time
     */
    protected long testAddTo(Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            list.add(defineIndex(position), new Object());
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    /**
     * This method returns the time it takes to get an element k times from position
     * @param position a place where adds elem
     * @return time
     */
    protected long testGetFrom(Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            Object object = list.get(defineIndex(position));
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    /**
     * This method returns the time it takes to remove k elements from position
     * @param position a place where adds elem
     * @return time
     */
    protected long testRemoveFrom(Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            list.remove(defineIndex(position));
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    /**
     * Convert enum value to index for list
     * @param position a place where adds elem
     * @return index
     */
    protected int defineIndex(Position position) {
        switch (position) {
            case BEGIN: {
                return 0;
            }
            case MIDDLE: {
                return list.size() / 2;
            }
            case END: {
                return list.size() - 1;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
    }
}
