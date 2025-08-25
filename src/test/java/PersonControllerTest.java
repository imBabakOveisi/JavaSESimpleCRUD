public class PersonControllerTest {
    public static void main(String[] args) {

        // save() test ✔:
/*
        OldPersonController.getController().save(
                "ali",
                "alipour",
                LocalDate.of(1990, 3, 16),
                PersonRole.admin,
                true
        );

        OldPersonController.getController().save(
                "reza",
                "ahadi",
                LocalDate.of(2020, 3, 4),
                PersonRole.costumer,
                true
        );
        OldPersonController.getController().save(
                "mahsa",
                "keramati",
                LocalDate.now(),
                PersonRole.costumer,
                true
        );
        OldPersonController.getController().save(
                "hossein",
                "ahmadi",
                LocalDate.of(1996, 12, 7),
                PersonRole.employee,
                false
        );

//        OldPersonController.getController().save( // throws AgeIsNotEnoughException
//                "reza",
//                "rezaii",
//                LocalDate.of(2015, 1, 28),
//                PersonRole.manager,
//                false
//        );
*/

        // update() test ✔:
/*
//        OldPersonController.getController().update( // throws AgeIsNotEnoughException
//                2,
//                "reza",
//                "ahadi",
//                LocalDate.of(2020, 3, 4),
//                PersonRole.manager,
//                true
//        );

        Person person = OldPersonController.getController().findById(2);
        OldPersonController.getController().update(
                person.getPersonId(),
                "mahtab",
                "karami",
                person.getBirthDate(),
                person.getRole(),
                person.isActive()
        );

//        OldPersonController.getController().update( // throws PersonNotFoundByIdException
//                10,
//                "hassan",
//                "mardi",
//                LocalDate.of(2013, 3, 16),
//                PersonRole.costumer,
//                false
//        );
*/

    }
}
