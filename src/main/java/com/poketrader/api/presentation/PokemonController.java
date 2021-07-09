package com.poketrader.api.presentation;

import com.poketrader.api.application.PokemonAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
public class PokemonController {

    private final PokemonAppService pokemonAppService;

    public PokemonController(PokemonAppService pokemonAppService) {
        this.pokemonAppService = pokemonAppService;
    }

    @PostMapping("/pokemon")
    ResponseEntity<PokemonResource> newPokemon(@RequestParam @NotBlank String name) {
        PokemonResource newPokemon = pokemonAppService.createNewPokemon(name);
        return ResponseEntity.ok().body(newPokemon);
    }

}
