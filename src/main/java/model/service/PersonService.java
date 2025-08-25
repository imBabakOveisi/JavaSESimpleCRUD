package model.service;

import controller.exception.AgeIsNotEnoughException;
import controller.exception.PersonNotFoundByFirstNameAndLastNameException;
import controller.exception.PersonNotFoundByIdException;
import lombok.Getter;
import model.entity.Person;
import model.repository.PersonRepository;

import java.time.LocalDate;
import java.util.List;

public class PersonService implements Service<Person, Integer> {
    @Getter
    private final static PersonService service = new PersonService();

    private PersonService() {
    }

    @Override
    public void save(Person person) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            if (person.getBirthDate().isAfter(LocalDate.of(2010, 1, 1)) && person.getRole().name().equals("manager")) {
                throw new AgeIsNotEnoughException();
            } else {
                personRepository.save(person);
            }
        }
    }

    @Override
    public void update(Person person) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            if (person.getBirthDate().isAfter(LocalDate.of(2010, 1, 1)) && person.getRole().name().equals("manager")) {
                throw new AgeIsNotEnoughException();
            } else {
                personRepository.update(person);
            }
        }
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            if (personRepository.findById(id) == null) {
                throw new PersonNotFoundByIdException(id);
            } else {
                personRepository.deleteById(id);
            }
        }
    }

    @Override
    public List<Person> findAll() throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            return personRepository.findAll();
        }
    }

    @Override
    public Person findById(Integer id) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            Person person = personRepository.findById(id);
            if (person == null) {
                throw new PersonNotFoundByIdException(id);
            } else {
                return person;
            }
        }
    }

    public List<Person> findByFirstNameAndLastName(String firstName, String lastName) throws Exception {
        try (PersonRepository personRepository = new PersonRepository()) {
            List<Person> personList = personRepository.findByFirstNameAndLastName(firstName, lastName);
            return personList;
//            if (personList.isEmpty()) {
//                throw new PersonNotFoundByFirstNameAndLastNameException(firstName, lastName);
//            } else {
//                return personList;
//            }
        }
    }
}
