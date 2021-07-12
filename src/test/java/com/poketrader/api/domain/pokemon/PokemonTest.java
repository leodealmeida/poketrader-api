package com.poketrader.api.domain.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PokemonTest {

    @Autowired
    private PokemonRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void whenPokemonIsInvalid_thenThrowsException() {
        //setup
        var invalidName = "";
        var pokemon = Pokemon.builder()
                .id(9999L)
                .name(invalidName)
                .baseExperience(-10)
                .build();

        //execute + verify
        assertThrows(TransactionSystemException.class, () -> {
            repository.save(pokemon);
            entityManager.flush();
        });
    }

}