

public class Main {

    public static void main(String[] args) {
        ArrayList<String> arrString = new ArrayList<>();
        arrString.add("1");
        arrString.add("2");
        arrString.add("3");
        System.out.println(arrString.toString());
        System.out.println(arrString.get(0));
        System.out.println(arrString.remove("4"));
        System.out.println(arrString.remove("3"));
        System.out.println(arrString.toString());

        ArrayList<Integer> arrInteger = new ArrayList<>();
        arrInteger.add(1);
        arrInteger.add(2);
        arrInteger.add(3);
        System.out.println(arrInteger.toString());
        arrInteger.remove((Integer)1);
        System.out.println(arrInteger.toString());
    }
}
