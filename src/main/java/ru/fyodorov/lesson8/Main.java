package ru.fyodorov.lesson8;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс реализует метод cleanup, который изменяет поля у объекта
 *
 * @author Fyodorov Alexandr
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Person person = new Person("Ivan", 20, 4.2, true);

        Map<String, Integer> map = new HashMap<>();
        map.put("averageGrade", 4);
        map.put("mathGrade", 3);
        map.put("englishGrade", 5);

        Set<String> fieldsToOutput = new HashSet<>();
        Set<String> fieldsToCleanup = new HashSet<>();
        fieldsToCleanup.add("averageGrade");

        Main main = new Main();
        main.cleanup(map, fieldsToCleanup, fieldsToOutput);
        main.cleanup(person, fieldsToCleanup, fieldsToOutput);
    }

    /**
     * Метод cleanup принимает любой объект и 2 коллекции строк.
     * В объекте, посредством reflection поля, перечисленные в fieldsToClenup устанавливает в значение null,
     * или, если поля примитивных типов в их значение по умолчанию.
     *
     * @param object любой объект
     * @param fieldsToCleanup список полей/ключей
     * @param fieldsToOutput  список полей/ключей
     * @throws IllegalAccessException выбрасывается исключение при отсутствии в объекте/мапе нужных полей/ключей
     */

    public void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput)
            throws IllegalAccessException {

        Class reflected = object.getClass();
        fieldsToOutput = new HashSet<>();

        if (reflected.getName().equals("java.util.HashMap")) {
            Map hashMap = (HashMap) object;
            Map originHashMap = new HashMap((HashMap) object);

            for (String key : fieldsToCleanup) {
                reflected.getFields();
                if (!hashMap.containsKey(key)) {
                    object = originHashMap;
                    throw new IllegalArgumentException("Такого поля не существует");
                }
                hashMap.put(key, null);
            }

            for (Object next : hashMap.entrySet()) {
                if (((Map.Entry) next).getValue() == null) {
                    fieldsToOutput.add(null);
                } else {
                    fieldsToOutput.add(((Map.Entry) next).getValue().toString());
                }
            }
        } else {
            Field[] fields = reflected.getDeclaredFields();
            for (String key : fieldsToCleanup) {
                for (Field field : fields) {
                    if (key.equals(field.getName())) {
                        if (field.getType().isPrimitive()) {
                            if (field.getType().getName().equals("boolean")) {
                                field.setAccessible(true);
                                field.set(object, false);
                                fieldsToOutput.add(field.getName() + " = " + field.get(object));

                            } else {
                                field.setAccessible(true);
                                field.set(object, 0);
                                fieldsToOutput.add(field.getName() + " = " + field.get(object));
                            }

                        }else {
                            field.setAccessible(true);
                            field.set(object, null);
                            fieldsToOutput.add(field.getName() + " = " + field.get(object));
                        }
                    }
                }
            }
        }
        System.out.println(fieldsToOutput);
    }
}