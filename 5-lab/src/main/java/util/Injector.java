package util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector {

    /**
     * Метод этого класса принимает произвольный объект, если поле имеет
     * анотацию "@AutoInjectable", то ищет реализацию в файле properties,
     * создает и записывают ссылку на экземпляр нужного класса
     *
     * @param <T> - тип передаваемого объекта
     * @param object - объект, который требует инъекции
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     */
    public static <T> T inject(T object) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class<? extends Object> objectClass = object.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(AutoInjectable.class);
            field.setAccessible(true);
            if (annotation != null) {
                String typeName = field.getType().getName();
                String className = Reader.getImplementationClass(typeName);
                Class cl = Class.forName(className);
                Object o = cl.newInstance();
                field.set(object, o);
            }
        }
        return object;
    }
}
