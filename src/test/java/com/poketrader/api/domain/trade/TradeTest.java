package com.poketrader.api.domain.trade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityManager;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TradeTest {

    @Autowired
    private TradeRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void whenNoPokemonIsSelected_thenThrowsException() {
        //setup
        var trade = Trade.builder()
                .status(false)
                .tradedPokemon(emptyList())
                .build();

        //execute + verify
        assertThrows(TransactionSystemException.class, () -> {
            repository.save(trade);
            entityManager.flush();
        });
    }

}