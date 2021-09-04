package net.zerotodev.api.kafka.service;

import lombok.RequiredArgsConstructor;
import net.zerotodev.api.kafka.domain.User;
import net.zerotodev.api.kafka.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    @Override
    public Mono<User> save(User entity) {
        return repository.save(entity);
    }

    @Override
    public Mono<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }
}
