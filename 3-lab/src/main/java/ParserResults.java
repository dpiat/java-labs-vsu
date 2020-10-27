import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class printed results of testing
 */
public class ParserResults {

    /**
     * This is main method
     * @param N is the initial size of list
     * @param k is the count iterations
     */
    public static void testPerformance(int N, int k) {
        Tester tester1 = new Tester(new ArrayList<Object>(), N, k);
        Tester tester2 = new TesterLinkedList(new LinkedList<Object>(), N, k);

        System.out.println(parseResult(Method.ADD, tester1.testAdd(), tester2.testAdd()));
        System.out.println(parseResult(Method.GET, tester1.testGet(), tester2.testGet()));
        System.out.println(parseResult(Method.REMOVE, tester1.testRemove(), tester2.testRemove()));
    }

    /**
     * This method reads result and return them in tabular form
     * @param method that is being tested
     * @param map1 a speed map of ArrayList
     * @param map2 a speed map of LinkedList
     * @return tabular form
     */
    private static String parseResult(Method method, Map<Position, Long> map1, Map<Position, Long> map2) {
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
        res += String.format("%6s|%10s|%10s|\n", Position.BEGIN, map1.get(Position.BEGIN), map2.get(Position.BEGIN));
        res += String.format("%6s|%10s|%10s|\n", Position.MIDDLE, map1.get(Position.MIDDLE), map2.get(Position.MIDDLE));
        res += String.format("%6s|%10s|%10s|\n", Position.END, map1.get(Position.END), map2.get(Position.END));
        res += "-----------------------------\n";

        return res;
    }


}
