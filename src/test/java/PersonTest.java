import model.entity.Person;
import model.entity.enums.PersonRole;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersonTest {
    public static void main(String[] args) {
        // Person test ✔:
        Person person1 =
                Person
                        .builder()
                        .personId(1)
                        .firstName("ali")
                        .lastName("alipour")
                        .birthDate(LocalDate.of(1996, 10, 13))
                        .role(PersonRole.admin)
                        .active(true)
                        .build();

        Person person2 =
                Person
                        .builder()
                        .personId(2)
                        .firstName("reza")
                        .lastName("rezaii")
                        .birthDate(LocalDate.of(2005, 6, 23))
                        .role(PersonRole.employee)
                        .active(false)
                        .build();

        Person person3 =
                Person
                        .builder()
                        .personId(3)
                        .firstName("ahmad")
                        .lastName("ahmadii")
                        .birthDate(LocalDate.of(2009, 12, 1))
                        .role(PersonRole.costumer)
                        .active(true)
                        .build();

        Person person4 =
                Person
                        .builder()
                        .personId(4)
                        .firstName("mahsa")
                        .lastName("akbari")
                        .birthDate(LocalDate.of(1980, 10, 6))
                        .role(PersonRole.manager)
                        .active(true)
                        .build();

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        for (Person person : personList) {
            System.out.println(person);
        }
    }
}
