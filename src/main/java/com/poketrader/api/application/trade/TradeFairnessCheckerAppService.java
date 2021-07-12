package com.poketrader.api.application.trade;

import com.poketrader.api.presentation.pokemon.PokemonResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeFairnessCheckerAppService {

    private final int FAIRNESS_RATE = 30;

    public boolean isTradeFair(List<PokemonResource> sideA, List<PokemonResource> sideB) {
        Integer absDifference = evalAbsDifference(sideA, sideB);
        return absDifference < FAIRNESS_RATE;
    }

    public Integer evalAbsDifference(List<PokemonResource> sideA, List<PokemonResource> sideB) {
        int totalBaseExperienceSideA = sideA.stream()
                .map(PokemonResource::getBase_experience)
                .mapToInt(Integer::intValue).sum();
        int totalBaseExperienceSideB = sideB.stream()
                .map(PokemonResource::getBase_experience)
                .mapToInt(Integer::intValue).sum();
        return Math.abs(totalBaseExperienceSideA - totalBaseExperienceSideB);
    }

}
