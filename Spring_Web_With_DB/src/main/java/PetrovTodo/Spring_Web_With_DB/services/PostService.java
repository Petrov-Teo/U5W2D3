package PetrovTodo.Spring_Web_With_DB.services;

import PetrovTodo.Spring_Web_With_DB.entities.Post;
import PetrovTodo.Spring_Web_With_DB.exceptions.BadRequestExeption;
import PetrovTodo.Spring_Web_With_DB.exceptions.NotFoundException;
import PetrovTodo.Spring_Web_With_DB.reposytoris.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Page<Post> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.postRepository.findAll(pageable);
    }

    public Post savePost(Post body) {
        this.postRepository.findByTitle(body.getTitolo()).ifPresent(autorePost -> {
            throw new BadRequestExeption("il Titolo " + body.getTitolo() + " è già esistente!");
        });
        int wordCount = body.getContenuto().split(" ").length;
        double tempoDiLetturaStimato = (double) (wordCount / 100);
        body.setTempoDiLettura(tempoDiLetturaStimato);
        this.postRepository.save(body);
        return body;
    }


    public Post findById(UUID postId) {
        return this.postRepository.findById(postId).orElseThrow(() -> new NotFoundException(postId));
    }

    public Post findByIdAndUpdate(UUID postId, Post body) {
        this.postRepository.findByTitle(body.getTitolo()).ifPresent(autorePost -> {
            throw new BadRequestExeption("il Titolo " + body.getTitolo() + " è già esistente!");
        });
        Post found = this.findById(postId);
        found.setCategoria(body.getCategoria());
        found.setTitolo(body.getTitolo());
        found.setCover(body.getCover());
        found.setContenuto(body.getContenuto());
        return this.postRepository.save(found);
    }

    public void findAndDelete(UUID postId) {
        Post found = this.findById(postId);
        this.postRepository.delete(found);
    }
}
