package net.zerotodev.api.kafka.controller;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.kafka.domain.User;
import net.zerotodev.api.kafka.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public Mono<ResponseEntity<User>> addUser(@RequestBody User user){
        return userService.save(user).map(i -> {return ResponseEntity.ok(i);})
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public Flux<User> findAll(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public Mono<User> findById(@PathVariable String id){
        return userService.findById(id);
    }
}
