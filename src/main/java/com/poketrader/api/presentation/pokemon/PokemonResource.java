package com.poketrader.api.presentation.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class PokemonResource {

    Long id;
    String name;

    @JsonProperty("base_experience")
    Integer base_experience;

}
