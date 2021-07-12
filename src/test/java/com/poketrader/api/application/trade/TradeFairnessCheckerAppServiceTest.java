package com.poketrader.api.application.trade;

import com.poketrader.api.presentation.pokemon.PokemonResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class TradeFairnessCheckerAppServiceTest {

    @Autowired
    TradeFairnessCheckerAppService tradeFairnessCheckerAppService;

    @Test
    void returnTrueWhenTradedPokemonIsFair() {
        //setup
        var ditto = new PokemonResource(132L, "ditto", 101);
        var pikachu =  new PokemonResource(25L, "pikachu", 112);

        ArrayList<PokemonResource> sideA = new ArrayList<>();
        sideA.add(ditto);

        ArrayList<PokemonResource> sideB = new ArrayList<>();
        sideB.add(pikachu);

        //execute
        boolean isFair = tradeFairnessCheckerAppService.isTradeFair(sideA, sideB);

        //verify
        assertThat(isFair).isTrue();
    }

    @Test
    void returnFalseWhenTradeIsNotFair() {
        //setup
        var lowBaseExperience = new PokemonResource(132L, "ditto", 2);
        var highBaseExperience = new PokemonResource(25L, "pikachu", 999);

        ArrayList<PokemonResource> sideA = new ArrayList<>();
        sideA.add(lowBaseExperience);

        ArrayList<PokemonResource> sideB = new ArrayList<>();
        sideB.add(highBaseExperience);

        //execute
        boolean isFair = tradeFairnessCheckerAppService.isTradeFair(sideA, sideB);

        //verify
        assertThat(isFair).isFalse();
    }

}