import java.util.*;

/**
 * This class tests the speed of a list methods
 */
public class Tester {

    /**
     * The count iterations which use in testing add, get, remove
     */
    private final int k;

    /**
     * List that is being tested
     */
    private List<Object> list;

    public Tester(List<Object> list, int N, int k) {
        this.list = list;
        this.k = k;
        setup(N);
    }

    /**
     * This method adds N objects to list
     */
    private void setup(int N) {
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
        map.put(Position.BEGIN, testAddTo(list, Position.BEGIN));
        map.put(Position.MIDDLE, testAddTo(list, Position.MIDDLE));
        map.put(Position.END, testAddTo(list, Position.MIDDLE));
        return map;
    }

    /**
     * This method returns speed map of method get from begin, middle and end of list
     * @return  map a speed map
     */
    public Map<Position, Long> testGet() {
        Map<Position, Long> map = new HashMap<Position, Long>();
        map.put(Position.BEGIN, testGetFrom(list, Position.BEGIN));
        map.put(Position.MIDDLE, testGetFrom(list, Position.MIDDLE));
        map.put(Position.END, testGetFrom(list, Position.MIDDLE));
        return map;
    }

    /**
     * This method returns speed map of method remove from begin, middle and end of list
     * @return map a speed map
     */
    public Map<Position, Long> testRemove() {
        Map<Position, Long> map = new HashMap<Position, Long>();
        map.put(Position.BEGIN, testRemoveFrom(list, Position.BEGIN));
        map.put(Position.MIDDLE, testRemoveFrom(list, Position.MIDDLE));
        map.put(Position.END, testRemoveFrom(list, Position.MIDDLE));
        return map;
    }

    /**
     * This method returns the time it takes to add k elements to position
     * @param list a list to test
     * @param position a place where adds elem
     * @return time
     */
    private long testAddTo(List<Object> list, Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            list.add(defineIndex(list, position), new Object());
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    /**
     * This method returns the time it takes to get an element k times from position
     * @param list a list to test
     * @param position a place where adds elem
     * @return time
     */
    private long testGetFrom(List<Object> list, Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            Object object = list.get(defineIndex(list, position));
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    /**
     * This method returns the time it takes to remove k elements from position
     * @param list a list to test
     * @param position a place where adds elem
     * @return time
     */
    private long testRemoveFrom(List<Object> list, Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            list.remove(defineIndex(list, position));
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    /**
     * Convert enum value to index for list
     * @param list a list to test
     * @param position a place where adds elem
     * @return index
     */
    private static int defineIndex(List<Object> list, Position position) {
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
