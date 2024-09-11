package PetrovTodo.Spring_Web_With_DB.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@NoArgsConstructor
public class CreazionePost {
    private UUID idAutore;
    private String categoria;
    private String titolo;
    private String contenuto;
}
