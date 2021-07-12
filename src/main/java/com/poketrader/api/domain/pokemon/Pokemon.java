package com.poketrader.api.domain.pokemon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private Integer baseExperience;

}
