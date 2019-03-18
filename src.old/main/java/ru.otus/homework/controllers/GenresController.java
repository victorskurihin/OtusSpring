package ru.otus.homework.controllers;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.models.Genre;

@RestController
public class GenresController
{

    private GenreDao repository;

    public GenresController(GenreDao repository) {
        this.repository = repository;
    }

    @GetMapping("/genre")
    public Flux<Genre> all() {
        return repository.findAll();
    }

    @GetMapping("/genre/{id}")
    public Mono<Genre> byId(Long id) {
        return repository.findById(id);
    }

    @PostMapping("/genre")
    public Mono<Genre> save(@RequestBody Genre dto) {
        return repository.save(dto);
    }
}
