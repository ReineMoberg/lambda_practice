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
        for(Person person : personList){
            if(filter.test(person)){
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
                foundPerson = new Person(person.getFirstName(), person.getLastName(), person.getBirthDate(), person.getGender());
                break;
            }
        }
        return foundPerson;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString) {
        String foundPerson = null;
        for (Person person : personList) {
            if (filter.test(person)) {
                foundPerson = personToString.apply(person);
                break;
            }
        }
        return foundPerson;
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString) {
        List<String> foundPersons = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                foundPersons.add(personToString.apply(person));
            }
        }
        return foundPersons;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer){
        for (Person person : personList){
            if (filter.test(person)){
                consumer.accept(person);
            }
        }
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator) {
        List<Person> foundPersons = new ArrayList<>(personList);
        foundPersons.sort(comparator);
        return foundPersons;
    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator) {
        List<Person> foundPersons = new ArrayList<>();
        for (Person person : personList) {
            if (filter.test(person)) {
                foundPersons.add(person);
            }
        }
        if (foundPersons.size() >= 2) {
            foundPersons.sort(comparator);
        }
        return foundPersons;
    }
}
