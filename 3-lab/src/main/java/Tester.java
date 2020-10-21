import java.util.ArrayList;
import java.util.List;

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

}
