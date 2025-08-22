import model.entity.Person;
import model.entity.enums.PersonRole;
import model.repository.PersonRepository;

import java.time.LocalDate;

public class PersonRepositoryTest {
    public static void main(String[] args) throws Exception {
        // Person test ✔:
        Person person1 =
                Person
                        .builder()
                        .firstName("ali")
                        .lastName("alipour")
                        .birthDate(LocalDate.of(1996, 10, 13))
                        .role(PersonRole.admin)
                        .active(true)
                        .build();

        Person person2 =
                Person
                        .builder()
                        .firstName("reza")
                        .lastName("rezaii")
                        .birthDate(LocalDate.of(2005, 6, 23))
                        .role(PersonRole.employee)
                        .active(false)
                        .build();

        Person person3 =
                Person
                        .builder()
                        .firstName("ahmad")
                        .lastName("ahmadii")
                        .birthDate(LocalDate.of(2009, 12, 1))
                        .role(PersonRole.costumer)
                        .active(true)
                        .build();

        Person person4 =
                Person
                        .builder()
                        .firstName("mahsa")
                        .lastName("akbari")
                        .birthDate(LocalDate.of(1980, 10, 6))
                        .role(PersonRole.manager)
                        .active(true)
                        .build();

/*
        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
*/

        try (PersonRepository personRepository = new PersonRepository()) {

            // save() test ✔:
/*
            for (Person person : personList) {
                personRepository.save(person);
            }
*/

            // update() test ✔:
/*
            person4.setPersonId(4);
            person4.setFirstName("mahtab");
            person4.setActive(false);
            personRepository.update(person4);

            Person person5 = Person.builder()
                    .personId(5)
                    .firstName("asghar")
                    .lastName("nazeri")
                    .birthDate(LocalDate.of(2003, 1, 6))
                    .role(PersonRole.costumer)
                    .active(false)
                    .build();
            personRepository.update(person5);
*/

            // deleteById() test ✔:
/*
            personRepository.deleteById(1);
            personRepository.deleteById(7);
*/

            // findAll() test ✔:
/*
            for(Person person : personRepository.findAll()) {
                System.out.println(person);
            }
*/

            // findById() test ✔:
/*
            System.out.println(personRepository.findById(4));
            System.out.println(personRepository.findById(7));
*/

            // findByFirstNameAndLastName() test ✔:
/*
            for(Person person : personRepository.findByFirstNameAndLastName("z", "")) {
                System.out.println(person);
            }
*/
        }
    }
}
