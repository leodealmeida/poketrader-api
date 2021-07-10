package com.poketrader.api.infrastructure.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PokemonNotFoundException extends RuntimeException {

    public PokemonNotFoundException(String message) {
        super(message);
    }

}