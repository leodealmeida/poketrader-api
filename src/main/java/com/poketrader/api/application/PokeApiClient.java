package com.poketrader.api.application;

import com.poketrader.api.presentation.PokemonResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PokeAPI", url = "https://pokeapi.co/api/v2/")
public interface PokeApiClient {

    @GetMapping("/pokemon/{name}")
    PokemonResource getPokemonByName(@PathVariable("name") String name);

}
