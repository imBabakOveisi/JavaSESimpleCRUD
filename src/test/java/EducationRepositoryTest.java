import model.entity.Education;
import model.entity.Person;
import model.entity.enums.EducationGrade;
import model.entity.enums.PersonRole;
import model.repository.EducationRepository;
import model.repository.PersonRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class EducationRepositoryTest {
    public static void main(String[] args) throws Exception {
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

        Education education1 =
                Education
                        .builder()
                        .educationId(1)
                        .personId(1)
                        .university("sharif")
                        .educationGrade(EducationGrade.master)
                        .average(19.20)
                        .startDate(LocalDate.of(2020, 10, 23))
                        .endDate(LocalDate.of(2022, 7, 13))
                        .build();

        Education education2 =
                Education
                        .builder()
                        .educationId(2)
                        .personId(1)
                        .university("tabriz")
                        .educationGrade(EducationGrade.bachelor)
                        .average(17.59)
                        .startDate(LocalDate.of(2016, 9, 21))
                        .endDate(LocalDate.of(2020, 7, 1))
                        .build();

        Education education3 =
                Education
                        .builder()
                        .educationId(3)
                        .personId(4)
                        .university("zanjan")
                        .educationGrade(EducationGrade.bachelor)
                        .average(16.10)
                        .startDate(LocalDate.of(2000, 1, 20))
                        .endDate(LocalDate.of(2005, 7, 13))
                        .build();

        Education education4 =
                Education
                        .builder()
                        .educationId(4)
                        .personId(4)
                        .university("tehran")
                        .educationGrade(EducationGrade.doctorate)
                        .average(19.73)
                        .startDate(LocalDate.of(2009, 9, 13))
                        .endDate(LocalDate.of(2014, 5, 29))
                        .build();

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        ArrayList<Education> educationList = new ArrayList<>();
        educationList.add(education1);
        educationList.add(education2);
        educationList.add(education3);
        educationList.add(education4);

//        for (Education education : educationList) {
//            System.out.println(education);
//        }

        // EducationRepository test ✔:
        try (PersonRepository personRepository = new PersonRepository();
             EducationRepository educationRepository = new EducationRepository()) {

            // save() test ✔:
/*
            for (Person person : personList) {
                personRepository.save(person);
            }
            for (Education education : educationList) {
                educationRepository.save(education);
            }
*/

            // update() test ✔:
/*
            Education education = educationRepository.findById(4);
            education.setUniversity("amirkabir");
            education.setAverage(16.59);
            educationRepository.update(education);
*/

            // findAll() test ✔:
/*
            for (Education education : educationRepository.findAll()) {
                System.out.println(education);
            }
*/

            // findById() test ✔:
/*
            System.out.println(educationRepository.findById(4));
*/

            // findByUniversityAndEducationGrade() test:
/*
//            for (Education education : educationRepository.findByUniversityAndEducationGrade("t", "")) {
//                System.out.println(education);
//            }

//            for (Education education : educationRepository.findByUniversityAndEducationGrade("e", "master")) {
//                System.out.println(education);
//            }

//            System.out.println(educationRepository.findByUniversityAndEducationGrade("sharif", "master"));
*/
        }
    }
}
