package com.poketrader.api.domain.pokemon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    private Long id;
    private String name;
    private Integer baseExperience;

}
