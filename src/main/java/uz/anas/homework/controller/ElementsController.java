package uz.anas.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.anas.homework.config.JwtUtil;

@RestController
@RequestMapping("/api/elements")
@RequiredArgsConstructor
public class ElementsController {

    private final JwtUtil jwtUtil;

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(jwtUtil.getDataFromAPI("https://jsonplaceholder.typicode.com/users"));
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity<?> getPostsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(jwtUtil.getDataFromAPI("https://jsonplaceholder.typicode.com/posts?userId=" + userId));
    }

    @GetMapping("/comments/{postId}")
    public ResponseEntity<?> getCommentsByPost(@PathVariable Integer postId) {
        return ResponseEntity.ok(jwtUtil.getDataFromAPI("https://jsonplaceholder.typicode.com/comments?postId=" + postId));
    }

    @GetMapping("/albums/{userId}")
    public ResponseEntity<?> getAlbumsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(jwtUtil.getDataFromAPI("https://jsonplaceholder.typicode.com/albums?userId=" +  userId));
    }

    @GetMapping("/photos/{albumId}")
    public ResponseEntity<?> getPhotosByUser(@PathVariable Integer albumId) {
        return ResponseEntity.ok(jwtUtil.getDataFromAPI("https://jsonplaceholder.typicode.com/photos?albumId=" + albumId));
    }

    @GetMapping("/todos/{userId}")
    public ResponseEntity<?> getTodosByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(jwtUtil.getDataFromAPI("https://jsonplaceholder.typicode.com/todos?userId=" + userId));
    }

}
