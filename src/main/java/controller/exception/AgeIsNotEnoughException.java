package controller.exception;

public class AgeIsNotEnoughException extends Exception {
    public AgeIsNotEnoughException() {
        super("Your birth date is after 2010-01-01. You are not old enough to become a manager.");
    }
}
