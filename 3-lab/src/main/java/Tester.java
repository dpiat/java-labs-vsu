import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Tester {

    private Tester() {};

    private List<Object> arrayList;
    private List<Object> linkedList;

    private void setup(int N) {
        arrayList = new ArrayList<Object>();
        for (int i = 0; i < N; i++) {
            arrayList.add(new Object());
        }
        linkedList.addAll(arrayList);
    }

    public void testPerformance() {

    }

    private long testAddTo(List<Object> list, Position position, int k) {
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
}
