package com.poketrader.api.application.trade;

import com.poketrader.api.domain.trade.Trade;
import com.poketrader.api.domain.trade.TradeRepository;
import com.poketrader.api.domain.trade.TradedPokemon;
import com.poketrader.api.domain.trade.TradedPokemonRepository;
import com.poketrader.api.presentation.pokemon.PokemonResource;
import com.poketrader.api.presentation.trade.TradeCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;

@Service
public class TradeAppService {

    private final TradeRepository tradeRepository;
    private final TradedPokemonRepository tradedPokemonRepository;
    private final TradeFairnessCheckerAppService fairnessChecker;

    public TradeAppService(TradeRepository tradeRepository,
                           TradedPokemonRepository tradedPokemonRepository,
                           TradeFairnessCheckerAppService tradeFairnessCheckerAppService) {
        this.tradeRepository = tradeRepository;
        this.tradedPokemonRepository = tradedPokemonRepository;
        this.fairnessChecker = tradeFairnessCheckerAppService;
    }

    @Transactional
    public Long createNewTrade(TradeCommand command) {
        boolean tradeStatus = fairnessChecker.isTradeFair(command.getSideA(), command.getSideB());
        List<TradedPokemon> tradedPokemonList = extractTradedPokemon(command);
        Trade trade = buildTrade(tradedPokemonList, tradeStatus);
        tradeRepository.save(trade);
        for(TradedPokemon t : tradedPokemonList) {
            t.setTrade(trade);
        }
        tradedPokemonRepository.saveAll(tradedPokemonList);
        return trade.getId();
    }

    @Transactional
    public List<Trade> getAllTrades() {
        return tradeRepository.findAll();
    }

    /* private */
    private List<TradedPokemon> extractTradedPokemon(TradeCommand cmd) {
        List<TradedPokemon> sideA = cmd.getSideA().stream()
                .map(resource -> buildTradedPokemon(resource, 'A'))
                .collect(toList());
        List<TradedPokemon> sideB = cmd.getSideB().stream()
                .map(resource -> buildTradedPokemon(resource, 'B'))
                .collect(toList());
        return concat(sideA.stream(), sideB.stream()).collect(toList());
    }

    private TradedPokemon buildTradedPokemon(PokemonResource resource, char side) {
        return TradedPokemon.builder()
                .pokemonName(resource.getName())
                .side(side)
                .build();
    }

    private Trade buildTrade(List<TradedPokemon> tradedPokemonList, boolean status) {
        return Trade.builder()
                .status(status)
                .createdAt(new Date())
                .tradedPokemon(tradedPokemonList)
                .build();
    }

}