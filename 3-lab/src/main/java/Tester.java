import java.util.*;

public class Tester {

    private Tester() {};

    private static List<Object> arrayList;
    private static List<Object> linkedList;

    private static int N;
    private static int k;

    private static void setup(int N, int k) {
        setN(N);
        setK(k);
        arrayList = new ArrayList<Object>();
        linkedList = new LinkedList<Object>();
        for (int i = 0; i < N; i++) {
            arrayList.add(new Object());
        }
        linkedList.addAll(arrayList);
    }

    public static void testPerformance(int N, int k) {
        setup(N, k);
        System.out.println(parseResult(Method.ADD, testAdd()));
        System.out.println(parseResult(Method.GET, testGet()));
        System.out.println(parseResult(Method.REMOVE, testRemove()));
    }

    private static String parseResult(Method method, Map<Position, List<Long>> map) {
        String res = "";

        switch (method) {
            case ADD: {
                res += "_______________ADD___________\n";
                break;
            }
            case GET: {
                res += "_______________GET___________\n";
                break;
            }
            case REMOVE: {
                res += "______________REMOVE_________\n";
                break;
            }
        }

        res += String.format("%6s|%10s|%10s|\n", "      ", "ArrayList", "LinkedList");
        res += String.format("%6s|%10s|%10s|\n", Position.BEGIN, map.get(Position.BEGIN).get(0), map.get(Position.BEGIN).get(1));
        res += String.format("%6s|%10s|%10s|\n", Position.MIDDLE, map.get(Position.MIDDLE).get(0), map.get(Position.MIDDLE).get(1));
        res += String.format("%6s|%10s|%10s|\n", Position.END, map.get(Position.END).get(0), map.get(Position.END).get(1));
        res += "-----------------------------\n";

        return res;
    }

    private static Map<Position, List<Long>> testAdd() {
        Map<Position, List<Long>> map = new HashMap<Position, List<Long>>();
        List<Long> time = new ArrayList<Long>();

        time.add(testAddTo(linkedList, Position.BEGIN));
        time.add(testAddTo(arrayList, Position.BEGIN));
        map.put(Position.BEGIN, time);
        time = new ArrayList<Long>();

        time.add(testAddTo(linkedList, Position.MIDDLE));
        time.add(testAddTo(arrayList, Position.MIDDLE));
        map.put(Position.MIDDLE, time);
        time = new ArrayList<Long>();

        time.add(testAddTo(linkedList, Position.END));
        time.add(testAddTo(arrayList, Position.END));
        map.put(Position.END, time);

        return map;
    }

    private static Map<Position, List<Long>> testGet() {
        Map<Position, List<Long>> map = new HashMap<Position, List<Long>>();
        List<Long> time = new ArrayList<Long>();

        time.add(testGetFrom(linkedList, Position.BEGIN));
        time.add(testGetFrom(arrayList, Position.BEGIN));
        map.put(Position.BEGIN, time);
        time = new ArrayList<Long>();

        time.add(testGetFrom(linkedList, Position.MIDDLE));
        time.add(testGetFrom(arrayList, Position.MIDDLE));
        map.put(Position.MIDDLE, time);
        time = new ArrayList<Long>();

        time.add(testGetFrom(linkedList, Position.END));
        time.add(testGetFrom(arrayList, Position.END));
        map.put(Position.END, time);

        return map;
    }

    private static Map<Position, List<Long>> testRemove() {
        Map<Position, List<Long>> map = new HashMap<Position, List<Long>>();
        List<Long> time = new ArrayList<Long>();

        time.add(testRemoveFrom(linkedList, Position.BEGIN));
        time.add(testRemoveFrom(arrayList, Position.BEGIN));
        map.put(Position.BEGIN, time);
        time = new ArrayList<Long>();

        time.add(testRemoveFrom(linkedList, Position.MIDDLE));
        time.add(testRemoveFrom(arrayList, Position.MIDDLE));
        map.put(Position.MIDDLE, time);
        time = new ArrayList<Long>();

        time.add(testRemoveFrom(linkedList, Position.END));
        time.add(testRemoveFrom(arrayList, Position.END));
        map.put(Position.END, time);

        return map;
    }

    private static long testAddTo(List<Object> list, Position position) {
        switch (position) {
            case BEGIN: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    list.add(0, new Object());
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            case MIDDLE: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    list.add(list.size() / 2, new Object());
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            case END: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    list.add(list.size() - 1, new Object());
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            default:
                return -1;
        }
    }

    private static long testGetFrom(List<Object> list, Position position) {
        switch (position) {
            case BEGIN: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    Object object = list.get(0);
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            case MIDDLE: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    Object object = list.get(list.size() / 2);
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            case END: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    Object object = list.get(list.size() - 1);
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            default:
                return -1;
        }
    }

    private static long testRemoveFrom(List<Object> list, Position position) {
        switch (position) {
            case BEGIN: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    list.remove(0);
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            case MIDDLE: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    list.remove(list.size() / 2);
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            case END: {
                Date startTime = new Date();
                for(int i = 0; i < k; i++) {
                    list.remove(list.size() - 1);
                }
                Date finishTime = new Date();
                return finishTime.getTime() - startTime.getTime();
            }
            default:
                return -1;
        }
    }

    public static void setN(int n) {
        N = n;
    }

    public static void setK(int K) {
        k = K;
    }
}
