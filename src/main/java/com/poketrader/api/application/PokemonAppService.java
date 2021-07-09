package com.poketrader.api.application;

import com.poketrader.api.presentation.PokemonResource;
import org.springframework.stereotype.Service;

@Service
public class PokemonAppService {

    private final PokeApiClient pokeApiClient;

    public PokemonAppService(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    public PokemonResource createNewPokemon(String name) {
        PokemonResource pokemonResource = pokeApiClient.getPokemonByName(name);
        return pokemonResource;
    }

}
