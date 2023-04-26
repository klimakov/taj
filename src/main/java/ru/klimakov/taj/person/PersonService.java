package ru.klimakov.taj.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);
    @Autowired
    private PersonRepository personRepository;

    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public Person saveFakePersonAndRollback() {
        Person fakePerson = new Person();
        fakePerson.setId(100);
        fakePerson.setName("Fake Person");
        fakePerson.setAge(99);
        personRepository.save(fakePerson);
        throw new RuntimeException("Transaction should be rolled back");
    }

    public Iterable<Person> fakePersonAndReturnAll() {
        try {
            saveFakePersonAndRollback();
        } catch (RuntimeException ex) {
            logger.info("Exception is caught and transaction should be rolled back");
        }
        return findAll();
    }


}
