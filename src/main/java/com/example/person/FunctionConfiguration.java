package com.example.person;

import com.example.person.model.Person;
import com.example.person.service.PersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class FunctionConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(FunctionConfiguration.class, args);
    }

    @Bean
    public Function<Person, Person> createPerson(PersonService service) {
        return service::create;
    }

    @Bean
    public Function<Long, Person> getPerson(PersonService service) {
        return id -> service.get(id).orElseThrow();
    }

    @Bean
    public Consumer<Long> deletePerson(PersonService service) {
        return service::delete;
    }
}
