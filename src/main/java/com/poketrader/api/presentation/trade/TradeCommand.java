package com.poketrader.api.presentation.trade;

import com.poketrader.api.presentation.PokemonResource;
import lombok.Value;

import java.util.List;

@Value
public class TradeCommand {

    List<PokemonResource> sideA;
    List<PokemonResource> sideB;

}
