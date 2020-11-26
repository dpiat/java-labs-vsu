package util;

import util.AutoInjectable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Injector {

    /* Т.е. некоторый метод этого класса,
        принимал бы произвольный объект, исследовал бы существующие
        в нем поля, и смотрел, аннотированы ли они нужной аннотацией.
        Если да, то тогда он бы смотрел тип этого поля и искал бы
        реализацию в файле properties:
        После этого, он создавал бы экземпляр нужного класса и записывал
        ссылку на этот экземпляр в нужное поле. Т.е. для нашего примера
        метод inject должен в поле field1 записать экземпляр класса
        implementaionClass.SomeImpl, а в поле field2 класса implementaionClass.SODoer.
    * */

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
