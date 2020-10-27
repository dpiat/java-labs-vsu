import java.util.*;

public class Tester {
    private final int N;
    private final int k;
    private List<Object> list;

    public Tester(List<Object> list, int N, int k) {
        this.list = list;
        this.N = N;
        this.k = k;
        setup();
    }

    private void setup() {
        for (int i = 0; i < N; i++) {
            list.add(new Object());
        }
    }

    public Map<Position, Long> testAdd() {
        Map<Position, Long> map = new HashMap<Position, Long>();
        map.put(Position.BEGIN, testAddTo(list, Position.BEGIN));
        map.put(Position.MIDDLE, testAddTo(list, Position.MIDDLE));
        map.put(Position.END, testAddTo(list, Position.MIDDLE));
        return map;
    }

    public Map<Position, Long> testGet() {
        Map<Position, Long> map = new HashMap<Position, Long>();
        map.put(Position.BEGIN, testGetFrom(list, Position.BEGIN));
        map.put(Position.MIDDLE, testGetFrom(list, Position.MIDDLE));
        map.put(Position.END, testGetFrom(list, Position.MIDDLE));
        return map;
    }

    public Map<Position, Long> testRemove() {
        Map<Position, Long> map = new HashMap<Position, Long>();
        map.put(Position.BEGIN, testRemoveFrom(list, Position.BEGIN));
        map.put(Position.MIDDLE, testRemoveFrom(list, Position.MIDDLE));
        map.put(Position.END, testRemoveFrom(list, Position.MIDDLE));
        return map;
    }

    private long testAddTo(List<Object> list, Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            list.add(defineIndex(list, position), new Object());
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    private long testGetFrom(List<Object> list, Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            Object object = list.get(defineIndex(list, position));
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

    private long testRemoveFrom(List<Object> list, Position position) {
        Date startTime = new Date();
        for(int i = 0; i < k; i++) {
            list.remove(defineIndex(list, position));
        }
        Date finishTime = new Date();
        return finishTime.getTime() - startTime.getTime();
    }

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
