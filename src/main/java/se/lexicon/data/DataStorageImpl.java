package se.lexicon.data;


import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl(){
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance(){
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
        Person foundPerson = null;
        for (Person person : personList) {
            if (filter.test(person)) {
                foundPerson = person;
                break;
            }
        }
        return foundPerson;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {
        Person foundPerson = findOne(filter);
        if (foundPerson != null) {
            return personToString.apply(foundPerson);
        }
        return null;

        /*String foundPerson = null;
        for (Person person : personList) {
            if (filter.test(person)) {
                foundPerson = personToString.apply(person);
                break;
            }
        }
        return foundPerson;*/
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString) {
        List<Person> personListFilter = findMany(filter);
        List<String> stringList = new ArrayList<>();
        for (Person person : personListFilter) {
            String convertedResult = personToString.apply(person);
            stringList.add(convertedResult);
        }
        return stringList;

        /*List<String> foundPersons = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                foundPersons.add(personToString.apply(person));
            }
        }
        return foundPersons;*/
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer){
        List<Person> find = findMany(filter);
        for (Person person : find) {
            consumer.accept(person);
        }

        /*for (Person person : personList){
            if (filter.test(person)){
                consumer.accept(person);
            }
        }*/
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator) {
        List<Person> copy = new ArrayList<>(personList);
        Collections.sort(copy, comparator);
        return copy;

        /*List<Person> foundPersons = new ArrayList<>(personList);
        foundPersons.sort(comparator);
        return foundPersons;*/
    }


    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator) {
        List<Person> personList = findMany(filter);
        personList.sort(comparator);
        return personList;

        /*List<Person> foundPersons = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                foundPersons.add(person);
            }
        }
        if (foundPersons.size() >= 2) {
            foundPersons.sort(comparator);
        }
        return foundPersons;*/
    }
}
