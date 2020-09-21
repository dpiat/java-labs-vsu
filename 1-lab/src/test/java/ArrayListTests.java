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

    @Test
    public void testRemoveElemByIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        Assert.assertEquals(2, arrayList.size());
        arrayList.remove(0);
        Assert.assertEquals(1, arrayList.size());
        Assert.assertEquals("2", arrayList.get(0));
    }

    @Test
    public void testRemoveElem() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        Assert.assertEquals(2, arrayList.size());
        arrayList.remove("1");
        Assert.assertEquals(1, arrayList.size());
        Assert.assertEquals("2", arrayList.get(0));
    }

    @Test
    public void testSetElemOnIndex() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        Assert.assertEquals(2, arrayList.size());
        arrayList.set(0, "3");
        Assert.assertEquals(2, arrayList.size());
        Assert.assertEquals("3", arrayList.get(0));
    }

    @Test
    public void testContainsElem() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        Assert.assertTrue(arrayList.contains("1"));
        Assert.assertFalse(arrayList.contains("2"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndexOutOfBoundsException() {
        ArrayList<String> emptyList = new ArrayList<>();
        String o = emptyList.get(0);
    }
}
