package com.example.person;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Person> all() {
        return repository.findAll();
    }

    @PostMapping
    public Person create(@RequestBody Person person) {
        return repository.save(person);
    }

    @GetMapping("/{id}")
    public Person get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Person update(@RequestBody Person newPerson, @PathVariable Long id) {
        return repository.findById(id).map(person -> {
            person.setName(newPerson.getName());
            person.setEmail(newPerson.getEmail());
            return repository.save(person);
        }).orElseGet(() -> {
            newPerson.setId(id);
            return repository.save(newPerson);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
