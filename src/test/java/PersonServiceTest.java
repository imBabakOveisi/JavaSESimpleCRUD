import model.entity.Person;
import model.entity.enums.PersonRole;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersonServiceTest {
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

        Person person5 =
                Person
                        .builder()
                        .firstName("hassan")
                        .lastName("karami")
                        .birthDate(LocalDate.of(2010, 2, 3))
                        .role(PersonRole.manager)
                        .active(false)
                        .build();

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
//        personList.add(person5); // throws AgeIsNotEnoughException


            // save() test ✔:
/*
            for (Person person : personList) {
                PersonService.getService().save(person);
            }
*/

            // update() test ✔:
/*
//            person4.setPersonId(4);
//            person4.setFirstName("mahtab");
//            person4.setActive(false);
//            PersonService.getService().update(person4);

//            person4.setLastName("kamali");
//            person4.setBirthDate(LocalDate.now());
//            PersonService.getService().update(person4); // throws AgeIsNotEnoughException

//            Person person6 = Person.builder()
//                    .personId(5)
//                    .firstName("asghar")
//                    .lastName("nazeri")
//                    .birthDate(LocalDate.of(2003, 1, 6))
//                    .role(PersonRole.costumer)
//                    .active(false)
//                    .build();
//            PersonService.getService().update(person6);

//        Person person = PersonService.getService().findById(2);
//        person.setFirstName("hossein");
//        person.setBirthDate(LocalDate.of(2008, 10, 27));
//        PersonService.getService().update(person);
*/

            // deleteById() test ✔:
/*
        PersonService.getService().deleteById(1);
//        PersonService.getService().deleteById(10); // throws a PersonNotFoundByIdException
*/

            // findAll() test ✔:
/*
            for(Person person : PersonService.getService().findAll()) {
                System.out.println(person);
            }
*/

            // findById() test ✔:
/*
            System.out.println(PersonService.getService().findById(4));
//            System.out.println(PersonService.getService().findById(7)); // throws PersonNotFoundByIdException
*/

            // findByFirstNameAndLastName() test ✔:
/*
//            for(Person person : PersonService.getService().findByFirstNameAndLastName("", "a")) {
//                System.out.println(person);
//            }

//        for(Person person : PersonService.getService().findByFirstNameAndLastName("p", "z")){ // throws PersonNotFoundByFirstNameAndLastName
//            System.out.println(person);
//        }
*/
    }
}
