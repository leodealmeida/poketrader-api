package com.poketrader.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradedPokemon {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String pokemonName;
    private char side;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_id", nullable = false)
    @JsonBackReference
    private Trade trade;

}
