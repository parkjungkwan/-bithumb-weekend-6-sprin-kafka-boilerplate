package net.zerotodev.api.kafka.controller;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.kafka.domain.User;
import net.zerotodev.api.kafka.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public Mono<ResponseEntity<User>> addUser(@RequestBody User user){
        return userService.save(user).map(i -> {return ResponseEntity.ok(i);})
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public Flux<User> findAll(){
        return userService.findAll();
    }
    @GetMapping(value="/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findAllSteam(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findById(@PathVariable String id){
        return userService.findById(id).map(i -> {return ResponseEntity.ok(i);})
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<User>> update(@PathVariable String id,
            @RequestBody User user){
        return userService.findById(id).flatMap( i -> {
            i.setAlias(user.getAlias());
            i.setEmail(user.getEmail());
            return userService.save(i);
        }).map(j -> {return ResponseEntity.ok(j);})
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") String id){
        return userService.deleteById(id)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
