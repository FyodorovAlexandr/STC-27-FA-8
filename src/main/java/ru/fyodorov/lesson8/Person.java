package ru.fyodorov.lesson8;

/**
 * Класс реализует шаблон объекта Person
 */
public class Person {

    private final String name;
    private final int age;
    private double averageGrade = 4.2;
    boolean study;

    public Person(String name, int age, double averageGrade, boolean study) {
        this.name = name;
        this.age = age;
        this.averageGrade = averageGrade;
        this.study = study;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", averageGrade=" + averageGrade +
                ", study=" + study +
                '}';
    }
}