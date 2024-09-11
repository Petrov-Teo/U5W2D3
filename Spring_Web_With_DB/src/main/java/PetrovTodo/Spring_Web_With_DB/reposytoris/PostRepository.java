package PetrovTodo.Spring_Web_With_DB.reposytoris;

import PetrovTodo.Spring_Web_With_DB.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    Optional<Post> findByTitle(String titolo);
}
