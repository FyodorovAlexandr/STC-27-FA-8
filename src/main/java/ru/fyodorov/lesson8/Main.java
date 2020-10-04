package ru.fyodorov.lesson8;

import java.util.*;

/**
 * Точка входа в программу
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

        Clean clean = new Clean();
        clean.cleanup(map, fieldsToCleanup, fieldsToOutput);
        clean.cleanup(person, fieldsToCleanup, fieldsToOutput);
    }
}