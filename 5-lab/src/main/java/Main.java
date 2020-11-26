import model.SomeBean;
import util.Injector;

public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        SomeBean someBean = new SomeBean();
        Injector.inject(someBean);
        someBean.foo();
    }
}
