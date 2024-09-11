package PetrovTodo.Spring_Web_With_DB.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String categoria;
    private String titolo;
    private String cover;//Immagine
    @Lob
    private String contenuto;
    private double tempoDiLettura;
    @ManyToOne
    @JoinColumn(name = "autore_id")
    private AutorePost autore;

    public Post(String categoria, String titolo, String cover, String contenuto, double tempoDiLettura, AutorePost autore) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.cover = cover;
        this.contenuto = contenuto;
        this.tempoDiLettura = tempoDiLettura;
        this.autore = autore;
    }

}
