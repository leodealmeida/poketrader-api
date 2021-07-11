package com.poketrader.api.domain.trade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradedPokemonRepository extends JpaRepository<TradedPokemon, Long> {

}
