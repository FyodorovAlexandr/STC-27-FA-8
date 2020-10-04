package ru.fyodorov.lesson8;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Класс реализует метод cleanup, который изменяет поля у объекта
 */
public class Clean {
    public void cleanup(Object object, Set<String> fieldsToCleanup, Set<String> fieldsToOutput) throws IllegalAccessException {

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

                            }
                            else {
                                field.setAccessible(true);
                                field.set(object, 0);
                                fieldsToOutput.add(field.getName() + " = " + field.get(object));
                            }

                        }else {
                            field.set(object, null);
                        }
                    }
                }
            }
        }
        System.out.println(fieldsToOutput);
    }
}