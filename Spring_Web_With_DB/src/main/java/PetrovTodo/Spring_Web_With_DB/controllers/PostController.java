package PetrovTodo.Spring_Web_With_DB.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private List<Post> findAllPosts() {
        return postService.findAll();
    }

    //2. POST http://localhost:3001/posts (+ body)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Post createPost(@RequestBody Post body) {
        return postService.savePost(body);
    }

    //3. GET  http://localhost:3001/posts/{postId}
    @GetMapping("/{postId}")
    private Post findPostById(@PathVariable int postId) {
        return postService.findPostById(postId);
    }

    //4. PUT http://localhost:3001/posts/{postId}
    @PutMapping("/{postId}")
    private Post findByPostIdAndUpdate(@PathVariable int postId, @RequestBody Post body) {
        postService.findPostByIdAndUpdate(postId, body);
        return body;
    }

    //5. DELETE http://localhost:3001/autors/{autoreId}
    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void findByIdAndDelete(@PathVariable int postId) {
        postService.findAndDelete(postId);
    }
}
