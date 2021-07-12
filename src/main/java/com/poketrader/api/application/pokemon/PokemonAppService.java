package com.poketrader.api.application.pokemon;

import com.poketrader.api.domain.pokemon.Pokemon;
import com.poketrader.api.domain.pokemon.PokemonRepository;
import com.poketrader.api.infrastructure.exception.PokemonNotFoundException;
import com.poketrader.api.presentation.pokemon.PokemonResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;

@Service
public class PokemonAppService {

    private final PokeApiClient pokeApiClient;
    private final PokemonRepository pokemonRepository;

    public PokemonAppService(PokeApiClient pokeApiClient, PokemonRepository pokemonRepository) {
        this.pokeApiClient = pokeApiClient;
        this.pokemonRepository = pokemonRepository;
    }

    @Transactional
    public PokemonResource createNewPokemon(String name) {
        Pokemon pokemon = pokemonRepository.findByName(name);
        if (isNull(pokemon)) {
            try {
                PokemonResource pokemonResource = pokeApiClient.getPokemonByName(name);
                pokemonRepository.save(buildPokemon(pokemonResource));
                return pokemonResource;
            } catch (Exception e) {
                String errorMessage = "Could not find Pokemon with name " + name;
                throw new PokemonNotFoundException(errorMessage);
            }
        }
        return new PokemonResource(pokemon.getId(), pokemon.getName(), pokemon.getBaseExperience());
    }

    private Pokemon buildPokemon(PokemonResource resource) {
        return Pokemon.builder()
                .id(resource.getId())
                .baseExperience(resource.getBase_experience())
                .name(resource.getName())
                .build();
    }

}
