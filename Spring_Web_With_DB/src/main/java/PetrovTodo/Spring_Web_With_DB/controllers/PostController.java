package PetrovTodo.Spring_Web_With_DB.controllers;


import PetrovTodo.Spring_Web_With_DB.entities.CreazionePost;
import PetrovTodo.Spring_Web_With_DB.entities.Post;
import PetrovTodo.Spring_Web_With_DB.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/*
1. GET http://localhost:3001/posts
2. POST http://localhost:3001/posts (+ body)
3. GET  http://localhost:3001/posts/{postId}
4. PUT http://localhost:3001/posts/{postId}


 */
@RestController
@RequestMapping("/posts")

public class PostController {

    @Autowired
    private PostService postService;

    //1. GET http://localhost:3001/posts
    @GetMapping
    private Page<Post> findAllPosts(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy) {

        return this.postService.findAll(page, size, sortBy);
    }

    //2. POST http://localhost:3001/posts (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Post createPost(@RequestBody CreazionePost body) {
        return postService.savePost(body);
    }

    //3. GET  http://localhost:3001/posts/{postId}
    @GetMapping("/{postId}")
    private Post findPostById(@PathVariable UUID postId) {
        return postService.findById(postId);
    }

    //4. PUT http://localhost:3001/posts/{postId}
    @PutMapping("/{postId}")
    private Post findByPostIdAndUpdate(@PathVariable UUID postId, @RequestBody Post body) {
        postService.findByIdAndUpdate(postId, body);
        return body;
    }

    //5. DELETE http://localhost:3001/autors/{autoreId}
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void findByIdAndDelete(@PathVariable UUID postId) {
        postService.findAndDelete(postId);
    }
}
