import org.junit.Assert;
import org.junit.Test;

import java.security.PublicKey;

public class ArrayListTests {
    @Test
    public void testAddElemToEnd() {


        ArrayList<String> arrayList = new ArrayList<>();

        Assert.assertEquals(0, arrayList.size());

        arrayList.add("1");
        Assert.assertEquals(1, arrayList.size());

        Assert.assertEquals("1", arrayList.get(arrayList.size() - 1));
    }

    @Test
    public void testAddElemOnIndex() {
        ArrayList<String> arrayList = new ArrayList<>();

        Assert.assertEquals(0, arrayList.size());

        arrayList.add(0, "1");
        Assert.assertEquals(1, arrayList.size());

        Assert.assertEquals("1", arrayList.get(0));
    }

    @Test
    public void testGetElemByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");

        Assert.assertEquals("1", arrayList.get(0));
    }

/*    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {

        ArrayList<String> emptyList = new ArrayList<>();

        String o = emptyList.get(0);

    }*/
}
