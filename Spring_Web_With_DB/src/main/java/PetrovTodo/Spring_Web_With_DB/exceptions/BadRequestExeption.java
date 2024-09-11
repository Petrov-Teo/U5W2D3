package PetrovTodo.Spring_Web_With_DB.exceptions;

public class BadRequestExeption extends RuntimeException {
    public BadRequestExeption(String st) {
        super("Verifica i campi inseriti err-400" + st);
    }
}
