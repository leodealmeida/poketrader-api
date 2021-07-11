package com.poketrader.api.domain.trade;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradedPokemon {

    @Id
    @GeneratedValue
    private Long id;

    private String pokemonName;
    private char side;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "trade_id", nullable = false)
    private Trade trade;

}
