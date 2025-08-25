package controller;

import controller.exception.PersonNotFoundByIdException;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import model.entity.Person;
import model.entity.enums.PersonRole;
import model.service.PersonService;

import java.time.LocalDate;

@Log4j
public class OldPersonController {
    @Getter
    private final static OldPersonController controller = new OldPersonController();

    private OldPersonController() {
    }

    public void save(
            String firstName,
            String lastName,
            LocalDate birthDate,
            PersonRole role,
            boolean active
    ) {
        try {
            Person person = Person
                    .builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .birthDate(birthDate)
                    .role(role)
                    .active(active)
                    .build();

            PersonService.getService().save(person);
            log.info(String.format("Person%s Saved Successfully.", person));
        } catch (Exception e) {
            log.error("Person Save Failed! " + e.getMessage());
        }
    }

    public void update(
            int id,
            String firstName,
            String lastName,
            LocalDate birthDate,
            PersonRole role,
            boolean active
    ) {
        try {
            Person person = Person
                    .builder()
                    .personId(id)
                    .firstName(firstName)
                    .lastName(lastName)
                    .birthDate(birthDate)
                    .role(role)
                    .active(active)
                    .build();

            if (PersonService.getService().findById(id) == null) {
                throw new PersonNotFoundByIdException(id);
            } else {
                PersonService.getService().update(person);
                log.info(String.format("Person%s Updated Successfully.", person));
            }
        } catch (Exception e) {
            log.error("Person Update Failed! " + e.getMessage());
        }
    }

    public Person findById(int id) {
        try {
            Person person = PersonService.getService().findById(id);
            log.info(String.format("Person%s Found Successfully.", person));
            return person;
        } catch (Exception e) {
            log.error("Person Find Failed! " + e.getMessage());
            return null;
        }
    }
}
