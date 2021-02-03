package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.data.DataStorageImpl;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        List<Person> personList = storage.findMany(p -> p.getFirstName().equals("Erik"));
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        List<Person> personList = storage.findMany(p -> p.getGender().equals(Gender.FEMALE));
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        List<Person> personList = storage.findMany(p -> p.getBirthDate().isAfter(LocalDate.of(2000, 1, 1)) ||
                p.getBirthDate().isEqual(LocalDate.of(2000, 1, 1)));
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        Person person = storage.findOne(p -> p.getId() == 123);
        System.out.println(person);
        System.out.println("----------------------");

    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message){
        System.out.println(message);
        String person = storage.findOneAndMapToString(p -> p.getId() == 456,
                p -> "Name: " + p.getFirstName() + " " + p.getLastName() + " born " + p.getBirthDate());
        System.out.println(person);
        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        List<String> personList = storage.findManyAndMapEachToString(p -> p.getFirstName().startsWith("E")
                && p.getGender().equals(Gender.MALE),
                (p) -> p.toString());
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        List<String> personList = storage.findManyAndMapEachToString(
                p -> Period.between(p.getBirthDate(), LocalDate.now()).getYears() < 10,
                p -> p.getFirstName() + " " + p.getLastName() + " " + Period.between(p.getBirthDate(), LocalDate.now()).getYears() + " years");
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        storage.findAndDo(p -> p.getFirstName().equals("Ulf"), p -> System.out.println(p));
        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        storage.findAndDo(p -> p.getLastName().contains(p.getFirstName()), p -> System.out.println(p));
        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        storage.findAndDo(p -> p.getFirstName().equalsIgnoreCase(String.valueOf(new StringBuilder().append(p.getFirstName()).reverse())),
                p -> System.out.println(p));
        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        List<Person> personList = storage.findAndSort(p -> p.getFirstName().startsWith("A"),
                Comparator.comparing(Person::getBirthDate));
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        List<Person> personList = storage.findAndSort(p -> p.getBirthDate().isBefore(LocalDate.of(1950, 1, 1)),
                Comparator.comparing(Person::getBirthDate).reversed());
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        List<Person> personList = storage.findAndSort(Comparator.comparing(Person::getLastName)
                .thenComparing(Person::getFirstName)
                .thenComparing(Person::getBirthDate));
        personList.forEach(System.out::println);
        System.out.println("----------------------");
    }
}
