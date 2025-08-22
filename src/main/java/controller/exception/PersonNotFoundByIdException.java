package controller.exception;

public class PersonNotFoundByIdException extends Exception {
    public PersonNotFoundByIdException(int id) {
        super(String.format("Person with id=%s not found!", id));
    }
}
