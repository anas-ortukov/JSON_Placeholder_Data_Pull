package uz.anas.homework.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import uz.anas.homework.dto.CommentDto;
import uz.anas.homework.dto.PhotoDto;
import uz.anas.homework.dto.MainDto;
import uz.anas.homework.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping("/api/elements")
public class ElementsController {

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", String.class);
        List<UserDto>  users = gson.fromJson(forObject,
                new TypeToken<List<UserDto>>() {}.getType());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<?> getPostsByUser(@PathVariable Integer userId) {
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts?userId=" + userId, String.class);
        List<MainDto>  posts = gson.fromJson(forObject,
                new TypeToken<List<MainDto>>() {}.getType());
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<?> getCommentsByPost(@PathVariable Integer postId) {
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("https://jsonplaceholder.typicode.com/comments?postId=" + postId, String.class);
        List<CommentDto>  comments = gson.fromJson(forObject,
                new TypeToken<List<CommentDto>>() {}.getType());
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/albums/{userId}")
    public ResponseEntity<?> getAlbumsByUser(@PathVariable Integer userId) {
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("https://jsonplaceholder.typicode.com/albums?userId=" + userId, String.class);
        List<MainDto>  albums = gson.fromJson(forObject,
                new TypeToken<List<MainDto>>() {}.getType());
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/photos/{albumId}")
    public ResponseEntity<?> getPhotosByUser(@PathVariable Integer albumId) {
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos?albumId=" + albumId, String.class);
        List<PhotoDto>  photos = gson.fromJson(forObject,
                new TypeToken<List<PhotoDto>>() {}.getType());
        return ResponseEntity.ok(photos);
    }

    @GetMapping("/todos/{userId}")
    public ResponseEntity<?> getTodosByUser(@PathVariable Integer userId) {
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("https://jsonplaceholder.typicode.com/todos?userId=" + userId, String.class);
        List<MainDto>  albums = gson.fromJson(forObject,
                new TypeToken<List<MainDto>>() {}.getType());
        return ResponseEntity.ok(albums);
    }

}
