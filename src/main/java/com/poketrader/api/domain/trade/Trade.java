package com.poketrader.api.domain.trade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trade {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private Date createdAt;

    @NotNull
    private boolean status;

    @NotNull
    @Size(min = 2)
    @OneToMany(mappedBy = "trade", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TradedPokemon> tradedPokemon;

}
