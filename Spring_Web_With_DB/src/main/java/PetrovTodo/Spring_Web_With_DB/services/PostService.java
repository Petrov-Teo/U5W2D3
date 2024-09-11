package PetrovTodo.Spring_Web_With_DB.services;

import PetrovTodo.Spring_Web_With_DB.entities.AutorePost;
import PetrovTodo.Spring_Web_With_DB.entities.CreazionePost;
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
    @Autowired
    private AutorService autorService;


    public Page<Post> findAll(int page, int size, String sortBy) {
        if (page > 100) page = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.postRepository.findAll(pageable);
    }

    public Post savePost(CreazionePost body) {
        this.postRepository.findByTitolo(body.getTitolo()).ifPresent(autorePost -> {
            throw new BadRequestExeption("il Titolo " + body.getTitolo() + " è già esistente!");
        });
        if (body.getIdAutore() == null) {
            throw new BadRequestExeption("L'ID autore non può essere nullo.");
        }
        AutorePost foundIdAutore = autorService.findById(body.getIdAutore());
        System.out.println(foundIdAutore);
        int wordCount = body.getContenuto().split(" ").length;
        double tempoDiLetturaStimato = (double) (wordCount / 100);
        Post post = new Post(body.getCategoria(), body.getTitolo(), "https://picsum.photos/200/300", body.getContenuto(), tempoDiLetturaStimato, foundIdAutore);

        return postRepository.save(post);
    }


    public Post findById(UUID postId) {
        return this.postRepository.findById(postId).orElseThrow(() -> new NotFoundException(postId));
    }

    public Post findByIdAndUpdate(UUID postId, Post body) {
        this.postRepository.findByTitolo(body.getTitolo()).ifPresent(autorePost -> {
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
