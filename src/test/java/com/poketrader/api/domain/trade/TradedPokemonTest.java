package com.poketrader.api.domain.trade;

import com.poketrader.api.domain.pokemon.Pokemon;
import com.poketrader.api.domain.pokemon.PokemonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TradedPokemonTest {

    @Autowired
    private TradedPokemonRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void whenTradedPokemonHasInvalid_thenThrowsException() {
        //setup
        var noName = "";
        var traded = TradedPokemon.builder()
                .pokemonName(noName)
                .build();

        //execute + verify
        assertThrows(TransactionSystemException.class, () -> {
            repository.save(traded);
            entityManager.flush();
        });
    }

}