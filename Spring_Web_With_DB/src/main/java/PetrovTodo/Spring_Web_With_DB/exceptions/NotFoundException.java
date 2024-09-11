package PetrovTodo.Spring_Web_With_DB.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("UPSSSS, l'elemento " + id + "non trovato! Ritenta saraì più fortunato!");
    }
}
