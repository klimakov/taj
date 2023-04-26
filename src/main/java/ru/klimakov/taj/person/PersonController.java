package ru.klimakov.taj.person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public Iterable<Person> getPersons() {
        return personService.findAll();
    }

    @PostMapping("/person")
    public Person savePerson(Person person) {
        return personService.save(person);
    }

    @GetMapping("/fake-person-and-return-all")
    public Iterable<Person> fakePersonAndReturnAll() {
        return personService.fakePersonAndReturnAll();
    }


}