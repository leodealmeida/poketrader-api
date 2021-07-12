package com.poketrader.api.presentation.trade;

import com.poketrader.api.presentation.pokemon.PokemonResource;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Value
public class TradeCommand {

    @NotNull
    @Size(min=1, max=6)
    List<PokemonResource> sideA;

    @NotNull
    @Size(min=1, max=6)
    List<PokemonResource> sideB;

}
