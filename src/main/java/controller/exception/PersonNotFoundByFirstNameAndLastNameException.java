package controller.exception;

public class PersonNotFoundByFirstNameAndLastNameException extends Exception {
    public PersonNotFoundByFirstNameAndLastNameException(String firstName, String lastName) {
        super(String.format("Person with first-name=%s, last-name=%s not found!", firstName, lastName));
    }
}
