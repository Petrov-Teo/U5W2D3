package PetrovTodo.Spring_Web_With_DB.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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
    private String contenuto;
    private double tempoDiLettura;

    @ManyToMany
    @JoinTable(name = "post_autori", joinColumns = @JoinColumn(name = "post_id"), inverseJoinColumns = @JoinColumn(name = "autore_id"))
    private List<AutorePost> autori;

}
